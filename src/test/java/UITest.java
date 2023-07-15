import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.sql.SQLException;
import java.time.Duration;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class UITest {

    String approvedCard = "4444 4444 4444 4441";
    String declinedCard = "4444 4444 4444 4442";

    String wrongCard = "4444 4444 4444 4440";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @AfterEach
    void clearAll() throws SQLException {
        SQLHelper.clearData();
    }

    @Test
    void shouldTestBePassedInPaymentByApprovedCard() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        String expected = "Операция одобрена Банком.";
        String actual = $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestBeFailedInPaymentByDeclinedCard() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(declinedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        String expected = "Ошибка! Банк отказал в проведении операции.";
        String actual = $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardNumberInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard.substring(0, approvedCard.length() - 1));
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(wrongCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        String expected = "Ошибка! Банк отказал в проведении операции.";
        String actual = $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardMonthDateInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("01");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("23");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверно указан срок действия карты";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardYearDateInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("20");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Истёк срок действия карты";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithoutSurnameInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithOneCharacterInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("I");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithNumbersInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan 1111");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithSpecialSymbolsInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan @!$%#");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameInRussianInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Иван Иванов");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        //
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        String expected = "Операция одобрена Банком.";
        String actual = $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCVCInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("99");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestAllEmptyFieldsInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue("");
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardMonthDateInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardYearDateInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyNameInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Поле обязательно для заполнения";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCVCInPayment() {
        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRecordsInDatabaseInPayment() {
        long countPaymentStart = SQLHelper.getRecordsCountFromPaymentEntity();

        $x("/html/body/div/div/button[1]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        long countPaymentEnd = SQLHelper.getRecordsCountFromPaymentEntity();

        long expected = countPaymentStart + 1;
        long actual = countPaymentEnd;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestBePassedInCreditByApprovedCard() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        String expected = "Операция одобрена Банком.";
        String actual = $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestBeFailedInCreditByDeclinedCard() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(declinedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        String expected = "Ошибка! Банк отказал в проведении операции.";
        String actual = $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardNumberInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard.substring(0, approvedCard.length() - 1));
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(wrongCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        String expected = "Ошибка! Банк отказал в проведении операции.";
        String actual = $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardMonthDateInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("01");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("23");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверно указан срок действия карты";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardYearDateInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("20");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Истёк срок действия карты";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithoutSurnameInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithOneCharacterInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("I");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithNumbersInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan 1111");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithSpecialSymbolsInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan @!$%#");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameInRussianInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Иван Иванов");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        //
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        String expected = "Операция одобрена Банком.";
        String actual = $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCVCInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("99");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestAllEmptyFieldsInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue("");
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardMonthDateInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardYearDateInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyNameInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Поле обязательно для заполнения";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCVCInCredit() {
        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();

        String expected = "Неверный формат";
        String actual = $(".input__sub").getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRecordsInDatabaseInCredit() {
        long countCreditStart = SQLHelper.getRecordsCountFromOrderEntity();

        $x("/html/body/div/div/button[2]").click();
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard);
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("10");
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("24");
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue("Ivan Ivanov");
        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue("999");
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(15));

        long countCreditEnd = SQLHelper.getRecordsCountFromOrderEntity();

        long expected = countCreditStart + 1;
        long actual = countCreditEnd;
        Assertions.assertEquals(expected, actual);
    }
}
