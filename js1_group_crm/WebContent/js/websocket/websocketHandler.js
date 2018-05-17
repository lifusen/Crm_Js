var WebsocketHandler = function(){
	return {
		onopen : function(event){
			
		},
		onmessage : function(event){
			var _this = this;
			var result = event.data;
			var message = JSON.parse(result);
			// 添加消息通知到头部
			NotificationUtil.addNotification(message);
			// 修改消息通知个数
			NotificationUtil.updateNotificationCount();
			// 显示桌面通知
			Notify.show({
				icon : Constant.NOTIFICATION_DEFAULT_ICON,
				title : '标题...',
				body : message.content,
				data : message,
				click : function (e) {
					console.log('callback click');
					console.log(e);
					console.log(e.target);
					var message = e.target.data;
					// 删除消息通知
					NotificationUtil.removeNotification(message.id);
					// 修改消息通知个数
					NotificationUtil.updateNotificationCount();
				}
			});
			Audio.playMp3(1);
			Audio.playBaiduYuyin(message.content);
			
			// 如果消息通知列表存在,则刷新
			$("#notificationSearchForm").submit();
		},
		onclose : function(event){
			
		},
		onbeforeunload : function(event){
			
		},
		onerror : function(event){
			
		},
		//发送消息
		send : function (){
		  var message = $('#text').val();
		  websocket.send(message);
		}
		
	}
}();