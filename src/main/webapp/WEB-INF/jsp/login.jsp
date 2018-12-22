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

    <title><spring:message code="app.title"/> </title>


</head>


<body class="text-center">
<jsp:include page="fragments/bodyHeader.jsp"/>
<br><br>
<div class="container" style="width: 300px; margin-top: 70px;">
<c:url value="/j_spring_security_check" var="loginUrl" />
<form class="form-signin" action="${loginUrl}" method="post">

    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

    <label  class="sr-only">Логин</label>
    <input type="text" name="j_username" class="form-control" placeholder="Email address" >
    <label class="sr-only">Пароль</label>
    <input type="password" name="j_password" class="form-control" placeholder="Password" >
  <%--  <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>--%>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="app.enter"/> </button>
    <p class="mt-5 mb-3 text-muted">© 2018-2019</p>
</form>
</div>

<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>

</body>

