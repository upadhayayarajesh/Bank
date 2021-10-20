package backend.managers;
import backend.accounts.Account;
import backend.accounts.AccountId;
import backend.accounts.exceptions.AccountNotFound;
import backend.accounts.exceptions.InsufficientFunds;
import backend.people.Member;
import backend.people.Person;
import com.sun.source.tree.BreakTree;
import java.util.ArrayList;
import java.util.List;

public class AccountsManager implements Manager {
    private double freshAccountNumber;
    private List<Account> accounts;

    public AccountsManager() {
        this.freshAccountNumber = 1_000_000_000;
        this.accounts = new ArrayList<>();
    }


    /**
     * System report
     */
    public void report() {
        System.out.println("Accounts Manager reporting in. Current accounts:");
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println();
    }

    private double getFreshAccountNumber() {
        return freshAccountNumber++;
    }

    /**
     * Create an account for the given person with an initial balance equal to
     * the given amount. To do this you must first create an AccountId which
     * requires an account number. Get this from the getFreshAccountNumber
     * method given above. Then add the new account to the list of accounts.
     * Lastly return this account to the caller.
     * @param person Person accounts are being created for
     * @param initialBalance Starting balance for account
     * @return The freshly created account.
     */
    // TODO: Fill this in with the above logic
    public Account addAccount(Person person, double initialBalance) {
        AccountId accountId= new AccountId(person.getLastName(), getFreshAccountNumber());
        Account account = new Account(accountId,initialBalance);
        accounts.add(account);
        return account;
    }

    /**
     * Remove given members account from the system.
     * @param member Member to remove
     */
    public void removeAccount(Member member) {
        accounts.removeIf(account ->
                account.getAccountId().equals(member.getAccountId()));
    }

    /**
     * Lookup the account with the given account id. If the account is not
     * found an exception is thrown.
     * @param accountId Account id of the wanted account
     * @return The account we looked up
     * @throws AccountNotFound If the account id does not exist in the system.
     */
    public Account lookupAccount(AccountId accountId) throws AccountNotFound {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new AccountNotFound();
    }

    /**
     * If a robbery was successful then the accounts manager is responsible
     * for giving the robber their money. They must take an equal amount of
     * money from each member of the bank. If however a member does not have
     * sufficient funds to pay their part then the remaining accounts must
     * pay more. If the last account being looked at does not have sufficient
     * funds then the robber does not get as much as they are asking for.
     * @param amount Amount being demanded
     */
    // TODO: Fill this in with the above logic
    public void submitToRobbery(double amount) {
        double totalNumberAccounts= accounts.size();
        for(Account account: accounts){
            double equalAmount = amount/totalNumberAccounts;
            try{
                account.withdraw(equalAmount);
            }catch (InsufficientFunds insufficientFunds){
                totalNumberAccounts--;
            }
        }


    }

    /**
     * At the end of the day the accounts manager must make sure that
     * each account in the bank updates its balance history and then
     * gets paid interest at the given rate.
     */
    public void endOfDay(double interestRate) {
        System.out.println("Adding " + interestRate + " interest to " +
                "everyone's accounts.");
        for (Account account : this.accounts) {
            account.updateHistory();
            account.postInterest(interestRate);
        }
    }
}
