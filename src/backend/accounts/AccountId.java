package backend.accounts;

/**
 * Represents the unique account number associated
 * with each account in the bank. The account number
 * is 10 digits long.
 */
public class AccountId {
    private String lastName;
    private double accountNumber;

    public AccountId(String lastName, double accountNumber) {
        this.lastName = lastName;
        this.accountNumber = accountNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAccountNumber() {
        return accountNumber;
    }

    public boolean equals(AccountId other) {
        return this.getLastName().equals(other.getLastName()) &&
                this.getAccountNumber() == other.getAccountNumber();
    }

    @Override
    public String toString() {
        return "AccountId{" +
                "lastName='" + lastName + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
