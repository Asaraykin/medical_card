let id = $("body").attr("patientId");
let noPatient = $("body").attr("noPatient");
let ajaxUrl = '/rest/patient/patientCard';
let parentURL = 'patient';
let form = $('#detailsFormModal');
let parentId = id;

$(document).ready(function () {
    let link;
    if (noPatient) {
        link = '/rest/patient/patientCard';
    }
    else {
        link = ajaxUrl + '?id=' + id;
    }
    $.ajax({
        'url': link,
        'method': 'GET',
        'dataType': 'json'
    }).done(function (list) {
        console.log(list)
        if (!noPatient) {
            fill(list);
        }
       /* else {
            fillUsers(list);
        }*/
    });
});

/*function fillUsers(data) {
    let list = $.parseJSON("[" + data + "]");
    console.log(list[0].users);
    let html = "";
    for (let i = 0; i < list[0].users.length; i++) {
        html += "<option value=\"";
        html += list[0].users[i].id + "\">";
        html += list[0].users[i].login + "</option>";
    }
    $("#selectUser").empty().append(html);
        $('#selectList option[value="' + 0 + '"]').prop('selected', true);
}*/


let dataForDatatable;

function fill(list) {
    let objects = $.parseJSON(list);
    dataForDatatable = $.parseJSON("[" + list + "]");
    allWorks = dataForDatatable[0].allWorks;
    $("#name").val(objects.patient.name);
    $("#address").val(objects.patient.address);
    $("#telephone").val(objects.patient.telephone);
    $("#date_of_birth").val(objects.patient.date_of_birth);
    $("#gender").val(objects.patient.gender);
    $("#blood_group").val(objects.patient.blood_group);
    $('#surgeriesList').dataTable({
        scrollX: false,
        scrollY: 200,
        scrollCollapse: true,
        scroller: true,
        searching: false,
        paging: false,
        info: false,
        "aaData": dataForDatatable[0].surgeries,
        "language": {
            "emptyTable": "В таблице отсутствуют данные",},
        "aoColumns": [
            {title: "Дата", "mDataProp": "date", "width": "80px"},
            {title: "Описание", "mDataProp": "type"},
        ],
        'columnDefs': [
            {
                "targets": 0, // your case first column
                "className": "text-center"
            },
            {
                "targets": 1,
                "className": "text-center",
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            $(nRow).click(function () {
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
            {title: "Дата", "mDataProp": "date", "width": "80px"},
            {title: "Жалобы", "mDataProp": "patientComplaint", "width": "100px"},
            {title: "Диагноз", "mDataProp": "diagnosis", "width": "100px"},
            {title: "Лечение", "mDataProp": "treatment"},
        ],
        "language": {
            "emptyTable": "В таблице отсутствуют данные",},
        'columnDefs': [
            {
                "targets": 0, // your case first column
                "className": "text-center"
            },
            {
                "targets": 1,
                "className": "text-center",
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            $(nRow).click(function () {
                window.location.href = "/rest/visit?id=" + aData.id + "&patientId=" + id;
            });
            return nRow;
        }
    });
    if(!noPatient){fillWorkPlaces(dataForDatatable[0].patient.workPlaces);}

}

function fillWorkPlaces(data) {
    $('#workPlace').dataTable({
        scrollX: false,
        scrollY: 35,
        scrollCollapse: true,
        scroller: true,
        searching: false,
        paging: false,
        info: false,
        "ordering": false,
        "aaData": data,
        "language": {
            "emptyTable": "В таблице отсутствуют данные",},
        "aoColumns": [
            {title: "Место работы", "mDataProp": "name", width: '200px'},
            {
                title: "Редактировать",
                "render": renderEditBtn1,
                "defaultContent": "",
                "orderable": false,
            },
            {
                title: "Удалить",
                "render": renderDeleteBtn1,
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
            $(nRow).click(function () {
                renderEditBtn1(aData, "display", aData);
                renderDeleteBtn1(aData, "display", aData);
            });
            return nRow;
        }
    });
    for (let i = 0; i < data.length; i++) {
        patientWorkPlaces.push(data[i].id);
    }
    ;
}

let nRow;
let aData;
let patientWorkPlaces = new Array;
let userList = new Array;

function renderEditBtn1(data, type, row) {
    if (type === "display") {
        aData = data;
        nRow = row;
        return "<a href='javascript:editWork(" + null + ")'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtn1(data, type, row) {
    if (type === "display") {
        aData = data;
        nRow = row;
        return "<a href='javascript:deleteEntity1();'><span class='fa fa-remove'></span></a>";
    }
}

function editWork(isAddClicked) {
    $('#workModal').modal({
        keyboard: true
    });
    if (isAddClicked != null) {
        fillSelectList(true);
    }
    else {
        fillSelectList(false);
    }
}

let allWorks;

function fillSelectList(isAddClicked) {
    let data = allWorks;
    let html = "";
    if (isAddClicked) {
        for (let i = 0; i < data.length; i++) {
            if (patientWorkPlaces.includes(data[i].id)) {
            }
            else {
                html += "<option value=\"";
                html += data[i].id + "\">";
                html += data[i].name + "</option>";
            }
        }
    }
    else {
        for (let i = 0; i < data.length; i++) {
            html += "<option value=\"";
            html += data[i].id + "\">";
            html += data[i].name + "</option>";
        }
    }
    $("#selectList").empty().append(html);
    if (aData != null) {
        $('#selectList option[value="' + aData.id + '"]').prop('selected', true);
    }
}


function checkAndSaveWorkPlace() {
    let tempURL = ajaxUrl;
    ajaxUrl = '/rest/patient/workplaces';
    let tempForm = form;
    let tempParentId = parentId;
    if (aData == null) {
        parentId += "&previousWorkPlaceId=";
        form = createForm($('#selectList').val(), $('#selectList option:selected').text());
    }
    else {
        parentId += "&previousWorkPlaceId=" + aData.id;
        form = createForm($('#selectList').val(), $('#selectList option:selected').text());
    }
    save();
    ajaxUrl = tempURL;
    form = tempForm;
    parentId = tempParentId;
}

function createForm(id, name) {
    form = $("<form></form>");
    form.append(
        $("<input>",
            {
                type: 'hidden',
                id: 'id',
                name: 'id',
                value: id,
            }
        )
    );
    form.append(
        $("<input>",
            {
                type: 'hidden',
                id: 'name',
                name: 'name',
                value: name,
            }
        )
    );
    return form;
}

function updateTable(data) {
    let datatable = new $.fn.dataTable.Api($("#workPlace"));
    datatable.clear();
    datatable.rows.add(data);
    datatable.draw();
    patientWorkPlaces = [];
    for (let i = 0; i < data.length; i++) {
        patientWorkPlaces.push(data[i].id);
    }
}


function deleteEntity1() {
    //  console.log("delete");
    let n = new Noty({
        text: '<div id="noty_bar_2b6f61e6-2e4b-4354-b33d-6a93b7798606" ' +
            'class="noty_bar noty_type__error noty_theme__mint noty_has_progressbar">' +
            '<div class="noty_body">Уверены, что хотите удалить запись?</div>' +
            '<div class="noty_progressbar"></div></div>',
        modal: true,
        layout: "center",
        theme: 'mint',
        buttons: [
            Noty.button('YES', 'btn btn-success', function () {
                let tempId = id;
                let tempAjaxURL = ajaxUrl;
                ajaxUrl = '/rest/patient/workPlace/delete';
                id = aData.id;
                $.ajax({
                    url: ajaxUrl + '/' + id + "/" + parentId,
                    type: 'DELETE',
                    statusCode: {
                        403: function () {
                            failNoty('недостаточно прав');
                            console.log("error")
                        },
                        200: function (data) {
                        }
                        //other codes. read the docs for more details
                    }
                }).done(function (data) {
                        //   console.log("done");
                        ajaxUrl = tempAjaxURL;
                        id = tempId;
                        n.close();
                        updateTable(data);
                        successNoty("common.deleted");
                    }
                );
            }),
            Noty.button('NO', 'btn btn-error', function () {
                n.close();
            })
        ]
    });
    n.show();
}

function checkAndSavePatient() {
    // console.log($('#detailsForm').serialize())
    savePatient();
    return false;
}

function savePatient() {
    let link;
        link = "/rest/patient/update/" + id;
 /*   let serializedData = $('#detailsForm').serialize();
    serializedData = serializedData.replace(/&?[^=]+=&|&[^=]+=$/g,'');
    console.log($('#detailsForm').serialize());*/
    $.ajax({
        url: link,
        type: 'POST',
        data: $('#detailsForm').serialize(),
        statusCode: {
            403: function () {
                failNoty('недостаточно прав');
                //   console.log("error")
            },
            200: function (data) {
            },
            400: function (data) {
                failNoty(data.responseText);
            },
            500: function (data) {
                failNoty(data.responseText);
            },
            //other codes. read the docs for more details
        }
    }).done(function (data) {
        successNoty('common.saved')
        }
    );
}


