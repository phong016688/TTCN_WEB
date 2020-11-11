$(document).ready(
    function () {
        $('#add-review-button').click(function () {
            postReview();
        })
    }
)

var listsize = $('#list-size').text();
var index = 0;

function postReview() {
    $.ajax({
        type: "POST",
        url: "/api/review/" + getProductId(),
        data: {
            content: $('#content').val(),
            listSize: listsize,
        }
    }).done(function (data) {
        if (data.size == 0) {

        } else {
            listsize = listsize / 1 + data.length + '';
            let i;
            for (i = data.length - 1; i >= 0; i--) {
                copyDiv(data[i])
            }
        }
        deleteReviewInput();
    }).fail(function (data) {
        if (data.responseJSON.message) {
            showMessage("Please login to review");
            openLogin();
        }
    });
}

function deleteReviewInput() {
    $('#content').val("");
    $('#content').focus();
}

function copyDiv(data) {
    console.log(index)
    $('#copy-review').clone()
        .attr('id', 'review-' + index).prependTo($('#place-to-copy'))
    $('#review-' + index + ' .user-name').text(data.firstname + ' ' + data.lastname);
    $('#review-' + index + ' .review-content').text(data.content);
    $('#review-' + index + ' .date-review').text(data.dateCreate);
    index++;
}