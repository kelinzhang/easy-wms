//=============================================================================
/**移动菜单**/
$(function(){
    $("#mselectAll").click(function(){
        $(".all_menus option").appendTo($(".selected_menus"));
    });
});
$(function(){
    $("#mdeselectAll").click(function(){
        $(".selected_menus option").appendTo($(".all_menus"));
    });
});
$(function(){
    $("#mselect").click(function(){
        $(".all_menus option:selected").appendTo($(".selected_menus"));
    });
});$(function(){
    $("#mdeselect").click(function(){
        $(".selected_menus option:selected").appendTo($(".all_menus"));
    });
});
/**从左边列表中移除已经分配的菜单**/
$(function(){
    //获取已经分配权限的value值
    var ids = $.map($(".selected_menus option"),function(item){
        return item.value;
    });
    //迭代左边列表,移除已经分配的权限
    $.each($(".all_menus option"),function(index,item){
        if($.inArray(item.value,ids) >= 0){
            $(item).remove();
        }
    })
});
/**在表单提交之前,将已经分配的菜单设置为已选中状态**/
$(function(){
    $("#editForm").submit(function(){
        $(".selected_menus option").prop("selected",true);
    });
});
//=============================================================================
/**移动权限**/
$(function(){
    $("#selectAll").click(function(){
        $(".all_permissions option").appendTo($(".selected_permissions"));
    });
});
$(function(){
    $("#deselectAll").click(function(){
        $(".selected_permissions option").appendTo($(".all_permissions"));
    });
});
$(function(){
    $("#select").click(function(){
        $(".all_permissions option:selected").appendTo($(".selected_permissions"));
    });
});$(function(){
    $("#deselect").click(function(){
        $(".selected_permissions option:selected").appendTo($(".all_permissions"));
    });
});
/**从左边列表中移除已经分配的权限**/
$(function(){
    //获取已经分配权限的value值
    var ids = $.map($(".selected_permissions option"),function(item){
        return item.value;
    });
    //迭代左边列表,移除已经分配的权限
    $.each($(".all_permissions option"),function(index,item){
        if($.inArray(item.value,ids) >= 0){
            $(item).remove();
        }
    })
});
/**在表单提交之前,将已经分配的权限设置为已选中状态**/
$(function(){
    $("#editForm").submit(function(){
        $(".selected_permissions option").prop("selected",true);
    });
});