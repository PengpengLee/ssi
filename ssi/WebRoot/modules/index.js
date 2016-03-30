
/** 菜单列表对应的样式 */
var menuClasses = [
	green= [
		"green",
		"green-meadow",
		"green-seagreen",
		"green-turquoise",
		"green-haze",
		"green-jungle",
		"green-sharp",
		"green-soft"
	],
	
	grey= [
		"grey",
		"grey-steel",
		"grey-cararra",
		"grey-gallery",
		"grey-cascade",
		"grey-silver",
		"grey-salsa",
		"grey-salt",
		"grey-mint"
	],
	
	red= [
		"red",
		"red-pink",
		"red-sunglo",
		"red-intense",
		"red-thunderbird",
		"red-flamingo",
		"red-soft",
		"red-haze"
	],
	
	yellow= [
		"yellow",
		"yellow-gold",
		"yellow-casablanca",
		"yellow-crusta",
		"yellow-lemon",
		"yellow-saffron"
	],
	
	purple= [
		"purple",
		"purple-plum",
		"purple-medium",
		"purple-studio",
		"purple-wisteria",
		"purple-seance",
		"purple-intense",
		"purple-sharp",
		"purple-soft"
	],
	
	blue= [
		"blue",
		"blue-madison",
		"blue-chambray",
		"blue-ebonyclay",
		"blue-hoki",
		"blue-steel",
		"blue-soft",
		"blue-dark",
		"blue-sharp"
	]
];

if(loginUser && loginUser.id) {
	var role = loginUser.roleVO;
	var menuList = role.menuVOList;
	var menuClassesLen = menuClasses.length;
	var subMenu = '';
	for(var i = 0; i < menuList.length; i++) {
		var menu = menuList[i]; // 菜单列表
//		var menuGroupIndex = parseInt(Math.random() * menuClassesLen);
//		var colorGroup = menuClasses[menuGroupIndex];
//		var menuIndex =  parseInt(Math.random() * menuGroupIndex);
//		var color = colorGroup[menuIndex];
		subMenu += '<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">';
		subMenu += '	<div class="dashboard-stat '+ menu.color +'">';
		subMenu += '		<div class="visual">';
		subMenu += '			<i class="'+ menu.icon +'"></i>';
		subMenu += '		</div>';
		subMenu += '		<div class="details">';
		subMenu += '			<div class="number">'+ menu.name +'</div>';
		subMenu += '			<div class="desc">'+ menu.remark +'</div>';
		subMenu += '		</div>';
		subMenu += '		<a class="more" href="' + ctx+menu.actionUrl + '">';
		subMenu += '			点击进入 <i class="m-icon-swapright m-icon-white"></i>';
		subMenu += '		</a>';
		subMenu += '	</div>';
		subMenu += '</div>';
	}
	$("#div_menu_list").html(subMenu);
}