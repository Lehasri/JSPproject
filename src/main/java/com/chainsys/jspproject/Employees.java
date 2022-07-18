package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class Employees
 */
@WebServlet("/Employees")
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Employee> allEmployees = EmployeeDao.getAllEmployee();
		Iterator<Employee> empIterator = allEmployees.iterator();
			response.setContentType("text/html");
			out.print("<html><head><title><Employees</title></head><body>");
			out.print("<table border=1px bgcolor=\"DodgerBlue\" width=50%>");
			out.print("<tr bgcolor=\"DarkSlateBlue\" align=center>");
			out.print("<th height=\"10\" width=\"90\">Emp_id:</th>");
			out.print("<th height=\"10\" width=\"90\">First_name:</th>");
			out.print("<th height=\"10\" width=\"90\">Last_name:</th>");
		while (empIterator.hasNext()) {
			out.print("<tr align=center>");
			Employee result = empIterator.next();
			out.print("<td bgcolor=\"DeepSkyBlue\">" + result.getEmp_ID() + "</td>");
			out.print("<td bgcolor=\"DeepSkyBlue\">" + result.getFirst_name() + "</td>");
			out.print("<td bgcolor=\"DeepSkyBlue\">" + result.getLast_name() + "</td>");
			out.print("</tr>");

			out.println(result.getEmp_ID() + "," + result.getFirst_name() + "," + result.getLast_name() + ","
					+ result.getEmail() + "," + result.getHire_date() + "," + result.getJob_id() + ","
					+ result.getSalary() + "</p>");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("submit").equals("Add")) {
			String source="AddNewEmployee";
			String message="<h1>Error while "+source+"</h1>";
		PrintWriter out = response.getWriter();
		Employee newemp = new Employee();
		int empId=0;
		int result=0;

		String testname=null;
			try {
				String id = request.getParameter("id");
				try {
					Validator.checkStringForParse(id);
				} catch (InvalidInputDataException err) {
					message +=" Error in Employee id input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
                   return; // It terminates the Code execution beyond this point 
				}
				empId = Integer.parseInt(id);
				try {
					Validator.CheckNumberForGreaterThanZero(empId);
				} catch (InvalidInputDataException err) {
					message +=" Error in Employee id input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
                   return; // It terminates the Code execution beyond this point 
				}
				
				newemp.setEmp_ID(empId);
//--------------------------------
				String fname = request.getParameter("fname");
				testname = fname;
				try {
					Validator.checkStringOnly(testname);
				} catch (InvalidInputDataException err) {
					message +=" Error in first name input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				try {
					Validator.checklengthOfString(fname);
				} catch (InvalidInputDataException err) {
					message +=" Error in first name input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				}
				newemp.setFirst_name(fname);
//-----------------------------------
				String lname = request.getParameter("lname");
				testname = fname;
				try {
					Validator.checkStringOnly(testname);
				} catch (InvalidInputDataException e) {
					message +=" Error in last name input </p>";
					String errorPage=ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}
				try {
					Validator.checklengthOfString(lname);
				} catch (InvalidInputDataException err) {
					message +=" Error in last name input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				}
				newemp.setLast_name(lname);
//----------------------------------			
				String email = request.getParameter("email");
				try {
					Validator.checkMail(email);
				} catch (InvalidInputDataException err) {
					message +=" Error in email input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setEmail(email);
//--------------------------------------			
				SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
				String emp_HireDate = request.getParameter("hdate");
				// Date hire_date=hire_dateFormate.parse(emp_HireDate);

				try {
					Validator.checkDateFormat(emp_HireDate);
				} catch (InvalidInputDataException err) {
					message +=" Error in Hire Date input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				}
				Date newDate = null;
				try {
					newDate = hire_dateFormate.parse(emp_HireDate);
				} catch (ParseException err) {
					message +=" Error in Hire Date input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				}
				try {
					Validator.CheckNofutureDate(newDate);
				} catch (InvalidInputDataException err) {
					message +=" Error in Hire Date input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				}

				newemp.setHire_date(newDate);
//----------------------------------------
				String jobId = request.getParameter("jobid");
				try {
					Validator.checkjob(jobId);
				} catch (InvalidInputDataException err) {
					message +=" Error in Job Id input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				}
				newemp.setJob_id(jobId);
//---------------------------------------			
				String sal = request.getParameter("salary");
				try {
					Validator.checkStringForParse(sal);
				} catch (InvalidInputDataException err) {
					message +=" Error in Salary input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				}
				float salParse = Float.parseFloat(sal);
				try {
					Validator.checkSalLimit(salParse);
				} catch (InvalidInputDataException err) {
					message +=" Error in Salary input </p>";
					String errorPage=ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				}
				newemp.setSalary(salParse);
//----------------------------------------------			
				result = EmployeeDao.insertEmployee(newemp);
				out.println(result + "row inserted");
			} catch (Exception e) {
				message +=" Error while inserting record </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}try {
				out.close();
			} catch (Exception e) {
				message +="Message: "+e.getMessage();
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
               return;
			}
		}else if(request.getParameter("submit").equals("Update")) {
			doPut(request,response);
		}else if(request.getParameter("submit").equals("Delete")) {
			doDelete(request,response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String source="UpdateEmployee";
		String message="<h1>Error while "+source+"</h1>";
	PrintWriter out = response.getWriter();
	Employee newemp = new Employee();
	int empId=0;
	int result=0;

	String testname=null;
		try {
			String id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message +=" Error in Employee id input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
               return; // It terminates the Code execution beyond this point 
			}
			empId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message +=" Error in Employee id input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
               return; // It terminates the Code execution beyond this point 
			}
			
			newemp.setEmp_ID(empId);
//--------------------------------
			String fname = request.getParameter("fname");
			testname = fname;
			try {
				Validator.checkStringOnly(testname);
			} catch (InvalidInputDataException err) {
				message +=" Error in first name input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checklengthOfString(fname);
			} catch (InvalidInputDataException err) {
				message +=" Error in first name input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			}
			newemp.setFirst_name(fname);
//-----------------------------------
			String lname = request.getParameter("lname");
			testname = fname;
			try {
				Validator.checkStringOnly(testname);
			} catch (InvalidInputDataException e) {
				message +=" Error in last name input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checklengthOfString(lname);
			} catch (InvalidInputDataException err) {
				message +=" Error in last name input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			}
			newemp.setLast_name(lname);
//----------------------------------			
			String email = request.getParameter("email");
			try {
				Validator.checkMail(email);
			} catch (InvalidInputDataException err) {
				message +=" Error in email input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setEmail(email);
//--------------------------------------			
			SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			String emp_HireDate = request.getParameter("hdate");
			// Date hire_date=hire_dateFormate.parse(emp_HireDate);

			try {
				Validator.checkDateFormat(emp_HireDate);
			} catch (InvalidInputDataException err) {
				message +=" Error in Hire Date input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			}
			Date newDate = null;
			try {
				newDate = hire_dateFormate.parse(emp_HireDate);
			} catch (ParseException err) {
				message +=" Error in Hire Date input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			}
			try {
				Validator.CheckNofutureDate(newDate);
			} catch (InvalidInputDataException err) {
				message +=" Error in Hire Date input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			}

			newemp.setHire_date(newDate);
//----------------------------------------
			String jobId = request.getParameter("jobid");
			try {
				Validator.checkjob(jobId);
			} catch (InvalidInputDataException err) {
				message +=" Error in Job Id input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			}
			newemp.setJob_id(jobId);
//---------------------------------------			
			String sal = request.getParameter("salary");
			try {
				Validator.checkStringForParse(sal);
			} catch (InvalidInputDataException err) {
				message +=" Error in Salary input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			}
			float salParse = Float.parseFloat(sal);
			try {
				Validator.checkSalLimit(salParse);
			} catch (InvalidInputDataException err) {
				message +=" Error in Salary input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			}
			newemp.setSalary(salParse);
//----------------------------------------------			
			result = EmployeeDao.updateEmployee(newemp);
			out.println(result + "row Updated");
		} catch (Exception e) {
			message +=" Error while Updating record </p>";
			String errorPage=ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}try {
			out.close();
		} catch (Exception e) {
			message +="Message: "+e.getMessage();
			String errorPage=ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
           return;
		}
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String source="DeleteEmployee";
		String message="<h1>Error while "+source+"</h1>";
	PrintWriter out = response.getWriter();
	Employee newemp = new Employee();
	int empId=0;
	int result=0;
	try {
			String id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message +=" Error in Employee id input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
               return; // It terminates the Code execution beyond this point 
			}
			empId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message +=" Error in Employee id input </p>";
				String errorPage=ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
               return; // It terminates the Code execution beyond this point 
			}
			
			newemp.setEmp_ID(empId);
			result = EmployeeDao.deleteEmployee(empId);
			out.println(result + "row Deleted");
		} catch (Exception e) {
			message +=" Error while Deleting record </p>";
			String errorPage=ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}try {
			out.close();
		} catch (Exception e) {
			message +="Message: "+e.getMessage();
			String errorPage=ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
           return;
		}
	}

}
