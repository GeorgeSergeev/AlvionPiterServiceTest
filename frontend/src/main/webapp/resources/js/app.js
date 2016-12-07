var testApp;
$(function(){

    function TestApp(){
        var self = this,
            configFileUrl= 'resources/config/config.json',
            defaultConfig = {
                restApiBaseUrl : '/backend/'
            };

        function init() {
            $.ajax({
                url: configFileUrl,
                success:function (result) {
                    $.extend(defaultConfig,result);
                    runApp();
                },
                error: function () {
                    alert('Произошла ошибка при инициализации приложения');
                }
            });


        }
        function runApp(){
            var orderTableController = new OrderTableController(),
                orderFormController = new OrderFormController(),
                notificationController = new NotificationController(),
                uiBlockerController = new UiBlockerController();
            orderTableController.init();
            orderFormController.init();
        }

        self.init = init;
        self.config = defaultConfig;
    }
    testApp = new TestApp();
    testApp.init();
});