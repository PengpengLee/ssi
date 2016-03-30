function loadTestQuestions() {
	// 分页参数：当前页
	var params = "page=" + Paging.page;
	var ss = $("#form_control_1").val();
	if(ss.trim()) {
		params += '&ss=' + ss;
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/testQuestion/getTestQuestionList.action',
		data : params,
		success : function(data) {
			Paging.init("sample_2_wrapper", data, loadTestQuestions)
			var tbody = $("#sample_2").find("tbody");
			var dataList = data.data;
			var trs = "";
			if(dataList && dataList.length > 0) {
				for(var i = 0; i < dataList.length; i++) {
					var vo = dataList[i];
					var id = vo.id;
					var trunk = vo.trunk;
					var checkStatus = vo.checkStatus; // 审核状态
					var chapter = vo.chapterName; // 章节
					var subject = vo.subjectName; // 学科
					var difficulty = vo.difficulty; // 难度：容易-1；简单-2；中等-3；困难-4
					switch(difficulty) {
					case '1':
						difficulty = '容易';
						break;
					case '2':
						difficulty = '简单';
						break;
					case '3':
						difficulty = '中等';
						break;
					case '4':
						difficulty = '困难';
						break;
					}
					var type = vo.type; // 题型：单选-1；多选-2
					type = type == 1 ? '单选' : '多选';
					
					trs += '<tr class="odd gradeX">';
					trs += '<td><div class="checker"><span><input type="checkbox" class="checkboxes" value="1" id="td_'+id+'"></span></div></td>';
					trs += '<td>' + trunk + '</td>';
					trs += '<td>' + subject + '</td>';
					trs += '<td>' + chapter + '</td>';
					trs += '<td>' + difficulty + '</td>';
					trs += '<td>' + type + '</td>';
					trs += '<td align="center">';
					trs += '<a class="add" href="#test_question-detail" data-toggle="modal" onclick="getTestQuestion(\''+id+'\')">查看</a>&nbsp;&nbsp;';
					if(checkStatus == '1') {
						trs += '<a class="delete" href="javascript:;" onclick="deleteTestQuestions(\''+id+'\')">删除</a>';
					}
					
					trs += '</td>';
					trs += '</tr>';
				}
			} else {
				trs = '<tr class="odd gradeX"><td colspan="4" align="center">(没有数据)</td></tr>';
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
 * 按试题名称搜索
 */
function checkForm(form) {
	Paging.page = 1;
	loadTestQuestions();
	return false;
}

/**
 * 页面加载时获取试题数据
 */
function reloadTestQuestions() {
	$("a[reloadTbl='sample_2']").click();
}

/**
 * 删除试题，支持批量删除
 */
function deleteTestQuestions(id) {
	var ids = '';
	if(id) {
		ids = id;
	} else {
		ids = getCheckedRowIds('sample_2');
	}
	if(!ids) return;
	Msg.confirm("确认删除？", function() {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : ctx + '/testQuestion/deleteTestQuestion.action',
			data : {id: ids},
			success : function(msg) {
				//Msg.alert(msg.msg);
				if(msg.key == "success") {
					/** 重新加载试题数据列表 */
					$("a[reloadTbl='sample_2']").click();
				}
			},
			error : function(msg) {
				Msg.alert("网络异常，请刷新重试");
			}
		});
	});
}

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
 * 获取试题详情
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
				/** 重新初始化添加试题表单 */
				var test = msg.obj;
				var html = '<h3 class="form-section margin-bottom-10"><b>'+ test.trunk +'</b></h3>';
//				var html = '<h3 class="form-section margin-bottom-10">'+ test.trunk +'</h3>';
				var serailArr = ["A", "B", "C", "D", "E", "F"];
				for(var i = 0; i < test.options.length; i++) {
					html += '<div class="row form-section">';
					if(test.options[i].isTruth == "1") {
						html += '	<div class="col-md-20" style="background-color: #eef7ea;border-color: #c9e2b3;border-left-color: rgb(201, 226, 179);color: #3c763d;padding: 15px 30px 15px 0px;border-left: 5px solid #C9E2B3;">';
					} else {
						html += '	<div class="col-md-20">';
					}
					html += '		<div class="form-group">';
					html += '			<label class="control-label col-md-1">'+serailArr[i]+'：</label>';
					html += '			<div class="col-md-20 form-control-static">';
//					html += '				<p class="form-control-static">'+test.options[i].option+'</p>';
					html += 				test.options[i].option;
					html += '			</div>';
					html += '		</div>';
					html += '	</div>';
					html += '</div>';
				}
				$("#test_question_detail_form").find(".form-body").html(html);
			} else {
				Msg.alert(msg.msg);
			}
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

/**
 * 表单提交前将CKEditor中的内容放到指定的文本域中
 * @param form
 */
function checkSaveTestQuestionForm(form) {
	var trunk = CKEDITOR.instances.trunk.getData();
	if($.trim(trunk)) { // 题干
		$("#div_trunk_editor_container").find("textarea[name='trunk']").val(trunk);
	}
	var option_a = CKEDITOR.instances.option_a.getData();
	if($.trim(option_a)) { // option_a
		$("#div_option_a_editor_container").find("textarea[name='option_a']").val(option_a);
	}
	var option_b = CKEDITOR.instances.option_b.getData();
	if($.trim(option_b)) { // option_b
		$("#div_option_b_editor_container").find("textarea[name='option_b']").val(option_b);
	}
	var option_c = CKEDITOR.instances.option_c.getData();
	if($.trim(option_c)) { // option_c
		$("#div_option_c_editor_container").find("textarea[name='option_c']").val(option_c);
	}
	var option_d = CKEDITOR.instances.option_d.getData();
	if($.trim(option_d)) { // option_d
		$("#div_option_d_editor_container").find("textarea[name='option_d']").val(option_d);
	}
	var option_e = CKEDITOR.instances.option_e.getData();
	if($.trim(option_e)) { // option_e
		$("#div_option_e_editor_container").find("textarea[name='option_e']").val(option_e);
	}
	var option_f = CKEDITOR.instances.option_f.getData();
	if($.trim(option_f)) { // option_f
		$("#div_option_f_editor_container").find("textarea[name='option_f']").val(option_f);
	}
}

/**
 * “添加”按钮点击时初始化学科下拉选择框
 */
function initSelects() {
	/* 加载学科选项列表 */
	getSubjectList();
}

$(function(){
	/** 加载试题数据列表 */
	loadTestQuestions();
	
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
	
	
	/** 添加试题 */
    $('#test_question_add_form').validate({
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
        	difficulty: {
        		required: true
        	},
        	trunk: {
                required: true
            },
            option_a: {
            	required: true
            },
            option_b: {
                required: true
            },
            option_c: {
                required: true
            },
            option_d: {
                required: true
            },
            answer: {
                required: true
            },
            type: {
            	required: true
            },
            point: {
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
				url : ctx + '/testQuestion/saveTestQuestion.action',
				type : 'POST',
				data : $(form).serialize(),
				dataType : 'json',
				success : function (msg){
					if(msg.key == 'success'){
						loadTestQuestions();
						$("button[data-dismiss='modal']").click();
						$('#test_question_add_form')[0].reset();
					}
					Msg.alert(msg.msg);
				}, error : function(msg){
					Msg.alert('网络异常，请刷新重试');
				}
			});
        }
    });
    /* 绑定表单回车事件 */
    $('[data-toggle="save"]').live("click", function(e) {
    	checkSaveTestQuestionForm();
    	if ($('#test_question_add_form').validate().form()) {
			$('#test_question_add_form').submit();
		}
		return false;
    });
    /* 绑定表单回车事件 */ 
    $('#test_question_add_form input').keypress(function(e) {
        if (e.which == 13) {
        	checkSaveTestQuestionForm();
            if ($('#test_question_add_form').validate().form()) {
                $('#test_question_add_form').submit();
            }
            return false;
        }
    });
});

