package demo.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class RequestListener implements Runnable {

	@Override
	public void run() {

		while (true) {
			String floorNumberStr = null;
			try {
				// Read input from console
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				floorNumberStr = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (floorNumberStr != null) {
				String[] tokensStr = floorNumberStr.split(" ");
				
				int [] tokens = StringArrayToIntArray(tokensStr);				
				
				Arrays.sort(tokens);
				
				for (int i=0;i< tokens.length;i++){
					
					if (isValidFloorNumber(tokens[i])) {
						System.out.println("User Pressed : " + tokens[i]);
						Elevator elevator = Elevator.getInstance();
						elevator.addFloor(tokens[i]);
					} else {
						System.out.println("Floor Request Invalid : " + tokens[i]);
					}
				}

				
			}
		}
	}
	
	public int[] StringArrayToIntArray(String[] stringArray)
	{
	    return Stream.of(stringArray).mapToInt(Integer::parseInt).toArray();
	}

	/**
	 * This method is used to define maximum floors this elevator can process.
	 * 
	 * @param s
	 *            - requested floor
	 * @return true if requested floor is integer and max floor = 4
	 */
	private boolean isValidFloorNumber(int s) {
		return ((s >=0 && s <=4)); 
	}

}
