package general;

import java.util.List;

public class EnumAsList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Players> listOfPlayers = Players.asList();
		
		for(Players player : listOfPlayers)
			System.out.println("Player Name : " + player);

	}

}
