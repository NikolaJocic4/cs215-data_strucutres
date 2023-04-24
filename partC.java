import java.io.IOException;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class partC
{
    // Variables
    String[] arr;
    int nItems;
    int temp;
    String personKilled=" ";    //Determines what person has been killed
    /**
     * Constructor for objects of class OrdArray
     */
    public partC(int size)
    {
        arr = new String[size];
        nItems = 0;
        temp=0;
    }
    
    private boolean isEmpty()
    {
        return ( nItems == 0 );
    }
    
    private boolean isFull()
    {
         return ( nItems == arr.length );   
    }
    
    //Regular insert method that inserts a desired item into an array
    public void insert(String item)
    {
        if ( isFull() )
        {
            System.out.println("ARRAY IS FULL! OPERATION ABORTED!");
        }
        else
        {
            arr[nItems] = item;
             nItems++;
        }
    }
    
    public void fillRandom()
    {
             //The code that fills the array with random names based on the text file contained in the folder of the Java package
             for (int i = 0; i < arr.length; i++)
             {
                 String line = null;
                    try {
                        line = Files.readAllLines(Paths.get("Names.txt")).get((int) (Math.random() * 252 + 0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                insert(line); 
             }
         
    }
    
    public int find(String item)
    {
        // For each index in the array
        for (int i = 0; i < nItems ; i++)
        {
            // Check if the 'item' is at the specified index
            if ( arr[ i ] == item )
            {
                // We found it!
                return i;
            }
        }            
        // We chaecked all the array and the 'item' is not there!
        return -1;
    }
    
    public void delete(String item)
    {
        if ( isEmpty() )
        {
            System.out.println("ARRAY EMPTY! OPERATION ABORTED!");
        }
        else 
        {
             // STEP 1: Call the find method to check if 'item' is in the array
             // STEP 2a: If the 'item' is not found, abort process
             // STEP 2b: If the 'item' is found at index k, proceed to next step
             // STEP 3: Move all items that are at indices k+1, k+2, etc. one position to the left k, K+1, etc....
             // STEP 4: Decrement nItems
            int k = find( item );
         
            if ( k == -1 )
            {
                 System.out.println("ERROR! " + item + " was not found in the array!");
            }
            else
            {
                for (int i = k; i < nItems-1; i++)
                {
                    arr[i] = arr[i+1];
                }
                nItems--;
            }
        }
    }
    
    
    //Method for determening where 'Kitsos' is the safest at
    public int safeSpot(int suicideNumber)
    {
        //Copies the contents of the original array into a newly created array 
        String arr2[]=new String[arr.length];
        for(int i=0; i<arr.length; i++)
        {
            arr2[i]=arr[i];
        }
        
        String solution;    //stores the solution value
        int position =0;    //determines the position of the item to be deleted
        int count=1;        //counts the number of times after which a person should commit suicide
        
        //Do while loop that goes through the loop deleting appropriate items until the array is left with one value
        do
        {
            if(count%suicideNumber==0)
            {
                delete(arr[position]);
                
                //moves the postion back one spot to maintain continuity
                position--;
                
                count++;
                position++;
                if(position+1>nItems)
                {
                    position=0;
                }
            }
            else
            {
                count++;
                position++;
                
                //If the position goes to the end of the loop, its value gets reset to 0 so that it accomodates the counting of the next suicide in circle
                if(position+1>nItems)
                {
                    position=0;
                }
            }
        }
        while (nItems!=1);
        
        //Storing the number left as 'surviving' into a variable and then deleting the whole array
        solution=arr[0];
        delete(arr[0]);
        
        //Refilling the array with the original values it had using the 'copy' array
        for(int i=0; i<arr2.length; i++)
        {
            if(isFull())
            {
                System.out.println("The array is full");
            }
            else
            {
                insert(arr2[i]);
            }
        }
        
        
        temp=find(solution); //Stores the position of the safest spot inside temp to be used later when the loop is being run again
        kitsos(suicideNumber); //starts a new loop-method that displays the progress throughout the loop with 'Kitsos' inside
        
        //returns a variable that stores temporary value
        return temp;
        
    }
    
    public void kitsos(int suicideNumber)
    {
        //Put 'Kitsos' to be at the safest spot inside the array that was previously stored in temp
        arr[temp]="Kitsos";
        int position =0;
        int count=1;
        
        
        //Loop runs 2nd time to display 'Kitsos' progress
        do
        {
            if(count%suicideNumber==0)
            {
                personKilled=arr[position];
                delete(arr[position]);
                
                position--;
                
                count++;
                position++;
                if(position+1>nItems)
                {
                    position=0;
                }
            }
            else
            {
                count++;
                position++;
                if(position+1>nItems)
                {
                    position=0;
                }
            }
        }
        while (nItems!=1);
        
        //Reseting the value of personKilled so that 'The people' only shows up as the title
        personKilled=" ";
        
    }
    
}
