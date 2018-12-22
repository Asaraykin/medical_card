function save() {
console.log(id);
    $.ajax({
        url: ajaxUrl + '/update' + "?userId=" + userId,
        type: 'POST',
        data: form.serialize(),
        statusCode: {
            403: function() {
                failNoty('недостаточно прав');
                console.log("error")
            },
            200: function(data) {
            }
            //other codes. read the docs for more details
        }
    }).done(function(data) {
            updateTable(data),
                successNoty('common.saved')
        }
    );
};


let failedNote;
function failNoty(errorInfo) {
    closeNoty();
    // https://stackoverflow.com/questions/48229776

    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;"  + errorInfo,
        type: "error",
        layout: "bottomRight"
    }).show();
}

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + i18n[key],
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function renderShowBtn(data, type, row) {
if (type === "display"){
    return "<a onclick='showRow(" + row.id + ");'><span class='fa fa-list-alt'></span></a>";
}
}







