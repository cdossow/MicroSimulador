package Main;
import java.math.BigDecimal;
import java.util.Random;

public class Util {
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {  
		return Math.sqrt( Math.pow(lat1-lat2, 2) + Math.pow(lng1-lng2, 2));
    }  
	
	public static double truncate(double d, int decimalPlace) {
	    BigDecimal bd = new BigDecimal(d);
	    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	}
}
