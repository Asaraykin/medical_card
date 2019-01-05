
//field validation
(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        let forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated')
            }, false)
        })
    }, false)
}());


function checkAndSave() {
    save();
    return false;
}

function save() {
      $.ajax({
        url: ajaxUrl + '/update' + "?" + parentURL + "Id=" + parentId,
        type: 'POST',
        data: form.serialize(),
        statusCode: {
            403: function () {
                failNoty('недостаточно прав');
                console.log("error")
            },
            200: function (data) {
            },
            500: function (data) {
                if (data.responseText.indexOf('повторяющееся значение ключа нарушает ограничение уникальности') > 0) {
                    failNoty('У этого направления уже существует запись результатов');
                }
            },
            //other codes. read the docs for more details
        }
    }).done(function (data) {
            updateTable(data),
                successNoty('common.saved')
        }
    );
}


function deleteEntity() {
    let n = new Noty({
        text: '<div id="noty_bar_2b6f61e6-2e4b-4354-b33d-6a93b7798606" ' +
            'class="noty_bar noty_type__error noty_theme__mint noty_has_progressbar">' +
            '<div class="noty_body">Уверены, что хотите удалить запись?</div>' +
            '<div class="noty_progressbar"></div></div>',
        modal: true,
        layout: "center",
        theme: 'mint',
        buttons: [
            Noty.button('Да', 'btn btn-success', function () {
                console.log('button 1 clicked');
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
                }).done(function () {
                        console.log("done");
                        back()
                    }
                );
            }),
            Noty.button('Нет', 'btn btn-error', function () {
                n.close();
            })
        ]
    });
    n.show();
}


let failedNote;

function failNoty(errorInfo) {
    closeNoty();
    // https://stackoverflow.com/questions/48229776

    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + errorInfo,
        type: "error",
        layout: "bottomRight",
        timeout: 1000
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
        text: "<span class='fa fa-lg fa-check'></span> " + i18n[key],
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a href='' ;'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a href='javascript:deleteUser("+ id +")'><span class='fa fa-remove'></span></a>";
    }
}

function back() {
    parent.history.back();
    return false;
}

