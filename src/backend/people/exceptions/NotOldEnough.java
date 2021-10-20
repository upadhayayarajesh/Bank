package backend.people.exceptions;

public class NotOldEnough extends Exception {
    public NotOldEnough() {
        super("Person is not old enough to join the bank");
    }
}
