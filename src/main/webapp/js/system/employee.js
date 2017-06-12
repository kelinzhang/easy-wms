/**角色移动**/
/**移动权限**/
$(function(){
    $("#selectAll").click(function(){
        $(".all_roles option").appendTo($(".selected_roles"));
    });
});
$(function(){
    $("#deselectAll").click(function(){
        $(".selected_roles option").appendTo($(".all_roles"));
    });
});
$(function(){
    $("#select").click(function(){
        $(".all_roles option:selected").appendTo($(".selected_roles"));
    });
});$(function(){
    $("#deselect").click(function(){
        $(".selected_roles option:selected").appendTo($(".all_roles"));
    });
});
/**从左边列表中移除已经分配的权限**/
$(function(){
    //获取已经分配权限的value值
    var ids = $.map($(".selected_roles option"),function(item){
        return item.value;
    });
    //迭代左边列表,移除已经分配的权限
    $.each($(".all_roles option"),function(index,item){
        if($.inArray(item.value,ids) >= 0){
            $(item).remove();
        }
    })
});
/**在表单提交之前,将已经分配的权限设置为已选中状态**/
$(function(){
    $("#editForm").submit(function(){
        $(".selected_roles option").prop("selected",true);
    });
});
/**表单校验**/
$(function () {
    $("#editForm").validate({
        rules: {
            "employee.name": {
                required: true,
                rangelength: [4, 10]
            },
            "employee.password": {
                required: true,
                rangelength: [4, 10]
            },
            "repassword": {
                equalTo: "#password"
            },
            "employee.email": {
                required: true,
                email: true
            },
            "employee.age": {
                required: true,
                range: [18, 60]
            }
        },
        messages: {
            "employee.name": {
                required: "请输入用户名",
                rangelength: "用户名长度应该为4-10位"
            },
            "employee.password": {
                required: "请输入密码",
                rangelength: "密码长度应该为4-10位"
            },
            "repassword": {
                equalTo: "两次输入密码不一致"
            },
            "employee.email": {
                required: "请输入邮箱",
                email: "请输入有效的邮箱地址"
            },
            "employee.age": {
                required: "请输入年龄",
                range: "年龄大小应该为18-60岁"
            }
        }
    })
});