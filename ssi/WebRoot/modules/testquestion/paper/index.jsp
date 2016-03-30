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
<title>试卷管理</title>
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
			<!-- BEGIN TEST PAPER ADD MODAL FORM-->
			<div class="modal fade" id="test_paper-add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<div class="portlet box purple">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-gift"></i> 新增试卷
									</div>
								</div>
								<div class="portlet-body form">
									<form role="form" id="test_paper_add_form" class="form-horizontal" action="javascript:void(0);">
										<div class="form-body">
											<div class="form-group">
												<label class="col-md-3 control-label">试卷名称</label>
												<div class="col-md-9">
													<input name="name" type="text" class="form-control" placeholder="试卷名称">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">学科</label>
												<div class="col-md-9">
													<select name="subjectId" onchange="getChapterList($(this).val(), sychroSubject(this))" class="form-control">
														<%--<option value="400a979ba2fe41e1bf04fd386cfbd493">JavaSE</option>（入学辅导班）
														<option value="5dc99d3ad5384f08ab15484678541379">Android</option>
														<option value="4cd1f9ac181b446d8be981ff0bf89a4f">JavaEE</option>
														<option value="828f3890778c403ebdff299c08110097">iOS</option>
													--%></select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">章节</label>
												<div class="col-md-9">
													<select name="chapterId" class="form-control">
														<option value="">--请选择章节--</option>
														<%--<option value="1B7CC8D507D74312A0AD631818B37F91">Java编程基础</option>
														<option value="14643F1AA94145788388230A57040C99">面向对象</option>
														<option value="2F2DF287BC86405E8CCF9AFAE581A92F">常用API</option>
														<option value="5457f929d77249029d5c8a03e239763c">集合框架</option>
														<option value="5F45982E95EF41CC9F6D57B2A6041385">IO流</option>
														<option value="6ADD7CC7AADC4A008362BCADA885D97F">多线程</option>
														<option value="86CD29E675474B1F922662BF9D093AF8">网络编程</option>
														<option value="C2848E4C98F7463680B35F88166CEC3F">反射</option>
													--%></select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">单选题数量</label>
												<div class="col-md-9">
													<select name="singleCount" class="form-control">
														<option value="5">5</option>
														<option value="10">10</option>
														<option value="15">15</option>
														<option value="20">20</option>
														<option value="25">25</option>
														<option value="30" selected="selected">30</option>
														<option value="35">35</option>
														<option value="40">40</option>
														<option value="45">45</option>
														<option value="50">50</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">多选题数量</label>
												<div class="col-md-9">
													<select name="doubleCount" class="form-control">
														<option value="5">5</option>
														<option value="10" selected="selected">10</option>
														<option value="15">15</option>
														<option value="20">20</option>
														<option value="25">25</option>
														<option value="30">30</option>
														<option value="35">35</option>
														<option value="40">40</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">描述</label>
												<div class="col-md-9">
													<textarea class="form-control" rows="3" name="description" placeholder="描述"></textarea>
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
			<!-- END  TEST PAPER ADD MODAL FORM -->
			<!-- BEGIN PUBLIS TO CLASSES MODAL FORM -->
			<div class="modal fade" id="test_paper-publish" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<div class="portlet box purple">
								<div class="portlet-title">
									<div class="caption">
										<i class="icon-energy"></i> 发布试卷
									</div>
									<div class="actions">
										<a href="#" data-toggle="modal" onclick="publishTestPaper()" class="btn btn-default btn-sm">
										<i class="icon-energy"></i> 发布 </a>
										<a href="#" data-dismiss="modal" class="btn btn-default btn-sm">
										<i class="icon-close"></i> 取消 </a>
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
												</tr>
											</thead>
											<tbody>
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
			<!-- END PUBLIS TO CLASSES MODAL FORM -->
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
						<a href="javascript:;">试卷管理</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-sm-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-list"></i>试卷列表
							</div>
							<div class="tools" style="display:none;">
								<a href="javascript:;" onclick="loadTestPapers()" class="reload" reloadTbl="tbl_test_paper">
								</a>
							</div>
							<div class="actions">
								<a href="#test_paper-add" data-toggle="modal" class="btn btn-default btn-sm">
								<i class="fa fa-plus"></i> 新增试卷 </a>
								<a href="javascript:;" class="btn btn-default btn-sm" onclick="deleteTestPapers()">
								<i class="fa fa-trash-o"></i> 删除试卷 </a>
							</div>
						</div>
						<div class="portlet-body form">
							<div id="tbl_test_paper_wrapper" class="dataTables_wrapper no-footer">
								<div class="table-toolbar">
									<form role="form" action="javascript:void(0);" id="form_search_test_paper" onsubmit="return checkSearchForm(this);">
										<div class="form-body">
											<div class="row">
												<div class="col-md-2">
													<div class="form-group">
														<select name="subjectId" onchange="getChapterList($(this).val(), sychroSubject(this))" class="form-control" title="学科">
															<option value="">--请选择学科--</option>
															<%--<option value="400a979ba2fe41e1bf04fd386cfbd493">JavaSE</option>
															<option value="5dc99d3ad5384f08ab15484678541379">Android</option>
															<option value="4cd1f9ac181b446d8be981ff0bf89a4f">JavaEE</option>
															<option value="828f3890778c403ebdff299c08110097">iOS</option>
														--%></select>
													</div>
												</div>
												<!--/span-->
												<div class="col-md-2">
													<div class="form-group">
														<select name="chapterId" class="form-control" title="章节">
															<option value="">--请选择章节--</option>
															<%--<option value="1B7CC8D507D74312A0AD631818B37F91">Java编程基础</option>
															<option value="14643F1AA94145788388230A57040C99">面向对象</option>
															<option value="2F2DF287BC86405E8CCF9AFAE581A92F">常用API</option>
															<option value="5457f929d77249029d5c8a03e239763c">集合框架</option>
															<option value="5F45982E95EF41CC9F6D57B2A6041385">IO流</option>
															<option value="6ADD7CC7AADC4A008362BCADA885D97F">多线程</option>
															<option value="86CD29E675474B1F922662BF9D093AF8">网络编程</option>
															<option value="C2848E4C98F7463680B35F88166CEC3F">反射</option>
														--%></select>
													</div>
												</div>
												<div class="col-md-8">
													<div class="input-group ">
														<input type="text" id="ipt_test_paper_name" name="name" class="form-control" placeholder="试卷名称">
														<span class="input-group-addon" style="cursor:pointer;">
														<i class="fa fa-search"></i>
														</span>
													</div>
												</div>
												<!--/span-->
											</div>
										</div>
									</form>
								</div>
								<table class="table table-striped table-bordered table-hover" id="tbl_test_paper">
									<thead>
										<tr>
											<th class="table-checkbox"><!-- ID -->
												<input type="checkbox" class="group-checkable" data-set="#tbl_test_paper .checkboxes"/>
											</th>
											<th>
												 试卷名称
											</th>
											<th>
												 学科
											</th>
											<th>
												 章节
											</th>
											<th>
												 创建时间
											</th>
											<th>
												 状态
											</th>
											<th>
												 试卷描述
											</th>
											<th>
												 操作
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
<script src="${ctx}/modules/testquestion/paper/js/index.js" type="text/javascript"></script>
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