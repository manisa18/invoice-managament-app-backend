package com.highradius.implementation;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

public class InvoiceDaoImpl implements InvoiceDao {
	//connect database
    private DatabaseConnection dbConnection;

    public InvoiceDaoImpl(ServletContext context) {
        this.dbConnection = DatabaseConnection.getDatabaseConnection(context);
    }

    //Get Invoice
    @Override
    public List<Invoice> getInvoice() {
    	
        List<Invoice> invoices = dbConnection.getInvoices();
        return invoices;
    }
    
    public List<Invoice> searchInvoiceByCustomerOrderId(int custId){
    	List<Invoice> invoices = dbConnection.searchInvoices(custId);
        return invoices;
    }
    
    public List<Invoice> searchInvoiceByQuery(int custId, int salesOrg, String custNo)
    {
    	List<Invoice> invoices = dbConnection.searchQueryInvoices(custId,salesOrg,custNo);
        return invoices;
    }
    //Update Invoice
    @Override
    public boolean updateInvoice(long id, Invoice updatedInvoice) {
    	return dbConnection.updateInvoice(id,updatedInvoice);
    }
    
    //Delete Invoice
    @Override
    public boolean deleteInvoice(long id) {
    	boolean deleted = false;
		try {
			deleted = dbConnection.deleteInvoices(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return deleted;
    }

    //Add Invoice
    @Override
    public void insertInvoice(Invoice invoice) {
        dbConnection.addInvoice(invoice);
    }

	@Override
	public List<Invoice> analyticView(String customerNumber, String distributionChannel) {
		List<Invoice> invoices = dbConnection.analyticView(customerNumber, distributionChannel);
        return invoices;
	}
}
