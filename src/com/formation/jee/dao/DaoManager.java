package com.formation.jee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.formation.jee.dao.impl.ArmorDaoImpl;
import com.formation.jee.dao.impl.ConsumableDaoImpl;
import com.formation.jee.dao.impl.LogDaoImpl;
import com.formation.jee.dao.impl.PlayerDaoImpl;
import com.formation.jee.dao.impl.WeaponDaoImpl;

public enum DaoManager {
	
	INSTANCE;

	private ArmorDao armorDao;
	private ConsumableDao consumableDao;
	private WeaponDao weaponDao;
	private PlayerDao playerDao;
	private LogDao logDao;
	
	// Identifiant de connection
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jee-training";
	private static final String USER = "jee-training";
	private static final String PASSWORD = "password";
	
	private DaoManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load driver : " + e.getMessage());
		}
		
		armorDao = new ArmorDaoImpl();
		consumableDao = new ConsumableDaoImpl();
		weaponDao = new WeaponDaoImpl();
		playerDao = new PlayerDaoImpl();
		logDao = new LogDaoImpl();
	}
	
	public static Connection getConn() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	}

	public ArmorDao getArmorDao() {
		return armorDao;
	}

	public ConsumableDao getConsumableDao() {
		return consumableDao;
	}

	public WeaponDao getWeaponDao() {
		return weaponDao;
	}

	public PlayerDao getPlayerDao() {
		return playerDao;
	}

	public LogDao getLogDao() {
		return logDao;
	}
}
