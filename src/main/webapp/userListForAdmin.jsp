<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Выберите запись</h1>
<table class=table_borders align="center">
    <tr><td>id</td><td>Логин</td><td>Роль</td><td>Delete</td><td>Update</td></tr>
    <c:forEach items="${userList}" var="user">
        <%--   <jsp:useBean id="user" scope="page" type="model.User"/> --%>
<tr>
    <td class="td_style">
        ${user.getId()}
    </td>
    <td>
            ${user.getLogin()}
    </td>
    <td>
            ${user.getRole()}
    </td>
    <td>
        <a href="" >delete</a>
    </td>
    <td>
            update
    </td>
</tr>
</c:forEach>
</table>

</body>
</html>
