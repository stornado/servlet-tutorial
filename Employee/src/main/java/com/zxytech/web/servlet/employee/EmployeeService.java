package com.zxytech.web.servlet.employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 2016/12/26.
 */
public class EmployeeService {

    List<Employee> employeeList = EmployeeList.getInstance();

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> result = new ArrayList<Employee>();
        for (Employee employee : employeeList) {
            if (employee.getName().equalsIgnoreCase(name) || employee.getLastName().equalsIgnoreCase(name)) {
                result.add(employee);
            }
        }
        return result;
    }

    public Employee getEmployee(long id) throws Exception {
        List<Employee> result = new ArrayList<Employee>();
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new Exception("The Employee id " + id + " not found");
    }

    public long addEmployee(Employee employee) {
        employeeList.add(employee);
        return employee.getId();
    }

    public boolean updateEmployee(Employee customer) {
        Employee employee = null;
        try {
            employee = getEmployee(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        employee.setBirthDate(customer.getBirthDate());
        employee.setDepartment(customer.getDepartment());
        employee.setEmail(customer.getEmail());
        employee.setLastName(customer.getLastName());
        employee.setName(customer.getName());
        employee.setRole(customer.getRole());
        return true;
    }

    public boolean deleteEmployee(long id) {
        Employee employee = null;
        try {
            employee = getEmployee(id);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (employeeList.remove(employee)) {
            return true;
        } else {
            return false;
        }

    }

}
