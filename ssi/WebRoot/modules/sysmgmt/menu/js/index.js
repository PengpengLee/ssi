var class_id; // 班级ID
function loadData(ss) {
	// 分页参数：当前页
	var params = "page=" + Paging.page;
	var ss = $("#form_control_1").val();
	if(ss.trim()) {
		params += '&ss=' + ss;
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/menu/getMenuList.action',
		data : params,
		success : function(data) {
			Paging.init("sample_2_wrapper", data, loadData)
			var tbody = $("#sample_2").find("tbody");
			var dataList = data.data;
			
			var trs = "";
			if(dataList && dataList.length > 0) {
				for(var i = 0; i < dataList.length; i++) {
					var _data = dataList[i];
					var id = _data.id;
					var name = _data.name;
					var actionUrl = _data.actionUrl;
					var roleId = _data.roleId;
					switch(roleId) {
					case "1":
						roleId = '系统管理员';
						break;
					case "5":
						roleId = '主管';
						break;
					case "10":
						roleId = '老师';
						break;
					case "20":
						roleId = '学员';
						break;
					}
					var createTime = _data.createTime;
					var remark = _data.remark;
					trs += '<tr class="odd gradeX" id="tr_'+ id +'">';
					trs += '<td><div class="checker"><span><input type="checkbox" class="checkboxes" id="td_'+id+'"></span></div></td>';
					trs += '<td>' + name + '</td>';
					trs += '<td>' + actionUrl + '</td>';
					trs += '<td>' + roleId + '</td>';
					trs += '<td>' + createTime + '</td>';
					trs += '<td>' + remark + '</td>';
					trs += '<td>';
					trs += '<a href="#menu-add" data-toggle="modal" onclick="getById(\''+id+'\')">修改</a>&nbsp;&nbsp;';
					trs += '<a href="javascript:void(0);" onclick="deleteData(\''+id+'\')">删除</a>';
					trs += '</td>';
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
function checkForm(form) {
	Paging.page = 1;
	loadData();
	return false;
}

/**
 * 页面加载时获取数据列表
 */
function reloadData() {
	$("a[reloadTbl='sample_2']").click();
}

/**
 * 添加按钮点击时重置表单
 */
function resetForm() {
	$("#add_data")[0].reset();
}

/**
 * 根据ID获取菜单
 * @param id
 */
function getById(id) {
	if(!id) return;
	if(id.length != 32) return;
	$.ajax({
		url: ctx + '/menu/getMenuById.action',
		type: 'GET',
		data: {id: id},
		dataType: 'json',
		success: function(msg) {
			if(msg.key == 'success') {
				var menu = msg.obj;
				$("#add_data").find("[name='name']").val(menu.name);
				$("#add_data").find("[name='actionUrl']").val(menu.actionUrl);
				$("#add_data").find("[name='roleId']").val(menu.roleId);
				$("#add_data").find("[name='icon']").val(menu.icon);
				$("#add_data").find("[name='color']").val(menu.color);
				$("#add_data").find("[name='remark']").val(menu.remark);
				
				$("#add_data").find("[name='code']").val(menu.code);
				$("#add_data").find("[name='parentId']").val(menu.parentId);
				$("#add_data").find("[name='type']").val(menu.type);
				$("#add_data").find("[name='serialNum']").val(menu.serialNum);
				$("#add_data").find("[name='id']").val(menu.id);
				$("#add_data").find("[name='createTime']").val(menu.createTime);
				$("#add_data").find("[name='del']").val(menu.del);
				
			} else {
				Msg.alert(msg.msg);
			}
		},
		error: function() {
			// Msg.alert("网络异常，请刷新重试");
		}
	});
}

/**
 * 删除数据，支持批量删除
 */
function deleteData(id) {
	var _id = '';
	if(id) {
		_id = id;
	} else {
		_id = getCheckedRowIds();
	}
	if(!_id) return;
	
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/menu/delMenu.action',
		data : {id: _id},
		success : function(msg) {
			if(msg.key == "success") {
				/** 重新加载数据列表 */
				$("a[reloadTbl='sample_2']").click();
			}
			Msg.alert(msg.msg);
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

function getCheckedRowIds() {
	var ids = '';
	$("#sample_2").find(".checkboxes").each(function(i){
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
	/** 加载班级数据列表 */
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
	
	/** 页面加载时为菜单图标列表初始化 */
	var iconList = ["action-redo","action-undo","anchor","arrow-down","arrow-left","arrow-right","arrow-up","badge","bag","ban","bar-chart","basket","basket-loaded","bell","book-open","briefcase","bubble","bubbles","bulb","calculator","calendar","call-end","call-in","call-out","camcorder","camera","check","chemistry","clock","close","cloud-download","cloud-upload","compass","control-end","control-forward","control-pause","control-play","control-rewind","control-start","credit-card","crop","cup","cursor","cursor-move","diamond","direction","directions","disc","dislike","doc","docs","drawer","drop","earphones","earphones-alt","emoticon-smile","energy","envelope","envelope-letter","envelope-open","equalizer","eye","eyeglasses","feed","film","fire","flag","folder","folder-alt","frame","game-controller","ghost","globe","globe-alt","graduation","graph","grid","handbag","heart","home","hourglass","info","key","layers","like","link","list","lock","lock-open","login","logout","loop","magic-wand","magnet","magnifier","magnifier-add","magnifier-remove","map","microphone","mouse","moustache","music-tone","music-tone-alt","note","notebook","paper-clip","paper-plane","pencil","picture","pie-chart","pin","plane","playlist","plus","pointer","power","present","printer","puzzle","question","refresh","reload","rocket","screen-desktop","screen-smartphone","screen-tablet","settings","share","share-alt","shield","shuffle","size-actual","size-fullscreen","social-dribbble","social-dropbox","social-facebook","social-tumblr","social-twitter","social-youtube","speech","speedometer","star","support","symbol-female","symbol-male","tag","target","trash","trophy","umbrella","user","user-female","user-follow","user-following","user-unfollow","users","vector","volume-1","volume-2","volume-off","wallet","wrench"];
	var options = '';
	for(var i = 0; i < iconList.length; i++) {
		options += '<option value="icon-'+iconList[i]+'">.icon-'+iconList[i]+'</option>';
	}
	$("select[name='icon']").live("change", function(){
		var icon = $(this).val().substring($(this).val().indexOf("-") + 1);
		$(this).parent().find("i").attr("class", "icon-" + icon);
	}).html(options);
	
	/** 页面加载时为菜单配色列表初始化 */
	var colorList = ["green","green-meadow","green-seagreen","green-turquoise","green-haze","green-jungle","green-sharp","green-soft",
	                "grey","ban","grey-steel","grey-cararra","grey-gallery","grey-cascade","grey-silver","grey-salsa","grey-salt","grey-mint",
	                "red","red-pink","red-sunglo","red-intense","red-thunderbird","red-flamingo","red-soft","red-haze",
	                "yellow","yellow-gold","yellow-casablanca","yellow-crusta","yellow-lemon","yellow-saffron",
	                "purple","purple-plum","purple-medium","purple-studio","purple-wisteria","purple-seance","purple-intense","purple-sharp","purple-soft",
	                "blue","blue-madison","blue-chambray","blue-ebonyclay","blue-hoki","blue-steel","blue-soft","blue-dark","blue-sharp"];
	options = '';
	for(var i = 0; i < colorList.length; i++) {
		options += '<option class="dashboard-stat '+colorList[i]+'" value="'+colorList[i]+'">'+colorList[i]+'</option>';
	}
	$("select[name='color']").html(options);
	
	/** 添加班级 */
    $('#add_data').validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",
        rules: {
            name: {
                required: true
            },
            actionUrl: {
            	required: true
            },
            icon: {
            	required: true
            },
            roleId: {
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
				url : ctx + '/menu/saveMenu.action',
				type : 'POST',
				data : $(form).serialize(),
				dataType : 'json',
				success : function (msg){
					if(msg.key == 'success'){
						loadData();
						$("button[data-dismiss='modal']").click();
						$('#add_data')[0].reset();
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
    $('#add_data input').keypress(function(e) {
        if (e.which == 13) {
            if ($('#add_data').validate().form()) {
                $('#add_data').submit();
            }
            return false;
        }
    });
});

