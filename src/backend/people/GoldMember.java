package backend.people;

import backend.accounts.Account;

/**
 * A special member of the bank
 */
public class GoldMember extends Member {

    public GoldMember(Person person, int memberId, Account account) {
        super(person, memberId, account);
        super.setHasGoldMemberCard(true);
    }

    @Override
    public void takeOutMembershipFees(double amount) {}
}
