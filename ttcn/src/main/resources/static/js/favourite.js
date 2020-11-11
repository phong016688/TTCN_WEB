$(document).ready(function () {
    getFavourite();
    $('#favourite-button').click(function () {
        updateFavourite();
    })
});

function getFavourite() {
    $.ajax(
        {
            url: '/api/favourite/' + getProductId(),
            type: 'GET',
        }
    ).done(function (data) {
        if (data.favourite) {
            setFavourite();
        }
    }).fail(function () {
    });
}

function updateFavourite() {
    $.ajax({
        url: '/api/favourite/' + getProductId(),
        type: 'POST',
        data: {
            isFavourite: isFavourite(),
        }
    }).done(function (data) {
        if (isFavourite()) {
            removeFavourite();
        } else {
            setFavourite();
        }
    }).fail(function (data) {
        openLogin();
        showMessage("Please login to favourite");
    })
}

function setFavourite() {
    $('#favourite-button').addClass('red');
}

function removeFavourite() {
    $('#favourite-button').removeClass('red');
}

function isFavourite() {
    return $('#favourite-button').hasClass('red');
}