package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Student;
import dao.MysqlConnection;
import dao.StudentDAO;

//实现类，继承于接口类
//数据库连接交给代理类完成

public class StudentDAOImpl implements StudentDAO 
{
	
	//1.插入数据
	public boolean doCreate(Student stu) throws Exception
	{
		boolean flag = false;

		MysqlConnection conn = new MysqlConnection();

		String sql = "INSERT INTO student(studentid,name,age) VALUES(?,?,?)";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);//组装sql语句		
		pstmt.setInt(1, stu.getStudentid());//从VO传来的值
		pstmt.setString(2, stu.getName());
		pstmt.setInt(3, stu.getAge());
		
		if(pstmt.executeUpdate() > 0)			
			flag = true;//如果返回值大于0，表示更新成功了
			
		pstmt.close();
		conn.close();
		
		return flag;
	}

	//2.按String查找可重复的非主键
	public List<Student> findAll(String keyword) throws Exception
	{	
		List<Student> all = new ArrayList<Student>();//查询结果的list
			
		MysqlConnection conn = new MysqlConnection();
		String sql = "SELECT studentid,name,age FROM student WHERE name LIKE ? ";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);//组装查询sql语句
			
		pstmt.setString(1, "%"+ keyword +"%");

		ResultSet rs = pstmt.executeQuery();//sql的结果集
		Student stu = null;
		while(rs.next())//循环赋值
		{
			stu = new Student();
			stu.setStudentid(rs.getInt(1));
			stu.setName(rs.getString(2));
			stu.setAge(rs.getInt(3));
			
			all.add(stu);//加到List中
		}
		
		pstmt.close();//关闭
		conn.close();

		return all;
	}

	//3.按int查找主键
	public Student findById(int stuNo) throws Exception
	{	
		Student stu = null;

		MysqlConnection conn = new MysqlConnection();
		String sql = "SELECT studentid,name,age FROM student WHERE studentid LIKE ?";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);//组装查询sql语句
		
		pstmt.setInt(1,stuNo);

		ResultSet rs = pstmt.executeQuery();//sql的结果集
			
		while(rs.next()) {
			stu = new Student();
			stu.setStudentid(rs.getInt(1));
			stu.setName(rs.getString(2));
			stu.setAge(rs.getInt(3));
		}
		
		pstmt.close();//关闭
		conn.close();
		
		return stu;
	}

}
