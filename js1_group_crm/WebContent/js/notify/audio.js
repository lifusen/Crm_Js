/**
 * Created by PC-201512111508$ on 2016/1/15.
 */
var Audio = function(){
	var mp3_01 = "../../mp3/01.mp3";
	var mp3_02 = "mp3/01.mp3";
	
	var id = "audioPalyerController";
	var audio = $("#"+id);
	if(audio.length == 0){
		audio = $("<audio id='"+id+"' hidden></audio>").appendTo('body');
	}
    var audio = audio[0];
    return {
        playMp3 : function(type){
        	var src;
        	switch(type){
        		case 1 :
        			src = mp3_01;
        			break;
        		case 2 : 
        			src = mp3_02;
        			break;
        		default :
        			src = mp3_01;
        			break;
        	}
        	audio.src = src;
        	audio.play();
        },
        playBaiduYuyin : function(text){alert(Constant.BAIDU_YUYIN_API + text);
        	audio.src = (Constant.BAIDU_YUYIN_API + text);
        	audio.play();
        }
        
    }
}();