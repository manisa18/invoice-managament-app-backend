package com.highradius.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import com.highradius.implementation.*;
import com.highradius.model.Invoice;
import java.util.*;
/**
 * Servlet implementation class AnalyticView
 */
@WebServlet("/AnalyticView")
public class AnalyticView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalyticView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 response.setHeader("Access-Control-Allow-Origin", "*");
		    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

		    
	    String customerNumber = request.getParameter("customerNumber");	    
	    String distributionChannel = request.getParameter("distributionChannel");
	    
		ServletContext context = getServletContext();
        InvoiceDao dao = new InvoiceDaoImpl(context);
        List<Invoice> invoices = null;
		if ((customerNumber != null && !customerNumber.isEmpty())&&(distributionChannel != null && !distributionChannel.isEmpty())) {
            // Search invoices by customer order ID
            invoices = dao.analyticView(customerNumber,distributionChannel);
        }
		response.setContentType("application/json");

        // Create a JSON converter (Gson)
        Gson gson = new Gson();

        // Convert invoices list to JSON
        String jsonInvoices = gson.toJson(invoices);

        // Get the response writer
        PrintWriter out = response.getWriter();

        // Write the JSON response
        out.println(jsonInvoices);

        // Close the response writer
        out.close();
	}

	

}
