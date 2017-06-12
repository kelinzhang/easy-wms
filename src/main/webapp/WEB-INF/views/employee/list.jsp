<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <title>PSS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%--////////////////////////////////////////////////////////--%>
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
<%--////////////////////////////////////////////////////////--%>
<s:form id="searchForm" namespace="/" action="employee" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        姓名/邮箱
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                        所属部门
                        <s:select list="#depts" name="qo.deptId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerKey="-1" headerValue="所有部门"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <s:url namespace="/" action="employee_input" var="url"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:property value="#url"/>"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url namespace="/" action="employee_batchDelete"/> "/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>用户名</th>
                        <th>EMAIL</th>
                        <th>年龄</th>
                        <th>所属部门</th>
                        <th>角色</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" autocomplete="off" class="acb"
                                       data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="email"/></td>
                            <td><s:property value="age"/></td>
                            <td><s:property value="dept.name"/></td>
                            <td><s:property value="roleName"/></td>
                            <td>
                                <s:a namespace="/" action="employee_input.action">
                                    <s:param name="employee.id" value="id"></s:param>编辑
                                </s:a>
                                    <%-- <s:a namespace="/" class="btn_delete" action="employee_delete.action">
                                         <s:param name="employee.id" value="id"></s:param>删除
                                     </s:a>--%>
                                <s:url namespace="/" action="employee_delete" var="deleteUrl">
                                    <s:param name="employee.id" value="id"/>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
                <%--分页条--%>
            <%@ include file="/WEB-INF/views/common/common_page.jsp" %>
        </div>
    </div>
</s:form>
</body>
</html>

