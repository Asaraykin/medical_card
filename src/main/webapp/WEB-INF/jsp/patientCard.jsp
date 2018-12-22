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
</head>

<body patientId = ${id}>

<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container" style="margin-bottom: 70px;">
    <div class="jumbotron" style="margin-top: 20px">


<div class="col-md-8 order-md-1" style="margin: auto;">
    <h4 class="mb-3">Медицинская карта №: ${id}</h4>
    <form class="needs-validation" novalidate="">
        <div class="mb-3">
            <label for="name">ФИО</label>
                <input type="text" class="form-control" id="name" placeholder="" value="" required="">
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
        </div>

        <div class="mb-3">
            <label for="address">Адрес</label>
            <input type="text" class="form-control" id="address" required="">
            <div class="invalid-feedback">
                Please enter your shipping address.
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 mb-3">
            <label for="telephone">Телефон</label>
            <input type="tel" class="form-control" id="telephone" placeholder="1234 Main St" required="">
            <div class="invalid-feedback">
                Please enter your shipping address.
            </div>
        </div>
            <div class="col-md-8 mb-3">
                <label for="workPlace">Место работы</label>
                <input type="tel" class="form-control" id="workPlace" required="">
                <div class="invalid-feedback">
                    Please enter your shipping address.
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="date_of_birth">Дата рождения</label>
                <input type="date" class="form-control" id="date_of_birth" required="true">
                <div class="invalid-feedback">
                    Please select a valid country.
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="gender">Пол</label>
                <input type="text" class="form-control" id="gender" required="true">
                <div class="invalid-feedback">
                    Please provide a valid state.
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="blood_group">Группа крови</label>
                <input type="number" class="form-control" id="blood_group" placeholder="" required="">
                <div class="invalid-feedback">
                    Zip code required.
                </div>
            </div>
        </div>


<div class="mb-3" style="margin-bottom: 70px; margin-top: 30px;">
    <p style="color: #2b2b2b">Список хирургических вмешательств
    <table id="surgeriesList" class="display" style="width:100%">  </table>
    <a href="/rest/surgery?userId=${id}">Создать новую запись</a>
</div>

        <div class="mb-3" style="margin-bottom: 70px; margin-top: 30px;">
            <p style="color: #2b2b2b">Список обращений
            <table id="visitList" class="display" style="width:100%">  </table>
            <a href="/rest/visit?userId=${id}">Создать новую запись</a>
        </div>



       <%-- <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>--%>
    </form>
</div>
    </div>
</div>


<%--
<div class="modal fade" id="surgeriesEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
--%>


<div class="footer" style="text-align: center;">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
