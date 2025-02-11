import java.util.*;

public class StringReversing { 
    public static void main(String[] args) {  
        Scanner scan = new Scanner(System.in);  
        System.out.print("Write a string: ");
        String userInput = scan.nextLine(); 
        // This line calls the method reverseString that reverses the string that the user inputted
        String reverseOutput = reverseString(userInput);  
        System.out.println(reverseOutput); 
    }

    // The method below reverses the string that the user inputted
    public static String reverseString(String userInput) {
        char[] letters = new char[userInput.length()]; 
        
        // Characters that were inputted in user's string are added to the array
        for (int i = 0; i < userInput.length(); i++) {
            letters[i] = userInput.charAt(i); 
        }

        // Each character before the current i value is switched
        for (int i = 0; i < userInput.length() / 2; i++) {
            char character = letters[i];
            letters[i] = letters[userInput.length() - 1 - i];
            letters[userInput.length() - 1 - i] = character;
        }

        // Character array that got reversed is returned as a string 
        return new String(letters); 
    }
}
