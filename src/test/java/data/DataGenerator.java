package data;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

import static java.lang.String.valueOf;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateWrongCard() {
        var card = new String[]{"4444 4444 4444 4440", "4444 4444 4444 4443", "4444 4444 4444 4444", "4444 4444 4444 4445", "4444 4444 4444 4446",
                "4444 4444 4444 4447", "4444 4444 4444 4448", "4444 4444 4444 4449"};
        return card[new Random().nextInt(card.length)];
    }

    public static String generateApprovedCard() {
        var card = new String[]{"4444 4444 4444 4441"};
        return card[new Random().nextInt(card.length)];
    }

    public static String generateDeclinedCard() {
        var card = new String[]{"4444 4444 4444 4442"};
        return card[new Random().nextInt(card.length)];
    }

    public static String generateApprovedStatus() {
        return "{\"status\":\"APPROVED\"}";
    }

    public static String generateDeclinedStatus() {
        return "{\"status\":\"DECLINED\"}";
    }

    public static String generateName() {
        var name = new String[]{"Ivan Ivanov", "Petr Petrov", "Alexey Smirnov", "Nicolay Sidorov", "Ekaterina Ivanova", "Petr Ivanov", "Ivan Petrov",
                "Nicolay Smirnov", "Maria Ivanova", "Evgenia Petrova", "Alexey Ivanov", "Ekaterina Smirnova", "Evgenia Ivanova", "Maria Petrova",
                "Ivan Smirnov", "Alexey Petrov", "Ekaterina Petrova", "Maria Sidorova", "Evgenia Sidorova", "Ekaterina Sidorova", "Maria Smirnova"};
        return name[new Random().nextInt(name.length)];
    }

    public static String generateNameWithoutSurname() {
        var name = new String[]{"Victor", "Petr", "Victoria", "Varvara", "Anastasia", "Ksenia", "Dmitry", "Nicolay", "Ivan", "Alexey",
                "Evgenia", "Ekaterina", "Maria", "Vladimir", "Fedor"};
        return name[new Random().nextInt(name.length)];
    }

    public static String generateNameWithOneCharacter() {
        var name = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z"};
        return name[new Random().nextInt(name.length)];
    }

    public static String generateNameWithNumbers() {
        var name = new String[]{"Victor 1111", "Petr123", "Victoria 1", "Varvara 2323232", "Anastasia1", "444 Ksenia 000", "Dmitry000",
                "Ev000genia", "Eka555terina", "111Maria", "123 Vladimir", "5Fedor"};
        return name[new Random().nextInt(name.length)];
    }

    public static String generateNameWithSpecialSymbols() {
        var name = new String[]{"Victor!!!", "Petr !@#", "Victoria&^%", "Varvara !@#$%", "#$%Anastasia", "$#@ Ksenia", "&^%$Dmitry$%",
                "Ev%$#genia", "@# Ekaterina $%", "@@@Maria", "Vladimir*", "^Fedor"};
        return name[new Random().nextInt(name.length)];
    }

    public static String generateNameInRussian() {
        var name = new String[]{"Иван Иванов", "Петр Петров", "Алексей Смирнов", "Николай Сидоров", "Екатерина Иванова", "Петр Иванов", "Иван Петров",
                "Николай Смирнов", "Мария Иванова", "Евгения Петрова", "Алексей Иванов", "Екатерина Смирнова", "Евгения Иванова", "Мария Петрова",
                "Иван Смирнов", "Алексей Петров", "Екатерина Петрова", "Мария Сидорова", "Евгения Сидорова", "Екатерина Сидорова", "Мария Смирнова"};
        return name[new Random().nextInt(name.length)];
    }

    public static String generateMonth() {
        var month = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return month[new Random().nextInt(month.length)];
    }

    public static String generateWrongMonth() {
        Calendar prevMonth = Calendar.getInstance();
        Calendar prevMonth1 = Calendar.getInstance();
        Calendar prevMonth2 = Calendar.getInstance();
        prevMonth.add(Calendar.MONTH, -1);
        String month0 = "0" + valueOf(prevMonth.get(Calendar.MONTH));
        prevMonth1.add(Calendar.MONTH, -2);
        String month1 = "0" + valueOf(prevMonth1.get(Calendar.MONTH));
        prevMonth2.add(Calendar.MONTH, -3);
        String month2 = "0" + valueOf(prevMonth2.get(Calendar.MONTH));
        var month = new String[]{month0, month1, month2};
        return month[new Random().nextInt(month.length)];
    }

    public static String generateYear() {
        Calendar prevYear = Calendar.getInstance();
        Calendar prevYear1 = Calendar.getInstance();
        Calendar prevYear2 = Calendar.getInstance();
        prevYear.add(Calendar.YEAR, 1);
        String year0 = valueOf(prevYear.get(Calendar.YEAR)).substring(2);
        prevYear1.add(Calendar.YEAR, 2);
        String year1 = valueOf(prevYear1.get(Calendar.YEAR)).substring(2);
        prevYear2.add(Calendar.YEAR, 3);
        String year2 = valueOf(prevYear2.get(Calendar.YEAR)).substring(2);
        var year = new String[]{year0, year1, year2};
        return year[new Random().nextInt(year.length)];
    }

    public static String generateWrongYear() {
        Calendar prevYear = Calendar.getInstance();
        Calendar prevYear1 = Calendar.getInstance();
        Calendar prevYear2 = Calendar.getInstance();
        prevYear.add(Calendar.YEAR, -1);
        String year0 = valueOf(prevYear.get(Calendar.YEAR)).substring(2);
        prevYear1.add(Calendar.YEAR, -2);
        String year1 = valueOf(prevYear1.get(Calendar.YEAR)).substring(2);
        prevYear2.add(Calendar.YEAR, -3);
        String year2 = valueOf(prevYear2.get(Calendar.YEAR)).substring(2);
        var year = new String[]{year0, year1, year2};
        return year[new Random().nextInt(year.length)];
    }

    public static String generateCurrentYear() {
        String currentYear = valueOf(LocalDate.now().getYear()).substring(2);
        var year = new String[]{currentYear};
        return year[new Random().nextInt(year.length)];
    }

    public static String generateCVC() {
        var cvc = new String[]{"999", "991", "990", "111", "112", "444", "443", "445", "898", "899", "900", "321", "432", "543", "654", "765", "876"};
        return cvc[new Random().nextInt(cvc.length)];
    }

    public static String generateWrongCVC() {
        var cvc = new String[]{"99", "91", "90", "11", "12", "44", "43", "45", "98", "99", "00", "21", "32", "43", "54", "65", "76"};
        return cvc[new Random().nextInt(cvc.length)];
    }
}
