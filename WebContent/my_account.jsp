<%@ include file="allIncludes.jspf" %>
<div class="row" style="padding-left:100px;padding-right:100px;padding-top:50px;">
	<div class="col-md-4 col-md-offset-4" align="center">
        <s:if test="#session.userId == null">
        	<div style="color:red;text-align: center">YOU HAVE TO FIRSTLY SIGN IN!</div>
        	<br/>
			<%@ include file="sign_in.jspf"%>
		</s:if>
		<s:else>
			<%@ include file="my_account.jspf"%>
		</s:else>
	</div>
</div>

<%@ include file="footer_home.jspf"%>