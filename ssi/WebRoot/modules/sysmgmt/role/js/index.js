var role_id; // 角色ID
function loadData() {
	// 分页参数：当前页
	var params = "page=" + Paging.page;
	var ss = $("#ipt_role").val();
	if($.trim(ss)) {
		params += '&ss=' + ss;
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/role/getRoleList.action',
		data : params,
		success : function(data) {
			Paging.init("tbl_role_container", data, loadData);
			var tbody = $("#tbl_role").find("tbody");
			var dataList = data.data;
			
			var trs = "";
			if(dataList && dataList.length > 0) {
				for(var i = 0; i < dataList.length; i++) {
					var role = dataList[i];
					var id = role.id;
					var name = role.name;
					var type = role.code;
					switch(type) {
					case '1':
						type = '总监';
						break;
					case '5':
						type = '主管';
						break;
					case '10':
						type = '老师';
						break;
					case '20':
						type = '学员（默认）';
						break;
					default :
						type = '非法数据';
						break;
					}
					
					trs += '<tr class="odd gradeX" onclick="showRoleUsers(\''+ role.code +'\')" title="点击显示角色成员">';
					// trs += '<td><div class="checker"><span><input type="checkbox" class="checkboxes" value="1" id="td_'+id+'"></span></div></td>';
					trs += '<td>' + name + '</td>';
					trs += '<td>' + type + '</td>';
					trs += '</tr>';
				}
			} else {
				trs = '<tr class="odd gradeX"><td colspan="3" align="center">(没有数据)</td></tr>';
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
 * 按角色名称搜索
 */
function checkReloadDataForm(form) {
	Paging.page = 1;
	loadData();
	return false;
}

/**
 * 获取角色用户列表
 */
function showRoleUsers(id) {
	var ss = '';
	if(id) { // 点击角色行，显示角色用户
		Paging.page = 1;
		role_id = id;
	} else {
		ss = $("#ipt_role_user").val();
	}
	// 获取数据
	getRoleUserList("tbl_role_user", role_id, true, ss, showRoleUsers);
}

/**
 * 按角色成员名称搜索
 */
function checkRoleUsersForm(form) {
	Paging.page = 1;
	showRoleUsers();
	return false;
}

/**
 * 获取非角色用户列表
 */
function showNotInRoleUsers() {
	if(!role_id) return;
	if(role_id == '20' || role_id == '1') role_id = '100';
	var ss = $("#ipt_add_role_users").val();
	// 获取数据
	getRoleUserList("tbl_add_role_user", role_id, false, ss, showNotInRoleUsers);
}

/**
 * 按非角色成员名称搜索
 */
function checkReloadNotInRoleUserForm(form) {
	Paging.page = 1;
	showNotInRoleUsers();
	return false;
}

/**
 * 获取角色用户数据
 * @param tableId 表格ID
 * @param role_id 角色ID
 * @param flag true-角色用户；false-非角色用户
 * @param ss 搜索条件
 * @param callback 回调函数
 * @author lipengpeng@itcast.cn
 * @date 2015-01-01
 */
function getRoleUserList(tableId, role_id, flag, ss, callback) {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/user/getUserLsit.action',
		data : {flag: flag, userType: role_id, name: ss, page: Paging.page},
		success : function(data) {
			Paging.init(tableId + "_container", data, callback);
			var tbody = $("#" + tableId).find("tbody");
			var dataList = data.data;
			
			var trs = "";
			if(dataList && dataList.length > 0) {
				for(var i = 0; i < dataList.length; i++) {
					var roleUser = dataList[i];
					var userId = roleUser.id;
					var name = roleUser.name;
					var mobile = roleUser.mobile;
					var userType = roleUser.userType;
					switch(userType) {
					case '1':
						userType = '总监';
						break;
					case '5':
						userType = '主管';
						break;
					case '10':
						userType = '老师';
						break;
					case '20':
						userType = '学员';
						break;
					default :
						userType = '非法数据';
						break;
					}
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
 * 添加角色用户，支持添加多个
 */
function addRoleUsers() {
	var flag = false;
	var tableId = "tbl_add_role_user";
	var ids = getCheckedRowIds(tableId);
	if(!ids) return;
	if(!role_id) return;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/role/saveRoleUsers.action',
		data : {code: role_id, id: ids},
		success : function(msg) {
			if(msg.key == "success") {
				checkRoleUsersForm();
				checkReloadNotInRoleUserForm();
			}
			Msg.alert(msg.msg);
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

/**
 * 删除角色或角色用户，支持批量删除
 */
function delRoleUsers(tableId, action) {
	var ids = getCheckedRowIds(tableId);
	if(!ids) return;
	Msg.confirm("确认删除？", function(){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : ctx + '/role/delRoleUsers.action',
			data : {id: ids, code: role_id},
			success : function(msg) {
				if(msg.key == "success") {
					checkRoleUsersForm();
				} else if(msg.key == 'error') {
					Msg.alert(msg.msg);
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

$(function(){
	/** 加载角色数据列表 */
	loadData();
	
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
	});
	
	/** 添加角色 */
	
    /** 绑定表单回车事件 */ 
    $('#role_user_form input').keypress(function(e) {
        if (e.which == 13) {
            if ($('#role_user_form').validate().form()) {
                $('#role_user_form').submit();
            }
            return false;
        }
    });
    $('#role_user_add_form>input').keypress(function(e) {
    	if (e.which == 13) {
    		if ($('#role_user_add_form').validate().form()) {
    			$('#role_user_add_form').submit();
    		}
    		return false;
    	}
    });
});

