var NotificationUtil = (function(){
	var $headerNotificationWrapper = $("#headerNotificationWrapper");				// 通知区域容器
	var $headerNotificationCount = $("#headerNotificationCount");					// 通知的显示个数
	var $headerNotificationUl = $("#headerNotificationUl");							// 通知区域的UL
	var $headerNotificationTotalTip = $("#headerNotificationTotalTip");				// 通知区域的提示
	var $headerNotificationLastLi = $("#headerNotificationLastLi");					// 通知区域中最后一个li
	var $showNotificationListViewBtn = $("#showNotificationListViewBtn");			// 显示所有的消息按钮
	var showCount = 2;
	return {
		init : function(){
			this.initShowNotificationListViewBtn();
			this.getAllUnreadNotification();
		},
		getAllUnreadNotification : function(){
			var _this = this;
			$.getJSON(window.rootPath + "/notification/getAllUnreadNotification.do",function(notifications){
				_this.addNotificationList(notifications);
			})
		},
		initShowNotificationListViewBtn : function(){
			$showNotificationListViewBtn.click(function(){
				// 加载消息管理列表视图
				aPost(window.rootPath + "/notification/getNotificationListView.do");
			});
		},
		addNotificationList : function(notifications){
			// 删除所有消息
			this.removeAllNotification();
			if(notifications && notifications.length > 0){
				for(var i = 0; i < notifications.length; i++){
					var notification = notifications[i];
					// 添加通知
					this.addNotification(notification);
				}
				// 修改通知显示信息
				this.updateNotificationCount();
			}
		},
		addNotification : function(notification){
			console.log("addNotification...");
			console.log(notification);
			if(!notification){
				return;
			}
			var count = this.getNotificationCount();
			var display = '';
			if(count > showCount){
				display = 'style="display:none;"';
			}
			var li = '<li id="notificationLi'+notification.id+'" class="notification" '+display+'>';
					li+='<a href="#">';
						li+='<span class="label label-success" style="padding-right:8px;"><i class="icon-plus"></i></span>';
							li+=notification.content;
						li+='<span class="time">2016-6-5 15:10</span>';
					li+='</a>';
					li+='</li>';
			// 添加通知消息到ul中
			$headerNotificationLastLi.before(li);
		},
		getNotificationCount : function(){
			return $headerNotificationUl.find("li.notification").length;
		},
		updateNotificationCount : function(){
			var count = this.getNotificationCount();
			if(count > 0){
				$headerNotificationCount.text(count);
				$headerNotificationTotalTip.text("您有"+count+"条未读消息");
			}else{
				$headerNotificationCount.text('');
				$headerNotificationTotalTip.text("您当前没有新消息!");
			}
		},
		removeNotification : function(notificationId){
			$("#notificationLi"+notificationId).remove();
			this.updateNotificationCount();
		},
		removeAllNotification : function(){
			$headerNotificationUl.find("li.notification").remove();
		},
		updateNotificationState : function(receiverId,notificationId){
			var _this = this;
			$.get("notification/updateNotificationState.do",{receiverId: receiverId,notificationId:notificationId},function(){
				_this.removeNotification(notificationId);
			});
		}
	}
})();