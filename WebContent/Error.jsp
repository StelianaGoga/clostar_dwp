<%@ page language="java" isErrorPage="true"%>
<%@ include file="JspHeader.jspf"%>

<head>
<%@ include file="JspScriptsIncludes.jspf"%>
<META Http-Equiv="Cache-Control" Content="no-cache">
<META Http-Equiv="Pragma" Content="no-cache">
<META Http-Equiv="Expires" Content="0">
<link rel="stylesheet" type="text/css" media="screen" href="css/stylesheet.css" />
<title><!-- <bean:message key="error-page.title" /> -->Error</title>
<style>
textarea#error_msg {
	width: 85%;
	height: 250px;
	border: 1px solid #cccccc;
	padding: 1px;
	font-family: Tahoma, sans-serif;
}
</style>

<script type="text/javascript">
	function setbg(color) {
		document.getElementById("error_msg").style.background = color;
	}
</script>

</head>
<body bgcolor="#fafafa">

    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />

    <h3 class="title">
    	Error!
        <!-- <bean:message key="error.message" /> -->
    </h3>

    <h5 align="center">
        <textarea id="error_msg" onfocus="setbg('#f8f8f8');" onblur="setbg('white')" readonly="readonly">
        	Error Text!
            <%-- < %
                if (exception != null) {
                        java.io.StringWriter sw = new java.io.StringWriter();
                        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
                        exception.printStackTrace(pw);
                        out.print(sw.toString().substring(0, 500) + " ...");
                    } else if (request.getAttribute("error-message") != null) {
                        out.println(request.getAttribute("error-message"));
                    }
            %> --%>
</textarea>
    </h5>
    <br />
    <center>
        <a href="login.do" style="color: white; text-decoration: none;"> Login-again<!-- <bean:message key="error.login-again" /> -->
        </a>
    </center>
</body>
