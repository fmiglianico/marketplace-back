package com.formation.jee.dao;

import java.util.List;
import java.util.Set;

import com.formation.jee.domain.Consumable;

public interface ConsumableDao {

	Set<Consumable> getAll();
	
	Consumable get(int id);
	
	List<Consumable> getConsumablesOfPlayer(String login);

}