package utils;

import java.util.List;

public class StringUtils {

    /**
     * Возвращает строку созданную из методово toString() определенных в коллекции классов
     * @param list - коллекция сущностей
     * @param <T> - типа данных
     * @return - собранная строка
     */
    public static <T> String createStringFromClasses(List<T> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach(l -> builder.append(l.toString()));
        return builder.toString();
    }

}
