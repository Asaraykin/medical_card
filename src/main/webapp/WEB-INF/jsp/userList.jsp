<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <title>Title</title>
   <jsp:include page="fragments/headTag.jsp"/>
    <link href="${mainCss}" rel="stylesheet" />
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<table class=table_borders align="center">
    <h1 align="center">Выберите запись</h1>
    <tr><td>id</td><td>Имя</td><td>Дата рождения</td><td>Адрес</td><td></td></tr>
    <c:forEach items="${userList}" var="user">
        <%--   <jsp:useBean id="user" scope="page" type="model.User"/> --%>
        <tr>
            <td class="td_style">
                <a href="/patientCard?id=${user.getId()}" >  ${user.getName()}</a>
            </td>
            <td>
                    ${user.getDate_of_birth()}
            </td>
            <td>
                    ${user.getAddress()}
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
<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>

