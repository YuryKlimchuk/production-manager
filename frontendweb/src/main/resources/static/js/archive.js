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
    $formType = $('.form-filter-archive').first();
    $('.check-type').change(event => {
        if($(event.currentTarget).is(':checked')) $formType.submit();
    });

    // form submit after text field change
    $('.text-input-filter').change(event => {
        $formType.submit();
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

    $('#edit-dialog').dialog();

    //
    $btnEditNumber = $('button[name="btnEditNumber"]').first().click(() => {
        console.log('btnEditNumber');

    });

    // handle button create new
    $('button[name="btnCreateNew"]').first().click(() => {
        let url =
            $(location).attr('protocol') + '//' +
            $(location).attr('host') +
            $(location).attr('pathname') + '/create-new';

        $(location).prop('href', url);
    });

    function getStatusInput(type) {
        let $divs = $('div[name="set-status"]');
        $divs.each((index, div) => {
            $(div).hide();
        });
        $('#set-status-' + type).show();
    };

    // form to create new part
    $createNewPartFrom = $('#create-new-part-form');
    $selectType = $createNewPartFrom.find('.select-type').first();
    getStatusInput($selectType.val());

    $selectType.on('change', function() {
      getStatusInput($selectType.val());
    });



});


