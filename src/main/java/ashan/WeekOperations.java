package ashan;

import java.util.Random;

public class WeekOperations {

    protected static String getNextWeek() {

        Random random = new Random();

        int index = random.nextInt(WEEKS.values().length);

        return WEEKS.values()[index].toString();

    }

    public static void main(String[] args) {
        System.out.println(getNextWeek());
    }

}
