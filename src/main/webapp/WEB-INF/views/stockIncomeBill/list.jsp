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
<s:form id="searchForm" namespace="/" action="stockIncomeBill" method="post">
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
                        仓库
                        <s:select list="#depots" name="qo.depotId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerValue="所有仓库" headerKey="-1"/>
                        审核状态
                        <s:select list="#{-1:'所有',0:'未审核',1:'已审核'}" name="qo.status" cssClass="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <s:url namespace="/" action="stockIncomeBill_input" var="url"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:property value="#url"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>订单编号</th>
                        <th>业务时间</th>
                        <th>仓库</th>
                        <th>采购数量</th>
                        <th>采购金额</th>
                        <th>制单人</th>
                        <th>审核人</th>
                        <th>审核状态</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" autocomplete="off"
                                       data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="vdate"/></td>
                            <td><s:property value="depot.name"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="inputUser.name"/></td>
                            <td><s:property value="auditor.name"/></td>
                            <td>
                                <s:if test="status == 0">
                                    <span style="color: green">未审核</span>
                                </s:if>
                                <s:elseif test="status == 1">
                                    <span style="color: red">已审核</span>
                                </s:elseif>
                            </td>
                            <td>
                                <s:if test="status == 0">
                                    <s:a namespace="/" action="stockIncomeBill_input"> 编辑
                                        <s:param name="stockIncomeBill.id" value="id"/>
                                    </s:a>

                                    <s:url namespace="/" action="stockIncomeBill_delete" var="deleteUrl">
                                        <s:param name="stockIncomeBill.id" value="id"/>
                                    </s:url>
                                    <a class="btn_delete" href="javascript:;"
                                       data-url="<s:property value="#deleteUrl"/>">删除</a>
                                    <s:url namespace="/" action="stockIncomeBill_audit" var="auditUrl">
                                        <s:param name="stockIncomeBill.id" value="id"/>
                                    </s:url>
                                    <a class="btn_audit" href="javascript:;"
                                       data-url="<s:property value="#auditUrl"/>">审核</a>
                                </s:if>
                                <s:elseif test="status == 1">
                                    <s:a namespace="/" action="stockIncomeBill_view"> 查看
                                        <s:param name="stockIncomeBill.id" value="id"/>
                                    </s:a>
                                </s:elseif>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <!--分页条-->
            <%@include file="/WEB-INF/views/common/common_page.jsp" %>
        </div>
    </div>
</s:form>
</body>
</html>
