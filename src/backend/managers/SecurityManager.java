package backend.managers;

import backend.people.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * The security manager is responsible for remembering people who robbed the
 * bank.
 */
public class SecurityManager implements Manager {
    private List<Person> notAllowed;

    public SecurityManager() {
        this.notAllowed = new ArrayList<>();
    }

    public void report() {
        System.out.println("Security manager reporting in. Current people" +
                " not allowed in the bank:");
        System.out.println(notAllowed);
    }

    public void addBannedPerson(Person person) {
        this.notAllowed.add(person);
    }

    public boolean isBanned(Person person) {
        return this.notAllowed.contains(person);
    }
}
