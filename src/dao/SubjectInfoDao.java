package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import vo.StudentSubject;
import vo.SubjectInfo;

public class SubjectInfoDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(SubjectInfo subjectInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {

			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO SUBJECT_INFO(SUBJ_NAME,SUBJ_OUTLINE,SUBJ_IMAGE,SUBJ_CODE_DIV,LIMIT_NUM,REGIST_CODE,PROF_ID,REGIST_POSSIBLE,SUBJ_CRE_DATE,SUBJ_MOD_DATE,CURR_NUM)"
							+ " VALUES (?,?,?,?,?,?,?,?,NOW(),NOW(),?)");
			stmt.setString(1, subjectInfo.getSubj_name());
			stmt.setString(2, subjectInfo.getSubj_outline());
			stmt.setString(3, subjectInfo.getSubj_image());
			stmt.setString(4, subjectInfo.getSubj_code_div());
			stmt.setInt(5, subjectInfo.getLimit_num());
			stmt.setString(6, subjectInfo.getRegist_code());
			stmt.setInt(7, subjectInfo.getProf_id());
			stmt.setInt(8, 1);
			stmt.setInt(9, 0);
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

	public List<String> selectSubject(ArrayList<String> list) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<String> subjectList = list;
		ArrayList<String> subjectSelect = new ArrayList<String>();

		Iterator<String> iterator = subjectList.iterator();

		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT SUBJ_CODE_DIV FROM SUBJECT_INFO");

			// 입력한 과목코드와 DB의 과목코드를 비교하여 맞는것만 리턴
			while (rs.next()) {
				while (iterator.hasNext()) {
					if (rs.getString("SUBJ_CODE_DIV").equals(iterator.next())) {
						subjectSelect.add(rs.getString("SUBJ_CODE_DIV"));
					}
				}
			}
			return subjectSelect;

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

	// 학생이 로그인 후 메인화면에 듣고있는 과목정보를 가져온다.
	public List<SubjectInfo> getSubjInfo(ArrayList<StudentSubject> studentSubject) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			ArrayList<SubjectInfo> subjInfoList = new ArrayList<SubjectInfo>();
			for (int i = 0; i < studentSubject.size(); i++) {

				rs = stmt.executeQuery(
						"SELECT SUBJ_CODE_DIV, SUBJ_NAME, SUBJ_IMAGE ,SUBJ_OUTLINE, REGIST_CODE FROM SUBJECT_INFO WHERE SUBJ_CODE_DIV='"
								+ studentSubject.get(i).getSubj_code_div() + "'");

				while (rs.next()) {
					subjInfoList.add(new SubjectInfo()
							.setSubj_code_div(rs.getString("SUBJ_CODE_DIV"))
							.setSubj_name(rs.getString("SUBJ_NAME"))
							.setSubj_outline(rs.getString("SUBJ_OUTLINE"))
							.setSubj_image(rs.getString("SUBJ_IMAGE"))
							.setRegist_code(rs.getString("REGIST_CODE")));

				}
			}

			return subjInfoList;

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

	// 교수가 로그인 후 메인화면에 듣고있는 과목정보를 가져온다.
	public List<SubjectInfo> getSubjInfo(int prof_id) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT SUBJ_CODE_DIV,SUBJ_NAME,SUBJ_IMAGE,SUBJ_OUTLINE,LIMIT_NUM,CURR_NUM,PROF_ID,REGIST_CODE,CURR_NUM FROM SUBJECT_INFO WHERE PROF_ID="
							+ prof_id);

			ArrayList<SubjectInfo> subjInfoList = new ArrayList<SubjectInfo>();

			while (rs.next()) {
				subjInfoList.add(new SubjectInfo().setSubj_code_div(rs.getString("SUBJ_CODE_DIV"))
						.setSubj_name(rs.getString("SUBJ_NAME")).setSubj_image(rs.getString("SUBJ_IMAGE"))
						.setSubj_outline(rs.getString("SUBJ_OUTLINE"))
						.setLimit_num(Integer.parseInt(rs.getString("LIMIT_NUM")))
						.setCurr_num(Integer.parseInt(rs.getString("CURR_NUM")))
						.setProf_id(Integer.parseInt(rs.getString("PROF_ID")))
						.setRegist_code(rs.getString("REGIST_CODE"))
						.setCurr_num(Integer.parseInt(rs.getString("CURR_NUM"))));

			}

			return subjInfoList;

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

	// 선택한 과목에 등록코드가 맞는지 확인한다.
	public int subjectRegistCheck(String subj_code_div, String regist_code) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT SUBJ_CODE_DIV,CURR_NUM" + " FROM SUBJECT_INFO WHERE SUBJ_CODE_DIV='"
					+ subj_code_div + "'" + " AND REGIST_CODE='" + regist_code + "'");

			if (rs.next()) {
				// 현재 인원 curr_num을 하나 증가시키고,
				// limit_num 보다 작은 값을 가질 때에만 등록이 가능하다.
				int curr_num = rs.getInt("CURR_NUM") + 1;
				PreparedStatement pstmt = null;

				pstmt = connection
						.prepareStatement("UPDATE SUBJECT_INFO SET CURR_NUM=" + curr_num + ",SUBJ_MOD_DATE=NOW() WHERE"
								+ " LIMIT_NUM > CURR_NUM AND SUBJ_CODE_DIV='" + subj_code_div + "'");
				pstmt.executeUpdate();

				return 1;

			} else {
				return 0;
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

	public int WebsubjectRegistCheck(String subj_code_div, String regist_code) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();

			/* System.out.println(subj_code_div+regist_code); */
			rs = stmt.executeQuery("SELECT SUBJ_CODE_DIV,CURR_NUM" + " FROM SUBJECT_INFO WHERE SUBJ_CODE_DIV='"
					+ regist_code + "_" + subj_code_div + "'");

			if (rs.next()) {
				return 1;

			} else {
				return 0;
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

	public int WebsubjectRegistCheck01(String regist) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();

			/* System.out.println(subj_code_div+regist_code); */
			rs = stmt.executeQuery("SELECT REGIST_CODE FROM SUBJECT_INFO WHERE REGIST_CODE='" + regist + "'");

			if (rs.next()) {
				return 1;

			} else {
				return 0;
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

	// 가입 가능한 모든 과목을 리턴한다.
	public List<SubjectInfo> subjectRegistList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT SUBJ_CODE_DIV, SUBJ_NAME, SUBJ_IMAGE,SUBJ_OUTLINE,CURR_NUM,LIMIT_NUM"
					+ " FROM SUBJECT_INFO WHERE REGIST_POSSIBLE=" + 1);

			ArrayList<SubjectInfo> subjRegList = new ArrayList<SubjectInfo>();

			while (rs.next()) {
				subjRegList.add(new SubjectInfo().setSubj_code_div(rs.getString("SUBJ_CODE_DIV"))
						.setSubj_name(rs.getString("SUBJ_NAME")).setSubj_image(rs.getString("SUBJ_IMAGE"))
						.setSubj_outline(rs.getString("SUBJ_OUTLINE")).setCurr_num(rs.getInt("CURR_NUM"))
						.setLimit_num(rs.getInt("LIMIT_NUM")));

			}

			return subjRegList;

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

	// 과목정보 업데이트.
	public int update(SubjectInfo subjectInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE SUBJECT_INFO SET SUBJ_OUTLINE=?, LIMIT_NUM=?, REGIST_CODE=?, SUBJ_MOD_DATE=now()"
							+ " WHERE SUBJ_CODE_DIV=?");
			stmt.setString(1, subjectInfo.getSubj_outline());
			stmt.setInt(2, subjectInfo.getLimit_num());
			stmt.setString(3, subjectInfo.getRegist_code());
			stmt.setString(4, subjectInfo.getSubj_code_div());

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
