package backend.managers;
import backend.accounts.Account;
import backend.accounts.AccountId;
import backend.accounts.exceptions.AccountNotFound;
import backend.accounts.exceptions.InsufficientFunds;
import backend.people.Member;
import backend.people.Person;
import backend.people.exceptions.MemberNotFound;
import backend.people.exceptions.NotOldEnough;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class serves as the bridge between the front end and the back end.
 * Each RequestType has a corresponding method here. These requests are then
 * directed to the appropriate manager.
 * If a MemberNotFound or AccountNotFound exception is thrown then
 * that person should be given a membership or account and the request
 * should be retried.
 * Each person should be checked by the SecurityManager to see if they are on
 * their list. If they are they should be immediately turned away.
 * If not then they can proceed with their request. A person gets on the
 * security manager's list by attempting to rob the bank whether successful
 * or not. Thus a person should only be able to rob the bank once.
 */
public class RequestManager implements Manager {
    /**
     * All the managers that are used by this different process methods
     * to do the necessary work.
     */
    private AccountsManager accountsManager;
    private MembershipManager membershipManager;
    private LoansManager loansManager;
    private SecurityManager securityManager;

    public RequestManager() {
        this.accountsManager = new AccountsManager();
        this.membershipManager = new MembershipManager();
        this.loansManager = new LoansManager();
        this.securityManager = new SecurityManager();
    }

    /**
     * Report the state of the system to the command line
     */
    public void report() {
        accountsManager.report();
        membershipManager.report();
        loansManager.report();
        securityManager.report();
    }

    /**
     * Process a request to join the bank. The security manager must first
     * screen the person. If they pass the screening then the membership
     * manager must attempt to add the person as a new member. If the person
     * is not old enough then they must be turned away.
     * @param person Person attempting to join the bank
     * @param amount Initial amount they want to put into their account
     */
    public void processJoinRequest(Person person, double amount) {
        if (!securityManager.isBanned(person)) {
            try {
                membershipManager.addMember(accountsManager, person, amount);
            } catch (NotOldEnough notOldEnough) {
                System.out.println(notOldEnough);
            }
        }
    }

    /**
     * The security manager must first screen the person. If they pass the
     * screening then the membership manager must remove the person from the
     * bank's membership.
     * @param person Person who wants to quit the bank
     */
    // TODO: Fill this in with the above logic
    public void processQuitRequest(Person person) {
        if(!securityManager.isBanned(person)){
            membershipManager.removeMember(accountsManager,person);
        }

    }

    /**
     * The security manager must first screen the person. If they pass the
     * screening then the account manager must first get the person's account
     * from their list of accounts. Then perform the deposit on the account.
     *
     * If the member is not found then they should be added as a member and
     * the request should be retried.
     * If the account is not found then an account should be added and the
     * request should be retried.
     * In both cases their initial balance in the account should be equal
     * to their monthly income.
     * @param person Person performing deposit
     * @param amount Amount to deposit
     */
    // TODO: Fill this in with the above logic
    public void processDepositRequest(Person person, double amount)  throws MemberNotFound,AccountNotFound,NotOldEnough{
       if(!securityManager.isBanned(person)){

           try {
               Account account = accountsManager.lookupAccount(membershipManager.lookupMember(person).getAccountId());
               account.deposit(amount);
               } catch (AccountNotFound accountNotFound) {
                   accountsManager.addAccount(person, person.getMonthlyIncome());
                   processDepositRequest(person, amount);

               } catch (MemberNotFound memberNotFound) {
                   try {
                       membershipManager.addMember(accountsManager, person,person.getMonthlyIncome());
                       processDepositRequest(person, amount);
                   } catch (NotOldEnough notOldEnough) {
                       System.out.println(notOldEnough);
                   }
           }

       }

    }

    /**
     * The security manager must first screen the person. If they pass the
     * screening then the accounts manager must lookup their account.
     * Then they must withdraw the given amount from the account.
     * If the member is not found then they should be added as a member and
     * the request should be retried.
     * If the account is not found then an account should be added and the
     * request should be retried.
     * In both cases their initial balance in the account should be equal
     * to their monthly income.
     * If they do not have sufficient funds to make the withdrawal then
     * they should be turned away.
     * @param person Person attempting to withdraw from their account
     * @param amount Amount to withdraw
     */
    // TODO: Fill this in with the above logic
    public void processWithdrawRequest(Person person, double amount)  throws MemberNotFound,AccountNotFound,NotOldEnough {
        if(!securityManager.isBanned(person)){
            try {
                Account account = accountsManager.lookupAccount(membershipManager.lookupMember(person).getAccountId());
                account.withdraw(amount);
            } catch (AccountNotFound accountNotFound) {
                accountsManager.addAccount(person, person.getMonthlyIncome());
                processWithdrawRequest(person, amount);

            } catch (MemberNotFound memberNotFound) {
                try {
                    membershipManager.addMember(accountsManager, person,person.getMonthlyIncome());
                    processWithdrawRequest(person, amount);
                } catch (NotOldEnough notOldEnough) {
                    System.out.println(notOldEnough);
                }
            } catch (InsufficientFunds insufficientFunds) {
                System.out.println(insufficientFunds);
            }

        }

    }

    /**
     * The security manager must first screen the person. If the person passes
     * the screening then the loan manager first must lookup their membership
     * and account. Then they can proceed to make a judgement on whether or not
     * they qualify for a loan. If they do qualify then the loan should be
     * added to the account using the addLoan method.
     * If they do no qualify then they need to be turned away.
     * If the member is not found then they should be added as a member and
     * the request should be retried.
     * If the account is not found then an account should be added and the
     * request should be retried.
     * In both cases their initial balance in the account should be equal
     * to their monthly income.
     * @param person
     * @param amount
     */
    // TODO: Fill this in with the above logic
    public void processNewLoanRequest(Person person, double amount) {
        if(!securityManager.isBanned(person)){
            try {
                Member member =  membershipManager.lookupMember(person);
                Account account = accountsManager.lookupAccount(membershipManager.lookupMember(person).getAccountId());
                if (loansManager.processNewLoanRequest(person, member, account, amount)){
                    account.addLoan(amount);
                }
            } catch (MemberNotFound memberNotFound) {
                try {
                    membershipManager.addMember(accountsManager, person,person.getMonthlyIncome());
                    processNewLoanRequest(person,amount);
                } catch (NotOldEnough notOldEnough) {
                    System.out.println(notOldEnough);
                }
            } catch (AccountNotFound accountNotFound) {
                accountsManager.addAccount(person, person.getMonthlyIncome());
                processNewLoanRequest(person, amount);
            }

        }
    }

    /**
     * The security manager must first screen the person. If they pass the
     * screening then the accounts manager must use the payLoan method to pay
     * the given amount against someone's loan.
     * @param person Person attempting to pay loan payment
     * @param amount Amount to pay to loan
     */
    // TODO: Fill this in with the above logic
    public void processPayLoanRequest(Person person, double amount) {
        if(!securityManager.isBanned(person)){
            try {
                Account account = accountsManager.lookupAccount(membershipManager.lookupMember(person).getAccountId());
                account.payLoan(amount);
            } catch (AccountNotFound accountNotFound) {
                System.out.println(accountNotFound);
            } catch (MemberNotFound memberNotFound) {
                System.out.println(memberNotFound);
            }

        }

    }

    /**
     * The security manager must first screen the person. If they pass the
     * screening then there should be a 5% chance that the robbery succeeds.
     * If they succeed then the amount they want is siphoned from each member
     * of the bank's accounts. If they do not succeed then they are removed from
     * the store and added to the security manager's list.
     * @param person Person attempting the robbery
     * @param amount Amount they are demanding
     */
    public void processRobbery(Person person, double amount) {
        if (!securityManager.isBanned(person)) {
            Random random = new Random();
            int randInt = random.nextInt(100);
            if (randInt < 5) {
                System.out.println("Robbery was successful... paying them " +
                        amount + " now.");
                accountsManager.submitToRobbery(amount);
            } else {
                System.out.println("We thwarted the robbery! " +
                        person + " removed.");
            }
            securityManager.addBannedPerson(person);
        }
    }

    /**
     * Process the tasks for the end of the day.
     * The accounts manager should go first followed by
     * the membership manager.
     * @param membershipFee The fee charged each day to remain a member
     * @param accountInterestRate The current interest rate given to accounts
     */
    public void processEndOfDay(double membershipFee,
                                double accountInterestRate) {
        try {
            accountsManager.endOfDay(accountInterestRate);
            membershipManager.endOfDay(accountsManager, membershipFee);
        }
        catch (AccountNotFound exc) {
            System.out.println(exc);
        }

    }
}
