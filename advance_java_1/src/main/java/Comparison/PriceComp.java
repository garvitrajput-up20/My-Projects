package Comparison;

import attributes.TshirtData;
import java.util.Comparator;


public class PriceComp implements Comparator<TshirtData>{
	
	public int compare(TshirtData tshirt1,TshirtData tshirt2) {
		
		return (int)(tshirt1.getPrice()-tshirt2.getPrice());
	}

}
