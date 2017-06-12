<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            $("input[name='qo.beginDate']").click(function () {
                WdatePicker({
                    maxDate:$("input[name='qo.endDate']").val(),
                    maxDate:"%y-%M-%d",
                    skin:"twoer"
                });
            });
            $("input[name='qo.endDate']").click(function () {
                WdatePicker({
                    minDate:$("input[name='qo.beginDate']").val(),
                    maxDate:"%y-%M-%d",
                    skin:"twoer"
                });
            });
            $("[name='qo.groupType']").change(function(){
                $("#searchForm").submit();
            });
        });
    </script>
    <title>PSS-采购订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<!--///////////////////////////////////////////////////////////-->
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<!--///////////////////////////////////////////////////////////-->
<s:form id="searchForm" namespace="/" action="chart_orderChart" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        订单日期
                        <s:date name="qo.beginDate" format="yyyy-MM-dd" var="begin"/>
                        <s:textfield name="qo.beginDate" cssClass="ui_input_txt02 Wdate" value="%{#begin}"/>~
                        <s:date name="qo.endDate" format="yyyy-MM-dd" var="end"/>
                        <s:textfield name="qo.endDate" cssClass="ui_input_txt02 Wdate" value="%{#end}"/>
                        货品
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                        供应商
                        <s:select list="#suppliers" name="qo.supplierId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerValue="所有供应商" headerKey="-1"/>
                        品牌
                        <s:select list="#brands" name="qo.brandId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerValue="所有品牌" headerKey="-1"/>
                        分组
                        <s:select list="#groupBy" listKey="name()" listValue="name" name="qo.groupType" cssClass="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>分组类型</th>
                        <th>订货数量</th>
                        <th>订货金额</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" autocomplete="off"
                                       data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="groupByName"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>
