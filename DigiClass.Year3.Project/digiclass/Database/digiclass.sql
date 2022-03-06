-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2021 at 01:20 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `digiclass`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `teacher_subject_id` int(11) NOT NULL,
  `places` int(2) NOT NULL,
  `course_description` varchar(150) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `exist` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_id`, `teacher_subject_id`, `places`, `course_description`, `start_date`, `end_date`, `exist`) VALUES
(85, 83, 21, 'History OL Students', '2021-05-24', '2021-09-28', 0),
(86, 81, 20, 'Comp Science HL', '2021-04-28', '2021-08-31', 1),
(93, 92, 24, 'We will teach you how to farm hai', '2021-05-17', '2021-09-08', 1),
(94, 92, 24, 'We will teach you how to farm hai', '2021-05-17', '2021-09-08', 1),
(105, 85, 20, 'Art HL', '2021-05-24', '2021-09-14', 0),
(106, 85, 12, 'des', '2021-05-27', '2021-09-21', 1),
(107, 84, 12, 'History OL Students', '2021-05-24', '2021-09-28', 1);

-- --------------------------------------------------------

--
-- Table structure for table `course_links`
--

CREATE TABLE `course_links` (
  `course_link_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `link` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `course_messages`
--

CREATE TABLE `course_messages` (
  `course_message_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_messages`
--

INSERT INTO `course_messages` (`course_message_id`, `course_id`, `message`, `date`) VALUES
(12, 107, 'test', '2021-05-06');

-- --------------------------------------------------------

--
-- Table structure for table `course_students`
--

CREATE TABLE `course_students` (
  `course_students_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_students`
--

INSERT INTO `course_students` (`course_students_id`, `course_id`, `student_id`) VALUES
(73, 106, 66);

-- --------------------------------------------------------

--
-- Table structure for table `course_waiting_list`
--

CREATE TABLE `course_waiting_list` (
  `course_waiting_list_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `enquiries`
--

CREATE TABLE `enquiries` (
  `inquiry_id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `seen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enquiries`
--

INSERT INTO `enquiries` (`inquiry_id`, `full_name`, `email`, `message`, `date`, `seen`) VALUES
(7, 'Denis Mooney', 'denis.e.mooney@gmail.com', 'Keep pushing forward!', '2020-03-03', 0),
(8, 'Jia Jun Tan', 'jiajuntan@gmail.com', 'Success is key!', '2020-02-03', 0),
(9, 'Conor McLoughlin', 'jiajuntan@gmail.com', 'Keep pushing forward!', '2020-02-03', 1),
(10, 'James Bellew', 'jamesbellew@gmail.com', 'Success is key!', '2020-04-03', 1),
(12, '123', 'G', '123', '2021-03-23', 0);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `description` varchar(225) NOT NULL,
  `seen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `review_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `ratingOutOfTen` int(2) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`review_id`, `teacher_id`, `student_id`, `ratingOutOfTen`, `date`, `description`) VALUES
(1, 48, 66, 8, '2021-03-16', 'test'),
(3, 48, 66, 9, '2021-04-04', 'DES'),
(4, 44, 66, 10, '2021-05-05', '                Excellent Teacher!');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `subject_id` int(2) NOT NULL,
  `subject_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`subject_id`, `subject_name`) VALUES
(1, 'Accounting'),
(2, 'Agricultural Science'),
(3, 'Applied Mathematics'),
(4, 'Arabic'),
(5, 'Art'),
(6, 'Biology'),
(7, 'Bulgarian'),
(8, 'Business'),
(9, 'Chemisty'),
(10, 'Classical Studies'),
(11, 'Computer Science'),
(12, 'Construction Studies'),
(13, 'Croatian'),
(14, 'Czech'),
(15, 'Design & Communication Graphics'),
(16, 'Dutch'),
(17, 'Economics'),
(18, 'Engineering'),
(19, 'English'),
(20, 'French'),
(21, 'Geography'),
(22, 'German'),
(23, 'History'),
(24, 'Home Economics S & S'),
(25, 'Hungarian'),
(26, 'Irish'),
(27, 'Italian'),
(28, 'Japanese'),
(29, 'Latin'),
(30, 'Latvian'),
(31, 'Link Modules'),
(32, 'Lithuanian'),
(33, 'Mathematics'),
(34, 'Music'),
(35, 'Physical Education'),
(36, 'Physics'),
(37, 'Polish'),
(38, 'Politics and Society'),
(39, 'Religious Education'),
(40, 'Romanian'),
(41, 'Russian'),
(42, 'Spanish'),
(43, 'Technology');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_details`
--

CREATE TABLE `teacher_details` (
  `teacher_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  `about_me` varchar(255) NOT NULL,
  `verify` tinyint(1) NOT NULL,
  `premium` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher_details`
--

INSERT INTO `teacher_details` (`teacher_id`, `user_id`, `qualifications`, `about_me`, `verify`, `premium`) VALUES
(42, 64, '', 'ABOUT', 1, 0),
(43, 67, 'conorDegree.jpg', 'My name is Conor! It will be my pleasure to teach.', 1, 0),
(44, 69, 'jackRiceHonoursDegree.jpg', 'Hi! I love Engineering', 1, 0),
(45, 71, '', 'ABOUT', 1, 0),
(46, 73, 'qualification1234.jpg', 'ABOUT', 1, 0),
(48, 79, '', 'Ido not know how they are letting me teach anything', 1, 0),
(49, 80, '', 'about me\r\n', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `teacher_subjects`
--

CREATE TABLE `teacher_subjects` (
  `teacher_subject_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `subject_level` varchar(2) NOT NULL,
  `past` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher_subjects`
--

INSERT INTO `teacher_subjects` (`teacher_subject_id`, `teacher_id`, `subject_id`, `subject_level`, `past`) VALUES
(77, 42, 1, 'HL', 0),
(78, 42, 6, 'OL', 1),
(79, 42, 4, 'HL', 0),
(80, 42, 21, 'OL', 0),
(81, 43, 11, 'HL', 0),
(82, 43, 17, 'HL', 0),
(83, 44, 18, 'HL', 0),
(84, 44, 23, 'OL', 0),
(85, 45, 5, 'HL', 0),
(86, 45, 19, 'HL', 0),
(87, 46, 30, 'HL', 0),
(88, 46, 18, 'HL', 0),
(92, 48, 2, 'HL', 0),
(93, 48, 17, 'HL', 0),
(94, 48, 19, 'OL', 0),
(95, 49, 3, 'HL', 0);

-- --------------------------------------------------------

--
-- Table structure for table `timetable`
--

CREATE TABLE `timetable` (
  `timetable_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `day` varchar(9) NOT NULL,
  `time` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `timetable`
--

INSERT INTO `timetable` (`timetable_id`, `course_id`, `day`, `time`) VALUES
(139, 85, 'Wednesday', 22),
(141, 85, 'Friday', 22),
(142, 86, 'Monday', 20),
(143, 86, 'Thursday', 20),
(144, 86, 'Friday', 20),
(145, 86, 'Saturday', 20),
(157, 94, 'Monday', 10),
(158, 94, 'Tuesday', 9),
(159, 94, 'Wednesday', 11),
(160, 94, 'Thursday', 10),
(173, 105, 'Monday', 20),
(174, 105, 'Tuesday', 20),
(175, 105, 'Friday', 20),
(176, 106, 'Thursday', 12),
(177, 106, 'Friday', 12),
(178, 107, 'Monday', 12),
(179, 107, 'Friday', 12),
(180, 107, 'Wednesday', 12);

-- --------------------------------------------------------

--
-- Table structure for table `timetable_cancellations`
--

CREATE TABLE `timetable_cancellations` (
  `timetable_cancellations_id` int(11) NOT NULL,
  `timetable_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_type` varchar(15) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(128) NOT NULL,
  `salt` varchar(250) NOT NULL,
  `user_status` varchar(8) NOT NULL,
  `secret_key` varchar(16) DEFAULT NULL,
  `reset_key` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_type`, `username`, `email`, `password`, `salt`, `user_status`, `secret_key`, `reset_key`) VALUES
(64, 'TEACHER', 'denisTeacher', 'denisTeacher@gmail.com', 'cfe654b2190e3c0678f24b46fc183adac28df4226de429854929383a9ef81785ca928d0a807b963f16ee2af58403398c75165a152b0d5c799ef4af5f1d74ffe0', '[28, 99, 93, 14, 122, 69, -74, 121, -106, -97, -57, -110, -41, -18, 27, -55]', 'ACTIVE', '', ''),
(65, 'ADMIN', 'denisAdmin', 'denisAdmin@gmail.com', 'cfb36e668979cfba4fbcbe344ad002c562a852c3fc367cfaee83605a7b8241f595cac2e80db664f78d2ddb8e418abb38acbdf13b497496915238529ff0526f51', '[-1, 90, -77, -96, -116, 40, -15, -125, 97, 25, 0, 18, -24, -24, -117, -125]', 'ACTIVE', '', ''),
(66, 'STUDENT', 'denisStudent', 'denisStudent@gmail.com', 'f2113b64fd77489852a216f4e25c7343de8280b7a6d433db16e5f9c529110541c96dc8926f4364651a6123ba0bd4b2140dc0126fec1519a124a5435f5f43df5c', '[-88, -31, -80, 91, 83, -32, 97, -101, 49, -128, 6, 0, -22, 93, -76, 2]', 'ACTIVE', 'XJ7F33ZUXAVV5YYQ', ''),
(67, 'TEACHER', 'conorTeacher', 'conorTeacher@gmail.com', 'a808160dca6007a6228933f912bcb27c7459bf60e476798a2025fb61a493e8753a7ccd57f21076620bb4640b51719e6222ac0b9a7a9d918dfcdc4d8ffd931e3f', '[101, -16, 115, -99, 35, -12, 5, -69, -41, 37, -87, 70, -94, -25, 18, 99]', 'ACTIVE', '', ''),
(68, 'STUDENT', 'conorStudent', 'conorStudent@gmail.com', '7c55b4cb73cc36a02257c3850c64e72a48448549631dc3145f317f5e13addaa1fefdd055b0043f7fe5ba6fcd72d01a98b04d44c60e9eee3beb3fdfd069a79848', '[126, -89, 83, -81, -68, 79, 77, 88, 126, -16, -56, 28, 69, 34, 117, -64]', 'ACTIVE', '', ''),
(69, 'TEACHER', 'testTeacher1', 'testTeacher1@gmail.com', '79bbee5a2a8e334427ec6908289f19b1ef03e0513e4e02fee47ad00aa6975039c118b9a2fd6c3ffcfb0cccbe08fe24364c5ef93a40419b350bbc84c7857a0107', '[-76, -79, 73, -11, -1, 89, -69, 7, -70, 93, -46, -21, 39, -126, 102, -1]', 'ACTIVE', 'WQMJSK2LTSPX3R2I', ''),
(70, 'STUDENT', 'testStudent1', 'testStudent1@gmail.com', 'f05a5e6f6b2f22f3b15d6cb32f7b3fb048b41f38f39676baf12d6249d8923109a08d7b4a75181c7566eda93b98d25e3d3e70252898538c53487d19580a9651f0', '[-107, 115, -110, -17, -19, -12, 66, 7, 78, 91, 93, -103, 43, -62, 113, 56]', 'ACTIVE', '', ''),
(71, 'TEACHER', 'testTeacher69', 'testTeacher69@gmail.com', '72bbc7610c6b8abced6a865f11005b37c93166c188d57f43a1fc97e333cb558c7bca982d8ee6325d963765bde42deb39b8b4930c0b5237eb1df1df80fd551d4b', '[-98, 108, -19, -30, -19, -74, 76, 28, 125, -86, -19, -41, -78, -55, -27, -43]', 'ACTIVE', '', ''),
(72, 'STUDENT', 'testStudent69', 'testStudent69@gmail.com', '03949c68b0157c6bdec26be7c5b865bfdf7858712b491e1e69bfeb63145c5399fc0514c49fdefa90c61677fa9f6bda2a4f99047bff3f5cf36f6ccca1216c1899', '[28, -104, 16, -121, 91, -39, -34, -89, 2, -12, 123, 92, -58, 47, 108, 75]', 'ACTIVE', '', ''),
(73, 'TEACHER', 'testteacher420', 'testteacher420@gmail.com', '94c0fa216e3d5db6661a235d93588fced40d96646e1df86c37f51493e2f5b064cd99aeb8a5f5b0ca39931bff3901c96604224158e74471315bdb9d41c6e909da', '[20, 107, 39, -61, 36, 111, -50, -37, -128, -12, -97, 101, -53, -112, 16, 116]', 'ACTIVE', '', ''),
(74, 'STUDENT', 'denistanStudent', 'denis.e.mooney2001@gmail.com', '76c000a012a08c6e71f8b99d75dcf0a72ebba36aba93bbce8d9e3abdc1a55acca1ddf30d247ddf4317f57d020f014ef73b631019281a1ac241e58b50d0d9762c', '[-13, -30, 11, 102, 9, 55, -65, 28, 23, 38, 26, -52, 81, -68, 23, 57]', 'ACTIVE', 'H2WP3P2V4FKRT4LZ', 'XTRCYEHTCXXFHL67'),
(76, 'STUDENT', 'denisTanJStudent', 'denisTanJStudent@gmail.com', 'c5d4df550daf717231456d2e449500b337d5d8229a93f81faa660bf7e9072461dd3163f1a0dfccd9743d6c0d95f4fbd62c335e111c2d84652e15358e7de7cf8b', '[79, -43, -10, 117, 69, 122, -66, 115, 71, 90, 107, -117, 10, 19, 93, 56]', 'ACTIVE', NULL, NULL),
(77, 'TEACHER', 'Bellew2020', 'd00194494@student.dkit.ie', 'bf34924504ad323f7c200e0940a95e6d85018202421526a9fd0a58afefc45878ac957ee536492c63aa1c39ff858d23d221853af4507172dd6401e8987ba50479', '[-9, 49, 78, 65, 2, 109, 64, 98, -63, 83, -47, 46, -94, 34, -38, -90]', 'ACTIVE', NULL, NULL),
(78, 'STUDENT', 'testStudent2', 'testStudent2@gmail.com', '9b2efcc5f905603f4e1bc2a6c2022aa87233b4ebde17a1cea843140cf760977493d5dcde3bb171344dbfe9cd3c6dc84f4202fd7ac0981be18531e519d8ef3bae', '[-128, -63, -103, -46, -97, 54, -40, 75, 83, -27, 51, -123, 110, -19, 117, 94]', 'ACTIVE', NULL, NULL),
(79, 'TEACHER', 'Jimmy2020', 'jimmybelya@gmail.com', '6ed56511e5288eecc29cb90b8c7f5359b83ff9814341e6b584fd8b48621cca18173fabee16cb42584113787874346f4b85fb3f9ace0684c251ae71ea75aa42b3', '[-89, 37, -3, -76, -5, 77, 21, -118, -30, 55, 41, -85, -46, 19, -97, 48]', 'ACTIVE', NULL, NULL),
(80, 'TEACHER', 'newTeacher1', 'newTeacher1@gmail.com', '47d25713185a7e14e3fc87d211d5b830a608131ef6eaf72203fc0297bfbe1eb26025395e30b2ea4dd052a1ba37b15a7c41fe8d831f7c121d8fcff28fc6a15181', '[101, -114, -4, 86, -102, -60, -61, -49, -53, 53, -83, 114, 42, 106, 48, -28]', 'ACTIVE', 'XK3OFWL7WZZ4RIUZ', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `user_details_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `first_name` varchar(60) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `addressLine1` varchar(40) NOT NULL,
  `addressLine2` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`user_details_id`, `user_id`, `first_name`, `last_name`, `profile_picture`, `contact_number`, `date_of_birth`, `addressLine1`, `addressLine2`) VALUES
(50, 64, 'Denis', 'Mooney', 'WIN_20210318_09_17_50_Pro.jpg', '0831587369', '2021-03-10', '8 Carlinn Green', 'Mullaharlin Road'),
(51, 66, 'Jia Jun ', 'Tan', '2_Frustrated-student-on-line-in-classroom.jpg', '0831587369', '2006-01-22', 'College Manor', 'Hoey\'s Lane'),
(52, 67, 'Conor', 'McLoughlin', 'conorProfilePic.jpg', '0831587369', '1998-03-14', 'Knockbridge', 'Dundalk'),
(53, 68, 'Jack', 'O\'Leary', '', '0831587369', '2003-01-14', '8 Carlinn Green', 'Mullaharlin Road'),
(54, 69, 'Jack', 'Owens', 'jackriceTeacher.jpg', '324', '2002-03-18', 'ldsfa', 'ldsfa'),
(55, 70, 'Denis', 'Mooney', '', '0831587369', '2001-03-09', '8 Carlinn Green', 'Mullaharlin Road'),
(56, 72, 'Denis', 'Mooney', '', '0831587369', '2006-03-02', '8 Carlinn Green', 'Mullaharlin Road'),
(58, 71, 'Denis', 'Mooney', '', '0831587369', '1990-01-31', '8 Carlinn Green', 'Mullaharlin Road'),
(59, 73, 'Seamus', 'Teacher', 'teacher16.jpg', '0831587369', '2002-04-02', '8 Carlinn Green', 'Mullaharlin Road'),
(60, 74, 'Denis', 'Mooney', '', '0831587369', '2006-03-30', '8 Carlinn Green', 'Mullaharlin Road'),
(62, 78, 'Denis', 'Mooney', '', '0831587369', '2006-04-30', '8 Carlinn Green', 'Mullaharlin Road'),
(63, 79, 'James', 'Bellew', '', '0987868888', '1997-12-10', 'Dundalk', 'Da Town'),
(64, 80, 'Denis', 'Mooney', '', '0831587369', '2002-05-06', '8 Carlinn Green', 'Mullaharlin Road');

-- --------------------------------------------------------

--
-- Table structure for table `user_payment`
--

CREATE TABLE `user_payment` (
  `user_payment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_payment`
--

INSERT INTO `user_payment` (`user_payment_id`, `user_id`, `course_id`, `start_date`, `end_date`) VALUES
(17, 74, 86, '2021-03-01', '2021-04-14'),
(21, 74, 85, '2021-04-22', '2021-05-22'),
(24, 66, 106, '2021-05-06', '2021-06-06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_id`),
  ADD KEY `teacher_subject_constraint` (`teacher_subject_id`);

--
-- Indexes for table `course_links`
--
ALTER TABLE `course_links`
  ADD PRIMARY KEY (`course_link_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `course_messages`
--
ALTER TABLE `course_messages`
  ADD PRIMARY KEY (`course_message_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `course_students`
--
ALTER TABLE `course_students`
  ADD PRIMARY KEY (`course_students_id`),
  ADD KEY `course_id_con` (`course_id`),
  ADD KEY `student_id_constraint` (`student_id`);

--
-- Indexes for table `course_waiting_list`
--
ALTER TABLE `course_waiting_list`
  ADD PRIMARY KEY (`course_waiting_list_id`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `course_waiting_list_ibfk_2` (`student_id`);

--
-- Indexes for table `enquiries`
--
ALTER TABLE `enquiries`
  ADD PRIMARY KEY (`inquiry_id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `teacher_id` (`teacher_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`subject_id`);

--
-- Indexes for table `teacher_details`
--
ALTER TABLE `teacher_details`
  ADD PRIMARY KEY (`teacher_id`),
  ADD UNIQUE KEY `user_id_2` (`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `teacher_subjects`
--
ALTER TABLE `teacher_subjects`
  ADD PRIMARY KEY (`teacher_subject_id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `teacher_id` (`teacher_id`);

--
-- Indexes for table `timetable`
--
ALTER TABLE `timetable`
  ADD PRIMARY KEY (`timetable_id`),
  ADD KEY `course_id_constraint` (`course_id`);

--
-- Indexes for table `timetable_cancellations`
--
ALTER TABLE `timetable_cancellations`
  ADD PRIMARY KEY (`timetable_cancellations_id`),
  ADD KEY `timetable_id` (`timetable_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`user_details_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `user_payment`
--
ALTER TABLE `user_payment`
  ADD PRIMARY KEY (`user_payment_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `course_id` (`course_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT for table `course_links`
--
ALTER TABLE `course_links`
  MODIFY `course_link_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `course_messages`
--
ALTER TABLE `course_messages`
  MODIFY `course_message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `course_students`
--
ALTER TABLE `course_students`
  MODIFY `course_students_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `course_waiting_list`
--
ALTER TABLE `course_waiting_list`
  MODIFY `course_waiting_list_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `enquiries`
--
ALTER TABLE `enquiries`
  MODIFY `inquiry_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `subject_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `teacher_details`
--
ALTER TABLE `teacher_details`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `teacher_subjects`
--
ALTER TABLE `teacher_subjects`
  MODIFY `teacher_subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT for table `timetable`
--
ALTER TABLE `timetable`
  MODIFY `timetable_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=181;

--
-- AUTO_INCREMENT for table `timetable_cancellations`
--
ALTER TABLE `timetable_cancellations`
  MODIFY `timetable_cancellations_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `user_details`
--
ALTER TABLE `user_details`
  MODIFY `user_details_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `user_payment`
--
ALTER TABLE `user_payment`
  MODIFY `user_payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `teacher_subject_constraint` FOREIGN KEY (`teacher_subject_id`) REFERENCES `teacher_subjects` (`teacher_subject_id`);

--
-- Constraints for table `course_links`
--
ALTER TABLE `course_links`
  ADD CONSTRAINT `course_links_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`);

--
-- Constraints for table `course_messages`
--
ALTER TABLE `course_messages`
  ADD CONSTRAINT `course_messages_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`);

--
-- Constraints for table `course_students`
--
ALTER TABLE `course_students`
  ADD CONSTRAINT `course_id_con` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  ADD CONSTRAINT `student_id_constraint` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `course_waiting_list`
--
ALTER TABLE `course_waiting_list`
  ADD CONSTRAINT `course_waiting_list_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  ADD CONSTRAINT `course_waiting_list_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher_details` (`teacher_id`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `teacher_details`
--
ALTER TABLE `teacher_details`
  ADD CONSTRAINT `teacher_details_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `teacher_subjects`
--
ALTER TABLE `teacher_subjects`
  ADD CONSTRAINT `teacher_subjects_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`),
  ADD CONSTRAINT `teacher_subjects_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher_details` (`teacher_id`);

--
-- Constraints for table `timetable`
--
ALTER TABLE `timetable`
  ADD CONSTRAINT `timetable_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`);

--
-- Constraints for table `timetable_cancellations`
--
ALTER TABLE `timetable_cancellations`
  ADD CONSTRAINT `timetable_cancellations_ibfk_1` FOREIGN KEY (`timetable_id`) REFERENCES `timetable` (`timetable_id`);

--
-- Constraints for table `user_details`
--
ALTER TABLE `user_details`
  ADD CONSTRAINT `user_details_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `user_payment`
--
ALTER TABLE `user_payment`
  ADD CONSTRAINT `user_payment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `user_payment_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
