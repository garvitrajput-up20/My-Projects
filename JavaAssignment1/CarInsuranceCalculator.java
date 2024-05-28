package JavaAssignment1;

import java.util.*;
public class CarInsuranceCalculator {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		String choice = "";
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		do {
			double premium = 0.0;
			System.out.print("Car Model: ");
			int model = sc.nextInt();
			String mod = Integer.toString(model);
			sc.nextLine();
			if(model>currentYear) {
				System.out.println("Car Model Should Not Be Greater Than Current Year \nTry Again!!\n\n");
				main(null);
			}
			else if(mod.length()<4) {
				System.out.println("Model should be of 4 digits. \nTry Again!!\n\n");
				main(null);
			}
			else {
				System.out.print("Car Cost: ");
				float carCost = sc.nextFloat();
				System.out.println("Car Type: \n1. Hatchback\n2. Sedan\n3. SUV");
				int carType = sc.nextInt();
				sc.nextLine();
				switch(carType) {
				case 1:
					premium = carCost * 0.05;
					break;
				case 2:
					premium = carCost * 0.08;
					break;
				case 3:
					premium = carCost * 0.1;
					break;
				default:
					System.out.println("Invalid Car Type !! Try Again...");
					main(null);
				}
				System.out.println("What type of insurance you want? [Premium, Basic]");
				String decision = sc.nextLine();
				if(decision.equalsIgnoreCase("Premium")) {
					premium += premium * 0.2;
				}
				premium = Math.round(premium * 100);
				premium = premium / 100;
				System.out.println("============FINAL DETAILS=============");
				System.out.println("Car model: " + model);
				System.out.println("Car cost price: " + carCost);
				System.out.println("Effective insurance premium: " + premium);
				System.out.println("=============DETAILS END==============");
				System.out.println("Do you want to enter details of any other car (Y/N)");
				choice = sc.nextLine();
			}
		}while(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("y"));
	}
}