package com.formation.jee.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.formation.jee.dao.ConsumableDao;
import com.formation.jee.dao.DaoManager;
import com.formation.jee.domain.Consumable;

public class ConsumableDaoImpl implements ConsumableDao {

	@Override
	public Set<Consumable> getAll() {
		Set<Consumable> consumables = new HashSet<Consumable>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, c.effect ");
			query.append("FROM item AS i ");
			query.append("JOIN consumable AS c on i.id = c.item_id ");
			
			rs = stmt.executeQuery(query.toString());
			
			while(rs.next()) {
				Consumable consumable = new Consumable();
				consumable.setId(rs.getInt(1));
				consumable.setName(rs.getString(2));
				consumable.setPrice(rs.getDouble(3));
				consumable.setImgUrl(rs.getString(4));
				consumable.setEffect(rs.getString(5));
				consumables.add(consumable);
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
		
		return consumables;
	}

	@Override
	public Consumable get(int id) {
		Consumable consumable = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, c.effect ");
			query.append("FROM item AS i ");
			query.append("JOIN consumable AS c on i.id = c.item_id ");
			query.append("WHERE i.id = ").append(id);
			
			rs = stmt.executeQuery(query.toString());
			
			if(rs.next()) {
				consumable = new Consumable();
				consumable.setId(rs.getInt(1));
				consumable.setName(rs.getString(2));
				consumable.setPrice(rs.getDouble(3));
				consumable.setImgUrl(rs.getString(4));
				consumable.setEffect(rs.getString(5));
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
		
		return consumable;
	}

	@Override
	public List<Consumable> getConsumablesOfPlayer(String login) {
		List<Consumable> consumableInventory = new ArrayList<Consumable>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, c.effect, p.quantity ");
			query.append("FROM player_item AS p ");
			query.append("JOIN item AS i on i.id = p.item_id ");
			query.append("JOIN consumable AS c on i.id = c.item_id ");
			query.append("WHERE p.player_login LIKE '");
			query.append(login).append("'");
			
			rs = stmt.executeQuery(query.toString());
			
			while(rs.next()) {
				Consumable consumable = new Consumable();
				consumable.setId(rs.getInt(1));
				consumable.setName(rs.getString(2));
				consumable.setPrice(rs.getDouble(3));
				consumable.setImgUrl(rs.getString(4));
				consumable.setEffect(rs.getString(5));
				consumable.setQuantity(rs.getInt(6));
				
				consumableInventory.add(consumable);
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
		
		return consumableInventory;
	}
	
	

}
