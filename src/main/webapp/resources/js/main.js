$(document).ready(function () {
    $("#find-button").on("click", find);
});

function getHeaders() {
    var name = $("meta[name=\"_csrf_header\"]").attr("content");
    var val = $("meta[name=\"_csrf\"]").attr("content");
    var result = {
        "Content-Type": "application/json"
    };
    result[name] = val;
    return result;
}

$.postJSON = function (url, data, callback) {
    return jQuery.ajax({
        headers: getHeaders(),
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "json",
        success: callback
    });
};

function find() {
    var data = {
        login: $("#find-login").val(),
        name: $("#find-name").val(),
        birthday: $("#find-birthday").val(),
        sex: $("#find-sex").val(),
        country: $("#find-country").val()
    };
    $.postJSON(res.url.user.find, data, function (response) {

    });
}