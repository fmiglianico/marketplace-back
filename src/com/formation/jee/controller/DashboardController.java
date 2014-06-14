package com.formation.jee.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.jee.domain.Log;
import com.formation.jee.service.DashboardService;
import com.formation.jee.service.impl.DashboardServiceImpl;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DashboardService dashboardService;
	
	public DashboardController() {
		dashboardService = new DashboardServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<Log> logs = null;
		try {
			logs = dashboardService.getLogs();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("logs", logs);
		req.getRequestDispatcher( "WEB-INF/dashboard.jsp" ).forward(req, resp);
	
	}
}
