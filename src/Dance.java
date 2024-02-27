import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.FileWriter;
import java.io.IOException;

import swiftbot.Button;
import swiftbot.SwiftBotAPI;

// this class is the main class for the project
// it calls upon the other classes to make an instance of the qr code input object and display conversions 
// where the print statements for the UI will be 
public class Dance {

	private static String hexNum; // return the value
	private static int decNum;
	private static int octalNum;
	private static String binaryNumber = "";
	public static ArrayList<String> hexNumList = new ArrayList<String>();
	private static AtomicInteger phototaken = new AtomicInteger(0);

	
	// main method
	public static void main(String[] args) {
		SwiftBotAPI swiftbot = new SwiftBotAPI(); // creating swiftbot object
		System.out.println("------------------");
	    System.out.println("BOOGIE BOT PROGRAM");
	    System.out.println("------------------");
		System.out.println();
		BoogieBot(swiftbot);
	}

	public static void BoogieBot(SwiftBotAPI swiftbot) {

		InputQRCode qr = new InputQRCode();

		hexNum = qr.gethexnum(swiftbot); // getting the hex number from the QR code input
		hexNumList.add(hexNum);

		HexConversions hexConversions = new HexConversions(); // creating hex conversions object
		binaryNumber = hexConversions.HextoBinary(hexNum); // converting input hex num to binary equivalent
		decNum = hexConversions.BinarytoDecimal(binaryNumber); // converting binary number to decimal equivalent
		octalNum = hexConversions.DecimaltoOctal(decNum); // converting decimal number to octal equivalent
		displayConversions(hexNum, octalNum, decNum, binaryNumber);

		DanceRoutine danceRoutine = new DanceRoutine(swiftbot); // creating dance routine object
		danceRoutine.SetSpeed(octalNum); // using octal equivalent to set the speed for routine
		danceRoutine.setLED(decNum); // using decimal equivalent to set the RBG LEDs for routine

		// ask for user input to start the dance
		System.out.println("Press 'A' to start the dance or 'B' to quit");
		System.out.println();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		swiftbot.enableButton(Button.A, () -> {
			// dance!!!
			System.out.println("---------------------");
			System.out.println("DANCE IN PROGRESS");
			System.out.println("---------------------");
			System.out.println();
			

			danceRoutine.boogieTime(hexNum, binaryNumber, phototaken); // hex number and binary equivalent is passed as an argument for the dance routine
			// disable LEDs
			swiftbot.disableUnderlights();
			System.out.println("Dance completed!!! LEDs turned off.");
			System.out.println();
			System.out.println("Would you like to enter another Hex number?");
			System.out.println("[ Y ] = Yes [ X ] = No");
			System.out.println();
			
		});  
		
			// ask for more input using buttons 
			// new hex number
		
		swiftbot.enableButton(Button.Y, () -> {
			
			swiftbot.disableAllButtons();
			BoogieBot(swiftbot);
			
		});
			
			

			swiftbot.enableButton(Button.X, () -> {
				
				// hexNumList = danceRoutine.SwiftbotDance(hexNum);
				sortedHexList(hexNumList,phototaken, "dance.txt");
				System.out.println();
				System.out.println("Number of images taken: " + phototaken);
				System.out.println("Images saved to: home/pi/Documents/DancePic");
				System.out.println("File location of data above: home/pi/Documents/dance.txt");
				System.out.println();
				System.out.println("---------------------");
				System.out.println("FAREWELL");
				System.out.println("---------------------");
				System.out.println();
				System.out.println("Thanks for dancing with me.");
				System.out.println("Let's do this again sometime xx");
				System.out.println();
				System.out.println("Program terminated.");
				System.out.println();

				//swiftbot.disableButton(Button.X);
				swiftbot.disableAllButtons();
				System.exit(5);

			});
			// swiftbot.disableAllButtons();


		swiftbot.enableButton(Button.B, () -> {
			
			swiftbot.disableUnderlights();
			System.out.println("Program terminated.");
			System.exit(5);
			swiftbot.disableAllButtons();
			
		});

	}

	public static void displayConversions(String hexNum, int octalNum, int decNum, String binaryNumber) {

		System.out.println("------------------------------");
		System.out.println("HEXADECIMAL CONVERSION RESULTS");
		System.out.println("------------------------------");
		System.out.println();
		System.out.println("Hexadecimal number: " + hexNum);
		System.out.println("Octal equivalent: " + octalNum);
		System.out.println("Decimal equivalent: " + decNum);
		System.out.println("Binary equivalent: " + binaryNumber);
		System.out.println();

	}
	
	public static void sortedHexList(ArrayList<String> hexNumList, AtomicInteger phototaken, String filename) {
		Collections.sort(hexNumList, new Comparator<String>() {
            public int compare(String hex1, String hex2) {
                // Convert hex strings to integer values for comparison
                return Integer.compare(Integer.parseInt(hex1, 16), Integer.parseInt(hex2, 16));
            }
        });

        // Print the sorted list
		System.out.println("------------------");
        System.out.println("SORTED HEX NUMBERS");
        System.out.println("------------------");
		System.out.println();
        for (String hex : hexNumList) {
            System.out.println(hex);
        }
        
        try (FileWriter writer = new FileWriter(filename)) {
            // Write sorted hex numbers
            writer.write("Sorted Hex Numbers:\n");
            for (String hex : hexNumList) {
                writer.write(hex + "\n");
            }
            
            // Write number of images
            writer.write("Number of images taken: " + phototaken + "\n");
            
            // Write folder location
            writer.write("Images folder location: " + "/home/pi/Documents/DancePic" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
