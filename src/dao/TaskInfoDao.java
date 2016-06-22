package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.Notice;
import vo.SubjectInfo;
import vo.TaskInfo;

public class TaskInfoDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	// 과제 추가
	public int insert(TaskInfo taskInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {

			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO TASK_INFO(TASK_CONTENT,TASK_NAME,SUBJ_CODE_DIV,TASK_GROUP_NUM,TASK_START,TASK_END,CRE_DATE,MOD_DATE)"
							+ " VALUES (?,?,?,?,?,?,now(),now())");
			stmt.setString(1, taskInfo.getTask_content());
			stmt.setString(2, taskInfo.getTask_name());
			stmt.setString(3, taskInfo.getSubj_code_div());
			stmt.setInt(4, taskInfo.getTask_group_num());
			stmt.setDate(5, taskInfo.getTask_start());
			stmt.setDate(6, taskInfo.getTask_end());
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

	// 메인화면에서
	// 로그인한 교수가 출제한 모든 과제정보를 가져온다.
	// 모든 과제 정보이기때문에 쿼리를 과제 수 만큼 반복해야한다.
	// where 문자열 쿼리 주는법.. 뭔지모름
	public List<TaskInfo> allSelectList(ArrayList<String> subj_code_div) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();

			ArrayList<TaskInfo> taskInfo = new ArrayList<TaskInfo>();

			for (int i = 0; i < subj_code_div.size(); i++) {

				rs = stmt.executeQuery(
						"SELECT TASK_NUMBER,TASK_NAME, TASK_CONTENT,TASK_START,TASK_END,SUBJ_CODE_DIV,TASK_GROUP,TASK_CODE" + 
								" FROM TASK_INFO WHERE SUBJ_CODE_DIV='"+subj_code_div.get(i)+"'");

				while(rs.next()) {
					taskInfo.add(new TaskInfo()
							.setTask_number(rs.getInt("TASK_NUMBER"))
							.setTask_name(rs.getString("TASK_NAME"))
							.setTask_content(rs.getString("TASK_CONTENT"))
							.setTask_start(rs.getDate("TASK_START"))
							.setTask_end(rs.getDate("TASK_END"))
							.setSubj_code_div(rs.getString("SUBJ_CODE_DIV"))
							.setTask_group(rs.getString("TASK_GROUP"))
							.setTask_code(rs.getInt("TASK_CODE"))
							);
				}
			}

			return taskInfo;

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

	// 메인화면에서
	// 로그인한 교수가 출제한 모든 과제정보를 가져온다.
	// 모든 과제 정보이기때문에 쿼리를 과제 수 만큼 반복해야한다.
	// where 문자열 쿼리 주는법.. 뭔지모름
	public List<TaskInfo> selectList(ArrayList<String> subj_code_div) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();

			ArrayList<TaskInfo> taskInfo = new ArrayList<TaskInfo>();

			for (int i = 0; i < subj_code_div.size(); i++) {

				rs = stmt.executeQuery(
						"SELECT TASK_NUMBER,TASK_NAME, TASK_CONTENT,TASK_START,TASK_END,SUBJ_CODE_DIV,TASK_GROUP,TASK_CODE"
								+ " FROM TASK_INFO WHERE SUBJ_CODE_DIV='" + subj_code_div.get(i) + "'");

				while (rs.next()) {
					taskInfo.add(new TaskInfo().setTask_number(rs.getInt("TASK_NUMBER"))
							.setTask_name(rs.getString("TASK_NAME")).setTask_content(rs.getString("TASK_CONTENT"))
							.setTask_start(rs.getDate("TASK_START")).setTask_end(rs.getDate("TASK_END"))
							.setSubj_code_div(rs.getString("SUBJ_CODE_DIV")).setTask_group(rs.getString("TASK_GROUP"))
							.setTask_code(rs.getInt("TASK_CODE")));
				}
			}

			return taskInfo;

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

	// 메인화면에서
	// 로그인한 학생이 출제된 모든 과제정보를 가져온다.
	public List<TaskInfo> oneSelectList(ArrayList<Integer> temp_taskNumber) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();

			ArrayList<TaskInfo> taskInfo = new ArrayList<TaskInfo>();

			for (int i = 0; i < temp_taskNumber.size(); i++) {

				rs = stmt.executeQuery(
						"SELECT TASK_NUMBER,TASK_NAME, TASK_CONTENT,TASK_START,TASK_END,SUBJ_CODE_DIV,TASK_GROUP" + 
								" FROM TASK_INFO WHERE TASK_NUMBER="+temp_taskNumber.get(i));

				while(rs.next()) {
					taskInfo.add(new TaskInfo()
							.setTask_number(rs.getInt("TASK_NUMBER"))
							.setTask_name(rs.getString("TASK_NAME"))
							.setTask_content(rs.getString("TASK_CONTENT"))
							.setTask_start(rs.getDate("TASK_START"))
							.setTask_end(rs.getDate("TASK_END"))
							.setSubj_code_div(rs.getString("SUBJ_CODE_DIV"))
							.setTask_group("TASK_GROUP")
							);
				}
			}

			return taskInfo;

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

	// 과제 생성한 task_number 리턴
	public int getLastNum() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;


		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();

			ArrayList<TaskInfo> taskInfo = new ArrayList<TaskInfo>();
			int num=0;


			rs = stmt.executeQuery(
					"SELECT MAX(TASK_NUMBER) TASK_NUMBER" + 
					" FROM TASK_INFO");

			while(rs.next()) {
				num = rs.getInt("TASK_NUMBER");
			}


			return num;

		} catch (Exception e) {
			throw e;

		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
	// 과제정보 업데이트.
	public int update(TaskInfo taskInfo) throws Exception { 
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE TASK_INFO SET TASK_CONTENT=?,TASK_NAME=?,TASK_START=?,TASK_END=?,MOD_DATE=now()"
							+ " WHERE TASK_NUMBER=?");
			stmt.setString(1,taskInfo.getTask_content());
			stmt.setString(2,taskInfo.getTask_name());
			stmt.setDate(3,taskInfo.getTask_start());
			stmt.setDate(4,taskInfo.getTask_end());
			stmt.setInt(5, taskInfo.getTask_number());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	// task_code 업데이트
	public int updateTask_code(int num) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			PreparedStatement pstmt = null;

			pstmt = connection.prepareStatement("UPDATE TASK_INFO SET TASK_CODE=1 where task_number=" + num);

			return pstmt.executeUpdate();

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

	// task_code 업데이트
	public int taskCount(int num) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			PreparedStatement pstmt = null;

			rs = stmt.executeQuery("SELECT count(*) AS rowcount FROM task_management WHERE task_number=" + num);
			while(rs.next()) {
				count = rs.getInt("rowcount");
			}
			rs.close();
			
			return count;

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
