<%-- BEGIN CORE PLUGINS --%>
<!--[if lt IE 9]>
<script src="${ctx}/static/assets/global/plugins/respond.min.js"></script>
<script src="${ctx}/static/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${sfp}/static/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${sfp}/static/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<%-- END CORE PLUGINS --%>
<%-- 全局的隐藏域，每个页面都需要引用的 --%>
<div id="common_hidden_div" style="display:none;">
	<input type="hidden" id="ctx" value="${ctx}"/><%-- 默认隐藏域，上下文对象 --%>
	<input type="hidden" id="sfp" value="${sfp}"/><%-- 默认隐藏域，文件服务器路径 --%>
	<textarea id="login_username">${loginUser.username}</textarea><%-- 默认隐藏域，当前登录用户 --%>
	<textarea id="login_name">${loginUser.name}</textarea><%-- 默认隐藏域，当前登录用户 --%>
	<textarea id="login_user">${loginUser}</textarea><%-- 默认隐藏域，当前登录用户 --%>
</div>
<script type="text/javascript">
var ctx = $('#ctx').val();
var sfp = $('#sfp').val();
var login_username = $("#login_username").val();
var login_name = $("#login_name").val();
var loginUser = $.parseJSON($("#login_user").val());
</script>
<%-- BEGIN PAGE LEVEL JS --%>
<script src="${ctx}/modules/common/include/js/common_header.js" type="text/javascript"></script>
<script src="${ctx}/modules/common/include/js/common_sidebar.js" type="text/javascript"></script>
<%-- END PAGE LEVEL JS --%>