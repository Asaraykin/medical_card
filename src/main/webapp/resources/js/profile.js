let userId = $('#id').val();
function changePassword() {
    console.log(userId);
    let oldPassword = $("#oldPassword").val();
    let passwordNew1 = $("#passwordNew1").val();
    let passwordNew2 = $("#passwordNew2").val();
    if (passwordNew1 != passwordNew2){
        failNoty("Пароли не совпадают");
    }
    else {
        $.ajax({
            url: '/rest/profile/' + userId + "/" + oldPassword + "/" + passwordNew1,
            type: 'POST',
            statusCode: {
                403: function () {
                    console.log("error")
                },
                202: function () {
                    console.log("saved");
                        successNoty('common.saved');
                },
                406: function () {
                    failNoty("Введен неверный старый пароль")
                    console.log("error");
                },
                //other codes. read the docs for more details
            }
        })
    }
}