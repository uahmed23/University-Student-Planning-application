import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.*;
/**
 * Write a description of class LE3Gui here.
 *
 * Umer Ahmed
 * November 13
 */
public class Planner extends JFrame implements ActionListener
{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 500;
    private TextArea collectionView;
    private TextField stringAdd;
    private TextField numberAdd;
    private String degreeName;
    private String majorName;
    private Student student; 
    //private String pwd;
    //private String user;
    //private MyConnection c;
    public Planner()
    {
        super();
        //pwd = DBDetails.password;
        //user = DBDetails.username;
        //c = new MyConnection(user,pwd);
        student = new Student();
        //testConnection();

        setSize(WIDTH, HEIGHT);
        setPreferredSize(new Dimension(400,400));

        degreeName = new String();
        majorName = new String();
        stringAdd = new TextField();
        numberAdd = new TextField();
        setTitle("Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        choiceStory();
        //setupUser();
        //setupAdmin();
       // setupAddToPanel();
        //setupCollectionView();
        
        setupMenubar();
        
        pack();
        
        
        
    }
    /*
    public void testConnection()
    {
        //MyConnection c = new MyConnection(user,pwd);

        boolean fullyResetTables = true; //Set this to true if you wish to rebuild/reset your tables!
        PrepStudentScript initTables = new PrepStudentScript(fullyResetTables); //called to initialize our tables
        
        //clear all DBStudent information so we have a fresh db
        c.deleteAllSavedStudent();
        
        //clear all course info and repopulate it with the provided course list
        c.repopulateCourses();

        //test addCourse
        c.addCourse("CIS*9999", "0.75", "Oopsies", "never", "");

        //test deleteCourse
        c.deleteCourse("CIS*9999");

        //test getAllCourses
        ArrayList<String> courses = c.getAllCourses();
        for (String course : courses){
            System.out.println(course);
        }
        
        //test getCourse for CIS*1500
        String course = c.findCourse("CIS*1500");
        System.out.println(course);

        //test save student
        ArrayList<String> cl = new ArrayList<String>();
        cl.add("CIS*3530,0.5,Data Base Systems and Concepts,F19,CIS*2520"); // A future course i plan to take
        cl.add("CIS*2520,0.5,Data Structures,W18,CIS*2500");                // A future course i plan to take
        cl.add("CIS*1500,F16,87,Completed");                                // What an attempt might look like (you format these however you need!)
        DBStudent s = new DBStudent("123123","Matt","MSC",cl);

        c.saveStudent(s);

        //test load student
        DBStudent loadedS = c.loadStudent("123123","Matt");

        System.out.println(loadedS.getId());
        JOptionPane.showMessageDialog(this,loadedS.getId() );
                
        System.out.println(loadedS.getName());
        System.out.println(loadedS.getDegree());
        cl = loadedS.getCourses();
        System.out.println(loadedS.getCourses());
        for (String cInfo : cl){
            //lets get each course from our course list!
            System.out.println(c.findCourse(cInfo.split(",")[0]));
        }

        //test clear Courses
        c.deleteAllCourses();

        //test delete specific student
        c.deleteSavedStudent("123123","Matt");
        loadedS = c.loadStudent("123123", "Matt");
        System.out.println(loadedS.getId());

        courses = c.getAllCourses();
        for (String course2 : courses){
            System.out.println(course);
        }


    }

    */
    private void choiceStory()
    {

        JPanel startPanel = new JPanel();
        //startPanel.setPreferredSize(new Dimension(400,400));
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));//BoxLayout.PAGE_AXIS
        
        //startPanel.add(new Label ("     WELCOME"));
        
        JButton userButton = new JButton("User");
        startPanel.add(userButton);
        userButton.addActionListener(ev->
            {
                //JOptionPane.showMessageDialog(this,"hello...");
                startPanel.setVisible(false);
                login();
            });

        JButton administratorButton = new JButton("Administrator");
        startPanel.add(administratorButton);
        administratorButton.addActionListener(ev->
            {
                startPanel.setVisible(false);
                setupAdmin();

            });
        
        add(startPanel);
        startPanel.setVisible(true);
        //pack();
        //revalidate();
    }
    private void login()
    {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));//BoxLayout.PAGE_AXIS
        
        TextField firstNm = new TextField();
        loginPanel.add(new Label("First Name:"));
        loginPanel.add(firstNm);

        TextField lastNm = new TextField();
        loginPanel.add(new Label("Last Name:"));
        loginPanel.add(lastNm);

        TextField idNm = new TextField();
        loginPanel.add(new Label("Student ID:"));
        loginPanel.add(idNm);

        
        
        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton);
        loginButton.addActionListener(ev->
            {
                int id;
                try
                {
                     student.setFirstName(firstNm.getText());
                     student.setLastName(lastNm.getText());
                     try
                     {
                        id= Integer.parseInt(idNm.getText());
                        student.setStudentNumber(Integer.parseInt(idNm.getText()));
                        student.newStudent();
                        loginPanel.setVisible(false);
                        setupUser();
                     }
                     catch(NumberFormatException e)
                     {
                         JOptionPane.showMessageDialog(this,"Please enter a valid student number.");
                     }
                                        
                }
                catch(NoInputException e)
                {
                  JOptionPane.showMessageDialog(this,"Please enter your full name.");
                }                             
            
                   
            });

          
        JButton backButton = new JButton("Back");
        loginPanel.add(backButton);
        
        backButton.addActionListener(ev->
            {
                loginPanel.setVisible(false);
                choiceStory();
                
            });

        add(loginPanel);
        loginPanel.setVisible(true);
    }
    private void setupUser()
    {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel(student.getFullName()+ " ("+Integer.toString(student.getStudentNumber()) + ")");
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        userPanel.add(title);
        JButton selectDegreeButton = new JButton("Select Degree");
        userPanel.add(selectDegreeButton);
        selectDegreeButton.addActionListener(ev->
            {
                //JOptionPane.showMessageDialog(this,"DEGREES");
                userPanel.setVisible(false);
                degreeChoice();
            });

        JButton selectMajorButton = new JButton("Select Major");
        userPanel.add(selectMajorButton);
        selectMajorButton.addActionListener(ev->
            {
               // JOptionPane.showMessageDialog(this,"MAJORS");
                userPanel.setVisible(false);
                majorChoice();
            });

        JButton planOfStudyButton = new JButton("Plan of Study");
        userPanel.add(planOfStudyButton);
        planOfStudyButton.addActionListener(ev->
            {
               // JOptionPane.showMessageDialog(this,"MAJORS");
                userPanel.setVisible(false);
                changePOS();
            });

        JButton transcriptButton = new JButton("Transcript");
        userPanel.add(transcriptButton);
        transcriptButton.addActionListener(ev->
            {
               // JOptionPane.showMessageDialog(this,"MAJORS");
                userPanel.setVisible(false);
                changeTranscript();
            });
             
        JButton saveButton = new JButton("Save my program");
        userPanel.add(saveButton);
        saveButton.addActionListener(ev->
            {
                student.saveStudent();
                JOptionPane.showMessageDialog(this,"saves student transcript and pos");
                
            });

             
            
        JButton backButton = new JButton("Logout");
        userPanel.add(backButton);
        
        backButton.addActionListener(ev->
            {
                userPanel.setVisible(false);
                choiceStory();
                
            });

               
        add(userPanel);
        userPanel.setVisible(true);
        //pack();
        //revalidate();
    } 

    private void changeTranscript()
    {
        JPanel transcriptPanel = new JPanel();
        transcriptPanel.setLayout(new BoxLayout(transcriptPanel, BoxLayout.PAGE_AXIS));

        TextField courseCode = new TextField();
        TextField grade = new TextField();
        TextField semesterTaken = new TextField();
        transcriptPanel.add(new Label ("Enter couse code:"));
        transcriptPanel.add(courseCode);
        transcriptPanel.add(new Label ("Enter the course grade:"));
        transcriptPanel.add(grade);
        transcriptPanel.add(new Label ("Enter the semester taken:"));
        transcriptPanel.add(semesterTaken);

        
        JButton addButton= new JButton("Add");
        transcriptPanel.add(addButton);
        addButton.addActionListener(ev->
        {
            try
            {
                student.addToTranscript(courseCode.getText(),semesterTaken.getText(),grade.getText());
                JOptionPane.showMessageDialog(this,courseCode.getText() + " was added");
            }
            catch(CourseNotFoundException e){
            JOptionPane.showMessageDialog(this,courseCode.getText() +" not found");
            }
        });

        JButton removeButton= new JButton("Remove");
        transcriptPanel.add(removeButton);
        removeButton.addActionListener(ev->
        {
              try
            {
                student.removeFromTranscript(courseCode.getText(),semesterTaken.getText(),grade.getText());
                JOptionPane.showMessageDialog(this,courseCode.getText() + " was removed");
            }
            catch(CourseNotFoundException e){
                JOptionPane.showMessageDialog(this,courseCode.getText() +" not found");
            }
        });

        JButton changeGradeButton= new JButton("Change Grade");
        transcriptPanel.add(changeGradeButton);
        changeGradeButton.addActionListener(ev->
        {
            JOptionPane.showMessageDialog(this,"Change Grade");
        });

        JButton backButton = new JButton("Back");
        transcriptPanel.add(backButton);
        backButton.addActionListener(ev->
            {
                transcriptPanel.setVisible(false);
                setupUser();
                
            });

               
        add(transcriptPanel);
        transcriptPanel.setVisible(true);

    }

    private void changePOS()
    {
        JPanel posPanel = new JPanel();
        posPanel.setLayout(new BoxLayout(posPanel, BoxLayout.PAGE_AXIS));

        TextField courseCode = new TextField();
        posPanel.add(new Label ("Enter couse code:"));
        posPanel.add(courseCode);
        
        JButton addButton= new JButton("Add");
        posPanel.add(addButton);
        addButton.addActionListener(ev->
        {
            try
            {
                student.addToPOS(courseCode.getText());
                JOptionPane.showMessageDialog(this,courseCode.getText() + " was added");
            }
            catch(CourseNotFoundException e){
            JOptionPane.showMessageDialog(this,courseCode.getText() +" not found");
            }
        });

        JButton removeButton= new JButton("Remove");
        posPanel.add(removeButton);
        removeButton.addActionListener(ev->
        {
            try
            {
                student.removeFromPos(courseCode.getText());
                JOptionPane.showMessageDialog(this,courseCode.getText() + " was removed");
            }
            catch(CourseNotFoundException e){
                JOptionPane.showMessageDialog(this,courseCode.getText() +" not found");
            }
        });

        JButton backButton = new JButton("Back");
        posPanel.add(backButton);
        
        backButton.addActionListener(ev->
            {
                posPanel.setVisible(false);
                setupUser();
                
            });

               
        add(posPanel);
        posPanel.setVisible(true);


    }

    private void degreeChoice()
    {
        JPanel choosePanel = new JPanel();
        choosePanel.setLayout(new BoxLayout(choosePanel, BoxLayout.PAGE_AXIS));

        JButton bchButton = new JButton("BCH (Bcomp Honours)");
        choosePanel.add(bchButton);
        bchButton.addActionListener(ev->
            {
                //JOptionPane.showMessageDialog(this,"MAGIC");
                degreeName = "bch";
                choosePanel.setVisible(false);
                setupUser();
                
            });
            
        JButton bcgButton = new JButton("BCG (Bcomp General)");
        choosePanel.add(bcgButton);
        bcgButton.addActionListener(ev->
            {
                //JOptionPane.showMessageDialog(this,"MAGIC");
                degreeName = "bcg";
                choosePanel.setVisible(false);
                setupUser();
                
            });
        
        JButton backButton = new JButton("Back");
        choosePanel.add(backButton);
        backButton.addActionListener(ev->
            {
                choosePanel.setVisible(false);
                setupUser();
                
            });

               
        add(choosePanel);
        choosePanel.setVisible(true);

    }

    private void majorChoice()
    {
       
        JPanel choosePanel = new JPanel();
        choosePanel.setLayout(new BoxLayout(choosePanel, BoxLayout.PAGE_AXIS));

        if(degreeName.equals("bcg"))
        {
            JButton bcgButton = new JButton("BCG");
            choosePanel.add(bcgButton);
            bcgButton.addActionListener(ev->
            {
                //JOptionPane.showMessageDialog(this,"MAGIC");
                majorName = "bcg";
                choosePanel.setVisible(false);
                setupUser();
                
            });
            
        
        /*
        JButton backButton = new JButton("Back");
        choosePanel.add(backButton);
        backButton.addActionListener(ev->
            {
                choosePanel.setVisible(false);
                setupUser();
                
            });

        */
        add(choosePanel);
        choosePanel.setVisible(true);

        }
        
        else if(degreeName.equals("bch"))
        {
        
            JButton sengButton = new JButton("sEng");
            choosePanel.add(sengButton);
            sengButton.addActionListener(ev->
            {
                //JOptionPane.showMessageDialog(this,"MAGIC");
                majorName = "seng";
                choosePanel.setVisible(false);
                setupUser();
                
            });
            
            JButton csButton = new JButton("CS");
            choosePanel.add(csButton);
            csButton.addActionListener(ev->
            {
                //JOptionPane.showMessageDialog(this,"MAGIC");
                majorName = "cs";
                choosePanel.setVisible(false);
                setupUser();
                
            });
            /*
            JButton backButton = new JButton("Back");
            choosePanel.add(backButton);
            backButton.addActionListener(ev->
            {
                choosePanel.setVisible(false);
                setupUser();
                
            });
             */

            add(choosePanel);
            choosePanel.setVisible(true);
        }

    
        else
        {
            JOptionPane.showMessageDialog(this,"ERROR: choose a degree first.");
            choosePanel.setVisible(false);
            setupUser();

            add(choosePanel);
            choosePanel.setVisible(true);

        }

    }
    
    private void setupAdmin()
    {
        
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.PAGE_AXIS));
        
        JButton adminButton = new JButton("Connect to Database");
        adminPanel.add(adminButton);
        adminButton.addActionListener(ev->
            {
                //JOptionPane.showMessageDialog(this,"MAGIC");
                adminPanel.setVisible(false);
                setupAdminMaintain();
                //setupCollectionView();
            });
            
            
        JButton backButton = new JButton("Back");
        adminPanel.add(backButton);
        
        backButton.addActionListener(ev->
            {
                adminPanel.setVisible(false);
                choiceStory();
                
            });
               
        add(adminPanel);
        adminPanel.setVisible(true);
        //pack();
        //revalidate();
    }

     private void setupAdminMaintain()
    {
       
         //maintain the list of degrees in the database (add/remove/change) 
         //maintain the list of courses in the database(add/remove/change)
        JPanel addThings = new JPanel();
        addThings.setLayout(new BorderLayout());

        //>WEST
        JPanel degreePanel = new JPanel();
        degreePanel.setLayout(new BoxLayout(degreePanel, BoxLayout.PAGE_AXIS));
        
        TextField degreeName = new TextField();
        degreePanel.add(new Label ("Enter degree name:"));
        degreePanel.add(degreeName);

        JButton addButtonD = new JButton("Add");
        degreePanel.add(addButtonD);
        JButton removeButtonD = new JButton("Remove");
        degreePanel.add(removeButtonD);
        
        //>EAST
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.PAGE_AXIS));
        
        TextField courseName = new TextField();
        coursePanel.add(new Label ("Enter course name:"));
        coursePanel.add(courseName);

        JButton addButtonC = new JButton("Add");
        coursePanel.add(addButtonC);
        JButton removeButtonC = new JButton("Remove");
        coursePanel.add(removeButtonC);
            

        addThings.add(degreePanel, BorderLayout.WEST);
        addThings.add(coursePanel, BorderLayout.EAST);


        //
        JButton backButton = new JButton("Back");
        addThings.add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(ev->
            {
                addThings.setVisible(false);
                setupAdmin();
                
            });

        add(addThings);
        addThings.setVisible(true);
        //pack();
        //revalidate();
    }
          
    
    private void setupAddToPanel()
    {
        JPanel addThings = new JPanel();
        addThings.setLayout(new BoxLayout(addThings, BoxLayout.PAGE_AXIS));
        addThings.add(new Label ("StringVariable data"));
        addThings.add(stringAdd);
        addThings.add(new Label ("NumberVariable data"));
        addThings.add(numberAdd);
        JButton submitButton = new JButton("Submit New Item");
        addThings.add(submitButton);
        add(addThings);
        //pack();
        //revalidate();
    }
       
    private void setupCollectionView()
    {
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JButton numSort = new JButton("Sort Numerically");
        numSort.addActionListener(this);
        buttonPanel.add(numSort);
        JButton alphaSort = new JButton("Sort Alphabetically");
        alphaSort.addActionListener(this);
        buttonPanel.add(alphaSort);
        add(buttonPanel);
        collectionView = new TextArea(15,40);
        collectionView.setEditable(false);
        collectionView.setText(numSort.getText()); //changed dat
        viewPanel.add(collectionView);
        add(viewPanel);
        //viewPanel.setVisible(false);//
        //pack();
        //revalidate();
        
    }
    
    private void setupMenubar(){
         /*Add a menubar*/
        
        //add menu bar 
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // add menu
        JMenu file = new JMenu ("File");
        menuBar.add(file);
        //add Open item in a menu
        JMenuItem open = new JMenuItem("Home");
        file.add(open);
        open.addActionListener(ev->
            {
                JOptionPane.showMessageDialog(this,":)");
                //choiceStory();
            });
        //add Quit item in a menu
        JMenuItem quit = new JMenuItem("Quit");
        file.add(quit);
        quit.addActionListener(ev->
            {
                System.exit(0);
            });

    }
    
    public static void main(String[] args)
    {
        Planner runMe = new Planner();
        runMe.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String choice = ae.getActionCommand();
        
        
        if(choice.equals("Sort Numerically") )
        {
           collectionView.setText("Car Name:" + stringAdd.getText() );
        }      
        if(choice.equals("Sort Alphabetically") )
        {
           
           collectionView.setText("Car mileage:" + numberAdd.getText() );
        }            
        
    }
}
    

