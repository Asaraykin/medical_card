let id = $("#id").val();
let userId = $("#patientId").val();
const ajaxUrl = '/rest/surgery';
let form = $('#detailsForm');


/*function updateTable() {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "/" + id + "/" + userId,
        'dataType': 'json'
    }).done(function (data) {
        $("#type").val(data.type);
        $("#date").val(data.date);
        console.log("saved");
    });
}*/

function updateTable(data) {
        $("#type").val(data.type);
        $("#date").val(data.date);
        $("#id").val(data.id);
        console.log(form.serialize());
}


function back() {
    window.location.href = "/rest/patient?id=" + userId;
}


function deleteSurgery() {
    var n = new Noty({
        text: '<div id="noty_bar_2b6f61e6-2e4b-4354-b33d-6a93b7798606" ' +
            'class="noty_bar noty_type__error noty_theme__mint noty_has_progressbar">' +
            '<div class="noty_body">Уверены, что хотите удалить запись?</div>' +
            '<div class="noty_progressbar"></div></div>',
        modal: true,
        layout: "center",
        theme: 'mint',
        buttons: [
            Noty.button('YES', 'btn btn-success', function () {
                console.log('button 1 clicked');
                $.ajax({
                    url: ajaxUrl + '/' + id + "/" + userId,
                    type: 'DELETE',
                    statusCode: {
                        403: function() {
                            failNoty('недостаточно прав');
                            console.log("error")
                        },
                        200: function(data) {

                        }
                        //other codes. read the docs for more details
                    }
                }).done(function () {
                    console.log("done")
                        back()
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

