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
									<form:form modelAttribute="modelForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/product-info/save" method="POST" enctype="multipart/form-data">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/> 
										<form:hidden path="createDate"/>
										<form:hidden var="imgSrc" path="imgUrl"/>
										
										<!-- Select category -->
										<!-- Check if not in view action => select category, if in view action => show form category name -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="cateId">Category <span class="required">*</span></label>
											<div class="col-md-6 col-sm-6 ">
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="cateId" cssClass="form-control">
															<form:options items="${mapCategory}" />
														</form:select>
														<div class="has-error">
															<form:errors path="cateId" cssClass="help-block"></form:errors>
														</div>
													</c:when>
													<c:otherwise>
														<form:input path="category.name" disabled="true" cssClass="form-control"/>
													</c:otherwise>
												</c:choose>				
											</div>
										</div>
																		
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="code">Code <span class="required">*</span></label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="code" cssClass="form-control " disabled="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="code" cssClass="help-block"></form:errors>
												</div>
											</div>
										</div>
										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Name <span class="required">*</span></label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="name" cssClass="form-control" disabled="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="name" cssClass="help-block"></form:errors>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label for="description" class="col-form-label col-md-3 col-sm-3 label-align">Description</label>
											<div class="col-md-6 col-sm-6 ">
												<form:textarea path="description" cssClass="form-control"  disabled="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="description" cssClass="help-block"></form:errors>
												</div>
											</div>
										</div>
						
										<!-- Upload file -->
										<c:if test="${!viewOnly}">
											<div class="item form-group">
												<label for="multipartFile" class="col-form-label col-md-3 col-sm-3 label-align">Image Upload</label>
												<div class="col-md-6 col-sm-6 ">
													<form:input path="multipartFile" cssClass="form-control"  type="file"/>
													<div class="has-error">
														<form:errors path="multipartFile" cssClass="help-block"></form:errors>
													</div>
												</div>
											</div>
										</c:if>			
										
										<!-- Image -->
										<div class="item form-group">
											<label for="imgUrl" class="col-form-label col-md-3 col-sm-3 label-align">Image</label>
											<div class="col-md-6 col-sm-6 ">
                            			    	<img src="<c:url value="${imgUrl}"/>" width="300px" height="300px"/>   
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
	$('#categorylistId').addClass('current-page').siblings().removeClass('current-page');
	var parents = $('#categorylistId').parents('li');
	parents.addClass('active').siblings().removeClass('active');
	$('#categorylistId').parents().show();
})
function cancel() {
	window.location.href = '<c:url value="/product-info/list"/>';
}
</script>