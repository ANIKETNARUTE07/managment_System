package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StudentDao;
import com.dto.Student;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String dob = req.getParameter("dob");
		String address = req.getParameter("address");
		String qualification = req.getParameter("qualification");
		String email = req.getParameter("email");
		int id= Integer.parseInt(req.getParameter("id"));

		Student student = new Student(id,name, dob, address, qualification, email);
		System.out.println(student);

		StudentDao dao = new StudentDao();
		boolean f = dao.updateStudent(student);

		HttpSession httpSession = req.getSession();

		if (f == true) {
			httpSession.setAttribute("succMsg", "Student details updated succesfully...");
			resp.sendRedirect("Index.jsp");

		} else {
			httpSession.setAttribute("errorMsg", "somthing wrong on server...");
			resp.sendRedirect("Index.jsp");

		}

	}

}
