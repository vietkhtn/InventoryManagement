/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.108
 * Generated at: 2021-03-21 14:33:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menu_002dpermission_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fform_0026_005fservletRelativeAction_005fmodelAttribute_005fmethod_005fcssClass;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fcssClass;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fradiobutton_0026_005fvalue_005fpath_005fdisabled_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fform_005fform_0026_005fservletRelativeAction_005fmodelAttribute_005fmethod_005fcssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fcssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005fradiobutton_0026_005fvalue_005fpath_005fdisabled_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fform_005fform_0026_005fservletRelativeAction_005fmodelAttribute_005fmethod_005fcssClass.release();
    _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fcssClass.release();
    _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005fradiobutton_0026_005fvalue_005fpath_005fdisabled_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
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
      response.setContentType("text/html; charset=UTF-8");
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
      out.write("\r\n");
      out.write("<!-- Convert number to currency 1000000 => 1,000,000 use for price-->\r\n");
      out.write("<script src=\"//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js\"></script>\r\n");
      out.write("<div class=\"right_col\" role=\"main\">\r\n");
      out.write("\t<div class=\"\">\r\n");
      out.write("\t\t<div class=\"page-title\">\r\n");
      out.write("\t\t\t<div class=\"title_left\">\r\n");
      out.write("\t\t\t\t<h2>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${titlePage}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</h2>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"clearfix\"></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-md-12 col-sm-12 \">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"x_panel\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"x_content\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t<br />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      //  form:form
      org.springframework.web.servlet.tags.form.FormTag _jspx_th_form_005fform_005f0 = (org.springframework.web.servlet.tags.form.FormTag) _005fjspx_005ftagPool_005fform_005fform_0026_005fservletRelativeAction_005fmodelAttribute_005fmethod_005fcssClass.get(org.springframework.web.servlet.tags.form.FormTag.class);
      boolean _jspx_th_form_005fform_005f0_reused = false;
      try {
        _jspx_th_form_005fform_005f0.setPageContext(_jspx_page_context);
        _jspx_th_form_005fform_005f0.setParent(null);
        // /WEB-INF/views/menu/menu-permission.jsp(21,9) name = modelAttribute type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_form_005fform_005f0.setModelAttribute("modelForm");
        // /WEB-INF/views/menu/menu-permission.jsp(21,9) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_form_005fform_005f0.setCssClass("form-horizontal form-label-left");
        // /WEB-INF/views/menu/menu-permission.jsp(21,9) name = servletRelativeAction type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_form_005fform_005f0.setServletRelativeAction("/menu/update-permission");
        // /WEB-INF/views/menu/menu-permission.jsp(21,9) name = method type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_form_005fform_005f0.setMethod("POST");
        int[] _jspx_push_body_count_form_005fform_005f0 = new int[] { 0 };
        try {
          int _jspx_eval_form_005fform_005f0 = _jspx_th_form_005fform_005f0.doStartTag();
          if (_jspx_eval_form_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<!-- Select Role -->\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<!-- Check if not in view action => select product, if in view action => show form product name -->\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"item form-group\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t<label class=\"col-form-label col-md-3 col-sm-3 label-align\" for=\"roleId\">Role <span class=\"required\">*</span></label>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-md-6 col-sm-6 \">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
              if (_jspx_meth_form_005fselect_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"item form-group\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t<label for=\"menuId\" class=\"col-form-label col-md-3 col-sm-3 label-align\">Menu <span class=\"required\">*</span></label>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-md-6 col-sm-6 \">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
              if (_jspx_meth_form_005fselect_005f1(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"item form-group\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t<label for=\"permission\" class=\"col-form-label col-md-3 col-sm-3 label-align\">Permission </label>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-md-6 col-sm-6 \">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
              if (_jspx_meth_form_005fradiobutton_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
                return;
              out.write(" Yes &nbsp;&nbsp;\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
              if (_jspx_meth_form_005fradiobutton_005f1(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
                return;
              out.write(" No\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"has-error\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t");
              //  form:errors
              org.springframework.web.servlet.tags.form.ErrorsTag _jspx_th_form_005ferrors_005f0 = (org.springframework.web.servlet.tags.form.ErrorsTag) _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.ErrorsTag.class);
              boolean _jspx_th_form_005ferrors_005f0_reused = false;
              try {
                _jspx_th_form_005ferrors_005f0.setPageContext(_jspx_page_context);
                _jspx_th_form_005ferrors_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
                // /WEB-INF/views/menu/menu-permission.jsp(50,13) name = path type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                _jspx_th_form_005ferrors_005f0.setPath("permission");
                // /WEB-INF/views/menu/menu-permission.jsp(50,13) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                _jspx_th_form_005ferrors_005f0.setCssClass("help-block");
                int[] _jspx_push_body_count_form_005ferrors_005f0 = new int[] { 0 };
                try {
                  int _jspx_eval_form_005ferrors_005f0 = _jspx_th_form_005ferrors_005f0.doStartTag();
                  if (_jspx_th_form_005ferrors_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    return;
                  }
                } catch (java.lang.Throwable _jspx_exception) {
                  while (_jspx_push_body_count_form_005ferrors_005f0[0]-- > 0)
                    out = _jspx_page_context.popBody();
                  _jspx_th_form_005ferrors_005f0.doCatch(_jspx_exception);
                } finally {
                  _jspx_th_form_005ferrors_005f0.doFinally();
                }
                _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody.reuse(_jspx_th_form_005ferrors_005f0);
                _jspx_th_form_005ferrors_005f0_reused = true;
              } finally {
                org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_form_005ferrors_005f0, _jsp_getInstanceManager(), _jspx_th_form_005ferrors_005f0_reused);
              }
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"ln_solid\"></div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"item form-group\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-md-6 col-sm-6 offset-md-3\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t<button class=\"btn btn-primary\" type=\"button\" onclick=\"cancel();\">Cancel</button>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
              if (_jspx_meth_c_005fif_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t\t\t\t\t");
              int evalDoAfterBody = _jspx_th_form_005fform_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_form_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            return;
          }
        } catch (java.lang.Throwable _jspx_exception) {
          while (_jspx_push_body_count_form_005fform_005f0[0]-- > 0)
            out = _jspx_page_context.popBody();
          _jspx_th_form_005fform_005f0.doCatch(_jspx_exception);
        } finally {
          _jspx_th_form_005fform_005f0.doFinally();
        }
        _005fjspx_005ftagPool_005fform_005fform_0026_005fservletRelativeAction_005fmodelAttribute_005fmethod_005fcssClass.reuse(_jspx_th_form_005fform_005f0);
        _jspx_th_form_005fform_005f0_reused = true;
      } finally {
        org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_form_005fform_005f0, _jsp_getInstanceManager(), _jspx_th_form_005fform_005f0_reused);
      }
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("$(document).ready(function(){\r\n");
      out.write("\t// Determind url at current page to highligh in side-bar\r\n");
      out.write("\t$('#menu-permissionlistId').addClass('current-page').siblings().removeClass('current-page');\r\n");
      out.write("\tvar parents = $('#menu-permissionlistId').parents('li');\r\n");
      out.write("\tparents.addClass('active').siblings().removeClass('active');\r\n");
      out.write("\t$('#menu-permissionlistId').parents().show();\r\n");
      out.write("})\r\n");
      out.write("function cancel() {\r\n");
      out.write("\twindow.location.href = '");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("';\r\n");
      out.write("}\r\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_form_005fselect_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  form:select
    org.springframework.web.servlet.tags.form.SelectTag _jspx_th_form_005fselect_005f0 = (org.springframework.web.servlet.tags.form.SelectTag) _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fcssClass.get(org.springframework.web.servlet.tags.form.SelectTag.class);
    boolean _jspx_th_form_005fselect_005f0_reused = false;
    try {
      _jspx_th_form_005fselect_005f0.setPageContext(_jspx_page_context);
      _jspx_th_form_005fselect_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
      // /WEB-INF/views/menu/menu-permission.jsp(28,12) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fselect_005f0.setPath("roleId");
      // /WEB-INF/views/menu/menu-permission.jsp(28,12) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fselect_005f0.setCssClass("form-control");
      int[] _jspx_push_body_count_form_005fselect_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_form_005fselect_005f0 = _jspx_th_form_005fselect_005f0.doStartTag();
        if (_jspx_eval_form_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_form_005foptions_005f0(_jspx_th_form_005fselect_005f0, _jspx_page_context, _jspx_push_body_count_form_005fselect_005f0))
              return true;
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_form_005fselect_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_form_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005fselect_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005fselect_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005fselect_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fcssClass.reuse(_jspx_th_form_005fselect_005f0);
      _jspx_th_form_005fselect_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_form_005fselect_005f0, _jsp_getInstanceManager(), _jspx_th_form_005fselect_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_form_005foptions_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fselect_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fselect_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  form:options
    org.springframework.web.servlet.tags.form.OptionsTag _jspx_th_form_005foptions_005f0 = (org.springframework.web.servlet.tags.form.OptionsTag) _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fnobody.get(org.springframework.web.servlet.tags.form.OptionsTag.class);
    boolean _jspx_th_form_005foptions_005f0_reused = false;
    try {
      _jspx_th_form_005foptions_005f0.setPageContext(_jspx_page_context);
      _jspx_th_form_005foptions_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f0);
      // /WEB-INF/views/menu/menu-permission.jsp(29,13) name = items type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005foptions_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mapRole}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_form_005foptions_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_form_005foptions_005f0 = _jspx_th_form_005foptions_005f0.doStartTag();
        if (_jspx_th_form_005foptions_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005foptions_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005foptions_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005foptions_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fnobody.reuse(_jspx_th_form_005foptions_005f0);
      _jspx_th_form_005foptions_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_form_005foptions_005f0, _jsp_getInstanceManager(), _jspx_th_form_005foptions_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_form_005fselect_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  form:select
    org.springframework.web.servlet.tags.form.SelectTag _jspx_th_form_005fselect_005f1 = (org.springframework.web.servlet.tags.form.SelectTag) _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fcssClass.get(org.springframework.web.servlet.tags.form.SelectTag.class);
    boolean _jspx_th_form_005fselect_005f1_reused = false;
    try {
      _jspx_th_form_005fselect_005f1.setPageContext(_jspx_page_context);
      _jspx_th_form_005fselect_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
      // /WEB-INF/views/menu/menu-permission.jsp(37,12) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fselect_005f1.setPath("menuId");
      // /WEB-INF/views/menu/menu-permission.jsp(37,12) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fselect_005f1.setCssClass("form-control");
      int[] _jspx_push_body_count_form_005fselect_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_form_005fselect_005f1 = _jspx_th_form_005fselect_005f1.doStartTag();
        if (_jspx_eval_form_005fselect_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_form_005foptions_005f1(_jspx_th_form_005fselect_005f1, _jspx_page_context, _jspx_push_body_count_form_005fselect_005f1))
              return true;
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_form_005fselect_005f1.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_form_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005fselect_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005fselect_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005fselect_005f1.doFinally();
      }
      _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fcssClass.reuse(_jspx_th_form_005fselect_005f1);
      _jspx_th_form_005fselect_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_form_005fselect_005f1, _jsp_getInstanceManager(), _jspx_th_form_005fselect_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_form_005foptions_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fselect_005f1, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fselect_005f1)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  form:options
    org.springframework.web.servlet.tags.form.OptionsTag _jspx_th_form_005foptions_005f1 = (org.springframework.web.servlet.tags.form.OptionsTag) _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fnobody.get(org.springframework.web.servlet.tags.form.OptionsTag.class);
    boolean _jspx_th_form_005foptions_005f1_reused = false;
    try {
      _jspx_th_form_005foptions_005f1.setPageContext(_jspx_page_context);
      _jspx_th_form_005foptions_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f1);
      // /WEB-INF/views/menu/menu-permission.jsp(38,13) name = items type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005foptions_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mapMenu}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_form_005foptions_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_form_005foptions_005f1 = _jspx_th_form_005foptions_005f1.doStartTag();
        if (_jspx_th_form_005foptions_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005foptions_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005foptions_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005foptions_005f1.doFinally();
      }
      _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fnobody.reuse(_jspx_th_form_005foptions_005f1);
      _jspx_th_form_005foptions_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_form_005foptions_005f1, _jsp_getInstanceManager(), _jspx_th_form_005foptions_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_form_005fradiobutton_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  form:radiobutton
    org.springframework.web.servlet.tags.form.RadioButtonTag _jspx_th_form_005fradiobutton_005f0 = (org.springframework.web.servlet.tags.form.RadioButtonTag) _005fjspx_005ftagPool_005fform_005fradiobutton_0026_005fvalue_005fpath_005fdisabled_005fnobody.get(org.springframework.web.servlet.tags.form.RadioButtonTag.class);
    boolean _jspx_th_form_005fradiobutton_005f0_reused = false;
    try {
      _jspx_th_form_005fradiobutton_005f0.setPageContext(_jspx_page_context);
      _jspx_th_form_005fradiobutton_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
      // /WEB-INF/views/menu/menu-permission.jsp(47,12) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fradiobutton_005f0.setPath("permission");
      // /WEB-INF/views/menu/menu-permission.jsp(47,12) name = disabled type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fradiobutton_005f0.setDisabled(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${viewOnly}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
      // /WEB-INF/views/menu/menu-permission.jsp(47,12) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fradiobutton_005f0.setValue("1");
      int[] _jspx_push_body_count_form_005fradiobutton_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_form_005fradiobutton_005f0 = _jspx_th_form_005fradiobutton_005f0.doStartTag();
        if (_jspx_th_form_005fradiobutton_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005fradiobutton_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005fradiobutton_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005fradiobutton_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fform_005fradiobutton_0026_005fvalue_005fpath_005fdisabled_005fnobody.reuse(_jspx_th_form_005fradiobutton_005f0);
      _jspx_th_form_005fradiobutton_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_form_005fradiobutton_005f0, _jsp_getInstanceManager(), _jspx_th_form_005fradiobutton_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_form_005fradiobutton_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  form:radiobutton
    org.springframework.web.servlet.tags.form.RadioButtonTag _jspx_th_form_005fradiobutton_005f1 = (org.springframework.web.servlet.tags.form.RadioButtonTag) _005fjspx_005ftagPool_005fform_005fradiobutton_0026_005fvalue_005fpath_005fdisabled_005fnobody.get(org.springframework.web.servlet.tags.form.RadioButtonTag.class);
    boolean _jspx_th_form_005fradiobutton_005f1_reused = false;
    try {
      _jspx_th_form_005fradiobutton_005f1.setPageContext(_jspx_page_context);
      _jspx_th_form_005fradiobutton_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
      // /WEB-INF/views/menu/menu-permission.jsp(48,12) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fradiobutton_005f1.setPath("permission");
      // /WEB-INF/views/menu/menu-permission.jsp(48,12) name = disabled type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fradiobutton_005f1.setDisabled(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${viewOnly}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
      // /WEB-INF/views/menu/menu-permission.jsp(48,12) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fradiobutton_005f1.setValue("0");
      int[] _jspx_push_body_count_form_005fradiobutton_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_form_005fradiobutton_005f1 = _jspx_th_form_005fradiobutton_005f1.doStartTag();
        if (_jspx_th_form_005fradiobutton_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005fradiobutton_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005fradiobutton_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005fradiobutton_005f1.doFinally();
      }
      _005fjspx_005ftagPool_005fform_005fradiobutton_0026_005fvalue_005fpath_005fdisabled_005fnobody.reuse(_jspx_th_form_005fradiobutton_005f1);
      _jspx_th_form_005fradiobutton_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_form_005fradiobutton_005f1, _jsp_getInstanceManager(), _jspx_th_form_005fradiobutton_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f0_reused = false;
    try {
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
      // /WEB-INF/views/menu/menu-permission.jsp(59,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!viewOnly}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<button class=\"btn btn-primary\" type=\"reset\">Reset</button>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-success\">Submit</button>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      _jspx_th_c_005fif_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f0_reused = false;
    try {
      _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f0.setParent(null);
      // /WEB-INF/views/menu/menu-permission.jsp(82,25) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f0.setValue("/menu/list");
      int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
      if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      _jspx_th_c_005furl_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f0, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f0_reused);
    }
    return false;
  }
}
