package backend.accounts.exceptions;

public class CannotPayFees extends Exception  {
    public CannotPayFees() {
        super("This member can no longer pay their fees and must" +
                "be removed from the bank");
    }
}
