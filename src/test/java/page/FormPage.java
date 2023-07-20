package page;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class FormPage {

    public void buyOnPayment() {
        open("http://localhost:8080/");
        $x("/html/body/div/div/button[1]").click();
    }
    public void buyOnCredit() {
        open("http://localhost:8080/");
        $x("/html/body/div/div/button[2]").click();
    }

    public void setCard(String card) {
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(card);
    }

    public void setWrongCardNumber(String approvedCard) {
        $("div.form-field > span > span > span:nth-child(2) > input").setValue(approvedCard.substring(0, approvedCard.length() - 1));
    }

    public void setCardMonth(String month) {
        $("div.form-field:nth-child(2) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue(month);
    }

    public void setCardYear(String year) {
        $("div.form-field:nth-child(2) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue(year);
    }

    public void setCardOwner(String cardOwner) {
        $("div.form-field:nth-child(3) > span > span:nth-child(1) > span > span > span:nth-child(2) > input").setValue(cardOwner);
    }

    public void setCardCVC(String cvc) {

        $("div.form-field:nth-child(3) > span > span:nth-child(2) > span > span > span:nth-child(2) > input").setValue(cvc);
    }

    public void pushContinueButton() {
        $x("/html/body/div/div/form/fieldset/div[4]/button").click();
    }

    public void waitingForMessage() {
        $("div.notification:nth-child(7) > div:nth-child(3)").shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public String actualMessage() {
        return $("div.notification:nth-child(7) > div:nth-child(3)").getText().trim();
    }

    public String actualErrorMessage() {
        return $(".input__sub").getText().trim();
    }
}
