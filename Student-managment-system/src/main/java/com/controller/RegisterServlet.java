package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conn.DBConnect;
import com.dao.StudentDao;
import com.dto.Student;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name= req.getParameter("name");
		String dob= req.getParameter("dob");
		String address= req.getParameter("address");
		String qualification= req.getParameter("qualification");
		String email= req.getParameter("email");
		
		
		Student student = new Student(name,dob,address,qualification,email);
		System.out.println(student);
		
	
		StudentDao dao= new StudentDao();
		boolean f= dao.registerStudent(student);
		
		HttpSession httpSession=req.getSession();
	    
		
		if(f==true) {
			httpSession.setAttribute("succMsg","Student details submitted succesfully...");
			resp.sendRedirect("add_student.jsp");
			
		}else {
			httpSession.setAttribute("errorMsg","somthing wrong on server...");
			resp.sendRedirect("add_student.jsp");

			
		}
		



		
		
	}
	
	

}
