package univ;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Objects;
/**
 * Store the details of a Course,
 * such as the course code, course title, course credit, course prerequisites,
 *  
 * @author Umer Ahmed
 * @version 2018.11.25
 */



public class Course implements Serializable
{
    private String courseCode;
    private String courseTitle;     
    private double credit;
    private ArrayList<Course> preReqList;
    private String semesterOffered;


    public Course() {
        this.courseCode = null;
        this.courseTitle = null;
        this.credit = 0;
        this.preReqList = new ArrayList<>();
        this.semesterOffered = null;
    }

    /*  Deep Copy Constructor for Course */
    public Course(Course course) {
        this.courseCode = course.getCourseCode();
        this.courseTitle = course.getCourseTitle();
        this.credit = course.getCourseCredit();
        this.preReqList = course.getPrerequisites();
        this.semesterOffered = course.getSemesterOffered();
        
    }
    
    /**
	 * Sets the course code. Protected so only class in univ can set it
	 * 
	 * @param courseCode A string course code (such as CIS*1500)
	 */
    protected void setCourseCode(String courseCode) {
        if (courseCode != null && !courseCode.isEmpty()) 
        {
            this.courseCode = courseCode;
        }
    }
    
    /**
     * Sets the course Title. Protected so only class in univ can set it
     * 
     * @param courseTitle A string course title (such as Introduction to Programming)
     */
    protected void setCourseTitle(String courseTitle) {
        if (courseTitle != null && !courseTitle.isEmpty()) 
        {
            this.courseTitle = courseTitle;
        }
    }


    /**
     * Sets the SemesterOffered. Protected so only class in univ can set it
     * 
     * @param semester A string semester taken (such as F18)
     */    
    protected void setSemesterOffered(String semester) {
        if (semester != null && !semester.isEmpty()) 
        {
            this.semesterOffered = semesterOffered;
        }

    }

    /**
     * Sets the course credit. Protected so only class in univ can set it
     * 
     * @param credit A double of how much credit a course is (such as 0.5)
     */ 
    public void setCourseCredit(Double credit) {
        if (credit != null && credit >= 0 && credit <= 1.0) 
        {
            this.credit = credit;
        }
    }

    /**
     * Sets the prerequisites. Protected so only class in univ can set it
     * 
     * @param  preReqList An arraylist of Courses of all the prerquisites of a particular course
     */ 
    public void setPrerequisites(ArrayList<Course> preReqList) {
        if (preReqList == null) 
        {
            this.preReqList = null;
        } 
        else 
        {
            this.preReqList = preReqList;
        }
    }

    /**
     *returns a string of the course code
     *@return course code
     */ 
    public String getCourseCode() { 
        return this.courseCode; 
    }


   /**
     * Returns a string of the course title
     * 
     * @return course title
     */
    public String getCourseTitle() { 
        return this.courseTitle; 
    }

    /**
     *returns a double of the course credit
     *@return course credit
     */ 
    public double getCourseCredit() { 
        return this.credit; 
    }

     /**
     *returns an arraylist of all the prerequisite courses for this course
     *@return prerequisites courses
     */ 
    public ArrayList<Course> getPrerequisites() { 
        return this.preReqList; 
    }
    
     /**
     *returns a string of the semester taken
     *@return semester taken
     */ 
    public String getSemesterOffered(){
        return this.semesterOffered;
    }


    @Override
    public String toString() {
        
        StringBuilder toString = new StringBuilder();
        if (this.courseCode != null) {
            toString = new StringBuilder((this.courseCode + "," ));
        }
        if (this.courseTitle != null) {
            toString.append(this.courseTitle + ",");
        }
        if (this.credit > 0) {
            toString.append(this.getCourseCredit() + ",");
        }
        if (this.semesterOffered != null) {
            toString.append(this.semesterOffered + ",");
        }
        if (this.preReqList != null) {
            
            for (Course c : this.preReqList) {
                if(c != preReqList.get(preReqList.size()-1))
                {
                    toString.append(c.getCourseCode()+":");
                }
                else
                {
                    toString.append(c.getCourseCode());
                }
            }
        }
        /*
        StringBuilder toString = new StringBuilder();
        if (this.courseCode != null) {
            toString = new StringBuilder(("Code: " + this.courseCode + System.getProperty("line.separator")));
        }
        if (this.courseTitle != null) {
            toString.append("Title: ").append(this.courseTitle).append(System.getProperty("line.separator"));
        }
        if (this.credit > 0) {
            toString.append("Credit: ").append(this.getCourseCredit()).append(System.getProperty("line.separator"));
        }
        if (this.semesterOffered != null) {
            toString.append("Semester Offered: ").append(this.semesterOffered).append(System.getProperty("line.separator"));
        }
        if (this.preReqList != null) {
            toString.append("Prerequisites: ");
            for (Course c : this.preReqList) {
                toString.append(c.getCourseCode());
            }
        }
         */
        return toString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Course)) {
            return false;
        }

        Course course = (Course) o;
        if (course.courseCode == null || !(this.courseCode.equals(course.courseCode))) {
            return false;
        }
        if (course.courseTitle == null || !(this.courseTitle.equals(course.courseTitle))) {
            return false;
        }
       
        return this.preReqList.equals(course.preReqList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.courseCode);
        hash = 53 * hash + Objects.hashCode(this.courseTitle);
        hash = 53 * hash + Objects.hashCode(this.preReqList);
        return hash;
    }
}
