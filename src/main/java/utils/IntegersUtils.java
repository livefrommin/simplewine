package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntegersUtils {
    /***
     * Поулчаем рандомное значение типа int из заданого интервала
     * @param maxInterval - максимальное значение интервала
     * @return - возвращаемое значение
     */
    public static int getRandomIntFromInterval(int maxInterval) {
        return ThreadLocalRandom.current().nextInt(1, maxInterval);
    }

    /**
     * Конвертирует строку в число.
     * @param inputStr - входящая строка
     * @return - возвращаемое число, если пусто или null, то возвращаем 0
     */
    public static int parseInteger(String inputStr) {
        if(inputStr == null || inputStr.isEmpty())
            return 0;
        return Integer.parseInt(inputStr);
    }

    public static List<Integer> getPriceInterval(String textInterval) {
        List<Integer> list = new ArrayList<>();
        switch (textInterval) {
            case "До 700 ₽":
                list.add(0);
                list.add(700);
                break;
            case "700–1 500 ₽":
                list.add(700);
                list.add(1500);
                break;
            case "1 500–3 000 ₽":
                list.add(1500);
                list.add(3000);
                break;
            case "3 000–5 000 ₽":
                list.add(3000);
                list.add(5000);
                break;
            case "5 000–10 000 ₽":
                list.add(5000);
                list.add(10000);
                break;
            case "от 8000 ₽":
                list.add(8000);
                list.add(1000000000);
                break;
            case "от 5000 ₽":
                list.add(5000);
                list.add(500000);
                break;
            case "5 000–7 000 ₽":
                list.add(5000);
                list.add(7000);
                break;
            case "от 10 000 ₽":
                list.add(10000);
                list.add(1000000000);
                break;
            default:
                list.add(0);
                list.add(0);
        }
        return list;
    }

}
