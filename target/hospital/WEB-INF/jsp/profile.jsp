<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="fragments/headTag.jsp"/>
    <script type="text/javascript" src="resources/js/profile.js" defer></script>
    <script type="text/javascript" src="resources/js/common.js" defer></script>
    <title><spring:message code="app.title"/> </title>
</head>

<body class="text-center">
<jsp:include page="fragments/bodyHeader.jsp"/>
<br><br>
<div class="container" style="width: 300px; margin-top: 70px;">
    <form class="form-signin">
        <input type="hidden" id="id" name="id" value="${userId}">
        <h1 class="h3 mb-3 font-weight-normal">Смена пароля</h1>
        <label  class="sr-only">Старый пароль</label>
        <input type="password" id="oldPassword" name="oldPassword" class="form-control" placeholder="Старый пароль" >
        <label class="sr-only">Введите новый пароль</label>
        <input type="password" id="passwordNew1" name="passwordNew1" class="form-control" placeholder="Введите новый пароль" >
        <label class="sr-only">Повторите новый пароль</label>
        <input type="password" id="passwordNew2" name="passwordNew2" class="form-control" placeholder="Повторите новый пароль" >
    </form>
    <button class="btn btn-lg btn-primary btn-block" onclick="changePassword()">Подтвердить</button>
    <p class="mt-5 mb-3 text-muted">© 2018-2019</p>
</div>

<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>

</body>

<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="userList"/>
</jsp:include>