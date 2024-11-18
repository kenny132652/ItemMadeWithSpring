package com.example.demo.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.model.ItemRepository;
import com.example.demo.model.Brand;
import com.example.demo.model.BrandRepository;
import com.example.demo.model.Category;
import com.example.demo.model.CategoryRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private CategoryRepository categoryRepo; // 引入 CategoryRepository

	@Autowired
	private BrandRepository brandRepo;

	public List<Item> findAllItem() {
		return itemRepo.findAll();
	}

	public Item findItemById(int id) {

		Optional<Item> op = itemRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	// 修改 addItem 方法，根據 itemCategoryId 查找 Category
	public Item addItem(String itemName, BigDecimal itemPrice, String itemLocation, int itemBrandId, int itemCategoryId,
			String itemInfo) {

		// 創建新的 Item 物件
		Item item = new Item();
		item.setItemName(itemName);
		item.setItemPrice(itemPrice);
		item.setItemLocation(itemLocation);
		item.setItemInfo(itemInfo);

		// 根據 itemBrandId 查找 Brand 並設置
		Brand brand = brandRepo.findById(itemBrandId).orElse(null); // 使用 findById 查找 Category
		if (brand != null) {
			item.setBrand(brand); // 設置對應的 Brand
		} else {
			// 如果找不到對應的 Brand，可以根據需求處理
			System.out.println("Brand not found with ID: " + itemCategoryId);
		}

		// 根據 itemCategoryId 查找 Category 並設置
		Category category = categoryRepo.findById(itemCategoryId).orElse(null); // 使用 findById 查找 Category
		if (category != null) {
			item.setCategory(category); // 設置對應的 Category
		} else {
			// 如果找不到對應的 Category，可以根據需求處理
			System.out.println("Category not found with ID: " + itemCategoryId);
		}

		return itemRepo.save(item);
	}

	public Item addItem(Item item) {
		return itemRepo.save(item);
	}

	public Item editItem(Item editItem) {
		Optional<Item> op = itemRepo.findById(editItem.getItemId());
		System.out.println("Received Category ID: " + editItem.getCategory().getCategoryId());

		if (op.isPresent()) {
			Item item = op.get();
			item.setItemName(editItem.getItemName());
			item.setItemPrice(editItem.getItemPrice());
			item.setItemInfo(editItem.getItemInfo());
			item.setItemLocation(editItem.getItemLocation());
			item.setItemDeleteStatus(editItem.isItemDeleteStatus());

			if (editItem.getBrand() != null) {
				Integer brandId = editItem.getBrand().getBrandId(); // 提取分類 ID
				Brand brand = brandRepo.findById(brandId).orElse(null); // 查找分類
				if (brand != null) {
					item.setBrand(brand); // 更新分類
				} else {
					System.out.println("Brand not found with ID: " + brandId);
				}
			}
			// 確保分類 ID 正確更新
			if (editItem.getCategory() != null) {
				Integer categoryId = editItem.getCategory().getCategoryId(); // 提取分類 ID
				Category category = categoryRepo.findById(categoryId).orElse(null); // 查找分類
				if (category != null) {
					item.setCategory(category); // 更新分類
				} else {
					System.out.println("Category not found with ID: " + categoryId);
				}
			}

			return itemRepo.save(item);
		}
		return null;
	}

	public void deleteItemById(Integer id) {

		itemRepo.deleteById(id);

	}

}
