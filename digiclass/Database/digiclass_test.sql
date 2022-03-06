-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2021 at 02:04 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `digiclass_test`
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
(44, 43, 25, 'This Arabic for Higher Level Students specifically', '2021-04-05', '2021-06-14', 1),
(45, 44, 12, 'Biology', '2021-04-05', '2021-03-30', 1),
(46, 48, 20, 'Description', '2021-03-21', '2021-09-01', 0),
(50, 49, 19, 'Course Description', '2020-09-30', '2021-03-22', 0),
(60, 50, 20, 'Course Description', '2021-04-05', '2021-03-30', 1),
(66, 50, 20, 'Course Description', '2021-04-05', '2021-03-30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `course_links`
--

CREATE TABLE `course_links` (
  `course_link_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `link` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_links`
--

INSERT INTO `course_links` (`course_link_id`, `course_id`, `link`) VALUES
(19, 44, 'Link'),
(21, 44, 'Link'),
(23, 44, 'Link');

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
(4, 44, 'Welcome!!!', '2021-05-07'),
(6, 44, 'Welcome!!!', '2021-05-07'),
(8, 44, 'Welcome!!!', '2021-05-07');

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
(11, 44, 26),
(12, 44, 24),
(32, 44, 29);

-- --------------------------------------------------------

--
-- Table structure for table `course_waiting_list`
--

CREATE TABLE `course_waiting_list` (
  `course_waiting_list_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_waiting_list`
--

INSERT INTO `course_waiting_list` (`course_waiting_list_id`, `course_id`, `student_id`) VALUES
(1, 44, 29);

-- --------------------------------------------------------

--
-- Table structure for table `enquiries`
--

CREATE TABLE `enquiries` (
  `inquiry_id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  `date` varchar(10) NOT NULL,
  `seen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enquiries`
--

INSERT INTO `enquiries` (`inquiry_id`, `full_name`, `email`, `message`, `date`, `seen`) VALUES
(7, 'Denis Mooney', 'denis.e.mooney@gmail.com', 'Keep pushing forward!', '2020-03-03', 1),
(8, 'Jia Jun Tan', 'jiajuntan@gmail.com', 'Success is key!', '2020-02-03', 1),
(9, 'Conor McLoughlin', 'jiajuntan@gmail.com', 'Keep pushing forward!', '2020-02-03', 1),
(10, 'James Bellew', 'jamesbellew@gmail.com', 'Success is key!', '2020-04-03', 1),
(11, '', '', '', '2021-03-23', 0),
(12, '123', 'G', '123', '2021-03-23', 0),
(13, '', '', '', '2021-03-23', 0),
(14, '', '', '', '2021-03-23', 0),
(15, '123', 'G', '123', '2021-05-07', 0);

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

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `userId`, `description`, `seen`) VALUES
(4, 22, 're', 1),
(6, 22, 're', 0),
(9, 22, 'Results', 0),
(11, 22, 'Results', 0);

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `subject_id` int(2) NOT NULL,
  `subject_name` varchar(25) NOT NULL
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
(15, 'Design & Communication Gr'),
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
(32, 22, '', 'ABOUT', 1, 0),
(33, 25, 'test.png', 'about me', 1, 1),
(34, 27, '', 'dsfad', 1, 0),
(35, 28, '', 'dsfsdaf', 1, 0),
(36, 24, 'qualifications', 'Test', 0, 0);

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
(43, 32, 4, 'HL', 0),
(44, 32, 6, 'OL', 0),
(45, 33, 1, 'HL', 0),
(46, 33, 8, 'HL', 0),
(48, 35, 15, 'HL', 0),
(49, 35, 7, 'HL', 0),
(50, 32, 40, 'HL', 0);

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
(72, 44, 'Friday', 20),
(73, 44, 'Tuesday', 17),
(74, 45, 'Saturday', 15),
(75, 45, 'Saturday', 21),
(76, 45, 'Sunday', 20),
(77, 46, 'Monday', 20),
(78, 46, 'Monday', 20),
(79, 46, 'Tuesday', 22),
(80, 45, 'Friday', 15);

-- --------------------------------------------------------

--
-- Table structure for table `timetable_cancellations`
--

CREATE TABLE `timetable_cancellations` (
  `timetable_cancellations_id` int(11) NOT NULL,
  `timetable_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `timetable_cancellations`
--

INSERT INTO `timetable_cancellations` (`timetable_cancellations_id`, `timetable_id`, `date`) VALUES
(4, 77, '2021-07-19');

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
  `reset_key` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_type`, `username`, `email`, `password`, `salt`, `user_status`, `secret_key`, `reset_key`) VALUES
(22, 'TEACHER', 'denisTeacher', 'denis.e.mooney2001@gmail.com', 'd6ac947d4b0c2851dfc7decea3436e78ba2df7c24aa9febeb46a1a16e417ea75dda4aaacdf191b4737a60602e17ca3e40c6b1d75a9a602671e604cae117c5f2e', '[-21, 66, 95, 114, 103, 77, 56, -53, 40, -26, 38, 83, 9, 78, 118, -37]', 'ACTIVE', '1', '4'),
(23, 'ADMIN', 'denisAdmin', 'denisAdmin@gmail.com', '08ac2877b1efed675a1278c75fba1cdf352e269b8eb5048eea7b8d071d5bca37a5a024dc241643a9db7334e6817597196ab843217e16eb68b30efbadb69e7fdb', '[43, -94, 53, 83, 32, -80, 53, -67, -4, 94, 52, 111, 93, 26, -81, -5]', 'ACTIVE', NULL, NULL),
(24, 'STUDENT', 'tanStudent', 'tanStudent@gmail.com', '0c93290d4887952ec41b8a8f48c0e96720fb584fcba54658f0c7e73ad0b6491d0568ee47183f1ff160c2716055fc318f3d808132e5b87f31bb52f2d0bcdd6bd1', '[-9, 39, -34, -7, 56, 8, 71, 3, 10, 122, 2, -105, -126, -87, -115, -38]', 'ACTIVE', NULL, NULL),
(25, 'TEACHER', 'tanTeacher', 'tanTeacher@gmail.com', 'dd758a266091724329258d9e9711fa7fb548a9fd3d06cbf9d709c06ffe31448b8412b61d70b6f5c87a272419c4dd4d3c891e06b1cf50aeb6f36770236d79efbc', '[-102, -10, -34, -127, 84, -66, 5, -76, 15, -101, -84, 16, -124, 82, -49, 51]', 'ACTIVE', NULL, NULL),
(26, 'STUDENT', 'denisStudent', 'denisStudent@gmail.com', '0bd9913b92f0eee4059c021e30de55667833d327d9e2f6623646d2c6853a11d4ae6022bfddb5dd02fb27679d9ecf36f2351e1aa3fdd234f5404cf464f0c92afc', '[-65, 19, -123, 0, -82, -112, 84, -31, -51, 35, 119, 62, -83, 58, 6, 53]', 'ACTIVE', NULL, NULL),
(27, 'TEACHER', 'testTeacher1', 'testTeacher1@gmail.com', '0f2861e58402835cc32de2207dca6b5ba76338fa87e15417979629083843a04c78c25e186724ffb72de050d1c31c96f7a52d64c8181b65059fee5f80bcc6c589', '[62, -24, 116, -114, -39, 60, 41, 100, 72, 54, -15, 101, 101, -12, 65, 34]', 'ACTIVE', NULL, NULL),
(28, 'TEACHER', 'testTeacher2', 'testTeacher2@gmail.com', '9564ff0a3d5c9063861a44db84373d6d971f2c2ff1f98f71e39353f359a5c6bca950d3016071d32418a4d9b27570585b6e1fff9d92be3939d6b9495372420a30', '[68, -5, -76, 76, -89, 111, 43, -77, 99, 126, 17, -30, -103, 81, 74, -4]', 'ACTIVE', NULL, NULL),
(29, 'STUDENT', 'testStudent1', 'testStudent1@gmail.com', '7fcf2baf52156fead4407933d5bda0c0b552cd069231996b0762af002bfe204293c030249d901efb87e9c075d0ab1e7d0fdfbc573f0f3100843f676cbc8a534d', '[-93, -3, 57, -87, 76, -128, -23, -103, -99, -2, 80, -43, 106, -82, 17, 85]', 'ACTIVE', NULL, NULL),
(32, 'ACTIVE', 'Test123', 'test@gmail.com', 'fbb6b797a1678cb08a3d2426f3b85e8948fc391a3938738327d4283a9d17cd3df64cda673643f3ecc56e64271989c1046263c509693261ae2ae29fd477e07d8a', '[99, -127, -84, 117, -122, -109, 89, 100, -39, 114, 58, 37, 37, 125, 21, 106]', 'ACTIVE', NULL, NULL);

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
(9, 22, 'Denis', 'Mooney', 'test.png', '0831587369', '2021-03-04', '8 Carlinn Green', 'Mullaharlin Road'),
(10, 24, 'Jia Jun', 'Tan', NULL, '0831587369', '2001-03-09', 'College Manor', 'Hoeys Lane'),
(11, 25, 'Denis', 'Mooney', '', '0831587369', '2021-03-17', '8 Carlinn Green', 'Mullaharlin Road'),
(12, 26, 'Denis', 'Mooney', '', '0831587369', '2019-11-14', '8  Green', 'Mullaharlin Road'),
(15, 29, 'Denis', 'Mooney123', '', '0831587369', '2019-11-14', '8 Carlinn Green', 'Mullaharlin Road');

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
(28, 26, 4, NULL, NULL),
(30, 26, 44, NULL, NULL),
(31, 24, 44, '2021-05-07', '2021-06-07'),
(32, 26, 4, NULL, NULL),
(34, 26, 44, NULL, NULL),
(35, 24, 44, '2021-05-07', '2021-06-07'),
(36, 26, 4, NULL, NULL),
(38, 26, 44, NULL, NULL),
(39, 24, 44, '2021-05-07', '2021-06-07');

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
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT for table `course_links`
--
ALTER TABLE `course_links`
  MODIFY `course_link_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `course_messages`
--
ALTER TABLE `course_messages`
  MODIFY `course_message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `course_students`
--
ALTER TABLE `course_students`
  MODIFY `course_students_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `course_waiting_list`
--
ALTER TABLE `course_waiting_list`
  MODIFY `course_waiting_list_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `enquiries`
--
ALTER TABLE `enquiries`
  MODIFY `inquiry_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `subject_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `teacher_details`
--
ALTER TABLE `teacher_details`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `teacher_subjects`
--
ALTER TABLE `teacher_subjects`
  MODIFY `teacher_subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `timetable`
--
ALTER TABLE `timetable`
  MODIFY `timetable_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT for table `timetable_cancellations`
--
ALTER TABLE `timetable_cancellations`
  MODIFY `timetable_cancellations_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `user_details`
--
ALTER TABLE `user_details`
  MODIFY `user_details_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user_payment`
--
ALTER TABLE `user_payment`
  MODIFY `user_payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

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
  ADD CONSTRAINT `user_payment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
