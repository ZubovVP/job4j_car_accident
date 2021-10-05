<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20.10.2020
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type='text' name='name' value=></td>
            <br>
            <td>Description:</td>
            <td><input type='text' name='text'></td>
            <br>
            <td>Address:</td>
            <td><input type='text' name='address'></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select></td>
        </tr>
        <tr>
            <td>Статьи:</td>
            <td>
                <select name="rIdName" multiple>
                    <c:forEach var="rule" items="${rules}">
                        <option value="${rule.id}_${rule.name}">${rule.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
    <input hidden name="status" value="Принята">
</form>
</body>
</html>
