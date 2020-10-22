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
<form  action="<c:url value='/update'/>" method='POST'>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type='text' name='name' value=${accident.name}></td>
            <br>
            <td>Description:</td>
            <td><input type='text' name='text' value=${accident.text}></td>
            <br>
            <td>Address:</td>
            <td><input type='text' name='address' value=${accident.address}></td>
            <input type='hidden' name="id" value=${accident.id}>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Update" /></td>
        </tr>
    </table>
</form>
</body>
</html>
