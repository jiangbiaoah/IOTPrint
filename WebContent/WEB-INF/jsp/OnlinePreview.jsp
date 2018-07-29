<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="checkLogin.jsp"%>
<%@page import="com.Atschool.Class.DocConverter"%>
<%
	String swfFilePath = (String) request.getAttribute("swfFilePath");
	System.out.println("OnlineView.jsp--swfFilePath=" + swfFilePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/flexpaper.js"></script>
<script type="text/javascript" src="./js/flexpaper_handlers.js"></script>
<style type="text/css" media="screen">
html, body {
	height: 100%;
}

body {
	margin: 0;
	padding: 0;
	overflow: auto;
}

#flashContent {
	display: none;
}
</style>

<title>在线预览平台系统</title>
</head>
<body>
	<div
		style="position: absolute; left: 0px; top: 0px; margin: 0 auto; width: 100%; height: 100%;">
		<div id="documentViewer" class="flexpaper_viewer"
			style="width: 100%; height: 100%; display: block; margin: 0 auto;"></div>

		<script type="text/javascript">   
  
            var startDocument = "Paper";  
  
            $('#documentViewer').FlexPaperViewer(  
                    { config : {  
  
                        SWFFile : decodeURI('<%=swfFilePath%>'),

					Scale : 1.4, //缩放的意思  D:/test/bbb.swf
					ZoomTransition : 'easeOut', //FlexPaper中缩放样式，它使用和Tweener一样的样式，默认参数值为easeOut，其他可选值包括：easenone，easeout，linear，easeoutquad
					ZoomTime : 0.5,//从一个缩放比例变为另外一个缩放比例需要花费的时间，该参数值应该为0或更大
					ZoomInterval : 0.1,//缩放比例之间间隔，默认值为0.1，该值为正数。
					FitPageOnLoad : false,//初始化时自适应页面，与使用工具栏上的适应页面按钮同样的效果
					FitWidthOnLoad : false,//初始化时自适应页面宽度，与工具栏上的适应宽度按钮同样的效果
					FullScreenAsMaxWindow : true,//如果设置为true的时候，点击全屏并不是全屏而是一个新页面，据说独立的flex播放的时候用这个比较合适  
					ProgressiveLoading : false,//true的话不全部加载文档，边看边加载  
					MinZoomSize : 0.2,//最小缩放比例  	
					MaxZoomSize : 3,//最大缩放比例 
					SearchMatchAll : true,//为true的时候搜索的时候便会出现高亮  
					InitViewMode : 'Portrait',//设置启动模式如"Portrait" or "TwoPage"
					RenderingOrder : 'flash',
					StartAtPage : '',

					ViewModeToolsVisible : false,//工具栏上是否显示样式选择框  	
					ZoomToolsVisible : false,//工具栏上是否显示缩放工具  
					NavToolsVisible : false,//工具栏上是否显示导航工具  
					CursorToolsVisible : false,//工具栏上是否显示光标工具  
					SearchToolsVisible : false,//工具栏上是否显示搜索  
					WMode : 'window',
					localeChain : 'en_US' //设置语言  en_US (English) zh_CN (Chinese)
				}
			});
		</script>
	</div>
</body>
</html>