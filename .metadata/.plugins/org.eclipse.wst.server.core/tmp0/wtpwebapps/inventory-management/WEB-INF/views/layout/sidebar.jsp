<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="menu_section">
		<h3>General</h3>
		<ul class="nav side-menu">
			<c:forEach items="${menuSession}" var="menu">
				<li id="${menu.idMenu}">
					
					<a>
						<!-- Icon -->
						<c:choose>
							<c:when test="${menu.idMenu == 'productId'}">
								<i class="fa fa-archive"></i>
							</c:when>
							<c:when test="${menu.idMenu == 'stockId'}">
								<i class="fa fa-cubes"></i>
							</c:when>
							<c:otherwise>
								<i class="fa fa-group"></i>
							</c:otherwise>
						</c:choose> 
						<!-- Menu name -->
						${menu.name} 
						<!-- Drop down Icon -->
						<span class="fa fa-chevron-down"></span>
					</a>
					<ul class="nav child_menu">
						<c:forEach items="${menu.child}" var="child">
							<li id="${child.idMenu}"><a href="<c:url value="${child.url}"/>">${child.name}</a></li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>