package com.highradius.servlets;

import com.highradius.connection.DatabaseConnection;
import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditServlet() {
        super();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");

        String id = request.getParameter("slNo");
        long slNo;
        try {
            slNo = Long.parseLong(id);
        } catch (NumberFormatException e) {
            slNo = -1;
        }

        // Retrieve the updated values from the request parameters
        String distributionChannel = request.getParameter("distributionChannel");
        String companyCodeParam = request.getParameter("companyCode");
        int companyCode;
        try {
            companyCode = Integer.parseInt(companyCodeParam);
        } catch (NumberFormatException e) {
            companyCode = -1;
        }
        String orderCurrency = request.getParameter("orderCurrency");
        ServletContext context = getServletContext();
        InvoiceDao dao = new InvoiceDaoImpl(context);
        
        Invoice invoice = new Invoice();
        invoice.setSlNo(slNo);
        invoice.setDistributionChannel(distributionChannel);
        invoice.setCompanyCode(companyCode);
        invoice.setOrderCurrency(orderCurrency);
        
        boolean updated = dao.updateInvoice(slNo, invoice);

        // Set the response content type
        response.setContentType("text/html");

        // Get the response writer
        PrintWriter out = response.getWriter();

        if (updated) {
            out.println("<h2>Invoice updated successfully!</h2>");
        } else {
            out.println("<h2>Failed to update the invoice.</h2>");
        }

        // Close the response writer
        out.close();
    }
}
