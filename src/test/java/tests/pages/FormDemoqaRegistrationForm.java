package tests.pages;

import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class FormDemoqaRegistrationForm extends TestBase {

    @Test
    public void fulfillFormTest() {
        step("Open the student registration form page", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill student registration form", () -> {
            step("Fill common data", () -> {
                $("#firstName").val(firstName);
                $("#lastName").val(lastName);
                $("#userEmail").val(email);
                $(byText("Male")).click();
                $("#userNumber").val(userNumber);
            });
            step("Fill subjects", () -> {
                $("#subjectsInput").setValue(subjectInput1).pressEnter();
                $("#subjectsInput").setValue(subjectInput2).pressEnter();
            });
            step("Fill hobby", () -> {
                $(byText("Sports")).click();
                $(byText("Reading")).click();
            });
            step("Fill address", () -> {
                $("#currentAddress").setValue(cAddress);
                $("#state").scrollIntoView(true);
                $("#state").click();
                $("#stateCity-wrapper").$(byText(state)).click();
                $("#city").click();
                $("#stateCity-wrapper").$(byText(city)).click();
            });
            step("Uploading picture", () -> {
                $("#uploadPicture").uploadFromClasspath("111.jpg");
            });
            step("B-day data", () -> {
                $("#dateOfBirthInput").clear();
                $(".react-datepicker__month-select").selectOption(monthOfBirth);
                $(".react-datepicker__year-select").selectOption(yearOfBirth);
                $(".react-datepicker__day--0" + dayOfBirth).click();
            });
            step("Form submission", () -> $("#submit").click());
        });
        step("Verify submitted successful form ", () -> {
            $(".modal-content").shouldHave(
                    text(firstName + " " + lastName),
                    text(email),
                    text("Male"),
                    text(userNumber),
                    text("02 April,1986"),
                    text(subjectInput1 + ", " + subjectInput2),
                    text("Sports, Reading"),
                    text("111.jpg"),
                    text(cAddress),
                    text(state + " " + city));
/*            $("#closeLargeModal").click();
            $(".modal-content").shouldNotBe(visible);*/
        });
    }
}
