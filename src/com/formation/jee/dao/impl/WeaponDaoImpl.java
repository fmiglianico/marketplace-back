package com.formation.jee.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.formation.jee.dao.DaoManager;
import com.formation.jee.dao.WeaponDao;
import com.formation.jee.domain.Weapon;

public class WeaponDaoImpl implements WeaponDao {

	@Override
	public Set<Weapon> getAll() {
		Set<Weapon> weapons = new HashSet<Weapon>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, w.power ");
			query.append("FROM item AS i ");
			query.append("JOIN weapon AS w on i.id = w.item_id ");
			
			rs = stmt.executeQuery(query.toString());
			
			while(rs.next()) {
				Weapon weapon = new Weapon();
				weapon.setId(rs.getInt(1));
				weapon.setName(rs.getString(2));
				weapon.setPrice(rs.getDouble(3));
				weapon.setImgUrl(rs.getString(4));
				weapon.setPower(rs.getInt(5));
				weapons.add(weapon);
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
		
		return weapons;
	}

	@Override
	public Weapon get(int id) {
		Weapon weapon = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, w.power ");
			query.append("FROM item AS i ");
			query.append("JOIN weapon AS w on i.id = w.item_id ");
			query.append("WHERE i.id = ").append(id);
			
			rs = stmt.executeQuery(query.toString());
			
			if(rs.next()) {
				weapon = new Weapon();
				weapon.setId(rs.getInt(1));
				weapon.setName(rs.getString(2));
				weapon.setPrice(rs.getDouble(3));
				weapon.setImgUrl(rs.getString(4));
				weapon.setPower(rs.getInt(5));
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
		
		return weapon;
	}

	@Override
	public List<Weapon> getWeaponsOfPlayer(String login) {
		List<Weapon> weaponInventory = new ArrayList<Weapon>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DaoManager.getConn();
			
			stmt = conn.createStatement();
			
			StringBuilder query = new StringBuilder("SELECT i.id, i.name, i.price, i.imgUrl, w.power, p.quantity ");
			query.append("FROM player_item AS p ");
			query.append("JOIN item AS i on i.id = p.item_id ");
			query.append("JOIN weapon AS w on i.id = w.item_id ");
			query.append("WHERE p.player_login LIKE '");
			query.append(login).append("'");
			
			rs = stmt.executeQuery(query.toString());
			
			while(rs.next()) {
				Weapon weapon = new Weapon();
				weapon.setId(rs.getInt(1));
				weapon.setName(rs.getString(2));
				weapon.setPrice(rs.getDouble(3));
				weapon.setImgUrl(rs.getString(4));
				weapon.setPower(rs.getInt(5));
				weapon.setQuantity(rs.getInt(6));
				
				weaponInventory.add(weapon);
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
		
		return weaponInventory;
	}
	
	

}
