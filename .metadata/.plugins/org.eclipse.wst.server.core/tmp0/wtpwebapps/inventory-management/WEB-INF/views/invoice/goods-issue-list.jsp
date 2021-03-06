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
                    <h2>Goods Issue List </h2>
                    <div class="clearfix"></div>
                  </div>

                  <div class="x_content">
                  <!-- Add Button -->
                  <a class="btn btn-app" href="<c:url value="/goods-issue/add"/>"><i class="fa fa-plus"></i> Add</a>
                  <!-- Export Button -->
                  <a class="btn btn-app" href="<c:url value="/goods-issue/export"/>"><i class="fa fa-cloud-download"></i> Export</a>
                  <!-- Form Search -->
                  <div class="container" style="padding: 50px;">
						<form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/goods-issue/list/1" method="POST">

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="code">Code </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="code" cssClass="form-control col-md-7 col-xs-12"  />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="fromDate">From Date </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="input-group date" id="fromDatePicker">
										<form:input path="fromDate" cssClass="form-control col-md-7 col-xs-12"  />
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="toDate">To Date </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="input-group date" id="toDatePicker">
										<form:input path="toDate" cssClass="form-control col-md-7 col-xs-12"  />
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
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
                            <th class="column-title">Code </th>
                            <th class="column-title">Product </th>
                            <th class="column-title">Qty </th>
                            <th class="column-title">Unit Price </th>
                            <th class="column-title">Total Price </th>
                            <th class="column-title">Update Date </th>
                            <th class="column-title no-link last text-center" colspan = "3"><span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                        <!-- even/odd pointer is determind to highlight even row(lighter than odd row) => for beauty -->
                        <c:forEach items="${invoices}" var="invoice" varStatus="loop">
                        	<c:choose>
                        		<c:when test="${loop.index % 2 == 0}">
                        			 <tr class="even pointer">
                        		</c:when>
                        		<c:otherwise>
                        		 	<tr class="odd pointer">
                        		</c:otherwise>
                        	</c:choose>
                            <td class=" ">${pageInfo.getOffset() + loop.index + 1}</td>
                            <td class=" ">${invoice.code } </td>
                            <td class=" ">${invoice.productInfo.name }</td>
                            <td class=" ">${invoice.qty }</td>
                            <td class="priceFormat">${invoice.price }</td>
                            <td class="priceFormat">${invoice.price * invoice.qty}</td>    
                            <td class="date">${invoice.updateDate }</td>
                            
                            <td class="text-center"><a href="<c:url value="/goods-issue/view/${invoice.id}"/>" class="btn btn-round btn-default" href="#">View</a>
                            <td class="text-center"><a href="<c:url value="/goods-issue/edit/${invoice.id}"/>" class="btn btn-round btn-primary" href="#">Edit</a>
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
/* If backend return any message success/error => use PNotify to show message */
$(document).ready(function(){
	processMessage();
	$("#fromDatePicker").datetimepicker({
		format: 'YYYY-MM-DD HH:mm:ss'
	});
	$("#toDatePicker").datetimepicker({
		format: 'YYYY-MM-DD HH:mm:ss'
	});
	$(".priceFormat").each(function(){ // for price in Prices
		$(this).text(numeral($(this).text()).format('0,0'));
	})
})

function gotoPage(page) {
	$('#searchForm').attr('action', '<c:url value="/goods-issue/list/"/>' + page);
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