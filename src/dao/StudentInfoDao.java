package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import vo.Member;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.StudentSubject;
import vo.SubjectInfo;

public class StudentInfoDao {

	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(StudentInfo stdInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO STUDENT_INFO(STD_ID,STD_NAME,STD_PWD,STD_EMAIL,STD_PHONE,STD_AUTH,STD_CRE_DATE,STD_MOD_DATE)"
							+ " VALUES (?,?,?,?,?,?,NOW(),NOW())");
			stmt.setInt(1, stdInfo.getStdId());
			stmt.setString(2, stdInfo.getStdName());
			stmt.setString(3, stdInfo.getStdPwd());
			stmt.setString(4, stdInfo.getStdEmail());
			stmt.setString(5, stdInfo.getStdPhone());
			stmt.setString(6, stdInfo.getStdAuth());
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

	// 로그인 후, 세션에 담을 객체 생성 ( 이메일, 이름, 권한 ,학번)
	public StudentInfo exist(String email, String password) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection
					.prepareStatement("SELECT STD_EMAIL,STD_NAME,STD_AUTH, STD_PWD, STD_PHONE, STD_ID,STD_CRE_DATE FROM STUDENT_INFO"
							+ " WHERE STD_EMAIL=? AND STD_PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new StudentInfo().setStdEmail(rs.getString("STD_EMAIL")).setStdName(rs.getString("STD_NAME"))
						.setStdAuth(rs.getString("STD_AUTH")).setStdPwd(rs.getString("STD_PWD"))
						.setStdPhone(rs.getString("STD_PHONE")).setStdId(rs.getInt("STD_ID")).setStdCreDate(rs.getDate("STD_CRE_DATE"));

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

	// 교수가 로그인 후 메인화면에 과목코드에 따른 학생정보를 가져간다.
	public Map<String, ArrayList<StudentInfo>> getInclassStd(Map<String, ArrayList<Integer>> code_stdIdList)
			throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();

			ArrayList<StudentInfo> inClass_stdList = null;
			Map<String, ArrayList<StudentInfo>> map = new HashMap<>();

			Iterator<String> keys = code_stdIdList.keySet().iterator();

			while (keys.hasNext()) {
				String key = keys.next();

				inClass_stdList = new ArrayList<StudentInfo>();

				for (int j = 0; j < code_stdIdList.get(key).size(); j++) {

					rs = stmt.executeQuery(
							"SELECT STD_ID, STD_NAME, STD_EMAIL, STD_PHONE,STD_CRE_DATE,STD_TOKEN FROM STUDENT_INFO WHERE STD_ID="
									+ code_stdIdList.get(key).get(j));

					while (rs.next()) {

						inClass_stdList.add(
								new StudentInfo().setStdId(rs.getInt("STD_ID")).setStdName(rs.getString("STD_NAME"))
										.setStdEmail(rs.getString("STD_EMAIL")).setStdPhone(rs.getString("STD_PHONE"))
										.setStdCreDate(rs.getDate("STD_CRE_DATE"))
										.setStdToken(rs.getString("STD_TOKEN")));

					}

				}

				map.put(key, inClass_stdList);
			}

			return map;

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
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
		}

	}

	// 정보 업데이트
	public int modify(StudentInfo stdInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		System.out.println(stdInfo.getStdId());
		System.out.println(stdInfo.getStdName());
		System.out.println(stdInfo.getStdPwd());
		System.out.println(stdInfo.getStdEmail());
		System.out.println(stdInfo.getStdPhone());
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("UPDATE STUDENT_INFO SET STD_NAME='" + stdInfo.getStdName() + "',"
					+ "STD_PWD='" + stdInfo.getStdPwd() + "'," + "STD_EMAIL='" + stdInfo.getStdEmail() + "',"
					+ "STD_PHONE='" + stdInfo.getStdPhone() + "'" + "WHERE STD_ID='" + stdInfo.getStdId() + "'");
			// + " VALUES (?,?,?,?,?,?,NOW(),NOW())");
			System.out.println("4번");
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

	// 학생정보 업데이트.
	public int update(StudentInfo studentInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE STUDENT_INFO SET STD_EMAIL=?,STD_PHONE=?,STD_PWD=?, STD_AUTH=?, STD_NAME=?, STD_MOD_DATE=now()" + " WHERE STD_ID=?");
			stmt.setString(1, studentInfo.getStdEmail());
			stmt.setString(2, studentInfo.getStdPhone());
			stmt.setString(3, studentInfo.getStdPwd());
			stmt.setString(4, studentInfo.getStdAuth());
			stmt.setString(5, studentInfo.getStdName());
			stmt.setInt(6, studentInfo.getStdId());
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
	public int tokenUpdate(String token, int stdId) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		System.out.println(token);
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE STUDENT_INFO SET STD_TOKEN=?,STD_MOD_DATE=now()" + " WHERE STD_ID=" + stdId);
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
