package service;

import dao.StudentDAO;
import dao.impl.StudentDAOImpl;
import bean.Student;

public class StudentService {
	
	private StudentDAO studentDao  = new StudentDAOImpl();

	public boolean add(Student stu) throws Exception
	{
		boolean flag = false;
		
		if(null == this.studentDao.findById(stu.getStudentid()))//�������������Ƿ��Ѿ�����  
		{
			flag = this.studentDao.doCreate(stu);//����dao������(StudentDAOImpl)����������  
		}
          
        return flag;
        
	}

}
