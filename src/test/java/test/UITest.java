package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataGenerator;
import data.SQLHelper;
import page.FormPage;
import org.junit.jupiter.api.*;

import io.qameta.allure.selenide.AllureSelenide;

import java.util.Calendar;

import static java.lang.String.valueOf;

public class UITest {
    private FormPage formPage;

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
        formPage = new FormPage();
    }

    @AfterEach
    void clearAll() {
        SQLHelper.clearData();
    }

    @Test
    void shouldTestBePassedInPaymentByApprovedCard() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        String expected = "Операция одобрена Банком.";
        String actual = formPage.actualMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestBeFailedInPaymentByDeclinedCard() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateDeclinedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        String expected = "Ошибка! Банк отказал в проведении операции.";
        String actual = formPage.actualMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardNumberInPayment() {
        formPage.buyOnPayment();
        formPage.setWrongCardNumber(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateWrongCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        String expected = "Ошибка! Банк отказал в проведении операции.";
        String actual = formPage.actualMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardMonthDateInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateWrongDateMonth());
        formPage.setCardYear(DataGenerator.generateWrongDateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверно указан срок действия карты";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardYearDateInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateWrongYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Истёк срок действия карты";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithoutSurnameInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameWithoutSurname());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithOneCharacterInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameWithOneCharacter());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithNumbersInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameWithNumbers());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithSpecialSymbolsInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameWithSpecialSymbols());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameInRussianInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameInRussian());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        String expected = "Операция одобрена Банком.";
        String actual = formPage.actualMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCVCInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateWrongCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestAllEmptyFieldsInPayment() {
        formPage.buyOnPayment();
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardInPayment() {
        formPage.buyOnPayment();
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardMonthDateInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardYearDateInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyNameInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Поле обязательно для заполнения";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCVCInPayment() {
        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRecordsInDatabaseInPayment() {
        long countPaymentStart = SQLHelper.getRecordsCountFromPaymentEntity();

        formPage.buyOnPayment();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        long countPaymentEnd = SQLHelper.getRecordsCountFromPaymentEntity();

        long expected = countPaymentStart + 1;
        long actual = countPaymentEnd;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestBePassedInCreditByApprovedCard() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        String expected = "Операция одобрена Банком.";
        String actual = formPage.actualMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestBeFailedInCreditByDeclinedCard() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateDeclinedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        String expected = "Ошибка! Банк отказал в проведении операции.";
        String actual = formPage.actualMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardNumberInCredit() {
        formPage.buyOnCredit();
        formPage.setWrongCardNumber(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateWrongCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        String expected = "Ошибка! Банк отказал в проведении операции.";
        String actual = formPage.actualMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardMonthDateInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateWrongDateMonth());
        formPage.setCardYear(DataGenerator.generateWrongDateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверно указан срок действия карты";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCardYearDateInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateWrongYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Истёк срок действия карты";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithoutSurnameInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameWithoutSurname());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithOneCharacterInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameWithOneCharacter());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithNumbersInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameWithNumbers());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameWithSpecialSymbolsInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateNameWithSpecialSymbols());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestNameInRussianInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateNameInRussian());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        String expected = "Операция одобрена Банком.";
        String actual = formPage.actualMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestWrongCVCInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateWrongCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestAllEmptyFieldsInCredit() {
        formPage.buyOnCredit();
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardInCredit() {
        formPage.buyOnCredit();
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardMonthDateInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCardYearDateInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyNameInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();

        String expected = "Поле обязательно для заполнения";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestEmptyCVCInCredit() {
        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.pushContinueButton();

        String expected = "Неверный формат";
        String actual = formPage.actualErrorMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRecordsInDatabaseInCredit() {
        long countCreditStart = SQLHelper.getRecordsCountFromOrderEntity();

        formPage.buyOnCredit();
        formPage.setCard(DataGenerator.generateApprovedCard());
        formPage.setCardMonth(DataGenerator.generateMonth());
        formPage.setCardYear(DataGenerator.generateYear());
        formPage.setCardOwner(DataGenerator.generateName());
        formPage.setCardCVC(DataGenerator.generateCVC());
        formPage.pushContinueButton();
        formPage.waitingForMessage();

        long countCreditEnd = SQLHelper.getRecordsCountFromOrderEntity();

        long expected = countCreditStart + 1;
        long actual = countCreditEnd;
        Assertions.assertEquals(expected, actual);
    }
}
