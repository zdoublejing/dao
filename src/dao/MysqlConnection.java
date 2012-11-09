package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {
    private static final String DBDriver = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/demo";    
    private static final String DBUser = "root";  
    private static final String DBPassWord = "root"; 
    Connection con = null;  
    
    public MysqlConnection() throws Exception {
    	 Class.forName(DBDriver);//�������ݿ�����    
    	 con = DriverManager.getConnection(DBURL, DBUser, DBPassWord);//����    
         System.out.println("������ "+con);  	 
    }
    
    public Connection getConnection() {
    	return this.con;
    }
    
    public void close() throws Exception {
    	if(null != this.con) {	
    		this.con.close();
    	}
    }
	
    
}