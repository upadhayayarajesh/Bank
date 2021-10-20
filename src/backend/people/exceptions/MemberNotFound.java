package backend.people.exceptions;

public class MemberNotFound extends Exception {
    public MemberNotFound() {
        super("Member not found");
    }
}
