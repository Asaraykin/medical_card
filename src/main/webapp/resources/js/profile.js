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

function createUser() {
    if(checkLogin() && checkPasswords()){
        let form = $("<form></form>");
        form.append(
            $("<input>",
                {
                    type: 'hidden',
                    id: 'login',
                    name: 'login',
                    value: $("#login").val(),
                }
            )
        );
        form.append(
            $("<input>",
                {
                    type: 'hidden',
                    id: 'password',
                    name: 'password',
                    value: $("#passwordNew2").val(),
                }
            )
        );
        $.ajax({
            url: '/rest/profile/create',
            type: 'POST',
            data: form.serialize(),
            statusCode: {
                500: function () {
                    failNoty("Пользователь уже создан")
                },
                //other codes. read the docs for more details
            }
        }).done(function () {
            successNoty('common.saved')
        })
        }
        else {
           failNoty("Исправьте ошибки")
        }
}

function createUserWithRole() {
    if(checkLogin() && checkPasswords()){
        let form = $("<form></form>");
        form.append(
            $("<input>",
                {
                    type: 'hidden',
                    id: 'login',
                    name: 'login',
                    value: $("#login").val(),
                }
            )
        );
        form.append(
            $("<input>",
                {
                    type: 'hidden',
                    id: 'password',
                    name: 'password',
                    value: $("#passwordNew2").val(),
                }
            )
        );
        form.append(
            $("<input>",
                {
                    type: 'hidden',
                    id: 'role',
                    name: 'role',
                    value: $("#role option:selected").text(),
                }
            )
        );
        $.ajax({
            url: '/rest/profile/create',
            type: 'POST',
            data: form.serialize(),
            statusCode: {
                500: function () {
                    failNoty("Пользователь уже создан")
                },
                //other codes. read the docs for more details
            }
        }).done(function () {
            successNoty('common.saved')
        })
    }
    else {
        failNoty("Исправьте ошибки")
    }
}


$("#login").keyup(function(eventObject) {
    checkLogin(eventObject);
});



let login = "";
let allLogins = new Array;
function checkLogin(){
    login = $("#login").val();
    if(login.length < 5){
        $('#invalidLogin small').text("Логин должен быть больше 5 символов");
    }
    else {
        for (let i = 0; i < allLogins.length; i++){
            if(allLogins[i] == login){
                $('#invalidLogin small').text("Это имя занято");
                return false;
            }
            else {
                $('#invalidLogin small').text("");
            }
        }
        return true;
    }
}

$("#passwordNew2").keyup(function(eventObject) {
    checkPasswords(eventObject);
});

$("#passwordNew1").keyup(function(eventObject) {
    checkPasswords(eventObject);
});

let passwordNew1;
let passwordNew2;
function checkPasswords(){
     passwordNew1  = $("#passwordNew1").val();
     passwordNew2 = $("#passwordNew2").val();
     if (passwordNew1.length < 5){
         $('#invalidPasswords1 small').text("Пароли должен быть длинее 5 символов");
         return false;
     }
     else {
         $('#invalidPasswords1 small').text("");
     }
    if (passwordNew1 != passwordNew2) {
        $('#invalidPasswords2 small').text("Пароли не совпадают");
        return false;
    }
    else {
        $('#invalidPasswords2 small').text("");
        return true;
    }
}

$(document).ready(function () {
    console.log("dsfs");
    $.ajax({
        url: '/rest/profile/getAllUsers',
        type: 'GET',
       dataType: 'json'
    }).done(function (data) {
        for (let i = 0; i < data.length; i++){
            allLogins.push(data[i]);
        }
    });
});