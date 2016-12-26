package com.zxytech.web.servlet.employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ryan on 2016/12/26.
 */
@WebServlet(
        name = "EmployeeServlet",
        urlPatterns = {"/employee"}
)
public class EmployeeServlet extends HttpServlet {

    EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action != null) {
            if (action.equalsIgnoreCase("searchById")) {
                searchEmployeeById(req, resp);
            } else if (action.equalsIgnoreCase("searchByName")) {
                searchEmployeeByName(req, resp);
            }
        } else {
            List<Employee> result = employeeService.getAllEmployees();
            forwardListEmployees(req, resp, result);
        }
    }

    private void searchEmployeeById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long idEmployee = Integer.valueOf(req.getParameter("idEmployee"));
        Employee employee = null;
        try {
            employee = employeeService.getEmployee(idEmployee);
        } catch (Exception ex) {
            Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("employee", employee);
        req.setAttribute("action", "edit");
        String nextJSP = "/new-employee.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void searchEmployeeByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String employeeName = req.getParameter("employeeName");
        List<Employee> result = employeeService.searchEmployeesByName(employeeName);
        forwardListEmployees(req, resp, result);
    }

    private void forwardListEmployees(HttpServletRequest req, HttpServletResponse resp, List employeeList)
            throws ServletException, IOException {
        String nextJSP = "/list-employees.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("employeeList", employeeList);
        dispatcher.forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("En el doPost");
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("add")) {
            addEmployeeAction(req, resp);
        } else if (action.equalsIgnoreCase("edit")) {
            editEmployeeAction(req, resp);
        } else if (action.equalsIgnoreCase("remove")) {
            removeEmployeeByName(req, resp);
        }

    }
    private void addEmployeeAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthDate");
        String role = req.getParameter("role");
        String department = req.getParameter("department");
        String email = req.getParameter("email");
        Employee employee = new Employee(name, lastName, birthday, role, department, email);
        long idEmployee = employeeService.addEmployee(employee);
        List<Employee> employeeList = employeeService.getAllEmployees();
        req.setAttribute("idEmployee", idEmployee);
        String message = "The new employee has been successfully created.";
        req.setAttribute("message", message);
        forwardListEmployees(req, resp, employeeList);
    }

    private void editEmployeeAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthDate");
        String role = req.getParameter("role");
        String department = req.getParameter("department");
        String email = req.getParameter("email");
        long idEmployee = Integer.valueOf(req.getParameter("idEmployee"));
        Employee employee = new Employee(name, lastName, birthday, role, department, email, idEmployee);
        employee.setId(idEmployee);
        boolean success = employeeService.updateEmployee(employee);
        String message = null;
        if (success) {
            message = "The employee has been successfully updated.";
        }
        List<Employee> employeeList = employeeService.getAllEmployees();
        req.setAttribute("idEmployee", idEmployee);
        req.setAttribute("message", message);
        forwardListEmployees(req, resp, employeeList);
    }

    private void removeEmployeeByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long idEmployee = Integer.valueOf(req.getParameter("idEmployee"));
        boolean confirm = employeeService.deleteEmployee(idEmployee);
        if (confirm){
            String message = "The employee has been successfully removed.";
            req.setAttribute("message", message);
        }
        List<Employee> employeeList = employeeService.getAllEmployees();
        forwardListEmployees(req, resp, employeeList);
    }

}

