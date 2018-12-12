<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Медицинская карта</title>
</head>
<body>
<h1>${patient.getName()}</h1>
<div>
    <label for="textfield">Имя пациента:</label>
    <input type="text" name="patientName" id="textfield">
   <c:forEach items="${surgery}" var="surgery" >
        ${surgery.getType()}
   </c:forEach>

    <a href="/delete?id=${patient.getId()}">delete</a>

</div>
</body>
</html>
