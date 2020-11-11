$(document).ready(
    function () {
        $('.custom-nav').click(function () {
            changeActiveNavTab($(this).attr('id'));
        })
        if (getWidthWindow() < 600) {
            $('#product-title').replaceWith("<h3>" + $('#product-title').text() + '</h3>');
        }
        $('.image-detail').width($('.main-image-detail').width() / 6);
        $('.image-detail').height($('.main-image-detail').width() / 6);
    }
)

function changeActiveNavTab(id) {
    if (id == 'item1') {
        addClassIfActive('item1');
        addClassIfNotActive('item2');
        addClassIfNotActive('item3');
    }
    if (id == 'item2') {
        addClassIfActive('item2');
        addClassIfNotActive('item1');
        addClassIfNotActive('item3')
    }
    if (id == 'item3') {
        addClassIfActive('item3');
        addClassIfNotActive('item2');
        addClassIfNotActive('item1')
    }
}

function addClassIfActive(id) {
    $("#" + id).addClass('nav-active')
    $("#" + id).removeClass('nav-not-active')
}

function addClassIfNotActive(id) {
    $("#" + id).removeClass('nav-active')
    $("#" + id).addClass('nav-not-active')
}