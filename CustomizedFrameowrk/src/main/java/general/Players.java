package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Players {
	
	Rohit_Sharma,
	Virat_Kohli,
	KL_Rahul;
	
	public static List<Players> asList(){
		return new ArrayList<Players>(Arrays.asList(values()));
	}

}
