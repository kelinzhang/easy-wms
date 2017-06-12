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
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            function clearTrData(tr) {
                tr.find("[tag=name]").val("");
                tr.find("[tag=salePrice]").val("");
                tr.find("[tag=pid]").val("");
                tr.find("[tag=number]").val("");
                tr.find("[tag=remark]").val("");
                tr.find("[tag=brand]").text("");
                tr.find("[tag=amount]").text("");
            }

            $("#edit_table_body")
                    .on("click", ".searchproduct", function () {
                        var ret = window.showModalDialog("product_selectProductList.action", '', "dialogHeight:500px;dialogWidth:500px");
                        var tr = $(this).closest("tr");
                        tr.find("[tag=name]").val(ret.name);
                        tr.find("[tag=salePrice]").val(ret.salePrice);
                        tr.find("[tag=brand]").text(ret.brandName);
                        tr.find("[tag=pid]").val(ret.id);
                    })
                    .on("blur", "[tag=number],[tag=salePrice]", function () {
                        var tr = $(this).closest("tr");
                        var number = tr.find("[tag=number]").val();
                        var salePrice = tr.find("[tag=salePrice]").val();
                        if (number && salePrice) {
                            var amount = (salePrice * number).toFixed(2);
                            tr.find("[tag=amount]").text(amount);
                        }
                    })
                    .on("click", ".removeItem", function () {
                        if ($("#edit_table_body tr").size() == 1) {
                            clearTrData($(this).closest("tr"));
                        } else {
                            $(this).closest("tr").remove();
                        }
                    });
            $(".appendRow").click(function () {
                var copy = $("#edit_table_body tr:first").clone(true);
                clearTrData(copy);
                copy.appendTo($("#edit_table_body"));
            });
            $(".btn_submit").click(function () {
                $.each($("#edit_table_body tr"), function (index, item) {
                    $(item).find("[tag=pid]").prop("name", "stockOutcomeBill.items[" + index + "].product.id");
                    $(item).find("[tag=salePrice]").prop("name", "stockOutcomeBill.items[" + index + "].salePrice");
                    $(item).find("[tag=number]").prop("name", "stockOutcomeBill.items[" + index + "].number");
                    $(item).find("[tag=remark]").prop("name", "stockOutcomeBill.items[" + index + "].remark");
                })
                $("#editForm").submit();
            });
            $("input[name='stockOutcomeBill.vdate']").click(function () {
                WdatePicker({
                    maxDate:"%y-%M-%d",
                    skin:"twoer"
                });
            });
        });
    </script>
</head>
<body>
<!--///////////////////////////////////////////////////////////-->
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<!--///////////////////////////////////////////////////////////-->
<s:form name="editForm" namespace="/" action="stockOutcomeBill_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="stockOutcomeBill.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">出库订单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">订单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">客户</td>
                    <td class="ui_text_lt">
                        <s:select list="#clients" name="stockOutcomeBill.client.id" listKey="id" listValue="name"
                                  cssClass="ui_select03"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <s:select list="#depots" name="stockOutcomeBill.depot.id" listKey="id" listValue="name"
                                  cssClass="ui_select03"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.vdate" cssClass="ui_input_txt02 Wdate"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <s:if test="stockOutcomeBill.id == null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04"
                                                     tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="stockOutcomeBill.items.product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="salePrice" name="stockOutcomeBill.items.salePrice"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="stockOutcomeBill.items.number"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="stockOutcomeBill.items.remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:iterator value="stockOutcomeBill.items">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04"
                                                         tag="name" name="product.name"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <s:hidden name="product.id" tag="pid"/>
                                        </td>
                                        <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                        <td><s:textfield tag="salePrice" name="salePrice"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><s:textfield tag="number" name="number"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><span tag="amount"><s:property value="amount"/></span></td>
                                        <td><s:textfield tag="remark" name="remark"
                                                         cssClass="ui_input_txt02"/></td>
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>