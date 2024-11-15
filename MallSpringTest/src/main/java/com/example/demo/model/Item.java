package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    private String itemName;

    private BigDecimal itemPrice;

    private String itemLocation;

    private int itemBrandId;

    private int itemCategoryId;

    private String itemInfo;

    private boolean itemDeleteStatus = false;

    // 建立多對一關聯：每個 Item 都對應一個 Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemCategoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
    private Category category;

    // Getter 和 Setter
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public int getItemBrandId() {
        return itemBrandId;
    }

    public void setItemBrandId(int itemBrandId) {
        this.itemBrandId = itemBrandId;
    }

    public int getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(int itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public boolean isItemDeleteStatus() {
        return itemDeleteStatus;
    }

    public void setItemDeleteStatus(boolean itemDeleteStatus) {
        this.itemDeleteStatus = itemDeleteStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
