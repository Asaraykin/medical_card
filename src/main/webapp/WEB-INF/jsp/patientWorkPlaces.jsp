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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <jsp:include page="fragments/headTag.jsp"/>
    <script src="resources/js/patientWorkPlaces.js" defer></script>
    <link rel="stylesheet" href="resources/css/dataTables.css">
    <script type="text/javascript" src="resources/js/common.js" defer></script>




</head>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container" style="margin-bottom: 70px;">



    <div class="jumbotron" style="margin-top: 70px;">
        <form id="detailsForm" class="needs-validation" novalidate onsubmit="return checkAndSave()">
            <input type="hidden" name="id"  id="id" readonly value="${patientId}">

            <button type="button" class="btn btn-success">+добавить</button>
            <div class="container" style="width: 400px; text-align: center">
                <div class="col-md-12 mb-3">
                    <label for="workPlace">Место работы</label>
                    <table id="workPlace" class="display" style="width:100%">  </table>
                </div>

            </div>

            <div class="container" style="width: 400px; text-align: center">
                <div>
                    <button type="button" class="btn btn-secondary" onclick="back()">Обратно</button>

                        <button type="button" class="btn btn-danger" onclick="deleteEntity('delete')">Удалить</button>

                    <button type="submit" class="btn btn-primary" >Сохранить</button>
                </div>
            </div>
        </form>
    </div>

</div>
<div class="footer" style="text-align: center;">
    <jsp:include page="fragments/footer.jsp"/>

</div>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="userList"/>
</jsp:include>