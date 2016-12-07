function OrderTableController(){
    var self = this,
        orderTableSelector = '#orderTable',
        orderRowSelector = '.order-row',
        $orderTable,
        dateFormatWithOffset  = 'DD-MM-YYYY HH:mm:ss Z',
        dateFormat  = 'DD-MM-YYYY HH:mm:ss',
        orderRowTemplateStr = '<tr data-id="<%= id %>" class="order-row"><td><%= id %></td><td><%= creationTime %></td><td><%= executionTime %></td><td><%= inputString %></td><td><%= outputString %></td><td><%= status %></td></tr>';

    function init(){
        $(document).on('order:success', loadOrderList);
        loadOrderList();
        checkOrders();
        setInterval(checkOrders,3000);
    }

    function renderOrderTable(data){
        var compiledTemplate = _.template(orderRowTemplateStr),
            $orderTable = $(orderTableSelector),
            rowsStr = '';

        $orderTable.find(orderRowSelector).remove();

        for (var i = 0; i < data.length; i++){
            data[i].index = i+1;
            if(!data[i].outputString){
                data[i].outputString = '';
            }

            data[i].creationTime = moment(data[i].creationTime, dateFormatWithOffset).format(dateFormat);
            data[i].executionTime = moment(data[i].executionTime, dateFormatWithOffset).format(dateFormat);
            rowsStr += compiledTemplate(data[i]);
        }
        $orderTable.append(rowsStr);
    }

    function loadOrderList(){
        $(document).trigger('uiBlocker:block');
        $.ajax({
            url: testApp.config.restApiBaseUrl + 'api/tasks/history',
            method: 'GET',
            success: function(result){
                renderOrderTable(result);
            },
            error: function(){
                $(document).trigger('notification:show',[[{msg: 'Произошла ошибка загрузки данных',type:'ERROR'}]]);
            }
        }).always(function () {
            $(document).trigger('uiBlocker:unblock');
        })
    }

    function checkOrders(){
        $.ajax({
            url:testApp.config.restApiBaseUrl + 'api/tasks/status',
            type:'GET',
            cahce:false,
            success:function(result){
                if(result && result.length){
                    for(var i = 0; i< result.length; i++){
                        loadOrderList();
                        if(result[i].status == 'done'){
                            $(document).trigger('notification:show',[[{msg: 'Заказ ID'+result[i].id+' обработан успешно',type:'SUCCESS'}]]);
                        }else if(result[i].status == 'error'){
                            $(document).trigger('notification:show',[[{msg: 'При обработке Заказа ID'+result[i].id+' произошла ошибка',type:'ERROR'}]]);
                        }
                    }
                }
            },
            error:function( jqXHR, status){
                console.log(status);
            }
        });
    }

    self.init = init;
}