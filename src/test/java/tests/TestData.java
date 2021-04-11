package tests;

import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();

    public final String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            userNumber = faker.numerify("##########"),
            dayOfBirth = "02",
            monthOfBirth = "April",
            yearOfBirth = "1986",
            subjectInput1 = "English",
            subjectInput2 = "Biology",
            cAddress = faker.address().fullAddress(),
            state = "Rajasthan",
            city = "Jaiselmer";
    public static final Integer issueNumber = 1;
}
