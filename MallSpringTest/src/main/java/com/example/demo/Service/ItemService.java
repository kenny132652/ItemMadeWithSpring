package com.example.demo.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.model.ItemRepository;
import com.example.demo.model.Category;
import com.example.demo.model.CategoryRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private CategoryRepository categoryRepo; // 引入 CategoryRepository

	public List<Item> findAllItem() {
		return itemRepo.findAll();
	}
	public Item findItemById(int id) {
		
		Optional<Item> op = itemRepo.findById(id);
		if(op.isPresent()) {
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
		item.setItemBrandId(itemBrandId);
		item.setItemInfo(itemInfo);

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

		if (op.isPresent()) {
			Item item = op.get();
			item.setItemName(editItem.getItemName());
			item.setItemPrice(editItem.getItemPrice());
			item.setItemInfo(editItem.getItemInfo());
			item.setItemLocation(editItem.getItemLocation());
			item.setItemBrandId(editItem.getItemBrandId());
			item.setCategory(editItem.getCategory());
			item.setItemDeleteStatus(editItem.isItemDeleteStatus());
			return itemRepo.save(item);
		}else {
			return null;
		}

	}
	public void deleteItemById(Integer id) {
		
		itemRepo.deleteById(id);
		
		
	}


}
