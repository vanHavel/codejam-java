import java.util.Scanner;
    
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        for (int testCase = 0; testCase < t; ++testCase) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int n = sc.nextInt();
            boolean done = false;
            while(!done) {
                int mid = a + (b - a) / 2;
                System.out.println(mid);
                String response = sc.next();
                if (response.equals("CORRECT")) {
                    done = true;
                }
                else if (response.equals("TOO_SMALL")) {
                    a = mid;
                }
                else if (response.equals("TOO_BIG")) {
                    b = mid;
                }
                else {
                    System.exit(0);
                }
            }
        }
    }

}