package dao;

import java.util.List;
import bean.*;

public interface StudentDAO
{
	public boolean doCreate(Student stu) throws Exception;
	
	public List<Student> findAll(String keyword) throws Exception;
	
	public Student findById(int stuNo) throws Exception;
	
}