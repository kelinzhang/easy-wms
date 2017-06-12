<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validate/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-artDialog/jquery.artDialog.source.js?skin=blue"></script>

</head>
<body>
<!--///////////////////////////////////////////////////////////-->
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
<!--///////////////////////////////////////////////////////////-->
<s:form name="editForm" namespace="/" action="systemMenu_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="systemMenu.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">系统菜单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">上级菜单</td>
                    <td class="ui_text_lt">
                        <s:textfield name="#parentName" disabled="true" style="background:pink" cssClass="ui_input_txt02"/>
                        <s:hidden name="qo.parentId"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">系统菜单名称</td>
                    <td class="ui_text_lt">
                        <s:textfield name="systemMenu.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">系统菜单编码</td>
                    <td class="ui_text_lt">
                        <s:textfield name="systemMenu.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">URL</td>
                    <td class="ui_text_lt">
                        <s:textfield name="systemMenu.url" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>