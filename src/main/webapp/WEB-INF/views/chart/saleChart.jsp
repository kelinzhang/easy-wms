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
            $("input[name='sqo.beginDate']").click(function () {
                WdatePicker({
                    maxDate: $("input[name='sqo.endDate']").val(),
                    maxDate: "%y-%M-%d",
                    skin: "twoer"
                });
            });
            $("input[name='sqo.endDate']").click(function () {
                WdatePicker({
                    minDate: $("input[name='sqo.beginDate']").val(),
                    maxDate: "%y-%M-%d",
                    skin: "twoer"
                });
            });
            $("[name='sqo.groupType']").change(function () {
                $("#searchForm").submit();
            });
            //图表分析
            $(".btn_chart").click(function () {
                if ($("[name='viewType']").val() == 'PIE') {
                    window.showModalDialog("/chart_viewByPie.action?" + $("#searchForm").serialize(), '', "dialogHeight:800px;dialogWidth:800px");
                } else {
                    window.showModalDialog("/chart_viewByLine.action?" + $("#searchForm").serialize(), '', "dialogHeight:800px;dialogWidth:800px");
                }
            });
        });
    </script>
    <title>PSS-销售报表管理</title>
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
<s:form id="searchForm" namespace="/" action="chart_saleChart" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        订单日期
                        <s:date name="sqo.beginDate" format="yyyy-MM-dd" var="begin"/>
                        <s:textfield name="sqo.beginDate" cssClass="ui_input_txt02 Wdate" value="%{#begin}"/>~
                        <s:date name="sqo.endDate" format="yyyy-MM-dd" var="end"/>
                        <s:textfield name="sqo.endDate" cssClass="ui_input_txt02 Wdate" value="%{#end}"/>
                        货品
                        <s:textfield name="sqo.keyword" cssClass="ui_input_txt02"/>
                        客户
                        <s:select list="#clients" name="sqo.clientId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerValue="所有供应商" headerKey="-1"/>
                        品牌
                        <s:select list="#brands" name="sqo.brandId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerValue="所有品牌" headerKey="-1"/>
                        分组
                        <s:select list="#groupBy" listKey="name()" listValue="name" name="sqo.groupType"
                                  cssClass="ui_select01"/>
                        图表类型
                        <s:select list="#{'LINE':'线形图','PIE':'饼状图'}" name="viewType" cssClass="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="图表分析" class="ui_input_btn01 btn_chart"/>
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
                        <th>销售数量</th>
                        <th>销售金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" autocomplete="off"
                                       data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="groupByName"/></td>
                            <td><s:property value="saleNumber"/></td>
                            <td><s:property value="saleAmount"/></td>
                            <td><s:property value="grossProfit"/></td>
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
