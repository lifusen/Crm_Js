var DateUtil = {
	getDiffDay : function(dateString){
		var nowTime = new Date().getTime();
		var endTime = Date.parse(dateString.replace(/-/g, "/"));
		var diffTime = endTime - nowTime;
		var diffDay = diffTime/(24*60*60*1000);
		console.log(diffDay);
		diffDay = parseInt(diffDay);
		return diffDay;
	},
	/**
	 * Example:var f = a(new Date(),'2015-4-14');
	 * @param date 日期
	 * @param string 日期字符串
	 * @returns true:相同/false:不同
	 */
	compareDay : function(date,string){
		var date1 = new Date(Date.parse(string.replace(/-/g, "/")));
		return date.getDay()==date1.getDay();
	},
	/**
	 * 判断日期是否相等
	 * @param date
	 * @param dateString
	 * @returns {Boolean}
	 */
	equals : function(date,dateString){
		var startTime = date.getTime();
		var endTime = new Date(dateString).getTime();
		return startTime==endTime;
	},
	/**
	 * 
	 * @param day 间隔多少天
	 */
	getEndDate : function(day){
		var currentTime = new Date().getTime();
		day = parseInt(day);
		var intervalTime = 1000*1*60*60*24*day;
		return new Date(currentTime+intervalTime);
	}
	
}