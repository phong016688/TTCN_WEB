$(document).ready(
    function () {
        $('.image-detail').click(function () {
            changeMainImage($(this).attr('id'));
        })
    }
)

function changeMainImage(imageId) {
    $('.main-image-detail').attr('src', getChangeImageSrc(imageId));
}

function getChangeImageSrc(imageId) {
    return $("#" + imageId).attr('src');
}