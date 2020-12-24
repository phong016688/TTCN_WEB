// Jquery objects
var $uploadImage = $('#upload-image');
var $image = $('#image');
var $imageError = $('#image-error');
var $imageOnDetailPage = $('#image-on-detail-page');
var $btnUpload = $('#btn-upload');
var $btnUpdate = $('#btn-update');
var $thumbnails = $('.thumbnails');

// Attributes
var srcAttr = 'src';
var imageIdAttr = 'image-id';
var orderImageAttr = 'order-image';

// default values
var attrDefault = {
    srcAttr: '/img/noimg.png',
    imageIdAttr: -1,
    orderImageAttr: -1
}

// className
var displayNoneClass = 'd-none';
var imageActiveClass = 'image-active';

$(document).ready(initPage);

function initPage() {
    $uploadImage.change(function () {
        readImage(this);
    });
    $('.icon-update').click(function () {
        iconUpdateClickHandler($(this).attr('id'));
    })
    $btnUpdate.on('click', btnUpdateClickHandler);
    $btnUpload.on("click", btnUploadClickHandler)
    $thumbnails.on('click', '.thumbnail-image', thumbnailImageClickHandler);

    $('.dropzone-wrapper').on('dragover', function (e) {
        e.preventDefault();
        e.stopPropagation();
        $(this).addClass('dragover');
    });

    $('.dropzone-wrapper').on('dragleave', function (e) {
        e.preventDefault();
        e.stopPropagation();
        $(this).removeClass('dragover');
    });


    $("form#upload-image-form").submit(function (event) {
            event.preventDefault();
            var url = "/api/image/product/" + $('#productId').attr("value"),
                method = "POST",
                formData = new FormData();
            console.log(getImageFile() + "file");
            formData.append("image_file", getImageFile());
            if (getImageId() != -1) {
                formData.append("image_id", getImageId());
            }
            $.ajax({
                type: method,
                url: url,
                data: formData,
                contentType: false,
                processData: false,
            }).done(function (result) {
                $("#exampleModalCenter").modal("hide");
                $('.modal-backdrop').hide();
                var imageUrl = getImageUrl(result.imageId, true);
                updateThumbnails(result.imageId, imageUrl);
            }).fail(function (error) {
                setMessage("Something went wrong! Please try again.", false);
            });
            return false;
        }
    );
}


function btnUploadClickHandler() {
    //updateAttribute($image);
    reset();
}

function iconUpdateClickHandler(id) {
    updateAttribute($image, "image" + id.substring(5));
    $image.attr("id", "newImage" + id.substring(5));
    reset();
}

function btnUpdateClickHandler() {
    updateAttribute($image, $imageOnDetailPage, null, false);
    reset();
}

function thumbnailImageClickHandler() {
    updateAttribute($imageOnDetailPage, $(this), null, false);
    $('.image-active').removeClass(imageActiveClass);
    $(this).addClass(imageActiveClass);
}

function reset() {
    $imageError.addClass("d-none");
    $uploadImage.val(null);
    $image.removeClass('animated-background');
    $('#image-name').html('');
    disableSaveImage();
}

function updateAttribute(targetElement, imageId) {
    targetElement.attr('src', getChangeImageSrc(imageId));
}

function getChangeImageSrc(id) {
    return $("#" + id).attr('src');
}

function isValidImageType(type) {
    return /image\/.*/g.test(type);
}

function readImage(input) {
    if (input.files && input.files[0]) {
        var type = input.files[0].type;
        if (isValidImageType(type)) {
            if (input.files[0].size > 10 * Math.pow(2, 20)) {
                $imageError.removeClass(displayNoneClass);
                $imageError.text("Your file size is over 10MB");
                $uploadImage.val(null);
            } else {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var wrapperZone = $(input).parent();
                    $image.addClass('animated-background');
                    $image.attr('src', e.target.result);
                    $('#image-name').html(input.files[0].name);
                    enableSaveImage();
                    wrapperZone.removeClass('dragover');

                };
                reader.readAsDataURL(input.files[0]);
                $imageError.addClass(displayNoneClass);
            }
        } else {
            $imageError.removeClass(displayNoneClass);
            $imageError.text("Your file is not an image file.");
            $('.dropzone-wrapper').removeClass('dragover');
        }
    }
}

function disableSaveImage() {
    $("#btn-submit").addClass('not-allow');
    $("#btn-submit").prop("disabled", true);
}

function enableSaveImage() {
    $("#btn-submit").removeClass('not-allow');
    $("#btn-submit").prop("disabled", false);
}

function getImageFile() {
    return $uploadImage.get(0).files[0];
}

function setMessage(msg, isSuccess) {
    $imageError.removeClass(displayNoneClass);
    $imageError.text(msg);
}

function getRequestImageURL() {
    return '/api/image/product/' + getProductId();
}

function getProductId() {
    alert($('#productId').attr("value"));
    return $('#productId').attr("value");
}

function getImageUrl(imageId) {
    return "/api/image/" + imageId;
}

function getImageId() {
    oldImageId = ($image.attr("id") == "image") ? 0 : $image.attr("id").substring(8);
    return oldImageId;
}

function updateThumbnails(imageId, imageUrl) {
    $("#image" + oldImageId).attr("src", imageUrl);
    $("#image" + oldImageId).attr("id", imageId);
    if (oldImageId == 0) {
        $("#btn-upload").attr("id", "icon_" + imageId);
    } else {
    }
}

/*
function addNewThumbnail({imageId}, imageUrl) {
    let orderImage = $thumbnails.children().length;
    let newThumbnail = renderThumbnail(orderImage, imageId, imageUrl);

    $btnUpload.before(newThumbnail);
    if (orderImage === 1) {
        updateAttribute($imageOnDetailPage, newThumbnail.children(), false);
        newThumbnail.children().addClass(imageActiveClass);
        $btnUpdate.removeClass(displayNoneClass);
    } else if (orderImage === +$('#max-number-upload-images').val()) {
        $btnUpload.remove();
    }
}
*/

/*
function renderThumbnail(orderImage, imageId, imageUrl) {
    return $($.parseHTML(
        < div
class
    = "thumbnail" >
        < img
class
    = "thumbnail-image"
    order - image =${orderImage}
        image - id =${imageId}
            src =${imageUrl}
\/>
    < label
class
    = "icon-update"
    title = "Update image."
    data - toggle = "modal"
    data - target = "#exampleModalCenter" >
        < i
class
    = "fa fa-camera p-md-2" > <\/i>
    <\/label>
    < /div>));
}
*/
