<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 18.10.2020
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <table class="table table-dark">
        <thead>
        <tr>
            <th>Id</th>
            <th>name</th>
            <th>text</th>
            <th>address</th>
            <th>type</th>
            <th>rule</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="accident" items="${accidents}">
            <tr>
                <td>${accident.value.id}</td>
                <td>${accident.value.name}</td>
                <td>${accident.value.text}</td>
                <td>${accident.value.address}</td>
                <td>${accident.value.type.id}</td>
                <td><c:forEach var="role" items="${accident.value.rules}">
                    ${role.name}<br>
                </c:forEach>
                </td>
                <td>
                    <a href="<c:url value='/update?id=${accident.key}'/>">Update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<a href="<c:url value='/create'/>">Добавить инцидент</a>
</body>
</html>