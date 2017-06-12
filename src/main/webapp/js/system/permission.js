//加载权限
$(function(){
    $(".btn_reload").click(function(){
        var url = $(this).data("url");
        $.dialog({
            title:"提示",
            content:"亲,重新加载权限可能需要耗费很长的时间,你确定加载吗?",
            icon:"face-smile",
            cancel:true,
            ok:function(){
               var dialog = $.dialog({title:"提示",icon:"face-smile"});
                $.get(url,function(){
                    dialog.content("权限加载成功!");
                    dialog.button({
                        name:"确认",
                        callback:function(){
                            window.location.reload();
                        }
                    });
                });
            }
        });
    });
});
