/**
 * Created by marko on 09.05.17.
 */
$(document).ready(function () {

    $('.contactOwnerButton').click(function () {

        var userFullName = $(this)[0].nextElementSibling.textContent;
        var userUserName = $(this)[0].nextElementSibling.nextElementSibling.textContent;
        var userEmail = $(this)[0].nextElementSibling.nextElementSibling.nextElementSibling.textContent;
        var userPhoneNumber = $(this)[0].nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.textContent;

        $('#modalUserFullName').text(userFullName);
        $('#modalUserName').text(userUserName);
        $('#modalUserEmail').text(userEmail);
        $('#modalUserPhoneNumber').text(userPhoneNumber);

        $('#emailButton').attr("href", "mailto:" + userEmail);
        $('#phoneButton').attr("href", "tel:" + userPhoneNumber);

    });

    $('#deleteHomeModal').on('show.bs.modal', function(e) {

        //get data-id attribute of the clicked element
        var homeId = $(e.relatedTarget).data('home-id');

        //populate the textbox
        $(e.currentTarget).find('a[id="deleteHomeButton"]').attr("href", "/home/delete/" + homeId);
    });
});