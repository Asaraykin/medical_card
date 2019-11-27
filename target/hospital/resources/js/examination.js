let parentURL = 'referral';
let id = $("#id").val();
let parentId = $("#parentId").val();
const ajaxUrl = '/rest/examination';
let form = $('#detailsForm');


function updateTable(data) {
    $("#date").val(data.date);
    $("#type").val(data.type);
    $("#id").val(data.id);
    $("#parentId").val();
    console.log(form.serialize());
}






