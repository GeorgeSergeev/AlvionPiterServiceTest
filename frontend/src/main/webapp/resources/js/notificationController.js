function NotificationController() {
    var self = this,
        notifications = {},
        notificationContainerSelector = '#notificationContainer',
        classes = {
            INFO: 'alert-info',
            WARN: 'alert-warning',
            SUCCESS: 'alert-success',
            ERROR: 'alert-danger'
        },
        defaults = {
            type: 'INFO',
            autohide: true
        },

        template = _.template(
            '<div class="alert <%= alertClass %> alert-dismissible js-alert" role="alert">' +
            /*  '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +*/
            '<%= message %>' +
            '</div>'
        );

    $(document).on('notification:show', showNotification);

    function showNotification(event, notifications) {
        for (var i = 0; i < notifications.length; i++) {
            renderNotification(notifications[i]);
        }
    }

    function renderNotification(notification) {
        var notificationObj = $.extend(defaults,notification),
            timer,
            notificationEl = $(template({
                alertClass: classes[notificationObj.type],
                message: notificationObj.msg
            }));
        $(notificationContainerSelector).append(notificationEl);

        timer = setTimeout(function () {
            hideNotification(timer);
            timer = null;
        }, 3000);
        notifications[timer] = notificationEl;
    }

    function hideNotification(timerId) {
        notifications[timerId].fadeOut(function () {
            $(this).remove();
            delete notifications[timerId];
        })
    }
}
