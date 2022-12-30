$(function() {

    // handle click to table row
    $('.clickable-row').on('click', event => {
        let $row = $(event.currentTarget);
        let id = $row.data('href');
        let url =
            $(location).attr('protocol') + '//' +
            $(location).attr('host') +
            $(location).attr('pathname') + '/' + id;

        $(location).prop('href', url);
    });

    // handle change radio button (type)
    $formType = $('.form-type').first();
    $('.check-type').change(event => {
        if($(event.currentTarget).is(':checked')) $formType.submit();
    });

    $('.clickable-row').on('click', event => {
        let $row = $(event.currentTarget);
        let id = $row.data('href');
        let url =
            $(location).attr('protocol') + '//' +
            $(location).attr('host') +
            $(location).attr('pathname') + '/' + id;

        $(location).prop('href', url);

    });

});