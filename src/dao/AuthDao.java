package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import vo.Auth;


public class AuthDao {

	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	@SuppressWarnings("resource")
	public Auth existAuth(String email, String password) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"SELECT PROF_AUTH FROM PROFESSOR_INFO"
							+ " WHERE PROF_EMAIL=? AND PROF_PWD=?");
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new Auth().setAuth(rs.getString("PROF_AUTH"));
			} else {
				stmt = connection.prepareStatement(
						"SELECT STD_AUTH FROM STUDENT_INFO"
								+ " WHERE STD_EMAIL=? AND STD_PWD=?");
				
				stmt.setString(1, email);
				stmt.setString(2, password);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
//					System.out.println(rs.getString("STD_AUTH"));
					return new Auth().setAuth(rs.getString("STD_AUTH"));
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			throw e;

		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
}
