package emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAL {
	private static final String DB_URL = "jdbc:postgresql://192.168.110.48:5432/plf_training";
	private static final String DB_USER = "plf_training_admin";
	private static final String DB_PASSWORD = "pff123";

	private static final String SELECT_ALL_EMPLOYEES_QUERY = "SELECT * FROM employee116";
	private static final String SELECT_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM employee116 WHERE emp_id = ?";
	private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO employee116 (emp_id, emp_name, designation, salary, dept_no) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE employee116 SET emp_name=?, designation=?, salary=?, dept_no=? WHERE emp_id=?";
	private static final String FIRST_RECORD_QUERY = "SELECT * FROM employee LIMIT 1";
	private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM employee116 WHERE emp_id=?";
	private static final String NEXT_RECORD_QUERY = "SELECT * FROM employees WHERE id > ? ORDER BY id LIMIT 1";
	private static final String LAST_RECORD_QUERY = " SELECT * FROM employee ORDER BY id DESC LIMIT 1";
	private static final String PREV_RECORD_QUERY = "SELECT * FROM employee WHERE id< ? ORDER BY id DESC LIMIT 1";

	public List<Employee> getAllEmployees() throws ClassNotFoundException {
		List<Employee> employees = new ArrayList<>();
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_EMPLOYEES_QUERY);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Employee employee = mapResultSetToEmployee(rs);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exception
		}
		return employees;
	}

	public Employee getEmployeeById(int emp_id) throws ClassNotFoundException {
		Employee employee = null;
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_EMPLOYEE_BY_ID_QUERY)) {
			stmt.setInt(1, emp_id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					employee = mapResultSetToEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exception
		}
		return employee;
	}

	public void addEmployee(Employee employee) throws ClassNotFoundException {
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_EMPLOYEE_QUERY)) {
			stmt.setInt(1, employee.getId());
			stmt.setString(2, employee.getName());
			stmt.setString(3, employee.getDesignation());
			stmt.setDouble(4, employee.getSalary());
			stmt.setInt(5, employee.getDept_no());
			stmt.executeUpdate();
			System.out.println("inserted");
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exception
		}
	}

	public void updateEmployee(Employee employee) throws ClassNotFoundException {
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_EMPLOYEE_QUERY)) {
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getDesignation());
			stmt.setDouble(3, employee.getSalary());
			stmt.setInt(4, employee.getDept_no());
			stmt.setInt(5, employee.getId());
			stmt.executeUpdate();
			System.out.println("update employees");
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exception
		}
	}

	public void deleteEmployee(int emp_id) throws ClassNotFoundException {

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
			stmt.setInt(1, emp_id);
			stmt.executeUpdate();
			System.out.println("deleted");
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exception
		}
	}

	private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("emp_id"));
		employee.setName(rs.getString("name"));
		employee.setDesignation(rs.getString("designation"));
		employee.setSalary(rs.getDouble("salary"));
		employee.setDept_no(rs.getInt("dept_no"));
		return employee;
	}

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		System.out.println("connected");
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

	public Employee getNextEmployee(int currentId) throws ClassNotFoundException {
		Employee employee = null;
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(NEXT_RECORD_QUERY)) {
			stmt.setInt(1, currentId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					employee = mapResultSetToEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	public Employee getPrevEmployee(int currentId) throws ClassNotFoundException {
		Employee employee = null;
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(PREV_RECORD_QUERY)) {
			stmt.setInt(1, currentId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					employee = mapResultSetToEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	public Employee getLastEmployee() throws ClassNotFoundException {
		Employee employee = null;
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(LAST_RECORD_QUERY)) {
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					employee = mapResultSetToEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;

	}

	public Employee getFirstEmployee() throws ClassNotFoundException {
		Employee employee = null;
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIRST_RECORD_QUERY)) {
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					employee = mapResultSetToEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

}