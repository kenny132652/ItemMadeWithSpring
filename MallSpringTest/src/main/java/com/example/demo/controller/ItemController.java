package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Service.BrandService;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ItemService;
import com.example.demo.model.Brand;
import com.example.demo.model.BrandRepository;
import com.example.demo.model.Category;
import com.example.demo.model.CategoryRepository;
import com.example.demo.model.Item;
import com.example.demo.model.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;

//	@Autowired
//	private ItemRepository itemRepo;
//	@Autowired
//	private CategoryRepository categoryRepo;
//	
	@Autowired
	private BrandRepository brandRepo;

	@GetMapping("/item/itemList")
	public String itemList(Model model) {

		List<Item> itemList = itemService.findAllItem();
		List<Category> categoryList = categoryService.findAll();
		List<Brand> brandList = brandRepo.findAll();
		model.addAttribute("itemList", itemList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("brandList", brandList);
		return "/item/itemListView";
	}

	@GetMapping("/item/deleteItem")
	public String deleteItem(@RequestParam Integer id) {
	    try {
	        itemService.deleteItemById(id);  // 假設刪除邏輯實現於此
	        return "redirect:/item/itemList";  // 刪除後返回項目列表
	    } catch (Exception e) {
	        // 捕捉錯誤，顯示錯誤信息
	        System.out.println("刪除商品失敗: " + e.getMessage());
	        return "errorPage";  // 若有錯誤，可以導向錯誤頁面
	    }
	}


	@GetMapping("/item/addItem")
	public String addItem(Model model) {

		model.addAttribute("item", new Item());
		model.addAttribute("categoryList", categoryService.findAll()); // 顯示所有分類
		model.addAttribute("brandList", brandService.findAll()); // 顯示所有品牌

		return "/item/itemAddView";
	}

	@PostMapping("/item/addItemPost")
	public String addItemPost(@ModelAttribute Item item) {	
		
		itemService.addItem(item);
		
		return "redirect:/item/itemList";
	}

	@GetMapping("/item/editItem")
	public String editItem(@RequestParam Integer id, Model model) {

		Item item = itemService.findItemById(id);

		model.addAttribute("item", item);
		model.addAttribute("categoryList", categoryService.findAll()); // 顯示所有分類
		model.addAttribute("brandList", brandService.findAll()); // 顯示所有品牌
		
		return "/item/itemEditView";
	}

	@PostMapping("/item/editItemPost")
	public String editItemPost(@ModelAttribute("item") Item postItem, Model model) {
		System.out.println("Received Item Category: " + postItem.getCategory());
		itemService.editItem(postItem);

		Item item = itemService.findItemById(postItem.getItemId());

		model.addAttribute("item", item);
		model.addAttribute("okMsg", "修改成功!");

		return "redirect:/item/itemList";
	}

}
