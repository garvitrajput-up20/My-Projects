package Comparison;

import java.util.Comparator;

import attributes.TshirtData;

public class RatingComp implements Comparator<TshirtData> {
	
	
	public int compare(TshirtData tshirt1, TshirtData tshirt2) {
		
		return (int)(tshirt2.getRating()-tshirt1.getRating());
	}
	
}
