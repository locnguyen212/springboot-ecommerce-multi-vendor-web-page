package com.dodo.web.controllers.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodo.web.IServices.IOrderService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.helpers.SalesDataExcelExporter;
import com.dodo.web.modelview.OrderView;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("admin/sales-data")
public class SalesdataAdminController {
	@Autowired
	IUserService userService;
	
	@Autowired
	IOrderService orderService;
	 
	@GetMapping({"index", "", "/"})
	public String index(
			ModelMap modelMap, 
			Authentication authentication, 
			@RequestParam(value = "month", defaultValue = "0") int month, 
			@RequestParam(value = "year", defaultValue = "0") int year
			) {	
		modelMap.put("years", orderService.findSalesdataYear());
		modelMap.put("year", year);
		modelMap.put("months", List.of(0,1,2,3,4,5,6,7,8,9,10,11,12));
		modelMap.put("month", month);
		
		if(month == 0 && year == 0) {
			return "admins/salesData/index"; 
		}
		
		if(year != 0) {
			if(month != 0) {
				modelMap.put("salesdata", orderService.findSalesdataByYearAndMonth(year, month));
			}else if(month == 0) {
				modelMap.put("salesdata", orderService.findSalesdataByYear(year));
			}
		}
		
		return "admins/salesData/index"; 
	}
	
	@GetMapping({"export"})
	public void export(
			ModelMap modelMap, 
			HttpServletResponse response, 
			@RequestParam("month") int month, 
			@RequestParam("year") int year
			) {			
		try {
			response.setContentType("application/octet-stream");
	        var dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=salesdata_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<OrderView> salesdata = new ArrayList<OrderView>();
			if(month != 0) {
				salesdata = orderService.findSalesdataByYearAndMonth(year, month);
			}else{
				salesdata = orderService.findSalesdataByYear(year);
			}
	         
	        SalesDataExcelExporter excelExporter = new SalesDataExcelExporter(salesdata);
         
        
			excelExporter.export(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     	
	}
}

