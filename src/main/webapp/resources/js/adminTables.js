let id;
let authorizedUserId = $("#userId").val();
let ajaxUrl = '/rest/admin/userList';
let parentId = 0;

//https://www.datatables.net/manual/server-side
$(document).ready(function() {
    $('#adminList').dataTable({
           "ajax": {
               'url':  '/rest/admin/userList',
               'method': 'GET',
               'dataType': 'json',
           },
               "processing": true,
           "serverSide": true,
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

                "aoColumns": [
                    { title: "ID","data": "id" },
                    { title: "Логин","data": "login" },
                    /*{title: "Пароль", "data": "password" },*/
                    { title: "Роль","data": "role" },
                    {title: "Редактировать",
                        "render": renderEditBtn,
                        "defaultContent": "",
                        "orderable": false
                    },
                    {title: "Удалить",
                        "render": renderDeleteBtn,
                        "defaultContent": "",
                        "orderable": false
                    },
                    {title:"Добавить пациента",
                        "render": renderAddPatientBtn,
                        "defaultContent": "",
                        "orderable": false
                    },
                ],
                "aaSorting": [
                    1, "asc"
                ],
                'columnDefs': [
                    {
                        "targets": 3, // your case first column
                        "className": "text-center"
                    },
                    {
                        "targets": 4,
                        "className": "text-center",
                    },
                    {
                        "targets": 5,
                        "className": "text-center",
                    }],
                "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                    // Bind click event
                    $(nRow).click(function() {
                        id = aData.id;
                        renderDeleteBtn();
                        renderEditBtn();
                        renderAddPatientBtn();
                    });
                    return nRow;
                }
            });


        });


/*
$(document).ready(function() {

    $.ajax({
        'url': "resources/js/array.txt",
     //   'url':  '/rest/admin/userList',
        'method': 'GET',
        "serverSide": true,


    }).done( function(data) {
      // data = {"recordsFiltered":22,"data":[{"id":100004,"login":"Admin1","role":"ADMIN"},{"id":100003,"login":"Doctor1","role":"DOCTOR"},{"id":100000,"login":"Patient1","role":"PATIENT"},{"id":100011,"login":"Patient10","role":"PATIENT"},{"id":100012,"login":"Patient11","role":"PATIENT"},{"id":100013,"login":"Patient12","role":"PATIENT"},{"id":100014,"login":"Patient13","role":"PATIENT"},{"id":100001,"login":"Patient2","role":"PATIENT"},{"id":100002,"login":"Patient3","role":"PATIENT"},{"id":100005,"login":"Patient4","role":"PATIENT"},{"id":100006,"login":"Patient5","role":"PATIENT"},{"id":100007,"login":"Patient6","role":"PATIENT"},{"id":100008,"login":"Patient7","role":"PATIENT"},{"id":100009,"login":"Patient8","role":"PATIENT"},{"id":100010,"login":"Patient9","role":"PATIENT"}],"draw":2,"recordsTotal":22}
     //  console.log($.parseJSON("[" + data + "]"));
    //    data = $.parseJSON("[" + data + "]");

        $('#adminList').DataTable( {

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


            "columns": [
                { title: "ID", "data": "id" },
                { title: "Логин","data": "login" },
                /!*{title: "Пароль", "data": "password" },*!/
                { title: "Роль","data": "role" },
                {title: "Редактировать",
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {title: "Удалить",
                    "render": renderDeleteBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {title:"Добавить пациента",
                    "render": renderAddPatientBtn,
                    "defaultContent": "",
                    "orderable": false
                },
            ],
            "aaSorting": [
                1, "asc"
            ],
            'columnDefs': [
                {
                    "targets": 3, // your case first column
                    "className": "text-center"
                },
                {
                    "targets": 4,
                    "className": "text-center",
                },
                {
                    "targets": 5,
                    "className": "text-center",
                }],

            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                // Bind click event
                $(nRow).click(function() {
                    id = aData.id;
                    renderDeleteBtn();
                    renderEditBtn();
                    renderAddPatientBtn();
                });
                return nRow;
            }
        });

    })
}

);*/


function deleteUser(){
    if(authorizedUserId == id){
        failNoty("Нельзя удалить самого себя")
    }
    else {
        deleteEntity();
       // console.log(id);
    }
}

function editUser(){
    console.log("edit");
    window.location.href = "/profile/" + id;

}

function addPatient() {
    window.location.href = "/rest/patient?id=" + id;
}
function renderAddPatientBtn(data, type, row) {
    if (type === "display") {
        return "<a href='javascript:addPatient()'><span class='fa fa-plus-square'></span></a>";
    }
}



function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a href='javascript:editUser()'><span class='fa fa-pencil'></span></a>";
    }
}