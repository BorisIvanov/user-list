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
    var birthday = getVal("#find-birthday input");
    if (birthday) {
        if (!birthdayValidate(birthday)) {
            return false;
        }
    }

    var data = {
        login: getVal("#find-login"),
        name: getVal("#find-name"),
        birthday: birthday,
        sex: getVal("#find-sex"),
        country: getVal("#find-country")
    };
    $.postJSON(res.url.user.find, data, function (response) {
        $("#find-table tbody tr").remove();
        var result = "";
        for (var i = 0; i < response.length; i++) {
            var item = response[i];

            result += "<tr><td>";
            result += item.login + "</td>";
            if (item.name) {
                result += "<td>" + item.name + "</td>";
            } else {
                result += "<td></td>";
            }
            if (item.birthday) {
                result += "<td>" + item.birthday + "</td>";
            } else {
                result += "<td></td>";
            }
            if (item.sexAsString) {
                if (item.sexAsString == "0") {
                    result += "<td>" + res.label.sex.M + "</td>";
                } else {
                    result += "<td>" + res.label.sex.F + "</td>";
                }
            } else {
                result += "<td></td>";
            }
            if (item.country) {
                result += "<td>" + item.country + "</td>";
            } else {
                result += "<td></td>";
            }
            result += "</tr>";
        }
        $("#find-table tbody").append(result);
    });
}

function getVal(selector) {
    var result = $(selector).val();
    if (result == "") {
        result = null;
    }
    return result;
}

function dateValidate(value) {
    var regEx = /^\d{4}-\d{2}-\d{2}$/;
    if (!value.match(regEx)) {
        return false;
    }
    var params = value.split(/[\.\-\/]/);
    var yyyy = parseInt(params[0], 10);
    var mm = parseInt(params[1], 10);
    var dd = parseInt(params[2], 10);
    var date = new Date(yyyy, mm - 1, dd, 0, 0, 0, 0);
    return mm === (date.getMonth() + 1) && dd === date.getDate() && yyyy === date.getFullYear();
}

function birthdayValidate(value) {
    if (dateValidate(value)) {
        $("#find-birthday").removeClass("has-error");
        return true;
    }
    $("#find-birthday").addClass("has-error");
    return false;
}