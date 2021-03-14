<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Paging -->
<ul class="pagination">
	<c:forEach begin="1" end="${pageInfo.totalPages}" varStatus="loop">
		<!-- Check if current page == page user click => if equal, do nothing/ if not equal, redirect to that page -->
		<c:choose>
			<c:when test="${pageInfo.indexPage == loop.index}">
				<li class="active"><a href="javascript:void(0);">${loop.index}</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:void(0)"
					onclick="gotoPage(${loop.index});">${loop.index}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</ul>