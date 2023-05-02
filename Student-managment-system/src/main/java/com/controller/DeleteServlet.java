package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StudentDao;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		StudentDao dao = new StudentDao();
		boolean f= dao.deleteStudent(id);
		System.out.println(f); 
		HttpSession httpsession= req.getSession();
		if (f) {
			httpsession.setAttribute("succMsg", "Student details deleted succesfully...");
			resp.sendRedirect("Index.jsp");

		} else {
			httpsession.setAttribute("errorMsg", "somthing wrong on server...");
			resp.sendRedirect("Index.jsp");

		}
		
		
	}
	

	}


