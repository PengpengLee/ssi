
/**
 * 根据学科ID获取班级列表
 * @param subjectId 学科ID
 */
function getClassesList(subjectId) {
	if(!subjectId) return;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/classes/getAllClasses.action',
		data : {subjectId: subjectId},
		success : function(msg) {
			if(msg.key == "success") {
				/** 重新初始化班级下拉框 */
				var classes = msg.obj;
				var selHtml = '<option value="">--请选择班级--</option>';
				if(classes) {
					for(var i = 0; i < classes.length; i++) {
						selHtml += '<option value="'+classes[i].id+'">'+classes[i].name+'</option>';
					}
				}
				$("[name='classesId']").html(selHtml);
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
 * 根据班级ID获取该班级已发布试卷列表
 * @param classesId 学科ID
 */
function getTestPaperList(classesId) {
	if(!classesId) return;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/testPaper/getByClassesId.action',
		data : {classesId: classesId},
		success : function(msg) {
			if(msg.key == "success") {
				/** 重新初始化试卷下拉框 */
				var testPaperList = msg.obj; 
				var selHtml = '<option value="">--请选择试卷--</option>';
				if(testPaperList) {
					for(var i = 0; i < testPaperList.length; i++) {
						selHtml += '<option value="'+testPaperList[i].id+'">'+testPaperList[i].name+'</option>';
					}
				}
				$("[name='testPaperId']").html(selHtml);
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
 * 获取指定班级、指定试卷的考试结果：
 * 1.考试总分列表；
 * 2.试题错误统计列表；
 * @returns
 */
function getExamStatics() {
	var classesId = $("[name='classesId']").val();
	var testPaperId = $("[name='testPaperId']").val();
	
	/* 总分统计 */
	getExamPointsList(classesId, testPaperId);
	/* 错误统计 */
	getTestPaperById(testPaperId);
}

/**
 * 根据班级ID、试卷ID获取考试成绩列表
 */
function getExamPointsList(classesId, testPaperId) {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/statics/getExmaPointsList.action',
		data : {classesId: classesId, testPaperId: testPaperId},
		success : function(msg) {
			if(msg.key == "success") {
				var dataList = msg.obj;
				for(var i = 0; i < dataList.length; i++) {
					$("#tbl_exam_points").addClass("table-bordered");
					var tbody = $("#tbl_exam_points").find("tbody");
					var trs = "";
					trs += '<tr style="background-color: #908F8F;"><td colspan="3" align="center">'+dataList[0].classesName+'</td></tr>';
					trs += '<tr style="background-color: #E87E04;"><td align="center"><b>名次</b></td><td align="center"><b>姓名</b></td><td align="center"><b>分数</b></td></tr>';
					if(dataList && dataList.length > 0) {
						EXAM_LIST_SIZE = dataList.length;
						var lastPoints = 0;
						var lastSerailNum = 1; // 名次
						var sum = 0.0; // 总分
						for(var i = 0; i < EXAM_LIST_SIZE; i++) {
							var vo = dataList[i];
							var id = vo.id;
							var examinerName = vo.examinerName; // 学员姓名
							var examPoints = vo.examPoints; // 考试分数
							examPoints = Number(examPoints);
							sum += examPoints; // 累加总分
							
							/* 根据分数设置名次：必须保证数据是按分数排序的 */
							if(examPoints != lastPoints) {
								lastSerailNum = i + 1;
								lastPoints = examPoints;
							}
							 
							/* 根据不同的分数等级设置不同的背景色 */
							var bgColor = ''; 
							if (examPoints == '100') {
								bgColor = '#1BA39C;';
							} else if (examPoints >= 90) {
								bgColor = '#26C281;';
							} else if (examPoints >= 80) {
								bgColor = '#578ebe;';
							} else if (examPoints >= 70) {
								bgColor = '#F4D03F;';
							} else if (examPoints >= 60) {
								bgColor = '#8E44AD;';
							} else {
								bgColor = '#EF4836;';
							}
							trs += '<tr align="center">';
							trs += '<td style="width:30%;background-color: '+bgColor+'">' + lastSerailNum + '</td>';
							trs += '<td style="width:35%;">' + examinerName + '</td>';
							trs += '<td style="width:35%;">' + examPoints + '</td>';
							trs += '</tr>';
						}
					} else {
						trs = '<tr class="odd gradeX"><td colspan="3" align="center">(没有数据)</td></tr>';
					}
					trs += '<tr style="background-color: #F4D03F;">';
					trs += '<td colspan="3" align="center">参加考试人数：<span style="color:red;">'+EXAM_LIST_SIZE
							+'</span>&nbsp;&nbsp;&nbsp;&nbsp;平均分：<span style="color:red;">'+sum/EXAM_LIST_SIZE+'</span></td>';
					trs += '</tr>';
					tbody.html(trs);
					/*
					 * 班级名：深灰-- #908F8F;
					 * 头部：金-- #E87E04;
					 * 100分：墨绿-- #1BA39C;
					 * 90分以上：浅绿-- #26C281;
					 * 80分以上：浅蓝-- #578ebe;
					 * 70分以上：淡黄-- #F4D03F;
					 * 60分以上：靛蓝-- #8E44AD;
					 * 60分一下：亮红-- #EF4836;
					 * 平均分： 亮黄-- #F4D03F;
					 */
				}
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
 * 根据试卷ID获取试卷对象，包含试题列表；
 * 并把试题展示在树形表中
 * @returns
 */
function getTestPaperById(testPaperId) {
    $.ajax({
    	url: ctx + '/testPaper/getTestPaperById.action',
    	type: 'GET',
    	data: {id: testPaperId},
    	dataType: 'json',
    	success: function(msg) {
    		if(msg.key == 'success') {
    			var testPaper = msg.obj;
    			var testQuestionList = testPaper.testQuestionList;
    			var tblTQList = $("#tbl_test_question_list"); // 试题列表Table
    			if(testQuestionList && testQuestionList.length > 0) {
    				var tbody = tblTQList.find("tbody");
    				var tr = '<tr><th></th><th>题干</th></tr>';
    				for(var i = 0; i < testQuestionList.length; i++) {
    					var testQuestion = testQuestionList[i];
    					tr += '<tr id="tr_tq_'+ testQuestion.id +'">';
    					tr += '<td><span class="row-details row-details-close"></span></td>';
    					tr += '<td>'+ testQuestion.trunk +'</td>';
    					tr += '</tr>';
    					
    					/* 选项列表 */
    					var opts = testQuestion.options;
    					var optSerial = ["A", "B", "C", "D", "E", "F"];
    					tr += '<tr class="details" get=""><td></td><td class="details">';
    					tr += '<table class="table table-striped dataTable table-hover">';
    					for(var j = 0; j < opts.length; j++) {
    						var opt = opts[j];
    						var rightColor = 'green;';
    						if(opt.isTruth == '0') {
    							rightColor = '';
    						}
    						tr += '<tr style="color:'+rightColor+'" id="tr_opt_'+ opt.id +'" isTruth="'+ opt.isTruth +'" title="">';
    						tr += '<td width="2%">'+ optSerial[j] +':</td><td>' + opt.option + '</td>';
    						tr += '<td width="4%" title="选择次数" style="color:red;"><span></span></td>';
    						tr += '</tr>';
    					}
    					tr += '</table>';
    					tr += '</td></tr>';
    				}
    				tbody.html(tr);
    			}
    			tblTQList.addClass("table-bordered");
    			tblTQList.find("tr.details").hide();
    		} else {
    			Msg.alert(msg.msg);
    		}
    	},
    	error: function(msg) {
    		Msg.alert("网络异常，请刷新重试");
    	}
    });
}

/**
 * 获取该试题的错误统计结果
 * @param nTr 试题所属的行
 */
function fnFormatDetails(nTr) {
	var trDetails = $(nTr).next("tr.details");
	if(trDetails.attr("get") == "yes") {
		trDetails.show();
		return;
	}
	var classesId = $("[name='classesId']").val();
	var testPaperId = $("[name='testPaperId']").val();
	var testQuestionId = $(nTr).attr("id").substring(6);
	$.ajax({
		url: ctx + '/statics/getExamDetailByTestQuestionId.action',
		data : {
			classesId : classesId,
			testPaperId : testPaperId,
			testQuestionId : testQuestionId
		},
		dataType: 'json',
		success: function(msg) {
			var table = trDetails.find("table");
			var errorListLen = msg.obj.length;
			for(var i = 0; i < errorListLen; i++) {
				var user = msg.obj[i]; // 错误用户
				var userName = user.examinerName; // 错误用户姓名
				var checkedIds = user.checkedPointsIds; // 错误用户选择的选项，可能含有多个
				if(checkedIds.indexOf(",") != -1) { // 包含多个选项
					var checked = checkedIds.split(",");
					for(var j = 0; j < checked.length; j++) {
						var opt = checked[j]; // 错误用户选择的选项
						var title = table.find("#tr_opt_" + opt).attr("title");
						if(title == "") {
							table.find("#tr_opt_" + opt).attr("title", userName);
						} else {
							table.find("#tr_opt_" + opt).attr("title", title + ","+ userName);
						}
						var errorCount = table.find("#tr_opt_" + opt).last("td").find("span").text();
						if(errorCount == '') {
							table.find("#tr_opt_" + opt).last("td").find("span").text("1");
						} else {
							table.find("#tr_opt_" + opt).last("td").find("span").text(Number(errorCount) + 1);
						}
					}
				} else {
					var title = table.find("#tr_opt_" + checkedIds).attr("title");
					if(title == "") {
						table.find("#tr_opt_" + checkedIds).attr("title", userName);
					} else {
						table.find("#tr_opt_" + checkedIds).attr("title", title + ","+ userName);
					}
					var errorCount = table.find("#tr_opt_" + opt).last("td").find("span").text();
					if(errorCount == '') {
						table.find("#tr_opt_" + checkedIds).last("td").find("span").text("1");
					} else {
						table.find("#tr_opt_" + checkedIds).last("td").find("span").text(Number(errorCount) + 1);
					}
				}
			}
			var trTotal = '<tr><td colspan="3" align="left">错误人次：<span style="color:red;">'+errorListLen
					+'</span>&nbsp;&nbsp;&nbsp;&nbsp;错误率（错误人数/总人数）：<span style="color:red;">'+ errorListLen/EXAM_LIST_SIZE * 100 +'%</span></td></tr>';
			table.find("tbody").append(trTotal);
			trDetails.attr("get", "yes");
			trDetails.show();
		},
		error: function(msg){
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

$(function(){
    /* 加载学科选项列表 */
	getSubjectList();
	
	$('#tbl_test_question_list').on('click', ' tbody td .row-details', function () {
        var nTr = $(this).parents('tr')[0];
        var thisClass = $(this).attr("class");
        if (thisClass.indexOf("row-details-close") != -1) { // 关闭状态
        	/* Open this row */
            $(this).removeClass("row-details-close").addClass("row-details-open");
            fnFormatDetails(nTr);
        } else { // 打开状态
        	/* This row is already open - close it */
        	$(this).addClass("row-details-close").removeClass("row-details-open");
        	$(nTr).next("tr.details").hide();
        	//$(nTr).next("tr.details").remove();
        }
    });
});

