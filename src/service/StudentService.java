package service;

import dao.StudentDAO;
import dao.impl.StudentDAOImpl;
import bean.Student;

public class StudentService {
	
	private StudentDAO studentDao  = new StudentDAOImpl();

	public boolean add(Student stu) throws Exception
	{
		boolean flag = false;
		
		if(null == this.studentDao.findById(stu.getStudentid()))//查主键，看看是否已经存在  
		{
			flag = this.studentDao.doCreate(stu);//根据dao的子类(StudentDAOImpl)，插入数据  
		}
          
        return flag;
        
	}

}
