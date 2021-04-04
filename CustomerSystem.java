
/*
 * Date: March 31, 2021
 * Names: Elaine and Tiffany
 * Teacher: Mr. Ho
 * Description: Creating a customer information system that allows users to enter their information,
 * while checking to see if their inputs are accurate and providing them with a personal file + number
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// More packages may be imported in the space below

class CustomerSystem{
    public static void main(String[] args) {
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);

        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below
        int count = 0;
        String userVal = "";
        String first, last, city, postalCode, creditCard;
        first = "";
        last = "";
        city = "";
        postalCode = "";
        creditCard = "";

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code         
                first = firstName(userVal);
                last = lastName(userVal);
                city = city(userVal);
                postalCode = validatePostalCode(userVal);
                creditCard = validateCreditCard(userVal);
                
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile(count, first, last, city, postalCode, creditCard) ;
                count = count +1;
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }
            
        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }

    /*
    * Prints the menu
    * @Author - Mr.Ho
    */
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }

    /*
    * Prompts user's first name
    * @return - value of String firstName
    * @Author - Elaine
    */
    public static String firstName(String first) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        first = reader.nextLine();

        
        return first;
    }

    /*
    * Prompts user's last name
    * @return - value of String lastName
    * @Author - Elaine
    */
    public static String lastName(String last) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your last name: ");
        last = reader.nextLine();

        
        return last;
    }

    /*
    * Prompts a city
    * @return - value of String city
    * @Author - Elaine
    */
    public static String city(String city) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your city: ");
        city = reader.nextLine();

        
        return city;
    }  

    /*
    * Prompts user's postal code
    * @return - value of String postalCode
    * @Author - Elaine
    */
    public static String postalCode(String postalCode){

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your postal code: ");
        postalCode = reader.nextLine();

        return postalCode;

    }
    /*
    * This method validates the postal code by checking 
    * if it has 3 characters, then detecting if it exits in 
    * a list of postal codes.
    * @return - will not return a value
    * @author - Elaine
    */
    public static String validatePostalCode(String postalCode){

        postalCode = postalCode(postalCode);
        int len = postalCode.length();

        if(len>=3){
            //open the spreadsheet
            String fileName = "postal_codes.csv";

            try{
                //Create a file instance to reference the file 
                File spreadSheet = new File(fileName);
                //Read the file
                //Creates a scanner instance to read the file 
                Scanner scnr = new Scanner(spreadSheet);
 
                int count = 0;

                while(scnr.hasNextLine()){

                    //while a line exists
                    String line = scnr.nextLine();
                    int index = line.indexOf(postalCode);

                    //the postal code has been found
                    if(index >=0){
                        count = count + 1;
                    }
                }
                if(count==1){
                    System.out.println("Valid postal code.");
                    return postalCode;
                }
                else{
                    System.out.println("Invalid postal code.");
                    return postalCode;
                }
            }
            catch(FileNotFoundException e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("Postal code invalid: must be greater than 3 characters");
        }
        return "";
    }

    /*
    * Prompts user's credit card number
    * @return - value of String creditCard
    * @Author - Elaine
    */

    public static String creditCard(String creditCard){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your credit card (no spaces, at least 9 digits): ");
        creditCard = reader.nextLine();
        return creditCard;
    }
    
    /*
     *@author - Tiffany Liang
     *
     * @param - a, entered credit card number
     * @return - reversed credit card number
     * */

    public static String reverseCreditCard(String a){
        String reverse = "";
        for (int i = a.length() -1; i>=0; i--) {
            reverse = reverse + a.charAt(i);
        }
        return reverse;
    }
    
    /*
     * @author - Tiffany Liang
     * Boolean that returns true and prints out "valid" if the user enters a legitimate credit card number 
     * 
     * @param - j, reversed credit card number
     * @return - true if it is a valid number, false it is invalid (<9 digits and/or doesn't pass luhn algorithm)
     */

    
    public static String validateCreditCard(String creditCard){

        String j = creditCard(creditCard);
        String n = reverseCreditCard(j);
        // Necessary variables
        if (n.length() < 9) {
            System.out.println("Credit card number must be at least 9 digits");
        }
        else{
            // length of reversed credit card number
            int evenSum = 0;
            int oddSum = 0;
            int totalSum = 0;
            // Runs through each digit/index, 'reads'/gets the numerical value and checks + runs through requirements depending on if it is odd or even
            for (int i = n.length() - 1; i>=0; i--){
                int digit = Character.getNumericValue(n.charAt(i));
                // For even digits/indexes, b/c the first number is 0, the even digits have to be at odd numbered indexes 
                if (i % 2 != 0) {
                    int multiplyTwo = digit*2;
                    if (multiplyTwo > 9) {
                        String product = Integer.toString(multiplyTwo); // convert to string
                        multiplyTwo = Character.getNumericValue(product.charAt(0)) + Character.getNumericValue(product.charAt(1));
                    }
                    evenSum += multiplyTwo;
                }
                // For odd digits/indexes
                else {
                    oddSum += digit;
                }
            }
            totalSum = evenSum + oddSum;
            // If there is no remainder after dividing the sum by 10, that must mean that it ends with a 0
            if (totalSum %10 ==0) {
                System.out.println("Valid credit card");
            }
            else {
                System.out.println("Invalid credit card");
            
            }
        }
        return j;
    }
    
    /*
    * Gathers the customer information
    * @Author - Elaine
    */
    
    /*
    public static void enterCustomerInfo(){
        String first = firstName();
        String last = lastName();
        String city = city();
        String postalCode = validatePostalCode();
        boolean creditCard = validateCreditCard();

        System.out.println();
    }
    */


    public static int generateCustomerID(int userID){

        userID = userID+1;
        System.out.println("customer ID: " + userID);
        System.out.println();

        return userID;
    }

    /*
    * @Author - Tiffany
    * Takes user's previously entered information + uniquely assigned number, and prints it into a file (name of their choice) 
    * 
    *  @return - none, method simply creates/generates customer's file
    */

    
    public static void generateCustomerDataFile(int count, String first, String last, String city, String postalCode, String creditCard){
        
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the file name (.csv):");
            String fileName = in.nextLine();
            File outFile = new File(fileName);
            PrintWriter out = new PrintWriter(outFile);

            generateCustomerID(count);

            if (outFile.exists()){
                System.out.print("File already exists, is it okay to overwrite (y/n)? ");
                if (!in.nextLine().startsWith("y")){
                    out.println(first + ", " + last + ", " + city + ", " + postalCode + ", " + creditCard);
                }
            }
            System.out.println("Customer information: ");
            out.println("First Name: "+ first + ", Last Name: " + last + ",  City: " + city + ", Postal Code: " + postalCode + ",  Credit Card: "+ creditCard);
            System.out.println(); // Spacing
            System.out.println("Your information can now be found in " + fileName);
            System.out.println(); // Spacing
            out.close(); 
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}