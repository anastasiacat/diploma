package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.RequestHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

public class APITest {

    String approved = "{\"status\":\"APPROVED\"}";
    String declined = "{\"status\":\"DECLINED\"}";
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

    @SneakyThrows
    @Test
    void checkRequestDatabaseInPaymentByApprovedCard() {
        var response = RequestHelper.postRequestPayment(approvedCard, "24", "10", "Ivan Ivanov", "999");

        String expected = approved;
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void checkRequestDatabaseInPaymentByDeclinedCard() {
        var response = RequestHelper.postRequestPayment(declinedCard, "24", "10", "Ivan Ivanov", "999");

        String expected = declined;
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void checkRequestDatabaseInPaymentByWrongCard() {
        var response = RequestHelper.postRequestPayment(wrongCard, "24", "10", "Ivan Ivanov", "999");

        boolean expectedBadRequest = true;
        boolean actual = response.body().contains("Bad Request");
        Assertions.assertEquals(expectedBadRequest, actual);
    }

    @SneakyThrows
    @Test
    void checkRequestDatabaseInCreditByApprovedCard() {
        var response = RequestHelper.postRequestCredit(approvedCard, "24", "10", "Ivan Ivanov", "999");

        String expected = approved;
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void checkRequestDatabaseInCreditByDeclinedCard() {
        var response = RequestHelper.postRequestCredit(declinedCard, "24", "10", "Ivan Ivanov", "999");

        String expected = declined;
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void checkRequestDatabaseInCreditByWrongCard() {
        var response = RequestHelper.postRequestCredit(wrongCard, "24", "10", "Ivan Ivanov", "999");

        boolean expectedBadRequest = true;
        boolean actual = response.body().contains("Bad Request");
        Assertions.assertEquals(expectedBadRequest, actual);
    }

    @SneakyThrows
    @Test
    void checkRecordsInDatabaseInPayment() {
        long countPaymentStart = SQLHelper.getRecordsCountFromPaymentEntity();

        RequestHelper.postRequestPayment(approvedCard, "24", "10", "Ivan Ivanov", "999");

        long countPaymentEnd = SQLHelper.getRecordsCountFromPaymentEntity();

        long expected = countPaymentStart + 1;
        long actual = countPaymentEnd;
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void checkRecordsInDatabaseInCredit() {
        long countCreditStart = SQLHelper.getRecordsCountFromOrderEntity();

        RequestHelper.postRequestCredit(approvedCard, "24", "10", "Ivan Ivanov", "999");

        long countCreditEnd = SQLHelper.getRecordsCountFromOrderEntity();

        long expected = countCreditStart + 1;
        long actual = countCreditEnd;
        Assertions.assertEquals(expected, actual);
    }
}
