import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

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

    @Test
    void checkRequestDatabaseInPaymentByApprovedCard() throws IOException, InterruptedException {
        var response = RequestHelper.PostRequestPayment(approvedCard, "24", "10", "Ivan Ivanov", "999");

        String expected = approved;
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRequestDatabaseInPaymentByDeclinedCard() throws IOException, InterruptedException {
        var response = RequestHelper.PostRequestPayment(declinedCard, "24", "10", "Ivan Ivanov", "999");

        String expected = declined;
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRequestDatabaseInPaymentByWrongCard() throws IOException, InterruptedException {
        var response = RequestHelper.PostRequestPayment(wrongCard, "24", "10", "Ivan Ivanov", "999");

        boolean expectedBadRequest = true;
        boolean actual = response.body().contains("Bad Request");
        Assertions.assertEquals(expectedBadRequest, actual);
    }

    @Test
    void checkRequestDatabaseInCreditByApprovedCard() throws IOException, InterruptedException {
        var response = RequestHelper.PostRequestCredit(approvedCard, "24", "10", "Ivan Ivanov", "999");

        String expected = approved;
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRequestDatabaseInCreditByDeclinedCard() throws IOException, InterruptedException {
        var response = RequestHelper.PostRequestCredit(declinedCard, "24", "10", "Ivan Ivanov", "999");

        String expected = declined;
        String actual = response.body();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRequestDatabaseInCreditByWrongCard() throws IOException, InterruptedException {
        var response = RequestHelper.PostRequestCredit(wrongCard, "24", "10", "Ivan Ivanov", "999");

        boolean expectedBadRequest = true;
        boolean actual = response.body().contains("Bad Request");
        Assertions.assertEquals(expectedBadRequest, actual);
    }

    @Test
    void checkRecordsInDatabaseInPayment() throws IOException, InterruptedException {
        long countPaymentStart = SQLHelper.getRecordsCountFromPaymentEntity();

        RequestHelper.PostRequestPayment(approvedCard, "24", "10", "Ivan Ivanov", "999");

        long countPaymentEnd = SQLHelper.getRecordsCountFromPaymentEntity();

        long expected = countPaymentStart + 1;
        long actual = countPaymentEnd;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkRecordsInDatabaseInCredit() throws IOException, InterruptedException {
        long countCreditStart = SQLHelper.getRecordsCountFromOrderEntity();

        RequestHelper.PostRequestCredit(approvedCard, "24", "10", "Ivan Ivanov", "999");

        long countCreditEnd = SQLHelper.getRecordsCountFromOrderEntity();

        long expected = countCreditStart + 1;
        long actual = countCreditEnd;
        Assertions.assertEquals(expected, actual);
    }
}
