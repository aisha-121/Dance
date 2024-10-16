import swiftbot.SwiftBotAPI;

import java.awt.image.BufferedImage;

// this class is used to handle the QR code process of the swiftbot
// uses swiftbot API to take a picture of the QR code and decode the hexadecimal number 


public class InputQRCode {
	
	public String gethexnum(SwiftBotAPI swiftBot) {

		String hexNum = "";
		
	try {

       
		System.out.println("Please scan a QR Code with a hexadecimal number (e.g. C or 1C)");
		System.out.println();
        Thread.sleep(10000);

        BufferedImage img = swiftBot.getQRImage(); // SwiftBot takes an image
        
        hexNum = swiftBot.decodeQRImage(img); //SwiftBot stores decoded QR code as a string
        
        if(hexNum.isEmpty())
        	{
	        	System.out.println("------");
	            System.out.println("ERROR!");
	            System.out.println("------");
	            System.out.println();
        		System.out.println("No QR Code was found. Try adjusting the distance of the SwiftBot's Camera from the QR code, or try another.");
        		System.exit(5);
        	}
        else if(hexNum.length()>2) { // error screen of hex num is larger than 2
        		System.out.println("------");
                System.out.println("ERROR!");
                System.out.println("------");
                System.out.println();
        		System.out.println("QR code was a Hex number of more than 2 digits! Please input a Hex number of no more than 2 digits.");
            	System.exit(5);
        		
        	}

    	}	
	
	catch(Exception e)// error if no QR code is found
		{
			System.out.println("------");
	        System.out.println("ERROR!");
	        System.out.println("------");
	        System.out.println();
			System.out.println("No QR Code was found. Try adjusting the distance of the SwiftBot's Camera from the QR code, or try another.");
        	//e.printStackTrace();
        	System.exit(5);
		}
	
	return hexNum;
	
	}
	
}	