package other;

public class AutoBoxing {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		long sum = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		long end = System.currentTimeMillis();
		System.out.println("time : " + (end - start) + " " + sum);
	}
}
