package com.example.demo.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.model.ItemRepository;
import com.example.demo.model.Category;
import com.example.demo.model.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private ItemRepository itemRepo;
    
    @Autowired
    private CategoryRepository categoryRepo; // 引入 CategoryRepository
    
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }



}
