package com.formation.jee.dao;

import java.sql.SQLException;
import java.util.List;

import com.formation.jee.domain.Log;

public interface LogDao {

	void persist(Log log) throws SQLException;

	List<Log> getAll() throws SQLException;

}