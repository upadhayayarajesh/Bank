package backend.managers;

import backend.accounts.Account;
import backend.accounts.exceptions.AccountNotFound;
import backend.accounts.exceptions.CannotPayFees;
import backend.people.GoldMember;
import backend.people.Member;
import backend.people.Person;
import backend.people.StandardMember;
import backend.people.exceptions.MemberNotFound;
import backend.people.exceptions.NotOldEnough;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The membership manager is responsible for adding
 * and removing members from the bank.
 * It is also responsible for upgrading or accepting new Gold
 * members. In order to become a gold member a person must have
 * a credit score above 700 and an account balance above $1,000,000.
 * The only qualification to become a normal member of
 * the bank is to be 18 years or older.
 */
public class MembershipManager implements Manager {
    private int freshMemberId;
    private List<Member> members;

    public MembershipManager() {
        this.freshMemberId = 0;
        this.members = new ArrayList<>();
    }

    /**
     * System report
     */
    public void report() {
        System.out.println("Membership Manager reporting in. Current " +
                "members:");
        for (Member member : members) {
            System.out.println(member);
        }
        System.out.println();
    }

    /**
     * Returns the current member id then increments
     * newMemberId by 1. Thus each time this is called
     * a different memberId should be returned.
     * @return Unique id for a member.
     */
    private int getFreshMemberId() {
        return freshMemberId++;
    }

    /**
     * Checks person's credit score and initial account balance
     * to see if they meet the criteria given above to become a
     * gold member. If they do not meet the criteria then they should be given
     * a normal membership.
     * @param accountsManager Responsible for creating an account
     * @param person Person looking to join the bank
     * @param initialBalance Starting balance for the person's account
     */
    // TODO: Fill this in with the above logic
    public void addMember(AccountsManager accountsManager,
                          Person person, double initialBalance)
            throws NotOldEnough {
        Account account= accountsManager.addAccount(person,initialBalance);

            if( person.getAge() >=10 && person.getCreditScore()>=700 && initialBalance >=1000000){
                Member member = new GoldMember(person ,getFreshMemberId(), account);
                members.add(member);
            }else if (person.getAge() >=10){
                Member member= new StandardMember(person,getFreshMemberId(),account);
                members.add(member);
            }else {
            throw new NotOldEnough();
        }


    }

    /**
     * Lookup the given person to find if they have a membership. If they are
     * not found then throw a MemberNotFound exception. If they are found
     * then return their member object to the caller.
     * Hint: look at lookupAccount in AccountsManager.
     * @param person Person whose membership is being queried
     * @return The member object of the person
     * @throws MemberNotFound If the member object is not found
     */
    // TODO: Fill this in with the above logic
    public Member lookupMember(Person person) throws MemberNotFound {
        for (Member member : members) {
            if(member.isPerson(person)){
                return member;
            }

        }
        throw new MemberNotFound();

    }

    /**
     * Remove the given person's membership from the bank
     * @param accountsManager Needed to also remove any accounts they have
     * @param person Person to be removed
     */
    // TODO: Fill this in with the above logic
    public void removeMember(AccountsManager accountsManager,
                             Person person) {
        try{
            Member member = lookupMember(person);
            accountsManager.removeAccount(member);
            members.remove(member);
        }catch (MemberNotFound memberNotFound){
            System.out.println(memberNotFound);
        }

    }

    /**
     * The membership manager must make sure that to post interest to each
     * members loan balances with a rate of 1% for gold
     * members and 2% for regular members.
     * Lastly they must collect membership fees from each member.
     * If a member cannot pay the fees then they must be removed.
     */
    // TODO: Fill this in with the above logic
    public void endOfDay(AccountsManager accountsManager,
                         double membershipFee)
            throws AccountNotFound {
        System.out.println();
        System.out.println("Withdrawing " + membershipFee + " from" +
                " everyone's accounts.");
        System.out.println();
        for (Member member : members) {
            Account account =accountsManager.lookupAccount(member.getAccountId());
            if(member.hasGoldMemberCard()){
                account.postInterest(0.02);
            }else {
                account.postInterest(0.01);
            }
            try {
                member.takeOutMembershipFees(membershipFee);
            }catch(CannotPayFees cannotPayFees){
                removeMember(accountsManager,member);
            }


        }
    }
}
