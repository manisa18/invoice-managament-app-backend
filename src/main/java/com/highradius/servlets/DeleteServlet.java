package com.highradius.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }
    
 
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        // Get the DatabaseConnection instance from the ServletContext
        ServletContext context = getServletContext();
        InvoiceDao dao = new InvoiceDaoImpl(context);
        boolean deleted = dao.deleteInvoice(slNo);
        
        if (deleted) {
            // Set the response content type
            response.setContentType("text/html");
            
            // Set the response content type
            PrintWriter out = response.getWriter();
            
            // Write a success message
            out.println("<h2>Invoice deleted successfully!</h2>");
            
            // Close the response writer
            out.close();
        } else {
            // Write an error message
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invoice not found");
        }
    }
}
