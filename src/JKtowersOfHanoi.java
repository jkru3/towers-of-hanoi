// "Joey" Joseph Krueger
// 05/23/2022
// CS145
//
// This program solved the Towers of Hanoi problem with up to 15 rings.
// I used additional features like Stacks, enum, and try/catch statements.
//

import java.util.*;

public class JKtowersOfHanoi
{
    //rather than make a separate class, I chose to use an enum
    //this made it easy to paste rod 'A', 'B' and 'C' in println statements
    //to be honest, I'm not sure if this was a good idea
    private enum rod
    {
        A(rodOne), B(rodTwo), C(rodThree);
    
        private Stack<Boolean> anyStack;
        
        rod(Stack<Boolean> anyStack)
        {
            this.anyStack = anyStack;
        }
        
        public void enumPush(boolean pop)
        {
            anyStack.push(pop);
        }
    
        private Boolean enumPop()
        {
            return anyStack.pop();
        }
    
    }
    
    //notice; referring to the numerical type "rodOne" always refers to the stack itself
    //not the object that get's passed around by the algorithm
    //this is useful for finding the size of each rod
    private static final int HARD_LIMIT = 15;
    private static Stack<Boolean> rodOne = new Stack<>();
    private static Stack<Boolean> rodTwo = new Stack<>();
    private static Stack<Boolean> rodThree = new Stack<>();
    
    public static void main(String[] args)
    {
        int intRing = menu();
        for(int i = intRing; i >= 1; i--)
            rodOne.push(true);
        rod start = rod.A;
        rod transfer = rod.B;
        rod destination = rod.C;
        tower(intRing, start, transfer, destination);
        System.out.println("[" + rodOne.size() + "] [" + rodTwo.size() + "] [" + rodThree.size() + "]");
    }
    
    //this is the algorithm
    static void tower(int intRing,
                      rod start,
                      rod transfer,
                      rod destination)
    {
        if (intRing == 1)
        {
            System.out.println("[" + rodOne.size() + "] [" + rodTwo.size() + "] [" + rodThree.size() + "]" +
                               "\t\tMove ring 1: " +  start + " -> " + destination);
            destination.enumPush(start.enumPop());
            
        } else {
            tower(intRing - 1,
                  start,
                  destination,  //notice how the order changes here
                  transfer);
            
            System.out.println("[" + rodOne.size() + "] [" + rodTwo.size() + "] [" + rodThree.size() + "]" +
                               "\t\tMove ring " + intRing + ": " +
                                start + " -> " + destination);
            destination.enumPush(start.enumPop());
            
            tower(intRing - 1,
                  transfer,
                  start,        //again the order changes
                  destination);
        }
    } //end of tower method
    
    public static int menu()
    {
        int intRing = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("How many rings would you like to test? (Max " + HARD_LIMIT + "): ");
        while(intRing < 1 || intRing > HARD_LIMIT)
        {
            String stringRing = input.nextLine();
            try
            {
                intRing = Integer.parseInt(stringRing);
                if(intRing < 1 || intRing > HARD_LIMIT)
                    System.out.print("That is not a valid number: ");
            } catch(Exception e) {
                System.out.println("Please enter a number between 1 and " + HARD_LIMIT + ": ");
            }
        }
        return intRing;
    }
}
