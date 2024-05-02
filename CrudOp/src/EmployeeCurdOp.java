
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeCurdOp
 */
@WebServlet("/EmployeeCurdOp")
public class EmployeeCurdOp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("storedValue");

		if (action != null) {
			switch (action) {
			case "save":
				try {
					saveEmployee(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "update":
				try {
					updateEmployee(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "delete":
				try {
					deleteEmployee(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}

	private void saveEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {

		int id = Integer.parseInt(request.getParameter("emp_id"));
		String name = request.getParameter("emp_name");
		String designation = request.getParameter("designation");
		double salary = Double.parseDouble(request.getParameter("salary"));
		int deptNo = Integer.parseInt(request.getParameter("dept_no"));

		Employee emp = new Employee();

		emp.setId(id);
		emp.setName(name);
		emp.setDesignation(designation);
		emp.setSalary(salary);
		emp.setDept_no(deptNo);

		EmployeeDAL employeeDAL = new EmployeeDAL();
		employeeDAL.addEmployee(emp);

		response.sendRedirect("employeeCurd.jsp");
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		int empId = Integer.parseInt(request.getParameter("emp_id"));
		String name = request.getParameter("emp_name");
		String designation = request.getParameter("designation");
		double salary = Double.parseDouble(request.getParameter("salary"));
		int deptNo = Integer.parseInt(request.getParameter("dept_no"));

		Employee employee = new Employee();
		employee.setId(empId);
		employee.setName(name);
		employee.setDesignation(designation);
		employee.setSalary(salary);
		employee.setDept_no(deptNo);

		// Update employee in database (Model)
		EmployeeDAL employeeDAL = new EmployeeDAL();
		employeeDAL.updateEmployee(employee);

		// Redirect or forward to appropriate view
		response.sendRedirect("employeeCurd.jsp");
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		int empId = Integer.parseInt(request.getParameter("emp_id"));

		EmployeeDAL employeeDAL = new EmployeeDAL();
		employeeDAL.deleteEmployee(empId);

		// Redirect or forward to appropriate view
		response.sendRedirect("employeeCurd.jsp");
	}

}