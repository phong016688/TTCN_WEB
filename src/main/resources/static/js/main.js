$(document).ready(function () {
    changeLogo();
});

$(window).resize(function () {
    changeLogo();
})

function changeLogo() {
    if (getWidthWindow()) {
        $('.logo-image').attr('src', '/img/logo-mobile.png');
    } else {
        $('.logo-image').attr('src', '/img/logo.png');
    }
}

function getWidthWindow() {
    return $(window).width();
}

$('#create-account').click(function () {
    if (checkAllFieldsHaveData()) {
        createAccount();
    } else {
        $('.check-field').css('display', 'block');
    }
})

$('#firstname').focus(function () {
    hiddenMessageError();
})

$('#lastname').focus(function () {
    hiddenMessageError();
})

$('#email').focus(function () {
    hiddenMessageError();
})
$('#password').focus(function () {
    hiddenMessageError();
})

function checkAllFieldsHaveData() {
    if ($('#firstname').val() == '') return false;
    if ($('#lastname').val() == '') return false;
    if ($('#email').val() == '') return false;
    if ($('#password').val() == '') return false;
    return true;
}

function hiddenMessageError() {
    $('.create-error').css("display", "none");
    $('.check-field').css("display", "none")
}

function createAccount() {
    $.ajax({
        type: 'GET',
        url: '/api/createaccount',
        data: {
            firstname: $('#firstname').val(),
            lastname: $('#lastname').val(),
            email: $('#email').val(),
            password: $('#password').val(),
        }
    }).done(function (data) {
        if (data.message) {
            $('#mySidenav').css('width', '100%');
            $('#mySidenav1').css('width', '0');
            showMessage(data.message);
        }
    }).fail(function (data) {
        if (data.responseJSON.message) {
            $('.create-error').css("display", "block");
        }
    })
}


function openNav() {
    $('#mySidenav2').css('width', '100%');
}

function openLogin() {
    $('#mySidenav1').css('width', '0');
    $('#mySidenav2').css('width', '0');
    $('#mySidenav').css('width', '100%');
}

function openCreateAccount() {
    hiddenMessageError();
    $('#mySidenav2').css('width', '0');
    $('#mySidenav').css('width', '0');
    $('#mySidenav1').css('width', '100%');
}

function closeNav() {
    $('#mySidenav').css('width', '0');
    $('#mySidenav1').css('width', '0');
    $('#mySidenav2').css('width', '0');
}

function showMessage(message) {
    $('#main-message').css("width", '300px');
    $('.main-message').text(message);
    setTimeout(function () {
        $('#main-message').css("width", '0');
    }, 4000);
}
