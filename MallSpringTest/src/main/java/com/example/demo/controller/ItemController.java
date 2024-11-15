package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ItemService;
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
	
	
//	@Autowired
//	private ItemRepository itemRepo;
//	@Autowired
//	private CategoryRepository categoryRepo;
//	
	
	@GetMapping("/item/itemList")
	public String itemList(Model model) {
		
		List<Item> itemList = itemService.findAllItem();
		List<Category> categorylist = categoryService.findAll();
		model.addAttribute("itemList",itemList);
		model.addAttribute("categorylist",categorylist);
		return "/item/itemListView";
	}
	
	@GetMapping("/item/deleteItem")
	public String deleteItem(@RequestParam Integer id) {
	    itemService.deleteItemById(id); // 假設刪除邏輯實現於此
	    return "redirect:/item/list"; // 刪除後返回項目列表
	}

	
	
	@GetMapping("/item/editItem")
	public String editItem(@RequestParam Integer id,Model model) {
		
		Item item = itemService.findItemById(id);
		List<Category> categorylist = categoryService.findAll();
		
		model.addAttribute("item",item);
		model.addAttribute("categorylist",categorylist);
		
		return "/item/itemEditView";
	}
	@PostMapping("/item/editItemPost")
	public String editItemPost(@ModelAttribute("item") Item postItem,Model model) {
		
		itemService.editItem(postItem);
		
		Item item = itemService.findItemById(postItem.getItemId());
		
		model.addAttribute("item",item);
		model.addAttribute("okMsg","修改成功!");
		
		
		return "/item/itemEditView";
	}
	
	
	
	
	
	
}
