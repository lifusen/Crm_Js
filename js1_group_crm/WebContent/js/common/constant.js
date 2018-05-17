var Constant = function(){
	var contextPath = window.contextPath;
	var rootPath = window.rootPath;
	return {
		// 百度语音API
		BAIDU_YUYIN_API : "http://tsn.baidu.com/text2audio?lan=zh&tok=24.1e4dc637b0244905cce69ed8a28bbfb2.2592000.1467696505.282335-8222816&ctp=1&cuid=fe80::250b:332c:ff3e:b1a9%12&tex=",
		// 桌面通知默认的图标
		NOTIFICATION_ICON_URL : rootPath + "/upload",
		NOTIFICATION_DEFAULT_ICON : rootPath +"/images/default_face.png"
	}
}();