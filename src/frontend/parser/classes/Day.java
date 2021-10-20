package frontend.parser.classes;

import java.util.List;

/**
 * A day for the bank
 */
public class Day {
    private int number;
    private List<Request> requests;

    public Day(int number, List<Request> requests) {
        this.number = number;
        this.requests = requests;
    }

    public int getNumber() {
        return number;
    }

    public List<Request> getRequests() {
        return requests;
    }
}
