<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="emp.Employee"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Form</title>
<script>
	function storeValue(value) {
		var clickedButtonValue = value;
		document.getElementById("storedValue").value = clickedButtonValue;
	}
</script>
<style>
body {
	font-family: Arial, sans-serif;
}

.form-group {
	margin-bottom: 1rem;
}

.form-group label {
	display: block;
	margin-bottom: 0.5rem;
	font-weight: bold;
}

.form-group input {
	width: 50%;
	padding: 0.5rem;
	font-size: 1rem;
}

.btn {
	padding: 0.5rem 1rem;
	margin-top: 1rem;
	cursor: pointer;
	border: none;
	border-radius: 4px;
	text-decoration: none;
	background-color: #007bff;
	color: #fff;
}

.btn:hover {
	background-color: #0056b3;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<h2>Employee Form</h2>
	<form action="EmployeeCurdOp" method="post">
		<div class="form-group">
			<label for="emp_id">Emp ID:</label> <input type="text" id="emp_id"
				name="emp_id">
		</div>
		<div class="form-group">
			<label for="emp_name">Name:</label> <input type="text" id="emp_name"
				name="emp_name">
		</div>
		<div class="form-group">
			<label for="designation">Designation:</label> <input type="text"
				id="designation" name="designation">
		</div>
		<div class="form-group">
			<label for="salary">Salary:</label> <input type="text" id="salary"
				name="salary">
		</div>
		<div class="form-group">
			<label for="dept_no">Dept No:</label> <input type="text" id="dept_no"
				name="dept_no">
		</div>
		<div id="btns">
			<div>
				<button type="submit" id="firstButton" class="btn"
					onclick="storeValue('first')">First</button>
				<button type="button" id="prevButton" class="btn"
					onclick="storeValue('prev')">Previous</button>
				<button type="button" id="nextButton" class="btn"
					onclick="storeValue('next')">Next</button>
				<button type="reset" id="lastButton" class="btn"
					onclick="storeValue('last')">Last</button>

			</div>
			<div>
				<button type="submit" class="btn" onclick="storeValue('save')">Save</button>
				<button type="button" class="btn" onclick="storeValue('update')">Update</button>
				<button type="button" class="btn" onclick="storeValue('delete')">Delete</button>
				<button type="reset" class="btn" onclick="storeValue('reset')">Reset</button>
			</div>

			<input type="hidden" id="storedValue" name="storedValue">
		</div>

	</form>
	<div id="tableDetails">
		<h2>Employee Details</h2>
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Designation</th>
					<th>Salary</th>
					<th>Dept_no</th>
				</tr>
			</thead>
			<tbody>
				<%
					// Retrieve the list of employees from the request attribute
				ArrayList<Employee> employees = (ArrayList<Employee>) request.getAttribute("employees");

				// Check if the list of employees is not null
				if (employees != null) {
					// Iterate over the list of employees
					for (Employee employee : employees) {
				%>
				<!-- Table row to display employee details -->
				<tr>
					<td><%=employee.getId()%></td>
					<td><%=employee.getName()%></td>
					<td><%=employee.getDesignation()%></td>
					<td><%=employee.getSalary()%></td>
					<td><%=employee.getDept_no()%></td>
				</tr>
				<%
					} // End of for loop
				}
				%>
			</tbody>
		</table>
	</div>
</body>
<script>
	document.addEventListener("DOMContentLoaded", function() {
        const prevButton = document.querySelector("#prevButton");
		const nextButton = document.querySelector("#nextButton");
		const firstButton = document.querySelector("#firstButton");
		const lastButton = document.querySelector("#lastButton");
		
		lastButton.addEventListener("click",function(){
			fetchLastRecord();
		});
		
		firstButton.addEventListener("click",function(){
			
			fetchFirstRecord();
		});
		
		
		nextButton.addEventListener("click", function(){
			const empId =document.querySelector("#emp_id").value;
			
			fetchNextRecord(empId);
		});
		
        prevButton.addEventListener("click", function() {
            const empId = document.querySelector("#emp_id").value;

            fetchPrevRecord(empId);
        });
		
        function fetchFirstRecord(){
        	fetch("EmployeeCurdOp?action=first")
        	.then(response=>response.json())
        	.then(data=>{
        		document.querySelector("#emp_id").value = data.id;
                document.querySelector("#name").value = data.name;
                document.querySelector("#designation").value = data.designation;
                document.querySelector("#salary").value = data.salary;
                document.querySelector("#dept_no").value = data.dept_no;
        	})
        	.catch(error => {
                console.error('Error:', error);
            });
        }
        
        function fetchFirstRecord(){
        	fetch("EmployeeCurdOp?action=last")
        	.then(response=>response.json())
        	.then(data=>{
        		document.querySelector("#emp_id").value = data.id;
                document.querySelector("#name").value = data.name;
                document.querySelector("#designation").value = data.designation;
                document.querySelector("#salary").value = data.salary;
                document.querySelector("#dept_no").value = data.dept_no;
        	})
        	.catch(error => {
                console.error('Error:', error);
            });
        }
        
        function fetchNextRecord(empId){
        	fetch("EmployeeCurdOp?action=next&emp_id="+empId)
        	.then(response=> response.json())
        	.then(data =>{
        		document.querySelector("#emp_id").value = data.id;
                document.querySelector("#name").value = data.name;
                document.querySelector("#designation").value = data.designation;
                document.querySelector("#salary").value = data.salary;
                document.querySelector("#dept_no").value = data.dept_no;
        	})
        	.catch(error => {
                console.error('Error:', error);
            });
        }
        function fetchPrevRecord(empId) {
            // Make an AJAX request to fetch the previous record
            fetch("EmployeeCurdOp?action=prev&emp_id=" + empId)
                .then(response => response.json())
                .then(data => {
                    // Update form fields with data of the previous record
                    document.querySelector("#emp_id").value = data.id;
                    document.querySelector("#name").value = data.name;
                    document.querySelector("#designation").value = data.designation;
                    document.querySelector("#salary").value = data.salary;
                    document.querySelector("#dept_no").value = data.dept_no;
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    });
</script>
</html>
