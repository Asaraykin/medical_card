let id = $("#id").val();
let ajaxUrl = '/rest/patient/patientWorkPlaces/';
$(document).ready(function () {
    $.ajax({
        'url': ajaxUrl + '?id=' + $("#id").val(),
        'method': 'GET',
        'dataType': 'json'
    }).done(function (list) {
        fill(list);
    });
});


function fill(list) {
    let dataForDatatable = $.parseJSON("[" + list + "]");
    $('#workPlace').dataTable({
        searching: false,
        paging: false,
        info: false,
        "aaData": dataForDatatable[0].work,
        "aoColumns": [
            { title: "Наименование", "mDataProp": "name", width:'200px'},
            {
                title: "Редактировать",
                "render": renderEditBtn,
                "defaultContent": "",
                "orderable": false,
            },
        ],
        'columnDefs': [
            {
                "targets": 0, // your case first column
                "className": "text-left"
            },
            {
                "targets": 1, // your case first column
                "className": "text-center"
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            $(nRow).click(function() {
                window.location.href = "/rest/patientWorkPlaces?id=" + id;
            });
            return nRow;
        }
    });
}