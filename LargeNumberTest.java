/******************************************************************************
* Omar Ramirez
* Project 2
* LargeNumberTester will be the testing class for the UnboundedInt class.
*******************************************************************************/

import java.util.*;

class LargeNumberTester {

   public static void main(String[] args){
      boolean options = true;
      Scanner input = new Scanner(System.in);
      System.out.println("Welcome to my testing program for the UnboundedInt class.");
      
      System.out.println("Please enter (without commas) your first very large number.");           
      String largeNumber = input.nextLine();
      
      System.out.println("Please enter (without commas) your second very large number.");           
      String largeNumber2 = input.nextLine();
      
      //creating new objects UnboundedInt and passing in the Strings
      //for the constructor to put it into linked list nodes
      UnboundedInt num = new UnboundedInt(largeNumber);        
      UnboundedInt num2 = new UnboundedInt(largeNumber2);
      
      //menu will repeat unless user ends it
      //by entering 7 when prompted   
      while (options == true){                  
         int menuOption = getMenuOption();       
         if(menuOption == 7) {                  
            options = false;                    
         }
         //switch to run each option in the menu   
         switch(menuOption) { 
         
            case 1:  displayBothNumbers(num, num2);
                     break;
                     
            case 2:  System.out.println("Please enter (without commas) your first very large number.");
                     String newNum = input.nextLine();
                     System.out.println("Please enter (without commas) your second very large number.");
                     String newNum2 = input.nextLine();
                     
                     num = new UnboundedInt(newNum);
                     num2 = new UnboundedInt(newNum2);
                     break;
                                                                                   
            case 3:  numbersEqualCheck(num, num2);
                     break;
                     
            case 4:  addingNumbers(num, num2);
                     break;
            
            case 5:  reportMultiplication(num, num2);
                     break;
            
            case 6:  creatingClone(num);
                     break; 
                                
            }             
      }//end of while loop
           
      System.out.println("Program has ended. Thank you for testing it."); 
   
   }//end of main method
    
   public static int getMenuOption(){
      Scanner menuInput = new Scanner(System.in);        //getMenuOption ensures user will never be able to crash program 
      boolean flag = true;                               //by only accepting integer numbers in the correct menu range. 
      int number = -1;
      while(flag==true){                                 //otherwise loop will continue prompting user to enter input 
         printMenu();          
         try{                         
            number = menuInput.nextInt();
            if (number < 1 || number > 7) {
               throw new Exception("Not an option. Please enter a menu option.");
            }
            flag = false;   
         } catch (InputMismatchException e) { 
            System.out.println("Invalid input. Please enter a menu option.");
            menuInput.nextLine();
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
      return number; //returns the menuOption
   } 
      
   public static void displayBothNumbers(UnboundedInt num, UnboundedInt num2){   
      System.out.println("Displaying your big numbers.");
      //displaying both numbers by using toStrings 
      System.out.println("num: " + num.toString());
      System.out.println("num2: " + num2.toString());
   }      
    
   public static void numbersEqualCheck(UnboundedInt num, UnboundedInt num2){
      System.out.println("Determining whether numbers are equal .");
      //calling equals method
      if(num.equals(num2)){
         System.out.println("Numbers are indeed the same.");
      } else {
         System.out.println("Numbers are not equal.");
      }
   }
   
   public static void addingNumbers(UnboundedInt num, UnboundedInt num2){
      System.out.println("Adding the big numbers..."); 
      //calling the add method 
      System.out.println("The sum of the big numbers is: " + num.add(num2));
   }
   
   public static void reportMultiplication(UnboundedInt num, UnboundedInt num2){
      System.out.println("Multiplying the big numbers...");
      System.out.println("The product of the big numbers is:\n" + num.multiply(num2));
   }
   
   public static void creatingClone(UnboundedInt num){
      System.out.println("Creating clone of first number..."); 
      //calling the clone method to create clone       
      UnboundedInt cloned = num.clone();
      String clone = cloned + "";
      clone = clone.replaceFirst(",", "");
      System.out.println("The clone of the first number: " + clone);
   }
    
   public static void printMenu(){
      System.out.println();
      System.out.println("Choose an option:");
      System.out.println("1. Display both numbers..");    
      System.out.println("2. Input 2 new numbers.");     
      System.out.println("3. Check if numbers are equal.");    
      System.out.println("4. Report the sum of the two numbers");  
      System.out.println("5. Report the multiplication of the two numbers.");    
      System.out.println("6. Create and output clone of first number.");    
      System.out.println("7. Quit");              
   }
}
   