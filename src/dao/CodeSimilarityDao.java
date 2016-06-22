package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.CodeSimilarityInfo;

public class CodeSimilarityDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(CodeSimilarityInfo code) throws Exception  {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO CODESIMILARTIY(STU_NAME1,STU_NAME2,TITLE,CONTENT)"
							+ " VALUES (?,?,?,?)");
			stmt.setString(1, code.getStu_name1());
			stmt.setString(2, code.getStu_name2());
			stmt.setString(3, code.getTitle());
			stmt.setDouble(4, code.getContent());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}


	
	public List<CodeSimilarityInfo> selectList(int task_number) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT TASK_NUMBER,STU_NAME1,STU_NAME2,CONTENT FROM CODESIMILARITY where task_number='"+task_number+"'");

			ArrayList<CodeSimilarityInfo> codesimilarityInfo = new ArrayList<CodeSimilarityInfo>();

			while(rs.next()) {				
				codesimilarityInfo.add(new CodeSimilarityInfo()
						.setTask_number(rs.getInt("TASK_NUMBER"))
						.setStu_name1(rs.getString("STU_NAME1"))
						.setStu_name2(rs.getString("STU_NAME2"))						
						.setContent(rs.getDouble("CONTENT"))
						);
			}
			
			return codesimilarityInfo;

		} catch (Exception e) {
			throw e;

		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
}
