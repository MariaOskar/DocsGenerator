package generator.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class GeneratorHelper {

    public static String formatBirthday(Date birthday) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(birthday);
    }

    public static int calculateAge(Date birthday) {
        Date now = new Date();
        LocalDate nowLocal = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate birthdayLocal = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthdayLocal, nowLocal).getYears();
    }

    public static int randomNum(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }

    public static String getRandomNums(int quantity) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            builder.append(String.valueOf(randomNum(0, 9)));
        }
        return builder.toString();
    }
}
