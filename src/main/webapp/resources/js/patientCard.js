const id = $("body").attr("patientId");
let ajaxUrl = '/rest/patient/patientCard/'

$(document).ready(function () {
    $.ajax({
        'url': ajaxUrl + '?id=' + id,
        'method': 'GET',
        'dataType': 'json'
    }).done(function (list) {
        fill(list);
    });
});

function fill(list) {
    let objects = $.parseJSON(list);
    let dataForDatatable = $.parseJSON("[" + list + "]");
    $("#name").val(objects.patient.name);
    $("#address").val(objects.patient.address);
    $("#telephone").val(objects.patient.telephone);
    $("#date_of_birth").val(objects.patient.date_of_birth);
    $("#gender").val(objects.patient.gender);
    $("#blood_group").val(objects.patient.blood_group);
    let temp = "";
    $.each(objects.patient.workPlaces, function (index, value) {
        temp += value.name + " "
    })
    $("#workPlace").val(
        temp
    );
    $('#surgeriesList').dataTable({
        scrollX: false,
        scrollY: 200,
        scrollCollapse: true,
        scroller: true,
        searching: false,
        paging: false,
        info: false,
        "aaData": dataForDatatable[0].surgeries,
        "aoColumns": [
            {title: "Дата", "mDataProp": "date",  "width": "80px" },
            {title: "Описание", "mDataProp": "type"},
        ],
        'columnDefs': [
            {
                "targets": 0, // your case first column
                "className": "text-center"
            },
            {
                "targets":  1,
                "className": "text-center",
            }],
           "fnRowCallback": function (nRow, aData, iDisplayIndex) {
               $(nRow).click(function() {
                   window.location.href = "/rest/surgery?id=" + aData.id + "&patientId=" + id;
               });
               return nRow;
           }
    });
    $('#visitList').dataTable({
        scrollX: false,
        scrollY: 200,
        scrollCollapse: true,
        scroller: true,
        searching: false,
        paging: false,
        info: false,
        "aaData": dataForDatatable[0].visits,
        "aoColumns": [
            {title: "Дата", "mDataProp": "date",  "width": "80px" },
            {title: "Жалобы", "mDataProp": "patientComplaint",  "width": "100px" },
            {title: "Диагноз", "mDataProp": "diagnosis",  "width": "100px" },
            {title: "Лечение", "mDataProp": "treatment"},
        ],
        'columnDefs': [
            {
                "targets": 0, // your case first column
                "className": "text-center"
            },
            {
                "targets":  1,
                "className": "text-center",
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            $(nRow).click(function() {
                window.location.href = "/rest/visit?id=" + aData.id + "&patientId=" + id;
            });
            return nRow;
        }
    });
};


/*$("#surgeries").click(function () {
    $('#surgeriesEdit').modal({
        keyboard: true
    })
});*/


