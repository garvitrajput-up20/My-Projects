package csvReader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import attributes.*;

public class csvReader {
	public static ArrayList<TshirtData> getAllTshirts() {
		ArrayList<TshirtData> getalldata = new ArrayList<TshirtData>();
		CSVReaderMethod("src\\main\\java\\database\\Adidas.csv", getalldata);
		CSVReaderMethod("src\\main\\java\\database\\Nike.csv", getalldata);
		CSVReaderMethod("src\\main\\java\\database\\Puma.csv", getalldata);
		return getalldata;
	}

	private static void CSVReaderMethod(String filePath, ArrayList<TshirtData> getalldata) {
		try {
			CSVParser parser = new CSVParserBuilder().withSeparator('|').withIgnoreQuotations(true).build();
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath)).withCSVParser(parser).withSkipLines(1).build();

			String[] S;
			try {
				while ((S = csvReader.readNext()) != null) {
					TshirtData tshirt = new TshirtData();

					tshirt.setId(S[0]);
					tshirt.setName(S[1]);
					tshirt.setColor(Colors.valueOf(S[2].toUpperCase()));
					tshirt.setGender(Gender.valueOf(S[3].toUpperCase()));
					tshirt.setSize(Size.valueOf(S[4].toUpperCase()));
					tshirt.setPrice(Double.parseDouble(S[5]));
					tshirt.setRating(Double.parseDouble(S[6]));
					tshirt.setAvalibilty(S[7]);

					getalldata.add(tshirt);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
