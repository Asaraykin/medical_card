<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Spring Security</title>

    <jsp:include page="fragments/headTag.jsp"/>

    <!-- Custom styles for this template -->

</head>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container" style="margin-top: 70px;">
    <div class="jumbotron" style="margin-top: 20px;">
        <h1><spring:message code="app.title" text="Nothing"/> </h1>
        <p class="lead">
            Электронная медицинская карта облегчает доступ к медицинским данным врачам и пациентам.
        </p>

        <sec:authorize access="!isAuthenticated()">
            <p>
                Для продолжения работы авторизуйтесь.
            </p>
            <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="(hasAuthority('ADMIN'))">
                <p><a href="/profile/create/${userId}">Добавить пользователя</a></p>
            </sec:authorize>
            <sec:authorize access="hasAnyAuthority('ADMIN', 'DOCTOR')">
                <p><a href="/userList?id=${userId}">Список пользователей</a></p>
                <p><a href="/patientListForAdmin?id=${userId}">Список пациентов</a></p>
            </sec:authorize>

            <sec:authorize access="(hasAuthority('PATIENT'))">
                <p><a href="/userList?id=${userId}">Список пациентов</a></p>
            </sec:authorize>
            <p></p>

            <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
        </sec:authorize>
    </div>

</div>
<div class="footer" style="text-align: center;">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
