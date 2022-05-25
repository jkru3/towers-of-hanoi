// "Joey" Joseph Krueger
// 05/23/2022
// CS145
//
//

import java.util.*;

public class JKtowersOfHanoi
{
    public enum rod
    {
        A(a), B(b), C(c);
    
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
    
    public static Stack<Boolean> a = new Stack<>();
    public static Stack<Boolean> b = new Stack<>();
    public static Stack<Boolean> c = new Stack<>();
    public static Stack aRef = a;
    public static Stack bRef = b;
    public static Stack cRef = c;
    
    public static void main(String[] args)
    {
        int intRing = menu();
        for(int i = intRing; i >= 1; i--)
            a.push(true);
        rod start = rod.A;
        rod transfer = rod.B;
        rod destination = rod.C;
        tower(intRing, start, transfer, destination, a, b, c);
    }
    
    static void tower(int intRing,
                      rod start,
                      rod transfer,
                      rod destination,
                      Stack a,
                      Stack b,
                      Stack c)
    {
        if (intRing == 1)
        {
            destination.enumPush(start.enumPop());
            System.out.println("Move ring 1: " +  start + " -> " + destination +
                               "\t\t[" + aRef.size() + "] [" + bRef.size() + "] [" + cRef.size() + "]");
        } else {
            tower(intRing - 1,
                  start,
                  destination,
                  transfer,
                  a, c, b);
    
            destination.enumPush(start.enumPop());
            System.out.println("Move ring " + intRing + ": " +
                                start + " -> " + destination +
                               "\t\t[" + aRef.size() + "] [" + bRef.size() + "] [" + cRef.size() + "]");
            
            tower(intRing - 1,
                  transfer,
                  start,
                  destination,
                  b, a, c);
        }
    }
    
    public static int menu()
    {
        int intRing = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("How many rings would you like to test? (Max 15): ");
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
        return intRing;
    }
}
