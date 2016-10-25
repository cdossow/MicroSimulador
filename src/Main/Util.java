package Main;
import java.math.BigDecimal;
import java.util.Random;

public class Util {
	
	  private static final char[] symbols;

	  static {
	    StringBuilder tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	      tmp.append(ch);
	    for (char ch = 'A'; ch <= 'Z'; ++ch)
	      tmp.append(ch);
	    symbols = tmp.toString().toCharArray();
	  }   
	
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




	/*

	  public String RandomString(int length) {
		  char[] buf;
		    if (length < 1)
			      throw new IllegalArgumentException("length < 1: " + length);
			    return buf = new char[length];
	  }
	  
	  public String nextString() {
		Random random = new Random();
	    for (int idx = 0; idx < buf.length; ++idx) 
	      buf[idx] = symbols[random.nextInt(symbols.length)];
	    return new String(buf);
	  }
	  */
}
