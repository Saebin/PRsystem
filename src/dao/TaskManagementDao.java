package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.Auth;
import vo.StudentSubject;
import vo.SubjectInfo;
import vo.TaskInfo;
import vo.TaskManagementInfo;

public class TaskManagementDao {

	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(int num, ArrayList<Integer> stdIdList) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {

			connection = ds.getConnection();
			for (int i = 0; i < stdIdList.size(); i++) {
				stmt = connection.prepareStatement(
						"INSERT INTO TASK_MANAGEMENT(TASK_NUMBER,STD_ID,TASK_SUBMIT_B,DEL,CRE_DATE,MOD_DATE)"
								+ " VALUES (?,?,?,?,NOW(),NOW())");
				stmt.setInt(1, num);
				stmt.setInt(2, stdIdList.get(i));
				stmt.setString(3, "0");
				stmt.setInt(4, 0);
				stmt.executeUpdate();
			}
			return 1;
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

	public int Contentinsert(int num, int stdIdList, TaskManagementInfo TaskManagementInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {

			connection = ds.getConnection();

			stmt = connection.prepareStatement(
					"INSERT INTO TASK_MANAGEMENT(TASK_NUMBER,STD_ID,TASK_SUBMIT_B,DEL,CRE_DATE,MOD_DATE,TASK_TITLE,TASK_CONTENT)"
							+ " VALUES (?,?,?,?,NOW(),NOW(),?,?)");
			stmt.setInt(1, num);
			stmt.setInt(2, stdIdList);
			stmt.setString(3, "0");
			stmt.setInt(4, 0);
			stmt.setString(5, TaskManagementInfo.getTask_title());
			stmt.setString(6, TaskManagementInfo.getTask_content());
			stmt.executeUpdate();

			return 1;
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

	// 교수 로그인 후 메인화면에
	// 과목코드당 과제번호의 과제관리 정보를 리턴한다.
	// 과제번호당 과제관리는 과목에 소속된 학생의 수와 같다
	public List<TaskManagementInfo> getInfo(int taskNumber) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT TASK_NUMBER, STD_ID, TASK_SCORE, TASK_COMMENT, TASK_ANSWER, TASK_GROUP_NUMBER, TASK_SUBMIT_B, MOD_DATE, GROUP_DIV,TASK_TITLE,TASK_CONTENT"
							+ " FROM TASK_MANAGEMENT WHERE TASK_NUMBER=" + taskNumber);

			ArrayList<TaskManagementInfo> info = new ArrayList<TaskManagementInfo>();
			while (rs.next()) {

				info.add(new TaskManagementInfo().setTask_number(rs.getInt("TASK_NUMBER"))
						.setStd_id(rs.getInt("STD_ID")).setTask_score(rs.getString("TASK_SCORE"))
						.setTask_comment(rs.getString("TASK_COMMENT")).setTask_answer(rs.getString("TASK_ANSWER"))
						.setTask_group_number(rs.getInt("TASK_GROUP_NUMBER"))
						.setTask_submit_b(rs.getString("TASK_SUBMIT_B")).setMod_date(rs.getDate("MOD_DATE"))
						.setGroup_div(rs.getInt("GROUP_DIV")).setTask_title(rs.getString("TASK_TITLE"))
						.setTask_content(rs.getString("TASK_CONTENT")));
			}

			return info;

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

	// 학생 로그인 후 메인화면에
	// 과목코드당 과제번호의 과제관리 정보를 리턴한다.
	public TaskManagementInfo getInfo(int taskNumber, int stdId) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT TASK_NUMBER, STD_ID, TASK_SCORE, TASK_COMMENT, TASK_ANSWER, TASK_GROUP_NUMBER, TASK_SUBMIT_B, MOD_DATE"
							+ " FROM TASK_MANAGEMENT WHERE TASK_NUMBER=" + taskNumber + " AND STD_ID=" + stdId);

			TaskManagementInfo info = new TaskManagementInfo();
			while (rs.next()) {

				info = new TaskManagementInfo().setTask_number(rs.getInt("TASK_NUMBER")).setStd_id(rs.getInt("STD_ID"))
						.setTask_score(rs.getString("TASK_SCORE")).setTask_comment(rs.getString("TASK_COMMENT"))
						.setTask_answer(rs.getString("TASK_ANSWER"))
						.setTask_group_number(rs.getInt("TASK_GROUP_NUMBER"))
						.setTask_submit_b(rs.getString("TASK_SUBMIT_B")).setMod_date(rs.getDate("MOD_DATE"));
			}

			return info;

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

	public int check(int task_number) throws Exception {
		Connection connection = null;
		Statement stmt1 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt1 = connection.createStatement();
			int count = 1;

			rs = stmt1.executeQuery("SELECT TASK_CODE" + " FROM TASK_INFO WHERE TASK_NUMBER='" + task_number + "'");
			while (rs.next()) {
				count = rs.getInt("TASK_CODE");
			}
			rs.close();

			System.out.println("이거"+count);

			if (count == 0) {
				// 현재 인원 curr_num을 하나 증가시키고,
				// limit_num 보다 작은 값을 가질 때에만 등록이 가능하다.

				PreparedStatement pstmt = null;

				pstmt = connection.prepareStatement(
						"UPDATE TASK_INFO SET TASK_CODE=1 WHERE TASK_NUMBER='" + task_number + "'");
				pstmt.executeUpdate();

				return 0;

			} else {
				return 1;
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
}
