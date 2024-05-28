package inputsOutputs;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import csvReader.csvReader;
import attributes.*;
import inputsOutputs.SearchInput;
import Comparison.*;

public class SearchOutput {
	
Scanner scn;
	
	public ArrayList<TshirtData>getMatchingTshirts(SearchInput si) throws FileNotFoundException{
		 
		ArrayList<TshirtData>tshirt = new ArrayList<TshirtData>();
		ArrayList<TshirtData>allTshirtData = csvReader.getAllTshirts();
		
		String inputFormat = String.format("%s,%s,%s", si.getColor(),si.getSize(),si.getGender());
		
		String outputFormat;
		String output = si.getOutputPreference().name();
		
		System.out.println("Your choice of Output Preference is : ");
		
		for(TshirtData allTshirtDataiterator : allTshirtData) {
			
			outputFormat = String.format("%s,%s,%s", allTshirtDataiterator.getColor(),allTshirtDataiterator.getSize(),allTshirtDataiterator.getGender());
			
			if(inputFormat.equalsIgnoreCase(outputFormat))
				tshirt.add(allTshirtDataiterator);
			
		}
		
		if(output.equalsIgnoreCase("Price")) {
			
			Collections.sort(tshirt,new PriceComp());
			System.out.println(output);
			
		}else if(output.equalsIgnoreCase("Rating")) {
			
			Collections.sort(tshirt,new RatingComp());
			System.out.println(output);
			
		}else {
			
			Collections.sort(tshirt,new PriceComp());
			Collections.sort(tshirt, new RatingComp());
			System.out.println(output);
			
		}
		
		System.out.println("\n\n");
		
		return tshirt;
	}

}
