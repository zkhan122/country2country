package cityDistance;

import java.util.*;
import java.io.*;
import java.math.*;


public class DownToEarth {
	
	/* Haversine formula function for calculating the distance using lat and long
	 see: https://en.wikipedia.org/wiki/Haversine_formula */
	
	public static void haversine(double lat1, double lat2, double long1, double long2) {
		int earthRadius = 3960; // in miles
		
		double distance = 2 * earthRadius * Math.asin(Math.sqrt( Math.pow(Math.sin(lat2-lat1)/2, 2) + Math.cos(lat1) * Math.cos(lat2) * 
					Math.pow(Math.sin((long2-long1)/2), 2)));
		
		System.out.println("Distance in miles: "+ (int) distance + " miles");
		System.out.println("Distance in kilometres: "+ (int) (distance * 1.61) + " km");
	}
	
	public static void distance(String country1, String country2, List<List<String>> list) {
		List<String> buff1 = null;
		List<String> buff2 = null;
		double lat1, lat2;
		double long1, long2;
		

        for (List<String> row : list) {
        	
        	for(Object i1 : row) {
        		String item = String.valueOf(i1);
        		if (item.contains(country1))
        			if (item.equals(country1)) // match to exact country (India != British Indian Ocean Territory)
        				buff1 = row;
        	}  
        	
        	for (Object i2 : row) {
        		String item2 = String.valueOf(i2);
        		if (item2.contains(country2)) {
        			if (item2.equals(country2))
        				buff2 = row;
        		}
        	}
        }
        System.out.println(buff1);
        System.out.println(buff2);
        
        lat1 = Double.parseDouble(buff1.get(1)) * Math.PI/180; // String to double (degrees)
		long1 = Double.parseDouble(buff1.get(2)) * Math.PI/180;
		
		lat2 = Double.parseDouble(buff2.get(1)) * Math.PI/180;;
		long2 = Double.parseDouble(buff2.get(2)) * Math.PI/180;
		
		haversine(lat1, lat2, long1, long2);
		
		
        
	}
	@SuppressWarnings("resource")
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		BufferedReader reader = null;
		String line = "";
		String file = "C:\\Users\\zayaa\\Documents\\countries.csv";
		List<List<String>> countries  = new ArrayList<>();
		
		// reading the csv file into an array
		
		reader = new BufferedReader(new FileReader(file));
		while ((line = reader.readLine()) != null) {
				
			List<String> nodes = Arrays.asList((line.split(","))); 
			countries.add(nodes);
		}
		
		System.out.println("Enter country 1: "); // user input
		String country1 = scanner.nextLine();
		
		System.out.println("Enter country 2: ");
		String country2 = scanner.nextLine();
		
		System.out.println();
		
		
		distance(country1, country2, countries);
	
	} 		
}
