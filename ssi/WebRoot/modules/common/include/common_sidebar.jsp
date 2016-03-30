	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
			<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
			<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
			<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<li class="start active open">
					<a href="${ctx}/index.action">
					<i class="icon-home"></i>
					<span class="title">首页</span>
					<span class="selected"></span>
					</a>
					<ul class="sub-menu">
						<%--<li>
							<a href="${ctx}/user/index.action">
							<i class="icon-user"></i>
							用户管理</a>
						</li>
						<li>
							<a href="${ctx}/menu/index.action">
							<i class="icon-basket"></i>
							菜单管理</a>
						</li>
						<li>
							<a href="${ctx}/classes/index.action">
							<i class="icon-list"></i>
							班级管理</a>
						</li> 
						<li>
							<a href="${ctx}/testQuestion/index.action">
							<i class="icon-question"></i>
							试题管理</a>
						</li> 
						<li>
							<a href="${ctx}/testPaper/index.action">
							<i class="icon-book-open"></i>
							试卷管理</a>
						</li> 
					--%></ul>
				</li>
				<%--<li>
					<a href="javascript:;">
					<i class="icon-settings"></i>
					<span class="title">系统管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="javascript:;">
							<i class="icon-basket"></i>
							菜单管理</a>
						</li>
						<li>
							<a href="javascript:;">
							<i class="icon-users"></i>
							角色管理</a>
						</li>
						<li>
							<a href="javascript:;">
							<i class="icon-tag"></i>
							权限管理</a>
						</li>
						<li>
							<a href="javascript:;">
							<i class="icon-notebook"></i>
							日志管理</a>
						</li>
						<li>
							<a href="${ctx}/dict/index.action">
							<i class="icon-handbag"></i>
							数据字典</a>
						</li>
						<li>
							<a href="javascript:;">
							<i class="icon-wallet"></i>
							文档管理</a>
						</li>
						<li>
							<a href="javascript:;">
							<i class="icon-envelope-open"></i>
							消息管理</a>
						</li>
						<li>
							<a href="${ctx}/org/index.action">
							<i class="icon-pencil"></i>
							组织机构</a>
						</li>
						
						<li>
							<a href="${ctx}/test/index.action">
							<i class="icon-envelope"></i>
							<i class="icon-doc"></i>
							<i class="icon-clock"></i>
							<i class="icon-login"></i>
							<i class="icon-logout"></i>
							<i class="icon-note"></i>
							管理</a>
						</li> 
					</ul>
				</li>--%>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>