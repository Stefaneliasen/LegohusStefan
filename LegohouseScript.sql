CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(90) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE orders (
  OrderId INT(11) primary key auto_increment,
  User_userId INT(11) NOT NULL,
    FOREIGN KEY `user`(User_userId)
    REFERENCES `user`(id));

CREATE TABLE legoblock(
legoblockName varchar(45) primary key,
length int(11)
);

CREATE TABLE odetails(
  Order_orderId INT(11),
  quantity INT UNSIGNED NULL,
  legoblockName varchar(45),
  primary key (Order_orderId, legoblockName),
    FOREIGN KEY (Order_orderId)
    REFERENCES orders(orderId),
    FOREIGN KEY (legoblockName)
    REFERENCES legoblock(legoblockName));

Insert into `legoblock` values('2x4', 4);
Insert into `legoblock` values('2x2', 2);
Insert into `legoblock` values('2x1', 1);
    
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES 
(1,'jens@somewhere.com','jensen','customer'),
(2,'ken@somewhere.com','kensen','customer'),
(3,'robin@somewhere.com','batman','employee');
UNLOCK TABLES;
