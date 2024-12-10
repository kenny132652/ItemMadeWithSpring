package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemPhotoRepositry extends JpaRepository<ItemPhoto, Integer> {
	
	@Query("select id from ItemPhoto where item.itemId = :id")
	List<Integer> findItemPhotoIdByItemId(@Param("id") Integer id);

}
