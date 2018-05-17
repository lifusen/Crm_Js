/**
 * Created by MrLiu on 2016/1/15.
 */
var Notify = function(){
    function isSupport(){

        if (Notification){
            return true;
        }else{
            //处理不支持的情况,弹窗提示
            return false;
        }
    }

    function notify(option) {
        if(!isSupport()){
            return false;
        }

        // request permission on page load
        document.addEventListener('DOMContentLoaded', function () {
            if (Notification.permission !== "granted")
                Notification.requestPermission();
        });
        if (!Notification) {
            alert('Desktop notifications not available in your browser. Try Chromium.');
            return;
        }

        if (Notification.permission !== "granted")
            Notification.requestPermission();
        else {
            var notification = new Notification(option.title, {
                icon: option.icon,
                body: option.body,
                data : option.data,
                tag : option.tag
            });
            console.log(notification);
            notification.onclick = function (e) {
                option.click(e);
                notification.close();
            };

            notification.onshow = function () {
                console.log('onshow');
            };

            notification.onclose = function (e) {
               //alert('onclose');
                console.log("close");
                /**
                 * close { target: Notification, isTrusted: true, currentTarget: Notification,
                 * eventPhase: 2, bubbles: false, cancelable: false, defaultPrevented: false,
                 * timeStamp: 1453108392010000, originalTarget: Notification,
                 * explicitOriginalTarget: Notification, NONE: 0 }
                 */
            };
        }

    }
    return {
        show : function(option){
            notify(option);
        }
    }
}();