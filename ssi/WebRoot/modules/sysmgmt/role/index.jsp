<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/include/common_consts.jsp" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>角色管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<%@ include file="/modules/common/include/common_head.jsp" %>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${sfp}/static/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link href="${sfp}/static/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${sfp}/static/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${sfp}/static/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${sfp}/static/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${sfp}/static/assets/admin/layout/css/themes/darkblue.css" id="style_color" rel="stylesheet" type="text/css"/>
<link href="${sfp}/static/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content ">
<!-- BEGIN HEADER -->
<div class="page-header -i navbar navbar-fixed-top">
<!-- BEGIN HEADER -->
<%@ include file="/modules/common/include/common_header.jsp" %>
<!-- END HEADER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<%@ include file="/modules/common/include/common_sidebar.jsp" %>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN Dialog -->
			<!-- BEGIN CLASSES-USER ADD MODAL FORM-->
			<div class="modal fade" id="role-user-add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<div class="portlet box purple">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-gift"></i> 添加角色成员
									</div>
									<div class="actions">
										<a href="#" onclick="addRoleUsers()" data-toggle="modal" class="btn btn-default btn-sm">
										<i class="fa fa-plus"></i> 添加 </a>
										<a href="#" class="btn btn-default btn-sm" data-dismiss="modal"> 取消 </a>
									</div>
								</div>
								<div class="portlet-body form">
									<div id="tbl_add_role_user_container" class="dataTables_wrapper no-footer">
										<form role="form" id="role_user_add_form" class="form-horizontal" onsubmit="return checkReloadNotInRoleUserForm();">
											<div class="form-body">
												<div class="input-group margin-top-10">
													<input type="text" class="form-control" id="ipt_add_role_users" placeholder="用户姓名">
													<span class="input-group-addon" style="cursor:pointer;" onclick="checkReloadNotInRoleUserForm()">
													<i class="fa fa-search"></i>
													</span>
												</div>
											</div>
										</form>	
										<table class="table table-striped table-bordered table-hover" id="tbl_add_role_user">
											<thead>
											<tr>
												<th class="table-checkbox">
													<input type="checkbox" class="group-checkable" data-set="#tbl_add_role_user .checkboxes"/>
												</th>
												<th>
													 姓名
												</th>
												<th>
													 手机
												</th>
												<th>
													 角色
												</th>
											</tr>
											</thead>
											<tbody>
												<tr class="odd gradeX"><td colspan="4" align="center">(没有数据)</td></tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- BEGIN CLASSES-USER END MODAL FORM-->
			<!-- END Dialog -->
			
			<!-- BEGIN PAGE HEADER-->
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="${ctx}/index.action">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="javascript:;">角色管理</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue-hoki"><!-- hoki madison --blue-- steel soft  -->
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-list"></i>角色列表
							</div>
							<div class="tools" style="display:none;">
								<a href="javascript:;" onclick="loadData()" class="reload" reloadTbl="sample_2">
								</a>
							</div>
							<%--<div class="actions">
								<a href="#classes-add" data-toggle="modal" class="btn btn-default btn-sm">
								<i class="fa fa-plus"></i> 添加 </a>
								<a href="javascript:;" class="btn btn-default btn-sm" onclick="deleteClasses('sample_2', 'deleteClasses')">
								<i class="fa fa-trash-o"></i> 删除 </a>
							</div>
						--%></div>
						<div class="portlet-body form">
							<div id="tbl_role_container" class="dataTables_wrapper no-footer">
								<div class="table-toolbar">
									<%--<form role="form" id="classes_user_add_form" action="javascript:void(0);" onsubmit="return checkReloadNotInClassesUserForm(this);">
										<div class="form-body">
											<div class="input-group margin-top-10">
												<input type="text" id="ipt_role" class="form-control" placeholder="学员姓名">
												<span class="input-group-addon" style="cursor:pointer;">
												<i class="fa fa-search"></i>
												</span>
											</div>
										</div>
									</form>	
								--%></div>
								<table class="table table-striped table-bordered table-hover" id="tbl_role">
									<thead>
										<tr>
											<%--<th class="table-checkbox">
												<input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes"/>
											</th>
											--%><th>
												 角色名称
											</th>
											<th>
												 角色
											</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue"><!-- hoki madison --blue-- steel soft  -->
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>角色成员
							</div>
							<div class="tools">
								<a href="javascript:;" style="display:none;" onclick="showNotInRoleUsers()" class="reload" reloadTbl="tbl_role_user">
								</a>
							</div>
							<div class="actions">
								<a href="#role-user-add" onclick="showNotInRoleUsers()" data-toggle="modal" class="btn btn-default btn-sm">
								<i class="fa fa-plus"></i> 添加 </a>
								<a href="javascript:;" class="btn btn-default btn-sm" onclick="delRoleUsers('tbl_role_user')">
								<i class="fa fa-trash-o"></i> 删除 </a>
							</div>
						</div>
						<div class="portlet-body form">
							<div id="tbl_role_user_container" class="dataTables_wrapper no-footer">
								<div class="table-toolbar">
									<form role="form" id="role_user_form" action="javascript:void(0);" onsubmit="return checkRoleUsersForm(this);">
										<div class="form-body">
											<div class="form-group form-md-line-input form-md-floating-label">
												<input type="text" class="form-control" id="ipt_role_user">
												<label for="ipt_role_user">按成员姓名查询</label>
												<span class="help-block">输入后请按回车键</span>
											</div>
										</div>
									</form>
								</div>
								<table class="table table-striped table-bordered table-hover" id="tbl_role_user">
									<thead>
									<tr>
										<th class="table-checkbox">
											<input type="checkbox" class="group-checkable" data-set="#tbl_role_user .checkboxes"/>
										</th>
										<th>
											 姓名
										</th>
										<th>
											 手机
										</th>
										<th>
											 角色
										</th>
									</tr>
									</thead>
									<tbody>
										<tr class="odd gradeX"><td colspan="4" align="center">(没有数据)</td></tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- BEGIN PAGE HIDDENs -->
<div style="dislplay:none;">
</div>
<!-- END PAGE HIDDENs -->
<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->
<%@ include file="/modules/common/include/common_footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<%@ include file="/modules/common/include/common_foot.jsp" %>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${sfp}/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script src="${sfp}/static/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${ctx}/modules/common/tools/MessageDialog.js" type="text/javascript"></script>
<script src="${ctx}/modules/common/tools/PagingToolbar.js" type="text/javascript"></script>
<script src="${ctx}/modules/sysmgmt/role/js/index.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {       
	// initiate layout and plugins
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
});
</script>
</body>
<!-- END BODY -->
</html>