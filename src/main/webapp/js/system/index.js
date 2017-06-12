//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
	}
}
/**zTree使用**/
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	async: {
		enable: true,
		url: "/systemMenu_getMenusByParentSn.action",
		autoParam: ["sn=qo.parentSn"]
	},
	callback: {
		onClick: function (event, treeId, treeNode, clickFlag) {
			if (treeNode.action) {
				var url = "/" + treeNode.action + ".action";
				$("#rightMain").prop("src", url);
				$("#here_area").html("当前位置：▶"+treeNode.getParentNode().name+" ▶"+treeNode.name);
			}
		}
	}
};

var zNodes = {
	business: [
		{id: 1, pId: 0, name: "业务模块", isParent: true,sn:"business"}
	],
	systemManage: [
		{id: 2, pId: 0, name: "系统管理", isParent: true,sn:"system"}
	],
	charts: [
		{id: 3, pId: 0, name: "报表", isParent: true,sn:"chart"}
	]
};
function loadMenus(sn) {
	$.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
};
$(function(){
	loadMenus("business");
	loadDate();
	//===========================================================
	$("#TabPage2 li").click(function () {
		//把所有菜单的样式和图片改变
		$.each($("#TabPage2 li"), function (index, item) {
			$(item).removeClass("selected");
			$(item).children("img").prop("src", "images/common/" + (index + 1) + ".jpg");
		});
		//改变样式和图片
		$(this).addClass("selected");
		var index = $(this).index() + 1;
		$(this).children("img").prop("src", "images/common/" + index + "_hover.jpg");
		//修改菜单的标题图片
		$("#nav_module").children("img").prop("src", "images/common/module_" + index + ".png");
		var sn = $(this).data("rootmenu");
		loadMenus(sn);
	});
	//===========================================================
	//显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
        switchSysBar();
    });
});