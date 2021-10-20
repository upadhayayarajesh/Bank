package backend.people;

public class Address {
    private int streetNumber;
    private String streetName;
    private int zipCode;

    public Address(int streetNumber, String streetName, int zipCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.zipCode = zipCode;
    }

    public boolean equals(Address other) {
        return this.streetNumber == other.streetNumber &&
                this.streetName.equals(other.streetName) &&
                this.zipCode == other.zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNumber=" + streetNumber +
                ", streetName=" + streetName +
                ", zipCode=" + zipCode +
                "}";
    }
}
