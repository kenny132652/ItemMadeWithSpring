package com.example.demo.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Item;
import com.example.demo.model.ItemOption;
import com.example.demo.model.ItemOptionRepositry;
import com.example.demo.model.ItemPhoto;
import com.example.demo.model.ItemRepository;
import com.example.demo.model.ItemTransportation;
import com.example.demo.model.TransportationRepository;
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
	
	@Autowired
	private ItemOptionRepositry itemOptionRepo;
	
	@Autowired
	private TransportationRepository transportationRepo;

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


	public Item addItem(Item item) {
		return itemRepo.save(item);
	}

	public void addOrUpdateItem(Item item, List<Integer> transportationMethods, MultipartFile[] files, boolean isEdit) throws IOException {
	    // 處理運送方式
	    if (transportationMethods != null) {
	        List<ItemTransportation> transportationList = transportationRepo.findAllById(transportationMethods);
	        item.setTransportationMethods(transportationList);
	    }

	    // 處理圖片
	    if (files != null && files.length > 0) {
	        List<ItemPhoto> photoList = new ArrayList<>();
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                ItemPhoto photo = new ItemPhoto();
	                photo.setPhotoFile(file.getBytes());
	                photo.setItem(item); // 關聯圖片到商品
	                photoList.add(photo);
	            }
	        }
	        item.setItemPhoto(photoList); // 只在有新圖片時更新
	    } else if (isEdit) {
	        // 編輯模式下，如果沒有提供新圖片，保留原圖片
	        Item existingItem = findItemById(item.getItemId());
	        if (existingItem != null) {
	            item.setItemPhoto(existingItem.getItemPhoto());
	        }
	    }

	    // 處理選項
	    if (item.getItemOption() != null) {
	        for (ItemOption option : item.getItemOption()) {
	            option.setItem(item); // 將選項關聯到商品
	        }
	    }

	    // 保存商品與選項
	    itemRepo.save(item);

	    // 更新商品價格
	    updateItemPrice(item);
	}


	public void deleteItemById(Integer id) {

		itemRepo.deleteById(id);

	}

	public void updateItemPrice(Item item) {
		BigDecimal minPrice = item.getItemOption().stream().map(ItemOption::getOptionPrice).min(BigDecimal::compareTo)
				.orElse(BigDecimal.ZERO);
		item.setItemPrice(minPrice);
		itemRepo.save(item); // 保存更新到資料庫
	}
}
