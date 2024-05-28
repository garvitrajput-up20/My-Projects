/*
	EXCERSIE 2: SQL ASSIGNMENT - GARVIT RAJPUT
*/


use adventureworks;
/*USING JOIN*/

Select C.CustomerID from Sales.SalesOrderHeader SOH
Right Join Sales.Customer C
on C.CustomerID=SOH.CustomerID
Where SalesOrderID is NULL;

/*USING SUB-QUERY*/
Select CustomerID from Sales.Customer C  
Where C.CustomerID  
Not in (Select CustomerID from Sales.SalesOrderHeader);

/*CTE*/
WITH CustomersWithoutOrder (CustmerID) 
AS 
(
	SELECT c.CustomerID
	FROM Sales.SalesOrderHeader soh
	RIGHT JOIN Sales.Customer c ON soh.CustomerID = c.CustomerID
	WHERE SalesOrderID IS NULL
)
SELECT * FROM CustomersWithoutOrder;

/*
	EXISTS
*/
SELECT c.CustomerID
FROM Sales.Customer c
WHERE NOT EXISTS (SELECT s.CustomerID 
	FROM Sales.SalesOrderHeader AS s
	WHERE s.CustomerID = c.CustomerID);