package com.kkch.xxworld.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kkch.xxworld.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Integer>{

}
