package storage;

import model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс описывает одну из реализаций хранилища Store, а именно склад.
 * По умолчанию, если срок годности продуктов израсходован менее, чем на 25%, продукты отправляются на склад.
 *
 * Класс реализует те же принципы SOLID, что и интерфейс Store.
 * Также данный класс соответствует принципу Magics, поскольку порог добавления продуктов в данное хранилище
 * вынесен в отдельное поле.
 */
public class Warehouse implements Store {
    private final List<Food> WAREHOUSE = new ArrayList<>();
    private static final double THRESHOLD = 0.25;

    /**
     * Если срок годности продуктов израсходован менее, чем на 25%, продукт добавляется на склад
     * @param food - продукт, который может быть добавлен
     */
    @Override
    public void add(Food food) {
        if (accept(food)) {
            WAREHOUSE.add(food);
        }
    }

    /**
     * Удаляет продукт со склада
     * @param food - удаляемый продукт
     */
    @Override
    public void delete(Food food) {
        WAREHOUSE.remove(food);
    }

    /**
     * Проверяет срок годности продукта и соответствие условия добавления продукта на склад.
     * @param food - проверяемый продукт
     * @return возвращает true, если продукт подходит для хранилища (в данном случае, если срок годности истек менее чем на 25%)
     */
    @Override
    public boolean accept(Food food) {
        return storage(food) < THRESHOLD;
    }

    /**
     * @return возвращает список из всех продуктов склада
     */
    @Override
    public List<Food> getAll() {
        return List.copyOf(WAREHOUSE);
    }
}