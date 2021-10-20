package frontend.parser.classes;

import backend.people.Person;

/**
 * A request being made to the bank. This is equivalent to a line in the
 * input files.
 */
public class Request {
    private Person person;
    private RequestType requestType;
    private double requestAmount;

    public Request(Person person, RequestType requestType,
                   double requestAmount) {
        this.person = person;
        this.requestType = requestType;
        this.requestAmount = requestAmount;
    }

    public Person getPerson() {
        return person;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    @Override
    public String toString() {
        return "Request{" +
                "person=" + person +
                ", requestType=" + requestType +
                ", requestAmount=" + requestAmount +
                '}';
    }
}
