package data;

import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }
    public static String generateWrongCard() {
        var card = new String[]{"4444 4444 4444 4440", "4444 4444 4444 4443", "4444 4444 4444 4444", "4444 4444 4444 4445", "4444 4444 4444 4446",
                "4444 4444 4444 4447", "4444 4444 4444 4448", "4444 4444 4444 4449"};
        return card[new Random().nextInt(card.length)];
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
        var name = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L","M", "N", "O", "P", "Q", "R", "S", "T", "U",
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
        var month = new String[]{"01", "02", "03", "04", "05", "06"};
        return month[new Random().nextInt(month.length)];
    }

    public static String generateYear() {
        var year = new String[]{"24", "25", "26"};
        return year[new Random().nextInt(year.length)];
    }

    public static String generateWrongYear() {
        var year = new String[]{"20", "21", "22"};
        return year[new Random().nextInt(year.length)];
    }

    public static String generateCurrentYear() {
        var year = new String[]{"23"};
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
