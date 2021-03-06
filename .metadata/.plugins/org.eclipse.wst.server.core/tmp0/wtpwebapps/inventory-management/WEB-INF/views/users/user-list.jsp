<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="right_col" role="main">
	<div class="">
		<div class="col-md-12 col-sm-12  ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>User List </h2>
                    <div class="clearfix"></div>
                  </div>

                  <div class="x_content">
                  <!-- Add Button -->
                  <a class="btn btn-app" href="<c:url value="/user/add"/>"><i class="fa fa-plus"></i> Add</a>
                  <!-- Form Search -->
                  <div class="container" style="padding: 50px;">
						<form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/user/list/1" method="POST">
						
						
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="userName">Username </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="userName" cssClass="form-control col-md-7 col-xs-12"  />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Name </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="name" cssClass="form-control col-md-7 col-xs-12"  />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
										<button type="submit" class="btn btn-success">Search</button>
								</div>
							</div>

						</form:form>
					</div>
                  <div class="ln_solid"></div>
					  <!-- Table Category -->
	                    <div class="table-responsive">
	                      <table class="table table-striped jambo_table bulk_action">
	                        <thead>
	                          <tr class="headings">
	                            <th class="column-title"># </th>
	                            <th class="column-title">Id </th>
	                            <th class="column-title">Name </th>
	                            <th class="column-title">Username </th>
	                            <th class="column-title">Password </th>
	                            <th class="column-title">Role </th>
	                            <th class="column-title">Email </th>                      
	                            <th class="column-title no-link last text-center" colspan = "3"><span class="nobr">Action</span>
	                            </th>
	                          </tr>
	                        </thead>
	
	                        <tbody>
	                        <!-- even/odd pointer is determind to highlight even row(lighter than odd row) => for beauty -->
	                        <c:forEach items="${users}" var="user" varStatus="loop">
	                        	<c:choose>
	                        		<c:when test="${loop.index % 2 == 0}">
	                        			 <tr class="even pointer">
	                        		</c:when>
	                        		<c:otherwise>
	                        		 	<tr class="odd pointer">
	                        		</c:otherwise>
	                        	</c:choose>
	                            <td class=" ">${pageInfo.getOffset() + loop.index + 1}</td>
	                            <td class=" ">${user.id }</td>
	                            <td class=" ">${user.name }</td>
	                            <td class=" ">${user.userName } </td>
	                            <td class=" ">${user.password }</td>
	                             <!-- If user role = 1 -> role is admin / role = 2 => role is staff -->
	                            <c:choose>
	                            	<c:when test="${user.roleId == 1 }">
	                            		<td class=" ">admin	</td>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<td class=" ">staff </td>
	                            	</c:otherwise>
	                            </c:choose>
	                            <td class=" ">${user.email }</td>
	                            <td class="text-center"><a href="<c:url value="/user/view/${user.id}"/>" class="btn btn-round btn-default" href="#">View</a>
	                            <td class="text-center"><a href="<c:url value="/user/edit/${user.id}"/>" class="btn btn-round btn-primary" href="#">Edit</a>
	                            <td class="text-center"><a onclick="confirmDelete(${user.id});" class="btn btn-round btn-danger" href="#">Delete</a>
	                            </td>
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
function confirmDelete(id){
	if (confirm("Do you want delete this user ?")) {
		window.location.href = '<c:url value="/user/delete/"/>' + id;
	}
}

/* If backend return any message success/error => use PNotify to show message */
$(document).ready(function(){
	processMessage();
})

function gotoPage(page) {
	$('#searchForm').attr('action', '<c:url value="/user/list/"/>' + page);
	$('#searchForm').submit();
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