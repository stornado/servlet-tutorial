<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 2016/12/26
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <h2>Employees</h2>
    <!--Search Form -->
    <form action="/employee" method="get" id="seachEmployeeForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="employeeName" id="employeeName" class="form-control" required="true"
                   placeholder="Type the Name or Last Name of the employee"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br>
        <br>
    </form>

    <!--Employees List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/employee" method="post" id="employeeForm" role="form">
        <input type="hidden" id="idEmployee" name="idEmployee">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty employeeList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Name</td>
                        <td>Last name</td>
                        <td>Birth date</td>
                        <td>Role</td>
                        <td>Department</td>
                        <td>E-mail</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="employee" items="${employeeList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${idEmployee == employee.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/employee?idEmployee=${employee.id}&searchAction=searchById">${employee.id}</a>
                            </td>
                            <td>${employee.name}</td>
                            <td>${employee.lastName}</td>
                            <td>${employee.birthDate}</td>
                            <td>${employee.role}</td>
                            <td>${employee.department}</td>
                            <td>${employee.email}</td>
                            <td><a href="#" id="remove"
                                   onclick="document.getElementById('action').value = 'remove';document.getElementById('idEmployee').value = '${employee.id}';

                                           document.getElementById('employeeForm').submit();">
                                <span class="glyphicon glyphicon-trash"/>
                            </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No people found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/new-employee.jsp">
        <br>
        <button type="submit" class="btn btn-primary  btn-md">New employee</button>
    </form>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>
