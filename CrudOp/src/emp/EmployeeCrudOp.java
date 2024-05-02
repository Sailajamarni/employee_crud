package emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class EmployeeCurdOp
 */
@WebServlet("/EmployeeCrudOp")
public class EmployeeCrudOp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("storedValue");

		if (action != null) {
			switch (action) {
			case "first":
				try {
					firstRecord(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "last":
				try {
					lastRecord(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "prev":
				try {
					previousRecord(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {

					e.printStackTrace();
				}
				break;
			case "next":
				try {
					nextRecord(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {

					e.printStackTrace();
				}
				break;
			case "save":
				try {
					saveEmployee(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {

					e.printStackTrace();
				}
				break;
			case "update":
				try {
					updateEmployee(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {

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

	private void nextRecord(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, ServletException {
		int currentId = Integer.parseInt(request.getParameter("emp_id"));

		// Retrieve the next record from the database based on the current ID
		EmployeeDAL employeeDAL = new EmployeeDAL();
		Employee nextEmployee = employeeDAL.getNextEmployee(currentId);

		// Set the retrieved employee as an attribute in the request
		String json = new Gson().toJson(nextEmployee);

		// Set response content type
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Send JSON response back to the client
		response.getWriter().write(json);

	}

	private void previousRecord(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, IOException, ServletException {
		int currentId = Integer.parseInt(request.getParameter("emp_id"));

		// Retrieve the next record from the database based on the current ID
		EmployeeDAL employeeDAL = new EmployeeDAL();
		Employee prevEmployee = employeeDAL.getPrevEmployee(currentId);

		// Set the retrieved employee as an attribute in the request
		String json = new Gson().toJson(prevEmployee);

		// Set response content type
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Send JSON response back to the client
		response.getWriter().write(json);

	}

	private void lastRecord(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, IOException, ServletException {
		EmployeeDAL employeeDAL = new EmployeeDAL();
		Employee firstEmployee = employeeDAL.getLastEmployee();

		// Convert the firstEmployee object to JSON
		String json = new Gson().toJson(firstEmployee);

		// Set response content type
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Send JSON response back to the client
		response.getWriter().write(json);

	}

	private void firstRecord(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, IOException, ServletException {
		EmployeeDAL employeeDAL = new EmployeeDAL();
		Employee firstEmployee = employeeDAL.getFirstEmployee();

		// Convert the firstEmployee object to JSON
		String json = new Gson().toJson(firstEmployee);

		// Set response content type
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Send JSON response back to the client
		response.getWriter().write(json);

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