package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.DBConnect;
import com.dto.Student;

public class StudentDao {

	DBConnect db = new DBConnect();

	public boolean registerStudent(Student student) {
		boolean f = false;

		String sql = "INSERT INTO STUDENT(name,dob,address,qualification,email) VALUES(?,?,?,?,?)";

		Connection conn = db.getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getFullName());
			pstmt.setString(2, student.getDob());
			pstmt.setString(3, student.getAddress());
			pstmt.setString(4, student.getQualification());
			pstmt.setString(5, student.getEmail());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				f = true;
				System.out.println("executed");

			}

			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

	public List<Student> getAllStudent() {
		Connection conn = db.getConnection();
		String sql = "SELECT * FROM STUDENT";

		List<Student> student = new ArrayList<>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Student std = new Student();
				std.setId(rs.getInt(1));
				std.setFullName(rs.getString(2));
				std.setDob(rs.getString(3));
				std.setAddress(rs.getString(4));
				std.setQualification(rs.getString(5));
				std.setEmail(rs.getNString(6));

				student.add(std);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student;
	}

	public Student getDetails(int id) {
		Student std = null;
		Connection conn = db.getConnection();
		String sql = "SELECT * FROM STUDENT WHERE ID=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				std = new Student();
				std.setId(rs.getInt(1));
				std.setFullName(rs.getString(2));
				std.setDob(rs.getString(3));
				std.setAddress(rs.getString(4));
				std.setQualification(rs.getString(5));
				std.setEmail(rs.getString(6));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return std;

	}

	public boolean updateStudent(Student student) {
		boolean f = false;
		String sql = "update student set name=? , dob=? , address=? , qualification=? , email=? where id=?";
		Connection conn = db.getConnection();

		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, student.getFullName());
			psmt.setString(2, student.getDob());
			psmt.setString(3, student.getAddress());
			psmt.setString(4, student.getQualification());
			psmt.setString(5, student.getEmail());
			psmt.setInt(6, student.getId());
			int i = psmt.executeUpdate();

			if (i == 1) {
				f = true;
				System.out.println("record updated");

			} else {
				f = false;
				System.out.println("record not updated");
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

	public boolean deleteStudent(int id) {
		boolean f = false;
		String sql = "DELETE FROM STUDENT WHERE id=?";

		Connection conn = db.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				f = true;
				System.out.println("record deleted");

			} else {
				f = false;
				System.out.println("record not deleted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

}
