package com.formation.jee.dao;

import java.util.List;
import java.util.Set;

import com.formation.jee.domain.Weapon;

public interface WeaponDao {

	Set<Weapon> getAll();
	
	Weapon get(int id);
	
	List<Weapon> getWeaponsOfPlayer(String login);

}