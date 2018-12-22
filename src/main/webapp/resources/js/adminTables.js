$(document).ready(function() {
    $.ajax({
        'url':  '/rest/admin/userList',
        'method': 'GET',
        'dataType': 'json'
    }).done( function(data) {
        $('#adminList').dataTable( {
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
                { "data": "id" },
                { "data": "login" },
                { "data": "password" },
                { "data": "role" },
                {
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "render": renderDeleteBtn,
                    "defaultContent": "",
                    "orderable": false
                },
            ],
            "aaSorting": [
                1, "asc"
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
        })
    })
} );






$.ajax({
    url: '/rest/admin/userList',
    type: 'GET',
    dataType: 'json',
    success: [function(data) {

        // data argument is the result you want
        console.log(data);
    }]
});