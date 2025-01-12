package net.roadtocareer.dailyfinance.utils;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 ** 2025, January 10, Friday, 12:44 PM
 */
public class MyDataGenerator {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String gender;

    public MyDataGenerator() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() +
                new Random().nextInt(1000) + "@gmail.com";
    }

    public MyDataGenerator(String emailPrefix) {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = emailPrefix + "+" + new Random().nextInt(1000) + "@gmail.com";
        this.password = "1234";
        this.phoneNumber = "015" + generateRandom8Digit();
        this.address = faker.address().fullAddress();
        this.gender = new ArrayList<String>(List.of("Male", "Female")).get(new Random().nextInt(2));
    }

    public int generateRandom8Digit() {
        return (int) (Math.random() * (99999999 - 10000000) + 10000000);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MySupplier{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
