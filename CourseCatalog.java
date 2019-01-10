package univ;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;


public class CourseCatalog implements Serializable {

    private ArrayList<Course> courseCatalog;
    private String pwd;
    private String user;
    private MyConnection c;
    
    public CourseCatalog(){
        this.courseCatalog = new ArrayList<>();
        pwd = DBDetails.password;
        user = DBDetails.username;
        c = new MyConnection(user, pwd);
        initializeCatalog();
    
    
    }

    public void setCourseCatalog(ArrayList<Course> courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public ArrayList<Course> getCourseCatalog() { 
        return this.courseCatalog; 
    }

    public void addCourse(Course toAdd) {
        for (Course c : this.courseCatalog) {
            if (c.equals(toAdd)) {
                return;
            }
        }
        courseCatalog.add(toAdd);
    }

    public void removeCourse(Course toRemove) {
        for (Course c : this.courseCatalog) {
            if (c.equals(toRemove)) {
                this.courseCatalog.remove(c);
                return;
            }
        }
    }

    public Course findCourse(String courseCode){
        String line = c.findCourse(courseCode);
        Course toFind = new Course();
        if(line == null)
        {
            return null;
        }
        else
        {
            String cvsSplit = ",";
            String preReqSplit = ":";
            ArrayList<Course> preReqList = new ArrayList<Course>(); 
            int i = 0;
            int j = 0;
                                              
                          
            String[] course = line.split(cvsSplit);
                    
            if(course.length < 5 )
            {
                                              
                toFind.setCourseCode(course[0]);
                toFind.setCourseCredit(Double.parseDouble(course[1]) );
                toFind.setCourseTitle(course[2]);
                toFind.setSemesterOffered(course[3]);
            }
            else
            {
                toFind.setCourseCode(course[0]);
                toFind.setCourseCredit(Double.parseDouble(course[1]) );
                toFind.setCourseTitle(course[2]);
                toFind.setSemesterOffered(course[3]);
                            
                String[] preReq = course[4].split(preReqSplit);
                            
                for(i =0; i< preReq.length; i++)
                {
                   preReqList.add(findCourse(preReq[i]) );
                }
                toFind.setPrerequisites(preReqList);
            }                                      
                           
                         
                                   
       }
                    
       return toFind;         
                 
                    
   }
                
                           
                
                                    
         
        
    
                
        
      

    public Boolean isEmpty() { 
        return courseCatalog.isEmpty(); 
    }

    public void saveCatalog() {
        /*
        try (FileWriter PoSData = new FileWriter("BootstrapCatalog.txt")) {
            String fileLine;
            for (Course c : this.courseCatalog) {
                fileLine = c.toFile();
                PoSData.write(fileLine);
            }
            PoSData.close();
            System.out.println("Course Catalog saved to 'BootstrapCatalog.txt'.");
        } catch (Exception e) {
            System.out.println("Failed to successfully save state for Plan Of Study.");
        }
        */
    }

    protected void initializeCatalog() {
        boolean fullyResetTables = true; //Set this to true if you wish to rebuild/reset your tables!
        PrepStudentScript initTables = new PrepStudentScript(fullyResetTables); //called to initialize our tables
        Course test= new Course();
        //clear all DBStudent information so we have a fresh db
        //c.deleteAllSavedStudent();
        
        //clear all course info and repopulate it with the provided course list
        c.deleteAllCourses();
        c.repopulateCourses();
        
        test = findCourse("cis*2750");
        if(test!= null){
            System.out.println(test.toString());
        }
        else{System.out.println("Not found");}
        

    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.courseCatalog != null) {
            toString.append("Course Catalog: ");
            for (Course c : this.courseCatalog) {
                toString.append(c.toString());
            }
            toString.append(System.getProperty("line.separator"));
        }
        return toString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CourseCatalog)) {
            return false;
        }

        ArrayList<Course> courseCat = ((CourseCatalog) o).courseCatalog;

        return this.courseCatalog.equals(courseCat);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.courseCatalog);
        return hash;
    }
    
    public static void main (String[] args){
        CourseCatalog p = new CourseCatalog();
    }
    

}
