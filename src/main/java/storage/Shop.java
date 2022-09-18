package storage;

import model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс описывает одну из реализаций хранилища Store, а именно магазин.
 * По умолчанию, если срок годности продуктов истек более, чем на 25%, продукты отправляются в магазин.
 * В случае, если срок годности продукта израсходован на 75%, на продукт выставялется скидка
 *
 * Класс реализует те же принципы SOLID, что и интерфейс Store.
 * Также данный класс соответствует принципу Magics, поскольку порог добавления продуктов в данное хранилище
 * вынесен в отдельное поле.
 */
public class Shop implements Store {
    private final List<Food> SHOP = new ArrayList<>();
    private static final double THRESHOLD_WITHOUT_DISCOUNT = 0.25;
    private static final double THRESHOLD_WITH_DISCOUNT = 0.75;

    /**
     * Если срок годности продуктов израсходован более, чем на 25%, продукт добавляется в магазин,
     * при этом если срок годности продукта израсходован на 75% и более, на продукт должна выставляться скидка
     * @param food - продукт, который может быть добавлен
     */
    @Override
    public void add(Food food) {
        if (accept(food)) {
            if (storage(food) >= THRESHOLD_WITH_DISCOUNT) {
                food.setPriceWithDiscount();
            }
            SHOP.add(food);
        }
    }

    /**
     * Удаляет продукт из магазина
     * @param food - удаляемый продукт
     */
    @Override
    public void delete(Food food) {
        SHOP.remove(food);
    }

    /**
     * Проверяет срок годности продукта и соответствие условия добавления продукта в магазин.
     * @param food - проверяемый продукт
     * @return возвращает true, если продукт подходит для хранилища (в данном случае, если срок годности истек более чем на 25%, но менее чем на 100%)
     */
    @Override
    public boolean accept(Food food) {
        return storage(food) >= THRESHOLD_WITHOUT_DISCOUNT && storage(food) <= 1;
    }

    /**
     * @return возвращает список из всех продуктов магазина
     */
    @Override
    public List<Food> getAll() {
        return List.copyOf(SHOP);
    }
}