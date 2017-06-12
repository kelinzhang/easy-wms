<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript">
    <s:if test="hasActionMessages()">
    $.dialog({
        title: "提示",
        content: "<s:property value="actionMessages"/>",
        icon: "face-smile",
        ok: true
    });
    </s:if>

    <s:if test="hasActionErrors()">
    $.dialog({
        title: "提示",
        content: "<s:property value="actionErrors"/>",
        icon: "face-smile",
        ok: true
    });
    </s:if>
</script>