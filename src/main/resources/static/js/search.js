$(document).ready(
    function () {
        $('#searchInfor input').bind('keypress', function (e) {
            if (e.keyCode == 13) {
                $('#searchForm').submit();
            }
        });
    }
)
