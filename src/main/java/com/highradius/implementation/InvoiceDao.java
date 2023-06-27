package com.highradius.implementation;

import java.util.*;

import com.highradius.model.*;

public interface InvoiceDao {

	List<Invoice> getInvoice();
    void insertInvoice(Invoice invoice);
    boolean updateInvoice(long id, Invoice invoice);
    boolean deleteInvoice(long id);
	List<Invoice> searchInvoiceByCustomerOrderId(int custId);
	List<Invoice> searchInvoiceByQuery(int custId, int salesOrg, String custNo);
	List<Invoice> analyticView(String customerNumber, String distributionChannel);
}
