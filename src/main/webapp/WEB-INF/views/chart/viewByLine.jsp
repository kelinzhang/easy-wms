<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/highcharts/highcharts.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#container').highcharts({
                title: {
                    text: '销售报表',
                    x: -20 //center
                },
                subtitle: {
                    text: '根据<s:property value="#groupBy" escapeHtml="false"/>分组',
                    x: -20
                },
                xAxis: {
                    categories: <s:property value="#names" escapeHtml="false"/>
                },
                yAxis: {
                    title: {
                        text: '销售金额(元)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '元'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [ {
                    name: '销售金额',
                    data: <s:property value="#values" escapeHtml="false"/>
                }, {
                    name: '毛利润',
                    data: <s:property value="#gross" escapeHtml="false"/>
                }]
            });
        });
    </script>
    <title>销售报表</title>
</head>
<body>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
