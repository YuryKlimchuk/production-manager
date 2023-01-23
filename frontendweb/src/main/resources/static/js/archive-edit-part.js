$(function() {

    console.log(updateValues);

    const editButtonsClass = 'btn-edit-field';
    const acceptButtonsClass = 'btn-accept-field';
    const updateValueClass = 'update-value';
    const updateInputClass = 'update-input';

    $('.' + editButtonsClass + ', .' + acceptButtonsClass).each(function() {
        $(this).click(function() {
            buttonClick(editButtonsClass, acceptButtonsClass, updateInputClass, updateValueClass, $(this));
        });
    });

    $('.btn-update').first().click(buttonUpdateClick);

});

function buttonClick(editClass, acceptClass, inputClass, valueClass, $button) {
    let $row = $button.closest('tr');
    let field = $row.data('field');
    let $btnAccept = $row.find('.' + acceptClass);
    let $btnEdit = $row.find('.' + editClass);
    let $input = $row.find('.' + inputClass);
    let $value = $row.find('.' + valueClass);

    if($button.hasClass(editClass)) {
        showInputField(updateValues, field, $btnEdit, $btnAccept, $input, $value);
    } else {
        saveUpdateValue(updateValues, field, $btnEdit, $btnAccept, $input, $value);
    }
}

function saveUpdateValue(updateValues, field, $btnEdit, $btnAccept, $input, $value) {
    $btnEdit.show();
    $btnAccept.hide();
    $input.hide();
    $value.show();
    updateValues[field] = $input.val();
    $value.text(updateValues[field]);
}

function showInputField(updateValues, field, $btnEdit, $btnAccept, $input, $value) {
    $btnEdit.hide();
    $btnAccept.show();
    $input.show();
    $value.hide();
}

function buttonUpdateClick() {
    console.log('UPDATE-BUTTON');

    console.log($(this));
    $form = $(this).closest('form');


    $.each(updateValues, function(field, value) {
        console.log($('tr[data-field="' + field + '"]').data('field'));
        $("<input />").attr("name", field).attr("value", value).appendTo($form);
    });
    $form.submit();

}

let updateValues = {
    'number': '',
    'name': '',
    'type': 'PISKA',
    'status': '',
    'created': '',
    'lastUpdate': '',
    'pdf': '',
    'otherFile': ''
}