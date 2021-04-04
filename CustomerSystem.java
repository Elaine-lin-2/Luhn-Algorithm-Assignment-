
/*
 * Date: April 8, 2021
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

        Scanner reader = new Scanner(System.in);

        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // Declare variables that will be used to store user's info
        int userID = 0;
        String userVal, first, last, city, postalCode, creditCard;
        userVal = "";
        first = "";
        last = "";
        city = "";
        postalCode = "";
        creditCard = "";

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){       
                first = firstName(userVal);
                last = lastName(userVal);
                city = city(userVal);
                postalCode = validatePostalCode(userVal);
                creditCard = validateCreditCard(userVal);
            }
            else if (userInput.equals(generateCustomerOption)) {
                //Generate a customer data file
                generateCustomerDataFile(userID, first, last, city, postalCode, creditCard);
                //adds onto the user ID
                userID = userID +1;
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
    * 
    * @param first - ""
    * @return - user's first name
    * @Author - Elaine
    */

    public static String firstName(String first) {
        //new reader
        Scanner reader = new Scanner(System.in);
        //Prompts user's first name
        System.out.println("Enter your first name: ");
        first = reader.nextLine();

        return first;
    }

    /*
    * Prompts user's last name
    *
    * @param last - ""
    * @return last - user's last name
    * @Author - Elaine
    */
    public static String lastName(String last) {
        //new reader
        Scanner reader = new Scanner(System.in);
        //Prompts user's last name
        System.out.println("Enter your last name: ");
        last = reader.nextLine();
        
        return last;
    }

    /*
    * @Author - Elaine
    * Prompts a city from the user
    * 
    * @param city - ""
    * @return city - value of String city
    */

    public static String city(String city) {
        //new reader
        Scanner reader = new Scanner(System.in);
        //Prompts a city name
        System.out.println("Enter your city: ");
        city = reader.nextLine();

        return city;
    }  

    /*
    * @Author - Elaine
    * Prompts user's postal code
    * 
    * @param postalCode - ""
    * @return postalCode- value of String postalCode
    *
    */

    public static String postalCode(String postalCode){
        //new reader
        Scanner reader = new Scanner(System.in);
        //Prompts user's postal code
        System.out.println("Enter your postal code: ");
        postalCode = reader.nextLine();

        return postalCode;
    }
    /*
    * @author - Elaine
    * This method validates the postal code by checking if it has 3 characters, then detecting if it exits in 
    * a list of postal codes.
    * 
    * @param postalCode - postal code (user's input)
    * @return - will not return a value
    * 
    */

    public static String validatePostalCode(String postalCode){

        postalCode = postalCode(postalCode);
        //Calculates the length of the code
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

                    String shortCode = postalCode.substring(0,3);
                    int index = line.indexOf(shortCode);

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
                    return "";
                }
            }
            catch(FileNotFoundException e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("Postal code invalid: must be greater than 3 characters");
            System.out.println(); // Spacing
        }
        return "";
    }

    /*
    * @Author - Elaine
    * Prompts user's credit card number
    * 
    * @param creditCard - ""
    * @return creditCard - user's input
    * 
    */

    public static String creditCard(String creditCard){
        //new reader
        Scanner reader = new Scanner(System.in);

        //Prompts user's credit card number
        System.out.println("Enter your credit card (no spaces, at least 9 digits): ");
        creditCard = reader.nextLine();
        return creditCard;
    }
    
    /*
     *@author - Tiffany Liang
     *
     * @param - a, entered credit card number
     * @return reverse - a reversed credit card number
     */

    public static String reverseCreditCard(String a){
        String reverse = "";
        //reverse the credit card
        for (int i = a.length() -1; i>=0; i--) {
            reverse = reverse + a.charAt(i);
        }
        return reverse;
    }
    
    /*
     * @author - Tiffany Liang
     * Boolean that returns true and prints out "valid" if the user enters a legitimate credit card number 
     * 
     * @param creditCard - credit card number (user's input)
     * @return - true if it is a valid number, false it is invalid (<9 digits and/or doesn't pass luhn algorithm)
     */

    
    public static String validateCreditCard(String creditCard){

        // Necessary variables
        String j = creditCard(creditCard);
        String n = reverseCreditCard(j);

        if (n.length() < 9) {
            System.out.println("Credit card number must be at least 9 digits");
            System.out.println(); // Spacing
        }
        else{
            //set variables to 0
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
                return j;
                
            }
            else {
                System.out.println("Invalid credit card");
                return "";
            
            }
        }
        return "";
        
    }
    
    /*
    * @Author - Tiffany
    * Takes user's previously entered information + uniquely assigned number, and prints it into a file (name of their choice) 
    * 
    * @param userID - counter to keep track of user's ID
    * @param first - First name (user's input)
    * @param last - Last name (user's input)
    * @param city - city (use's input)
    * @param postalCode - postal code (user's input)
    * @param creditCard - credit card# (user's input)
    * @return - none, method simply creates/generates customer's file
    */

    public static void generateCustomerDataFile(int userID, String first, String last, String city, String postalCode, String creditCard){

        try{
            Scanner in = new Scanner(System.in);

            if(!creditCard.equals("") && !postalCode.equals("")){

                //generates a user ID
                userID = userID+1;
                System.out.println("Success. Your CSV will be generated.");
                System.out.println();

                //Ask the user for the file name
                System.out.println("Enter the file name (.csv):");
                String fileName = in.nextLine();
                File outFile = new File(fileName);
                PrintWriter out = new PrintWriter(outFile);
            
                if(outFile.exists()){
                    System.out.print("File already exists, is it okay to overwrite (y/n)? ");
                    if (in.nextLine().startsWith("y")){
                        //prints the customer information
                        out.println("Customer information - " + "User ID: " + userID + ", First Name: "+ first + ", Last Name: " + last + ", City: " + city + ", Postal Code: " + postalCode + ", Credit Card: " + creditCard);
                        
                        System.out.println(); // Spacing
                        System.out.println("Your information can now be found in " + fileName);
                        System.out.println(); // Spacing
                    }
                    else{
                        System.out.println("Failed to generate customer profile");
                    }
                }
                out.close(); 
    
            }
            else if(creditCard.equals("") && postalCode.equals("")){
                System.out.println("Your postal code and credit card are both invalid, unable to generate a profile.");
                System.out.println(); // Spacing
            }
            else{
                System.out.println("You have 1 invalid insertion, unable to generate a profile. ");
                System.out.println(); // Spacing
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}