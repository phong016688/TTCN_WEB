$(window).ready(
    function () {
        $('#add-card-button').click(function () {
            handleClickAddCart();
        })
        $('#buy-now-button').click(function () {
            handleClickBuyNow();
        })

        $('.delete').click(function (event) {
            var id = event.target.id;
            handleClickDeleteProductCart(id);
        })
    }
)

var cartListSize = $('#cart-list-size').text();

function handleClickBuyNow() {
    handleClickAddCart();

}

function handleClickDeleteProductCart(item) {
    $.ajax({
        url: "/api/cart/delete/" + getProductId(),
        type: "GET",
    }).done(function (data) {
        cartListSize--;
        if (cartListSize == 0) {
            $('.shopping-cart').addClass('d-none')
        } else {
            handleDelete();
            $('#total-cart').text(data.totalCart);
        }
    }).fail(function (data) {
        showMessage("Some thing went wrong!")
    })

    function getProductId() {
        return item.substr(6);
    }

    function handleDelete() {
        $('#product' + getProductId()).addClass("d-none")
    }

}


function handleClickAddCart() {
    $.ajax({
        url: "/api/cart/" + getProductId(),
        type: "GET",
        data: {
        }
    }).done(function (data) {

    }).fail(function (data) {

    })
}

$('#button-next').click(function () {
    $('#contact-information').addClass('d-none');
    $('#recipient-information').removeClass('d-none')
})

$('#button-back').click(function () {
    $('#contact-information').removeClass('d-none');
    $('#recipient-information').addClass('d-none')
})











