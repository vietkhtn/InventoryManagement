<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Convert number to currency 1000000 => 1,000,000 use for price-->
<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
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
									<form:form modelAttribute="modelForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/menu/update-permission" method="POST">
																			
										<!-- Select Role -->
										<!-- Check if not in view action => select product, if in view action => show form product name -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="roleId">Role <span class="required">*</span></label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="roleId" cssClass="form-control">
													<form:options items="${mapRole}" />
												</form:select>
											</div>
										</div>
																		
										<div class="item form-group">
											<label for="menuId" class="col-form-label col-md-3 col-sm-3 label-align">Menu <span class="required">*</span></label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="menuId" cssClass="form-control">
													<form:options items="${mapMenu}" />
												</form:select>
											</div>
										</div>
										
										
										<div class="item form-group">
											<label for="permission" class="col-form-label col-md-3 col-sm-3 label-align">Permission </label>
											<div class="col-md-6 col-sm-6 ">
												<form:radiobutton path="permission" disabled="${viewOnly}" value="1"/> Yes &nbsp;&nbsp;
												<form:radiobutton path="permission" disabled="${viewOnly}" value="0"/> No
												<div class="has-error">
													<form:errors path="permission" cssClass="help-block"></form:errors>
												</div>
											</div>
										</div>
										
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
	$('#menu-permissionlistId').addClass('current-page').siblings().removeClass('current-page');
	var parents = $('#menu-permissionlistId').parents('li');
	parents.addClass('active').siblings().removeClass('active');
	$('#menu-permissionlistId').parents().show();
})
function cancel() {
	window.location.href = '<c:url value="/menu/list"/>';
}
</script>