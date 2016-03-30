/**
 * <p>公共菜单JS，用于展示页面左侧菜单信息</p>
 * 
 * @author lipp
 * @date 2015-05-27
 */
$(function(){
	if(loginUser && loginUser.id) {
		var role = loginUser.roleVO;
		var menuList = role.menuVOList;
		var subMenu = '';
		for(var i = 0; i < menuList.length; i++) {
			var menu = menuList[i];
			subMenu += '<li>';
			subMenu += '<a href="' + ctx+menu.actionUrl + '">';
			subMenu += 		'<i class="'+ menu.icon +'"></i>' + menu.name;
			subMenu += '</a>';
			subMenu += '</li>';
		}
		$(".page-sidebar").find("ul.sub-menu").html(subMenu);
	}
});
