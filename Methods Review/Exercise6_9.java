package Exercise6_9;

public class Exercise6_9 {

	public static void main(String[] args) {
		double feet = 1;
		double meters = 20;
		System.out.println("Feet\tMeters\t\tMeters\tFeet");
		System.out.println("---------------------------------------");
		
		for (int i = 0; i < 10; i++) {
			System.out.printf("%.1f\t%.3f\t\t%.1f\t%.3f\r", feet, footToMeter(feet), meters, meterToFoot(meters));
			feet += 1;
			meters += 5;
		}

	}
	
	public static double footToMeter(double foot) {
		double meter = 0.305 * foot;
		return meter;
	}
	
	public static double meterToFoot(double meter) {
		double foot = 3.279 * meter;
		return foot;
	}

}
