<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise </title>
</head>
<body class="text-center">

<%--<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <div class="navbar-brand"><img src="resources/images/icon.png"> <spring:message code="app.title"/></div>
        <form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
            <input class="form-control mr-1" type="text" placeholder="Email" name="username">
            <input class="form-control mr-1" type="password" placeholder="Password" name="password">
            <button class="btn btn-success" type="submit">
                <span class="fa fa-sign-in"></span>
            </button>
        </form>
    </div>
</nav>--%>

<a href="/test">test</a>



<b>Авторизация</b>
<form method="post" action="userList">
    <b>Meals of</b>
    <select name="userId">
        <option value="100000">User</option>
        <option value="100003">Doctor</option>
        <option value="100004">Admin</option>
    </select>
    <button type="submit">Select</button>
</form>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript">
    function login(username, password) {
        setCredentials(username, password);
        $("#login_form").submit();
    }
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>
