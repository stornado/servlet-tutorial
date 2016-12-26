<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 2016/12/26
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>$Title$</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form action="/employee" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}"/>
        <input type="hidden" id="idEmployee" name="idEmployee" value="${employee.id}"/>
        <h2>Employee</h2>
        <div class="form-group col-xs-4">
            <label for="name" class="control-label col-xs-4">Name:</label>
            <input type="text" name="name" id="name" class="form-control" value="${employee.name}"
                   required="true"/>

            <label for="lastName" class="control-label col-xs-4">Last name:</label>
            <input type="text" name="lastName" id="lastName" class="form-control" value="${employee.lastName}"
                   required="true"/>

            <label for="birthdate" class="control-label col-xs-4">Birth date</label>
            <input type="text" pattern="^\d{2}-\d{2}-\d{4}$" name="birthDate" id="birthdate" class="form-control"
                   value="${employee.birthDate}" maxlength="10" placeholder="dd-MM-yyy" required="true"/>

            <label for="role" class="control-label col-xs-4">Role:</label>
            <input type="text" name="role" id="role" class="form-control" value="${employee.role}"
                   required="true"/>

            <label for="department" class="control-label col-xs-4">Department:</label>
            <input type="text" name="department" id="department" class="form-control"
                   value="${employee.department}" required="true"/>

            <label for="department" class="control-label col-xs-4">E-mail:</label>
            <input type="text" name="email" id="email" class="form-control" value="${employee.email}"
                   placeholder="smith@aol.com" required="true"/>

            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
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