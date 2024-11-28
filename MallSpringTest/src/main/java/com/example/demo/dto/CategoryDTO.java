package com.example.demo.dto;

public class CategoryDTO {
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryPhoto;  // 用來存放 Base64 編碼的圖片

    // 建構子
    public CategoryDTO(Integer categoryId, String categoryName, String categoryDescription, String categoryPhoto) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryPhoto = categoryPhoto;
    }

    // Getter 和 Setter 方法
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryPhoto() {
        return categoryPhoto;
    }

    public void setCategoryPhoto(String categoryPhoto) {
        this.categoryPhoto = categoryPhoto;
    }
}
