/************************************************************************************
* Omar Ramirez
* CSC 103 - Project 2 
* UnboundedInt class uses Linked List to store numbers larger than int and long.
*************************************************************************************/

import java.util.*;

public class UnboundedInt implements Cloneable {

   // Invariant of the UnboundedInt class:
   //   1. The number of nodes in the sequence is in the instance variable 
   //      manyNodes.
   //   2. The link to the front of the Linked List is the instance variable front. 
   //   3. The link to the back of the Linked List is the instance variable back.
   //   4. The link to the current element within the Linked List is the instance 
   //      variable cursor.    
   private int manyNodes;
   private IntNode front; 
   private IntNode back;
   private IntNode cursor;
   
   /**
   * Initialize an empty sequence with a specified String that
   * will be divided into sections of 3. The sections will then 
   * be placed into IntNodes in reverse order. 
   * <dt><b>Param number:</b><dd>
   *   the string to be passed in as a large number. 
   * <dt><b>Postcondition:</b><dd>
   *   This sequence has taken the String input and placed into
   *   IntNodes that will range between 0 and 999.
   **/   
   public UnboundedInt(String number){
      String value; 
      int parts, length, remainder, counter = 0;
      
      length = number.length();                 //finds the length of the String passed in 
      remainder = (length % 3);                 //and finds the remainder if it were divided by 3
      if(remainder == 1 || remainder == 2){
         value = number.substring(counter, remainder);   //the remainder will be used to find the 
         parts = Integer.parseInt(value);                //the value of the first IntNode 
         counter = remainder;      
      } else { 
         value = number.substring(counter, 3);
         parts = Integer.parseInt(value);
         counter = 3;
      }
      
      //creating the first IntNode
      front = new IntNode(parts, null);      
      back = front;                          
      manyNodes++;
      
      //creates more IntNodes and places them in front of
      //the previous IntNode to get the String number in a reverse order
      for(int i = counter; i <= length; i+=3){
         if(counter != length){
            counter += 3;
            value = number.substring(i, counter);
            parts = Integer.parseInt(value);
            front = new IntNode(parts, front);          
         }
         manyNodes++;
      }   
   }
   
   /**
   * Adds the current UnboundedInt with a passed in UnboundedInt,
   * and returns a new UnboundedInt with a value of the sum of 
   * the two UnboundedInts.
   * <dt><b>Param addend:</b><dd>
   *   The UnboundedInt that will be added with the current UnboundedInt.
   * <dt><b>Postcondition:</b><dd>
   *   A new UnboundedInt object that will store the value of the sum
   *   of the two UnboundedInts that were added together.
   **/
   public UnboundedInt add(UnboundedInt addend){
      String answer = "";
      int sum = 0, remainder = 0;
      //place cursor of both UnboundedInt at front        
      start();
      addend.start();
      
      while(cursor != null && addend.cursor != null){          //while there are remaining IntNodes in both UnboundedInts,
         sum = cursor.getData() + addend.cursor.getData();     //the variable sum will be the IntNodes added together 
         if(remainder != 0){                                   //as well as the remainder if there's one.
            sum = remainder + sum;
            remainder = 0;           
         }
         if(sum > 999){                                        //determines whether or not there is a remainder
            remainder = sum / 1000;                            //by checking if size of sum is greater than 999
            sum = sum % 1000;
            answer = String.format("%03d",sum) + answer;
            
         } else {
            answer = String.format("%03d",sum) + answer;  
         }                  
         advance();                                            //advances both UnboundedInts to next IntNode
         addend.advance();
      }
      
      while(cursor != null || addend.cursor != null || remainder != 0){    //For the scenarios that one UnboundedInt is much larger than the other,
         if(remainder != 0){                                               //or there is a remainder that will cause a new IntNode.
            if(cursor == null && addend.cursor == null){
               answer = Integer.toString(remainder) + answer;              //if there is a remainder but both IntNodes in UnboundedInts are null
               remainder = 0;                                              //it will add the value of remainder to the front.
          
            } else if (cursor != null){                                    //if there is a remainder and one UnboundedInt has IntNodes left
               sum = cursor.getData() + remainder;                         //it will add the IntNode and the remainder 
               answer = String.format("%03d",sum) + answer;
               remainder = 0;
            } else if (addend.cursor != null){
               sum = addend.cursor.getData() + remainder;
               answer = String.format("%03d",sum) + answer;
               remainder = 0;
            }
               
         } else if (remainder == 0){                        //if there is no remainder but either UnboundedInt has IntNodes left 
            if(cursor != null){                             //the IntNodes will be added to the answer
               sum = cursor.getData();                      //and will advance until for UnboundedInts are at tail location
               answer = String.format("%03d",sum) + answer;
               advance();                   
            } else if (addend.cursor != null){
               sum = addend.cursor.getData();
               answer = String.format("%03d",sum) + answer;
               addend.advance();
            }
         }
      } 
      UnboundedInt unboundSum = new UnboundedInt(answer);  
      return unboundSum;
   }
   
   /**
   * Multiplies the current UnboundedInt with a passed in UnboundedInt,
   * and returns a new UnboundedInt with a value of the product of 
   * the two UnboundedInts.
   * <dt><b>Param addend:</b><dd>
   *   The UnboundedInt that will be multiplied with the current UnboundedInt.
   * <dt><b>Postcondition:</b><dd>
   *   A new UnboundedInt object that will store the value of the product
   *   of the two UnboundedInts that were multiplied together.
   **/
   public UnboundedInt multiply(UnboundedInt factor){         
      String answer = "";
      int value = 0, value2 = 0, product = 0, product2 = 0;
      int remainder = 0, remainder2 = 0, counter = 0;

      for(factor.start();factor.cursor != null; factor.advance()){      
         value = factor.cursor.getData();
         
         for(start(); cursor != null; advance()){     //for loop is meant to multiply all the numbes in the current object
            value2 = cursor.getData();                //by the same number in the passed in object. 
            if(counter == 0){
               product = value * value2;
               
               if(product > 999){                     //get remainder if product is larger than 3 places
                  remainder = product/1000;
                  product = product%1000;
                  answer = String.format("%03d", product) + answer;
               } else {
                  answer = String.format("%03d", product) + answer;
               }
            } else if(counter > 1){                                     //attempt to multiply the next number in the sequence and 
               product2 = (value * value2) + remainder + product; 
                                                                       //find the sum of the values multiplied plus the remainders
               if(product2 > 999){
                  remainder2 = product/1000;
                  product2 = product%1000;
                  answer = String.format("%03d", product2) + answer;
               } else {
                  answer = String.format("%03d", product2) + answer;
               }
            }           
            counter++;
         }
      }
      
      UnboundedInt unboundProduct = new UnboundedInt(answer);
      return unboundProduct;
   }
                       
   /**
   * Generate a copy of this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Return:</b><dd>
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public UnboundedInt clone(){
      
      UnboundedInt cloned;
      
      try{
         cloned = (UnboundedInt) super.clone();
      } catch (CloneNotSupportedException e){
         // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException ("This class does not implement cloneable.");
      }      
      cloned.front = IntNode.listCopy(front);
            
      return cloned;
   }
    
   /**
   *  Method that returns true if linked list represents the same 
   *  numberical number as the input parameter. False otherwise, overrides 
   *  method in Object class. 
   * <dt><b>Param - Object obj:</b><dd>
   *   A sequence to compare to our linked list sequence
   * <dt><b>Return:</b><dd> 
   *   Boolean expression if sequence is the same numerical number as the input parameter.
   **/
   public boolean equals(Object obj){
   
		if (obj instanceof UnboundedInt){
			UnboundedInt candidate = (UnboundedInt) obj;                                     //boolean will only return true if both objects        
			if (candidate.manyNodes == this.manyNodes){                                      //have the same value of ManyNodes
            IntNode temp = candidate.front;
            for(IntNode temp2 = this.front; temp2 != null; temp2 = temp2.getLink()){      //and if each node has the same numberical value
               if(temp.getData() != temp2.getData()){
                  return false;
               }
               temp = temp.getLink();
            }
            return true;
         }
      }
      return false;      
   }

   /**
   * Set the cursor element at the front of this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/ 
   public void start(){
      cursor = front;
   }
   
   /**
   * Move forward, so that the cursor element is now the next element in
   * this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Postcondition:</b><dd> 
   *   The new element is the element immediately after the 
   *   original current element.
   * <dt><b>Exception IllegalStateException:</b><dd>
   *   Indicates sequence is empty.
   **/
   public void advance(){  
      if(front == null){
         throw new IllegalStateException("The sequence is empty.");
      }    
      cursor = cursor.getLink();
   }
   
   /**
   * Creates a string of all elements in order separated by a comma.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *    All the elements in this sequence will be printed with a comma in between.
   * <dt><b>Exception IllegalStateException:</b><dd>
   *    Indicates sequence is empty.
   **/              
   public String toString(){
      if(front == null){
         throw new IllegalStateException("The sequence is empty.");
      }
      String answer = "";
      for(start(); cursor != null; advance()){        //for loop to get each node.
         if(front == back){                           //if there is only one node, it will return the node
            answer = "" + cursor.getData();
            return answer;
         } 
         if (cursor == back){                          //since the back of the linked list is the first number
            answer = cursor.getData() + answer;        //there's no need to format it or add commas
         } else {
            answer = "," + String.format("%03d",cursor.getData()) + answer; 
         }  
      } 
      return answer;
   } 
        
}//end of class UnboundedInt