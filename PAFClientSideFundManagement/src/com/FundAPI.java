package com;

import java.io.IOException;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/FundAPI")
public class FundAPI extends HttpServlet {
	
	Fund FundObj = new Fund();
	
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = FundObj.insertFund(request.getParameter("name"), 
				 request.getParameter("address"), 
				request.getParameter("amount"), 
				request.getParameter("email"),
				request.getParameter("phone"),
				request.getParameter("nic")); 
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	// Convert request parameters to a Map
	
		 
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Map paras = getParasMap(request); 
		 String output = FundObj.updateFund(paras.get("hidFundIDSave").toString(), 
		 paras.get("name").toString(), 
		 paras.get("address").toString(), 
		 paras.get("amount").toString(), 
		paras.get("email").toString(), 
		paras.get("phone").toString(),
		paras.get("nic").toString()); 
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 String output = FundObj.deleteFund(paras.get("id").toString()); 
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}

		 

}
