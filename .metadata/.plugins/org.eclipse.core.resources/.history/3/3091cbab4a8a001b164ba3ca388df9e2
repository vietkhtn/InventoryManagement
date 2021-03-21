<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h2>${titlePage}</h2>
			</div>
		</div>
		<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
						
								<div class="x_content"> 
									<br />
									<form:form modelAttribute="modelForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/user/save" method="POST">
										
										<div class="item form-group">
											<label for="name" class="col-form-label col-md-3 col-sm-3 label-align">Full Name</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="name" cssClass="form-control"  disabled="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="name" cssClass="help-block"></form:errors>
												</div>
											</div>
										</div>
										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="email">Email </label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="email" cssClass="form-control" disabled="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="email" cssClass="help-block"></form:errors>
												</div>
											</div>
										</div>
																												
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="userName">Username <span class="required">*</span></label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="userName" cssClass="form-control " disabled="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="userName" cssClass="help-block"></form:errors>
												</div>
											</div>
										</div>
										
										<c:if test="${editMode == null}">
											<div class="form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Password <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:input path="password" type="password" cssClass="form-control col-md-7 col-xs-12" />
													<div class="has-error">
														<form:errors path="password" cssClass="help-block"></form:errors>
													</div>
												</div>
											</div>
										</c:if>
										
										<!-- Select role -->
										<!-- Check if not in view action => select role, if in view action => show form role name -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="role">Role <span class="required">*</span></label>
											<div class="col-md-6 col-sm-6 ">
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="roleId" cssClass="form-control">
															<form:options items="${mapRole}" />
														</form:select>
														<div class="has-error">
															<form:errors path="roleId" cssClass="help-block"></form:errors>
														</div>
													</c:when>
													<c:otherwise>
													<form:input path="roleId" cssClass="form-control " disabled="${viewOnly}"/>
														 <%-- <!-- If roleId = 1 -> row is admin/ type = 2 => row is staff -->
								                            <c:choose>
								                            	<c:when test="${roleId == 1 }">
								                            		<input value="admin" class="form-control" disabled="${viewOnly}"/>
								                            	</c:when>
								                            	<c:otherwise>
								                            		<input value="staff" class="form-control" disabled="${viewOnly}"/>
								                            	</c:otherwise>
								                            </c:choose> --%>
													</c:otherwise>
												</c:choose>				
											</div>
										</div>
																										
										<!-- line seperated -->
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-primary" type="button" onclick="cancel();">Cancel</button>
												<c:if test="${!viewOnly}">
													<button class="btn btn-primary" type="reset">Reset</button>
													<button type="submit" class="btn btn-success">Submit</button>
												</c:if>
											</div>
										</div>
										
									</form:form>
								</div>
							</div>
						</div>
					</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// Determind url at current page to highligh in side-bar
	$('#userlistId').addClass('current-page').siblings().removeClass('current-page');
	var parents = $('#userlistId').parents('li');
	parents.addClass('active').siblings().removeClass('active');
	$('#userlistId').parents().show();
})
function cancel() {
	window.location.href = '<c:url value="/user/list"/>';
}
</script>