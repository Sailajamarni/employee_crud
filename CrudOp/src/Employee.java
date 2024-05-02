public class Employee {
	private int emp_id, dept_no;
	private String emp_name, designation;
	private double salary;

	public int getId() {
		return emp_id;
	}

	public void setId(int id) {
		this.emp_id = id;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public String getName() {
		return emp_name;
	}

	public void setName(String name) {
		this.emp_name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}