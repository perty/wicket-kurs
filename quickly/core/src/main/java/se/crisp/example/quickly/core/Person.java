package se.crisp.example.quickly.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@EqualsAndHashCode(exclude = {"Id"})
@Entity
public class Person implements Serializable {

    private static Person nullObject = new Person();

    @Id
    @GeneratedValue
    private Long Id;
    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String zip = "";
    private String city = "";

    public static Person nullObject() {
        return nullObject;
    }

    // Follows a type safe builder with flow style. Hard to read but a breeze to use.
    // Person.builder().firstName("Per").....build();
    public static IFirstName builder() {
        return new Builder();
    }

    public static class Builder implements IBuild, IFirstName, ILastName, IAddress, IZip, ICity {

        private String firstName;
        private String lastName;
        private String address;
        private String zip;
        private String city;

        @Override
        public Person build() {
            Person person = new Person();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setAddress(address);
            person.setZip(zip);
            person.setCity(city);
            return person;
        }

        @Override
        public IZip address(String address) {
            this.address = address;
            return this;
        }

        @Override
        public IBuild city(String city) {
            this.city = city;
            return this;
        }

        @Override
        public ILastName firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public IAddress lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public ICity zip(String zip) {
            this.zip = zip;
            return this;
        }
    }

    public interface IBuild {
        Person build();
    }

    public interface IFirstName {
        ILastName firstName(String firstName);
    }

    public interface ILastName {
        IAddress lastName(String lastName);
    }

    public interface IAddress {
        IZip address(String address);
    }

    public interface IZip {
        ICity zip(String zip);
    }

    public interface ICity {
        IBuild city(String city);
    }
}
