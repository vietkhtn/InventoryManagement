<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="right_col" role="main">
	<div class="">
		<div class="col-md-12 col-sm-12  ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Role List </h2>
                    <div class="clearfix"></div>
                  </div>

                  <div class="x_content">
                  <!-- Add Permission Button -->
                  <a class="btn btn-app" href="<c:url value="/menu/permission"/>"><i class="fa fa-plus"></i>Permission</a>
                  <!-- Form Search -->
                  <div class="container" style="padding: 50px;">
						<form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/menu/list/1" method="POST"></form:form>
					</div>
	                  <div class="ln_solid"></div>
						  <!-- Table Category -->
		                    <div class="table-responsive">
		                      <table class="table table-striped jambo_table bulk_action">
		                        <thead>
		                          <tr class="headings">
		                            <th rowspan="2" class="column-title" style="border-left: 2px solid"># </th>
		                            <th rowspan="2" class="column-title text-center" style="border-left: 2px solid">Url </th>
		                            <th rowspan="2" class="column-title text-center" style="border-left: 2px solid">Status </th>
		                            <th class="column-title text-center" colspan="${roles.size()}" style="border-left: 2px solid">Role </th>
		                            
		                            <!-- List all role name in Role column -->                    
		                            <tr >
		                            	<c:forEach var="role" items="${roles}">
		                            		 <th class="column-title" style="border-left: 2px solid">${role.roleName}</th>
		                            	</c:forEach>
		                            </tr>
		                          
		                        </thead>
		
		                        <tbody>
		                        <!-- even/odd pointer is determind to highlight even row(lighter than odd row) => for beauty -->
		                        <c:forEach items="${menus}" var="menu" varStatus="loop">
		                        	<c:choose>
		                        		<c:when test="${loop.index % 2 == 0}">
		                        			 <tr class="even pointer">
		                        		</c:when>
		                        		<c:otherwise>
		                        		 	<tr class="odd pointer">
		                        		</c:otherwise>
		                        	</c:choose>
		                            <td class=" ">${pageInfo.getOffset() + loop.index + 1}</td>
		                            
		                           <!--  URL -->
		                            <td class=" ">${menu.url }</td>
		                            
		                            <!-- Status -->
		                            <c:choose>
		                            	<c:when test="${menu.activeFlag == 1 }">
		                            		<td><a href="javascript:void(0)" onclick="confirmChange(${menu.id}, ${menu.activeFlag});" class="btn btn-danger btn-sm">Disable</a></td>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<td><a href="javascript:void(0)" onclick="confirmChange(${menu.id}, ${menu.activeFlag});" class="btn btn-success btn-sm">Enable</a></td>
		                            	</c:otherwise>
		                            </c:choose>
		                            
									<!-- Check Authorize permission for each role; if permission = 1 => O / = 0 = > X-->
									<c:forEach items="${menu.mapAuth}" var="mapAuth">
										<c:choose>
											<c:when test="${mapAuth.value == 1 }">
												<td><i class="fa fa-check" style="color:green"></i></td>
											</c:when>
											<c:otherwise>
												<td><i class="fa fa-times" style="color:red"></i></td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
		                         </c:forEach>
		                          
		                        </tbody>
		                      </table>
	                     <jsp:include page="../layout/paging.jsp"></jsp:include>
	                    </div>
	                  </div>
	                </div>
	              </div>
	</div>
</div>
<script>
/* If backend return any message success/error => use PNotify to show message */
$(document).ready(function(){
	processMessage();
})

function gotoPage(page) {
	$('#searchForm').attr('action', '<c:url value="/menu/list/"/>' + page);
	$('#searchForm').submit();
}

function confirmChange(id, activeFlag) {
	var msg = activeFlag == 1 ? 'Do you want to disable this menu ?' : 'Do you want to enable this menu ?'
	if (confirm(msg)) {
		window.location.href = '<c:url value="/menu/change-status/"/>' + id;
	}
}

function processMessage() {
	// Get message variable in Constant class
	var msgSuccess = "${successMsg}";
	var msgError = "${errorMsg}";
	// if has any message => show by  PNotify
	if (msgSuccess) {
		new PNotify({
            title: 'Success',
            text: msgSuccess,
            type: 'success',
            styling: 'bootstrap3'
        });
	}
	if(msgError) {
		new PNotify({
            title: 'Error',
            text: msgError,
            type: 'error',
            styling: 'bootstrap3'
        });
	}
}
</script>