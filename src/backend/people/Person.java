package backend.people;

import java.util.ArrayList;
import java.util.List;

/**
 * Regular person with all the information that a bank needs to know.
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Address address;
    private int creditScore;
    private double monthlyIncome;
    private double debt;
    private boolean hasGoldMemberCard;

    public Person(String firstName, String lastName,
                  int age, Address address,
                  int creditScore, double monthlyIncome,
                  double debt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.creditScore = creditScore;
        this.monthlyIncome = monthlyIncome;
        this.debt = debt;
        this.hasGoldMemberCard = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void haveBirthday() {
        System.out.println("Happy Birthday " + this.firstName + "!");
        this.age++;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public boolean hasGoldMemberCard() {
        return hasGoldMemberCard;
    }

    public void setHasGoldMemberCard(boolean hasGoldMemberCard) {
        this.hasGoldMemberCard = hasGoldMemberCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                creditScore == person.creditScore &&
                Double.compare(person.monthlyIncome, monthlyIncome) == 0 &&
                Double.compare(person.debt, debt) == 0 &&
                hasGoldMemberCard == person.hasGoldMemberCard &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                address.equals(person.address);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", creditScore= " + creditScore +
                ", income= " + monthlyIncome +
                ", debt= " + debt +
                '}';
    }

    public static void main(String[] args) {
        Person person1 = new Person("Joe", "haugh", 28,
                new Address(123, "Something", 818),
                0, 0, 0);
        Person person2 = new Person("Joe", "haugh", 28,
                new Address(123, "Something", 818),
                0, 0, 0);
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        System.out.println(persons.contains(person2));
    }
}
