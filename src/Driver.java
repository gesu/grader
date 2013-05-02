public class Driver {
	
	public static void main(String[] args) {
		int n = 4;
		int g = 100;
		Program3 p = new Program3();
		GradeFunction gf = new RealisticGrader(n, g);
		p.initialize(n, g, gf);
		p.computeGrades(100);
	}
}
