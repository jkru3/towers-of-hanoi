// "Joey" Joseph Krueger
// 05/23/2022
// CS145
//
//

import java.util.ArrayList;
import java.util.*;
import java.util.Stack;

public class JKtowersOfHanoi
{
    public enum rod
    {
        A,
        B,
        C;
    }
    
    public static void main(String[] args)
    {
        int intRing = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("\nHow many rings would you like to test? (Max 15): ");
        while(intRing < 1 || intRing > 15)
        {
            String stringRing = input.nextLine();
            try
            {
                intRing = Integer.parseInt(stringRing);
                if(intRing < 1 || intRing > 15)
                    System.out.print("That is not a valid number: ");
            } catch(Exception e) {
                System.out.println("Please enter a number between 1 and 15: ");
            }
        }
        
        char l__ = 'A'; // this rod is always the start
        char _l_ = 'B'; // this rod is always the middle auxilery rod
        char __l = 'C'; // this rod is always the end
        tower(intRing, l__, _l_, __l);
        System.out.println("done!");
    }
    
    static void tower(int intRing, char l__, char _l_, char __l)
    {
        if (intRing == 1)
        {
            //this prints every other since ring 1 must be moved every other turn
            System.out.println("Move ring 1: " +  l__ + " -> " + __l);
        } else {
            //notice how this recursion mixes up the B and C rods
            //on even ring counts, the top ring is sent to the middle auxilery rod first
            //on odd ring counts, the top ring is sent to the end rod
            tower(intRing - 1, l__, __l, _l_);
            
            //
            System.out.println("Move ring " + intRing +
                               ": " + l__ + " -> " + __l);
            tower(intRing - 1, _l_, l__, __l);
        }
    }
}
