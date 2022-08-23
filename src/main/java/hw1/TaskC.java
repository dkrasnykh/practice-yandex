package hw1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String newFullNumber = in.nextLine().replaceAll("[+()-]", "");

        List<String> fullNumbers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fullNumbers.add(in.nextLine().replaceAll("[+()-]", ""));
        }
        String newCode = getCode(newFullNumber);
        String newNumber = getNumber(newFullNumber);

        for(String fullNumber: fullNumbers){
            if(newCode.equals(getCode(fullNumber)) && newNumber.equals(getNumber(fullNumber))){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    public static String getCode(String fullNumber) {
        return (fullNumber.length() == 7) ? "495" : fullNumber.substring(1, 4);
    }
    public static String getNumber(String fullNumber) {
        return (fullNumber.length() == 7) ? fullNumber : fullNumber.substring(4) ;
    }
}
