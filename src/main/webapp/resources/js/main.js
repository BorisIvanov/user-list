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
    var birthday = $("#find-birthday input").val();
    if (birthday == "") {
        birthday = null;
    } else {
        if (!birthdayValidate(birthday)) {
            return false;
        }
    }

    var data = {
        login: $("#find-login").val(),
        name: $("#find-name").val(),
        birthday: birthday,
        sex: $("#find-sex").val(),
        country: $("#find-country").val()
    };
    $.postJSON(res.url.user.find, data, function (response) {

    });
}

function dateValidate(value) {
    try {
        var date = value.split("-");
        var y = parseInt(date[0], 10),
            m = parseInt(date[1], 10),
            d = parseInt(date[2], 10);
        if (isNaN(y) || isNaN(m) || isNaN(d)) {
            return false;
        }
        new Date(y, m - 1, d);
        return true;
    } catch (e) {
        return false;
    }
}

function birthdayValidate(value) {
    if (dateValidate(value)) {
        $("#find-birthday").removeClass("has-error");
        return true;
    }
    $("#find-birthday").addClass("has-error");
    return false;
}