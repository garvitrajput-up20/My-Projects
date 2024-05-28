package display;

import java.util.Scanner;

import attributes.*;
import inputsOutputs.SearchInput;

public class InputData {
	
	public String getColor(Scanner scn) {
		boolean isValid = true;
		System.out.println("We Have Following Colors: \nBlack,White,Blue,Purple,Grey,Pink,Maroon,Yellow\nEnter color of Tshirt:");
		
		String colorType="";
		do {
			try {
				colorType = scn.nextLine();
				if(Colors.valueOf(colorType.toUpperCase())!=null)
					isValid = false;
				
			}
			catch(IllegalArgumentException e) {
				System.out.println("Enter a valid color of Tshirt:\n(available: Black,White,Blue,Purple,Grey,Pink,Maroon,Yellow)");
			}
		}
		while(isValid);
		return colorType;
	}
	
	public String getSize(Scanner scn) {
		boolean isValid = true;
		System.out.println("Enter size of Tshirt: \n S , M , L, XL ");
		
		String sizeType="";
		do {
			try {
				sizeType = scn.nextLine();
				if(Size.valueOf(sizeType.toUpperCase())!=null)
					isValid = false;
				
			}
			catch(IllegalArgumentException e) {
				System.out.println("Enter a valid size of Tshirt:\n(choose: S , M , L, XL )");}
		
			} while(isValid);
		return sizeType;
	}
		
		public String getGender(Scanner scn) {
			boolean isValid = true;
			System.out.println("Gender : \nM-male,F-female,U-unisex");
			
			String gender="";
			do {
				try {
					gender = scn.nextLine();
					if(Gender.valueOf(gender.toUpperCase())!=null)
						isValid = false;
					
				}
				catch(IllegalArgumentException e) {
					System.out.println("Enter a valid Gender:\n(M-male , F-femle,U-unisex )");
			}
			} while(isValid);
			return gender;
		}
			
			public String getOutputPreference(Scanner scn) {
				boolean isValid = true;
				System.out.println("Enter sorting output preference of Tshirt : \nPrice,Rating,Both");
				
				String outputPreference="price";
				do {
					try {
						outputPreference = scn.nextLine();
						if(OutputPreference.valueOf(outputPreference.toUpperCase())!=null)
						isValid = false;
					}
					catch(IllegalArgumentException e) {
						System.out.println("Enter a valid output preference:");
				}
			} while(isValid);
				return outputPreference;
	}
			
		public SearchInput getInput() {
			
			SearchInput sip = new SearchInput();
			Scanner scn = new Scanner(System.in);
			
			System.out.println("Enter the Required Details:\n");
					
			String color = getColor(scn);
		    String size = getSize(scn);
		    String gender = getGender(scn);
		    String outputPreference = getOutputPreference(scn);
		    sip.setColor(Colors.valueOf(color.toUpperCase()));
		    sip.setSize(Size.valueOf(size.toUpperCase()));
		    sip.setGender(Gender.valueOf(gender.toUpperCase()));
		    sip.setOutputPreference(OutputPreference.valueOf(outputPreference.toUpperCase()));
		    return sip;
		    
		}

}
