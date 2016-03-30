
/**
 * <p>获取学科列表</p>
 */
function getSubjectList(htmlSubjectName) {
	$.ajax({
		type : "POST",
		url : ctx + '/subject/getSubjectList.action',
		dataType : "json",
		success : function(msg) {
			if(msg.key == "success") { // subjectId
				var subjectName = "subjectId";
				if(htmlSubjectName) {
					subjectName = htmlSubjectName;
				}
				var list = msg.obj;
				var selOptionsHtml = '<option value="">--请选择学科--</option>';
				for ( var i = 0; i < list.length; i++) {
					selOptionsHtml += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
				$("select[name='"+ subjectName +"']").html(selOptionsHtml);
			}
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

/**
 * <p>根据学科获取章节列表</p>
 */
function getChapterList(subjectId, htmlChapterName) {
	if(!subjectId) return;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/subject/getChaptersList.action',
		data : {subjectId: subjectId},
		success : function(msg) {
			if(msg.key == "success") {
				var chapterName = "chapterId";
				if(htmlChapterName) {
					chapterName = htmlChapterName;
				}
				var list = msg.obj;
				var selOptionsHtml = '<option value="">--请选择章节--</option>';
				for ( var i = 0; i < list.length; i++) {
					selOptionsHtml += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
				$("select[name='"+ chapterName +"']").html(selOptionsHtml);
			}
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}

/**
 * <p>获取学校列表</p>
 */
function getSchoolList(htmlSchoolName) {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : ctx + '/school/getSchoolList.action',
		data : {},
		success : function(msg) {
			if(msg.key == "success") {
				var schoolName = "schoolId";
				if(htmlSchoolName) {
					schoolName = htmlSchoolName;
				}
				var list = msg.obj;
				var selOptionsHtml = '<option value="">--请选择学校--</option>';
				for ( var i = 0; i < list.length; i++) {
					selOptionsHtml += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
				$("select[name='"+ schoolName +"']").html(selOptionsHtml);
			}
		},
		error : function(msg) {
			Msg.alert("网络异常，请刷新重试");
		}
	});
}