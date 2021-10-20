package backend.accounts;

import backend.accounts.exceptions.InsufficientFunds;

import java.util.Arrays;

/**
 * The most important idea at any bank is the accounts of the members.
 * Thus this class contains a lot of logic to do all the transactions
 * performed at a bank.
 * An account is given a variable amount of interest
 * each week based on the average balance of the account
 * over the past 7 days.
 */
public class Account {
    private AccountId accountId;
    private double balance;
    private double[] balanceHistory;
    private double loanBalance;

    public Account(AccountId accountId,
                   double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.balanceHistory = new double[7];
        Arrays.fill(balanceHistory, balance);
        this.loanBalance = 0;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public double balance() {
        return balance;
    }

    public double[] balanceHistory() {
        return balanceHistory;
    }

    public double loanBalance() {
        return loanBalance;
    }

    /**
     * Withdraw the given amount from the account.
     * Throws an exception if the account does not
     * have sufficient funds.
     * @param amount Amount to withdraw
     * @throws InsufficientFunds
     */

    // TODO: Fill this in with the above logic
    public void withdraw(double amount) throws InsufficientFunds {
        if (amount<=balance) {
            balance-=amount;
        }else {
            throw new InsufficientFunds();
        }
    }

    /**
     * Deposit the given amount into the account
     * @param amount Amount to deposit
     */
    // TODO: Fill this in with the above logic
    public void deposit(double amount) {
        balance+=amount;

    }

    /**
     * Add the given amount to the loan balance
     * @param amount Amount to add
     */
    // TODO: Fill this in with the above logic
    public void addLoan(double amount) {
        loanBalance+=amount;

    }

    /**
     * Subtract the given amount from the loan balance.
     * @param amount Amount to pay
     */
    // TODO: Fill this in with the above logic
    public void payLoan(double amount) {
        loanBalance-=amount;
    }

    /**
     * Give interest to the loan balance equal to the given rate.
     * The formula for giving interest is:
     * loan balance = (loan balance) * rate
     * @param rate Rate of interest accruing on loan. Given as decimal
     *             between 0 and 1.
     */
    // TODO: Fill this in with the above logic
    public void payLoanInterest(double rate) {

        loanBalance=rate*loanBalance;
    }

    /**
     * Update the history with a new amount. The balanceHistory only holds
     * the last 7 account balances. Thus you must remove the oldest balance
     * and add the current balance into the array.
     */
    // TODO: Fill this in with the above logic
    public void updateHistory() {
           for(int i=0;i<6;i++){
               balanceHistory[i]=balanceHistory[i+1];
           }
            balanceHistory[6]=balance;
    }

    /**
     * Add interest income to this account equal to:
     * balance = balance +
     *   (average balance over the past 7 days) * interestRate
     */
    // TODO: Fill this in with the above logic
    public void postInterest(double interestRate) {
        double avgBalc=0;
        for(int i=0; i<balanceHistory.length;i++){
            avgBalc+=balanceHistory[i];
        }
        avgBalc/=7;
        balance=balance+avgBalc*interestRate;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", balanceHistory=" + Arrays.toString(balanceHistory) +
                ", loanBalance=" + loanBalance +
                '}';
    }
}
