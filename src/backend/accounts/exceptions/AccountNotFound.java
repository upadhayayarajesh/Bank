package backend.accounts.exceptions;

public class AccountNotFound extends Exception {
    public AccountNotFound() {
        super("We cannot find that account");
    }
}
