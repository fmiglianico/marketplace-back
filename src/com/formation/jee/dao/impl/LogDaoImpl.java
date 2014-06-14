package com.formation.jee.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.formation.jee.common.MarketPlaceAction;
import com.formation.jee.dao.DaoManager;
import com.formation.jee.dao.LogDao;
import com.formation.jee.domain.Log;

public class LogDaoImpl implements LogDao {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public void persist(Log log) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			StringBuilder query = new StringBuilder(
					"INSERT INTO log (date, player_login, action, player_accountBalance, item_id, item_quantity) VALUES ('");
			query.append(sdf.format(log.getDate())).append("', '");
			query.append(log.getPlayerLogin()).append("', '");
			query.append(log.getAction()).append("', ");
			query.append(log.getPlayerAccountBalance()).append(", ");
			query.append(log.getItemId()).append(", ");
			query.append(log.getItemQuantity()).append(")");
			
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
	public List<Log> getAll() throws SQLException {
		List<Log> logs = new ArrayList<Log>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DaoManager.getConn();

			stmt = conn.createStatement();

			String query = "SELECT player_login, item_id, item_quantity, action, player_accountBalance, date FROM log ORDER BY date DESC LIMIT 20";
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Log log = new Log();
				log.setPlayerLogin(rs.getString("player_login"));
				log.setItemId(rs.getInt("item_id"));
				log.setItemQuantity(rs.getInt("item_quantity"));
				log.setAction(MarketPlaceAction.valueOf(rs.getString("action")));
				log.setPlayerAccountBalance(rs.getDouble("player_accountBalance"));
				log.setDate(rs.getDate("date"));
				logs.add(log);

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

		return logs;
	}

}