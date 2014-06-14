package com.formation.jee.dao;

import java.util.List;
import java.util.Set;

import com.formation.jee.domain.Armor;

public interface ArmorDao {

	Set<Armor> getAll();
	
	Armor get(int id);
	
	List<Armor> getArmorsOfPlayer(String login);

}