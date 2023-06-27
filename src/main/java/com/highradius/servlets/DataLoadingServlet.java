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
import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;
import java.util.*;
/**
 * Servlet implementation class ReadServlet
 */
@WebServlet("/DataLoadingServlet")
public class DataLoadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DataLoadingServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set CORS headers
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

	    String customerOrderId = request.getParameter("customerOrderId");
	    String customerNumber = request.getParameter("customerNumber");
	    String salesOrg = request.getParameter("salesOrg");
	    String distributionChannel = request.getParameter("distributionChannel");
	    
		ServletContext context = getServletContext();
        InvoiceDao dao = new InvoiceDaoImpl(context);
        List<Invoice> invoices;
        
        if ((customerOrderId != null && !customerOrderId.isEmpty()) &&
        		(customerNumber != null && !customerNumber.isEmpty())&&
        		(salesOrg != null && !salesOrg.isEmpty())) {
            // Search invoices by customer order ID
        	int customerOrderIdQuery;
            try {
            	customerOrderIdQuery = Integer.parseInt(customerOrderId);
            } catch (NumberFormatException e) {
            	customerOrderIdQuery = -1;
            }
            
            int salesOrgQuery;
            try {
            	salesOrgQuery = Integer.parseInt(salesOrg);
            } catch (NumberFormatException e) {
            	salesOrgQuery = -1;
            }
            invoices = dao.searchInvoiceByQuery(customerOrderIdQuery,salesOrgQuery,customerNumber);
        } 
        else if (customerOrderId != null && !customerOrderId.isEmpty()) {
            // Search invoices by customer order ID
        	int customerOrderIdQuery;
            try {
            	customerOrderIdQuery = Integer.parseInt(customerOrderId);
            } catch (NumberFormatException e) {
            	customerOrderIdQuery = -1;
            }
            invoices = dao.searchInvoiceByCustomerOrderId(customerOrderIdQuery);
        } else {
            // Retrieve all invoices if no customer order ID is provided
            invoices = dao.getInvoice();
        }
        // Set the response content type
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
