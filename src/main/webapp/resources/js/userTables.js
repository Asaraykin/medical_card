let ajaxUrl = '/rest/patient';
let id = 0;
let parentId = 0;
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
                { title: "name", "data": "name"},
                { title: "date_of_birth","data": "date_of_birth" },
                { title: "address","data": "address" },
                { title: "telephone","data": "telephone" },
                {
                    title: "Открыть/Редактировать","render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    title: "Удалить","render": renderDeleteBtn,
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
                $(nRow).click(function() {
                    id = aData.id;
                    renderDeleteBtn(aData, "display", nRow);
                    renderEditBtn(aData, "display", nRow);
                    /*window.location.href = "/rest/patient?id=" + aData.id;;*/
                });
                return nRow;
            }
        });
    });
});


function deleteUser(id){
          deleteEntity();
        //console.log(id);

}

function editUser(id){
    window.location.href = "/rest/patient?id=" + id;
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        let id = row.id;
        return "<a href='javascript:editUser("+id+")'><span class='fa fa-pencil'></span></a>";
    }
}

