import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.io.*;

import swiftbot.ImageSize;
import swiftbot.SwiftBotAPI;


public class DanceRoutine {
	 private String hexNum;
	 private int speed;
	 private int redLED;
	 private int greenLED;
	 private int blueLED;
	 //private ArrayList<String> hexNumList;
	 //int[] colourToLightUp = {redLED,greenLED,blueLED};
	 int movement;
	 
	 
	 SwiftBotAPI swiftbot; 
	
	 public DanceRoutine(SwiftBotAPI api) {
		 swiftbot = api;
	 }
	
	 
	 public int SetSpeed(int octalNum) {
		 
			 if (octalNum <= 50) {
				 speed = octalNum + 50;
			 }
			 else if  (octalNum > 100){
				 speed = 100;
			 }
			 else {
				 speed = octalNum;
			 }
		System.out.println("Speed : " + speed);
		
		return speed;
	    }
	 
	 
	 
	 public int [] setLED(int decNum) {

	        // Calculate speed, red, green, blue
	        redLED = decNum;
	        greenLED = (decNum % 80) * 3;
	        blueLED = Math.max(redLED, greenLED);

	        // Display additional information
	        System.out.println("LED colours : (red " + redLED + ", green " + greenLED + ", blue " + blueLED + ")");
	   	
	        int[] colourToLightUp = {redLED,greenLED,blueLED};

	        swiftbot.fillUnderlights(colourToLightUp);
	        System.out.println("LEDs turned on.");
	        System.out.println();
			
	        return colourToLightUp;
	       
	    }
	 
	 public int boogieTime(String hexNum, String binaryNumber) {
		 
		 int i = 0;
		 int phototaken = 0;
		 
		 StringBuilder reversedBinary = new StringBuilder(binaryNumber).reverse();
	     
		// boolean blinkLeds = hexNum.length() == 2;
		 
		 if (hexNum.length()==1) {
			 movement = 1000; // if hexNum is 1 the SwiftBot movement is for 1 second
			
		 }
		 else {
			 movement = 500; // if hexNum is 2 digits then SwiftBot movement is 0.5s
			 // make LEDs blink here
			 
		 }
		 
		 for (i = reversedBinary.length() - 1; i>=0; i-- ) {

			 if (reversedBinary.charAt(i)=='1') {
				 
				 try {
					 //System.out.println("[moving forwards]");
					 swiftbot.move(speed,speed,movement);
					 } 
				 catch (IllegalArgumentException e) {
					 e.printStackTrace();
					 }

				 // takes a pic if binary digit is 1
				 
					 BufferedImage danceimg = swiftbot.takeGrayscaleStill(ImageSize.SQUARE_144x144);
					 //ImageIO.write(img, "jpg", new File("/home/pi/Documents.jpg"));
					 //ImageIO.write(danceimg, "jpg", new File("/home/pi/Pictures"));
					 try {
						ImageIO.write(danceimg, "jpg", new File("danceimg.jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 phototaken=+1; // counting how many photos were taken in the dance
					
			 }
			 else if (reversedBinary.charAt(i)=='0') {
				 //spin
				 //System.out.println("[spinning]");
				 if (hexNum.length()==1) {
					 swiftbot.move(speed,0,3000);
				 }
				 else if (hexNum.length()==2) {
					 swiftbot.move(0,speed,3000);
				 }
			 }
			 
		 }
		return phototaken;
		 
	 } // return phototaken???
	 
	 public void blinkeyblink() {
		 int[] colourToLightUp = {redLED,greenLED,blueLED};

		 
			 swiftbot.fillUnderlights(colourToLightUp);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				swiftbot.disableUnderlights();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	 }
	 
}
