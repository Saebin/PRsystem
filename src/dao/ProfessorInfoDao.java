package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import vo.ProfessorInfo;
import vo.StudentInfo;

public class ProfessorInfoDao {

	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(ProfessorInfo profInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO PROFESSOR_INFO(PROF_ID,PROF_NAME,PROF_PWD,PROF_EMAIL,PROF_PHONE,PROF_AUTH,PROF_CRE_DATE,PROF_MOD_DATE)"
							+ " VALUES (?,?,?,?,?,?,NOW(),NOW())");
			stmt.setInt(1, profInfo.getProfId());
			stmt.setString(2, profInfo.getProfName());
			stmt.setString(3, profInfo.getProfPwd());
			stmt.setString(4, profInfo.getProfEmail());
			stmt.setString(5, profInfo.getProfPhone());
			stmt.setString(6, profInfo.getProfAuth());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}
	}

	// 로그인 후, 세션에 담을 객체 생성 ( 이메일, 이름, 권한 ,교번)
	public ProfessorInfo exist(String email, String password) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection
					.prepareStatement("SELECT PROF_EMAIL,PROF_NAME,PROF_PHONE,PROF_AUTH,PROF_ID, PROF_PWD FROM PROFESSOR_INFO"
							+ " WHERE PROF_EMAIL=? AND PROF_PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new ProfessorInfo().setProfName(rs.getString("PROF_NAME"))
						.setProfPhone(rs.getString("PROF_PHONE")).setProfEmail(rs.getString("PROF_EMAIL"))
						.setProfAuth(rs.getString("PROF_AUTH"))
						.setProfPwd(rs.getString("PROF_PWD"))
						.setProfId(Integer.parseInt(rs.getString("PROF_ID")));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}
	}

	// 교수정보 업데이트.
	public int update(ProfessorInfo professorInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		System.out.println("sdfasd");
		System.out.println(professorInfo.getProfEmail());
		System.out.println(professorInfo.getProfAuth());
		System.out.println(professorInfo.getProfId());
		System.out.println(professorInfo.getProfName());
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE PROFESSOR_INFO SET PROF_EMAIL=?,PROF_PHONE=?,PROF_PWD=?, PROF_NAME=?, PROF_AUTH=?, PROF_MOD_DATE=now()"
							+ " WHERE PROF_ID=?");
			stmt.setString(1, professorInfo.getProfEmail());
			stmt.setString(2, professorInfo.getProfPhone());
			stmt.setString(3, professorInfo.getProfPwd());
			stmt.setString(4, professorInfo.getProfName());
			stmt.setString(5, professorInfo.getProfAuth());
			stmt.setInt(6, professorInfo.getProfId());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}
	}

	// 로그인 할때마다 GCM 토큰을 업데이트한다.
	public int tokenUpdate(String token, int profId) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {

			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE PROFESSOR_INFO SET PROF_TOKEN=?,PROF_MOD_DATE=NOW()" + " WHERE PROF_ID=" + profId);
			stmt.setString(1, token);
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}
	}
}
