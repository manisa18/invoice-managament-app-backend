package com.highradius.model;

public class Invoice {
	private Long SlNo;
	private int CustomerOrderId;
	private int SalesOrg;
	private String DistributionChannel;
	private int CompanyCode;
	private String OrderCreationDate;
	private String OrderCurrency;
	private String CustomerNumber;
	private String AmountinUSD;
	private String OrderAmount;

	public Invoice() {
		    	
		    }
    // Parameterized constructor
    public Invoice(Long SlNo,
    		int CustomerOrderId,
    		int SalesOrg,
	String DistributionChannel,
	String CustomerNumber,
	int CompanyCode,
	String OrderCurrency,
	String OrderAmount,
	String OrderCreationDate) {
    	this.SlNo=SlNo;
    	this.CustomerOrderId=CustomerOrderId;
    	this.SalesOrg=SalesOrg;
    	this.DistributionChannel=DistributionChannel;
    	this.CustomerNumber=CustomerNumber;
    	this.CompanyCode=CompanyCode;
    	this.OrderCurrency=OrderCurrency;
    	this.OrderAmount=OrderAmount;
    	this.OrderCreationDate=OrderCreationDate;
    }

    // Getter methods
    public Long getSlNo() {
        return SlNo;
    }

    public int getCustomerOrderId() {
        return CustomerOrderId;
    }

    public int getSalesOrg() {
        return SalesOrg;
    }

    public String getDistributionChannel() {
        return DistributionChannel;
    }
    
    public int getCompanyCode() {
        return CompanyCode;
    }
    
    public String getOrderCreationDate() {
        return OrderCreationDate;
    }
    
    public String getOrderCurrency() {
        return OrderCurrency;
    }
    
    public String getCustomerNumber() {
        return CustomerNumber;
    }
    
    public String getAmountinUSD() {
        return AmountinUSD;
    }
    
    public String getOrderAmount() {
        return OrderAmount;
    }
    
    // Setter methods
    public void setSlNo(Long SlNo) {
    	this.SlNo = SlNo;
    }

    public void setCustomerOrderId(int CustomerOrderId) {
        this.CustomerOrderId = CustomerOrderId;
    }

    public void setSalesOrg(int SalesOrg) {
        this.SalesOrg = SalesOrg;
    }

    public void setDistributionChannel(String DistributionChannel) {
        this.DistributionChannel = DistributionChannel;
    }
    
    public void setCompanyCode(int CompanyCode) {
    	this.CompanyCode = CompanyCode;
    }
    
    public void setOrderCreationDate(String OrderCreationDate) {
        this.OrderCreationDate = OrderCreationDate;
    }
    
    public void setOrderCurrency(String OrderCurrency) {
        this.OrderCurrency = OrderCurrency;
    }
    
    public void setCustomerNumber(String CustomerNumber) {
        this.CustomerNumber = CustomerNumber;
    }
    
    public void setAmountinUSD(String AmountinUSD) {
        this.AmountinUSD = AmountinUSD;
    }
    
    public void setOrderAmount(String OrderAmount) {
        this.OrderAmount = OrderAmount;
    }
}
