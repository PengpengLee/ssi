function loadData(ss) {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/subject/getSubjectList.action',
		data : {},
		success : function(data) {
			var tbody = $("#sample_2").find("tbody");
			var dataList = data.obj;
			
			var trs = "";
			if(dataList && dataList.length > 0) {
				for(var i = 0; i < dataList.length; i++) {
					var _data = dataList[i];
					var id = _data.id;
					var name = _data.name;
					var createTime = _data.createTime;
					trs += '<tr class="odd gradeX" id="tr_'+ id +'">';
					trs += '<td><div class="checker"><span><input type="checkbox" class="checkboxes" id="td_'+id+'"></span></div></td>';
					trs += '<td>' + name + '</td>';
					trs += '<td>' + createTime + '</td>';
					trs += '<td>';
					trs += '<a href="#data-add" data-toggle="modal" onclick="getById(\''+id+'\')">修改</a>&nbsp;&nbsp;';
					trs += '<a href="javascript:void(0);" onclick="deleteData(\''+id+'\')">删除</a>';
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
 * 根据ID获取学科
 * @param id
 */
function getById(id) {
	if(!id) return;
	if(id.length != 32) return;
	$.ajax({
		url: ctx + '/subject/getSubjectById.action',
		type: 'GET',
		data: {id: id},
		dataType: 'json',
		success: function(msg) {
			if(msg.key == 'success') {
				var subject = msg.obj;
				$("#add_data").find("[name='name']").val(subject.name);
				$("#add_data").find("[name='code']").val(subject.code);
				$("#add_data").find("[name='id']").val(subject.id);
				$("#add_data").find("[name='createTime']").val(subject.createTime);
				$("#add_data").find("[name='del']").val(subject.del);
				
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
	Msg.confirm("确认删除？", function() {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : ctx + '/subject/delSubject.action',
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
	
	/** 添加班级 */
    $('#add_data').validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",
        rules: {
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
				url : ctx + '/subject/saveSubject.action',
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

