package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.vo.Member;

public class MemberDao {
	
	/* Remove to add dbconnection pool
	Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	*/
	
	/* Remove to add datasource form javax
	DBConnectionPool connPool;
	public void setDBConnectionPool(DBConnectionPool connPool) {
		this.connPool = connPool;
	}
	*/
	
	DataSource ds;
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
				"select mno, mname, email, cre_date" +
				" from members" +
				" order by mno asc"
			);
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while (rs.next()) {
				members.add(new Member()
					.setNo(rs.getInt("mno"))
					.setName(rs.getString("mname"))
					.setEmail(rs.getString("email"))
					.setCreatedDate(rs.getDate("cre_date"))
				);
			}
			
			return members;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			//try {if (connection != null) connPool.returnConnection(connection);} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
	
	public int insert(Member member) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			//connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
				"insert into MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)" +
				" values(?, ?, ?, NOW(), NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			//try {if (connection != null) connPool.returnConnection(connection);} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
	
	public int delete(int no) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			//connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
				"delete from members where mno = ?"
			);
			stmt.setInt(1, no);
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			//try {if (connection != null) connPool.returnConnection(connection);} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
	
	public Member selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
				"select mno, mname, email, cre_date" +
				" from members" +
				" where mno = " + no
			);
			
			if (rs.next()) {
				return new Member()
					.setNo(no)
					.setName(rs.getString("mname"))
					.setEmail(rs.getString("email"))
					.setCreatedDate(rs.getDate("cre_date"));
			} else {
				throw new Exception("Can't find mno = " + no);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			//try {if (connection != null) connPool.returnConnection(connection);} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
	
	public int update(Member member) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			//connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
				"update members set mname = ?, email = ?, mod_date = now()" +
				" where mno = ?");
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setInt(3, member.getNo());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			//try {if (connection != null) connPool.returnConnection(connection);} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
				"select mname, email from members" +
				" where email = ? and pwd = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Member()
					.setName(rs.getString("mname"))
					.setEmail(rs.getString("email"));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			//try {if (connection != null) connPool.returnConnection(connection);} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
}
