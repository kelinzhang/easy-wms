/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-04-04 05:13:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fa_0026_005ftarget_005fnamespace_005faction;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fa_0026_005ftarget_005fnamespace_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fa_0026_005ftarget_005fnamespace_005faction.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh-CN\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("    <title>小码哥PSS（演示版）</title>\r\n");
      out.write("    <link href=\"/style/main_css.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("    <link href=\"/style/zTreeStyle.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/jquery/jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/commonAll.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/system/index.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/plugins/zTree/js/jquery.ztree.core-3.4.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"top\">\r\n");
      out.write("    <div id=\"top_logo\">\r\n");
      out.write("        <img alt=\"logo\" src=\"images/common/logo.jpg\" width=\"274\" height=\"49\" style=\"vertical-align:middle;\">\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id=\"top_links\">\r\n");
      out.write("        <div id=\"top_op\">\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <img alt=\"当前用户\" src=\"images/common/user.jpg\">：\r\n");
      out.write("                    <span>");
      if (_jspx_meth_s_005fproperty_005f0(_jspx_page_context))
        return;
      out.write("</span>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <img alt=\"今天是\" src=\"images/common/date.jpg\">：\r\n");
      out.write("                    <span id=\"day_day\"></span>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div id=\"top_close\">\r\n");
      out.write("            ");
      if (_jspx_meth_s_005fa_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- side menu start -->\r\n");
      out.write("<div id=\"side\">\r\n");
      out.write("    <div id=\"left_menu\">\r\n");
      out.write("        <ul id=\"TabPage2\" style=\"height:200px; margin-top:50px;\">\r\n");
      out.write("            <li id=\"left_tab1\" class=\"selected\" title=\"业务模块\" data-rootmenu=\"business\">\r\n");
      out.write("                <img alt=\"业务模块\" title=\"业务模块\" src=\"images/common/1_hover.jpg\" width=\"33\" height=\"31\">\r\n");
      out.write("            </li>\r\n");
      out.write("            <li id=\"left_tab2\" title=\"系统管理\" data-rootmenu=\"systemManage\">\r\n");
      out.write("                <img alt=\"系统管理\" title=\"系统管理\" src=\"images/common/2.jpg\" width=\"33\" height=\"31\">\r\n");
      out.write("            </li>\r\n");
      out.write("            <li id=\"left_tab3\" title=\"报表\" data-rootmenu=\"charts\">\r\n");
      out.write("                <img alt=\"报表\" title=\"报表\" src=\"images/common/3.jpg\" width=\"33\" height=\"31\">\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("\r\n");
      out.write("        <div id=\"nav_show\" style=\"position:absolute; bottom:0px; padding:10px;\">\r\n");
      out.write("            <a href=\"javascript:;\" id=\"show_hide_btn\">\r\n");
      out.write("                <img alt=\"显示/隐藏\" title=\"显示/隐藏\" src=\"images/common/nav_hide.png\" width=\"35\" height=\"35\">\r\n");
      out.write("            </a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id=\"left_menu_cnt\">\r\n");
      out.write("        <div id=\"nav_module\">\r\n");
      out.write("            <img src=\"images/common/module_1.png\" width=\"210\" height=\"58\"/>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div id=\"nav_resource\">\r\n");
      out.write("            <ul id=\"dleft_tab1\" class=\"ztree\">\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- side menu start -->\r\n");
      out.write("<div id=\"top_nav\">\r\n");
      out.write("    <span id=\"here_area\">当前位置：系统&nbsp;>&nbsp;系统介绍</span>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"main\">\r\n");
      out.write("    <iframe name=\"right\" id=\"rightMain\" src=\"/employee.action\" frameborder=\"no\" scrolling=\"auto\" width=\"100%\" height=\"100%\" allowtransparency=\"true\"/>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_005fproperty_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_005fproperty_005f0 = (org.apache.struts2.views.jsp.PropertyTag) _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_005fproperty_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fproperty_005f0.setParent(null);
    // /WEB-INF/views/main.jsp(25,26) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fproperty_005f0.setValue("#session.USER_NAME");
    int _jspx_eval_s_005fproperty_005f0 = _jspx_th_s_005fproperty_005f0.doStartTag();
    if (_jspx_th_s_005fproperty_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fa_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:a
    org.apache.struts2.views.jsp.ui.AnchorTag _jspx_th_s_005fa_005f0 = (org.apache.struts2.views.jsp.ui.AnchorTag) _005fjspx_005ftagPool_005fs_005fa_0026_005ftarget_005fnamespace_005faction.get(org.apache.struts2.views.jsp.ui.AnchorTag.class);
    _jspx_th_s_005fa_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fa_005f0.setParent(null);
    // /WEB-INF/views/main.jsp(34,12) name = namespace type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fa_005f0.setNamespace("/");
    // /WEB-INF/views/main.jsp(34,12) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fa_005f0.setAction("logout");
    // /WEB-INF/views/main.jsp(34,12) null
    _jspx_th_s_005fa_005f0.setDynamicAttribute(null, "target", "_parent");
    int _jspx_eval_s_005fa_005f0 = _jspx_th_s_005fa_005f0.doStartTag();
    if (_jspx_eval_s_005fa_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fa_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fa_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fa_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                <img alt=\"退出系统\" title=\"退出系统\" src=\"images/common/close.jpg\" style=\"position: relative; top: 10px; left: 25px;\">\r\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_s_005fa_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fa_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fa_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fa_0026_005ftarget_005fnamespace_005faction.reuse(_jspx_th_s_005fa_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fa_0026_005ftarget_005fnamespace_005faction.reuse(_jspx_th_s_005fa_005f0);
    return false;
  }
}