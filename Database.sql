-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2023 at 05:22 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dodo`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CategoryID` int(11) NOT NULL,
  `CategoryName` varchar(255) DEFAULT NULL,
  `Description` text DEFAULT NULL,
  `ParentCategoryID` int(11) DEFAULT NULL,
  `UserId` int(11) DEFAULT NULL,
  `ImagePath` varchar(255) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `Status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryID`, `CategoryName`, `Description`, `ParentCategoryID`, `UserId`, `ImagePath`, `CreatedAt`, `UpdatedAt`, `Status`) VALUES
(1, 'Uncategorize', 'Uncategorize Description', 4, 1, NULL, '2023-12-02 11:15:57', '2023-12-02 13:02:04', 1),
(2, 'Clothing', 'Clothing and fashion', 2, 3, NULL, '2023-10-15 15:06:59', '2023-11-27 16:55:03', 1),
(3, 'Furniture', 'Furniture and home decor', 3, 3, NULL, '2023-10-15 15:06:59', '2023-11-27 16:55:06', 1),
(4, 'Book', 'Halloween Books', 4, 3, NULL, '2023-10-14 08:06:59', '2023-11-27 16:55:08', 1),
(5, 'Arts & Crafts', 'Beading & Jewelry Making', 4, 3, NULL, '2023-10-17 08:06:59', '2023-11-27 16:55:10', 1),
(6, 'Computers', 'Computer Accessories & Peripherals', 3, 3, NULL, '2023-10-14 08:06:59', '2023-11-30 05:12:52', 0),
(7, 'Home and Kitchen', 'Shop kitchen and dinning, bedding, bath, furniture, home decor and more', 3, 3, NULL, '2023-10-15 08:06:59', '2023-11-27 16:55:15', 0),
(8, 'Video Games', 'Shop video games for Nintendo, PlayStation, Xbox and more', 3, 3, NULL, '2023-10-17 08:06:59', '2023-11-27 16:55:17', 1),
(9, 'Tools and Home Improvement', 'Shop power and handtools, woodworking, lighting, hardware and more', 4, 3, NULL, '2023-10-15 08:06:59', '2023-11-27 16:55:19', 1),
(10, 'Digital Music', 'Stream Music', 4, 3, NULL, '2023-08-15 08:06:59', '2023-11-27 16:55:21', 1),
(12, 'Digital Music 1', 'Stream Music', 1, 4, NULL, '2023-08-15 08:06:59', '2023-11-05 09:20:14', NULL),
(13, 'Digital Music 2', 'Stream Music', 1, 4, NULL, '2023-08-15 08:06:59', '2023-11-27 16:55:30', NULL),
(14, 'Video Games 1', 'Shop video games for Nintendo, PlayStation, Xbox and more', 3, 4, NULL, '2023-10-17 08:06:59', '2023-11-27 16:55:33', 0),
(15, 'Computers 1', 'Computer Accessories & Peripherals', 3, 4, NULL, '2023-10-14 08:06:59', '2023-11-05 09:18:17', 0),
(17, 'Electronics', 'Electronics and gadgets', 4, 3, NULL, '2023-10-15 15:06:59', '2023-12-02 13:01:57', 1),
(19, 'test category', 'test category', 1, 9, NULL, '2023-12-04 12:35:20', '2023-12-04 12:35:42', 1);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `InvoiceID` int(11) NOT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `ShopOwnerID` int(11) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `InvoiceDate` date DEFAULT NULL,
  `TotalAmount` double(10,2) DEFAULT NULL,
  `PaymentMethod` varchar(255) DEFAULT NULL,
  `IsPaid` tinyint(1) DEFAULT 0,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`InvoiceID`, `OrderID`, `ShopOwnerID`, `CustomerID`, `ProductID`, `InvoiceDate`, `TotalAmount`, `PaymentMethod`, `IsPaid`, `CreatedAt`) VALUES
(2, 1, 3, 1, 1, '2023-10-15', 50.00, 'Credit Card', 1, '2023-10-15 15:41:37'),
(3, 2, 3, 2, 2, '2023-10-16', 60.00, 'PayPal', 1, '2023-10-15 15:41:37'),
(4, 3, 3, 4, 3, '2023-10-17', 70.00, 'Cash on Delivery', 0, '2023-10-15 15:41:37');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `ItemID` int(11) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `Quantity` int(10) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`ItemID`, `UserId`, `ProductID`, `Quantity`, `CreatedAt`) VALUES
(4, 7, 3, 1, '2023-10-15 15:22:33'),
(59, 4, 13, 1, '2023-12-02 12:45:30'),
(60, 4, 11, 7, '2023-12-02 12:45:40'),
(61, 4, 14, 5, '2023-12-02 12:46:03'),
(69, 6, 13, 1, '2023-12-04 13:37:38');

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `NotificationID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `NotificationType` varchar(50) DEFAULT NULL,
  `Message` text DEFAULT NULL,
  `IsRead` tinyint(1) DEFAULT 0,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`NotificationID`, `UserID`, `NotificationType`, `Message`, `IsRead`, `CreatedAt`) VALUES
(2, 3, 'New Message', 'You have a new message', 0, '2023-10-15 15:23:50'),
(3, 3, 'New Friend Request', 'You have a new friend request', 1, '2023-10-15 15:23:50'),
(4, 3, 'New Message', 'You have a new message', 0, '2023-10-15 15:25:12'),
(5, 3, 'New Friend Request', 'You have a new friend request', 1, '2023-10-15 15:25:13'),
(6, 3, 'Reminder', 'Dont forget your appointment tomorrow', 0, '2023-10-15 15:25:13');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `OrderID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ShopOwnerID` int(11) DEFAULT NULL,
  `OrderDate` datetime DEFAULT NULL,
  `TotalAmount` double(10,2) DEFAULT NULL,
  `ShippingAddress` varchar(255) DEFAULT NULL,
  `PaymentMethod` varchar(50) DEFAULT NULL,
  `OrderStatus` varchar(50) DEFAULT NULL,
  `UpdatedAt` timestamp NULL DEFAULT NULL,
  `PaymentStatus` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`OrderID`, `UserID`, `ShopOwnerID`, `OrderDate`, `TotalAmount`, `ShippingAddress`, `PaymentMethod`, `OrderStatus`, `UpdatedAt`, `PaymentStatus`) VALUES
(1, 6, 3, '2023-10-15 14:30:00', 100.00, '123 Main St, City, Country', 'Cash on Delivery', 'Waiting For Approval', NULL, NULL),
(2, 6, 3, '2023-10-16 15:45:00', 75.50, '456 Elm St, City, Country', 'Cash on Delivery', 'Waiting For Approval', '2023-10-02 08:50:19', 'Payed'),
(3, 6, 3, '2023-10-17 11:15:00', 120.25, '789 Oak St, City, Country', 'Cash on Delivery', 'Ready For Transport', NULL, NULL),
(4, 6, 3, '2023-10-17 11:15:00', 130.25, '789 Oak St, City, Country', 'Cash on Delivery', 'Packaging', NULL, NULL),
(5, 6, 3, '2023-10-17 11:15:00', 130.25, '789 Oak St, City, Country', 'Cash on Delivery', 'Delivered', '2023-11-30 06:57:44', 'Payed'),
(6, 6, 3, '2023-10-17 11:15:00', 130.25, '789 Oak St, City, Country', 'Cash on Delivery', 'Ready For Transport', NULL, NULL),
(7, 6, 3, '2023-09-05 15:45:00', 175.50, '456 Elm St, City, Country', 'Cash on Delivery', 'Delivered', '2023-08-13 08:50:19', 'Await Payment'),
(8, 6, 3, '2023-07-10 11:15:00', 225.25, '789 Oak St, City, Country', 'Cash on Delivery', 'Delivered', '2023-09-03 09:13:47', 'Await Payment'),
(9, 6, 3, '2023-07-10 11:15:00', 100.25, '789 Oak St, City, Country', 'Cash on Delivery', 'In Process', '2023-09-03 09:13:47', NULL),
(10, 6, 3, '2023-11-08 00:23:34', 287.18, '789 Oak St, City, Country', 'Cash on Delivery', 'In Process', NULL, NULL),
(11, 6, 3, '2023-11-08 00:23:34', 21.59, '789 Oak St, City, Country', 'Cash on Delivery', 'Cancelled', NULL, NULL),
(12, 7, 3, '2023-11-08 00:24:25', 10.04, '789 Oak St, City, Country', 'Cash on Delivery', 'In Process', NULL, NULL),
(13, 7, 3, '2023-11-08 19:46:02', 10.04, '789 Oak St, City, Country', 'Cash on Delivery', 'In Process', NULL, NULL),
(14, 7, 3, '2023-11-08 20:23:10', 1149.95, '789 Oak St, City, Country', 'Cash on Delivery', 'Packaging', '2023-11-29 15:33:53', NULL),
(15, 7, 3, '2023-11-08 20:23:10', 21.59, '789 Oak St, City, Country', 'Cash on Delivery', 'Waiting For Approval', NULL, NULL),
(16, 7, 3, '2023-11-12 12:31:46', 718.46, '789 Oak St, City, Country', 'Cash on Delivery', 'Ready For Transport', NULL, NULL),
(17, 7, 3, '2023-11-12 12:31:46', 72.00, '789 Oak St, City, Country', 'Cash on Delivery', 'Ready For Transport', NULL, NULL),
(18, 7, 3, '2023-11-12 12:31:46', 6.00, '789 Oak St, City, Country', 'Cash on Delivery', 'Waiting For Approval', NULL, NULL),
(19, 7, 3, '2023-11-12 12:32:43', 49.99, '789 Oak St, City, Country', 'Cash on Delivery', 'Delivered', NULL, 'Await Payment'),
(20, 7, 3, '2023-11-13 00:10:10', 43.18, '789 Oak St', 'Cash on Delivery', 'Delivered', NULL, 'Await Payment'),
(21, 7, 3, '2023-11-13 00:11:15', 85.47, '789 Oak St', 'Cash on Delivery', 'Delivered', NULL, 'Await Payment'),
(22, 7, 3, '2023-11-19 10:35:53', 41.67, '789 Oak St', 'Cash on Delivery', 'Cancelled', NULL, NULL),
(23, 6, 1, '2023-11-30 13:03:11', 413.98, '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', 'Cash on Delivery', 'Ready For Transport', '2023-11-30 07:03:21', NULL),
(24, 6, 3, '2023-11-30 13:03:11', 8.45, '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', 'Cash on Delivery', 'Delivered', '2023-11-30 06:49:36', 'Payed'),
(25, 6, 1, '2023-12-04 18:53:15', 229.99, '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', 'Cash on Delivery', 'Delivered', NULL, 'Payed'),
(26, 6, 3, '2023-12-04 18:53:15', 3.99, '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', 'Cash on Delivery', 'Delivered', NULL, 'Payed'),
(27, 6, 1, '2023-12-04 19:45:01', 49.99, '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', 'Cash on Delivery', 'Waiting For Approval', NULL, NULL),
(28, 6, 3, '2023-12-04 19:45:02', 8.45, '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', 'Cash on Delivery', 'Waiting For Approval', NULL, NULL),
(29, 6, 11, '2023-12-04 19:45:02', 50.00, '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', 'Cash on Delivery', 'Ready For Transport', '2023-12-04 12:46:31', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ordercancellation`
--

CREATE TABLE `ordercancellation` (
  `CancellationID` int(11) NOT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ShopOwnerID` int(11) DEFAULT NULL,
  `CancellationDate` date DEFAULT NULL,
  `Reason` text DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ordercancellation`
--

INSERT INTO `ordercancellation` (`CancellationID`, `OrderID`, `UserID`, `ShopOwnerID`, `CancellationDate`, `Reason`, `Status`, `CreatedAt`) VALUES
(1, 1, 2, 3, '2023-10-15', 'Product was damaged during shipping', b'1', '2023-10-15 15:29:06'),
(3, 1, 1, 3, '2023-10-15', 'Product was damaged during shipping', b'1', '2023-10-15 15:29:34'),
(4, 2, 2, 3, '2023-10-16', 'Changed my mind about the order', b'1', '2023-10-15 15:29:35'),
(8, 18, 7, 3, NULL, 'Miss click', NULL, '2023-11-29 15:35:07'),
(9, 28, 6, 3, NULL, 'text', NULL, '2023-12-04 12:55:50');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetail`
--

CREATE TABLE `orderdetail` (
  `OrderDetailID` int(11) NOT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `UnitPrice` double(10,2) DEFAULT NULL,
  `Subtotal` double(10,2) DEFAULT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `ProductDescription` text DEFAULT NULL,
  `ProductImage` varchar(255) DEFAULT NULL,
  `IsReviewed` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orderdetail`
--

INSERT INTO `orderdetail` (`OrderDetailID`, `OrderID`, `ProductID`, `Quantity`, `UnitPrice`, `Subtotal`, `ProductName`, `ProductDescription`, `ProductImage`, `IsReviewed`) VALUES
(1, 1, 1, 2, 24.99, 49.98, 'ROG CETRA TRUE Wireless gaming headset', 'ROG CETRA TRUE Wireless gaming headset', 'product1.jpg', 1),
(3, 1, 2, 2, 24.99, 49.98, 'Anti-snoring machine with dual vortex technology', 'Anti-snoring machine with dual vortex technology', 'product1.jpg', 1),
(4, 2, 2, 3, 19.99, 59.97, 'MPPT45 WIF CHARGER', 'MPPT45 WIF CHARGER', 'product2.jpg', 1),
(5, 3, 3, 1, 29.99, 29.99, 'Dogitek M45 wireless headphones', 'Dogitek M45 wireless headphones have a rugged design with many ENC, ACC, noise suppression, and waterproof technologies (EU version, 2023)', 'product3.jpg', 1),
(6, 4, 9, 1, 29.99, 29.99, 'Infinity Cube Fun Entertainment', 'Infinity Cube Fun Entertainment', 'product3.jpg', 1),
(7, 5, 8, 1, 29.99, 29.99, 'Modern hot and cold 2-way hair dryer with high capacity of 3500W', 'Modern hot and cold 2-way hair dryer with high capacity of 3500W', 'product3.jpg', 1),
(8, 6, 18, 1, 29.99, 29.99, '20000mAh Transparent Power Bank TZ07', '20000mAh Transparent Power Bank TZ07 With 2 Fast Charging Ports 22.5w and PD 20W With 4 LED Screen Chargers - BH 24T', 'product3.jpg', 1),
(9, 6, 17, 1, 29.99, 29.99, 'HOCO 10000mAh battery', 'HOCO 10000mAh battery backup charger with built-in charging cord for many phone devices 24 Months Warranty', 'product3.jpg', 1),
(10, 10, 3, 1, 258.69, 258.69, 'Headphones ', 'Sony WH-1000XM4 Wireless Premium Noise Canceling Overhead Headphones with Mic for Phone-Call and Alexa Voice Control, Silver WH1000XM4', '51SKmu2G9FL.jpg', 1),
(11, 10, 2, 1, 28.49, 28.49, 'Gaming Chair', 'Gaming Chair Massage with LED RGB Lights and Footrest Ergonomic Computer Gaming Chair with High Back Video Game Chair with Adjustable Lumbar Support Red and Black', '61fhPNvlO7L._AC_SX522_.jpg', 1),
(12, 11, 7, 1, 21.59, 21.59, 'Baby Girls\' ', 'Simple Joys by Carter\'s Toddlers and Baby Girls\' Loose-Fit Flame Resistant Fleece Footed Pajamas, Pack of 3', '91bwj3Ay8nL._AC_UX679_.jpg', 1),
(13, 12, 5, 1, 10.04, 10.04, 'T-Shirt', 'The Children\'s Place Girls\' Long Sleeve Animal Graphic T-Shirt', '91E4MhzC84L._AC_UX466_.jpg', 1),
(14, 13, 5, 1, 10.04, 10.04, 'T-Shirt', 'The Children\'s Place Girls\' Long Sleeve Animal Graphic T-Shirt', '91E4MhzC84L._AC_UX466_.jpg', 1),
(15, 14, 1, 5, 229.99, 1149.95, 'Portable SSD', 'SAMSUNG T7 Shield 4TB, Portable SSD, up-to 1050MB/s, USB 3.2 Gen2, Rugged, IP65 Water & Dust Resistant, for Photographers, Content Creators and Gaming, Extenal Solid State Drive (MU-PE4T0S/AM), Black', 'samsung.jpg', 1),
(16, 15, 7, 1, 21.59, 21.59, 'Baby Girls\' ', 'Simple Joys by Carter\'s Toddlers and Baby Girls\' Loose-Fit Flame Resistant Fleece Footed Pajamas, Pack of 3', '91bwj3Ay8nL._AC_UX679_.jpg', 1),
(17, 16, 1, 3, 229.99, 689.97, 'Portable SSD', 'SAMSUNG T7 Shield 4TB, Portable SSD, up-to 1050MB/s, USB 3.2 Gen2, Rugged, IP65 Water & Dust Resistant, for Photographers, Content Creators and Gaming, Extenal Solid State Drive (MU-PE4T0S/AM), Black', 'samsung.jpg', 1),
(18, 16, 2, 1, 28.49, 28.49, 'Gaming Chair', 'Gaming Chair Massage with LED RGB Lights and Footrest Ergonomic Computer Gaming Chair with High Back Video Game Chair with Adjustable Lumbar Support Red and Black', '61fhPNvlO7L._AC_SX522_.jpg', 1),
(19, 17, 10, 1, 72.00, 72.00, 'Sofa Bed ', 'Marshmallow Furniture, Minnie Mouse 3-in-1 Slumber Sofa Baby Lounger, Convertible Kids Couch, Sofa Bed & Foam Toddler Nap Mat with Attached Blanket', '81aU4DZHoLL._AC_SX679_.jpg', 1),
(20, 18, 12, 1, 6.00, 6.00, 'Don\'t Push the Button', 'Don\'t Push the Button! A Halloween Treat: A Spooky Fun Interactive Book For Kids', '6139cKj7T4L._SY466_.jpg', 0),
(21, 19, 4, 1, 49.99, 49.99, 'Sony WI-1000XM2', 'Sony WH-1000XM4 Wireless Noise Canceling Overhead Headphones with Mic for Phone-Call, Voice Control, Silver, with USB Wall Adapter and Microfiber Cleaning Cloth - Bundle', '719UxFdAiSL._AC_SX466_.jpg', 0),
(22, 20, 7, 2, 21.59, 43.18, 'Baby Girls\' ', 'Simple Joys by Carter\'s Toddlers and Baby Girls\' Loose-Fit Flame Resistant Fleece Footed Pajamas, Pack of 3', '91bwj3Ay8nL._AC_UX679_.jpg', 1),
(23, 21, 2, 3, 28.49, 85.47, 'Gaming Chair', 'Gaming Chair Massage with LED RGB Lights and Footrest Ergonomic Computer Gaming Chair with High Back Video Game Chair with Adjustable Lumbar Support Red and Black', '61fhPNvlO7L._AC_SX522_.jpg', 0),
(24, 22, 5, 2, 10.04, 20.08, 'T-Shirt', 'The Children\'s Place Girls\' Long Sleeve Animal Graphic T-Shirt', '91E4MhzC84L._AC_UX466_.jpg', 0),
(25, 22, 7, 1, 21.59, 21.59, 'Baby Girls\' ', 'Simple Joys by Carter\'s Toddlers and Baby Girls\' Loose-Fit Flame Resistant Fleece Footed Pajamas, Pack of 3', '91bwj3Ay8nL._AC_UX679_.jpg', 1),
(26, 23, 1, 2, 206.99, 413.98, 'Portable SSD', 'SAMSUNG T7 Shield 4TB, Portable SSD, up-to 1050MB/s, USB 3.2 Gen2, Rugged, IP65 Water & Dust Resistant, for Photographers, Content Creators and Gaming, Extenal Solid State Drive (MU-PE4T0S/AM), Black', 'samsung.jpg', 0),
(27, 24, 14, 1, 8.45, 8.45, 'Paint by Sticker Kids: Zoo Animals', 'Paint by Sticker Kids: Zoo Animals: Create 10 Pictures One Sticker at a Time (Paint by Sticker)', '81GIvo3b02L._SY466_.jpg', 1),
(28, 25, 1, 1, 229.99, 229.99, 'Portable SSD', '<p>SAMSUNG T7 Shield 4TB, Portable SSD, up-to 1050MB/s, USB 3.2 Gen2, Rugged, IP65 Water &amp; Dust Resistant, for Photographers, Content Creators and Gaming, Extenal Solid State Drive (MU-PE4T0S/AM), Black</p>', 'samsung.jpg', 1),
(29, 26, 13, 1, 3.99, 3.99, 'Themed Coloring Pages of Turtle, Shark, Whale, Dolphin', 'Sea Creatures Color By Number: 50 Big and Easy Ocean Animal Themed Coloring Pages of Turtle, Shark, Whale, Dolphin & Many More for Kids, Boys, Girls ', '71niI22e2-L._SY466_.jpg', 0),
(30, 27, 4, 1, 49.99, 49.99, 'Sony WI-1000XM2', 'Sony WH-1000XM4 Wireless Noise Canceling Overhead Headphones with Mic for Phone-Call, Voice Control, Silver, with USB Wall Adapter and Microfiber Cleaning Cloth - Bundle', '719UxFdAiSL._AC_SX466_.jpg', 0),
(31, 28, 14, 1, 8.45, 8.45, 'Paint by Sticker Kids: Zoo Animals', 'Paint by Sticker Kids: Zoo Animals: Create 10 Pictures One Sticker at a Time (Paint by Sticker)', '81GIvo3b02L._SY466_.jpg', 0),
(32, 29, 20, 1, 50.00, 50.00, 'Product shop 4', '<p>Product shop 4</p>', '9cb48cd645354fab93125f5614b46dd8.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `parentcategory`
--

CREATE TABLE `parentcategory` (
  `ParentCategoryID` int(11) NOT NULL,
  `ParentCategoryName` varchar(255) DEFAULT NULL,
  `ParentCategoryDescription` text DEFAULT NULL,
  `ParentCategoryImagePath` varchar(255) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parentcategory`
--

INSERT INTO `parentcategory` (`ParentCategoryID`, `ParentCategoryName`, `ParentCategoryDescription`, `ParentCategoryImagePath`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 'No Parent Category', 'Parent category for electronic products', 'e454edf3e923454d89b6eb555b9545c5.png', '2023-10-14 17:00:00', '2023-12-02 13:06:18'),
(2, 'Clothing', 'Parent category for clothing and fashion products', 'b212133cb4624fce9c4f8c3a0486f62b.png', '2023-10-14 17:00:00', '2023-12-02 11:11:47'),
(3, 'Furniture', 'Parent category for furniture and home decor products', '751e4e30c4d54f62b0c5d03c7304bc30.png', '2023-10-14 17:00:00', '2023-12-02 11:12:07'),
(4, 'Electronic', 'No Parent Category Description', '39554209e60845fabcb4c3e872708b05.jpg', '2023-12-02 11:15:17', '2023-12-02 13:06:25');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ProductID` int(11) NOT NULL,
  `ShopOwnerID` int(11) DEFAULT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `Description` text DEFAULT NULL,
  `Price` double(10,2) DEFAULT NULL,
  `CategoryID` int(11) DEFAULT NULL,
  `StockQuantity` int(11) DEFAULT NULL,
  `ProductImage` varchar(255) DEFAULT NULL,
  `ExpiryDate` date DEFAULT NULL,
  `Status` tinyint(1) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductID`, `ShopOwnerID`, `ProductName`, `Description`, `Price`, `CategoryID`, `StockQuantity`, `ProductImage`, `ExpiryDate`, `Status`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 1, 'Portable SSD', '<p>SAMSUNG T7 Shield 4TB, Portable SSD, up-to 1050MB/s, USB 3.2 Gen2, Rugged, IP65 Water &amp; Dust Resistant, for Photographers, Content Creators and Gaming, Extenal Solid State Drive (MU-PE4T0S/AM), Black</p>', 229.99, 17, 98, 'samsung.jpg', '2023-12-31', 1, '2023-10-14 17:00:00', '2023-12-04 06:53:05'),
(2, 1, 'Gaming Chair', 'Gaming Chair Massage with LED RGB Lights and Footrest Ergonomic Computer Gaming Chair with High Back Video Game Chair with Adjustable Lumbar Support Red and Black', 29.99, 17, 0, '61fhPNvlO7L._AC_SX522_.jpg', '2023-11-30', 1, '2023-10-15 15:18:11', '2023-12-02 13:02:35'),
(3, 1, 'Headphones ', 'Sony WH-1000XM4 Wireless Premium Noise Canceling Overhead Headphones with Mic for Phone-Call and Alexa Voice Control, Silver WH1000XM4', 258.69, 17, 75, '51SKmu2G9FL.jpg', '2024-01-31', 1, '2023-10-15 15:18:11', '2023-12-02 13:02:38'),
(4, 1, 'Sony WI-1000XM2', 'Sony WH-1000XM4 Wireless Noise Canceling Overhead Headphones with Mic for Phone-Call, Voice Control, Silver, with USB Wall Adapter and Microfiber Cleaning Cloth - Bundle', 49.99, 17, 100, '719UxFdAiSL._AC_SX466_.jpg', '2023-12-31', 1, '2023-10-15 08:18:11', '2023-12-02 13:18:00'),
(5, 2, 'T-Shirt', 'The Children\'s Place Girls\' Long Sleeve Animal Graphic T-Shirt', 10.04, 2, 50, '91E4MhzC84L._AC_UX466_.jpg', '2023-11-30', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:01'),
(6, 2, ' Cotton Tee', 'Hanes Women\'s Raglan Sleeve Tee, Women’s Stretch Cotton Tee, Women’s Crewneck Tee', 12.00, 2, 100, '71fczrTUjQL._AC_UX569_.jpg', '2023-12-31', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:03'),
(7, 2, 'Baby Girls\' ', 'Simple Joys by Carter\'s Toddlers and Baby Girls\' Loose-Fit Flame Resistant Fleece Footed Pajamas, Pack of 3', 21.59, 2, 50, '91bwj3Ay8nL._AC_UX679_.jpg', '2023-11-30', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:05'),
(8, 2, ' V-Neck T-Shirt', 'Amazon Essentials Women\'s Classic-Fit Short-Sleeve V-Neck T-Shirt, Multipacks', 29.00, 2, 75, '71JpCcQ-2IS._AC_UY550_.jpg', '2024-01-31', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:08'),
(9, 2, 'Y2K Nostalgia', 'Bratz Funky Fashion Furniture Retro-Swing Chair Playset & Display with Footrest, Swings Back & Forth, Fits Dolls Up to 12\" Tall, Y2K Nostalgia, Collectors Ages 6 7 8 9 10+', 24.00, 3, 100, '81Ogs2hh1YL._AC_SX679_.jpg', '2023-12-31', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:11'),
(10, 2, 'Sofa Bed ', 'Marshmallow Furniture, Minnie Mouse 3-in-1 Slumber Sofa Baby Lounger, Convertible Kids Couch, Sofa Bed & Foam Toddler Nap Mat with Attached Blanket', 72.00, 3, 50, '81aU4DZHoLL._AC_SX679_.jpg', '2023-11-30', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:13'),
(11, 3, 'The Woman in Me', 'The Woman in Me Hardcover – October 24, 2023', 19.00, 4, 75, '61KI7oL-u9L._SY466_.jpg', '2024-01-31', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:16'),
(12, 3, 'Don\'t Push the Button', 'Don\'t Push the Button! A Halloween Treat: A Spooky Fun Interactive Book For Kids', 6.00, 4, 100, '6139cKj7T4L._SY466_.jpg', '2023-12-31', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:18'),
(13, 3, 'Themed Coloring Pages of Turtle, Shark, Whale, Dolphin', 'Sea Creatures Color By Number: 50 Big and Easy Ocean Animal Themed Coloring Pages of Turtle, Shark, Whale, Dolphin & Many More for Kids, Boys, Girls ', 3.99, 5, 50, '71niI22e2-L._SY466_.jpg', '2023-11-30', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:20'),
(14, 3, 'Paint by Sticker Kids: Zoo Animals', 'Paint by Sticker Kids: Zoo Animals: Create 10 Pictures One Sticker at a Time (Paint by Sticker)', 8.45, 5, 74, '81GIvo3b02L._SY466_.jpg', '2024-01-31', 1, '2023-10-15 08:18:11', '2023-11-30 06:49:36'),
(15, 3, 'Macard WiFi Extender Booster', 'Macard WiFi Extender Booster - 2023 Release Up To 74% Faster - Broader Coverage Than Ever, Signal Booster for Home - with Ethernet Port, Made for USA', 44.99, 6, 100, '51kHOZQpA5L._AC_SY879_.jpg', '2023-12-31', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:24'),
(16, 3, 'Sceptre 24-inch Professional Thin 1080p LED Monitor', 'Sceptre 24-inch Professional Thin 1080p LED Monitor 99% sRGB 2x HDMI VGA Build-in Speakers, Machine Black (E248W-19203R Series)', 89.99, 6, 50, '81zM2vVM+wL._AC_SX679_.jpg', '2023-11-30', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:27'),
(17, 3, 'NexiGo N60 1080P Webcam with Microphone', 'NexiGo N60 1080P Webcam with Microphone, Adjustable FOV, Zoom, Software Control & Privacy Cover, USB HD Computer Web Camera, Plug and Play, for Zoom/Skype/Teams, Conferencing and Video Calling', 29.69, 6, 75, '51EPhWPDRhL._AC_SX679_.jpg', '2024-01-31', 1, '2023-10-15 08:18:11', '2023-10-23 12:14:29'),
(18, 3, 'Anker USB C Charger', 'Anker USB C Charger, 735 Charger (Nano II 65W), iPad Charger, PPS 3-Port Fast Compact Foldable for MacBook Pro/Air, iPad Pro, Galaxy S23, Dell XPS 13, Note 20/10+, iPhone 15/Pro, Steam Deck, and More', 99.99, 17, 75, '518CnkU772L._AC_SX466_.jpg', '2024-01-31', 1, '2023-10-15 08:18:11', '2023-12-02 13:02:47'),
(19, 1, 'Portable 11SSD', '', 12.00, 3, 2, '50c99b3255a44eb0a3438b62bb766348.jpg', NULL, 1, '2023-12-01 17:00:00', '2023-12-04 11:48:08'),
(20, 11, 'Product shop 4', '<p>Product shop 4</p>', 50.00, 19, 9, '9cb48cd645354fab93125f5614b46dd8.jpg', NULL, 1, '2023-12-03 17:00:00', '2023-12-04 12:46:31');

-- --------------------------------------------------------

--
-- Table structure for table `productattribute`
--

CREATE TABLE `productattribute` (
  `ProductAttributeID` int(11) NOT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `AttributeName` varchar(255) DEFAULT NULL,
  `AttributeValue` varchar(255) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productattribute`
--

INSERT INTO `productattribute` (`ProductAttributeID`, `ProductID`, `AttributeName`, `AttributeValue`, `Status`) VALUES
(1, 1, 'Color', 'Red', '1'),
(2, 2, 'Size', 'Large', '1'),
(3, 3, 'Material', 'Cotton', '1'),
(4, 4, 'Color', 'Red', '1'),
(5, 4, 'Size', 'XL', '1'),
(6, 4, 'Color', 'Blue', '1'),
(7, 4, 'Size', 'Large', '1'),
(8, 6, 'Color', 'Red', '1'),
(9, 6, 'Size', 'XL', '1'),
(10, 6, 'Color', 'Blue', '1'),
(11, 6, 'Size', 'Large', '1'),
(12, 5, 'Color', 'Red', '1'),
(13, 5, 'Size', 'XL', '1'),
(14, 5, 'Color', 'Blue', '1'),
(15, 5, 'Size', 'Large', '1');

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `PromotionID` int(11) NOT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `ShopownerId` int(11) DEFAULT NULL,
  `DiscountAmount` double(10,2) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `Description` text DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`PromotionID`, `ProductID`, `ShopownerId`, `DiscountAmount`, `StartDate`, `EndDate`, `Description`, `CreatedAt`, `UpdatedAt`) VALUES
(7, 3, 1, 10.00, '2023-12-01', '2023-12-16', '<p>test</p>', '2023-12-02 12:24:57', NULL),
(8, 1, 1, 1.00, '2023-11-14', '2023-11-14', '<p>test 2</p>', '2023-12-02 12:26:00', '2023-12-02 12:26:39'),
(9, 5, 2, 20.00, '2023-11-13', '2023-11-13', '', '2023-12-02 12:43:37', NULL),
(10, 11, 3, 11.00, '2023-12-01', '2023-12-01', '', '2023-12-04 13:28:29', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `ReviewID` int(11) NOT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `Rating` int(11) DEFAULT NULL,
  `Comment` text DEFAULT NULL,
  `ReviewDate` date DEFAULT NULL,
  `HelpfulCount` int(11) DEFAULT 0,
  `ReportCount` int(11) DEFAULT 0,
  `IsVerified` tinyint(1) DEFAULT 0,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `OrderDetailID` int(11) DEFAULT NULL,
  `ReviewImage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`ReviewID`, `ProductID`, `UserID`, `Rating`, `Comment`, `ReviewDate`, `HelpfulCount`, `ReportCount`, `IsVerified`, `CreatedAt`, `UpdatedAt`, `OrderDetailID`, `ReviewImage`) VALUES
(2, 1, 6, 4, 'Great product, fast shipping!', '2023-10-15', 5, 0, 1, '2023-10-15 15:35:17', '2023-11-30 05:30:22', 22, 'rv1.jpg'),
(3, 2, 6, 5, 'Excellent quality, highly recommended.', '2023-10-16', 10, 0, 1, '2023-10-15 15:35:17', '2023-11-29 15:26:11', 22, NULL),
(4, 3, 7, 3, 'Average product, could be better.', '2023-10-17', 2, 1, 0, '2023-10-15 15:35:17', '2023-11-29 15:26:14', 22, NULL),
(5, 7, 7, 4, 'Great product, fast shipping!', '2023-11-19', 1, 1, 1, '2023-11-19 08:02:30', '2023-11-30 05:26:19', 22, NULL),
(6, 7, 6, 3, 'Great product, fast shipping!', '2023-11-19', 1, 1, 1, '2023-11-19 09:48:09', '2023-11-29 15:26:18', 25, NULL),
(7, 14, 6, 5, 'GOOD', '2023-11-30', NULL, NULL, 1, '2023-11-30 07:22:57', NULL, 27, '4282b86926f148488a1a2e26c917bebf.png'),
(8, 1, 6, 1, 'Ple', '2023-12-04', NULL, NULL, 1, '2023-12-04 12:58:30', NULL, 28, 'cd0cb8f36bc647d6bfb748dd18a93a51.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `RoleID` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Status` tinyint(1) DEFAULT 1,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`RoleID`, `Name`, `Status`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 'ROLE_SUPER_ADMIN', 1, '2023-10-15 15:12:54', '2023-10-18 05:55:58'),
(2, 'ROLE_ADMIN', 2, '2023-10-15 15:12:54', '2023-10-18 05:56:10'),
(3, 'ROLE_SHOPOWNER', 3, '2023-10-15 15:12:54', '2023-10-18 05:56:17'),
(4, 'ROLE_USER', 1, '2023-10-18 05:56:46', '2023-10-18 05:56:46');

-- --------------------------------------------------------

--
-- Table structure for table `salesdata`
--

CREATE TABLE `salesdata` (
  `SaleID` int(11) NOT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ShopOwnerID` int(11) DEFAULT NULL,
  `SaleDate` date DEFAULT NULL,
  `TotalAmount` double(10,2) DEFAULT NULL,
  `PaymentMethod` varchar(255) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salesdata`
--

INSERT INTO `salesdata` (`SaleID`, `OrderID`, `UserID`, `ShopOwnerID`, `SaleDate`, `TotalAmount`, `PaymentMethod`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 1, 1, 1, '2023-10-15', 150.00, 'Credit Card', '2023-10-15 15:37:25', '2023-10-15 15:37:25'),
(2, 2, 2, 2, '2023-10-16', 80.50, 'PayPal', '2023-10-15 15:37:25', '2023-10-15 15:37:25'),
(3, 3, 3, 3, '2023-10-17', 210.75, 'Cash on Delivery', '2023-10-15 15:37:25', '2023-10-15 15:37:25');

-- --------------------------------------------------------

--
-- Table structure for table `shopowner`
--

CREATE TABLE `shopowner` (
  `OwnerID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ShopName` varchar(100) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `ShopDescription` text DEFAULT NULL,
  `ShopLogoPath` varchar(255) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shopowner`
--

INSERT INTO `shopowner` (`OwnerID`, `UserID`, `ShopName`, `status`, `ShopDescription`, `ShopLogoPath`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 3, 'My Electronics Shop', 1, '<p>We sell the latest electronics We sell the latest electronics We sell the latest electronics</p>', 'electronics_shop.jpg', '2023-10-14 17:00:00', '2023-12-04 13:09:20'),
(2, 4, 'Fashion Boutique', 1, 'Trendy fashion for all', 'fashion_boutique.jpg', '2023-10-15 15:27:09', '2023-11-30 06:14:53'),
(3, 5, 'Furniture Emporium', 1, 'Quality furniture and decor', 'furniture_emporium.jpg', '2023-10-15 15:27:09', '2023-11-30 06:15:03'),
(11, 9, 'Shop 4', 1, '<p>Shop 4</p>', '0f8b83f56fa8435eb42a11ce0cbc3a7a.jpg', '2023-12-03 17:00:00', '2023-12-04 13:16:47');

-- --------------------------------------------------------

--
-- Table structure for table `shopownercoupon`
--

CREATE TABLE `shopownercoupon` (
  `ShopOwnerCouponID` int(11) NOT NULL,
  `ShopOwnerID` int(11) DEFAULT NULL,
  `CouponCode` varchar(20) DEFAULT NULL,
  `DiscountAmount` double(10,2) DEFAULT NULL,
  `ExpiryDate` date DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT 1,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shopownercoupon`
--

INSERT INTO `shopownercoupon` (`ShopOwnerCouponID`, `ShopOwnerID`, `CouponCode`, `DiscountAmount`, `ExpiryDate`, `IsActive`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 3, 'SHOP20OFF', 20.00, '2023-12-31', 1, '2023-10-15 15:38:17', '2023-11-27 17:13:10'),
(2, 3, 'SALE10', 10.00, '2023-11-30', 1, '2023-10-15 15:38:17', '2023-11-27 17:13:14'),
(3, 3, 'HOLIDAY25', 25.00, '2023-12-25', 1, '2023-10-15 15:38:17', '2023-10-15 15:38:17');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Avatar` varchar(255) DEFAULT NULL,
  `Gender` varchar(255) DEFAULT NULL,
  `Dob` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `RoleID` int(11) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `Token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Username`, `Password`, `Email`, `FirstName`, `LastName`, `Address`, `PhoneNumber`, `Avatar`, `Gender`, `Dob`, `status`, `RoleID`, `CreatedAt`, `UpdatedAt`, `Token`) VALUES
(1, 'superadmin', '$2a$10$FCTHw8dWPvGbCdr0yIMGp.ZyG8Thzr.z0TArYhFvcxiq2a9LuUxp2', 'superadmin@example.com', 'John', 'Doe', '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', '123-456-7890', 'avatar1.jpg', 'female', '1990-01-15', 1, 1, '2023-10-15 15:16:04', '2023-11-29 15:20:50', NULL),
(2, 'admin', '$2a$10$FCTHw8dWPvGbCdr0yIMGp.ZyG8Thzr.z0TArYhFvcxiq2a9LuUxp2', 'admin@example.com', 'Jane', 'Smith', '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', '987-654-3210', 'avatar2.jpg', 'male', '1995-03-20', 1, 2, '2023-10-15 15:16:04', '2023-11-29 15:20:55', NULL),
(3, 'shop1', '$2a$10$FCTHw8dWPvGbCdr0yIMGp.ZyG8Thzr.z0TArYhFvcxiq2a9LuUxp2', 'shop1@example.com', 'Mike', 'Johnson', '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', '555-123-4567', 'avatar3.jpg', 'male', '1985-07-10', 1, 3, '2023-10-15 15:16:04', '2023-12-04 13:09:20', NULL),
(4, 'shop2', '$2a$10$xCpWxzZY3/tVzIpeSu7jD.VtietutgJhcxKpJu9g3wl6OVrQYf1WO', 'shop2@example.com', 'Mike', 'Johnson', '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', '555-123-4567', '08ddcbf5f1b54bb5b9894d9e060d9b38.png', 'male', '1985-07-10', 1, 3, '2023-10-14 17:00:00', '2023-11-29 15:21:04', NULL),
(5, 'shop3', '$2a$10$QJuQE1g56zTVSKdXfh724eCp6GH5B0vkRB9.hFcajtB8Wc97Rq7XW', 'shop3@example.com', 'John', 'Doe', '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', '555-123-4567', '5a3a4fb4796b4858a22d77ba61abc073.png', 'female', '2023-10-09', 1, 3, '2023-11-02 17:00:00', '2023-11-29 15:21:09', NULL),
(6, 'user1', '$2a$10$Sqnpewl0sUl9XHjGdnMcduVQG/WRV0RIdxE6t5hjp1SQq3B8oJxm.', 'user1@example.com', 'John', 'Doe', '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', '555-123-4567', '633a4fb4796b4858a22d77ba61abc073.png', 'female', '2023-10-09', 1, 4, '2023-11-02 17:00:00', '2023-12-02 12:30:12', NULL),
(7, 'user2', '$2a$10$QJuQE1g56zTVSKdXfh724eCp6GH5B0vkRB9.hFcajtB8Wc97Rq7XW', 'user2@example.com', 'John', 'Doe', '622 D. Cong Hoa Ward 13, Tan Binh, Ho Chi Minh City', '555-123-4567', '5a5a4fb4796b4858a22d77ba61abc073.png', 'female', '2023-10-09', 1, 4, '2023-11-02 17:00:00', '2023-11-30 05:03:42', NULL),
(9, 'shop4', '$2a$10$wOs47r7lRvmGqeyJAeBLKe5UHXW5EsKHRTvH5OIn.DuVnzb/D/q9G', '222thienvo@gmail.com', 'SHOP', 'DO', 'address12', '987654321kkkk', '9bc3a3956b9a4dd19845f5c643909982.png', 'female', '2023-12-03', 1, 3, '2023-12-04 12:29:26', '2023-12-04 13:09:44', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `WishlistID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`WishlistID`, `UserID`, `ProductID`, `CreatedAt`) VALUES
(1, 7, 1, '2023-10-15 15:27:28'),
(2, 7, 2, '2023-10-15 15:27:28'),
(3, 7, 3, '2023-10-15 15:27:28'),
(8, 6, 13, '2023-12-02 12:39:37'),
(9, 6, 4, '2023-12-04 12:41:45');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryID`),
  ADD KEY `ParentCategoryID` (`ParentCategoryID`),
  ADD KEY `category_user_fk` (`UserId`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`InvoiceID`),
  ADD KEY `OrderID` (`OrderID`),
  ADD KEY `ShopOwnerID` (`ShopOwnerID`),
  ADD KEY `CustomerID` (`CustomerID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`ItemID`),
  ADD KEY `UserID` (`UserId`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`NotificationID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`OrderID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `order_shopowner_fk` (`ShopOwnerID`);

--
-- Indexes for table `ordercancellation`
--
ALTER TABLE `ordercancellation`
  ADD PRIMARY KEY (`CancellationID`),
  ADD KEY `OrderID` (`OrderID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `ShopOwnerID` (`ShopOwnerID`);

--
-- Indexes for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`OrderDetailID`),
  ADD KEY `OrderID` (`OrderID`,`ProductID`),
  ADD KEY `order_product_fk` (`ProductID`);

--
-- Indexes for table `parentcategory`
--
ALTER TABLE `parentcategory`
  ADD PRIMARY KEY (`ParentCategoryID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`),
  ADD KEY `CategoryID` (`CategoryID`),
  ADD KEY `product_ibfk_3` (`ShopOwnerID`);

--
-- Indexes for table `productattribute`
--
ALTER TABLE `productattribute`
  ADD PRIMARY KEY (`ProductAttributeID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`PromotionID`),
  ADD KEY `ProductID` (`ProductID`),
  ADD KEY `promotion_shopowner_fk` (`ShopownerId`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`ReviewID`),
  ADD KEY `ProductID` (`ProductID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `OrderDetailID` (`OrderDetailID`) USING BTREE;

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`RoleID`);

--
-- Indexes for table `salesdata`
--
ALTER TABLE `salesdata`
  ADD PRIMARY KEY (`SaleID`),
  ADD KEY `OrderID` (`OrderID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `ShopOwnerID` (`ShopOwnerID`);

--
-- Indexes for table `shopowner`
--
ALTER TABLE `shopowner`
  ADD PRIMARY KEY (`OwnerID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `shopownercoupon`
--
ALTER TABLE `shopownercoupon`
  ADD PRIMARY KEY (`ShopOwnerCouponID`),
  ADD KEY `ShopOwnerID` (`ShopOwnerID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `RoleID` (`RoleID`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`WishlistID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `CategoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `InvoiceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `ItemID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `NotificationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `OrderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `ordercancellation`
--
ALTER TABLE `ordercancellation`
  MODIFY `CancellationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `orderdetail`
--
ALTER TABLE `orderdetail`
  MODIFY `OrderDetailID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `parentcategory`
--
ALTER TABLE `parentcategory`
  MODIFY `ParentCategoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `productattribute`
--
ALTER TABLE `productattribute`
  MODIFY `ProductAttributeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `PromotionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `ReviewID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `RoleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `salesdata`
--
ALTER TABLE `salesdata`
  MODIFY `SaleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `shopowner`
--
ALTER TABLE `shopowner`
  MODIFY `OwnerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `shopownercoupon`
--
ALTER TABLE `shopownercoupon`
  MODIFY `ShopOwnerCouponID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `WishlistID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`ParentCategoryID`) REFERENCES `parentcategory` (`ParentCategoryID`),
  ADD CONSTRAINT `category_user_fk` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`),
  ADD CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`ShopOwnerID`) REFERENCES `shopowner` (`OwnerID`),
  ADD CONSTRAINT `invoice_ibfk_3` FOREIGN KEY (`CustomerID`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `invoice_ibfk_4` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `order_shopowner_fk` FOREIGN KEY (`ShopOwnerID`) REFERENCES `shopowner` (`OwnerID`);

--
-- Constraints for table `ordercancellation`
--
ALTER TABLE `ordercancellation`
  ADD CONSTRAINT `ordercancellation_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`),
  ADD CONSTRAINT `ordercancellation_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `ordercancellation_ibfk_3` FOREIGN KEY (`ShopOwnerID`) REFERENCES `shopowner` (`OwnerID`);

--
-- Constraints for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD CONSTRAINT `order_product_fk` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`),
  ADD CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`ShopOwnerID`) REFERENCES `shopowner` (`OwnerID`);

--
-- Constraints for table `productattribute`
--
ALTER TABLE `productattribute`
  ADD CONSTRAINT `productattribute_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`);

--
-- Constraints for table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`),
  ADD CONSTRAINT `promotion_shopowner_fk` FOREIGN KEY (`ShopownerId`) REFERENCES `shopowner` (`OwnerID`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`),
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `review_ibfk_3` FOREIGN KEY (`OrderDetailID`) REFERENCES `orderdetail` (`OrderDetailID`);

--
-- Constraints for table `salesdata`
--
ALTER TABLE `salesdata`
  ADD CONSTRAINT `salesdata_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`),
  ADD CONSTRAINT `salesdata_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `salesdata_ibfk_3` FOREIGN KEY (`ShopOwnerID`) REFERENCES `shopowner` (`OwnerID`);

--
-- Constraints for table `shopowner`
--
ALTER TABLE `shopowner`
  ADD CONSTRAINT `shopowner_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `shopownercoupon`
--
ALTER TABLE `shopownercoupon`
  ADD CONSTRAINT `shopownercoupon_ibfk_1` FOREIGN KEY (`ShopOwnerID`) REFERENCES `shopowner` (`OwnerID`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`);

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `wishlist_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
