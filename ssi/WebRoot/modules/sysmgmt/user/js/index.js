
/**
 * 加载用户列表
 * @param ss 搜索参数
 */
function loadData() {
	var params = "page=" + Paging.page;
	var ss = $("#form_control_1").val();
	if(ss) {
		params += "&ss=" + ss;
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/user/getUserLsit.action',
		data : params,
		success : function(data) {
			Paging.init("sample_2_wrapper", data, loadData);
			var tbody = $("#sample_editable_1").find("tbody");
			var dataList = data.data;
			var trs = "";
			for(var i = 0; i < dataList.length; i++) {
				var uservo = dataList[i];
				var username = uservo.username;
				var name = uservo.name;
				var mobile = uservo.mobile;
				var createTime = uservo.createTime;
				var id = uservo.id;
				var userType = uservo.userType;
				switch(userType) {
				case '1':
					userType = '系统管理员';
					break;
				case '5':
					userType = '权限管理员';
					break;
				case '10':
					userType = '管理员';
					break;
				case '20':
					userType = '普通用户';
					break;
				default :
					userType = '非法数据';
					break;
				}
				trs += '<tr class="odd gradeX" id="tr_'+ id +'">';
				trs += '<td column="none">' + id + '</td>';
				trs += '<td>' + username + '</td>';
				trs += '<td>' + name + '</td>';
				trs += '<td>' + mobile + '</td>';
				trs += '<td>' + userType + '</td>';
				trs += '<td>' + createTime + '</td>';
				trs += '<td>';
				// trs += '<a class="edit" href="javascript:;">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;';
				trs += '<a class="delete" href="javascript:;" onclick="deleteUser(\''+id+'\')">Delete</a>';
				trs += '</td>';
				trs += '</tr>';
			}
			tbody.html(trs);
			// 隐藏ID列
			$("[column='none']").css({"display": "none"});
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

/**
 * 删除用户
 */
function deleteUser(id) {
	Msg.confirm("确定要删除吗？", function(){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : ctx + '/user/delUser.action',
			data : {id: id},
			success : function(msg) {
				if(msg.key == "success"){
					$(".reload").click();
				} else {
					Msg.alert(msg.msg);
				}
			},
			error : function(msg) {
				Msg.alert("网络异常，请刷新重试");
			}
		});
	});
}

/**
 * 按用户名/姓名搜索用户
 */
function checkForm(form) {
	Paging.page = 1;
	loadData();
	return false;
}


$(function(){
	// initiate layout and plugins
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	// 页面加载时初始化用户数据
    $(".reload").click();
	/** 页面加载时为表格的行tr设置点击样式 */
	$(".gradeX").live("click", function(){
		$(this).parent().find(".gradeX").each(function(){
			$(this).attr("style", "");
		});
		$(this).attr("style", "background-color:silver;");
	});
});
