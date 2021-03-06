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
									<form:form modelAttribute="modelForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/role/save" method="POST">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/> 
										<form:hidden path="createDate"/>
										
										<div class="item form-group">
											<label for="roleName" class="col-form-label col-md-3 col-sm-3 label-align">Role Name</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="roleName" cssClass="form-control"  disabled="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="roleName" cssClass="help-block"></form:errors>
												</div>
											</div>
										</div>
										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="description">Description </label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="description" cssClass="form-control" disabled="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="description" cssClass="help-block"></form:errors>
												</div>
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
	$('#rolelistId').addClass('current-page').siblings().removeClass('current-page');
	var parents = $('#rolelistId').parents('li');
	parents.addClass('active').siblings().removeClass('active');
	$('#rolelistId').parents().show();
})
function cancel() {
	window.location.href = '<c:url value="/role/list"/>';
}
</script>