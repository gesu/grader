
public class Program3 implements IProgram3 {
	private int N, G;
	private GradeFunction F;
	private int[][] M; // M[number of classes][number of total hours] = optimum hours

	@Override
    public void initialize(int numClasses, int maxGrade, GradeFunction gf){
    	this.N = numClasses;
    	this.G = maxGrade;
    	this.F = gf;
    }
    
    @Override
    public int[] computeHours(int totalHours){
    	int[] a = new int[N]; // return array
    	this.M = new int[N][totalHours]; // optimal hours matrix
    	// fill in initial known values for M
    	for(int j=0; j < totalHours; j++){
    		for(int i=0; i < N; i++){
    			// j==0 corresponds to 0 total hours, so it's just grade(i,0)
    			if(j==0) M[i][j] = (i==0) ? F.grade(i,0) : F.grade(i,0) + M[i-1][j];
    			// i==0 corresponds to 0 classes, so it's just 0
    			else if(i==0) M[i][j] = 0;
    		}
    	}
    	return a;
    }

    public int[] computeGrades(int totalHours){
    	int[] a = new int[N];
    	return a;
    }

}
