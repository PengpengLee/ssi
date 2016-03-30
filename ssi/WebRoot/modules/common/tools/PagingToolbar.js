/**
 * <p>自定义分页控件</p>
 * 
 * 分页功能分析：
 * 1.分页功能在表格最下面（不属于表格）：
 * 		左侧显示内容为：第 m 到 n 条数据，共 t 条
 * 		右侧显示内容为：“上一页”按钮；页码按钮，最多5个；“下一页”按钮
 * 2.点击分页相关按钮支持表格数据刷新和同步：
 * 		用回调函数实现数据的重新加载，每次加载数据后都重新初始化分页控件
 * 3.分页功能不属于表格，它和表格同属于<div class="dataTables_wrapper no-footer"></div>的子元素
 * 4.该控件支持的功能有：
 * 	A.点击分页数字按钮刷新表格数据，但不刷新页面，支持多表格共存；
 * 	B.页面在第一页和最后一页时，上一页或下一页按钮不可用；
 * 	C.分页数字按钮最多同时展示5个，向后翻页时支持页码滑动；
 * 
 * @description		☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆ 使用说明 ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
 * @description 	初始化：	Paging.init("sample_2_wrapper", data, loadTestQuestions)
 * @description		☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
 * @author lipengpeng@itcast.cn
 * @version 1.0
 * 
 */
var Paging = {
	page: 1, // 当前页
	rows: 10, // 每页记录数
	total: 0, // 总记录数
	pageCount: 0, // 总页数
	pageStart: 1, // 分页按钮开始序号
	pageEnd: 5, // 分页按钮结束序号，最多同时显示5个按钮（不算上一页和下一页）
	data: [], // 数据列表
	outlineMsg: '共 0 条数据', // 概要信息：第 m 到 n 条，共 t 条数据
	callback: function(){}, // 回调函数
	
	/**
	 * 分页功能初始化函数，分页控件的入口
	 *  
	 * @param tableContainerId 必填。table标签的父标签ID
	 * @param data 必填。JSON格式的数据，格式诸如：
	 * 		{
	 * 			page: 1, 
	 * 			rows: 10, 
	 * 			total: 100,
	 * 			data: [
	 * 				{
	 * 				"username":"zhangsan",
	 * 				"password":"123456",
	 * 				"address":"China"
	 * 				},
	 * 				{
	 * 				"username":"zhangsan",
	 * 				"password":"123456",
	 * 				"address":"China"
	 * 				}
	 * 			]
	 * 		}
	 * @param callback 必填。回调函数，用于重新加载数据
	 */
	init: function(tableContainerId, data, callback) { // 解析Json数据
		this.page = data.page;
		this.rows = data.rows;
		this.total = data.total;
		this.pageStart = 1;
		this.pageEnd = 5;
		this.data = data.data;
		this.pageCount = Math.ceil(this.total / this.rows);
		this.outlineMsg = this.total == 0 ? '共 0 条数据' : '第 '+((this.page-1)*this.rows + 1) +' 到 '+ 
				(this.page*this.rows > this.total ? this.total : this.page*this.rows) +' 条，共 '+this.total+' 条数据';

		if($("#page_" + tableContainerId).length > 0) { // 已存在分页工具则移除重新初始化
			$("#page_" + tableContainerId).remove();
		}
		
		if(this.total == 0 || this.pageCount == 0) return; // 数据为空，不初始化分页工具
		
		// 将分页控件追加到table的父对象的最后
		$("#" + tableContainerId).append(this.pageHTML(tableContainerId));
		// 为分页按钮追加点击事件
		this.appendEvent(tableContainerId, callback);
	},
	pageHTML: function(tableContainerId) {
		var html = '<div class="row" id="page_'+tableContainerId+'">\
						<div class="col-md-5 col-sm-12">\
							<div style="display:none;">\
								<input type="hidden" id="paging_page_'+ tableContainerId +'" value="'+ this.page +'">\
								<input type="hidden" id="paging_pageCount_'+ tableContainerId +'" value="'+ this.pageCount +'">\
							</div>\
							<div class="dataTables_info" id="sample_2_info" role="status" aria-live="polite">'+this.outlineMsg+'</div>\
						</div>\
						<div class="col-md-7 col-sm-12">\
							<div class="dataTables_paginate paging_simple_numbers" id="sample_2_paginate">\
								<ul class="pagination">';
		html += 					this.pageBtnList();				
		html += '				</ul>\
							</div>\
						</div>\
					</div>';
		return html;
	},
	pageBtnList: function() {
		if(this.pageCount <= 5) { // 总页数 <= 5
			this.pageEnd = this.pageCount;
		} else { // 总页数大于5时
			if((this.page + 2) <= this.pageCount) { // 总页数超过当前页至少两个，不从1显示。距离：当前页为5，显示3-7的页码
				this.pageStart = this.page - 2 > 0 ? this.page - 2 : 1;
				this.pageEnd = this.page + 2 < 5 ? 5 : this.page + 2; // 有可能点击最前面两页
			} else { // 当前页 + 2超过了总页数，即已经点击到最后两页
				this.pageStart = this.pageCount - 4;
				this.pageEnd = this.pageCount;
			}
		}
		var liHtml = '<li class="paginate_button previous" serail="-1" aria-controls="sample_2" tabindex="0" id="sample_2_previous">'+
				'<a href="javascript:void(0);"><i class="fa fa-angle-left"></i></a></li>';
		for(var i = this.pageStart; i <= this.pageEnd; i++) { // 点击后的样式：active
			var cl = "paginate_button";
			if(this.page == i) {
				cl = "paginate_button active";
			}
			liHtml += '<li class="'+ cl +'" serail="'+i+'" aria-controls="sample_2" tabindex="0"><a href="javascript:void(0);">'+i+'</a></li>';
		}
		liHtml += '<li class="paginate_button next" serail="+1" aria-controls="sample_2"tabindex="0" id="sample_2_next">' +
				'<a href="javascript:void(0);"><i class="fa fa-angle-right"></i></a></li>';
		return liHtml;
	},
	appendEvent: function(tableContainerId, callback) {
		if(Paging.page == 1) { // 第一页
			$("#" + tableContainerId).find(".pagination").find(".previous").addClass("disabled");
		}
		if(Paging.page == Paging.pageCount) { // 最后一页
			$("#" + tableContainerId).find(".pagination").find(".next").addClass("disabled");
		}
		$("#" + tableContainerId).find(".pagination").find("li").die().live("click", function(i){
			// 获取上一个点击的按钮
			var lastVisit = $("#" + tableContainerId).find(".pagination").find(".active");
			var lastNum = lastVisit.find("a").text();
			var serail = $(this).attr("serail");
			var currentPageNum = Number($("#paging_page_" + tableContainerId).val());
			var currentPageCountNum = Number($("#paging_pageCount_" + tableContainerId).val());
			if(serail == '-1') { // 上一页
				Paging.page = currentPageNum - 1;
				Paging.page = Paging.page < 0 ? 1 : Paging.page;
				if(Paging.page == 1) {
					$(this).addClass("disabled"); // 上一页按钮不可用
				}
			} else if(serail == '+1') { // 下一页
				Paging.page = currentPageNum + 1;
				Paging.page = Paging.page > currentPageCountNum ? currentPageCountNum : Paging.page;
				if(Paging.page == currentPageCountNum) {
					$(this).addClass("disabled"); // 下一页按钮不可用
				}
			} else { // 序号按钮
				Paging.page = Number(serail);
				// 移除上一个不可用状态的按钮的样式
				$("#" + tableContainerId).find(".pagination").find(".disabled").removeClass("disabled");
				if(lastNum == serail) { // 上一次访问的页码和当前点击页相同
					return;
				}
			}
			// 移除上一个点击的按钮的样式
			lastVisit.removeClass("active");
			// 回调函数：执行数据重新加载的代码
			if(typeof callback == "function") {
				callback();
			}
		});
	}
};
