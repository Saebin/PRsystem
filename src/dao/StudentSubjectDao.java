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

import vo.StudentInfo;
import vo.StudentSubject;
import vo.SubjectInfo;

public class StudentSubjectDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	// 학생이 과목가입을 요청한다.
	public int subjectRegist(int id, String subj_code_div) throws Exception  {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO STUDENT_SUBJECT(STD_ID,SUBJ_CODE_DIV,DEL,REGIST_DATE,CRE_DATE,MOD_DATE)"
							+ " VALUES (?,?,?,NOW(),NOW(),NOW())");
			stmt.setInt(1, id);
			stmt.setString(2, subj_code_div);
			stmt.setInt(3, 0);
			stmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			throw e;

		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int WebsubjectRegist(int id, String subj_code_div) throws Exception  {
		Connection connection = null;
		Statement stmt1 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

	      try {	    	  
	         connection = ds.getConnection();
	         stmt1 = connection.createStatement();
	         
	         stmt = connection.prepareStatement(
						"INSERT INTO STUDENT_SUBJECT(STD_ID,SUBJ_CODE_DIV,DEL,REGIST_DATE,CRE_DATE,MOD_DATE)"
								+ " VALUES (?,?,?,NOW(),NOW(),NOW())");
				stmt.setInt(1, id);
				stmt.setString(2, subj_code_div);
				stmt.setInt(3, 0);
				stmt.executeUpdate();
	         
	         /*System.out.println(subj_code_div+regist_code);*/
	         rs = stmt1.executeQuery(
	               "SELECT SUBJ_CODE_DIV,CURR_NUM" + 
	                     " FROM SUBJECT_INFO WHERE SUBJ_CODE_DIV='"+subj_code_div+"'");
	         
				

	         if(rs.next()) {
	            // 현재 인원 curr_num을 하나 증가시키고,
	            // limit_num 보다 작은 값을 가질 때에만 등록이 가능하다.
	            int curr_num = rs.getInt("CURR_NUM")+1;
	            PreparedStatement pstmt = null;
	            
	             pstmt = connection.prepareStatement("UPDATE SUBJECT_INFO SET CURR_NUM="+curr_num+ ",SUBJ_MOD_DATE=NOW() WHERE"
	                     + " LIMIT_NUM > CURR_NUM AND SUBJ_CODE_DIV='"+subj_code_div+"'");
	             pstmt.executeUpdate();

	            return 1;

	         } else {
	            return 0;
	         }

	      } catch (Exception e)    {
	         throw e;

	      } finally {
	         try {if (rs != null) rs.close();} catch(Exception e) {}
	         try {if (stmt != null) stmt.close();} catch(Exception e) {}
	         try {if (connection != null) connection.close();} catch(Exception e) {}
	      }
	      

	   
		
	}

	// 학생이 로그인 후 메인화면에 듣고있는 과목 코드를 가져온다.
	public List<StudentSubject> getSubjCodeDivList(int stdId) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT SUBJ_CODE_DIV FROM STUDENT_SUBJECT WHERE STD_ID="+stdId);

			ArrayList<StudentSubject> subjCodeDivList = new ArrayList<StudentSubject>();
			while(rs.next()) {

				subjCodeDivList.add(new StudentSubject()
						.setSubj_code_div(rs.getString("SUBJ_CODE_DIV"))
						);	
			}


			return subjCodeDivList;	

		} catch ( Exception e) {
			throw e;

		}  finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
			try {if (rs != null) rs.close();} catch(Exception e) {}
		}

	}
	
	
	// 교수가 강의하는 모든 과목의 학번과 과목코드를 가져온다.
	public Map<String,ArrayList<Integer>> getInClassStdId(ArrayList<String> subj_code_divList) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		SubjectInfo subjectInfo = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			ArrayList<Integer> code_stdIdList = null;
			Map<String,ArrayList<Integer>> map = new HashMap<>();
			
			for(int i=0 ; i<subj_code_divList.size() ; i++) {
				
				rs = stmt.executeQuery(
						"SELECT STD_ID, SUBJ_CODE_DIV FROM STUDENT_SUBJECT WHERE SUBJ_CODE_DIV='"+subj_code_divList.get(i)+"'");
				
				code_stdIdList = new ArrayList<Integer>();
				while(rs.next()) {
					code_stdIdList.add(
							rs.getInt("STD_ID"));
					
				}
				map.put(subj_code_divList.get(i),code_stdIdList);
				
			}
	

			return map;	

		} catch ( Exception e) {
			throw e;

		}  finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
			try {if (rs != null) rs.close();} catch(Exception e) {}
		}

	}
	
	// 교수의 과목코드 하나에 대한 모든 학번을 리턴한다,
		public ArrayList<Integer> getStdId(String subj_code_div) throws Exception {
			Connection connection = null;
			Statement stmt = null;
			ResultSet rs = null;

			SubjectInfo subjectInfo = null;
			try {
				connection = ds.getConnection();

				stmt = connection.createStatement();
				ArrayList<Integer> stdIdList = null;
				

					rs = stmt.executeQuery(
							"SELECT STD_ID FROM STUDENT_SUBJECT WHERE SUBJ_CODE_DIV='"+subj_code_div+"'");

					stdIdList = new ArrayList<Integer>();
					while(rs.next()) {
						stdIdList.add(rs.getInt("STD_ID"));

					}


				return stdIdList;	

			} catch ( Exception e) {
				throw e;

			}  finally {
				try {if (stmt != null) stmt.close();} catch(Exception e) {}
				try {if (connection != null) connection.close();} catch(Exception e) {}
				try {if (rs != null) rs.close();} catch(Exception e) {}
			}

		}
}
