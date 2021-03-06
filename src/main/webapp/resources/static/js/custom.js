/**
 * Created by marko on 09.05.17.
 */

$(document).ready(function () {

    $(document).ready(function () {
        var url = window.location;
        // Will only work if string in href matches with location
        $('ul.nav a[href="' + url + '"]').parent().addClass('active');

        // Will also work for relative and absolute hrefs
        $('ul.nav a').not('#homeLink').filter(function () {
            return this.href == url;
        }).parent().addClass('active').parent().parent().addClass('active');
    });

    $(document).ready(function () {
        $("#statisticsForm").on("hide.bs.collapse", function () {
            $(".btn-success").html('<span class="glyphicon glyphicon-collapse-down"></span> Show');
        });
        $("#statisticsForm").on("show.bs.collapse", function () {
            $(".btn-success").html('<span class="glyphicon glyphicon-collapse-up"></span> Hide');
        });
    });

    $('#available').bootstrapToggle('on');


    $(function () {
        function is_touch_device() {
            return 'ontouchstart' in window        // works on most browsers
                || navigator.maxTouchPoints;       // works on IE10/11 and Surface
        };

        if (!is_touch_device() && $('.navbar-toggle:hidden')) {
            $('.dropdown-menu', this).css('margin-top', 0);
            $(".dropdown").not('#messagesBox').not('#profileBox').hover(function () {
                $('.dropdown-toggle', this).trigger('click').toggleClass("disabled");
            });
        }
    });

    $("label").click(function () {
        $(this).css({"background-color": "#white"});
        $(this).nextAll().css({"background-color": "#white"});
    });

    $(':radio').change(function () {
        console.log('New star rating: ' + this.value);
    });

    $('#profileModal').on('show.bs.modal', function (e) {

        var userFullName = $(e.relatedTarget).data('fullname-id');
        var userUserName = $(e.relatedTarget).data('username-id');
        var userEmail = $(e.relatedTarget).data('email-id');
        var userPhoneNumber = $(e.relatedTarget).data('phonenumber-id');

        $('#modalUserFullName').text(userFullName);
        $('#modalUserName').text(userUserName);
        $('#modalUserEmail').text(userEmail);
        $('#modalUserPhoneNumber').text(userPhoneNumber);

        $('#emailButton').attr("href", "mailto:" + userEmail);
        $('#phoneButton').attr("href", "tel:" + userPhoneNumber);
        $('#modalMessageButton').attr("href", "/message/new/" + userUserName);

    });

    $('#rateHomeModal').on('show.bs.modal', function (e) {
        var homeName = $(e.relatedTarget).data('homename-id');
        var href = $(e.relatedTarget).data('href-id');

        $('#rateHomeForm').attr("action", href);
        $('#homeName').text(homeName);


    });

    $('#deleteHomeModal').on('show.bs.modal', function (e) {

        //get data-id attribute of the clicked element
        var homeId = $(e.relatedTarget).data('home-id');

        //populate the textbox
        $(e.currentTarget).find('a[id="deleteHomeButton"]').attr("href", "/home/delete/" + homeId);
    });

    $(".deleteImageButton[disabled!=disabled]").on('click', function () {
        var imageName = $(this).data('image-name');
        var imageDeleteUrl = $(this).attr('link-template');
        $('#deleteImage').attr('href', imageDeleteUrl);
        $('#image-name').text(imageName);
        $('#deleteModal').modal('show');
    });
});

$(function () {
    $('.homeImage').on('click', function () {
        $('.enlargeImageModalSource').attr('src', $(this).attr('src'));
        $('.enlargeImageModalTitle').text($(this).attr('name'));
        $('#enlargeImageModal').modal('show');
    });

    $('#deleteMessageModal').on('show.bs.modal', function (e) {

        //get data-id attribute of the clicked element
        var messageId = $(e.relatedTarget).data('message-id');

        var href = "/message/delete/" + messageId;

        //set the element href
        $(e.currentTarget).find('a[id="deleteMessageButton"]').attr('href', href);

    });

});