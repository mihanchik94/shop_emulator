import model.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.Store;

import java.util.List;

/**
 * Класс описывает сервис контроля качества продуктов (описаны классом Food) и распределяет их по хранилищам (Описаны интерфейсом Store).
 * Продукты, в зависимости от срока годности, могут быть распределены:
 * - на склад (Warehouse)
 * - в магазин (Shop)
 * - в мусорку (Trash)
 * Распределение в магазин может быть с учетом скидки или без учета скидки, в зависимости от срока годности продукта.
 *
 * Данный класс реализует следующие принципы SOLID:
 * Single-responsibility principle, так как данный класс отвечает только за распределение продуктов по их хранилищам.
 * Open-closed principle, так как вместо конкретной реализации списка в качестве хранилища мы используем List, а также в качестве типа, с которым работает
 * список используется абстракция (Store), что позволяет расширять стратегии распределения продуктов
 * Liskov substitution principle, так как мы можем обращаться с любым хранилищем (Warehouse, Shop, Trash) как с базовым Store.
 * Dependency inversion principle, так как любое из хранилищ от абстракции (Store) и неважно, как хранилища реализованы внутри.
 */
public class ControlQuality {
    private final Logger LOG = LoggerFactory.getLogger(ControlQuality.class.getName());
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }


    /**
     * Распределяет продукт из параметров по различным хранилищам.
     * Если продукт не добавлен - кидает исключение IllegalArgumentException.
     * @param food продукт который может быть добавлен
     */
    public void add (Food food) {
        boolean result = false;
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
                result = true;
                break;
            }
        }
        if (result) {
            System.out.println(food.getName() + " moved successfully");
        } else {
            try {
                throw new IllegalArgumentException("Date was wrong!");
            } catch (Exception e) {
                LOG.error("Check dates of food or including containers", e);
            }
        }
    }
}