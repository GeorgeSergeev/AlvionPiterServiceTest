function OrderFormController(){
    var self = this,
        datetimeInputSelector = '#datetimeInput',
        messageInputSelector = '#messageInput',
        orderSubmitSelector = '#orderSubmit',
        $datetimeInput,
        $messageInput,
        dateFormat  = 'DD-MM-YYYY HH:mm:ss Z',
        $orderSubmitButton;

    function init(){
        initializeControls();
    }

    function initializeControls(){
        $datetimeInput = $(datetimeInputSelector);
        $messageInput = $(messageInputSelector);
        $orderSubmitButton = $(orderSubmitSelector);
        $orderSubmitButton.on('click', onSubmitButtonClick);

        $datetimeInput.on('keydown change input',function () {
            setValidationState($(this),true)
        });
        $messageInput.on('keydown change input',function () {
            setValidationState($(this),true)
        });
        $datetimeInput.timepicker({
            minuteMax: 4,
            showHour: false,
            showSecond: true,
            timeFormat:'mm:ss',
            showButtonPanel: false
        }).inputmask({mask:'99:99'});
    }

    function processOrder(data){
        $(document).trigger('uiBlocker:block');
        $.ajax({
            url:testApp.config.restApiBaseUrl + 'api/tasks',
            data:   JSON.stringify(data) ,
            method: 'POST',
            contentType : 'application/json',
            success: function(result){
                $(document).trigger('order:success');
                clearInputs();
                $(document).trigger('notification:show',[[{msg: 'Заказ добавлен успешно',type:'SUCCESS'}]]);
            },
            error: function(result){
                $(document).trigger('order:error');
                $(document).trigger('notification:show',[[{msg: 'Произошла ошибка обработки данных',type:'ERROR'}]]);
            }
        }).always(function () {
            $(document).trigger('uiBlocker:unblock');
        });
    }

    function clearInputs(){
        $datetimeInput.val('');
        $messageInput.val('');
    }
    function validateInputData(time, message){
        var isValid = true;

        if(!message.length || message.length > 20){
            isValid = false;
            setValidationState($messageInput, isValid);
            $(document).trigger('notification:show',[[{msg: 'Поле "Сообщение" должно содержать от 1 до 20 символов',type:'ERROR'}]]);
        }
        if(!time.isValid()||time.minutes() <0 ||time.minutes()>4 || time.seconds() < 0||time.seconds()> 59 || $datetimeInput.inputmask('unmaskedvalue').length < 4){
            isValid = false;
            setValidationState($datetimeInput, isValid);
            $(document).trigger('notification:show',[[{msg: 'Поле "Время обработки заказа" должно содержать время от 0 до 5 минут',type:'ERROR'}]]);
        }
        return isValid;
    }

    function setValidationState($el,isValid){
        if(!isValid){
            if(!$el.parents('.form-group').hasClass('has-error')){
                $el.parents('.form-group').addClass('has-error');
            }
        }else{
            if($el.parents('.form-group').hasClass('has-error')){
                $el.parents('.form-group').removeClass('has-error');
            }
        }
    }

    function onSubmitButtonClick(){
        var selectedTime  = moment($datetimeInput.datetimepicker('getDate')),
            now = moment(),
            orderMessage = $messageInput.val(),
            isValid = validateInputData(selectedTime, orderMessage);

        if(isValid){
            now.add({minutes: selectedTime.minutes(), seconds: selectedTime.seconds()});
            var data = {
                string: orderMessage,
                startTime: now.format(dateFormat)
            };

            processOrder(data);
        }
    }

    self.init = init;
}