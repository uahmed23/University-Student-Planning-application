package univ;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Objects;
//import univ.Course;


/**
 * Write a description of class Attempt here.
 *
 * Umer Ahmed
 * Nov 26 2018
 */
public class Attempt 
{
    private Course attemptedCourse;
    private String grade;
    private String semester;
   
    public Attempt() {
        this.attemptedCourse= new Course();
        this.grade = null;
        this.semester = null;
    }

    
    // a number, P, F, INC or MNR
    public void setAttemptGrade(String grade) {
        if (grade == null) 
        {
            this.grade = null;
            return;
        }
        else if (grade.equalsIgnoreCase("P") || grade.equalsIgnoreCase("F") ||grade.equalsIgnoreCase("INC")||grade.equalsIgnoreCase("MNR")  )      
        {
            this.grade = grade;
            return;
        }
        int gradeNum;
        try {
            gradeNum = Integer.parseInt(grade);
            if (gradeNum <= 100 && gradeNum >= 0) 
            {
                this.grade = grade;
            }
        } catch (Exception ignored) {
            System.out.println("Grades must be: a number(0-100), P, F, INC or MNR.");
        }
    }
    
     public String getAttemptGrade() { 
        return this.grade; 
    }
    
        
   
    public void setSemesterTaken(String semester){
        if (semester != null && !semester.isEmpty()) 
        {
            this.semester = semester;
        }
    }
    
    public String getSemesterTaken(){
        return this.semester;
    }
    
    public void setCourseAttempted(Course theCourse){
        this.attemptedCourse = theCourse;
    }
    
    public Course getCourseAttempted(){
        return this.attemptedCourse;
    }
          


    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.attemptedCourse.getCourseCode() != null) {
            toString = new StringBuilder(("Code: " + this.attemptedCourse.getCourseCode() + System.getProperty("line.separator")));
        }
        if (this.grade != null) {
            toString.append("Grade: ").append(this.grade).append("\n");
        }
        if (this.semester != null) {
            toString.append("Semester Taken: ").append(this.semester).append(System.getProperty("line.separator"));
        }
        
        return toString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Attempt)) {
            return false;
        }

        Attempt attempt = (Attempt) o;
        if (attempt.attemptedCourse == null || !(this.attemptedCourse.equals(attempt.attemptedCourse))) {
            return false;
        }
        if (attempt.grade == null || !(this.grade.equals(attempt.grade))) {
            return false;
        }
        if (attempt.semester == null || !(this.semester.equals(attempt.semester))) {
            return false;
        }
        if (this.grade != null && attempt.grade != null) {
            if (!(this.grade.equals(attempt.grade))) {
                return false;
            }
        }
        
        return this.semester.equals(attempt.semester);
         
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.attemptedCourse);
        hash = 53 * hash + Objects.hashCode(this.grade);
        hash = 53 * hash + Objects.hashCode(this.semester);
        
        return hash;
    }
}    

