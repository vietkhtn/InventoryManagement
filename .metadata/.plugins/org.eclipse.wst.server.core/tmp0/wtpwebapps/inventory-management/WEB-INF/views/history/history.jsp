<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Convert number to currency 1000000 => 1,000,000 use for price-->
<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
<div class="right_col" role="main">
	<div class="">
		<div class="col-md-12 col-sm-12  ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>History </h2>
                    <div class="clearfix"></div>
                  </div>

                  <div class="x_content">
                  <!-- Form Search -->
                  <div class="container" style="padding: 50px;">
						<form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/history/list/1" method="POST">

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="code">Code </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="productInfo.code" cssClass="form-control col-md-7 col-xs-12"  />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="cateId">Category 
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:select path="productInfo.category.id" cssClass="form-control">
										<option value=""/>
										<form:options items="${mapCategory}" />
									</form:select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Action </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:select path="actionName" cssClass="form-control">
										<option value=""/>
										<form:options items="${mapAction}"/>
									</form:select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Type </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:select path="type" cssClass="form-control">
										<form:options items="${mapType}"/>
									</form:select>
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
                            <th class="column-title">Category </th>
                            <th class="column-title">Code </th>
                            <th class="column-title">Name </th>
                            <th class="column-title">Qty </th>
                            <th class="column-title">Price </th>
                            <th class="column-title">Type </th>
                            <th class="column-title">Action </th>
                            <th class="column-title">Time </th>                            
                          </tr>
                        </thead>

                        <tbody>
                        <!-- even/odd pointer is determind to highlight even row(lighter than odd row) => for beauty -->
                        <c:forEach items="${histories}" var="history" varStatus="loop">
                        	<c:choose>
                        		<c:when test="${loop.index % 2 == 0}">
                        			 <tr class="even pointer">
                        		</c:when>
                        		<c:otherwise>
                        		 	<tr class="odd pointer">
                        		</c:otherwise>
                        	</c:choose>
                            <td class=" ">${pageInfo.getOffset() + loop.index + 1}</td>
                            <td class=" ">${history.productInfo.category.name }</td>
                            <td class=" ">${history.productInfo.code } </td>
                            <td class=" ">${history.productInfo.name }</td>
                            <td class=" ">${history.qty }</td>
                            <td class="priceFormat">${history.price }</td>
                            <!-- If history type = 1 -> row is Good Receipt / type = 2 => row is Goods Issue -->
                            <c:choose>
                            	<c:when test="${history.type == 1 }">
                            		<td class=" ">Goods Receipt</td>
                            	</c:when>
                            	<c:otherwise>
                            		<td class=" ">Goods Issues</td>
                            	</c:otherwise>
                            </c:choose>
                            <td class=" ">${history.actionName }</td>
                            <td class=" ">${history.updateDate }</td>
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
	if (confirm("Do you want delete this product ?")) {
		window.location.href = '<c:url value="/history/delete/"/>' + id;
	}
}

/* If backend return any message success/error => use PNotify to show message */
$(document).ready(function(){
	processMessage();
	$(".priceFormat").each(function(){ // for price in Prices
		$(this).text(numeral($(this).text()).format('0,0'));
	})
})

function gotoPage(page) {
	$('#searchForm').attr('action', '<c:url value="/history/list/"/>' + page);
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