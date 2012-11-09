import service.StudentService;
import bean.Student;

public class Main {
	
	public static void main(String[] args) throws Exception {

		Student stu = null;
		StudentService studentService = new StudentService();
			
		for(int i=0;i<5;i++) {
			stu = new Student();
			stu.setStudentid(500+i);
			stu.setName("jingjing"+i);
			stu.setAge(i);
			
			studentService.add(stu);

		}
		
	}

}