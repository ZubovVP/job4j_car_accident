<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20.10.2020
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Имя:</td>
            <td><input type='text' name='name' value=${accident.name}></td>
            <br>
            <td>Описание:</td>
            <td><input type='text' name='text' value=${accident.text}></td>
            <br>
            <td>Адрес:</td>
            <td><input type='text' name='address' value=${accident.address}></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Статья нарушения:</td>
            <td>
                <select name="rIdName" multiple>
                    <c:forEach var="rule" items="${rules}">
                        <option value="${rule.id}_${rule.name}">${rule.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td>Статус:</td>
            <td>
                <select name="status" multiple>
                    <c:forEach var="status" items="${statuses}">
                        <option value="${status.name}">${status.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Update"/></td>
        </tr>
    </table>
</form>
</body>
</html>