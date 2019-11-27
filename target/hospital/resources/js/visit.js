let id = $("#id").val();
let parentId = $("#parentId").val();
let parentURL = 'patient';
const ajaxUrl = '/rest/visit';
let form = $('#detailsForm');


$(document).ready(function () {
    $.ajax({
        'url': ajaxUrl + '/' + id + '/' + parentId,
        'method': 'GET',
        'dataType': 'json'
    }).done(function (map) {
        fill(map);
    });
});

function updateTable(data) {
    $("#date").val(data.date);
    $("#patientComplaint").val(data.patientComplaint);
    $("#preliminaryDiagnosis").val(data.preliminaryDiagnosis);
    $("#treatment").val(data.treatment);
    $("#diagnosis").val(data.diagnosis);
    $("#id").val(data.id);
    console.log(form.serialize());
}

function fill(list) {
    console.log(list);
    let dataForDatatable = $.parseJSON("[" + list + "]");
    $('#referrals').dataTable({
        scrollX: false,
        scrollY: 200,
        scrollCollapse: true,
        scroller: true,
        searching: false,
        paging: false,
        info: false,
        "language": {
            "emptyTable": "В таблице отсутствуют данные",},
        "aaData": dataForDatatable[0].referrals,
        "aoColumns": [
            {title: "Дата", "mDataProp": "date",  "width": "20px" },
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
                window.location.href = "/rest/referral?id=" + aData.id + "&visitId=" + id +
                    "&" + parentURL + "Id=" + parentId;
            });
            return nRow;
        }
    });
}







