package Practice;

import java.util.Random;

public class RandomNumber {

	public int rNum() {
		Random rnum=new Random();
		int number;
		number=rnum.nextInt(4000);
		return number;
		
	}

}
