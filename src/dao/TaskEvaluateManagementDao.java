package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.Auth;
import vo.SubjectInfo;
import vo.TaskEvaluateManagementInfo;
import vo.TaskManagementInfo;

public class TaskEvaluateManagementDao {

	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(TaskEvaluateManagementInfo taskEvaluateManagementInfo) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {

			connection = ds.getConnection();

			stmt = connection.prepareStatement(
					"INSERT INTO TASK_EVALUATE_MANAGEMENT(TASK_NUMBER,STD_ID,STD_ID_EVALUATE,SUBMIT_COMMENT,SUBMIT_SCORE,CRE_DATE)"
							+ " VALUES (?,?,?,?,?,NOW())");
			stmt.setInt(1, taskEvaluateManagementInfo.getTask_number());
			stmt.setInt(2, taskEvaluateManagementInfo.getStd_id());
			stmt.setInt(3, taskEvaluateManagementInfo.getStd_id_evaluate());
			stmt.setString(4, taskEvaluateManagementInfo.getSubmit_comment());
			stmt.setInt(5, taskEvaluateManagementInfo.getSubmit_score());

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
	public List<TaskEvaluateManagementInfo> getInfo (int task_number) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT TASK_NUMBER, STD_ID, STD_ID_EVALUATE, SUBMIT_COMMENT, SUBMIT_SCORE,CRE_DATE"
							+ " FROM TASK_EVALUATE_MANAGEMENT WHERE TASK_NUMBER=" + task_number);

			ArrayList<TaskEvaluateManagementInfo> info = new ArrayList<TaskEvaluateManagementInfo>();
			while (rs.next()) {

				info.add(new TaskEvaluateManagementInfo()
						.setTask_number(rs.getInt("TASK_NUMBER"))
						.setStd_id(rs.getInt("STD_ID"))
						.setStd_id_evaluate(rs.getInt("STD_ID_EVALUATE"))
						.setSubmit_comment(rs.getString("SUBMIT_COMMENT"))
						.setSubmit_score(rs.getInt("SUBMIT_SCORE"))
						.setCre_date(rs.getDate("CRE_DATE")));
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
		}
	}
	public List<TaskEvaluateManagementInfo> AllgetInfo () throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT TASK_NUMBER, STD_ID, STD_ID_EVALUATE, SUBMIT_COMMENT, SUBMIT_SCORE,CRE_DATE"
							+ " FROM TASK_EVALUATE_MANAGEMENT");

			ArrayList<TaskEvaluateManagementInfo> info = new ArrayList<TaskEvaluateManagementInfo>();
			while (rs.next()) {

				info.add(new TaskEvaluateManagementInfo()
						.setTask_number(rs.getInt("TASK_NUMBER"))
						.setStd_id(rs.getInt("STD_ID"))
						.setStd_id_evaluate(rs.getInt("STD_ID_EVALUATE"))
						.setSubmit_comment(rs.getString("SUBMIT_COMMENT"))
						.setSubmit_score(rs.getInt("SUBMIT_SCORE"))
						.setCre_date(rs.getDate("CRE_DATE")));
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
		}
	}

}
