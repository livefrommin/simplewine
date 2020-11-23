package helpers;

import pages.items.ProductSnippet;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionHelper<E> {

    /**
     * Получаем рандомное значение из пришедшей коллекции
     *
     * @param e   - входной массив
     * @param <E> - тип данных массива
     * @return возвращает объект из массива
     */
    public static <E> E getRandom(Collection<E> e) {
        return e.stream()
                .skip((int) (e.size() * Math.random()))
                .findFirst().get();
    }
}
