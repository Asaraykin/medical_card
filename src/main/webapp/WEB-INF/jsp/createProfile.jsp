<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="fragments/headTag.jsp"/>
    <script type="text/javascript" src="resources/js/profile.js" defer></script>
    <script type="text/javascript" src="resources/js/common.js" defer></script>
    <title><spring:message code="app.title"/></title>
</head>

<body class="text-center">
<jsp:include page="fragments/bodyHeader.jsp"/>
<br><br>
<div class="container" style="width: 300px; margin-top: 70px;">
    <form class="form-signin">
        <input type="hidden" id="id" name="id" value="${adminId}">
        <label class="sr-only">Логин</label>
        <input type="text" id="login" name="login" class="form-control" placeholder="Логин">
        <div class="text-danger" id="invalidLogin">
            <small></small>
        </div>
        <p></p>
        <sec:authorize access="(hasAuthority('ADMIN'))">
            <label class="sr-only">Роль</label>
            <select class="form-control" id="role">
                <option value="ADMIN">ADMIN</option>
                <option value="DOCTOR">DOCTOR</option>
                <option value="PATIENT">PATIENT</option>
            </select>
            <p></p>
        </sec:authorize>
            <label class="sr-only">Введите пароль</label>
        <input type="password" id="passwordNew1" name="passwordNew1" class="form-control" placeholder="Введите новый пароль" >
        <div class="text-danger" id="invalidPasswords1">
            <small></small>
        </div>
        <p></p>
        <label class="sr-only">Повторите пароль</label>
        <input type="password" id="passwordNew2" name="passwordNew2" class="form-control" placeholder="Повторите новый пароль" >
        <div class="text-danger" id="invalidPasswords2">
            <small></small>
        </div>
    </form>
<sec:authorize access="(hasAuthority('ADMIN'))">
        <button class="btn btn-lg btn-primary btn-block" onclick="createUserWithRole()">Создать</button>
</sec:authorize>
    <sec:authorize access="(!hasAuthority('ADMIN'))">
        <button class="btn btn-lg btn-primary btn-block" onclick="createUser()">Создать</button>
    </sec:authorize>
    <p class="mt-5 mb-3 text-muted">© 2018-2019</p>
</div>

<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>

</body>

<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="userList"/>
</jsp:include>