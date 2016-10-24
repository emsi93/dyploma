package dyploma.auction.system.carriage.goods.modules;

import java.util.ArrayList;
import java.util.List;

public class TrailersList {

	private static List<String> trailersList = new ArrayList<String>();
	
	public static List<String> getTrailersList(){
		
		trailersList.add("");
		trailersList.add("kurtynowa");
		trailersList.add("naczepowa");
		trailersList.add("ch³odnicza");
		trailersList.add("typu MEGA");
		trailersList.add("ponadgabarytowa");
		trailersList.add("nispokopodwoziowa z najazdami");
		trailersList.add("ADR");
		trailersList.add("cysterna");
		return trailersList;
	}
}
