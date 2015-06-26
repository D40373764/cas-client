<%@ page language="java"
	import="java.util.Map,java.util.HashMap,java.util.Iterator"
	pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>Smartlink dynamic loginform</title>
		<script type="text/javascript" language="javascript">
			function submitform(){
			document.forms['login'].submit();
			}
		</script>
	</head>

	<body onload="submitform()">
		<%	Map<String, String> formLoginParameters = new HashMap<String, String>();
			formLoginParameters = (Map<String,String>) request.getAttribute("formLoginParameters");
			formLoginParameters.remove("method");
			String url = formLoginParameters.get("url");
			formLoginParameters.remove("url");
		%>
		Please wait for the application to load ...
		<form name="login" action="<%=url%>" method="post">
			<table border="0" cellpadding="0" cellspacing="0">
				<%	Iterator<?> iterator = formLoginParameters.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
						String input = (String) entry.getKey();
						String value = (String) entry.getValue();
				%>
				<tr><td><input type="hidden" name="<%=input%>" value="<%=value%>"></td></tr>
				<% }
				%>
			</table>
		</form>
	</body>
</html>
