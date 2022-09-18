package model;

import java.time.LocalDate;

/**
 * Описывает модель продуктов. Содержит в себе поля:
 * String name - хранит название продукта
 * LocalDate createDate - хранит в себе дату создания продукта
 * LocalDate expiryDate - хранит в себе дату, до которой можно употреблять продукт
 * double price - хранит стоимость продукции
 * double discount - хранит размер скидки
 */
public class Food {
    private final String name;
    private final LocalDate createDate;
    private final LocalDate expiryDate;
    private double price;
    private double discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price, double discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    /**
     * @return Возвращает имя продукта
     */
    public String getName() {
        return name;
    }

    /**
     * @return Возвращает дату создания продукта
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * @return Возвращает дату, до которой можно употреблять продукт
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }


    /**
     * @return Возвращает стоимость продукта
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return Возвращает размер скидки, устанавливаемой магазином
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Меняет размер скидки
     * @param discount размер скидки
     */
    public void changeDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Выставляет стоимость со скидкой
     */
    public void setPriceWithDiscount() {
        this.price = price - discount * price;
    }
}