package com.formation.jee.service;

import java.sql.SQLException;
import java.util.List;

import com.formation.jee.domain.Log;

public interface DashboardService {

	List<Log> getLogs() throws SQLException;

}