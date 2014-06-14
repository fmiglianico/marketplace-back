package com.formation.jee.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.formation.jee.dao.ArmorDao;
import com.formation.jee.dao.DaoManager;
import com.formation.jee.domain.Armor;

public class ArmorDaoImpl implements ArmorDao {

	@Override
	public Set<Armor> getAll() {
		Set<Armor> armors = new HashSet<Armor>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, a.description ");
			query.append("FROM item AS i ");
			query.append("JOIN armor AS a on i.id = a.item_id ");
			
			rs = stmt.executeQuery(query.toString());
			
			while(rs.next()) {
				Armor armor = new Armor();
				armor.setId(rs.getInt(1));
				armor.setName(rs.getString(2));
				armor.setPrice(rs.getDouble(3));
				armor.setImgUrl(rs.getString(4));
				armor.setDescription(rs.getString(5));
				armors.add(armor);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : " + e.getMessage());
			}
		}
		
		return armors;
	}

	@Override
	public Armor get(int id) {
		Armor armor = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, a.description ");
			query.append("FROM item AS i ");
			query.append("JOIN armor AS a on i.id = a.item_id ");
			query.append("WHERE i.id = ").append(id);
			
			rs = stmt.executeQuery(query.toString());
			
			if(rs.next()) {
				armor = new Armor();
				armor.setId(rs.getInt(1));
				armor.setName(rs.getString(2));
				armor.setPrice(rs.getDouble(3));
				armor.setImgUrl(rs.getString(4));
				armor.setDescription(rs.getString(5));
			}
			
		} catch (SQLException e) {
			System.err.println("Could not retrieve connection : " + e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : " + e.getMessage());
			}
		}
		
		return armor;
	}

	@Override
	public List<Armor> getArmorsOfPlayer(String login) {
		List<Armor> armorInventory = new ArrayList<Armor>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, a.description, p.quantity ");
			query.append("FROM player_item AS p ");
			query.append("JOIN item AS i on i.id = p.item_id ");
			query.append("JOIN armor AS a on i.id = a.item_id ");
			query.append("WHERE p.player_login LIKE '");
			query.append(login).append("'");
			
			rs = stmt.executeQuery(query.toString());
			
			while(rs.next()) {
				Armor armor = new Armor();
				armor.setId(rs.getInt(1));
				armor.setName(rs.getString(2));
				armor.setPrice(rs.getDouble(3));
				armor.setImgUrl(rs.getString(4));
				armor.setDescription(rs.getString(5));
				armor.setQuantity(rs.getInt(6));
				
				armorInventory.add(armor);
			}
			
		} catch (SQLException e) {
			System.err.println("Could not retrieve connection : " + e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : " + e.getMessage());
			}
		}
		
		return armorInventory;
	}
	
	

}
