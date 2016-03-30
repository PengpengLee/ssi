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
<title>试题管理</title>
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
<body class="page-header-fixed page-quick-sidebar-over-content page-header-fixed-mobile page-footer-fixed-mobile">
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
			<!-- BEGIN TEST_QUESTION ADD MODAL FORM-->
			<div class="modal fade" id="test_question-add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:60%;">
					<div class="modal-content">
						<div class="modal-body">
							<div class="portlet box green">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-gift"></i> 添加试题
									</div>
									<div class="actions">
										<a id="action_save" data-toggle="save" class="btn btn-default btn-sm">
										保存 </a>
										<a data-dismiss="modal" class="btn btn-default btn-sm">
										取消 </a>
									</div>
								</div>
								<div class="portlet-body form">
									<form role="form" id="test_question_add_form" class="form-horizontal" action="javascript:void(0);">
										<div class="form-body">
											<div class="form-group">
												<label class="col-md-2 control-label">学科<span class="required" aria-required="true"> * </span></label>
												<div class="col-md-10">
													<select name="subjectId" onchange="getChapterList($(this).val())" class="form-control">
														<option value="400a979ba2fe41e1bf04fd386cfbd493">JavaSE</option><%--（入学辅导班）--%>
														<option value="5dc99d3ad5384f08ab15484678541379">Android</option>
														<option value="4cd1f9ac181b446d8be981ff0bf89a4f">JavaEE</option>
														<option value="828f3890778c403ebdff299c08110097">iOS</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">章节<span class="required" aria-required="true"> * </span></label>
												<div class="col-md-10">
													<select name="chapterId" class="form-control">
														<option value="">--请选择章节--</option>
														<option value="1B7CC8D507D74312A0AD631818B37F91">Java编程基础</option>
														<option value="14643F1AA94145788388230A57040C99">面向对象</option>
														<option value="2F2DF287BC86405E8CCF9AFAE581A92F">常用API</option>
														<option value="5457f929d77249029d5c8a03e239763c">集合框架</option>
														<option value="5F45982E95EF41CC9F6D57B2A6041385">IO流</option>
														<option value="6ADD7CC7AADC4A008362BCADA885D97F">多线程</option>
														<option value="86CD29E675474B1F922662BF9D093AF8">网络编程</option>
														<option value="C2848E4C98F7463680B35F88166CEC3F">反射</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">试题题干<span class="required"> * </span>
												</label>
												<div class="col-md-10" id="div_trunk_editor_container">
													<textarea class="ckeditor form-control" name="trunk" rows="6" data-error-container="#trunk_editor"></textarea>
													<div id="trunk_editor">
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">选项A<span class="required"> * </span>
												</label>
												<div class="col-md-10" id="div_option_a_editor_container">
													<textarea class="ckeditor form-control" name="option_a" rows="6" data-error-container="#option_a_editor"></textarea>
													<div id="option_a_editor">
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">选项B<span class="required"> * </span>
												</label>
												<div class="col-md-10" id="div_option_b_editor_container">
													<textarea class="ckeditor form-control" name="option_b" rows="6" data-error-container="#option_b_editor"></textarea>
													<div id="option_b_editor">
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">选项C<span class="required"> * </span>
												</label>
												<div class="col-md-10" id="div_option_c_editor_container">
													<textarea class="ckeditor form-control" name="option_c" rows="6" data-error-container="#option_c_editor"></textarea>
													<div id="option_c_editor">
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">选项D<span class="required"> * </span>
												</label>
												<div class="col-md-10" id="div_option_d_editor_container">
													<textarea class="ckeditor form-control" name="option_d" rows="6" data-error-container="#option_d_editor"></textarea>
													<div id="option_d_editor">
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">选项E
												</label>
												<div class="col-md-10" id="div_option_e_editor_container">
													<textarea class="ckeditor form-control" name="option_e" rows="6" data-error-container="#option_e_editor"></textarea>
													<div id="option_e_editor">
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">选项F
												</label>
												<div class="col-md-10" id="div_option_f_editor_container">
													<textarea class="ckeditor form-control" name="option_f" rows="6" data-error-container="#option_f_editor"></textarea>
													<div id="option_f_editor">
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">难度<span class="required" aria-required="true"> * </span></label>
												<div class="col-md-10 md-radio-inline">
													<div class="md-radio has-success">
														<input type="radio" id="radio_difficulty_1" value="1" name="difficulty" class="md-radiobtn" checked>
														<label for="radio_difficulty_1">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														容易 </label>
													</div>
													<div class="md-radio has-info">
														<input type="radio" id="radio_difficulty_2" value="2" name="difficulty" class="md-radiobtn">
														<label for="radio_difficulty_2">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														简单 </label>
													</div>
													<div class="md-radio has-warning">
														<input type="radio" id="radio_difficulty_3" value="3" name="difficulty" class="md-radiobtn">
														<label for="radio_difficulty_3">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														中等 </label>
													</div>
													<div class="md-radio has-error">
														<input type="radio" id="radio_difficulty_4" value="4" name="difficulty" class="md-radiobtn">
														<label for="radio_difficulty_4">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														困难 </label>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">答案<span class="required" aria-required="true"> * </span></label>
												<div class="col-md-10 md-checkbox-inline">
													<div class="md-checkbox has-success">
														<input type="checkbox" id="checkbox_answer_A" value="A" name="answer" class="md-check" checked>
														<label for="checkbox_answer_A">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														A </label>
													</div>
													<div class="md-checkbox has-info">
														<input type="checkbox" id="checkbox_answer_B" value="B" name="answer" class="md-check">
														<label for="checkbox_answer_B">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														B </label>
													</div>
													<div class="md-checkbox has-warning">
														<input type="checkbox" id="checkbox_answer_C" value="C" name="answer" class="md-check">
														<label for="checkbox_answer_C">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														C </label>
													</div>
													<div class="md-checkbox has-error">
														<input type="checkbox" id="checkbox_answer_D" value="D" name="answer" class="md-check">
														<label for="checkbox_answer_D">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														D </label>
													</div>
													<div class="md-checkbox">
														<input type="checkbox" id="checkbox_answer_E" value="E" name="answer" class="md-check">
														<label for="checkbox_answer_E">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														E </label>
													</div>
													<div class="md-checkbox">
														<input type="checkbox" id="checkbox_answer_F" value="F" name="answer" class="md-check">
														<label for="checkbox_answer_F">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														F </label>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">类型<span class="required" aria-required="true"> * </span></label>
												<div class="col-md-10 md-radio-inline">
													<div class="md-radio has-success">
														<input type="radio" id="radio_type_1" value="1" name="type" class="md-radiobtn" checked>
														<label for="radio_type_1">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														单选 </label>
													</div>
													<div class="md-radio has-error">
														<input type="radio" id="radio_type_2" value="2" name="type" class="md-radiobtn">
														<label for="radio_type_2">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														多选 </label>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">分值<span class="required" aria-required="true"> * </span></label>
												<div class="col-md-10 md-radio-inline">
													<div class="md-radio has-success">
														<input type="radio" id="radio1_point_2" value="2" name="point" class="md-radiobtn" checked>
														<label for="radio1_point_2">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														2分</label>
													</div>
													<div class="md-radio has-info">
														<input type="radio" id="radio1_point_2_5" value="2.5" name="point" class="md-radiobtn">
														<label for="radio1_point_2_5">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														2.5分 </label>
													</div>
													<div class="md-radio has-warning">
														<input type="radio" id="radio1_point_4" value="4" name="point" class="md-radiobtn">
														<label for="radio1_point_4">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														4分 </label>
													</div>
													<div class="md-radio has-error">
														<input type="radio" id="radio1_point_5" value="5" name="point" class="md-radiobtn">
														<label for="radio1_point_5">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														5分 </label>
													</div>
													<div class="md-radio">
														<input type="radio" id="radio1_point_6" value="6" name="point" class="md-radiobtn">
														<label for="radio1_point_6">
														<span></span>
														<span class="check"></span>
														<span class="box"></span>
														6分 </label>
													</div>
												</div>
											</div>
										</div>
										<div class="form-actions right">
											<button type="button" data-toggle="save" class="btn green">保存</button>
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
			<!-- END TEST_QUESTION ADD MODAL FORM-->
			<!-- BEGIN TEST_QUESTION DETAIL MODAL FORM-->
			<div class="modal fade" id="test_question-detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:50%;">
					<div class="modal-content">
						<div class="modal-body">
							<div class="portlet box green">
								<div class="portlet-title">
									<%--<div class="caption">
										<i class="fa fa-gift"></i> 试题详情
									</div>
									--%><div class="caption">
										<i class="icon-equalizer"></i>
										<span class="caption-subject bold uppercase">试题详情</span>
										<span class="caption-helper">some info...</span>
									</div>
									<div class="actions">
										<a data-dismiss="modal" class="btn btn-default btn-sm">
										取消 </a>
									</div>
								</div>
								<div class="portlet-body form flip-scroll">
									<form role="form" id="test_question_detail_form" class="form-horizontal" action="javascript:void(0);">
										<div class="form-body">
											<h3 class="form-section margin-bottom-20">题干</h3>
											<div class="row form-section">
												<div class="col-md-9">
													<div class="form-group">
														<label class="control-label col-md-2">A:</label>
														<div class="col-md-9">
															<p class="form-control-static">
																 选项A
															</p>
														</div>
													</div>
												</div>
											</div>
											<div class="row form-section">
												<div class="col-md-9">
													<div class="form-group">
														<label class="control-label col-md-2">B:</label>
														<div class="col-md-9">
															<p class="form-control-static">
																 选项B
															</p>
														</div>
													</div>
												</div>
												<!--/span-->
											</div>
											<div class="row form-section">
												<div class="col-md-9">
													<div class="form-group">
														<label class="control-label col-md-2">C:</label>
														<div class="col-md-9">
															<p class="form-control-static">
																 选项C
															</p>
														</div>
													</div>
												</div>
												<!--/span-->
											</div>
											<!--/row-->
											<div class="row form-section">
												<div class="col-md-9">
													<div class="form-group">
														<label class="control-label col-md-2">D:</label>
														<div class="col-md-9">
															<p class="form-control-static">
																 选项D
															</p>
														</div>
													</div>
												</div>
												<!--/span-->
											</div>
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
			<!-- END TEST_QUESTION DETAIL MODAL FORM-->
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
						<a href="javascript:;">试题管理</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-sm-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-list"></i>试题列表
							</div>
							<div class="tools" style="display:none;">
								<a href="javascript:;" onclick="loadTestQuestions()" class="reload" reloadTbl="sample_2">
								</a>
							</div>
							<div class="actions">
								<a href="#test_question-add" onclick="initSelects()" data-toggle="modal" class="btn btn-default btn-sm">
								<i class="fa fa-plus"></i> 添加 </a>
								<a href="javascript:;" class="btn btn-default btn-sm" onclick="deleteTestQuestions()">
								<i class="fa fa-trash-o"></i> 删除 </a>
							</div>
						</div>
						<div class="portlet-body form flip-scroll">
							<div id="sample_2_wrapper" class="dataTables_wrapper no-footer">
								<div class="table-toolbar">
									<form role="form" action="javascript:void(0);" onsubmit="return checkForm(this);">
										<div class="form-body">
											<div class="form-group form-md-line-input form-md-floating-label">
												<input type="text" class="form-control" id="form_control_1">
												<label for="form_control_1">按试题题干查询</label>
												<span class="help-block">输入后请按回车键搜索</span>
											</div>
										</div>
									</form>
								</div>
								<table class="table table-bordered table-striped table-condensed flip-content table-hover" id="sample_2">
									<thead class="flip-content">
										<tr>
											<th class="table-checkbox" width="5%"><!-- ID -->
												<input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes"/>
											</th>
											<th width="60%">
												 题干
											</th>
											<th width="5%">
												 学科
											</th>
											<th width="10%">
												 章节
											</th>
											<th width="5%">
												 难度
											</th>
											<th width="5%">
												 题型
											</th>
											<th width="10%" style="text-align:center;">
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
<script src="${sfp}/static/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script src="${sfp}/static/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${sfp}/static/assets/global/plugins/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="${ctx}/modules/common/tools/MessageDialog.js" type="text/javascript"></script>
<script src="${ctx}/modules/common/tools/PagingToolbar.js" type="text/javascript"></script>
<script src="${ctx}/modules/testquestion/common/js/common_const.js" type="text/javascript"></script>
<script src="${ctx}/modules/testquestion/test/js/index.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {       
	// initiate layout and plugins
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	//CKEDITOR.replace("trunk", {height:70 })
	CKEDITOR.replace("option_a", {height:90 })
	CKEDITOR.replace("option_b", {height:90 })
	CKEDITOR.replace("option_c", {height:90 })
	CKEDITOR.replace("option_d", {height:90 })
	CKEDITOR.replace("option_e", {height:50 })
	CKEDITOR.replace("option_f", {height:50 })
});
</script>
</body>
<!-- END BODY -->
</html>