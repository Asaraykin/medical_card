var ajaxUrl = "ajax/rest";
$(document).ready(function() {
    $.ajax({

        'url':  '/rest/userList',
        'method': 'GET',
        'dataType': 'json'
    }).done( function(data) {
        $('#userList').dataTable( {
            "language": {
            "processing": "Подождите...",
            "search": "Поиск:",
            "lengthMenu": "Показать _MENU_ записей",
            "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
            "infoEmpty": "Записи с 0 до 0 из 0 записей",
            "infoFiltered": "(отфильтровано из _MAX_ записей)",
            "infoPostFix": "",
            "loadingRecords": "Загрузка записей...",
            "zeroRecords": "Записи отсутствуют.",
            "emptyTable": "В таблице отсутствуют данные",
            "paginate": {
            "first": "Первая",
                "previous": "Предыдущая",
                "next": "Следующая",
                "last": "Последняя"
        },
            "aria": {
            "sortAscending": ": активировать для сортировки столбца по возрастанию",
                "sortDescending": ": активировать для сортировки столбца по убыванию"
        }
        },
            "aaData": data,
            "columns": [
                { "data": "name"},
                { "data": "date_of_birth" },
                { "data": "address" },
                { "data": "telephone" },
                {
                    "render": renderShowBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
            ],
            "aaSorting": [
                0, "asc"
            ],
            'columnDefs': [
                {
                    "targets": 4, // your case first column
                    "className": "text-center"
                },
                {
                    "targets": 5,
                    "className": "text-center",
                }],
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {

                // Bind click event
                $(nRow).click(function() {
                    window.location.href = "/rest/patient?id=" + aData.id;
                });
                return nRow;
            }
        });
        successNoty("common.saved");
     console.log(data);
    });

});


function showRow(id) {
    $.ajax({
        'url': ajaxUrl + "/patientCard" + id,
        'method': 'GET',
        'dataType': 'json'
    })
        .done(function (data) {
            $('#userList').dataTable({})
        });
}






$.ajax({
    url: '/rest/userList',
    type: 'GET',
    dataType: 'json',
    success: [function(data) {

        // data argument is the result you want

    }]
});