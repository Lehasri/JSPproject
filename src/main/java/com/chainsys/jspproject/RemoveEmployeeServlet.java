package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class RemoveEmployeeServlet
 */
@WebServlet("/RemoveEmployeeServlet")
public class RemoveEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.setAttribute("result",result);
		RequestDispatcher rd = request.getRequestDispatcher("/deleteemp.jsp");
		rd.forward(request, response);
	}

}

