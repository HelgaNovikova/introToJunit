package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    }

    public static String getDay(Integer dayNumber) {
        ArrayList<String> daysList = new ArrayList<>();
        daysList.add("Sunday");
        daysList.add("Monday");
        daysList.add("Tuesday");
        daysList.add("Wednesday");
        daysList.add("Thursday");
        daysList.add("Friday");
        daysList.add("Saturday");
        if (dayNumber == null) {
            throw new NullPointerException();
        } else if (dayNumber < 1) {
            return "The number should be equal or larger than 1";
        } else if (dayNumber > 7) {
            return "The number should be equal or smaller than 7";
        } else {
            return daysList.get(dayNumber - 1);
        }
    }

    public static int[] calculateScores(String enter) {
        int[] result = new int[3];
        enter = enter.replaceAll("\\s","");
        if (enter.isEmpty()) {
            throw new RuntimeException("Empty input string");
        }
        for (int i = 0; i < enter.length(); i++) {
            switch (enter.charAt(i)) {
                case 'A' -> result[0]++;
                case 'B' -> result[1]++;
                case 'C' -> result[2]++;
                default -> throw new RuntimeException("Wrong symbols");
            }
        }
        return result;
    }
}