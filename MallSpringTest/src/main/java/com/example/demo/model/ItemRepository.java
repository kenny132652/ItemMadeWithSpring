package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	@Query("select i.itemId from Item i where i.userInfo.userId = :id")
	List<Integer> findItemByUserId(@Param("id") Integer id);

	
}
