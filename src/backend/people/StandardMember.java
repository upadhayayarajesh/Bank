package backend.people;

import backend.accounts.Account;

/**
 * A Standard member of the bank
 */
public class StandardMember extends Member {

    public StandardMember(Person person, int memberId, Account account) {
        super(person, memberId, account);
    }
}
