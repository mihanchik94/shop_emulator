import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import model.Food;
import storage.Shop;
import storage.Store;
import storage.Trash;
import storage.Warehouse;

import java.time.LocalDate;
import java.util.List;

class ControlQualityTest {
    private final Warehouse warehouse = new Warehouse();
    private final Shop shop = new Shop();
    private final Trash trash = new Trash();
    private final List<Store> stores = List.of(warehouse, shop, trash);
    private final ControlQuality controlQuality = new ControlQuality(stores);


    @Test
    public void whenAddToWarehouse() {
        LocalDate now = LocalDate.now();
        controlQuality.add(new Food("Milk", now.minusDays(2), now.plusDays(30), 60.0, 0.8));
        assertThat(warehouse.getAll().size()).isEqualTo(1);
        assertThat(shop.getAll().size()).isEqualTo(0);
        assertThat(trash.getAll().size()).isEqualTo(0);
        assertThat(warehouse.getAll().get(0).getPrice()).isEqualTo(60.0);

    }

    @Test
    public void whenAddToShopWithoutSale() {
        LocalDate now = LocalDate.now();
        controlQuality.add(new Food("Milk", now.minusDays(10), now.plusDays(10), 60.0, 0.8));
        assertThat(warehouse.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(trash.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().get(0).getPrice()).isEqualTo(60.0);

    }

    @Test
    public void whenAddToShopWithSale() {
        LocalDate now = LocalDate.now();
        controlQuality.add(new Food("Milk", now.minusDays(15), now.plusDays(5), 60.0, 0.8));
        assertThat(warehouse.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(trash.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().get(0).getPrice()).isEqualTo(12.0);

    }

    @Test
    public void whenAddToTrash() {
        LocalDate now = LocalDate.now();
        controlQuality.add(new Food("Milk", now.minusDays(10), now.minusDays(1), 60.0, 0.8));
        assertThat(warehouse.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().size()).isEqualTo(0);
        assertThat(trash.getAll().size()).isEqualTo(1);
        assertThat(trash.getAll().get(0).getPrice()).isEqualTo(60.0);

    }
}