/**
 * 消息提示框工具
 * @description 消息提示框工具
 */
var Msg = {
	title:'消息',//标题
	context:'确认？',//内容
	type:'alert',//消息框类型：alert/prom
	affirm:function(){},//确认按钮函数
	cancel:function(){},//取消按钮函数
	showEnv:function(){},
	hideEnv:function(){ // 隐藏消息框后将消息框移除
		$("#msg-dialog-" + Msg.type).remove();
	},
	/**
	 * 初始化Dialog
	 */
	init:function(){
		var dialog_id = "#msg-dialog-" + this.type;
		if($(dialog_id).length == 0){
			$("body").append(this.createMsg());
		}else{
			$(dialog_id).find(".modal-title").html(this.title);
			$(dialog_id).find(".modal-body").html(this.context);
		}
		var btns = $(dialog_id+" .modal-footer").find(":button");
		if(this.type == "alert"){
			$(btns.get(1)).hide(); // 普通消息框不显示“取消”按钮
		}else{
			$(btns.get(1)).show();
		}
		// 给按钮绑定事件
		$(btns.get(0)).live('click',this.affirm);
		$(btns.get(1)).live('click',this.cancel);
		// 事件
		//$(dialog_id).die().live('shown.bs.modal',this.showEnv); // 移除原有live的事件并绑定新事件
		//$(dialog_id).die().live('hidden.bs.modal',this.hideEnv); // 移除原有live的事件并绑定新事件
	},
	/**
	 * 弹出一个消息框
	 * @description demo:Msg.alert("消息内容");
	 * @param msg 必须。显示的消息内容
	 * @param callback 回调函数
	 * @return
	 */
	alert:function(msg, callback){
		this.type = 'alert';
		this.title = "消息";
		this.context = msg;
		if(arguments[1]) { // 确定按钮
			this.affirm = arguments[1];
		} else {
			this.affirm = null;
		}
		Msg.init();
		$("#msg-dialog-"+this.type).modal("show");
	},
	/**
	 * 弹出一个提示框
	 * @description demo:Msg.alert("提示内容");
	 * @param msg 必须。显示的提示内容
	 * @param callback 回调函数
	 * @param args 回调函数的参数
	 * @return
	 */
	prompt:function(msg, callback){
		this.type = 'prompt';
		this.title = "提示";
		this.context = msg;
		if(arguments[1]) { // 确定按钮
			this.affirm = arguments[1];
		} else {
		 	this.affirm = null;
		}
		Msg.init();
		$("#msg-dialog-"+this.type).modal("show");
	},
	/**
	 * 弹出一个确认框
	 * @description demo:Msg.confirm("确认删除此条记录吗？", function(){Msg.alert("删除成功！");});
	 * @param callback 回调函数
	 * @return 无
	 */
	confirm:function(msg, callback){
		this.type = 'confirm';
		this.title = "确认";
		this.context = msg;
		if(arguments[1]) {
			this.affirm = arguments[1];
		} else {
			this.affirm = null;
		}
		if(arguments[2]) {
			this.cancel = arguments[2];
		} else {
			this.cancel = null;
		}
		Msg.init();
		$("#msg-dialog-"+this.type).modal("show");
	},
	/**
	 * 生成dialog的html代码
	 * @author machao@icloud.edu.com
	 * @date 2014-12-04
	 * @return {} html代码
	 */
	createMsg:function(){
		var strHtml = '';
		strHtml += '<div class="modal fade" id="msg-dialog-'+this.type+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
  		strHtml += '<div class="modal-dialog">';
    	strHtml += '<div class="modal-content">';
      	strHtml += '<div class="modal-header">';
        strHtml += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>';
        strHtml += '<h4 class="modal-title">'+this.title+'</h4>';
     	strHtml += ' </div>';
      	strHtml += '<div class="modal-body">';
       	strHtml += this.context;
      	strHtml += '</div>';
      	strHtml += '<div class="modal-footer">';
      	strHtml += '<button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>';
	    strHtml += '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>';
      	strHtml += '</div>';
    	strHtml += '</div>';
  		strHtml += '</div>';
		strHtml += '</div>';
		return strHtml;
	}
}
