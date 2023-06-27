package com.highradius.servlets;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
     // Read the JSON payload from the request body
        BufferedReader reader = request.getReader();
        StringBuilder payload = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            payload.append(line);
        }

        // Parse the JSON payload
        JSONObject json = new JSONObject(payload.toString());

        long slNo = json.getLong("slNo");
        int customerOrderId = json.getInt("customerOrderId");
        int salesOrg = json.getInt("salesOrg");
        String distributionChannel = json.getString("distributionChannel");
        String customerNumber = json.getString("customerNumber");
        int companyCode = json.getInt("companyCode");
        String orderCurrency = json.getString("orderCurrency");
        String orderAmount = json.getString("orderAmount");
        String orderCreationDate = json.getString("orderCreationDate");

        Invoice invoice = new Invoice(slNo, customerOrderId, salesOrg, distributionChannel,customerNumber, companyCode, orderCurrency, orderAmount, orderCreationDate);

        // Get the DatabaseConnection instance from the ServletContext
        ServletContext context = getServletContext();
        InvoiceDao dao = new InvoiceDaoImpl(context);
        
        
        dao.insertInvoice(invoice);

        // Set the response content type
        response.setContentType("text/html");

        // Get the response writer
        PrintWriter out = response.getWriter();

        // Write a success message
        out.println("<h2>Invoice added successfully!</h2>");

        // Close the response writer
        out.close();
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set CORS headers for preflight requestresponse.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
        response.setHeader("Access-Control-Max-Age", "86400"); // 24 hours

        // Return a successful response
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
