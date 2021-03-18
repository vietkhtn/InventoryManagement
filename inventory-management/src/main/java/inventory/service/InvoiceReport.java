package inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import inventory.model.Invoice;
import inventory.util.Constant;
import inventory.util.DateUtil;

public class InvoiceReport extends AbstractXlsxView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// Get list good-receipt
		List<Invoice> invoices = new ArrayList<Invoice>();
		if ((List<Invoice>) model.get(Constant.KEY_GOODS_RECEIPT_REPORT) != null) {
			 invoices=(List<Invoice>) model.get(Constant.KEY_GOODS_RECEIPT_REPORT);
		}else {
			invoices =(List<Invoice>) model.get(Constant.KEY_GOODS_ISSUE_REPORT);
		}
		
		String fileName = "", titleName = "";
		if(invoices.get(0).getType() == Constant.TYPE_GOODS_RECEIPT) {
			fileName = "invoice-import-" + DateUtil.currentMillisToDateTimeString(System.currentTimeMillis()) +".xlsx";
			titleName = "IMPORT INVOICE REPORT";
		}else {
			fileName = "invoice-export-" + DateUtil.currentMillisToDateTimeString(System.currentTimeMillis()) +".xlsx";
			titleName = "EXPORT INVOICE REPORT";
		}
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		// Create new sheet 
		Sheet sheet = workbook.createSheet("data");
		// Create Title Row
		Row title = sheet.createRow(0);
		title.createCell(0).setCellValue(titleName);
		//Create Header Row
		Row header = sheet.createRow(1);
		header.createCell(0).setCellValue("#");
		header.createCell(1).setCellValue("Code");
		header.createCell(2).setCellValue("Qty");
		header.createCell(3).setCellValue("Price");
		header.createCell(4).setCellValue("Product");
		header.createCell(5).setCellValue("Update Date");
		

		int rowNum = 2;
		for (Invoice invoice : invoices) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(rowNum - 1);
			row.createCell(1).setCellValue(invoice.getCode());
			row.createCell(2).setCellValue(invoice.getQty());
			row.createCell(3).setCellValue(invoice.getPrice().toString());
			row.createCell(4).setCellValue(invoice.getProductInfo().getName());
			row.createCell(5).setCellValue(DateUtil.dateToString(invoice.getUpdateDate()));
		}
	}

}
