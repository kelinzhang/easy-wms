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
    <script type="text/javascript" src="/js/plugins/fancybox/jquery.fancybox.js"></script>
    <link href="/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function () {
            $('.smallPic').fancybox();
        });
    </script>
    <title>PSS-货品管理</title>
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
<s:form id="searchForm" namespace="/" action="product" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        货品名称/编码
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                        品牌
                        <s:select list="#brands" name="qo.brandId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerKey="-1" headerValue="所有品牌"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <s:url namespace="/" action="product_input" var="url"/>
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
                        <th>货品图片</th>
                        <th>货品名称</th>
                        <th>货品编码</th>
                        <th>货品品牌</th>
                        <th>成本价</th>
                        <th>销售价</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" autocomplete="off"
                                       data-eid="<s:property value="id"/>"/>
                            </td>
                            <td>
                                <a class="smallPic" href="<s:property value="imagePath"/>" data-fancybox-group="gallery"
                                   title="<s:property value="name"/>">
                                    <img src="<s:property value="smallImage"/>" class="list_img_min">
                                </a>
                            </td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="brand.name"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="salePrice"/></td>
                            <td>
                                <s:a namespace="/" action="product_input"> 编辑
                                    <s:param name="product.id" value="id"/>
                                </s:a>

                                <s:url namespace="/" action="product_delete" var="deleteUrl">
                                    <s:param name="product.id" value="id"/>
                                </s:url>
                                <a class="btn_delete" href="javascript:;"
                                   data-url="<s:property value="#deleteUrl"/>">删除</a>
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
