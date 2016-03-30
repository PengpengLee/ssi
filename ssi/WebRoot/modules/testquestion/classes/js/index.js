var class_id; // 班级ID
function loadClasses() {
	// 分页参数：当前页
	var params = "page=" + Paging.page;
	var ss = $("#ipt_classes").val();
	if($.trim(ss)) {
		params += '&name=' + ss;
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
					
					var oprate = '';
					var status = classes.status;
					switch(status) {
					case '0':
						status = '未开班';
						oprate = '<a href="#" onclick="updateStatus(\''+ id +'\', 1)">开班</a>';
						break;
					case '1':
						status = '已开班';
						oprate = '<a href="#" onclick="updateStatus(\''+ id +'\', 2)">结课</a>';
						break;
					case '2':
						status = '已结课';
						 oprate = '无';
						break;
					}
					
					trs += '<tr class="odd gradeX" onclick="showClassesUsers(\''+ id +'\')" title="点击显示班级成员">';
					trs += '<td><div class="checker"><span><input type="checkbox" class="checkboxes" value="1" id="td_'+id+'"></span></div></td>';
					trs += '<td>' + name + '</td>';
					trs += '<td>' + type + '</td>';
					trs += '<td>' + subjectName + '</td>';
					trs += '<td>' + phaseNumber + '</td>';
					trs += '<td>' + status + '</td>';
					trs += '<td>' + oprate + '</td>';
					trs += '</tr>';
				}
			} else {
				trs = '<tr class="odd gradeX"><td colspan="7" align="center">(没有数据)</td></tr>';
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
 * 获取班级用户
 */
function showClassesUsers(classesId) {
	var ss = '';
	if(classesId) { // 点击班级行，显示班级用户
		Paging.page = 1;
		class_id = classesId;
	} else {
		ss = $("#ipt_classes_user").val();
	}
	// 获取数据
	getClassesUserList("tbl_classes_user", class_id, true, ss, showClassesUsers);
}


/**
 * 按班级成员名称搜索
 */
function checkReloadClassesUserForm(form) {
	Paging.page = 1;
	showClassesUsers();
	return false;
}

/**
 * 获取非班级用户列表
 */
function showNotInClassesUsers() {
	if(!class_id) return;
	var ss = $("#ipt_add_classes_users").val();
	// 获取数据
	getClassesUserList("tbl_add_classes_user", class_id, false, ss, showNotInClassesUsers);
}

/**
 * 按非班级成员名称搜索
 */
function checkReloadNotInClassesUserForm(form) {
	Paging.page = 1;
	showNotInClassesUsers();
	return false;
}

/**
 * 获取班级用户数据
 * @param tableId 表格ID
 * @param class_id 班级ID
 * @param flag true-班级用户；false-非班级用户
 * @param ss 搜索条件
 * @param callback 回调函数
 * @author lipengpeng@itcast.cn
 * @date 2015-01-01
 */
function getClassesUserList(tableId, class_id, flag, ss, callback) {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/classes/getClassesUserList.action',
		data : {id: class_id, flag: flag, ss: ss, page: Paging.page},
		success : function(data) {
			Paging.init(tableId + "_container", data, callback);
			var tbody = $("#" + tableId).find("tbody");
			var dataList = data.data;
			
			var trs = "";
			if(dataList && dataList.length > 0) {
				for(var i = 0; i < dataList.length; i++) {
					var classesUser = dataList[i];
					var userId = classesUser.userId;
					var name = classesUser.name;
					var mobile = classesUser.mobile;
					var createTime = classesUser.createTime;
					var userType = classesUser.userType;
					userType = userType > 10 ? "学员" : "老师";
					
					trs += '<tr class="odd gradeX">';
					trs += '<td><div class="checker"><span><input type="checkbox" class="checkboxes" value="1" id="td_'+userId+'"></span></div></td>';
					trs += '<td>' + name + '</td>';
					trs += '<td>' + mobile + '</td>';
					trs += '<td>' + userType + '</td>';
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
}

/**
 * 根据班级ID更新班级状态
 * @param classId
 * @param status
 */
function updateStatus(classId, status) {
	if(!classId) return;
	if(!status) return;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/classes/updateStatus.action',
		data : {id: classId, status: status},
		success : function(msg) {
			if(msg.key == "success") {
				reloadClasses();
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
 * 添加班级用户，支持添加多个
 */
function addClassesUsers() {
	var flag = false;
	var tableId = "tbl_add_classes_user";
	var ids = getCheckedRowIds(tableId);
	if(!ids) return;
	if(!class_id) return;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/classes/saveClassesUser.action',
		data : {id: class_id, reserveField1: ids},
		success : function(msg) {
			if(msg.key == "success") {
				checkReloadClassesUserForm();
				checkReloadNotInClassesUserForm();
			}
			//Msg.alert(msg.msg);
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

/**
 * 页面加载时获取班级数据
 */
function reloadClasses() {
	$("a[reloadTbl='tbl_classes']").click();
}

/**
 * 删除班级或班级用户，支持批量删除
 */
function deleteClasses(tableId, action) {
	var ids = getCheckedRowIds(tableId);
	if(!ids) return;
	Msg.confirm("确认删除？", function(){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : ctx + '/classes/'+ action +'.action',
			data : {id: ids, reserveField1: class_id},
			success : function(msg) {
				if(msg.key == "success") {
					// 重新加载班级数据列表
					$("a[reloadTbl='"+ tableId +"']").click();
					// 重新加载班级用户列表
					showClassesUsers(class_id);
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
 * “添加”按钮点击时初始化学校、学科下拉选择框
 */
function initSelects() {
	/* 加载学校选项列表 */
	getSchoolList();
	/* 加载学科选项列表 */
	getSubjectList();
	
}

$(function(){
	/** 加载班级数据列表 */
	loadClasses();
	
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
	
	/** 页面加载时为搜索文本框绑定事件 */
	$(".margin-top-10").keypress(function(e) {
		var val = $(this).find("input").val();
		checkReloadNotInClassesUserForm();
	});
	$(".fa-search").click(function(e) {
		var val = $(this).find("input").val();
		showClassesUsers(null, $.trim(val));
	});
	
	/** 页面加载时为表格的行tr设置点击样式 */
	$(".gradeX").live("click", function(){
		$(this).parent().find(".gradeX").each(function(){
			$(this).attr("style", "");
		});
		$(this).attr("style", "background-color:silver;");
	});
	
	/** 添加班级 */
    $('#classes_add_form').validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",
        rules: {
            name: {
                required: true
            },
            schoolId: {
            	required: true
            },
            subjectId: {
                required: true
            },
            type: {
                required: true
            },
            phaseNumber: {
                required: true
            },
            serialNumber: {
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
				url : ctx + '/classes/saveClasses.action',
				type : 'POST',
				data : $(form).serialize(),
				dataType : 'json',
				success : function (msg){
					if(msg.key == 'success'){
						loadClasses();
						$("button[data-dismiss='modal']").click();
						$('#classes_add_form')[0].reset();
					} else {
						Msg.alert(msg.msg);
					}
				}, error : function(msg){
					Msg.alert('网络异常，请刷新重试');
				}
			});
        }
    });
    /* 绑定表单回车事件 */ 
    $('#classes_add_form input').keypress(function(e) {
        if (e.which == 13) {
            if ($('#classes_add_form').validate().form()) {
                $('#classes_add_form').submit();
            }
            return false;
        }
    });
    /* 绑定表单回车事件 */ 
    $('#classes_user_add_form input').keypress(function(e) {
    	if (e.which == 13) {
    		if ($('#classes_user_add_form').validate().form()) {
    			$('#classes_user_add_form').submit();
    		}
    		return false;
    	}
    });
    /* 为添加班级页面初始化文本框数据 */
    var date = new Date();
    var dayOfMonth = date.getDate();
    var month = date.getMonth() + 1;
    month = month < 10 ? '0' + month : month;
    
    $('#classes_add_form').find("[name='phaseNumber']").val(dayOfMonth);
    $('#classes_add_form').find("[name='serialNumber']").val(month + "" + dayOfMonth);
    var classesName = $('#classes_add_form').find("[name='name']");
    classesName.val(classesName.val() + '第' + dayOfMonth + "期" + month + dayOfMonth + "班" );
    
    /* 自动生成班级名称 */ 
    $('#classes_add_form input,select').live("change", function(e) {
    	var school = $("[name='schoolId']").val();
    	if(school) {
    		school = $("[name='schoolId']").find("option[value='"+school+"']").text();
    	}
    	var subject = $("[name='subjectId']").val();
    	if(subject) {
    		subject = $("[name='subjectId']").find("option[value='"+subject+"']").text();
    	}
    	var type = $('#classes_add_form').find("[name='type']");
    	type = type.find("option[value='"+type.val()+"']").text();
    	var phaseNumber = $('#classes_add_form').find("[name='phaseNumber']").val();
    	var serialNumber = $('#classes_add_form').find("[name='serialNumber']").val();
    	var name = school+subject+type+"第"+phaseNumber+"期"+serialNumber+"班";
    	$('#classes_add_form').find("[name='name']").val(name);
    });
    
  
});

