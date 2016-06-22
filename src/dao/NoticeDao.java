package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import vo.Member;
import vo.Notice;
import vo.StudentSubject;

public class NoticeDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(Notice notice) throws Exception  {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO NOTICE(NOTI_COMMENT,NOTI_NAME,SUBJ_CODE_DIV,PROF_ID,NOTI_CRE_DATE,NOTI_MOD_DATE)"
							+ " VALUES (?,?,?,?,NOW(),NOW())");
			stmt.setString(1, notice.getNoti_comment());
			stmt.setString(2, notice.getNoti_name());
			stmt.setString(3, notice.getSubj_code_div());
			stmt.setInt(4, notice.getProf_id());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}


	// 메인화면에서
	// 로그인한 교수가 보낸 모든 공지사항을 가져온다.
	public List<Notice> selectList(int profId) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;


		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT NOTI_NAME, NOTI_COMMENT,NOTI_CRE_DATE,NOTI_MOD_DATE,SUBJ_CODE_DIV,PROF_ID,NOTI_NUMBER" + 
							" FROM NOTICE WHERE PROF_ID="+profId);

			ArrayList<Notice> notice = new ArrayList<Notice>();

			while(rs.next()) {
				notice.add(new Notice()
						.setNoti_name(rs.getString("NOTI_NAME"))
						.setNoti_comment(rs.getString("NOTI_COMMENT"))
						.setNoti_cre_date(rs.getDate("NOTI_CRE_DATE"))
						.setNoti_mod_date(rs.getDate("NOTI_MOD_DATE"))
						.setSubj_code_div(rs.getString("SUBJ_CODE_DIV"))
						.setProf_id(rs.getInt("PROF_ID"))
						.setNoti_number(rs.getInt("NOTI_NUMBER"))
						);
			}

			return notice;

		} catch (Exception e) {
			throw e;

		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
	public Map<String,ArrayList<Notice>> selectList(ArrayList<StudentSubject> studentSubject) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;


		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			//ArrayList<Notice> notice = new ArrayList<Notice>();
			Map<String,ArrayList<Notice>> notice = new HashMap<String,ArrayList<Notice>>();
			for(int i=0 ; i<studentSubject.size() ; i++) {
				rs = stmt.executeQuery(
						"SELECT NOTI_NAME, NOTI_COMMENT,NOTI_CRE_DATE,NOTI_MOD_DATE,SUBJ_CODE_DIV,PROF_ID,NOTI_NUMBER" + 
								" FROM NOTICE WHERE SUBJ_CODE_DIV='"+studentSubject.get(i).getSubj_code_div()+"'");
				ArrayList<Notice> temp_notice = new ArrayList<Notice>();
				while(rs.next()) {
					temp_notice.add(new Notice()
							.setNoti_name(rs.getString("NOTI_NAME"))
							.setNoti_comment(rs.getString("NOTI_COMMENT"))
							.setNoti_cre_date(rs.getDate("NOTI_CRE_DATE"))
							.setNoti_mod_date(rs.getDate("NOTI_MOD_DATE"))
							.setSubj_code_div(rs.getString("SUBJ_CODE_DIV"))
							.setProf_id(rs.getInt("PROF_ID"))
							.setNoti_number(rs.getInt("NOTI_NUMBER"))
							);
				}
				notice.put(studentSubject.get(i).getSubj_code_div(), temp_notice);
			}

			return notice;

		} catch (Exception e) {
			throw e;

		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
}
