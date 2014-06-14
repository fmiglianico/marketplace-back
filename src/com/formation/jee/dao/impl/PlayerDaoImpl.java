package com.formation.jee.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.formation.jee.dao.DaoManager;
import com.formation.jee.dao.PlayerDao;
import com.formation.jee.domain.Item;
import com.formation.jee.domain.Player;
import com.formation.jee.domain.PlayerLogin;

public class PlayerDaoImpl implements PlayerDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formation.jee.dao.PlayerDao#getPlayers()
	 */
	@Override
	public List<Player> getAll() {

		List<Player> players = new ArrayList<Player>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			String query = "SELECT login, password FROM player";
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Player player = new Player.Builder().login(rs.getString("login"))
						.password("password").build();

				players.add(player);

			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : "
						+ e.getMessage());
			}
		}

		return players;
	}

	@Override
	public void add(PlayerLogin playerLogin) throws SQLException {

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			StringBuilder query = new StringBuilder(
					"INSERT INTO player (login, password, accountBalance) VALUES ('");
			query.append(playerLogin.getLogin()).append("', '");
			query.append(playerLogin.getPassword()).append("', ");
			query.append(500.0).append(")");
			
			stmt.executeUpdate(query.toString());

		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : "
						+ e.getMessage());
			}
		}
	}

	@Override
	public String login(PlayerLogin playerLogin) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String token = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			StringBuilder query = new StringBuilder(
					"SELECT password FROM player WHERE login LIKE '");
			query.append(playerLogin.getLogin()).append("'");
			rs = stmt.executeQuery(query.toString());

			while (rs.next()) {
				String playerPassword = rs.getString("password");
				if (playerLogin.getPassword().equals(playerPassword)) {
					token = generateToken(playerLogin.getLogin());
				}
			}

		} catch (SQLException e) {
			System.err.println("Could not retrieve connection : "
					+ e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : "
						+ e.getMessage());
			}
		}

		return token;
	}

	private String generateToken(String login) {
		return login + "rocks13246789";
	}

	@Override
	public Player get(String token) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String login = token.substring(0, token.indexOf("rocks13246789"));

		Player player = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			StringBuilder query = new StringBuilder(
					"SELECT accountBalance FROM player WHERE login LIKE '");
			query.append(login).append("'");
			rs = stmt.executeQuery(query.toString());

			if (rs.next()) {
				player = new Player.Builder().login(login)
						.accountBalance(rs.getDouble("accountBalance"))
						.build();
			}

		} catch (SQLException e) {
			System.err.println("Could not retrieve connection : "
					+ e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : "
						+ e.getMessage());
			}
		}

		return player;
	}

	@Override
	public void buy(Player player, Item item) {

		List<Item> inventory = player.getInventory();
		boolean newItem = !inventory.contains(item);

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			StringBuilder query = new StringBuilder();

			if (newItem) {
				query.append(
						"INSERT INTO player_item (player_login, item_id, quantity) ")
						.append("VALUES ('").append(player.getLogin())
						.append("', ").append(item.getId()).append(", ")
						.append(item.getQuantity()).append(")");
			} else {

				int oldQuantity = inventory.get(inventory.indexOf(item)).getQuantity();
				query.append("UPDATE player_item ").append("SET quantity = ")
						.append(oldQuantity + item.getQuantity()).append(" ")
						.append("WHERE player_login LIKE '")
						.append(player.getLogin()).append("' ")
						.append("AND item_id = ").append(item.getId());
			}
			stmt.executeUpdate(query.toString());

		} catch (SQLException e) {
			System.err.println("Could not retrieve connection : "
					+ e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : "
						+ e.getMessage());
			}
		}

	}

	@Override
	public void sell(Player player, Item item) {
		List<Item> inventory = player.getInventory();
		int newQuantity = inventory.get(inventory.indexOf(item)).getQuantity() - item.getQuantity();

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			StringBuilder query = new StringBuilder();

			if (newQuantity == 0) {
				query.append("DELETE FROM player_item ");
			} else {
				query.append("UPDATE player_item ").append("SET quantity = ")
						.append(newQuantity).append(" ");
			}
			query.append("WHERE player_login LIKE '").append(player.getLogin())
					.append("' ").append("AND item_id = ").append(item.getId());
			stmt.executeUpdate(query.toString());

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : "
						+ e.getMessage());
			}
		}
	}
	
	@Override
	public void updateBalance(Player player) {
		
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			StringBuilder query = new StringBuilder();
			
			query.append("UPDATE player ").append("SET accountBalance = ")
					.append(player.getAccountBalance())
					.append(" WHERE login LIKE '")
					.append(player.getLogin())
					.append("' ");
			
			stmt.executeUpdate(query.toString());

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Problem while trying to clean : "
						+ e.getMessage());
			}
		}
	}

}
