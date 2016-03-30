<%@page import="cn.itcast.ssi.common.constant.SystemProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- ContextPath --%>
<c:set var="ctx" value="<%=request.getContextPath()%>"></c:set>
<%-- StaticFilePath --%>
<%--
<c:set var="sfp" value="<%=request.getContextPath()%>"></c:set>
--%>
<c:set var="sfp" value="<%=SystemProperties.STATIC_PATH%>"></c:set>