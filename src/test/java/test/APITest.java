package test;

import data.DataGenerator;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.RequestHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

public class APITest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void checkRequestDatabaseInPaymentByApprovedCard() {
        var response = RequestHelper.postRequestPayment(DataGenerator.generateApprovedCard(), "24", "10", "Ivan Ivanov", "999");

        String expected = DataGenerator.generateApprovedStatus();
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRequestDatabaseInPaymentByDeclinedCard() {
        var response = RequestHelper.postRequestPayment(DataGenerator.generateDeclinedCard(), "24", "10", "Ivan Ivanov", "999");

        String expected = DataGenerator.generateDeclinedStatus();
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRequestDatabaseInPaymentByWrongCard() {
        var response = RequestHelper.postRequestPayment(DataGenerator.generateWrongCard(), "24", "10", "Ivan Ivanov", "999");

        boolean expectedBadRequest = true;
        boolean actual = response.body().contains("Bad Request");
        Assertions.assertEquals(expectedBadRequest, actual);
    }

    @Test
    void checkRequestDatabaseInCreditByApprovedCard() {
        var response = RequestHelper.postRequestCredit(DataGenerator.generateApprovedCard(), "24", "10", "Ivan Ivanov", "999");

        String expected = DataGenerator.generateApprovedStatus();
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRequestDatabaseInCreditByDeclinedCard() {
        var response = RequestHelper.postRequestCredit(DataGenerator.generateDeclinedCard(), "24", "10", "Ivan Ivanov", "999");

        String expected = DataGenerator.generateDeclinedStatus();
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRequestDatabaseInCreditByWrongCard() {
        var response = RequestHelper.postRequestCredit(DataGenerator.generateWrongCard(), "24", "10", "Ivan Ivanov", "999");

        boolean expectedBadRequest = true;
        boolean actual = response.body().contains("Bad Request");
        Assertions.assertEquals(expectedBadRequest, actual);
    }

    @Test
    void checkRecordsInDatabaseInPayment() {
        long countPaymentStart = SQLHelper.getRecordsCountFromPaymentEntity();

        RequestHelper.postRequestPayment(DataGenerator.generateApprovedCard(), "24", "10", "Ivan Ivanov", "999");

        long countPaymentEnd = SQLHelper.getRecordsCountFromPaymentEntity();

        long expected = countPaymentStart + 1;
        long actual = countPaymentEnd;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRecordsInDatabaseInCredit() {
        long countCreditStart = SQLHelper.getRecordsCountFromOrderEntity();

        RequestHelper.postRequestCredit(DataGenerator.generateApprovedCard(), "24", "10", "Ivan Ivanov", "999");

        long countCreditEnd = SQLHelper.getRecordsCountFromOrderEntity();

        long expected = countCreditStart + 1;
        long actual = countCreditEnd;
        Assertions.assertEquals(expected, actual);
    }
}
