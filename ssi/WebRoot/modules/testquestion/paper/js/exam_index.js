var TEST_PAPER; // 考试试卷
function loadTestPapers() {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/testPaper/getTestPaperForExam.action',
		data : {},
		success : function(data) {
			if(data.key == "success") {
				var testPaper = data.obj; // 试卷对象
				TEST_PAPER = testPaper;
				var dataList = testPaper.testQuestionList; // 试题列表
				var tests = "";
				if(dataList && dataList.length > 0) {
					for(var i = 0; i < dataList.length; i++) {
						var vo = dataList[i];
						var id = vo.id;
						var trunk = vo.trunk; // 题干
						var type = vo.type; // 题干
						var options = vo.options; // 选项列表
						var opts = ["A", "B", "C", "D", "E", "F"];
						
						tests += '<div id="div_'+id+'" class="panel panel-info">'; // primary, danger, success, warning, default
						tests += '	<div class="panel-heading" style="background-color: #7BC9F1;">';
						tests += '		<h4 class="panel-title">';
						tests += '		<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion_container" href="#accordion_'+id+'">';
						tests += '		<table><tbody><tr>';
						tests += '			<td valign="top">'+ (i + 1) +'.&nbsp;</td>';
						tests += '			<td>' + trunk + '</td>';
						tests += '		</tr></tbody></table>';
						//tests += '		' + trunk;
						
						tests += '		</a>';
						tests += '		</h4>';
						tests += '	</div>';
//						if(i == 0) {
							tests += '	<div id="accordion_'+id+'" class="panel-collapse collapse in">';
//						} else {
//							tests += '	<div id="accordion_'+id+'" class="panel-collapse collapse">';
//						}
						tests += '		<div class="panel-body">';
						tests += '			<ul id="ul_'+id+'" class="ver-inline-menu tabbable margin-bottom-10">';
						
						for(var j = 0; j < options.length; j++) {
							var option = options[j];
							tests += '				<li id="li_'+option.id+'">';
							tests += '					<a>';
							var oo = option.option;
							if(oo.length < 100) {
								$("#div_default_hidden").html(oo);
								oo = $("#div_default_hidden").text();
							}
							tests += '					<i>'+ opts[j] +'</i> ' + oo + ' </a>';
							tests += '				</li>';
						}

						tests += '			</ul>';
						tests += '		</div>';
						tests += '	</div>';
						tests += '</div>';
					}
				}
				$("#accordion_container").html(tests);
				// 追加点击事件
				$("#accordion_container").find("li").die().live("click", function(){
					$(this).toggleClass("active");
					// 每次点击之后将选中的ID存储在本地
					storageCheckedOptionIds();
				});
				// 初始化已选择的选项
				initCheckedOptions(testPaper.examTime);
			} else {
				Msg.alert(data.msg);
			}
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
	return false;
}

/**
 * 提交试卷，获取分数
 */
function getTestPoints() {
	// 获取选择的试题
	var ids = '';
	var divTestList = $("#accordion_container").find(".panel-info");
	for(var i = 0; i < divTestList.length; i++) {
		var test = divTestList[i];
		var testId = test.id.substring(4); // 试题ID
		var checkedLi = $(test).find(".active")[0];
		if(checkedLi) { // 有选中的选项
			var optIds = '';
			$(test).find(".active").each(function(){
				optIds += ',' + $(this).attr("id").substring(3);
			});
			var optId = optIds.substring(1);
			ids += ';' + testId + "=" + optId;
		} else {
			var _href = location.href;
			_href = _href.substring(0, _href.indexOf("?"));
			location.href = _href+'#'+ divTestList[i].id;
//			$("#accordion_" + testId).click();
//			Msg.alert("您还有题没做");
			return;
		}
	}
	
	if(ids == '') return;
	
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/testPaper/getTestPoints.action',
		data : {id: ids.substring(1)},
		success : function(msg) {
			if(msg.key == "success") {
				Msg.alert(msg.msg, function() {
					window.location.href = ctx + '/index.action';
				});
			} else {
				Msg.alert(msg.msg);
			}
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

/** 页面初始化时为表单对象赋值 */
function initCheckedOptions(examTime) {
	if (window.localStorage) {
		var ids = '';
		var currentTime = new Date().getTime();
		var fiveDays = 5 * 24 * 60 * 60 * 1000; // 本地缓存保留五天时间。单位：毫秒
		if(currentTime - Number(examTime) >  fiveDays) {
			localStorage.removeItem('ids');
			return;
		} else {
			ids = localStorage.getItem('ids');
		}
		
		if (ids) {
			var testAndOptionArr = ids.split(";"); // testId=optionID1,optionID3;testId=optionID3;testId=optionID1,optionID2,optionID3
			for ( var i = 0; i < testAndOptionArr.length; i++) {
				var testAndOption = testAndOptionArr[i]; // testId=optionID1,optionID2,optionID3
				var testId = testAndOption.split('=')[0]; // 试题ID
				var optIds = testAndOption.split('=')[1]; // 选项IDs
				if (optIds && optIds.length > 32) { // 选择了多个选项
					var optArr = optIds.split(",");
					for ( var j = 0; j < optArr.length; j++) {
						$("#div_" + testId).find("#li_" + optArr[j]).addClass("active");
					}
				} else {
					$("#div_" + testId).find("#li_" + optIds).addClass("active");
				}
			}
		}
	}
}

/**
 * 存储已选择的选项IDs到浏览器本地缓存中，防止刷新页面后选项丢失
 */
function storageCheckedOptionIds() {
	if (typeof localStorage === 'object') {
	    try {
	    	/* 获取已选择的选项IDs */
	    	var ids = '';
	    	var divTestList = $("#accordion_container").find(".panel-info");
	    	for(var i = 0; i < divTestList.length; i++) {
	    		var test = divTestList[i];
	    		var testId = test.id.substring(4); // 试题ID
	    		var checkedLi = $(test).find(".active")[0];
	    		if(checkedLi) { // 有选中的选项
	    			var optIds = '';
	    			$(test).find(".active").each(function(){
	    				optIds += ',' + $(this).attr("id").substring(3);
	    			});
	    			var optId = optIds.substring(1);
	    			ids += ';' + testId + "=" + optId;
	    		} else {
	    			ids += ';' + testId + "=" + optId;
	    		}
	    	}
	    	// 将数据存储到本地缓存
	        localStorage.setItem('ids', ids);
	        // localStorage.removeItem('ids'); // 移除本地缓存
	    } catch (e) {
	        Storage.prototype._setItem = Storage.prototype.setItem;
	        Storage.prototype.setItem = function() {};
	        //Msg.alert('您正在使用无痕浏览，将无法使用本地存储功能保存数据');
	    }
	} 
}

/**
 * 右键刷新页面
 */
function refreshPage() {
	//window.location.href = window.location.href;
	location.reload();
}

$(function(){
	/** 加载试卷数据列表 */
	loadTestPapers();
	
	/** 追加提交按钮 */ 
//	$(".page-footer").append('<div class="scroll-to-middle success" style="display:block;"><i class="icon-action-redo" title="提交" onclick="getTestPoints()"></i></div>');
//	$(".page-footer").append('<div class="scroll-to-middle success" style="display:block;"><i class="icon-action-redo" title="提交" onclick="getTestPoints()"></i></div>');
	$(".page-footer").append('<div class="ti" title="提交"><a class="sigined" style="background: url('+ctx+'/modules/testquestion/paper/img/ti.png) no-repeat center center;"'+
			'href="Javascript:void(0);" onclick="getTestPoints()"></a></div>');
	
});

