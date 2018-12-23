let id = $("#id").val();
let parentId = $("#parentId").val();
const ajaxUrl = '/rest/surgery';
let parentURL = 'patient';
let form = $('#detailsForm');



function updateTable(data) {
        $("#type").val(data.type);
        $("#date").val(data.date);
        $("#id").val(data.id);
        console.log(form.serialize());
}







