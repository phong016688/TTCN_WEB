$(document).ready(function () {
    $('.rating-star').on({
            mouseenter: function f() {
                handleMouseEnter($(this).attr('id'));
            },
            mouseleave: function () {
                handleMouseOut($(this).attr('id'));
            },
            click: function () {
                handleClick($(this).attr('id'));
            }
        }
    )
})

var mainRating = 5;

function slice(string) {
    return string.slice(5, 6);
}

function handleMouseEnter(id) {
    var  rating = slice(id);
    setCheckedStar(rating);
}

function handleMouseOut(id) {
    setCheckedStar(mainRating);
}

function handleClick(id) {
    var rating = slice(id);
    mainRating = rating;
    setCheckedStar(mainRating);
}

$('#button-save-rating').click(
    function postRating() {
        $.ajax({
            type: 'POST',
            url: '/api/rating/' + getProductId(),
            data: {
                rating: mainRating
            }
        }).done(function (data) {
            if (data.message) {
                changeRatingButtonInDetailPage();
            }
        }).fail(function (data) {
            if (data.responseJSON.message) {
                hideModal();
                showMessage("Please login to rating!");
                openLogin();
            }
        })
    })

$('#rate-button').click(
    function getUserRate() {
        $.ajax({
            type: 'GET',
            url: '/api/rating/' + getProductId()
        }).done(function (data) {
            if (data.rating) {
                mainRating = data.rating;
                setCheckedStar(data.rating);
            }
        }).fail(function (data) {
            if (data.responseJSON.message) {
                hideModal();
                showMessage("Please login to rating!");
                openLogin();
            }
        })
    }
)

function changeRatingButtonInDetailPage() {
    $.ajax({
        url: '/api/rating/update/' + getProductId(),
        type: 'get',
    }).done(function (data) {
        $('#div-rate-1').addClass('d-none');
        $('#div-rate-2').removeClass(('d-none'));
        setCheckedRate(data.rating);
    }).fail(function (data) {

    })
}

function setCheckedStar(rating) {
    for (i = 1; i <= 5; i++) {
        var button = $("#star-" + i);
        if (i <= rating) {
            button.addClass("yellow");
        } else button.removeClass("yellow");
    }
}

function setCheckedRate(rating) {
    $('#div-rate-2').removeClass('d-none')
    for (i = 1; i <= 5; i++) {
        var button = $("#rate-" + i);
        if (i <= rating) {
            button.addClass("yellow");
        } else button.removeClass("yellow");
    }
}

function getProductId() {
    return $('#activityId').val();
}

function hideModal() {
    $('#rateModal').hide();
    $('.modal-backdrop').hide();
}

function showModal() {
    $('#rateModal').show();
}
