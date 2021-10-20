package backend.accounts.exceptions;

public class InsufficientFunds extends Exception {
    public InsufficientFunds() {
        super("Unfortunately you have insufficient funds to make that " +
                "transaction.");
    }
}
