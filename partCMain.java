import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math;
/**
 * Kitsos and the Suicides.
 * 
 * Some time ago in a small Greek village, the inhabitants decided that there was no escape from the Romans, 
which were a threat that was nearing them. The inhabitants settled on committing suicide, except one person, 
Kitsos. He instructed that the inhabitants should at least commit their suicide in a fashionable manner, 
so he suggested that they do them in an interval (Every second, third, etc..). 

This program aims to, through a carefully constructed algorithm, determine the best position, 
Kitsos should place himself at, so that he is the last man standing, meaning there will not be anybody 
to witness him not commit a suicide.
 * 
 *
 * @Nikola Jocic Student ID: 20200041 email: 20200041@student.act.edu
 * @version 1.01 Date: 23/11/2021
 */ 
public class partCMain extends JFrame implements ActionListener
{
    //Variables
    int size;
    int kitsos;
    partC array ;
    
    //The front menu text boxes
    JTextField numberOfPeople;
    JTextField suicideInterval;
    
    //Creating the labels above the text boxes
    JLabel numberPeople;
    JLabel suicides;
    
    //The button to submit the entries
    JButton submit;
    
    //Creating the labels that will represent the people as well as the array 'people' in which they will later be stored
    JLabel person0;
    JLabel person1;
    JLabel person2;
    JLabel person3;
    JLabel person4;
    JLabel person5;
    JLabel person6;
    JLabel person7;
    JLabel person8;
    JLabel person9;
    JLabel[] people;
    
    //Creating the panels
    JPanel panel_numberPeople;
    JPanel panel_suicideInterval;
    JPanel panel_submit;
    JPanel panel_everything;
    JPanel panel_parameters;
    JPanel labels;
    
    public partCMain()
    {
        setTitle("Kitsos"); //Setting title
        
        //Defining the text fields
        numberOfPeople = new JTextField(15);
        suicideInterval = new JTextField(15);
        
        //Defining the labels aboce the text fields and giving them value
        numberPeople = new JLabel("Number of people (Maximum 10): ");
        suicides = new JLabel("Suicide interval: ");
        
        //Series of labels used as people
        person0= new JLabel(" ");
        person1= new JLabel(" ");
        person2= new JLabel(" ");
        person3= new JLabel(" ");
        person4= new JLabel(" ");
        person5= new JLabel(" ");
        person6= new JLabel(" ");
        person7= new JLabel(" ");
        person8= new JLabel(" ");
        person9= new JLabel(" ");
        people = new JLabel [10];
        
        //Putting labels according to their index into the correct position of the array
        people[0]= person0;
        people[1]= person1;
        people[2]= person2;
        people[3]= person3;
        people[4]= person4;
        people[5]= person5;
        people[6]= person6;
        people[7]= person7;
        people[8]= person8;
        people[9]= person9;
        
        //Putting the text onto the submit button
        submit=new JButton("Submit");
        
        //Defining the panels
        panel_numberPeople = new JPanel();
        panel_suicideInterval = new JPanel();
        panel_submit = new JPanel();
        panel_everything = new JPanel();
        panel_parameters = new JPanel();
        labels= new JPanel();
        
        //Adding the text box and label related to the number of people inside of one panel
        panel_numberPeople.add(numberPeople);
        panel_numberPeople.add(numberOfPeople);
        
        //Adding the interval of the suicides text box and label to one shared panel
        panel_suicideInterval.add(suicides);
        panel_suicideInterval.add(suicideInterval);
        
        //Joining the panel for number of people and suicides together in a box layout panel
        panel_parameters.setLayout(new BoxLayout(panel_parameters, BoxLayout.X_AXIS));
        panel_parameters.add(panel_numberPeople);
        panel_parameters.add(panel_suicideInterval);
        
        //Putting the button submit into one panel
        panel_submit.setLayout(new FlowLayout());
        panel_submit.add(submit);
        
        //Adding all the labels into the one 'labels' panel
        labels.setLayout(new FlowLayout());
        labels.add(person0);
        labels.add(person1);
        labels.add(person2);
        labels.add(person3);
        labels.add(person4);
        labels.add(person5);
        labels.add(person6);
        labels.add(person7);
        labels.add(person8);
        labels.add(person9);
        //Setting the layout of the panel to border and naming it 'People'
        labels.setBorder(BorderFactory.createTitledBorder("People"));
        
        
        //Joining the three big panels (panel_parameters, panel_submit, labels) together and organising them by a vertical Y axis inside of a BoxLayout panel
        panel_everything.setLayout(new BoxLayout(panel_everything, BoxLayout.Y_AXIS));
        panel_everything.add(panel_parameters);
        panel_everything.add(submit);
        panel_everything.add(labels);
        
        //Making every label invisible
        for (int i=0; i<10; i++)
        {
            people[i].setVisible(false);
        }
        
        //Putting everything on content pane
        getContentPane().add(panel_everything);
        
        //Making action alert and putting in on 'submit' button
        submit.addActionListener(this);
        submit.setActionCommand("Submit");
        
    }
    
    //Action performed
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Submit"))
        {
            //Making the array, initializing it and taking the information from the text boxes to fill it
            size = Integer.parseInt(numberOfPeople.getText()); 
            partC array = new partC(size);
            array.fillRandom();
            
            //Putting the spot where kitsos is safe inside a variable
            kitsos=array.safeSpot(Integer.parseInt(suicideInterval.getText()));
            
            //Marking the label with "Kitsos" so that it is later detected as safe spot
            people[kitsos].setText("Kitsos");
            
            //Loop that 'marks' all 'unsafe' spots as dead, living out only the label that displays "Kitsos"
            for (int i=0; i<size; i++)
            {
                if(people[i].getText()=="Kitsos")
                {
                    people[kitsos].setText("Kitsos");
                    people[i].setVisible(true);
                }
                else
                {
                    people[i].setText("dead");
                    people[i].setVisible(true);
                }
            }
            
            //Setting the submit button to false
            submit.setEnabled(false);
        }
    }
    
    //Main method
    public static void main(String[] args)
    {
        // Create a JFrame Object and show it on the screen
        partCMain frame = new partCMain();
        
        // Create a WindowQuitter Object
        //System.exit(0);    

        // Set the proper size for the frame
        frame.setSize(600,350);
        
        // Prevent the user from resizing the frame
        frame.setResizable(false);
        
        // Make myframe visible
        frame.setVisible(true);
    }
}

