<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.List,com.chainsys.jspproject.pojo.Employee,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Emp By Id</title>
</head>
<body>
<table> 
<% 
List<Employee> employeeById = (ArrayList<Employee>)request.getAttribute("emplist");
for(Employee emp: employeeById){
 %>
 <tr>
  <td> <%=emp.getEmp_ID()%>  </td>
  <td> <%=emp.getFirst_name()%>  </td>
  <td> <%=emp.getLast_name()%>  </td>
  <td> <%=emp.getEmail()%>  </td>
  <td> <%=emp.getHire_date()%>  </td>
  <td> <%=emp.getJob_id()%>  </td>
  <td> <%=emp.getSalary()%>  </td>
 </tr>
 <%}%>
 </table>
</body>
</html>