<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redis</title>
<style type="text/css">
	#container{
		width:800px;
		margin:20px auto;
	}
	#top{
		width:780px;
		height:140px;
		border:1px solid #ccc;
		padding-left:20px;
		border-bottom:0px;
	}
	#key{
		width:700px;
		height:20px;
	}
	#type{
		width:80px;
		height:24px;
	}
	#submitBtn{
		width:80px;
		height:25px;
	}
	#showIframe{
		width:100%;
		height:480px;
		border:1px solid #ccc;
		overflow: scroll;
	}
</style>
</head>
<body>
	<div id="container">
		<div id="top">
			<form action="${pageContext.request.contextPath}/getRedisValue.do" target="showIframe">
				<p>
					key: <input id="key" type="text" name="key"/>
				</p>
				<p>
					type:<select id="type" name="type">
						<option value="string">string</option>
						<option value="hash">hash</option>
						<option value="list">list</option>
						<option value="set">set</option>
						<option value="zset">zset</option>
					</select>
				</p>
				<p style="padding-left:40px;">
					<input id="submitBtn" type="submit" value="查询"/>
				</p>
			</form>
		</div>
		<iframe id="showIframe" name="showIframe" frameborder="0"></iframe>
	</div>
</body>
</html>