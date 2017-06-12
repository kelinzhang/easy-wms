//=========================================================================
//禁用全局化序列化参数
$.ajaxSettings.traditional = true;
//=========================================================================
/**批量删除**/
$(function () {
    //全选/不全选
    $("#all").click(function () {
        $(".acb").prop("checked", this.checked);
    });
    //批量删除
    $(".btn_batchDelete").click(function () {
        /*var ids = new Array();
         $.each($(".acb:checked"),function(index,item){
         ids[index] = $(item).data("eid");
         })*/
        var deleteUrl = $(this).data("url");
        var ids = $.map($(".acb:checked"), function (item) {
            return $(item).data("eid");
        })
        if (ids.length == 0) {
            $.dialog({
                title: "提示",
                content: "请选择要删除的数据!",
                icon: "face-smile",
                ok: true
            });
            return;
        }
        $.dialog({
            title: "提示",
            content: "你确定要删除吗?",
            icon: "face-smile",
            ok: function () {
                var dialog = $.dialog({title: "提示", icon: "face-smile"});
                $.post(deleteUrl, {ids: ids}, function () {
                    dialog.content("批量删除成功!").button({
                        name: "确定",
                        callback: function () {
                            window.location.reload();
                        }
                    })
                });
            },
            cancel: true,
            resize: false,
            drag: false
        });
    });
});
//=========================================================================
/**删除会话框**/
$(function () {
    $(".btn_audit").click(function () {
        var auditurl = $(this).data("url");
        console.debug(auditurl);
        $.dialog({
            title: "提示",
            content: "你确定要审核吗?",
            icon: "face-smile",
            ok: function () {
                var dialog = $.dialog({title: "提示", icon: "face-smile", ok: true});
                $.get(auditurl, function (data) {
                    if(data.success){
                        dialog.content(data.msg).button({
                            name: "确定",
                            callback: function () {
                                window.location.reload();
                            }
                        })
                    }else {
                        dialog.content(data.msg).button({
                            name: "确定"
                        })
                    }
                },"json");
            },
            cancel: true,
            resize: false,
            drag: false
        });
    });
});
//=========================================================================
/**删除会话框**/
$(function () {
    $(".btn_delete").click(function () {
        var deleteurl = $(this).data("url");
        $.dialog({
            title: "提示",
            content: "你确定要删除吗?",
            icon: "face-smile",
            ok: function () {
                var dialog = $.dialog({title: "提示", icon: "face-smile", ok: true});
                $.get(deleteurl, function () {
                    dialog.content("删除成功!").button({
                        name: "确定",
                        callback: function () {
                            window.location.reload();
                        }
                    })
                });
            },
            cancel: true,
            resize: false,
            drag: false
        });
    });
});
//=========================================================================
/**点击跳转**/
$(function () {
    $(".btn_input").click(function () {
        window.location.href = $(this).data("url");
    });
});
//==========================================================================
/** 翻页跳转* */
$(function () {
    $("input[name='qo.currentPage']").css("width", "35px").prop("type", "number").prop("min", "1")
        .prop("max", $(".totalPage").data("page"));

    $(".btn_page").click(function () {
        $("input[name='qo.currentPage']").val($(this).data("page") || $("input[name='qo.currentPage']").val());
        $("#searchForm").submit();
    });
    //选择每页条数
    $(":input[name='qo.pageSize']").change(function () {
        $("#searchForm").submit();
    });
});
//==========================================================================
/** table鼠标悬停换色* */
$(function () {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({background: "#CDDAEB"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#1D1E21"});
        });
    }).mouseout(function () {
        $(this).css({background: "#FFF"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#909090"});
        });
    });
});
