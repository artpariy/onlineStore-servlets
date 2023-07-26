package ru.pariy.model.product;

import jakarta.annotation.Nullable;
/**
 * @brief Текущий класс описывает товар
 */
public class Product {
    private final int id;
    private final int typeId;
    private final int brandId;
    private String brandName;
    private final String model;
    private final String release;
    private final int price;
    private final int colorId;
    private String colorName;
    private final int vendorCode;
    private final int countOnStock;

    public Product(int id, int typeId, int brandId, String model, String release, int price, int colorId, int vendorCode, int countOnStock) {
        this.id = id;
        this.typeId = typeId;
        this.brandId = brandId;
        this.model = model;
        this.release = release;
        this.price = price;
        this.colorId = colorId;
        this.vendorCode = vendorCode;
        this.countOnStock = countOnStock;
    }

    public Product(int id, int typeId, int brandId, String brandName, String model, String release, int price, int colorId, String colorName, int vendorCode, int countOnStock) {
        this.id = id;
        this.brandName = brandName;
        this.typeId = typeId;
        this.brandId = brandId;
        this.model = model;
        this.release = release;
        this.price = price;
        this.colorId = colorId;
        this.colorName = colorName;
        this.vendorCode = vendorCode;
        this.countOnStock = countOnStock;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getBrandName() {
        return brandName;
    }
    public int getTypeId() {
        return typeId;
    }
    public int getBrandId() {
        return brandId;
    }

    public String getModel() {
        return model;
    }

    public String getRelease() {
        return release;
    }

    public int getPrice() {
        return price;
    }

    public int getColorId() {
        return colorId;
    }
    @Nullable
    public String getColorName() {
        return colorName;
    }

    public int getVendorCode() {
        return vendorCode;
    }

    public int getCountOnStock() {
        return countOnStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", model='" + model + '\'' +
                ", release='" + release + '\'' +
                ", price=" + price +
                ", colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                ", vendorCode=" + vendorCode +
                ", countOnStock=" + countOnStock +
                '}';
    }
}
