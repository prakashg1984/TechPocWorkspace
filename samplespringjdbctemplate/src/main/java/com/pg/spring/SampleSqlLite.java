package com.pg.spring;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleSqlLite {


	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = createNewDatabase("test.db");
			if (conn != null) {
				createTable(conn);
				insertData(conn);
				selectAllData(conn);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

    public static Connection createNewDatabase(String fileName) {
    	 String url = "jdbc:sqlite:" + fileName;
    	//For Inmemory use below
    	 //String url = "jdbc:sqlite::memory:";
    	 Connection conn = null;
         try {
        	 conn = DriverManager.getConnection(url);
             if (conn != null) {
                 DatabaseMetaData meta = conn.getMetaData();
                 System.out.println("The driver name is " + meta.getDriverName());
                 System.out.println("A new database has been created.");
             }
  
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         return conn;
    }
    
    public static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS EMPLOYEE (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    designation text NOT NULL\n"
                + ");";

    	Statement stmt = conn.createStatement();
    	stmt.execute(sql);
    	
    	stmt.close();
    }
    
    public static void insertData(Connection conn) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE(name,designation) VALUES(?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "Prakash");
        pstmt.setString(2, "TA");
        pstmt.executeUpdate();

        pstmt.close();
    }
    
    public static void selectAllData(Connection conn) throws SQLException {
    	String sql = "SELECT id, name, designation FROM EMPLOYEE";

    	Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        
        while (rs.next()) {
            System.out.println(rs.getInt("id") +  "\t" + 
                               rs.getString("name") + "\t" +
                               rs.getString("designation"));
        }
        
        rs.close();
        stmt.close();
    }
    
    
    public static void createTableCategories(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS da_te_categories (\n"
                + "    category_id integer PRIMARY KEY,\n"
                + "    category_group text NOT NULL,\n"
                + "    category_name text NOT NULL\n"
                + ");";

        System.out.println("Inside createTableCategories");
    	Statement stmt = conn.createStatement();
    	stmt.execute(sql);
    	
    	stmt.close();
    }
    
    public static void insertDataCategories(Connection conn) throws SQLException {
        String sql = "INSERT INTO da_te_categories(category_id,category_name,category_group) VALUES(?,?,?)";

        System.out.println("Inside insertDataCategories");

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, 1);
        pstmt.setString(2, "PG");
        pstmt.setString(3, "PGGroup");

        pstmt.executeUpdate();

        pstmt.close();
    }
}
