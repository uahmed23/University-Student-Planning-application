
/**
 * Store the details of a Student,
 * such as the first name, last name, and student number.
 * 
 * @author Umer Ahmed
 * @version 2018.10.03
 */

import java.util.Objects;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.io.Serializable;
import univ.Course;
import univ.Attempt;
import univ.CourseCatalog;
import univ.MyConnection;
import univ.DBDetails;
import univ.DBStudent;
import univ.PrepStudentScript;
//import univ.Degree;
//import univ.HonoursDegree;
//import univ.GeneralDegree;
//import univ.BCG;




public class Student implements Serializable {

    private String first;
    private String last;
    private int studentNum;
    private CourseCatalog Courses;
    private ArrayList<Course> plannedCourses;
    private ArrayList<Attempt> transcriptCourses;
    private String pwd;
    private String user;
    private MyConnection c;
    private DBStudent loadedS;
    
    //private Degree deg;
    

    public Student() {
        this.first = null;
        this.last = null;
        this.studentNum = 0;
        this.plannedCourses = new ArrayList<>();
        this.transcriptCourses = new ArrayList<>();
        Courses = new CourseCatalog();
        pwd = DBDetails.password;
        user = DBDetails.username;
        c = new MyConnection();
        //loadedS = new  DBStudent();
        newStudent();
        //this.deg = null;
    }
    
    public void newStudent(){
                   
          if(c.loadStudent(Integer.toString(getStudentNumber()),getFirstName()).getId() == null)
         {
             loadedS = new DBStudent(Integer.toString(getStudentNumber()),getFirstName());
             c.saveStudent(loadedS);
                
         }
         else
         {
             loadedS = c.loadStudent(Integer.toString(getStudentNumber()),getFirstName());
         }
          
        
    }
    
    public void saveStudent()
    {
        c.saveStudent(loadedS);
    }

    public void addToTranscript(String courseCode,String semesterTaken, String grade ) throws CourseNotFoundException{
        Course toAdd = new Course();
        
        toAdd = Courses.findCourse(courseCode);
        
        if (toAdd == null)
        {
            throw new CourseNotFoundException();
        }
        else
        {
                //test save student
            ArrayList<String> cl = new ArrayList<String>();
            cl = loadedS.getCourses();
            cl.add(courseCode+","+ semesterTaken+ ","+ grade);  
            loadedS.setCourses(cl);
                     
        
        }

    }
    
    public void removeFromTranscript(String courseCode,String semesterTaken, String grade ) throws CourseNotFoundException{
        
        Course toRemove = new Course();
        
        toRemove = Courses.findCourse(courseCode);
        
        if (toRemove == null)
        {
            throw new CourseNotFoundException();
        }
        else
        {
                //test save student
            ArrayList<String> cl = new ArrayList<String>();
             // A future course i plan to take
            //cl.add("CIS*2520,0.5,Data Structures,W18,CIS*2500");                // A future course i plan to take
            //cl.add("CIS*1500,F16,87,Completed");                                // What an attempt might look like (you format these however you need!)
            //DBStudent s = new DBStudent("123123","Matt","MSC",cl);
    
            //c.saveStudent(s);
                //test load student
            //DBStudent loadedS = c.loadStudent(Integer.toString(getStudentNumber()),getFirstName());
            cl = loadedS.getCourses();
            
            cl.remove(courseCode+","+ semesterTaken+ ","+ grade);
            
            /*
            for(String course1 : cl){
                if(course1.equalsIgnoreCase(toRemove.toString()))
                {
                    cl.remove(course1);
                    
                }                                      
        
            }
            */
            loadedS.setCourses(cl);
        }
    }
    
    public void addToPOS(String courseCode) throws CourseNotFoundException{
        Course toAdd = new Course();
        
        toAdd = Courses.findCourse(courseCode);
        
        if (toAdd == null)
        {
            throw new CourseNotFoundException();
        }
        else
        {
                //test save student
            ArrayList<String> cl = new ArrayList<String>();
             // A future course i plan to take
            //cl.add("CIS*2520,0.5,Data Structures,W18,CIS*2500");                // A future course i plan to take
            //cl.add("CIS*1500,F16,87,Completed");                                // What an attempt might look like (you format these however you need!)
            //DBStudent s = new DBStudent("123123","Matt","MSC",cl);
    
            //c.saveStudent(s);
                //test load student
            //DBStudent loadedS = c.loadStudent(Integer.toString(getStudentNumber()),getFirstName());
            cl = loadedS.getCourses();
            cl.add(toAdd.toString());
            loadedS.setCourses(cl);
                     
        
        }

    }
    
    public void removeFromPos(String courseCode) throws CourseNotFoundException{
        
        Course toRemove = new Course();
        
        toRemove = Courses.findCourse(courseCode);
        
        if (toRemove == null)
        {
            throw new CourseNotFoundException();
        }
        else
        {
                //test save student
            ArrayList<String> cl = new ArrayList<String>();
             // A future course i plan to take
            //cl.add("CIS*2520,0.5,Data Structures,W18,CIS*2500");                // A future course i plan to take
            //cl.add("CIS*1500,F16,87,Completed");                                // What an attempt might look like (you format these however you need!)
            //DBStudent s = new DBStudent("123123","Matt","MSC",cl);
    
            //c.saveStudent(s);
                //test load student
            //DBStudent loadedS = c.loadStudent(Integer.toString(getStudentNumber()),getFirstName());
            cl = loadedS.getCourses();
            
            cl.remove(toRemove.toString());
            
            /*
            for(String course1 : cl){
                if(course1.equalsIgnoreCase(toRemove.toString()))
                {
                    cl.remove(course1);
                    
                }                                      
        
            }
            */
            loadedS.setCourses(cl);
        }

    }

    public void setFirstName(String first) throws NoInputException{
        if (first == null || first.isEmpty())
        {
            throw new NoInputException();
        }
        else
        {
            this.first = first;
        }
    }

    public void setLastName(String last) throws NoInputException{
        if (last == null|| last.isEmpty())
        {
            throw new NoInputException();
        }
        else
        {
            this.last = last;
        }
    }

    public void setStudentNumber(Integer studentNum) { this.studentNum = studentNum; }

    public String getFullName() {
        String fullName;
        if (this.first == null && this.last == null) {
            return null;
        } else if (this.first == null) {
            fullName = this.last;
        } else if (this.last == null) {
            fullName = this.first;
        } else {
            fullName = this.first + " " + this.last;
        }
        return fullName;
    }

    public String getFirstName() { return this.first; }

    public String getLastName() { return this.last; }

    public Integer getStudentNumber() { return this.studentNum; }
    
    

    @Override
    public String toString() {
        String toString = "";
        if (this.first != null) {
            toString = ("First name: " + this.first + System.getProperty( "line.separator" ));
        }
        if (this.last != null) {
            toString += ("Last name: " + this.last + System.getProperty( "line.separator" ));
        }
        toString += ("Student number: " + this.studentNum + System.getProperty( "line.separator" ));

        return toString;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Student)) {
            return false;
        }

        Student student = (Student) o;
        if (!(this.first.equals(student.first))){
            return false;
        }
        if (!(this.last.equals(student.last))){
            return false;
        }
        return this.studentNum == student.studentNum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.first);
        hash = 37 * hash + Objects.hashCode(this.last);
        hash = 37 * hash + Objects.hashCode(this.studentNum);
        return hash;
    }
    
    
}

