package backend.managers;
import backend.accounts.Account;
import backend.accounts.exceptions.AccountNotFound;
import backend.people.Member;
import backend.people.Person;
import backend.people.exceptions.MemberNotFound;

import javax.swing.*;

/**
 * The loans manager is responsible for approving or denying loan requests.
 */
public class LoansManager implements Manager {

    public LoansManager() {}

    /**
     * System report.
     */
    public void report() {
        System.out.println("Loan Manager checking in.");
    }

    /**
     * Debt to equity ratio is calculated as follows:
     * [debt + (current loan balance)] /
     * [(bank account balance) + (yearly income)]
     *
     * The following rules are followed when deciding whether someone
     * can get a loan:
     * Must be 18 years or older
     *
     * For loans less than or equal to $10,000 no other criteria are considered:
     *
     * For loans between $10,000 and $100,000 you must have a debt to equity
     * ratio of less than or equal to 1 and a credit score greater than 600.
     *
     * For loans between $100,000 and $1,000,000 you must have a debt to equity
     * ratio of less than or equal to 0.5 and a credit score greater than 700.
     *
     * For loans greater than 1,000,000 you must have a debt to equity ratio of
     * 0 and credit score greater than 800.

     * @param person Person applying for a loan
     * @param amount Amount of the loan
     * @return Boolean representing if the loan was approved
     */
    // TODO: Fill this in with the above logic
    public boolean processNewLoanRequest(Person person,
                                         Member member,
                                         Account account,
                                         double amount) {

        double equalityRatio=(person.getDebt()+account.loanBalance())/(account.balance()+person.getMonthlyIncome()*12);

             if (member.hasGoldMemberCard())
             {return true;}
            else if ( person.getAge()>=18 && account.loanBalance() <= 10000)
            { return true; }
            else if( person.getAge()>=18 && equalityRatio <= 1 && person.getCreditScore() >= 600 &&
                    (account.loanBalance()> 10000 && account.loanBalance() < 100000))
            { return true; }
            else if( person.getAge()>=18 &&  equalityRatio <= 0.5 && person.getCreditScore() >= 700 &&
                    (account.loanBalance()> 100000 && account.loanBalance() < 1000000))
            { return true; }
            else if ( person.getAge()>=18 &&  equalityRatio == 0 && person.getCreditScore() >= 800
                    && account.loanBalance() >1000000)
            { return true; }

        return false;
    }
}
