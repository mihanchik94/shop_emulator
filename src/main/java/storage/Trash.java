package storage;

import model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс описывает одну из реализаций хранилища Store, а именно мусорный контейнер.
 * По умолчанию, если срок годности продуктов истек, продукты добавляются в мусорный контейнер.
 * Класс реализует те же принципы SOLID, что и интерфейс Store.
 * Также данный класс соответствует принципу Magics, поскольку порог добавления продуктов в данное хранилище
 * вынесен в отдельное поле.
 */
public class Trash implements Store {
    private final List<Food> TRASH = new ArrayList<>();
    private static final double THRESHOLD = 1.0;

    /**
     * Если продукт с исчерпанным сроком годности, он добавляется в мусорный контейнер
     * @param food - продукт, который может быть добавлен
     */
    @Override
    public void add(Food food) {
        if (accept(food)) {
            TRASH.add(food);
        }
    }

    /**
     * Удаляет продукт из мусорного контейнера
     * @param food - удаляемый продукт
     */
    @Override
    public void delete(Food food) {
        TRASH.remove(food);
    }

    /**
     * Проверяет срок годности продукта и соответствие условия добавления продукта в мусорный контейнер.
     * @param food - проверяемый продукт
     * @return возвращает true, если продукт подходит для хранилища (в данном случае, если срок годности истек)
     */
    @Override
    public boolean accept(Food food) {
        return storage(food) > THRESHOLD;
    }

    /**
     * @return возвращает список из всех продуктов мусорного контейнера
     */
    @Override
    public List<Food> getAll() {
        return List.copyOf(TRASH);
    }

    /**
     * Очищает мусорный контейнер
     */
    public void clear() {
        TRASH.clear();
    }
}