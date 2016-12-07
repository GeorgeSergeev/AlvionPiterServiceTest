function UiBlockerController(){
    var self = this,
        $uiBlockElement = $('#uiBlocker');

    $(document).on('uiBlocker:block',block);
    $(document).on('uiBlocker:unblock',unblock);

    function block(){
        $uiBlockElement.fadeIn();
    }
    function unblock(){
        $uiBlockElement.fadeOut();
    }
}