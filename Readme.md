Run the spring boot application and application gets started in tomcat server 8080
Login to the H2 database and create dummy data using queries
    Insert into account values ('account1', 250.0)
    Insert into account values ('account2', 500.0)
Hit a post request in post man localhost:8080/transfer
with json data 

{
	"sourceAccountNumber" : "account1",
	"destinationAccountNumber": "account2",
	"amount": 50.0
}

If successful, output should be 
    Success
    
If account number not exist, should throw error,
 {
     "timestamp": "2020-02-11T03:54:38.398+0000",
     "status": 500,
     "error": "Internal Server Error",
     "message": "Account account1 does not exists yet",
     "path": "/transfer"
 }
 
 If balance is low, should throw error,
 
 {
     "timestamp": "2020-02-11T03:54:38.398+0000",
     "status": 500,
     "error": "Internal Server Error",
     "message": "There is insufficient balance to do the transaction",
     "path": "/transfer"
 }
