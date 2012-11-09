package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Student;
import dao.MysqlConnection;
import dao.StudentDAO;

//ʵ���࣬�̳��ڽӿ���
//���ݿ����ӽ������������

public class StudentDAOImpl implements StudentDAO 
{
	
	//1.��������
	public boolean doCreate(Student stu) throws Exception
	{
		boolean flag = false;

		MysqlConnection conn = new MysqlConnection();

		String sql = "INSERT INTO student(studentid,name,age) VALUES(?,?,?)";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);//��װsql���		
		pstmt.setInt(1, stu.getStudentid());//��VO������ֵ
		pstmt.setString(2, stu.getName());
		pstmt.setInt(3, stu.getAge());
		
		if(pstmt.executeUpdate() > 0)			
			flag = true;//�������ֵ����0����ʾ���³ɹ���
			
		pstmt.close();
		conn.close();
		
		return flag;
	}

	//2.��String���ҿ��ظ��ķ�����
	public List<Student> findAll(String keyword) throws Exception
	{	
		List<Student> all = new ArrayList<Student>();//��ѯ�����list
			
		MysqlConnection conn = new MysqlConnection();
		String sql = "SELECT studentid,name,age FROM student WHERE name LIKE ? ";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);//��װ��ѯsql���
			
		pstmt.setString(1, "%"+ keyword +"%");

		ResultSet rs = pstmt.executeQuery();//sql�Ľ����
		Student stu = null;
		while(rs.next())//ѭ����ֵ
		{
			stu = new Student();
			stu.setStudentid(rs.getInt(1));
			stu.setName(rs.getString(2));
			stu.setAge(rs.getInt(3));
			
			all.add(stu);//�ӵ�List��
		}
		
		pstmt.close();//�ر�
		conn.close();

		return all;
	}

	//3.��int��������
	public Student findById(int stuNo) throws Exception
	{	
		Student stu = null;

		MysqlConnection conn = new MysqlConnection();
		String sql = "SELECT studentid,name,age FROM student WHERE studentid LIKE ?";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);//��װ��ѯsql���
		
		pstmt.setInt(1,stuNo);

		ResultSet rs = pstmt.executeQuery();//sql�Ľ����
			
		while(rs.next()) {
			stu = new Student();
			stu.setStudentid(rs.getInt(1));
			stu.setName(rs.getString(2));
			stu.setAge(rs.getInt(3));
		}
		
		pstmt.close();//�ر�
		conn.close();
		
		return stu;
	}

}
