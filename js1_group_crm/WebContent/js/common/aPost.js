function aPost(url){
	//$(".page-content-body").load(url);
	
    App.scrollTop();
    var pageContent = $('.page-content');
    var pageContentBody = $('.page-content .page-content-body');
    App.blockUI(pageContent, false);
    $.messager.showLoadProgress();
    $.post(url, {}, function (res) {
    	$.messager.closeLoadProgress();
        App.unblockUI(pageContent);
        pageContentBody.html(res);
        App.fixContentHeight(); // fix content height
        App.initUniform(); // initialize uniform elements
    });
}

/**
 * 给页面中所有的类样式为ajaxify和aPost的a标签注册ajax加载
 */
$('.page-content a.ajaxify,.page-content a.aPost').live('click', function (e) {
    e.preventDefault();
    App.scrollTop();
    var url = $(this).attr("href");
    var pageContent = $('.page-content');
    var pageContentBody = $('.page-content .page-content-body');
    App.blockUI(pageContent, false);

    $.post(url, {}, function (res) {
        App.unblockUI(pageContent);
        pageContentBody.html(res);
        App.fixContentHeight(); // fix content height
        App.initUniform(); // initialize uniform elements
    });

});