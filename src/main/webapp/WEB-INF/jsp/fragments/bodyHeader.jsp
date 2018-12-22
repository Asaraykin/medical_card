<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-dark bg-dark">

    <div class="container" >
        <a href="" class="navbar-brand"><img src="resources/images/icon.png"> <spring:message code="app.title"/></a>
        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <div class="nav-item">
                <a class="btn btn-info mr-1" href="profile">Вы вошли как <sec:authentication property="principal.username" /></a>
                    <%--<p style="color:#cbcbcb">Вы вошли как <sec:authentication property="principal.username" /></p>--%>
                <a class="btn btn-primary" href="<c:url value="/logout"/>" role="button">
                    <span class="fa fa-sign-out"> <a  href="<c:url value="/logout" />"/></span>
                </a>
               <%-- <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>--%>
            </div>



        </sec:authorize>
    </div>
</nav>
