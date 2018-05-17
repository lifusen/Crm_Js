var LocationUtil = function(){
    return {
        getServerAddress : function(){
            var l = location.href;
            var str = l.substr(7);
            var i = str.indexOf("/");
            str = str.substr(0,i);
            console.log(str);
            return str;
        }
    }
}();