package univ;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.Serializable;



public abstract class Degree implements Serializable
{
   
    String title;
    ArrayList<Course> listOfRequiredCourseCodes;//this should probably be an ArrayList<Course>
    CourseCatalog courses;
    
    
    public Degree()
    {
        this.title = null;
        this.listOfRequiredCourseCodes = new ArrayList<>();
        this.courses = new CourseCatalog();
        //requiredCourses = new ArrayList<>();
    }
    
    

    public String getDegreeTitle()
    {
        return this.title;
    }
    
    public void setDegreeTitle(String title)
    {
        if (title != null && !title.isEmpty())
            this.title = title;
    }
    
    public void setRequiredCourses(ArrayList<Course> listOfRequiredCourseCodes) 
    {
        if (listOfRequiredCourseCodes != null && !listOfRequiredCourseCodes.isEmpty())
            this.listOfRequiredCourseCodes = listOfRequiredCourseCodes;
    }
    
    public ArrayList<Course> getRequiredCourses() 
     { 
       //needs to be implemented.
            /***algorithm
                for each course code in listOfRequiredCourseCodes:
                     find the course in the course catalog
                     add the found course to an arraylist
                 return the arraylist
    
            **/

            return new ArrayList<Course>(); 
        } 
    
    /*
    public void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes)
    {
        
        for(String course:listOfRequiredCourseCodes)
        {
            if(availableCourses.findCourse(course) != null)
            {
                requiredCourses.add(availableCourses.findCourse(course));
                System.out.println("added Required course: " + course );
            }
                      
        }
        
        
    
    }
    */
    public abstract boolean meetsRequirements(ArrayList<Course> allTheCoursesPlannedAndTaken);
    public abstract double numberOfCreditsRemaining(ArrayList<Course> allTheCoursesPlannedAndTaken);
    public abstract ArrayList<Course> remainingRequiredCourses(ArrayList<Course> allTheCoursesPlannedAndTaken);
   
    @Override
    public abstract String toString();
    @Override
    public abstract boolean equals(Object o);
    @Override
    public abstract int hashCode();
   
   
    /*
    public abstract boolean meetsRequirements(PlanOfStudy thePlan);
    public abstract double numberOfCreditsRemaining(PlanOfStudy thePlan);
    public abstract ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan);

    @Override
    public abstract String toString();
    @Override
    public abstract boolean equals(Object o);
    @Override
    public abstract int hashCode();
    */
    
    
}
