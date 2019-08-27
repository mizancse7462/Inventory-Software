/*
SQLyog Ultimate - MySQL GUI v8.21 
MySQL - 5.0.45-community-nt : Database - supershop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`supershop` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `supershop`;

/*Table structure for table `add_employee` */

CREATE TABLE `add_employee` (
  `Department` varchar(90) default NULL,
  `Employee_ID` varchar(90) default NULL,
  `Employee_Name` varchar(90) default NULL,
  `Reference_Name` varchar(60) default NULL,
  `Designation` varchar(70) default NULL,
  `Joining_Date` varchar(70) default NULL,
  `Employee_Permanant_Address` text,
  `Employee_Present_Address` text,
  `Mobile` varchar(50) default NULL,
  `Basic_Salary` varchar(40) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `add_employee` */

insert  into `add_employee`(`Department`,`Employee_ID`,`Employee_Name`,`Reference_Name`,`Designation`,`Joining_Date`,`Employee_Permanant_Address`,`Employee_Present_Address`,`Mobile`,`Basic_Salary`) values ('electronics','001','poli','MD hasan','ert','01.11.15','mirpur','feni','98760','10000'),('electronics','001','poli','MD hasan','ert','01.11.15','mirpur','feni','98760','10000'),('electronics','001','poli','MD hasan','ert','01.11.15','feni','feni','098700','10000'),('electronics','001','poli','MD hasan','ert','01.11.15','feni','feni','098700','10000'),('electronics','001','poli','MD hasan','ert','01.11.15','feni','feni','098700','10000'),('electronics','002','moli','MD hasan','ert','01.11.15','dhaka','dhaka','98760','10000');

/*Table structure for table `admin_user` */

CREATE TABLE `admin_user` (
  `id` int(10) NOT NULL auto_increment,
  `FullName` varchar(50) NOT NULL default '',
  `UserName` varchar(10) default '',
  `Admin_Type` varchar(50) default '',
  `Password` varchar(30) default NULL,
  `Concact_No` varchar(25) default NULL,
  `Email` varchar(30) default NULL,
  `Address` varchar(500) default NULL,
  `User_Status` varchar(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `User_Name` (`FullName`,`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `admin_user` */

insert  into `admin_user`(`id`,`FullName`,`UserName`,`Admin_Type`,`Password`,`Concact_No`,`Email`,`Address`,`User_Status`) values (14,'mizanur rahman','1','Main Admin','1','01735203790','mizancse7462@gmail.com','kkk','Active');

/*Table structure for table `bank_info` */

CREATE TABLE `bank_info` (
  `date` varchar(20) default NULL,
  `bank_Name` varchar(50) NOT NULL,
  `account_Type` varchar(30) default NULL,
  `account_No` varchar(50) default NULL,
  `branch_Name` varchar(50) default NULL,
  `address` varchar(100) default NULL,
  `mobile` varchar(30) default NULL,
  PRIMARY KEY  (`bank_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bank_info` */

insert  into `bank_info`(`date`,`bank_Name`,`account_Type`,`account_No`,`branch_Name`,`address`,`mobile`) values ('10-05-2017','AB Bank','Saving Account','12345678','2000000','Feni','018142034455'),('07-05-2017','Dutch Bangla Bank','Current Account','928737448585','Feni','Feni','0187384855'),('24-05-2017','Social Islami Bank','Current Account','7266667777','Dhaka','Dhaka','01838478758');

/*Table structure for table `bankmanagement` */

CREATE TABLE `bankmanagement` (
  `id` int(11) NOT NULL auto_increment,
  `date` varchar(20) default NULL,
  `bank_Name` varchar(50) default NULL,
  `Ac_No` varchar(30) default NULL,
  `transetion_Type` varchar(30) default NULL,
  `debit_Type` varchar(30) default NULL,
  `check_No` varchar(30) default NULL,
  `amount` double default NULL,
  `narration` varchar(500) default NULL,
  `transetion_Id` varchar(30) NOT NULL default '',
  `current_Balance` double default NULL,
  `DefultDate` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `bankmanagement` */

insert  into `bankmanagement`(`id`,`date`,`bank_Name`,`Ac_No`,`transetion_Type`,`debit_Type`,`check_No`,`amount`,`narration`,`transetion_Id`,`current_Balance`,`DefultDate`) values (1,'16-06-2017','Social Islami Bank','7266667777','Deposit','Voucher','45435',0,'54645','D-16-6-2017-01',0,'2017-03-16'),(2,'02-06-2017','AB Bank','12345678','Deposit','Select Transaction Type','4566546',565656,'','D-16-6-2017-02',565656,'2017-00-02'),(3,'09-06-2017','AB Bank','12345678','Withdrawal','By Check','445',5555,'','W-16-6-2017-01',560101,'2017-00-09'),(4,'09-06-2017','AB Bank','12345678','Deposit','Voucher','4535',444,'555','D-16-6-2017-03',560545,'2017-00-09'),(5,'08-06-2017','AB Bank','12345678','Deposit','Voucher','4423',34,'ewtret','D-17-6-2017-04',560579,'2017-00-08'),(6,'17-06-2017','AB Bank','12345678','Deposit','Voucher','5345345',345345,'rgdf','D-17-6-2017-05',905924,'2017-00-17'),(7,'09-06-2017','AB Bank','12345678','Deposit','Voucher','435435',4354,'34534','D-17-6-2017-06',910278,'2017-00-09'),(8,'15-06-2017','AB Bank','12345678','Deposit','Voucher','453454',45435,'4354','D-17-6-2017-07',955713,'2017-00-15'),(9,'03-06-2017','AB Bank','12345678','Deposit','Voucher','435435',345435,'435345','D-17-6-2017-08',1301148,'2017-00-03'),(10,'16-06-2017','AB Bank','12345678','Withdrawal','By Check','545435',44444,'gfgdfg','W-30-6-2017-02',1256704,'2017-00-16'),(11,'15-06-2017','AB Bank','12345678','Deposit','Voucher','454545',435345,'fgdh','D-30-6-2017-09',436602139,'2017-00-15'),(12,'16-06-2017','AB Bank','12345678','Deposit','Voucher','4444',445555,'54545','D-30-6-2017-10',481157694,'2017-00-16'),(13,'16-06-2017','AB Bank','12345678','Deposit','Voucher','45435',4354354,'4545','D-30-6-2017-11',485512048,'2017-00-16'),(14,'16-06-2017','AB Bank','12345678','Withdrawal','By Check','4543543',345435,'3454435','W-30-6-2017-03',450968514,'2017-00-16'),(15,'16-06-2017','AB Bank','12345678','Deposit','Voucher','5654654',999999999,'654645','D-30-6-2017-12',451033079,'2017-00-16'),(16,'16-06-2017','AB Bank','12345678','Withdrawal','By Check','5555',999999999,'ytryrtyrty','W-30-6-2017-04',445477524,'2017-00-16'),(17,'02-06-2017','AB Bank','12345678','Deposit','Voucher','543543',4545,'454','D-30-6-2017-13',445482069,'2017-00-02'),(18,'29-07-2017','AB Bank','12345678','Deposit','Voucher','34456',5555556,'fhfgjf','D-1-7-2017-14',451037625,'2017-00-29'),(19,'11-07-2017','AB Bank','12345678','Deposit','Voucher','324235',500000,'asdfsdfgsd','D-20-7-2017-15',451537625,'2017-00-11');

/*Table structure for table `current_ purchase` */

CREATE TABLE `current_ purchase` (
  `Product_ID` varchar(10) default NULL,
  `Product_Name` varchar(100) default NULL,
  `Product_Stock` varchar(10000) default NULL,
  `Purchase_Price` float default NULL,
  `Sales_Price` float default NULL,
  `Item_Name` varchar(100) default NULL,
  `Category_Name` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `current_ purchase` */

/*Table structure for table `customeandsupplyer` */

CREATE TABLE `customeandsupplyer` (
  `id` int(15) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `mobile` varchar(25) default NULL,
  `email` varchar(30) default NULL,
  `present_address` text,
  `permanent_address` text,
  `member_type` varchar(30) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`,`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `customeandsupplyer` */

insert  into `customeandsupplyer`(`id`,`name`,`mobile`,`email`,`present_address`,`permanent_address`,`member_type`) values (1,'fdsfdsf','423423','asfdsfds@dfds','ddsfdsfds','sasdfsdf','Customer'),(2,'fsdfsdf','3543543','dsfsd@sfd','sfsdfsdf','sdfsdf','Supplyer'),(3,'asfsdf','23543534','sfsdf@asdfsd','dfsdgsdg','sdfasdfrey','Customer');

/*Table structure for table `customer_report` */

CREATE TABLE `customer_report` (
  `Invoice_No` varchar(50) default NULL,
  `Invoice_Date` varchar(50) default NULL,
  `Customer_Name` varchar(50) default NULL,
  `Total_Amount` float default NULL,
  `Paid_Amount` float default NULL,
  `Due_Amount` float default NULL,
  `Balance` float default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customer_report` */

/*Table structure for table `employee_department` */

CREATE TABLE `employee_department` (
  `Department_ID` varchar(60) default NULL,
  `Department_Name` varchar(50) default NULL,
  `Description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `employee_department` */

insert  into `employee_department`(`Department_ID`,`Department_Name`,`Description`) values ('0001','electronics','nmhhfdfshgf');

/*Table structure for table `employeeinfo` */

CREATE TABLE `employeeinfo` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) default NULL,
  `designation` varchar(20) default NULL,
  `mobile` varchar(30) default NULL,
  `email` varchar(30) default NULL,
  `address` varchar(500) default NULL,
  `joining_Date` varchar(10) default NULL,
  `educational_Qualification` varchar(100) default NULL,
  `basic_Salary` varchar(500) default NULL,
  `employee_Status` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `employeeinfo` */

insert  into `employeeinfo`(`id`,`name`,`designation`,`mobile`,`email`,`address`,`joining_Date`,`educational_Qualification`,`basic_Salary`,`employee_Status`) values ('01','al-Amin','Accountant','018273848441','alamin@gmail.com','Dhaka','20-02-2017','HSC','10000','Active'),('02','Sumon','Sub Accountant','01829394442','sumon@gmail.com','Dhaka','28-03-2016','SSC','80000','Active'),('03','kalam','Sweeper','018159127895','kalam@gmail.com','Dhaka','01-02-2017','Dhaka','200000','Inactive');

/*Table structure for table `employeesalary` */

CREATE TABLE `employeesalary` (
  `id` int(10) NOT NULL,
  `InvoiceNo` varchar(15) NOT NULL,
  `Date` varchar(20) default NULL,
  `Month` varchar(30) default NULL,
  `EmployeeName` varchar(59) default NULL,
  `Phone` varchar(30) default NULL,
  `PaymentType` varchar(30) default NULL,
  `Amount` double default NULL,
  `Comment` varchar(200) default NULL,
  `Admin` varchar(20) default NULL,
  UNIQUE KEY `id` (`id`,`InvoiceNo`),
  UNIQUE KEY `Date` (`Date`,`Month`,`EmployeeName`,`Phone`,`PaymentType`,`Amount`,`Admin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `employeesalary` */

insert  into `employeesalary`(`id`,`InvoiceNo`,`Date`,`Month`,`EmployeeName`,`Phone`,`PaymentType`,`Amount`,`Comment`,`Admin`) values (1,'PINV-01','May 11, 2017','January','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-02','May 26, 2017','January','Sumon','01829394442','Salary',80000,'','Admin'),(1,'PINV-03','May 18, 2017','May','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-04','May 4, 2017','May','Sumon','01829394442','Salary',80000,'','Admin'),(1,'PINV-05','May 12, 2017','May','Sumon','01829394442','Salary',80000,'','Admin'),(1,'PINV-06','May 10, 2017','April','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-07','May 12, 2017','March','al-Amin','018273848441','Salary',10000,'fgfdg','Admin'),(1,'PINV-08','May 10, 2017','February','Sumon','01829394442','Salary',80000,'','Admin'),(1,'PINV-10','May 4, 2017','January','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-11','May 5, 2017','January','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-12','May 12, 2017','February','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-14','11-05-2017','April','Sumon','01829394442','Salary',80000,'','Admin'),(1,'PINV-15','12-05-2017','May','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-16','09-06-2017','January','al-Amin','018273848441','Salary',10000,'55','Admin'),(1,'PINV-17','20-06-2017','January','al-Amin','018273848441','Salary',10000,'dsfgsfdgf','Admin'),(1,'PINV-18','01-07-2017','January','Sumon','01829394442','Salary',80000,'','Admin'),(1,'PINV-19','01-07-2017','February','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-20','07-07-2017','June','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-21','07-07-2017','May','al-Amin','018273848441','Salary',10000,'','Admin'),(1,'PINV-22','07-07-2017','June','Sumon','01829394442','Salary',80000,'','Admin'),(1,'PINV-23','06-07-2017','February','Sumon','01829394442','Salary',80000,'','Admin'),(2,'PINV-01','May 11, 2017','January','al-Amin','018273848441','Bonus',600,'','Admin'),(2,'PINV-04','May 4, 2017','May','Sumon','01829394442','Bonus',100,'','Admin'),(2,'PINV-06','May 10, 2017','April','al-Amin','018273848441','Bonus',1000,'','Admin'),(2,'PINV-07','May 12, 2017','March','al-Amin','018273848441','Bonus',1000,'','Admin'),(2,'PINV-08','May 10, 2017','February','Sumon','01829394442','Bonus',50000,'','Admin'),(2,'PINV-13','May 11, 2017','February','al-Amin','018273848441','Salary',10000,'','Admin'),(2,'PINV-17','09-06-2017','February','al-Amin','018273848441','Bonus',5435,'','Admin'),(2,'PINV-18','01-07-2017','January','Sumon','01829394442','Bonus',6677,'yfgk','Admin'),(2,'PINV-19','01-07-2017','March','al-Amin','018273848441','Bonus',89090,'','Admin'),(2,'PINV-20','07-07-2017','June','al-Amin','018273848441','Bonus',777,'','Admin'),(2,'PINV-22','07-07-2017','June','Sumon','01829394442','Bonus',44545,'','Admin'),(2,'PINV-23','06-07-2017','February','Sumon','01829394442','Bonus',555,'','Admin'),(3,'PINV-09','May 11, 2017','February','Sumon','01829394442','Salary',80000,'','Admin'),(3,'PINV-11','May 5, 2017','January','al-Amin','018273848441','Bonus',0,'','Admin'),(3,'PINV-21','07-07-2017','June','al-Amin','018273848441','Bonus',3333,'','Admin');

/*Table structure for table `employeesalaryleadger` */

CREATE TABLE `employeesalaryleadger` (
  `id` int(10) NOT NULL auto_increment,
  `EntryDate` varchar(30) default NULL,
  `InvoiceNo` varchar(15) NOT NULL default '',
  `EmployeeName` varchar(50) default NULL,
  `Phone` varchar(30) default NULL,
  `Month` varchar(30) default NULL,
  `Salary` double default NULL,
  `Paid` double default NULL,
  `DueAmount` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Data for the table `employeesalaryleadger` */

insert  into `employeesalaryleadger`(`id`,`EntryDate`,`InvoiceNo`,`EmployeeName`,`Phone`,`Month`,`Salary`,`Paid`,`DueAmount`) values (1,'May 11, 2017','PINV-01','al-Amin','018273848441','January',10600,10600,0),(2,'May 26, 2017','PINV-02','Sumon','01829394442','January',80000,80000,0),(3,'May 18, 2017','PINV-03','al-Amin','018273848441','May',10000,10000,0),(4,'May 4, 2017','PINV-04','Sumon','01829394442','May',80100,80100,0),(5,'May 12, 2017','PINV-05','Sumon','01829394442','May',80000,80000,0),(6,'May 10, 2017','PINV-06','al-Amin','018273848441','April',11000,11000,0),(7,'May 12, 2017','PINV-07','al-Amin','018273848441','March',11000,10000,1000),(8,'May 10, 2017','PINV-08','Sumon','01829394442','February',130000,130000,0),(9,'May 11, 2017','PINV-09','Sumon','01829394442','February',80000,8000,72000),(10,'May 4, 2017','PINV-10','al-Amin','018273848441','January',10000,10000,0),(11,'May 5, 2017','PINV-11','al-Amin','018273848441','January',10000,10000,0),(12,'May 12, 2017','PINV-12','al-Amin','018273848441','February',10000,10000,0),(13,'May 11, 2017','PINV-13','al-Amin','018273848441','February',10000,10000,0),(14,'May 11, 2017','PINV-14','Sumon','01829394442','April',80000,80000,0),(15,'May 12, 2017','PINV-15','al-Amin','018273848441','May',10000,10000,0),(16,'Jun 9, 2017','PINV-16','al-Amin','018273848441','January',10000,10000,0),(17,'Jun 9, 2017','PINV-17','al-Amin','018273848441','February',15435,15435,0),(18,'Jul 1, 2017','PINV-18','Sumon','01829394442','January',86677,86677,0),(19,'Jul 1, 2017','PINV-19','al-Amin','018273848441','March',99090,99090,0),(20,'Jul 7, 2017','PINV-20','al-Amin','018273848441','June',10777,10777,0),(21,'Jul 7, 2017','PINV-21','al-Amin','018273848441','June',13333,13333,0),(22,'Jul 7, 2017','PINV-22','Sumon','01829394442','June',124545,124545,0),(23,'Jul 6, 2017','PINV-23','Sumon','01829394442','February',80555,80555,0);

/*Table structure for table `expensesource` */

CREATE TABLE `expensesource` (
  `ID` int(10) NOT NULL auto_increment,
  `VoucherNo` varchar(20) default NULL,
  `BillNo` varchar(20) default NULL,
  `EntryDate` varchar(30) default NULL,
  `ExpenseSource` varchar(200) default NULL,
  `Details` text,
  `Amount` double default NULL,
  `EntryInfo` varchar(30) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `expensesource` */

insert  into `expensesource`(`ID`,`VoucherNo`,`BillNo`,`EntryDate`,`ExpenseSource`,`Details`,`Amount`,`EntryInfo`) values (1,'EVNO-01','56456','04-05-2017','Office Rent','gfhgfhgfh',6575,'Admin'),(2,'EVNO-02','657657','01-05-2017','Office Rent','6867865',5676,'Admin'),(3,'EVNO-03','4554','10-05-2017','Office Rent','gfgfgf',5345,'Admin'),(4,'EVNO-04','2457645','01-07-2017','Office Rent','jhfhjf',67467,'Admin'),(5,'EVNO-05','3343434','07-07-2017','Office Rent','sdfdg',34234,'Admin'),(6,'EVNO-06','534536','07-07-2017','Office Rent','rety5rtytrgyuh',456456,'Admin');

/*Table structure for table `incomesource` */

CREATE TABLE `incomesource` (
  `select_Type` varchar(30) default '',
  `source_Id` int(1) NOT NULL auto_increment,
  `source_Title` varchar(50) default NULL,
  PRIMARY KEY  (`source_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `incomesource` */

insert  into `incomesource`(`select_Type`,`source_Id`,`source_Title`) values ('Expense',6,'Office Rent'),('Expense',7,'Employee Salary'),('Expense',8,'Entertainment costs'),('Income Source',9,'House Rent'),('Income Source',10,'Sale');

/*Table structure for table `iteminfo` */

CREATE TABLE `iteminfo` (
  `UnitName` varchar(100) default NULL,
  `ItemID` varchar(10) NOT NULL,
  `ItemName` varchar(200) default NULL,
  PRIMARY KEY  (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `iteminfo` */

insert  into `iteminfo`(`UnitName`,`ItemID`,`ItemName`) values ('Electronic Item','ITEM-001','Laptop'),('Electronic Item','ITEM-002','Mouse'),('Food Item','ITEM-003','Rice'),('Office Stationary','ITEM-004','Pen'),('Office Stationary','ITEM-005','Paper'),('Chair','ITEM-006','Relux Chair');

/*Table structure for table `product_category` */

CREATE TABLE `product_category` (
  `UnitName` varchar(100) default NULL,
  `ItemName` varchar(100) default NULL,
  `Category_id` varchar(10) NOT NULL,
  `Category_name` varchar(100) default NULL,
  PRIMARY KEY  (`Category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product_category` */

insert  into `product_category`(`UnitName`,`ItemName`,`Category_id`,`Category_name`) values ('Electronic Item','Laptop','CATEID-000','Toshiba'),('Electronic Item','Laptop','CATEID-001','LG'),('Electronic Item','Mouse','CATEID-002','Delux'),('Office Stationary','Pen','CATEID-003','Link3'),('Office Stationary','Paper','CATEID-004','A4'),('Chair','Relux Chair','CATEID-005','Otobi');

/*Table structure for table `product_unit` */

CREATE TABLE `product_unit` (
  `Unit_ID` varchar(10) NOT NULL,
  `Unit_name` varchar(50) default NULL,
  PRIMARY KEY  (`Unit_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product_unit` */

insert  into `product_unit`(`Unit_ID`,`Unit_name`) values ('UID-0001','Electronic Item'),('UID-0002','Food Item'),('UID-0003','Gift Item'),('UID-0004','Office Stationary'),('UID-0005','Bed'),('UID-0006','Chair');

/*Table structure for table `productinfo` */

CREATE TABLE `productinfo` (
  `product_Type` varchar(100) NOT NULL default '',
  `product_Item` varchar(200) NOT NULL default '',
  `product_Brand` varchar(200) NOT NULL default '',
  `product_name` varchar(200) default NULL,
  `product_ID` varchar(10) NOT NULL default '',
  `purchase_price` float NOT NULL,
  `sales_price` float NOT NULL,
  `Measurment_Unit` varchar(50) default NULL,
  `Color` varchar(50) default NULL,
  `product_details` text,
  `Barcode` text,
  PRIMARY KEY  (`product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `productinfo` */

insert  into `productinfo`(`product_Type`,`product_Item`,`product_Brand`,`product_name`,`product_ID`,`purchase_price`,`sales_price`,`Measurment_Unit`,`Color`,`product_details`,`Barcode`) values ('safsd','fsdf','sdfsdsdf','sdfsd','4234',223,343,'234','234','234','234'),('asdfsdf','sdfs','dfsdf','sdfsdf','dfsd',3423,234,'sdf','sdf','sdfs','sdfsd'),('Electronic Item','Laptop','LG','LG-3489','PDT-000003',40000,45000,NULL,NULL,NULL,NULL),('Electronic Item','Mouse','LG','A4tech','PDT-000004',400,450,NULL,NULL,NULL,NULL),('Electronic Item','Mouse','Delux','Delux','PDT-000005',12,15,NULL,NULL,NULL,NULL),('Electronic Item','Laptop','Toshiba','akhi','PDT-000010',500000,600000,NULL,NULL,NULL,NULL);

/*Table structure for table `purchase_form` */

CREATE TABLE `purchase_form` (
  `voucherNo` varchar(50) NOT NULL default '',
  `VoucherDate` varchar(50) NOT NULL default '',
  `InvoiceNo` varchar(50) NOT NULL default '',
  `InvoiceDate` varchar(50) NOT NULL default '',
  `SupplyerName` varchar(50) NOT NULL default '',
  `SupplyerPhone` varchar(50) NOT NULL default '',
  `ProductName` varchar(10) default NULL,
  `ProductQun` double NOT NULL,
  `ProductPrice` double NOT NULL,
  `Total_Purchase_Price` double NOT NULL,
  `Total_Sale_Price` double NOT NULL,
  UNIQUE KEY `NewIndex1` (`voucherNo`,`ProductName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase_form` */

insert  into `purchase_form`(`voucherNo`,`VoucherDate`,`InvoiceNo`,`InvoiceDate`,`SupplyerName`,`SupplyerPhone`,`ProductName`,`ProductQun`,`ProductPrice`,`Total_Purchase_Price`,`Total_Sale_Price`) values ('324324','15-08-2017','23423','10-08-2017','4234234','345345','LG-3489',44534,345,345345,345),('53425','04-08-2017','2352345','02-08-2017','2352345','2345235','akhi',3333,500000,1666500000,600000),('342354','02-08-2017','2323','17-08-2017','23423','4554','LG-3489',1,40000,40000,45000),('342354','02-08-2017','2323','17-08-2017','23423','4554','akhi',2,500000,1000000,600000),('342354','02-08-2017','2323','17-08-2017','23423','4554','A4tech',1,400,400,450),('345345','09-08-2017','234534','10-08-2017','345345','45454','A4tech',11,400,4400,450),('345345','09-08-2017','234534','10-08-2017','345345','45454','akhi',4,500000,2000000,600000),('2323','02-08-2017','1232','09-08-2017','123','123123','LG-3489',11,40000,440000,45000),('34234','09-08-2017','234234','08-08-2017','324234','34234','Delux',33,12,396,15),('35345','02-08-2017','23545','10-08-2017','2345345','23545','A4tech',333,400,133200,450),('35345','02-08-2017','23545','10-08-2017','2345345','23545','akhi',333,500000,166500000,600000),('2131231232','03-08-2017','343','03-08-2017','343','345','LG-3489',3,40000,120000,45000),('2131231232','03-08-2017','343','03-08-2017','343','345','Delux',4,12,48,15),('2343255','02-08-2017','2343534','10-08-2017','335','345345','LG-3489',33,40000,1320000,45000),('2343255','02-08-2017','2343534','10-08-2017','335','345345','Delux',444,12,5328,15),('2343255','02-08-2017','2343534','10-08-2017','335','345345','akhi',555,500000,277500000,600000),('235345','10-08-2017','345454455','03-08-2017','sdfsdf','34456','LG-3489',555,40000,22200000,45000),('235345','11-08-2017','34324','04-08-2017','34534534','23545','Delux',33,12,396,15),('235345','11-08-2017','435435','04-08-2017','345345','asdfsdf','sdfsdf',5,3423,17115,234),('325345','17-08-2017','4354','11-08-2017','345345','345345','A4tech',4,400,1600,450),('325345','17-08-2017','4354','11-08-2017','345345','345345','sdfsd',6,223,1338,343),('325345','17-08-2017','4354','11-08-2017','345345','345345','sdfsdf',4,3423,13692,234),('325345','17-08-2017','4354','11-08-2017','345345','345345','Delux',4,12,48,15),('325345','17-08-2017','4354','11-08-2017','345345','345345','akhi',5,500000,2500000,600000);

/*Table structure for table `purchase_ledger` */

CREATE TABLE `purchase_ledger` (
  `purchaseInvoiceNo` varchar(50) NOT NULL default '',
  `TotalPrice` double NOT NULL,
  `Paid` double NOT NULL,
  `Due` double NOT NULL,
  PRIMARY KEY  (`purchaseInvoiceNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase_ledger` */

insert  into `purchase_ledger`(`purchaseInvoiceNo`,`TotalPrice`,`Paid`,`Due`) values ('2343534',278825328,44345656,234479672),('343',120048,120008,40),('34324',396,396,0),('4354',2538331,44444,2493887),('435435',17223,17223,0);

/*Table structure for table `sales_from` */

CREATE TABLE `sales_from` (
  `Invoice_No` varchar(50) NOT NULL default '',
  `Date` varchar(50) NOT NULL,
  `Customer_Name` varchar(50) NOT NULL default '',
  `Phone_Number` varchar(50) default NULL,
  `Product_Name` varchar(100) default NULL,
  `PDTQuentity` double default NULL,
  `UnitPurchasePrice` double default NULL,
  `UnitSalePrice` double default NULL,
  `TotalPurchase_Price` double NOT NULL,
  `TotalSale_Price` double NOT NULL,
  PRIMARY KEY  (`Invoice_No`),
  UNIQUE KEY `NewIndex1` (`Invoice_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sales_from` */

insert  into `sales_from`(`Invoice_No`,`Date`,`Customer_Name`,`Phone_Number`,`Product_Name`,`PDTQuentity`,`UnitPurchasePrice`,`UnitSalePrice`,`TotalPurchase_Price`,`TotalSale_Price`) values ('2345436','10-08-2017','sdasfasdf','234543634','Delux',3344,0,15,0,50160),('55555','11-08-2017','monir','123456789','akhi',2,44444,600000,445555,1200000);

/*Table structure for table `sales_ledger` */

CREATE TABLE `sales_ledger` (
  `Product_Invoice_No` varchar(50) NOT NULL default '',
  `Date` varchar(50) NOT NULL,
  `Customer_Name` varchar(50) NOT NULL default '',
  `Phone_Number` varchar(50) default NULL,
  `Address` text,
  `Product_Name` varchar(50) NOT NULL,
  `Product_ID` varchar(10) default NULL,
  `Purchase_Price` double NOT NULL,
  `Sale_Price` double NOT NULL,
  `Quentity` double NOT NULL,
  `Total_Purchase_Price` double NOT NULL,
  `Total_Sale_Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sales_ledger` */

insert  into `sales_ledger`(`Product_Invoice_No`,`Date`,`Customer_Name`,`Phone_Number`,`Address`,`Product_Name`,`Product_ID`,`Purchase_Price`,`Sale_Price`,`Quentity`,`Total_Purchase_Price`,`Total_Sale_Price`) values ('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',400,450,1,400,450),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',400,450,1,400,450),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',400,450,1,400,450),('','','Select Customer...........',NULL,NULL,'A4tech','00001',0,450,33,0,44),('','','Select Customer...........',NULL,NULL,'A4tech','00001',0,450,33,0,44),('00001','01.11.15','Select Customer...........',NULL,NULL,'A4tech','00001',0,450,23423,0,34),('124234','02.11.15','amzad',NULL,NULL,'A4tech','00001',0,450,222323,0,33223),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',0,450,234,0,23432),('00001','01.11.15','Select Customer...........',NULL,NULL,'A4tech','00001',0,450,1323,0,23432),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',0,450,34,0,34),('00001','01.11.15','Select Customer...........',NULL,NULL,'Mobile','PDT-000001',0,44.55,23423,0,23234),('00001','01.11.15','Select Customer...........',NULL,NULL,'','',0,0,0,0,0),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',0,450,332,0,32423),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',0,450,332,0,32423),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',0,450,332,0,32423),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',0,450,332,0,32423),('00001','01.11.15','amzad',NULL,NULL,'A4tech','00001',0,450,332,0,32423),('00001','01.11.15','Select Customer...........',NULL,NULL,'234','PDT-000002',0,4,23423,0,23423),('00001','01.11.15','amzad',NULL,NULL,'dfdsfg','PDT-000001',0,2432,232,0,32423),('00001','01.11.15','amzad',NULL,NULL,'dfdsfg','PDT-000001',0,23,234,0,23432),('234234','','amzad',NULL,NULL,'A4tech','00001',0,4503,2343,0,23432),('pdt01','345','amzad',NULL,NULL,'Laptop','PDT-000001',0,44.55,345,0,34543),('432','03.11.15','dsffff','','','Laptop','PDT-000001',0,44.55,324,0,2342),('432','03.11.15','dsffff','','','Laptop','PDT-000001',0,44.55,324,0,2342),('00001','01.11.15','','','','Laptop','PDT-000001',0,44.55,1,0,44.55),('00001','01.11.15','','','','Laptop','PDT-000001',0,44.55,1,0,44.55),('1234','03.11.15','','','','23423','4324',0,44.55345,23423,0,50000),('23123','12312','','','','Laptop','PDT-000001',0,44.55,22,0,33),('23123','12312','','','','Laptop','PDT-000001',0,44.55,344,0,4445),('23123','12312','','','','Laptop','PDT-000001',0,44.55,45,0,44),('00001','03.11.15','','','','Laptop','PDT-000001',0,55,1,0,55),('00001','01.11.15','','','','Laptop','PDT-000001',0,45,1,0,45),('00002','23-11-2015','','','','Laptop','PDT-000001',0,50,1,0,50);

/*Table structure for table `stock` */

CREATE TABLE `stock` (
  `Product_Name` varchar(100) NOT NULL,
  `ProductStock` mediumtext,
  `Purchase_Price` double default NULL,
  `Sales_Price` double default NULL,
  PRIMARY KEY  (`Product_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`Product_Name`,`ProductStock`,`Purchase_Price`,`Sales_Price`) values ('A4tech','4',400,450),('akhi','5',500000,600000),('Delux','13.0',12,15),('sdfsd','11.0',223,343),('sdfsdf','15.0',3423,234);

/*Table structure for table `supliar` */

CREATE TABLE `supliar` (
  `Supplier_ID` varchar(20) NOT NULL,
  `Supplier_Name` varchar(50) NOT NULL default '',
  `Comapny_Name` varchar(50) default NULL,
  `Email` varchar(50) default NULL,
  `Mobile_No` varchar(50) default NULL,
  `Address` text,
  PRIMARY KEY  (`Supplier_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `supliar` */

insert  into `supliar`(`Supplier_ID`,`Supplier_Name`,`Comapny_Name`,`Email`,`Mobile_No`,`Address`) values ('grg','RGag','CGMHCM','bdzf','00909',' BCHHHHHHHHHHHHHHG,'),('S001','karim','mknjk','karim@yahoo.com','00909','feni');

/*Table structure for table `supplier_report` */

CREATE TABLE `supplier_report` (
  `Invoice_No` varchar(50) default NULL,
  `Suplier_Invoice_No` varchar(50) default NULL,
  `Invoice_Date` varchar(50) default NULL,
  `Suplier_Invoice_Date` varchar(50) default NULL,
  `Supplie_Name` varchar(50) default NULL,
  `Total_Amount` float default NULL,
  `Paid_Amount` float default NULL,
  `Due_Amount` float default NULL,
  `Balance` float default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `supplier_report` */

insert  into `supplier_report`(`Invoice_No`,`Suplier_Invoice_No`,`Invoice_Date`,`Suplier_Invoice_Date`,`Supplie_Name`,`Total_Amount`,`Paid_Amount`,`Due_Amount`,`Balance`) values ('32432523','123','23-12','21321','Select Suplier...........',0,0,0,0),('32432523','123','23-12','21321','karim',0,0,0,0),('11124','234324','22-11-15','22-12-15','karim',23432,3434,23423,0),('11124','234324','22-11-15','22-12-15','karim',23432,3434,23423,0),('11124','234324','22-11-15','22-12-15','karim',23432,3434,23423,0),('11124','234324','22-11-15','22-12-15','karim',23432,3434,23423,0),('','','','','Select Suplier...........',0,0,0,0),('123','34','12-12-12','22-12-15','karim',0,0,0,0),('','','','','Select Suplier...........',0,0,0,0),('','','','','Select Suplier...........',0,0,0,0),('','','','','Select Suplier...........',0,0,0,0),('','','','','Select Suplier...........',0,0,0,0),('3434534','34534','01.11.15','22-11-2015','karim',234324,234234,24234,0),('000013','234234','','22-11-2015','karim',324,324,234,0),('234234','S013','02.11.15','324333','karim',2345,234543,234324,0),('','','','','Select Suplier...........',0,0,0,0),('543','654','03.11.15','22-11-2015','RGag',432,54,54,0),('','','','','Select Suplier...........',0,0,0,0),('INV-0001','3455','01.11.15','22-11-2015','karim',2345,324,24234,0),('INV-0002','12345','02.11.15','22-11-2015','RGag',4545,4545,0,0),('INV-0003','2341','01.11.15','01.11.15','karim',45,45,0,0),('INV-0004','','','','Select Suplier...........',0,0,0,0),('INV-0004','1687','01/23/2016','01/23/2016','karim',100,100,0,0),('INV-0004','1687','01/23/2016','01/23/2016','karim',0,800,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
