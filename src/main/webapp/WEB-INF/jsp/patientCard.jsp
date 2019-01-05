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
    <script type="text/javascript" src="resources/js/patientCard.js" defer></script>
    <script type="text/javascript" src="resources/js/common.js" defer></script>

</head>

<body patientId= ${id} noPatient=${noPatient}>


<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container" style="margin-bottom: 70px;">

    <div class="jumbotron" style="margin-top: 20px">
        <form class="needs-validation" id="detailsForm" novalidate onsubmit="return checkAndSavePatient()">

            <div class="col-md-8 order-md-1" style="margin: auto;">
                <h4 class="mb-3">Медицинская карта №: ${id}</h4>
            </div>

            <div class="col-md-8 order-md-1"
                 style="margin: auto; background-color: #ccd2fc; padding-bottom: 20px; padding-top: 10px">

                <div class="row">
                <div class="col-md-9 mb-3">
                    <label for="name">ФИО</label>
                    <input type="text" class="form-control" id="name" name="name" required value="1">
                    <div class="invalid-feedback" style="width: 100%;">
                      Необходимо ввести ФИО
                    </div>
                </div>
                <div class="col-md-3 mb-3">
                    <label for="oms">Номер ОМС</label>
                    <input type="text" class="form-control" id="oms" name="oms" required value="1">
                    <div class="invalid-feedback" style="width: 100%;">
                        Необходимо ввести номер ОМС
                    </div>
                </div>
                </div>

                <div class="mb-3">
                    <label for="address">Адрес</label>
                    <input type="text" class="form-control" id="address" name="address" required value="1">
                    <div class="invalid-feedback" style="width: 100%;">
                        Необходимо ввести адрес
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="telephone">Телефон</label>
                        <input type="tel" class="form-control" id="telephone"  placeholder="(___)___-__-__"  name="telephone" <%--pattern="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$"--%> >
                    </div>


                    <c:if test="${noPatient != true}">
                    <div class="col-md-8 mb-3" style="margin-top: -15px">
                        <%-- <label for="workPlace">Место работы</label>--%>
                        <table id="workPlace" class="display" style="width:100%"></table>
                    </div>
                    <div class="col-md-1 mb-1" style="margin-top: 31px; margin-left: -10px">
                        <button type="button" class="btn btn-success" onclick='editWork(" + add + ")'>+</button>
                    </div>
                    </c:if>

                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="date_of_birth">Дата рождения</label>
                        <input type="date" class="form-control" id="date_of_birth" name="date_of_birth" required value="2018-07-22">
                        <div class="invalid-feedback" style="width: 100%;">
                            Необходимо ввести дату рождения
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="gender">Пол</label>
                        <input type="text" class="form-control" id="gender" name="gender" required pattern="[М|Ж|м|ж]{1}" value="М">
                        <div class="invalid-feedback" style="width: 100%;">
                            Необходимо указать пол (формат: М или Ж)
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="blood_group">Группа крови</label>
                        <input type="number" class="form-control" id="blood_group" name="blood_group" min="1" max="4" value="1">
                        <div class="invalid-feedback" style="width: 100%;">
                         Группа крови от 1 до 4
                        </div>
                    </div>
                </div>

                <div class="container" style=" text-align: right">
                    <%--<button type="button" class="btn btn-danger" onclick="deleteEntity('delete')">Удалить</button>--%>
                    <button class="btn btn-primary" type="submit">Сохранить</button>
                </div>
            </div>
        </form>
        <%--dfkjghdkjfh--%>

        <div class="col-md-8 order-md-1" style="margin: auto;">
            <div class="mb-3" style="margin-bottom: 70px; margin-top: 30px;">
                <p style="color: #2b2b2b">Список хирургических вмешательств
                <table id="surgeriesList" class="display" style="width:100%"></table>
                <a href="/rest/surgery?patientId=${id}">Создать новую запись</a>
            </div>

            <div class="mb-3" style="margin-bottom: 70px; margin-top: 30px;">
                <p style="color: #2b2b2b">Список обращений
                <table id="visitList" class="display" style="width:100%"></table>
                <a href="/rest/visit?patientId=${id}">Создать новую запись</a>
            </div>
        </div>


    </div>
</div>


<div class="modal fade" id="workModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Редактировать место работы</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">
                <form id="detailsFormModal" class="needs-validation"
                      novalidate <%--onsubmit="return checkAndSaveWorkPlace()"--%>>

                    <div class="container" style="width: 400px; text-align: center; margin-bottom: 30px">


                        <select id="selectList">
                        </select>


                    </div>
                    <div class="container" style="width: 400px; text-align: center; margin-bottom: 70px">

                        <button type="button" class="btn btn-primary" onclick="checkAndSaveWorkPlace()">Сохранить
                        </button>
                    </div>
                </form>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>


<div class="footer" style="text-align: center;">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="userList"/>
</jsp:include>
