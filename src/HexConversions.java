
public class HexConversions {
	// this class is used to convert the hex number from the QR code into its binary, decimal and octal equivalents
	
	
	public String HextoBinary(String hexNum) { 
		
		// initialise the counter
		int i = 0;
		String binaryNumber = "";
		
		// continuing the while loop until there is no data left in the char array
		for (i = 0; i < hexNum.length(); i++) {
            char hex = hexNum.charAt(i);
            switch (hex) {
            case '0':
            	binaryNumber = binaryNumber + "0000";
                break;
            case '1':
            	binaryNumber = binaryNumber + "0001";
                break;
            case '2':
            	binaryNumber = binaryNumber + "0010";
                break;
            case '3':
            	binaryNumber = binaryNumber + "0011";
                break;
            case '4':
            	binaryNumber = binaryNumber + "0100";
                break;
            case '5':
            	binaryNumber = binaryNumber + "0101";
                break;
            case '6':
            	binaryNumber = binaryNumber + "0110";
                break;
            case '7':
            	binaryNumber = binaryNumber + "0111";
                break;
            case '8':
            	binaryNumber = binaryNumber + "1000";
                break;
            case '9':
            	binaryNumber = binaryNumber + "1001";
                break;
            case 'A':
            case 'a':
            	binaryNumber = binaryNumber + "1010";
                break;
            case 'B':
            case 'b':
            	binaryNumber = binaryNumber + "1011";
                break;
            case 'C':
            case 'c':
            	binaryNumber = binaryNumber + "1100";
                break;
            case 'D':
            case 'd':
            	binaryNumber = binaryNumber + "1101";
                break;
            case 'E':
            case 'e':
            	binaryNumber = binaryNumber + "1110";
                break;
            case 'F':
            case 'f':
            	binaryNumber = binaryNumber + "1111";
            	break;
            default: // default case for error screen
            	System.out.println("------");
	            System.out.println("ERROR!");
	            System.out.println("------");
	            System.out.println();
                System.out.print("Invalid Hex number inputted. Please input a valid Hexadecimal number.");
                System.exit(5);
			}
         }

		return binaryNumber;
	}
		
	
	
	public int BinarytoDecimal(String binaryNum) {
	
		int i = 0;
		int decNum = 0;
		int power = 0;

        for (i = binaryNum.length() - 1; i >= 0; i--) {
            char binaryChar = binaryNum.charAt(i);

            // Convert the character to its numeric value (0 or 1)
            int digitValue = binaryChar - '0';
            
            // Add the decimal value of the current bit multiplied by 2 raised to the power
            decNum += digitValue * Math.pow(2, power);
            
            // Increment the power for the next iteration
            power++;         

        }

        return decNum;		
		
		
	}

	public int DecimaltoOctal(int decNum) {
		
		// initialise counter 
		int DNum = decNum;
		int i =1;
		int octalNum = 0;
		
		// while loop so that it is excecuted until no longer true
		while (DNum!=0)
		{
			int r = DNum % 8; // remainder of dec mod 8 stored in r 
			octalNum += r * i; // store value of octal num 
			i = i * 10; // increment 
			DNum /= 8;
		}
		
		return octalNum;
	
	}
	
}
