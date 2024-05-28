package display;

import java.util.ArrayList;

import attributes.TshirtData;

public class Output {
	
	public void displayOutput(ArrayList<TshirtData>matchedTshirts) {
		System.out.println("RESULT:\n");
		
		
		if(matchedTshirts.isEmpty()) {
			System.out.println("Sorry!! NO PRODUCT OF YOUR CHOICE IS AVAILABLE RIGHT NOW");
			
			
		}else {
		
			for(TshirtData i :matchedTshirts) {
				System.out.println("ID\t\t|\t"+ i.getId());
				System.out.println("NAME\t\t|\t"+ i.getName());
				System.out.println("COLOR\t\t|\t"+ i.getColor());
				System.out.println("GENDER\t\t|\t"+ i.getGender());
				System.out.println("SIZE\t\t|\t"+ i.getSize());
				System.out.println("PRIZE\t\t|\t"+ i.getPrice());
				System.out.println("RATING\t\t|\t"+ i.getRating());
				System.out.println("AVALIABILITY\t|\t"+ i.getAvalibilty());
				System.out.println();
			}
			
		}
	}


}
