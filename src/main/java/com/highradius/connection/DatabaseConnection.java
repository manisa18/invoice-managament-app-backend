package com.highradius.connection;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import com.highradius.model.Invoice;

public class DatabaseConnection {
	
	//SQL COMMANDS
    private static final String SELECT_ALL_INVOICE = "SELECT * FROM h2h_oap ";
    private static final String INSERT_INVOICE_SQL = "INSERT INTO h2h_oap (Sl_no, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE, ORDER_CURRENCY, ORDER_AMOUNT,  ORDER_CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_INVOICE_SQL = "UPDATE h2h_oap SET DISTRIBUTION_CHANNEL=?, COMPANY_CODE=?, ORDER_CURRENCY=? WHERE Sl_no = ?;";
    private static final String DELETE_INVOICE_SQL = "DELETE FROM h2h_oap WHERE Sl_No = ?;";

    // DatabaseConnection
    public static DatabaseConnection getDatabaseConnection(ServletContext context) {
        DatabaseConnection dbConnection = (DatabaseConnection) context.getAttribute("dbConnection");
        if (dbConnection == null) {
            dbConnection = new DatabaseConnection();
            context.setAttribute("dbConnection", dbConnection);
        }
        return dbConnection;
    }

    // get Connection
    public Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/hrc?useSSL=false";
        String username = "root";
        String password = "";

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database......" + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection is successful!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Get Invoice
    public List<Invoice> getInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_INVOICE)) {
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long Sl_no = rs.getLong("Sl_no");
                int CUSTOMER_ORDER_ID = rs.getInt("CUSTOMER_ORDER_ID");
                int SALES_ORG = rs.getInt("SALES_ORG");
                String DISTRIBUTION_CHANNEL = rs.getString("DISTRIBUTION_CHANNEL");
                int COMPANY_CODE = rs.getInt("COMPANY_CODE");
                String ORDER_CREATION_DATE = rs.getString("ORDER_CREATION_DATE");
                String ORDER_CURRENCY = rs.getString("ORDER_CURRENCY");
                String CUSTOMER_NUMBER = rs.getString("CUSTOMER_NUMBER");
                String ORDER_AMOUNT = rs.getString("ORDER_AMOUNT");
                invoices.add(new Invoice(Sl_no, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE,  ORDER_CURRENCY, ORDER_AMOUNT, ORDER_CREATION_DATE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    public List<Invoice> searchInvoices(int customerId) {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM h2h_oap WHERE CUSTOMER_ORDER_ID=?")) {
            statement.setInt(1, customerId); // Set the customer ID parameter in the query
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long Sl_no = rs.getLong("Sl_no");
                int CUSTOMER_ORDER_ID = rs.getInt("CUSTOMER_ORDER_ID");
                int SALES_ORG = rs.getInt("SALES_ORG");
                String DISTRIBUTION_CHANNEL = rs.getString("DISTRIBUTION_CHANNEL");
                int COMPANY_CODE = rs.getInt("COMPANY_CODE");
                String ORDER_CREATION_DATE = rs.getString("ORDER_CREATION_DATE");
                String ORDER_CURRENCY = rs.getString("ORDER_CURRENCY");
                String CUSTOMER_NUMBER = rs.getString("CUSTOMER_NUMBER");
                String ORDER_AMOUNT = rs.getString("ORDER_AMOUNT");
                invoices.add(new Invoice(Sl_no, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE,  ORDER_CURRENCY, ORDER_AMOUNT, ORDER_CREATION_DATE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    
    public List<Invoice> searchQueryInvoices(int customerId, int salesOrg, String customerNumber) {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM h2h_oap WHERE CUSTOMER_ORDER_ID=? OR SALES_ORG=? OR CUSTOMER_NUMBER=?")) {
            statement.setInt(1, customerId); // Set the customer ID parameter in the query
            statement.setInt(2, salesOrg); // Set the sales org parameter in the query
            statement.setString(3, customerNumber); // Set the customer number parameter in the query
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long Sl_no = rs.getLong("Sl_no");
                int CUSTOMER_ORDER_ID = rs.getInt("CUSTOMER_ORDER_ID");
                int SALES_ORG = rs.getInt("SALES_ORG");
                String DISTRIBUTION_CHANNEL = rs.getString("DISTRIBUTION_CHANNEL");
                int COMPANY_CODE = rs.getInt("COMPANY_CODE");
                String ORDER_CREATION_DATE = rs.getString("ORDER_CREATION_DATE");
                String ORDER_CURRENCY = rs.getString("ORDER_CURRENCY");
                String CUSTOMER_NUMBER = rs.getString("CUSTOMER_NUMBER");
                String ORDER_AMOUNT = rs.getString("ORDER_AMOUNT");
                invoices.add(new Invoice(Sl_no, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE, ORDER_CURRENCY, ORDER_AMOUNT, ORDER_CREATION_DATE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    
    public List<Invoice> analyticView(String customerNumber, String distributionChannel) {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM h2h_oap WHERE CUSTOMER_NUMBER=? OR DISTRIBUTION_CHANNEL=?")) {
            statement.setString(1, customerNumber); // Set the customer number parameter in the query
            statement.setString(2, distributionChannel); // Set the sales org parameter in the query
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long Sl_no = rs.getLong("Sl_no");
                int CUSTOMER_ORDER_ID = rs.getInt("CUSTOMER_ORDER_ID");
                int SALES_ORG = rs.getInt("SALES_ORG");
                String DISTRIBUTION_CHANNEL = rs.getString("DISTRIBUTION_CHANNEL");
                int COMPANY_CODE = rs.getInt("COMPANY_CODE");
                String ORDER_CREATION_DATE = rs.getString("ORDER_CREATION_DATE");
                String ORDER_CURRENCY = rs.getString("ORDER_CURRENCY");
                String CUSTOMER_NUMBER = rs.getString("CUSTOMER_NUMBER");
                String ORDER_AMOUNT = rs.getString("ORDER_AMOUNT");
                invoices.add(new Invoice(Sl_no, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE, ORDER_CURRENCY, ORDER_AMOUNT, ORDER_CREATION_DATE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    // Add Invoice
    public void addInvoice(Invoice invoice) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVOICE_SQL)) {
        	preparedStatement.setLong(1, invoice.getSlNo());
        	preparedStatement.setInt(2, invoice.getCustomerOrderId());
        	preparedStatement.setInt(3, invoice.getSalesOrg());
        	preparedStatement.setString(4, invoice.getDistributionChannel());
        	preparedStatement.setString(5, invoice.getCustomerNumber());
        	preparedStatement.setInt(6, invoice.getCompanyCode());
        	preparedStatement.setString(7, invoice.getOrderCurrency());
        	preparedStatement.setString(8, invoice.getOrderAmount());
        	preparedStatement.setString(9, invoice.getOrderCreationDate());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Delete Invoice
    public boolean deleteInvoices(long id) throws SQLException {
    	boolean rowDeleted;
    	try (Connection connection = getConnection();
    			PreparedStatement statement = connection.prepareStatement(DELETE_INVOICE_SQL)) {
    		statement.setLong(1, id);
    		rowDeleted = statement.executeUpdate() > 0;
    	}
    	return rowDeleted;
    }

 
    public boolean updateInvoice(long slNo,Invoice invoice) {
    	boolean updated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVOICE_SQL)) {
            preparedStatement.setString(1, invoice.getDistributionChannel());
            preparedStatement.setInt(2, invoice.getCompanyCode());
            preparedStatement.setString(3, invoice.getOrderCurrency());
            preparedStatement.setLong(4, slNo); // Set the ID for WHERE clause

            int rowsUpdated = preparedStatement.executeUpdate();
            updated = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

}
