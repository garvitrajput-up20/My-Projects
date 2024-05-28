package com.garvitrajput.assignment2AdvanceJava;

import java.util.ArrayList;

public class Output {

	public void displayOutput(ArrayList<Tshirt> matchedTshirts) {
		System.out.println("Thanks for Confirmation!!! \nYour Result:\n");

		if (matchedTshirts.isEmpty()) {
			System.out.println("Sorry,no tshirt is available of your required choice");

		} else {

			for (Tshirt i : matchedTshirts) {

				System.out.println("ID:\t" + i.getId());
				System.out.println("NAME:\t" + i.getName());
				System.out.println("COLOR:\t" + i.getColor());
				System.out.println("GENDER:\t" + i.getGender());
				System.out.println("SIZE:\t" + i.getSize());
				System.out.println("PRIZE:\t" + i.getPrice());
				System.out.println("RATING:\t" + i.getRating());
				System.out.println("AVALIABILITY:\t" + i.getAvalibilty());
				System.out.println();
			}

		}
	}

}
