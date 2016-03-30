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
<title>班级管理</title>
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
			<!-- BEGIN CLASSES ADD MODAL FORM-->
			<div class="modal fade" id="dialog-classes-add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<div class="portlet box red">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-gift"></i> 添加班级
									</div>
								</div>
								<div class="portlet-body form">
									<form role="form" id="classes_add_form" class="form-horizontal" action="javascript:void(0);">
										<div class="form-body">
											<div class="form-group">
												<label class="col-md-3 control-label">班级名称</label>
												<div class="col-md-9">
													<input type="text" name="name" class="form-control" 
													placeholder="班级名称" value="北京Java基础" readonly>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">所属校区</label>
												<div class="col-md-9">
													<select name="schoolId" class="form-control">
														<%--<option value="0DE772890FC94E7B9FB94D42CB8F6191">北京</option>
														<option value="3FEF4BDFD5DF46E09DE3E20B8EE7EDBF">上海</option>
														<option value="5276DCB2456F40AFA8CC0CC80AEC0A3D">深圳</option>
														<option value="8e70e64972024ed7826659d4831a27d3">长沙</option>
													--%></select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">学科</label>
												<div class="col-md-9">
													<select name="subjectId" class="form-control">
														<%--<option value="400a979ba2fe41e1bf04fd386cfbd493">JavaSE</option>（入学辅导班）
														<option value="4cd1f9ac181b446d8be981ff0bf89a4f">JavaEE</option>（入学辅导班）
														<option value="5dc99d3ad5384f08ab15484678541379">Android</option>
														<option value="828f3890778c403ebdff299c08110097">iOS</option>
													--%></select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">班级类型</label>
												<div class="col-md-9">
													<select name="type" class="form-control">
														<option value="0">基础班</option><%--（入学辅导班）--%>
														<option value="1">就业班</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">期数</label>
												<div class="col-md-9">
													<input name="phaseNumber" type="text" class="form-control" placeholder="number">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">班级编号</label>
												<div class="col-md-9">
													<input name="serialNumber" type="text" class="form-control" placeholder="MM/dd">
												</div>
											</div>
										</div>
										<div class="form-actions right">
											<button type="submit" class="btn green">保存</button>
											<button type="button" class="btn default" data-dismiss="modal">取消</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END CLASSES MODAL FORM-->
			<!-- BEGIN CLASSES-USER ADD MODAL FORM-->
			<div class="modal fade" id="classes-user-add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<div class="portlet box purple">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-gift"></i> 添加班级成员
									</div>
									<div class="actions">
										<a href="#" onclick="addClassesUsers()" data-toggle="modal" class="btn btn-default btn-sm">
										<i class="fa fa-plus"></i> 添加 </a>
										<a href="#" class="btn btn-default btn-sm" data-dismiss="modal"> 取消 </a>
									</div>
								</div>
								<div class="portlet-body form">
									<div id="tbl_add_classes_user_container" class="dataTables_wrapper no-footer">
										<div class="table-toolbar">
											<form role="form" id="classes_user_add_form" action="javascript:void(0);" onsubmit="return checkReloadNotInClassesUserForm(this);">
												<div class="form-body">
													<div class="input-group margin-top-10">
														<input type="text" id="ipt_add_classes_users" class="form-control" placeholder="学员姓名">
														<span class="input-group-addon" style="cursor:pointer;">
														<i class="fa fa-search"></i>
														</span>
													</div>
												</div>
											</form>	
										</div>
										<table class="table table-striped table-bordered table-hover" id="tbl_add_classes_user">
											<thead>
											<tr>
												<th class="table-checkbox">
													<input type="checkbox" class="group-checkable" data-set="#tbl_add_classes_user .checkboxes"/>
												</th>
												<th>
													 姓名
												</th>
												<th>
													 手机
												</th>
												<th>
													 成员类型
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
						<a href="javascript:;">班级管理</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-list"></i>班级列表
							</div>
							<div class="tools" style="display:none;">
								<a href="javascript:;" onclick="loadClasses()" class="reload" reloadTbl="tbl_classes">
								</a>
							</div>
							<div class="actions">
								<a href="#dialog-classes-add" onclick="initSelects()" data-toggle="modal" class="btn btn-default btn-sm">
								<i class="fa fa-plus"></i> 添加 </a>
								<a href="javascript:;" class="btn btn-default btn-sm" onclick="deleteClasses('tbl_classes', 'deleteClasses')">
								<i class="fa fa-trash-o"></i> 删除 </a>
							</div>
						</div>
						<div class="portlet-body form">
							<div id="tbl_classes_container" class="dataTables_wrapper no-footer">
								<div class="table-toolbar">
									<form role="form" action="javascript:void(0);" onsubmit="return checkReloadClassesForm(this);">
										<div class="form-body">
											<div class="form-group form-md-line-input form-md-floating-label">
												<input type="text" class="form-control" id="ipt_classes">
												<label for="ipt_classes">按班级名称查询</label>
												<span class="help-block">输入后请按回车键</span>
											</div>
										</div>
									</form>
								</div>
								<table class="table table-striped table-bordered table-hover" id="tbl_classes">
									<thead>
										<tr>
											<th class="table-checkbox">
												<input type="checkbox" class="group-checkable" data-set="#tbl_classes .checkboxes"/>
											</th>
											<th>
												 班级名称
											</th>
											<th>
												 班级类型
											</th>
											<th>
												 学科
											</th>
											<th>
												 期数
											</th>
											<th>
												 状态
											</th>
											<th>
												 操作
											</th>
										</tr>
									</thead>
									<tbody>
										<%--<tr class="odd gradeX">
											<td>
												<input type="checkbox" class="checkboxes" value="1"/>
											</td>
											<td>
												 shuxer
											</td>
											<td>
												<a href="mailto:shuxer@gmail.com">
												shuxer@gmail.com </a>
											</td>
											<td>
												<span class="label label-sm label-success">
												已开班 </span>
												<span class="label label-sm label-warning">
												未开班 </span>
												<span class="label label-sm label-default">
												已结课 </span>
											</td>
										</tr>--%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>班级成员
							</div>
							<div class="tools">
								<a href="javascript:;" style="display:none;" onclick="showClassesUsers(class_id)" class="reload" reloadTbl="tbl_classes_user">
								</a>
							</div>
							<div class="actions">
								<a href="#classes-user-add" onclick="showNotInClassesUsers()" data-toggle="modal" class="btn btn-default btn-sm">
								<i class="fa fa-plus"></i> 添加 </a>
								<a href="javascript:;" class="btn btn-default btn-sm" onclick="deleteClasses('tbl_classes_user', 'deleteClassesUser')">
								<i class="fa fa-trash-o"></i> 删除 </a>
							</div>
						</div>
						<div class="portlet-body form">
							<div id="tbl_classes_user_container" class="dataTables_wrapper no-footer">
								<div class="table-toolbar">
									<form role="form" action="javascript:void(0);" onsubmit="return checkReloadClassesUserForm(this);">
										<div class="form-body">
											<div class="form-group form-md-line-input form-md-floating-label">
												<input type="text" class="form-control" id="ipt_classes_user">
												<label for="ipt_classes_user">按姓名查询</label>
												<span class="help-block">输入后请按回车键</span>
											</div>
										</div>
									</form>
								</div>
								<table class="table table-striped table-bordered table-hover" id="tbl_classes_user">
									<thead>
									<tr>
										<th class="table-checkbox">
											<input type="checkbox" class="group-checkable" data-set="#tbl_classes_user .checkboxes"/>
										</th>
										<th>
											 姓名
										</th>
										<th>
											 手机
										</th>
										<th>
											 类型
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
<script src="${ctx}/modules/testquestion/common/js/common_const.js" type="text/javascript"></script>
<script src="${ctx}/modules/testquestion/classes/js/index.js" type="text/javascript"></script>
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