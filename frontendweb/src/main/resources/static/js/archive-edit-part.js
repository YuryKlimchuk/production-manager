$(function() {

    // EDIT PAGE
    // edit number
    function editNumberShowHide() {
        let $editNumberLabel = $('#number-label');
        let $editNumberInput = $('#number-input');
        let $btnEditNumber = $('button[name="btnEditNumber"]').first();
        let $btnAcceptNumber = $('button[name="btnAcceptNumber"]').first();

        if($editNumberInput.is(':visible')) {
            $editNumberInput.hide();
            $editNumberLabel.show();
            $btnEditNumber.show();
            $btnAcceptNumber.hide();
        } else {
            $editNumberInput.show();
            $editNumberInput.val($editNumberLabel.text())
            $editNumberLabel.hide();
            $btnEditNumber.hide();
            $btnAcceptNumber.show();
        }


        // Checks CSS content for display:[none|block], ignores visibility:[true|false]
        //$(element).is(":visible");

        // The same works with hidden
        //$(element).is(":hidden");

    }

    $('button[name="btnEditNumber"]').first().click(() => {
        console.log('btnEditNumber');
        editNumberShowHide();
    });

    $('button[name="btnAcceptNumber"]').first().click(() => {
        console.log('btnAcceptNumber');
        editNumberShowHide();
    });




});


