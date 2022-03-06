/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Daos.*;
import Business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author denis
 */
public class test {

    public static void main(String[] args) throws NoSuchAlgorithmException, MalformedURLException, IOException {
        ArrayList<Subject> subjects = new ArrayList<>();
//        SubjectDao subDao = new SubjectDao("digiclass");
        UserDao userDao = new UserDao("digiclass");
//        UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
        TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
        TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
//        CourseDao courseDao = new CourseDao("digiclass");
//        TimetableDao timetableDao = new TimetableDao("digiclass");
            UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
//        SubjectDao sDao = new SubjectDao("digiclass");
//        ArrayList<String> test = new ArrayList<>();
//        ArrayList<TeacherDetails> tsList = teacherDetailsDao.getAllVerifiedTeachers();
//        CourseMessageDao cmDao = new CourseMessageDao("digiclass");
//        CourseStudentsDao csDao = new CourseStudentsDao("digiclass");

        System.out.println(userDao.getNoOfStudents());

    }
}
