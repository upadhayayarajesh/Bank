package backend.people;

import backend.accounts.Account;
import backend.accounts.AccountId;
import backend.accounts.exceptions.CannotPayFees;
import backend.accounts.exceptions.InsufficientFunds;

/**
 * A member of the bank
 */
public abstract class Member extends Person {
    private int memberId;
    private Account account;

    public Member(Person person, int memberId, Account account) {
        super(person.getFirstName(), person.getLastName(), person.getAge(),
                person.getAddress(), person.getCreditScore(),
                person.getMonthlyIncome(), person.getDebt());
        this.memberId = memberId;
        this.account = account;
    }

    public AccountId getAccountId() {
        return account.getAccountId();
    }

    public void takeOutMembershipFees(double amount) throws CannotPayFees {
        try {
            this.account.withdraw(amount);
        }
        catch (InsufficientFunds exc) {
            throw new CannotPayFees();
        }
    }

    public boolean isPerson(Person person) {
        return this.getFirstName().equals(person.getFirstName()) &&
                this.getLastName().equals(person.getLastName()) &&
                this.getAge() == person.getAge() &&
                this.getAddress().equals(person.getAddress());
    }
}
