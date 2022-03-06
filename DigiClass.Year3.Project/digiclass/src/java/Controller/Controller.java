/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Daos.*;
import Business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author junta
 */
@MultipartConfig
@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    public static UserDao userDao = new UserDao("digiclass");
    public static UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    public static TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    public static TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    public static CourseDao courseDao = new CourseDao("digiclass");
    public static CourseStudentsDao courseStudentsDao = new CourseStudentsDao("digiclass");
    public static JoinDao joinDao = new JoinDao("digiclass");
    public static TimetableDao timetableDao = new TimetableDao("digiclass");
    public static CourseWaitingListDao courseWaitingListDao = new CourseWaitingListDao("digiclass");
    public static CourseLinkDao courseLinkDao = new CourseLinkDao("digiclass");
    public static EnquiriesDao enquiriesDao = new EnquiriesDao("digiclass");
    public static TimetableCancellationsDao timetableCancellations = new TimetableCancellationsDao("digiclass");
    public static UserPaymentDao userPaymentDao = new UserPaymentDao("digiclass");
    public static NotificationDao notificationDao = new NotificationDao("digiclass");
    public static String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}";
    public static ReviewsDao reviewDao = new ReviewsDao("digiclass");
    public static CourseMessageDao courseMessageDao = new CourseMessageDao("digiclass");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check what the user wants to do
        String action = request.getParameter("action");
        // Create a variable to track where we are going to send the user
        String forwardToJsp = "index.jsp";

        // Get access to the user's session - it's not automatically available here
        // so we need to get it from the request variable
        HttpSession session = request.getSession(true);

        // If we got here through a valid interaction, an action value should exist
        if (action != null) {
            switch (action) {
                //User Function
                case "login":
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    if ((username != null || password != null) && (!username.equals("") && !password.equals(""))) {
                        try {
                            if (userDao.userLogin(username, password) == true) {
                                User loggedUser = userDao.getUserByUsername(username);
                                if (!"DISABLED".equals(loggedUser.getUser_status())) {
                                    if (!(loggedUser.getSecret_key() == null || loggedUser.getSecret_key().equals(""))) {
                                        session.setAttribute("userId", loggedUser.getID());
                                        session.setAttribute("username", username);
                                        session.setAttribute("login", false);
                                        forwardToJsp = "loginOTP.jsp";
                                    } else {
                                        session.setAttribute("userId", loggedUser.getID());
                                        session.setAttribute("username", username);
                                        session.setAttribute("login", true);
                                        forwardToJsp = "home.jsp";
                                    }
                                } else {
                                    forwardToJsp = "error.jsp";
                                    String error = "You have beend DISABLED by the Admin, Please Contact our service team for more information.";
                                    session.setAttribute("errorMessage", error);
                                }
                            } else {
                                forwardToJsp = "error.jsp";
                                String error = " Invalid information - wrong username and / or password";
                                session.setAttribute("errorMessage", error);
                            }
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (username == null || password == null) {
                        forwardToJsp = "error.jsp";
                        String error = " No username or password information contained in request";
                        session.setAttribute("errorMessage", error);
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = "Blank username and password supplied";
                        session.setAttribute("errorMessage", error);
                    }
                    break;

                case "registerBasicUser":
                    String uName = request.getParameter("username");
                    String pWord = request.getParameter("password");
                    String confirmPword = request.getParameter("confirmPassword");
                    String uType = request.getParameter("userType");
                    String email = request.getParameter("email");
                    String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
                    if (userDao.usernameAlreadyExists(uName) == false) {
                        if (userDao.emailAlreadyExists(email) == false) {
                            if (email.matches(emailRegex)) {
                                if (pWord.matches(passwordPattern) && pWord.matches(confirmPword)) {
                                    try {
                                        userDao.userSignup(uName, email, pWord, uType);
                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    forwardToJsp = "error.jsp";
                                    String error = "Passwords do not match/ Password does not match Pattern";
                                    session.setAttribute("errorMessage", error);
                                }
                            } else {
                                forwardToJsp = "error.jsp";
                                String error = "Email Does Not Match Regex!";
                                session.setAttribute("errorMessage", error);
                            }

                        } else {
                            forwardToJsp = "error.jsp";
                            String error = "Email already Exists";
                            session.setAttribute("errorMessage", error);
                        }
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = "Username already Exists";
                        session.setAttribute("errorMessage", error);
                    }
                    break;

                case "forgetPasswordLink":
                    String eM = request.getParameter("email");
                    String hash = request.getParameter("token");
                    if (userDao.getUserByEmail(eM) != null && userDao.checkEmailAndHashCode(eM, hash) == true) {
                        forwardToJsp = "resetPassword.jsp";
                        session.setAttribute("email", eM);
                        User user = userDao.getUserByEmail(eM);
                        userDao.updateResetKey(user.getID(), null);
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = "Error";
                        session.setAttribute("errorMessage", error);
                    }
                    break;

                case "forgetPassword":
                    String em = (String) session.getAttribute("email");
                    String newPass = request.getParameter("newPassword");
                    String reNewPas = request.getParameter("reNewPass");
                    if (newPass.matches(passwordPattern) && reNewPas.matches(passwordPattern)) {
                        if (reNewPas.equals(newPass)) {
                            try {
                                userDao.resetPasswordByEmail(em, newPass);
                                session.removeAttribute("email");
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            forwardToJsp = "error.jsp";
                            String error = "Password not matches";
                            session.setAttribute("errorMessage", error);
                        }
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = "Password does not match Pattern";
                        session.setAttribute("errorMessage", error);
                    }
                    break;

                case "resetPassword":
                    String currentPass = "";
                    Gson gSon = new GsonBuilder().disableHtmlEscaping().create();
                    String uname = (String) session.getAttribute("username");
                    String current = request.getParameter("current");
                    String newPassword = request.getParameter("newPassword");
                    String reNewPass = request.getParameter("reNewPass");
                    HashPass hp = new HashPass();
                    String pass = userDao.userPassword(uname);
                    String saltReturn = userDao.userSalt(uname);
                    byte[] salt = hp.StringtoByte(saltReturn);
                    try {
                        currentPass = hp.SecurePass512(current, salt);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (newPassword.matches(passwordPattern)) {
                        if (pass.equals(currentPass)) {
                            if (newPassword.equals(reNewPass)) {
                                try {
                                    userDao.resetPassword((int) session.getAttribute("userId"), newPassword);
                                } catch (NoSuchAlgorithmException ex) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                String error = "Password not matches";
                                session.setAttribute("result", gSon.toJson(error));
                                forwardToJsp = "searchEmailResult.jsp";
                            }
                        } else {
                            String error = "Password Incorrect";
                            session.setAttribute("result", gSon.toJson(error));
                            forwardToJsp = "searchEmailResult.jsp";

                        }
                    } else {
                        String error = "Password does not match Pattern";
                        session.setAttribute("result", gSon.toJson(error));
                        forwardToJsp = "searchEmailResult.jsp";
                    }
                    break;

                case "resetCheckEmail":
                    String eml = request.getParameter("email");
                    ArrayList<User> uL = userDao.getAllUsers();
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    String errorMsg = "Email Not Found";
                    boolean check = false;
                    if ((eml != null) && (!eml.equals(""))) {
                        for (User u : uL) {
                            if (u.getEmail().equals(eml)) {
                                check = true;
                                String reset_key = TwoFactorAuthenticator.generateBase32Secret();
                                User us = userDao.getUserByEmail(eml);
                                userDao.updateResetKey(us.getID(), reset_key);
                                try {
                                    SendEmail.sendEmail(eml, userDao.getUserKeyByEmail(eml));
                                } catch (MessagingException ex) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if (check == true) {
                            session.setAttribute("result", gson.toJson(eml));
                            session.setAttribute("email", eml);
                            forwardToJsp = "searchEmailResult.jsp";
                        } else {
                            session.setAttribute("result", gson.toJson(errorMsg));
                            forwardToJsp = "searchEmailResult.jsp";
                        }
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = "Input field is empty";
                        session.setAttribute("errorMessage", error);
                    }
                    break;

                case "applyTwoAuth":
                    String secretKey = TwoFactorAuthenticator.generateBase32Secret();
                    userDao.updateSecretKey((int) session.getAttribute("userId"), secretKey);
                    forwardToJsp = "profile.jsp";
                    break;

                case "2-auth":
                    User user = userDao.getUserByUsername((String) session.getAttribute("username"));
                    String key = user.getSecret_key();
                    String value = request.getParameter("OTP");
                    try {
                        key = TwoFactorAuthenticator.generateCurrentNumberString(user.getSecret_key());
                    } catch (GeneralSecurityException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (value.equals(key)) {
                        forwardToJsp = "home.jsp";
                        session.setAttribute("login", true);
                    } else {
                        forwardToJsp = "error.jsp";
                        session.setAttribute("errorMessage", key);
                    }
                    break;

                case "insertProfileDetails":
                    int uId = (int) session.getAttribute("userId");
                    String first_name = request.getParameter("firstName");
                    String last_name = request.getParameter("lastName");
                    String profile_picture = "";
                    String contact_number = request.getParameter("number");
                    String date_of_birthTeacher = request.getParameter("dateOfBirthTeacher");
                    String date_of_birthStudent = request.getParameter("dateOfBirthStudent");
                    String userType = request.getParameter("userType");
                    String newDoB = "";
                    if (date_of_birthTeacher.equals("")) {
                        newDoB = date_of_birthStudent;
                    } else if (date_of_birthStudent.equals("")) {
                        newDoB = date_of_birthTeacher;
                    }
                    String addressLine1 = request.getParameter("addressLine1");
                    String addressLine2 = request.getParameter("addressLine2");
                    userDetailsDao.insertUserDetails(uId, first_name, last_name, profile_picture, contact_number, newDoB, addressLine1, addressLine2);
                    forwardToJsp = "profile.jsp";
                    break;

                case "updateUserDetails":
                    int update_uId = (int) session.getAttribute("userId");
                    String update_first_name = request.getParameter("firstName");
                    String update_last_name = request.getParameter("lastName");
                    String update_contact_number = request.getParameter("number");
                    String dobTeacher = request.getParameter("dateOfBirthTeacher");
                    String dobStudent = request.getParameter("dateOfBirthStudent");
                    String update_addressLine1 = request.getParameter("addressLine1");
                    String update_addressLine2 = request.getParameter("addressLine2");
                    String userType1 = userDao.getUserById(update_uId).getUser_type();
                    String finalDob = "";

                    if (userType1.equals("TEACHER")) {
                        finalDob = dobTeacher;
                    } else if (userType1.equals("STUDENT")) {
                        finalDob = dobStudent;
                    }
                    userDetailsDao.updateUserDetails(update_uId, update_first_name, update_last_name, update_contact_number, finalDob, update_addressLine1, update_addressLine2);
                    forwardToJsp = "profile.jsp";
                    break;

                case "logOut":
                    session.removeAttribute("userId");
                    session.removeAttribute("username");
                    session.removeAttribute("login");
                    forwardToJsp = "index.jsp";
                    break;

                //Teacher Function
                case "registerTeacherDetails":
                    int user_id = (int) session.getAttribute("userId");
                    String qualifications = "";
                    String aboutMe = request.getParameter("aboutMe");
                    int subjectId1 = Integer.parseInt(request.getParameter("subject1"));
                    int subjectId2 = Integer.parseInt(request.getParameter("subject2"));
                    int subjectId3 = Integer.parseInt(request.getParameter("subject3"));
                    String subLevel1 = request.getParameter("level");
                    String subLevel2 = request.getParameter("level2");
                    String subLevel3 = request.getParameter("level3");
                    int teacherId = teacherDetailsDao.insertTeacherDetails(user_id, qualifications, aboutMe);
                    teacherSubjectsDao.insertTeacherSubjects(teacherId, subjectId1, subLevel1);
                    if (subjectId2 >= 0) {
                        if (subjectId1 != subjectId2 && !subLevel1.equals(subLevel2) || subjectId3 != subjectId2 && !subLevel3.equals(subLevel2)) {
                            teacherSubjectsDao.insertTeacherSubjects(teacherId, subjectId2, subLevel2);
                        } else {
                            forwardToJsp = "error.jsp";
                            String error = "Duplicate Record Inserted";
                            session.setAttribute("errorMessage", error);
                        }
                    }
                    if (subjectId3 >= 0) {
                        if (subjectId1 != subjectId3 && !subLevel1.equals(subLevel3) || subjectId3 != subjectId2 && !subLevel3.equals(subLevel2)) {
                            teacherSubjectsDao.insertTeacherSubjects(teacherId, subjectId3, subLevel3);
                        } else {
                            forwardToJsp = "error.jsp";
                            String error = "Duplicate Record Inserted";
                            session.setAttribute("errorMessage", error);
                        }
                    }
                    forwardToJsp = "profile.jsp";
                    break;

                case "updateTeacherDetails":
                    int userId = (int) session.getAttribute("userId");
                    String updateAboutMe = request.getParameter("aboutMe");
                    teacherDetailsDao.updateTeacherDetails(userId, updateAboutMe);
                    forwardToJsp = "profile.jsp";
                    break;

                case "updateTeacherSubjects":
                    int teacherSubjectId = Integer.parseInt(request.getParameter("tsId"));
                    int updateSubjectId = Integer.parseInt(request.getParameter("subject"));
                    String subjectLevel = request.getParameter("level");
                    int teacher_details_id = teacherDetailsDao.getTeacherDetailsByUserId((int) session.getAttribute("userId")).getTeacher_id();
                    if (!(teacherSubjectsDao.checkIfTeacherSubjectAlreadyExists(teacher_details_id, updateSubjectId, subjectLevel))) {
                        teacherSubjectsDao.setTeacherSubjectToPast(teacherSubjectId);
                        teacherSubjectsDao.insertTeacherSubjects(teacher_details_id, updateSubjectId, subjectLevel);
                    }
                    forwardToJsp = "updateTeacherSubjects.jsp";
                    break;
                case "signupCourse":

                    int studentId = (int) session.getAttribute("userId");
                    int coId = Integer.parseInt(request.getParameter("courseId"));
                    int count = courseStudentsDao.getCourseCount(coId);
                    Course c = courseDao.getCourseByID(coId);
                    boolean paid = Boolean.parseBoolean(request.getParameter("paid"));
                    UserDetails ud = userDetailsDao.getUserDetailsByUserId((int) session.getAttribute("userId"));
                    ArrayList<Integer> studentList = courseStudentsDao.getStudentIdsInCourse(coId);
                    boolean checkStudent = false;
                    if (ud != null) {
                        for (int i = 0; i < studentList.size(); i++) {
                            if (studentList.get(i) == studentId) {
                                checkStudent = true;
                            }
                        }
                        if (checkStudent == false) {
                            if (count < c.getPlaces()) {

                                forwardToJsp = "home.jsp";
                                if (paid == true) {
                                    courseStudentsDao.insertCourseStudent(coId, studentId);
                                    forwardToJsp = "paymentPage.jsp";
                                    session.setAttribute("courseIdPayCourse", coId);
                                } else if (paid == false) {
                                    courseStudentsDao.insertCourseStudent(coId, studentId);
                                    userPaymentDao.insertCoursePaymentLater(studentId, coId);
                                    forwardToJsp = "home.jsp";
                                }
                            } else {
                                courseWaitingListDao.insertStudentInWaitingList(coId, studentId);
                                forwardToJsp = "home.jsp";
                            }
                        } else {
                            forwardToJsp = "error.jsp";
                            String error = "Already Enroll in the course.";
                            session.setAttribute("errorMessage", error);
                        }
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = " Please Enter your Details first.";
                        session.setAttribute("errorMessage", error);
                    }
                    break;

                case "signUpCourseWaitingList":

                    int studentIdWaitingList = (int) session.getAttribute("userId");
                    int coIdWaitingList = Integer.parseInt(request.getParameter("courseId"));
                    courseWaitingListDao.insertStudentInWaitingList(coIdWaitingList, studentIdWaitingList);
                    forwardToJsp = "home.jsp";
                    break;

                case "goToPayCourseFee":
                    int courseIdPayCourse = Integer.parseInt(request.getParameter("courseId"));
                    session.setAttribute("courseIdPayCourse", courseIdPayCourse);
                    forwardToJsp = "paymentPage.jsp";
                    break;

                case "payOverdueFee":
                    int userIdInsertPayment = Integer.parseInt(request.getParameter("userId"));
                    int courseIdInsertPayment = Integer.parseInt(request.getParameter("courseId"));
                    if (!(userPaymentDao.userCoursePaymentExists(userIdInsertPayment, courseIdInsertPayment))) {
                        userPaymentDao.insertPaymentDetails(userIdInsertPayment, courseIdInsertPayment);
                    } else {
                        userPaymentDao.updateCoursePayment(userIdInsertPayment, courseIdInsertPayment);
                    }
                    forwardToJsp = "home.jsp";
                    break;

                case "startCourse":
                    int teacher_subId = Integer.parseInt(request.getParameter("teacherSubjectId"));
                    String desc = request.getParameter("description");
                    String startDate = request.getParameter("startDate");
                    String endDate = request.getParameter("endDate");
                    int places = Integer.parseInt(request.getParameter("places"));
                    TeacherDetails td = teacherDetailsDao.getTeacherDetailsByUserId((int) session.getAttribute("userId"));
                    if (td.isVerify() == true) {
                        session.setAttribute("courseDesc", desc);
                        session.setAttribute("courseStartDate", startDate);
                        session.setAttribute("courseEndDate", endDate);
                        session.setAttribute("coursePlaces", places);
                        session.setAttribute("courseTeacherSubId", teacher_subId);
                        forwardToJsp = "insertTimetable.jsp";
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = " You have to wait for Admin to verify";
                        session.setAttribute("errorMessage", error);
                    }
                    break;

                case "insertTimetable":
                    TeacherDetails teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId((int) session.getAttribute("userId"));
                    int teacher_id = teacherDetails.getTeacher_id();
                    ArrayList<Timetable> ongoingTimetablesForTeacher = joinDao.getOngoingTeacherTimeTables(teacher_id);
                    boolean day1AlreadyExists = false;
                    boolean day2AlreadyExists = false;
                    boolean day3AlreadyExists = false;
                    boolean day4AlreadyExists = false;
                    boolean day5AlreadyExists = false;
                    boolean day6AlreadyExists = false;
                    String day1 = request.getParameter("teachingDay1");
                    String day2 = request.getParameter("teachingDay2");
                    String day3 = request.getParameter("teachingDay3");
                    String day4 = request.getParameter("teachingDay4");
                    String day5 = request.getParameter("teachingDay5");
                    String day6 = request.getParameter("teachingDay6");
                    int teacher_subIdInsert = (int) session.getAttribute("courseTeacherSubId");
                    String descInsert = (String) session.getAttribute("courseDesc");
                    String startDateInsert = (String) session.getAttribute("courseStartDate");
                    String endDateInsert = (String) session.getAttribute("courseEndDate");
                    int placesInsert = (int) session.getAttribute("coursePlaces");
                    int startTime1 = Integer.parseInt(request.getParameter("startTime1"));
                    int startTime2 = Integer.parseInt(request.getParameter("startTime2"));
                    String startTime3 = request.getParameter("startTime3");
                    int startTime_3 = 0;
                    if (!("".equals(startTime3) || startTime3 == null)) {
                        startTime_3 = Integer.parseInt(startTime3);
                    }
                    String startTime4 = request.getParameter("startTime4");
                    int startTime_4 = 0;
                    if (!("".equals(startTime4) || startTime4 == null)) {
                        startTime_4 = Integer.parseInt(startTime4);
                    }
                    String startTime5 = request.getParameter("startTime5");
                    int startTime_5 = 0;
                    if (!("".equals(startTime5) || startTime5 == null)) {
                        startTime_5 = Integer.parseInt(startTime5);
                    }
                    String startTime6 = request.getParameter("startTime6");
                    int startTime_6 = 0;
                    if (!("".equals(startTime6) || startTime6 == null)) {
                        startTime_6 = Integer.parseInt(startTime6);
                    }
                    if (!(day1.isEmpty() && startTime1 < 9 && startTime1 > 22)) {
                        if (ongoingTimetablesForTeacher.size() > 0) {
                            for (Timetable t : ongoingTimetablesForTeacher) {
                                if (t.getDay().equals(day1) && t.getTime() == startTime1) {
                                    day1AlreadyExists = true;
                                    break;
                                }
                            }
                        }
                        if (!day1AlreadyExists) {
                            ongoingTimetablesForTeacher.add(new Timetable(1, 0, day1, startTime1));
                        }
                    }
                    if (!(day2.isEmpty() && startTime2 < 9 && startTime2 > 22)) {
                        if (ongoingTimetablesForTeacher.size() > 0) {
                            for (Timetable t : ongoingTimetablesForTeacher) {
                                if (t.getDay().equals(day2) && t.getTime() == startTime2) {
                                    day2AlreadyExists = true;
                                    break;
                                }
                            }
                        }
                        if (!day2AlreadyExists) {
                            ongoingTimetablesForTeacher.add(new Timetable(2, 0, day2, startTime2));
                        }
                    }
                    if (!(day3.isEmpty() && startTime3 == null)) {
                        if (startTime_3 > 8 && startTime_3 < 23) {
                            if (ongoingTimetablesForTeacher.size() > 0) {
                                for (Timetable t : ongoingTimetablesForTeacher) {
                                    if (t.getDay().equals(day3) && t.getTime() == startTime_3) {
                                        day3AlreadyExists = true;
                                        break;
                                    }
                                }

                            }
                        }
                        if (!day3AlreadyExists) {
                            ongoingTimetablesForTeacher.add(new Timetable(3, 0, day3, startTime_3));
                        }
                    }
                    if (!(day4.isEmpty() && startTime4 == null)) {
                        if (startTime_4 > 8 && startTime_4 < 23) {
                            if (ongoingTimetablesForTeacher.size() > 0) {
                                for (Timetable t : ongoingTimetablesForTeacher) {
                                    if (t.getDay().equals(day4) && t.getTime() == startTime_4) {
                                        day4AlreadyExists = true;
                                        break;
                                    }
                                }
                            }

                        }
                        if (!day4AlreadyExists) {
                            ongoingTimetablesForTeacher.add(new Timetable(4, 0, day4, startTime_4));
                        }
                    }
                    if (!(day5.isEmpty() && startTime5 == null)) {
                        if (startTime_5 > 8 && startTime_5 < 23) {
                            if (ongoingTimetablesForTeacher.size() > 0) {
                                for (Timetable t : ongoingTimetablesForTeacher) {
                                    if (t.getDay().equals(day5) && t.getTime() == startTime_5) {
                                        day5AlreadyExists = true;
                                        break;
                                    }
                                }

                            }
                        }
                        if (!day5AlreadyExists) {
                            ongoingTimetablesForTeacher.add(new Timetable(5, 0, day5, startTime_5));
                        }
                    }
                    if (!(day6.isEmpty() && startTime6 == null)) {
                        if (startTime_6 > 8 && startTime_6 < 23) {
                            if (ongoingTimetablesForTeacher.size() > 0) {
                                for (Timetable t : ongoingTimetablesForTeacher) {
                                    if (t.getDay().equals(day6) && t.getTime() == startTime_6) {
                                        day6AlreadyExists = true;
                                    } else {
                                        forwardToJsp = "error.jsp";
                                        String error = "You have duplicated day and time inserted.";
                                        session.setAttribute("errorMessage", error);
                                    }
                                }

                            }
                        }
                        if (!day6AlreadyExists) {
                            ongoingTimetablesForTeacher.add(new Timetable(6, 0, day6, startTime_6));
                        }
                    }
                    if (day1.equals(day2) && startTime1 == startTime2 || day1.equals(day3) && startTime1 == startTime_3 || day1.equals(day4) && startTime1 == startTime_4 || day1.equals(day5) && startTime1 == startTime_5 || day1.equals(day6) && startTime1 == startTime_6) {
                        forwardToJsp = "insertTimetable.jsp";
                        String error = "Duplicates were found! PLEASE TRY AGAIN!";
                        session.setAttribute("courseErrorMessage", error);
                    } else if (day2.equals(day3) && startTime2 == startTime_3 || day2.equals(day4) && startTime2 == startTime_4 || day2.equals(day5) && startTime2 == startTime_5 || day2.equals(day6) && startTime2 == startTime_6) {
                        forwardToJsp = "insertTimetable.jsp";
                        String error = "Duplicates were found! PLEASE TRY AGAIN!";
                        session.setAttribute("courseErrorMessage", error);
                    } else if (!day3.equals("Select Day") && (day3.equals(day4) && startTime_3 == startTime_4 || day3.equals(day5) && startTime_3 == startTime_5 || day3.equals(day6) && startTime_3 == startTime_6)) {
                        forwardToJsp = "insertTimetable.jsp";
                        String error = "Duplicates were found! PLEASE TRY AGAIN!";
                        session.setAttribute("courseErrorMessage", error);
                    } else if (!day4.equals("Select Day") && (day4.equals(day5) && startTime_4 == startTime_5 || day4.equals(day6) && startTime_4 == startTime_6)) {
                        forwardToJsp = "insertTimetable.jsp";
                        String error = "Duplicates were found! PLEASE TRY AGAIN!";
                        session.setAttribute("courseErrorMessage", error);
                    } else if (!day5.equals("Select Day") && (day5.equals(day6) && startTime_5 == startTime_6)) {
                        forwardToJsp = "insertTimetable.jsp";
                        String error = "Duplicates were found! PLEASE TRY AGAIN!";
                        session.setAttribute("courseErrorMessage", error);
                    } else if (day1AlreadyExists || day2AlreadyExists || day3AlreadyExists || day4AlreadyExists || day5AlreadyExists || day6AlreadyExists) {
                        forwardToJsp = "insertTimetable.jsp";
                        String error = "Hours Entered Conflict with your Teaching Timetable! PLEASE TRY AGAIN!";
                        session.setAttribute("courseErrorMessage", error);
                    } else {
                        int courseId = courseDao.insertCourse(teacher_subIdInsert, placesInsert, descInsert, startDateInsert, endDateInsert);
                        if (!(day1.isEmpty() && startTime1 < 9 && startTime1 > 22)) {
                            timetableDao.insertTimetable(courseId, day1, startTime1);
                        }
                        if (!(day2.isEmpty() && startTime2 < 9 && startTime2 > 22)) {
                            timetableDao.insertTimetable(courseId, day2, startTime2);
                        }
                        if (!(day3.isEmpty() && startTime3 == null)) {
                            if (startTime_3 > 8 && startTime_3 < 23) {
                                timetableDao.insertTimetable(courseId, day3, startTime_3);
                            }
                        }
                        if (!(day4.isEmpty() && startTime4 == null)) {
                            if (startTime_4 > 8 && startTime_4 < 23) {
                                timetableDao.insertTimetable(courseId, day4, startTime_4);
                            }
                        }
                        if (!(day5.isEmpty() && startTime5 == null)) {
                            if (startTime_5 > 8 && startTime_5 < 23) {
                                timetableDao.insertTimetable(courseId, day5, startTime_5);
                            }
                        }
                        if (!(day6.isEmpty() && startTime6 == null)) {
                            if (startTime_6 > 8 && startTime_6 < 23) {
                                timetableDao.insertTimetable(courseId, day6, startTime_6);
                            }
                        }
                        forwardToJsp = "home.jsp";
                    }
                    break;

                case "updateTimetable":
                    String updateDay1 = request.getParameter("teachingDay1");
                    int time1 = Integer.parseInt(request.getParameter("startTime1"));
                    int timetable_id1 = Integer.parseInt(request.getParameter("timetable_id1"));
                    String updateDay2 = request.getParameter("teachingDay2");
                    int time2 = Integer.parseInt(request.getParameter("startTime2"));
                    String timetableid_2 = request.getParameter("timetable_id2");
                    int timetable_id2 = 0;
                    if (!("".equals(timetableid_2) || timetableid_2 == null)) {
                        timetable_id2 = Integer.parseInt(timetableid_2);
                    }
                    String updateDay3 = request.getParameter("teachingDay3");
                    String time3 = request.getParameter("startTime3");
                    int time_3 = 0;
                    if (!("".equals(time3) || time3 == null)) {
                        startTime_3 = Integer.parseInt(time3);
                    }
                    String timetableid_3 = request.getParameter("timetable_id3");
                    int timetable_id3 = 0;
                    if (!("".equals(timetableid_3) || timetableid_3 == null)) {
                        timetable_id3 = Integer.parseInt(timetableid_3);
                    }
                    String updateDay4 = request.getParameter("teachingDay4");
                    String time4 = request.getParameter("startTime4");
                    int time_4 = 0;
                    if (!("".equals(time4) || time4 == null)) {
                        time_4 = Integer.parseInt(time4);
                    }
                    String timetableId_4 = request.getParameter("timetable_id4");
                    int timetable_id4 = 0;
                    if (!("".equals(timetableId_4) || timetableId_4 == null)) {
                        timetable_id4 = Integer.parseInt(timetableId_4);
                    }
                    String updateDay5 = request.getParameter("teachingDay5");
                    String time5 = request.getParameter("startTime5");
                    int time_5 = 0;
                    if (!("".equals(time5) || time5 == null)) {
                        time_5 = Integer.parseInt(time5);
                    }
                    String timetableId_5 = request.getParameter("timetable_id5");
                    int timetable_id5 = 0;
                    if (!("".equals(timetableId_5) || timetableId_5 == null)) {
                        timetable_id5 = Integer.parseInt(timetableId_5);
                    }
                    String updateDay6 = request.getParameter("teachingDay6");
                    String time6 = request.getParameter("startTime6");
                    int time_6 = 0;
                    if (!("".equals(time6) || time6 == null)) {
                        time_6 = Integer.parseInt(time6);
                    }
                    String timetableId_6 = request.getParameter("timetable_id6");
                    int timetable_id6 = 0;
                    if (!("".equals(timetableId_6) || timetableId_6 == null)) {
                        timetable_id6 = Integer.parseInt(timetableId_6);
                    }
                    TeacherDetails teacherDetailsUsedForUpdatingTimetable = teacherDetailsDao.getTeacherDetailsByUserId((int) session.getAttribute("userId"));
                    int teacher_id1 = teacherDetailsUsedForUpdatingTimetable.getTeacher_id();
                    ArrayList<Timetable> ongoingTimetablesForTeacher1 = joinDao.getOngoingTeacherTimeTables(teacher_id1);
                    boolean day1Existing = false;
                    boolean day2Existing = false;
                    boolean day3Existing = false;
                    boolean day4Existing = false;
                    boolean day5Existing = false;
                    boolean day6Existing = false;
                    if (!(updateDay1.isEmpty() && time1 < 9 && time1 > 23)) {
                        for (Timetable t : ongoingTimetablesForTeacher1) {
                            if (t.getDay().equals(updateDay1) && t.getTime() == time1) {
                                day1Existing = true;
                            }
                        }
                        if (day1Existing = false) {
                            timetableDao.updateTimetableByTimetableid(timetable_id1, updateDay1, time1);
                        }
                    }
                    if (!(updateDay2.isEmpty() && time2 < 9 && time2 > 23)) {
                        for (Timetable t : ongoingTimetablesForTeacher1) {
                            if (t.getDay().equals(updateDay2) && t.getTime() == time2) {
                                day2Existing = true;
                            }
                        }
                        if (day2Existing = false) {
                            timetableDao.updateTimetableByTimetableid(timetable_id2, updateDay2, time2);
                        }
                    }
                    if (!(updateDay3.isEmpty() && time3 == null)) {
                        for (Timetable t : ongoingTimetablesForTeacher1) {
                            if (t.getDay().equals(updateDay3) && t.getTime() == time_3) {
                                day3AlreadyExists = true;
                            }
                        }
                        if (day3AlreadyExists = false) {
                            timetableDao.updateTimetableByTimetableid(timetable_id3, updateDay3, time_3);
                        }
                    }
                    if (!(updateDay4.isEmpty() && time4 == null)) {
                        for (Timetable t : ongoingTimetablesForTeacher1) {
                            if (t.getDay().equals(updateDay4) && t.getTime() == time_4) {
                                day4AlreadyExists = true;
                            }
                        }
                        if (day4AlreadyExists = false) {
                            timetableDao.updateTimetableByTimetableid(timetable_id4, updateDay4, time_4);
                        }
                    }
                    if (!(updateDay5.isEmpty() && time5 == null)) {
                        for (Timetable t : ongoingTimetablesForTeacher1) {
                            if (t.getDay().equals(updateDay5) && t.getTime() == time_5) {
                                day5AlreadyExists = true;
                            }
                        }
                        if (day5AlreadyExists = false) {
                            timetableDao.updateTimetableByTimetableid(timetable_id5, updateDay5, time_5);
                        }
                    }
                    if (!(updateDay6.isEmpty() && time6 == null)) {
                        for (Timetable t : ongoingTimetablesForTeacher1) {
                            if (t.getDay().equals(updateDay6) && t.getTime() == time_6) {
                                day6AlreadyExists = true;
                            }
                        }
                        if (day6AlreadyExists = false) {
                            timetableDao.updateTimetableByTimetableid(timetable_id6, updateDay6, time_6);
                        }
                    }
                    break;

                case "sendInquiry":
                    String nameInquiry = request.getParameter("name");
                    String emailInquiry = request.getParameter("email");
                    String messageInquiry = request.getParameter("message");
                    enquiriesDao.InsertInquiry(nameInquiry, emailInquiry, messageInquiry);
                    forwardToJsp = "index.jsp";
                    break;

                case "uploadImage":
                    String image = request.getParameter("image_name");
                    String[] splitArray = image.split(Pattern.quote("\\"));
                    String fileName = splitArray[splitArray.length - 1];
                    userDetailsDao.updateImage((int) session.getAttribute("userId"), fileName);
                    forwardToJsp = "profile.jsp";
                    break;

                case "uploadQualification":
                    String qualification = request.getParameter("image_name");
                    String[] splitArray1 = qualification.split(Pattern.quote("\\"));
                    String qualName = splitArray1[splitArray1.length - 1];
                    int teachId = teacherDetailsDao.getTeacherDetailsByUserId((int) session.getAttribute("userId")).getTeacher_id();
                    teacherDetailsDao.uploadQualification(teachId, qualName);
                    forwardToJsp = "profile.jsp";
                    break;
                case "verify":
                    int uID = Integer.parseInt(request.getParameter("userId"));
                    teacherDetailsDao.verifyTeacher(uID);
                    forwardToJsp = "toVerifyList.jsp";
                    break;
                case "nonVerify":
                    forwardToJsp = "toVerifyList.jsp";
                    break;

                case "changeUserStauts":
                    int user_Id = Integer.parseInt(request.getParameter("userId"));
                    String status = request.getParameter("status");
                    userDao.changeUserStatus(user_Id, status);
                    forwardToJsp = "changeUserStatus.jsp";
                    break;

                case "cancelClass":
                    int timetableId = Integer.parseInt(request.getParameter("timetableID"));
                    int cId = timetableDao.getCourseIdByTid(timetableId);
                    Course course = courseDao.getCourseByID(cId);
                    String cancelClassDate = request.getParameter("date");
                    String courseStartDate = course.getStart_date();
                    String courseEndDate = course.getEnd_date();
                    Date date1 = null;
                    Date date2 = null;
                    Date date3 = null;
                     {
                        try {
                            date1 = new SimpleDateFormat("yyyy-mm-dd").parse(cancelClassDate);
                        } catch (ParseException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                     {
                        try {
                            date2 = new SimpleDateFormat("yyyy-mm-dd").parse(courseStartDate);
                        } catch (ParseException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                     {
                        try {
                            date3 = new SimpleDateFormat("yyyy-mm-dd").parse(courseEndDate);
                        } catch (ParseException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (date1.compareTo(date2) > 0 && date1.compareTo(date3) < 0) {
                        timetableCancellations.insertCancelDate(timetableId, cancelClassDate);
                        ArrayList<Integer> studentIds = courseStudentsDao.getStudentIdsInCourse(cId);
                        for (int studId : studentIds) {
                            notificationDao.insertNotification(studId, "Class Cancelled on " + cancelClassDate);
                        }
                        forwardToJsp = "cancelClass.jsp";
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = "Selected Date is smaller than start date or greater than end date!";
                        session.setAttribute("error", error);
                    }
                    break;

                case "searchResultTeacher":
                    int tId = Integer.parseInt(request.getParameter("teacherID"));
                    session.setAttribute("selectedTeacher", tId);
                    ArrayList<String> tsList = joinDao.searchByTeacher(tId);
                    Gson Gson = new GsonBuilder().disableHtmlEscaping().create();
                    session.setAttribute("tsList", Gson.toJson(tsList));
                    forwardToJsp = "searchResultByTeacher.jsp";
                    break;

                case "dropOutOfCourse":
                    int courseID = Integer.parseInt(request.getParameter("subject"));
                    courseStudentsDao.deleteStudentCourse(courseID, (int) session.getAttribute("userId"));
                    forwardToJsp = "home.jsp";
                    break;

                case "selectedTeacher":
                    int teacherid = Integer.parseInt(request.getParameter("teacherId"));
                    session.setAttribute("teacherId", teacherid);
                    forwardToJsp = "teacherDetails.jsp";
                    break;

                case "switchFreeToPremiumTeacher":
                    teacherDetailsDao.changePremiumStatus((int) session.getAttribute("userId"), true);
                    forwardToJsp = "home.jsp";
                    break;

                case "insertPaymentDetails":
                    userPaymentDao.insertPaymentDetails((int) session.getAttribute("userId"), (int) session.getAttribute("courseId"));
                    forwardToJsp = "home.jsp";
                    break;

                case "addSubjects":
                    int tID = teacherDetailsDao.getTeacherDetailsByUserId((int) session.getAttribute("userId")).getTeacher_id();
                    int sub1 = Integer.parseInt(request.getParameter("subject0"));
                    int sub2 = Integer.parseInt(request.getParameter("subject1"));
                    String lvl1 = request.getParameter("level0");
                    String lvl2 = request.getParameter("level1");
                    if (sub1 > 0 && !"none".equals(lvl1)) {
                        teacherSubjectsDao.insertTeacherSubjects(tID, sub1, lvl1);
                    }
                    if (sub2 > 0 && !"none".equals(lvl2)) {
                        teacherSubjectsDao.insertTeacherSubjects(tID, sub2, lvl2);
                    }
                    forwardToJsp = "updateTeacherSubjects.jsp";
                    break;

                case "updateCourseDetails":
                    int tsId = Integer.parseInt(request.getParameter("tsId"));
                    int capacity = Integer.parseInt(request.getParameter("capacity"));
                    String des = request.getParameter("description");
                    String sDate = request.getParameter("startDate");
                    String eDate = request.getParameter("endDate");
                    courseDao.updateOngoingCourse(tsId, capacity, des, sDate, eDate);
                    forwardToJsp = "updateCourseDetails.jsp";
                    break;

                case "updateCourseLink":
                    String courseLink = request.getParameter("courseLink");
                    int courseId = Integer.parseInt(request.getParameter("courseId"));
                    if ("".equals(courseLinkDao.getLink(courseId))) {
                        courseLinkDao.insertCourseLink(courseId, courseLink);
                    } else {
                        courseLinkDao.updateLink(courseId, courseLink);
                    }
                    forwardToJsp = "courseBoard.jsp";
                    break;

                case "toSignUpCourse":
                    int cID = Integer.parseInt(request.getParameter("courseId"));
                    session.setAttribute("courseID", cID);
                    forwardToJsp = "comfirmRegister.jsp";
                    break;

                case "updateNotification":
                    int notificationId = Integer.parseInt(request.getParameter("id"));
                    notificationDao.updateNotification(notificationId);
                    forwardToJsp = "home.jsp";
                    break;

                case "deleteNotification":
                    int notiId = Integer.parseInt(request.getParameter("id"));
                    notificationDao.deleteNotification(notiId);
                    forwardToJsp = "home.jsp";
                    break;

                case "viewCourse":
                    int viewCourseId = Integer.parseInt(request.getParameter("viewCourseId"));
                    session.setAttribute("viewCourseId", viewCourseId);
                    forwardToJsp = "courseBoard.jsp";
                    break;
                case "insertCourseMessage":
                    int courseId1 = Integer.parseInt(request.getParameter("courseId"));
                    String message = request.getParameter("message");
                    String newMessage = "";
                    newMessage = message.trim();
                    courseMessageDao.insertMessage(courseId1, newMessage);
                    forwardToJsp = "courseBoard.jsp";
                    break;
                case "deleteCourseMessage":
                    int courseMessageId = Integer.parseInt(request.getParameter("courseMessageId"));

                    courseMessageDao.deleteMessage(courseMessageId);

                    forwardToJsp = "courseBoard.jsp";
                    break;
                case "insertReview":
                    int stud_id = Integer.parseInt(request.getParameter("userId"));
                    int teach_id = Integer.parseInt(request.getParameter("teacherId"));
                    int rating = Integer.parseInt(request.getParameter("rating"));
                    String description = request.getParameter("description");
                    String newDes = "";
                    newDes = description.trim();
                    reviewDao.insertReview(teach_id, stud_id, rating, newDes);
                    forwardToJsp = "home.jsp";
                    break;
                case "disableCourse":
                    int course_id1 = Integer.parseInt(request.getParameter("courseId"));

                    courseDao.disableCourse(course_id1);
                    forwardToJsp = "home.jsp";
                    break;
                case "downloadReport":
                    SubjectDao subDao = new SubjectDao("digiclass");
                    ArrayList<Subject> subjects = subDao.getAllSubjects();
                    int noOfStudents = userDao.getNoOfStudents();
                    int noOfTeachers = userDao.getNoOfTeachers();
                    int noOfCourses = courseDao.getNoOfPastAndPresentCourse();
                    ArrayList<TeacherDetails> tsList1 = teacherDetailsDao.getAllVerifiedTeachers();
                    int counter = 1;
                    String name = "";
                    try {
                        PrintWriter pw = new PrintWriter(new File("C:\\Users\\denis\\documents\\DigiClassReport.csv"));
                        StringBuilder sb = new StringBuilder();
                        sb.append(",");
                        sb.append(",");
                        sb.append(",");
                        sb.append("ADMINISTRATOR REPORT");
                        sb.append("\r\n");
                        sb.append("\r\n");
                        sb.append("Subjects taught at both HL/OL");
                        sb.append("\r\n");
                        sb.append("subject_id");
                        sb.append(",");
                        sb.append("subject_name");
                        sb.append("\r\n");

                        for (Subject s : subjects) {
                            sb.append(s.getSubject_id());
                            sb.append(",");
                            sb.append(s.getSubject_name());
                            sb.append("\r\n");
                        }
                        sb.append("\r\n");
                        sb.append("Number Of Students on DigiClass:");
                        sb.append(",");
                        sb.append(noOfStudents);
                        sb.append("\r\n");
                        sb.append("Number Of Teachers on DigiClass:");
                        sb.append(",");
                        sb.append(noOfTeachers);
                        sb.append("\r\n");
                        sb.append("\r\n");
                        sb.append("Number Of Courses (Past + Present) on DigiClass:");
                        sb.append(",");
                        sb.append(noOfCourses);
                        sb.append("\r\n");
                        sb.append("List of Verified Teachers");
                        sb.append("\r\n");
                        for (TeacherDetails verified : tsList1) {
                            UserDetails ud1 = userDetailsDao.getUserDetailsByUserId(verified.getUser_id());
                            name = ud1.getFirst_name() + " " + ud1.getLast_name();
                            sb.append(counter);
                            sb.append(",");
                            sb.append(name);
                            sb.append("\r\n");
                            counter++;
                        }

                        sb.append("\r\n");
                        sb.append("END OF REPORT");
                        sb.append(",");
                        sb.append(",");
                        sb.append(",");
                        sb.append("Email:");
                        sb.append(",");
                        sb.append("digiclassdtc@gmail.com");
                        sb.append(",");
                        sb.append(2021);
                        pw.write(sb.toString());
                        pw.close();
                        forwardToJsp = "home.jsp";
                        break;
                    } catch (Exception e) {
                        forwardToJsp = "error.jsp";
                        String error = e.toString();
                        session.setAttribute("errorMessage", error);
                        break;
                    }

                default:
                    forwardToJsp = "error.jsp";
                    String error = "No such function available on this site";
                    session.setAttribute("errorMessage", error);
            }
        }
        response.sendRedirect(forwardToJsp);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
