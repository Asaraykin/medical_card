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
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="resources/css/dataTables.css">
    <script type="text/javascript" src="resources/js/visit.js" defer></script>
    <script type="text/javascript" src="resources/js/common.js" defer></script>
</head>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container" style="margin-bottom: 70px;">
    <div class="jumbotron" style="margin-top: 70px;">
        <form id="detailsForm" class="needs-validation" novalidate onsubmit="return checkAndSave()">
            <input type="hidden" name="id" id="id" readonly value="${visit.getId()}">
            <input type="hidden" name="parentId" id="parentId" readonly value="${patientId}">
            <div class="container" style="width: 300px;">
                <div class="col-md-12 mb-2" style="text-align: center">
                    <label for="date">Дата обращения</label>
                    <input type="date" class="form-control" name="date" id="date" required=""
                           value="${visit.getDate()}">
                    <div class="invalid-feedback">
                        Введите дату.
                    </div>
                </div>
            </div>

            <div class="container" style="width: 800px; text-align: center">
                <div class="col-md-12 mb-3">
                    <label for="patientComplaint">Жалобы</label>
                    <textarea class="form-control" name="patientComplaint" id="patientComplaint" rows="8"
                              required="yes">${visit.getPatientComplaint()}</textarea>
                    <div class="invalid-feedback">
                        Заполните жалобы пациента.
                    </div>
                </div>
            </div>

            <div class="container" style="width: 800px; text-align: center">
                <div class="col-md-12 mb-3">
                    <label for="preliminaryDiagnosis">Предварительный диагноз</label>
                    <textarea class="form-control" name="preliminaryDiagnosis" id="preliminaryDiagnosis"
                              rows="8">${visit.getPreliminaryDiagnosis()}</textarea>
                </div>
            </div>

            <div class="container" style="width: 800px; text-align: center">
                <div class="col-md-12 mb-3">
                    <label for="treatment">Назначенное лечение</label>
                    <textarea class="form-control" name="treatment" id="treatment" rows="8"
                              required="yes">${visit.getTreatment()}</textarea>
                    <div class="invalid-feedback">
                        Лечение не назначено.
                    </div>
                </div>
            </div>

            <div class="container" style="width: 800px; text-align: center">
                <div class="col-md-12 mb-3">
                    <label for="diagnosis">Окончательный диагноз</label>
                    <textarea class="form-control" name="diagnosis" id="diagnosis" rows="8"
                              required="yes">${visit.getDiagnosis()}</textarea>
                    <div class="invalid-feedback">
                        Диагноз не поставлен.
                    </div>
                </div>
            </div>

            <c:if test="${!empty visit.getId()}">
                <div class="container" style="width: 800px; text-align: center; margin-bottom: 30px;">
                    <div class="mb-3" style="margin-top: 30px;">
                        <p style="color: #2b2b2b">Список выданных направлений на обследования</p>
                        <table id="referrals" class="display" style="width:100%"></table>
                        <br>
                        <a href="/rest/referral?visitId=${visit.getId()}">Выписать новое направление</a>
                    </div>
                </div>
            </c:if>

            <div class="container" style="width: 800px; text-align: center">
                <div>
                    <button type="button" class="btn btn-secondary" onclick="back()">Обратно</button>
                    <c:if test="${!empty visit.getId()}">
                        <button type="button" class="btn btn-danger" onclick="deleteEntity('delete')">Удалить</button>
                    </c:if>
                    <button class="btn btn-primary" type="submit">Сохранить</button>
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