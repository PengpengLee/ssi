function loadTestPapers() {
	// 分页参数：当前页
	var params = "page=" + Paging.page;
	var ss = $("#ipt_test_paper_name").val();
	if($.trim(ss)) {
		params += '&name=' + ss;
	}
	var subjectId = $("#form_search_test_paper").find("select[name='subjectId']").val();
	if(subjectId) {
		params += '&subjectId=' + subjectId;
	}
	var chapterId = $("#form_search_test_paper").find("select[name='chapterId']").val();
	if(chapterId) {
		params += '&chapterId=' + chapterId;
	}

	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/testPaper/getPageList.action',
		data : params,
		success : function(data) {
			Paging.init("tbl_test_paper_wrapper", data, loadTestPapers)
			var tbody = $("#tbl_test_paper").find("tbody");
			var dataList = data.data;
			var trs = "";
			if(dataList && dataList.length > 0) {
				for(var i = 0; i < dataList.length; i++) {
					var vo = dataList[i];
					var id = vo.id;
					var name = vo.name;
					var points = vo.points; // 总分
					var subjectId = vo.subjectId; // 学科ID
					var subjectName = vo.subjectName; // 学科名称
					var chapterName = vo.chapterName; // 章节
					var createTime = vo.createTime; // 创建时间
					var status = vo.status; // 试卷描述
					var description = vo.description; // 试卷描述
					var statusName = '', statusBtns = '';
					switch(status) {
					case '0':
						statusName = '创建';
						statusBtns += '<a href="#test_paper-publish" data-toggle="modal" onclick="loadClasses(\''+subjectId+'\', \''+id+'\')">发布</a>&nbsp;&nbsp;';
						statusBtns += '<a href="javascript:;" onclick="deleteTestPapers(\''+id+'\')">删除</a>';
						break;
					case '1':
						statusName = '发布';
						statusBtns += '<a href="#test_paper-publish" data-toggle="modal" onclick="loadClasses(\''+subjectId+'\', \''+id+'\')">再次发布</a>&nbsp;&nbsp;';
						statusBtns += '<a href="javascript:;" onclick="Msg.alert(\'敬请期待！\')">发布记录</a>&nbsp;&nbsp;';
						statusBtns += '<a href="javascript:;" onclick="Msg.alert(\'敬请期待！\')">过期</a>';
						break;
					case '2':
						statusName = '已过期';
						statusBtns += '<a href="javascript:;" onclick="Msg.alert(\"敬请期待！\")">发布记录</a>&nbsp;&nbsp;';
						break;
					}
					
					trs += '<tr class="odd gradeX">';
					trs += '<td><div class="checker"><span><input type="checkbox" class="checkboxes" value="1" id="td_'+id+'"></span></div></td>';
					trs += '<td><a href="javascript:;" onclick="getTestPaperDetail(\''+id+'\')" title="点击查看详情">' + name + '</a></td>';
//					trs += '<td>' + points + '</td>';
					trs += '<td>' + subjectName + '</td>';
					trs += '<td>' + chapterName + '</td>';
					trs += '<td>' + createTime + '</td>';
					trs += '<td>' + statusName + '</td>';
					trs += '<td>' + description + '</td>';
					trs += '<td>';
					trs += statusBtns;
					trs += '</td>';
					trs += '</tr>';
				}
			} else {
				trs = '<tr class="odd gradeX"><td colspan="8" align="center">(没有数据)</td></tr>';
			}
			tbody.html(trs);
			
			// 追加样式
			tbody.find(".checker").each(function(i){
				$(this).live("hover", function(e){
					$(this).addClass("hover");
				});
				$(this).live("mouseout", function(e){
					$(this).removeClass("hover");
				});
				$(this).live("click", function(e){
					var _s = $(this).find("span");
					if(_s.attr("class") == "checked") {
						_s.removeClass("checked");
					} else {
						_s.addClass("checked");
					}
				});
			});
			
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
	return false;
}

/**
 * 按试卷名称搜索
 */
function checkSearchForm(form) {
	Paging.page = 1;
	loadTestPapers();
	return false;
}

/**
 * 页面加载时获取试卷数据
 */
function reloadTestPapers() {
	$("a[reloadTbl='tbl_test_paper']").click();
}

/**
 * 删除试卷，支持批量删除
 */
function deleteTestPapers(id) {
	var ids = '';
	if(id) {
		ids = id;
	} else {
		ids = getCheckedRowIds('tbl_test_paper');
	}
	if(!ids) return;
	Msg.confirm("确认删除？", function(){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : ctx + '/testPaper/deleteTestPaperById.action',
			data : {id: ids},
			success : function(msg) {
				if(msg.key == "success") {
					/** 重新加载试卷数据列表 */
					$("a[reloadTbl='tbl_test_paper']").click();
				}
			},
			error : function(msg) {
				Msg.alert("网络异常，请刷新重试");
			}
		});
	});
}
var test_paper_id;// 试卷ID
var subject_id; // 学科ID
function loadClasses(subjectId, testPaperId) {
	// 分页参数：当前页
	var params = "page=" + Paging.page;
	var ss = $("#ipt_classes").val();
	if($.trim(ss)) {
		params += '&name=' + ss;
	}
	if(subjectId) {
		subject_id = subjectId;
	}
	if(testPaperId) {
		test_paper_id = testPaperId;
	}
	params += '&subjectId=' + subject_id + '&status=1';// 学科、班级状态（1-已开班）
	
	if(!test_paper_id || !subject_id) {
		Msg.alert("请选择试卷");
		return;
	}
	
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/classes/getClassesList.action',
		data : params,
		success : function(data) {
			Paging.init("tbl_classes_container", data, loadClasses);
			var tbody = $("#tbl_classes").find("tbody");
			var dataList = data.data;
			
			var trs = "";
			if(dataList && dataList.length > 0) {
				for(var i = 0; i < dataList.length; i++) {
					var classes = dataList[i];
					var id = classes.id;
					var name = classes.name;
					var type = classes.type;
					type = type == 0 ? '基础班' : '就业班';
					var subjectName = classes.subjectName;
					var phaseNumber = classes.phaseNumber;
					var status = classes.status;
					switch(status) {
					case '0':
						status = '未开班';
						break;
					case '1':
						status = '已开班';
						break;
					case '2':
						status = '已结课';
						break;
					}
					
					trs += '<tr class="odd gradeX">';
					trs += '<td><div class="checker"><span><input type="checkbox" class="checkboxes" value="1" id="td_'+id+'"></span></div></td>';
					trs += '<td>' + name + '</td>';
					trs += '<td>' + type + '</td>';
					trs += '<td>' + subjectName + '</td>';
					trs += '<td>' + phaseNumber + '</td>';
					trs += '<td>' + status + '</td>';
					trs += '</tr>';
				}
			} else {
				trs = '<tr class="odd gradeX"><td colspan="6" align="center">(没有数据)</td></tr>';
			}
			tbody.html(trs);
			
			// 追加样式
			tbody.find(".checker").each(function(i){
				$(this).live("hover", function(e){
					$(this).addClass("hover");
				});
				$(this).live("mouseout", function(e){
					$(this).removeClass("hover");
				});
				$(this).live("click", function(e){
					var _s = $(this).find("span");
					if(_s.attr("class") == "checked") {
						_s.removeClass("checked");
					} else {
						_s.addClass("checked");
					}
				});
			});
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

/**
 * 按班级名称搜索
 */
function checkReloadClassesForm(form) {
	Paging.page = 1;
	loadClasses();
	return false;
}

/**
 * 试卷发布
 */
function publishTestPaper() {
	if(!test_paper_id) {
		Msg.alert("请选择试卷");
		return;
	}
	var classesIds = getCheckedRowIds("tbl_classes"); // 班级IDs
	if(!classesIds) {
		Msg.alert("请选择班级")
		return;
	}
	var params = "id=" + test_paper_id + "&classesId=" + classesIds;
	$.ajax({
		type : "POST",
		dataType : "json",
		data : params,
		url : ctx + '/testPaper/publilshById.action',
		data : params,
		success : function(msg) {
			if(msg.key == 'success') {
				loadTestPapers(); // 重新加载试卷列表
			}
			Msg.alert(msg.msg);
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
	
}

/**
 * 获取当前表格中选定的数据行的ID值，可能有多个
 * @param tableId
 * @returns {String}
 */
function getCheckedRowIds(tableId) {
	var ids = '';
	$("#" + tableId).find(".checkboxes").each(function(i){
		var ch = $(this).parent().attr("class");
		if(ch == "checked") {
			var id = $(this).attr("id").substring(3);
			if(ids == '') {
				ids += id;
			} else {
				ids += ',' + id;
			}
		}
	});
	return ids;
}

/**
 * 同步学科下拉选择框
 */
function sychroSubject(subject) {
	var subjectId = $(subject).val();
	$("[name='subjectId']").val(subjectId);
}

/**
 * 获取试卷详情
 * @param id
 */
function getTestQuestion(id) {
	if(!id) return;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/testQuestion/getTestQuestion.action',
		data : {id: id},
		success : function(msg) {
			if(msg.key == "success") {
				/** 重新初始化添加试卷表单 */
				var test = msg.obj;
				alert(test.trunk);
			} else {
				Msg.alert(msg.msg);
			}
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

$(function(){
	/** 加载试卷数据列表 */
	loadTestPapers();
	
	/** 页面加载时为全选框定义事件 */
	$(".group-checkable").click(function(){
		var data_set = $(this).attr("data-set");
		data_set = data_set.substring(1).split(" ")[0];
		var _checked = $(this).attr("checked");
		$("#" + data_set).find("tbody").find(".checkboxes").each(function(){
			if(_checked == "checked") {
				$(this).parent().addClass("checked");
				$(this).attr("checked", true);
			} else {
				$(this).parent().removeClass("checked");
				$(this).attr("checked", false);
			}
		});
	});
	
	/** 页面加载时为表格的行tr设置点击样式 */
	$(".gradeX").live("click", function(){
		$(this).parent().find(".gradeX").each(function(){
			$(this).attr("style", "");
		});
		$(this).attr("style", "background-color:silver;");
		//$(this).find(":checkbox").parent().toggleClass("checked");
	});
	
	
	/** 搜索试卷 */
    $('#test_paper_add_form').validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",
        rules: {
        	subjectId: {
        		required: true
        	},
        	chapterId: {
        		required: true
        	},
        	name: {
        		required: true
        	}
        },

        invalidHandler: function(event, validator) { //display error alert on form submit   
        	//alert('12');
        	// TODO checkForm <span class="help-block" id="name-error">This field is required.</span>
        },

        highlight: function(element) { // hightlight error inputs
            $(element)
                .closest('.form-group').addClass('has-error'); // set error class to the control group
        },

        success: function(label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },

        errorPlacement: function(error, element) {
            if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
                error.insertAfter($('#register_tnc_error'));
            } else if (element.closest('.input-icon').size() === 1) {
                error.insertAfter(element.closest('.input-icon'));
            } else {
                error.insertAfter(element);
            }
        },

        submitHandler: function(form) {
        	// 异步提交表单 
			$.ajax({
				url : ctx + '/testPaper/saveTestPaper.action',
				type : 'POST',
				data : $(form).serialize(),
				dataType : 'json',
				success : function (msg){
					if(msg.key == 'success'){
						loadTestPapers();
						$("button[data-dismiss='modal']").click();
						$('#test_paper_add_form')[0].reset();
					}
					Msg.alert(msg.msg);
				}, error : function(msg){
					Msg.alert('网络异常，请刷新重试');
				}
			});
        }
    });
    /* 绑定表单回车事件 */ 
    $('.input-group-addon').live("click", function(e) {
    	if ($('#form_search_test_paper').validate().form()) {
			$('#form_search_test_paper').submit();
		}
		return false;
    });
    /* 绑定表单回车事件 */ 
    $('#ipt_test_paper_name').keypress(function(e) {
        if (e.which == 13) {
            if ($('#form_search_test_paper').validate().form()) {
                $('#form_search_test_paper').submit();
            }
            return false;
        }
    });
    
    /* 加载学科选项列表 */
	getSubjectList();
});

