## Hotel Reservation System using JDBC

<br/>
A simple java console based application using JDBC(Java Database Connectivity) & MySQL <br/> With The features

*  1.Reserve a room
* 2.View reservation
* 3.Get room number
* 4.Update reservation
*  5.Delete reservation
*  0.Exit



### Cretate Table using this query (mySQL):

```sql
CREATE TABLE reservations (
  reservation_id INT AUTO_INCREMENT,
  guest_name VARCHAR(255) NOT NULL,
  room_number INT NOT NULL,
  contact_number VARCHAR(10) NOT NULL,
  reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (reservation_id)
);
```
Configure the database settings in the `in.manage` package using your specific database credentials.

```java
private static final String url = "jdbc:mysql://localhost:3306/DB_Name";
private static final String user = "SQL_userName";
private static final String password = "SQL_Password";
```









