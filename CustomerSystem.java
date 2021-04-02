// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray
/*
 * Date: March 31, 2021
 * Names: Elaine and Tiffany
 * Teacher: Mr. Ho
 * Description: Creating a customer information system that allows users to enter their information, while checking to see if 
 *              their inputs are accurate and providing them with a personal file + number
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// More packages may be imported in the space below

class CustomerSystemCopy{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);

        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu
            String first = firstName();
            String last = lastName();
            String city = city();
            String postalCode = validatePostalCode();
            String creditCard = creditCard();

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code         
                enterCustomerInfo();
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
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
    public static String firstName() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String first = reader.nextLine();
        return first;
    }

    /*
    * Prompts user's last name
    * @return - value of String lastName
    * @Author - Elaine
    */
    public static String lastName() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your last name: ");
        String last = reader.nextLine();
        return last;
    }

    /*
    * Prompts a city
    * @return - value of String city
    * @Author - Elaine
    */
    public static String city() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your city: ");
        String city = reader.nextLine();

        return city;
    }  

    /*
    * Prompts user's postal code
    * @return - value of String postalCode
    * @Author - Elaine
    */
    public static String postalCode(){

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your postal code: ");
        String postalCode = reader.nextLine();

        return postalCode;

    }
    /*
    * Prompts user's credit card number
    * @return - value of String creditCard
    * @Author - Elaine
    */

    public static String creditCard() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your credit card: ");
        String creditCard = reader.nextLine();
        return creditCard;
    }
    
    /*
    * This method validates the postal code by checking 
    * if it has 3 characters, then detecting if it exits in 
    * a list of postal codes.
    * @return - will not return a value
    * @author - Elaine
    */
    public static String validatePostalCode(){

        String postalCode = postalCode();
        int len = postalCode.length();

        if(len>=3){
            System.out.println("reading file...");
            String fileName = "postal_codes.csv";

            try{
                File spreadSheet = new File(fileName);
                Scanner scnr = new Scanner(spreadSheet);
                int count = 0;

                while(scnr.hasNextLine()){
                    
                    String line = scnr.nextLine();
                    //System.out.println(line);

                    int index = line.indexOf(postalCode);
                    //System.out.println(index);

                    if(index >=0){
                        count = count + 1;
                    }
                }
                if(count==1){
                    System.out.println("Valid postal code.");
                }
                else{
                    System.out.println("Invalid postal code.");
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

    
    public static void validateCreditCard(){

    }
    

    /*
    * Gathers the customer information
    * @Author - Elaine
    */
    public static void enterCustomerInfo(){
        String first = firstName();
        String last = lastName();
        String city = city();
        String postalCode = validatePostalCode();
        String creditCard = creditCard();
    }

    /*
    * @Author - Tiffany
    */

    //THIS NEEDS TO BE MODIFIED 
    //SO THAT it does not prompt the user's input again and 
    // only PRINTS the pre-entered values
    public static void generateCustomerDataFile(){

        String first = firstName();
        String last = lastName();
        String city = city();
        String postalCode = validatePostalCode();
        String creditCard = creditCard();

        System.out.println();
        System.out.println("First name: " + first);
        System.out.println("Last name: " + last);
        System.out.println("City: " + city);
        System.out.println("Postal Code: " + postalCode);
        System.out.println("Credit card number: " + creditCard);

    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}