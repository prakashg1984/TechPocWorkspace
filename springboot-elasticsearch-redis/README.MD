ORDER
	
	POST : 
		http://localhost:8086/msapi/orders
	GET :
		http://localhost:8086/msapi/orders/O2
		http://localhost:8086/msapi/orders/find/all
		http://localhost:8086/msapi/orders/search?searchFields={"Accounts.Account.AccountCategory":"NEW_ACCOUNT","Accounts.Account.BillingAccountNumber":"123456"}
		
CUSTOMER
	
	POST : 
		http://localhost:8086/msapi/customer
	GET :
		http://localhost:8086/msapi/customer/C2
		http://localhost:8086/msapi/customer/find/all
		http://localhost:8086/msapi/customer/search?searchFields={"Accounts.Account.AccountCategory":"NEW_ACCOUNT","Accounts.Account.BillingAccountNumber":"123456"} 