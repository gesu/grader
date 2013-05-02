import java.util.Arrays;

public class Program3 implements IProgram3 {
	private int N, G, H;
	private GradeFunction F;
	private int[][] M, K;
	private int[] A; // return array

	@Override
    public void initialize(int numClasses, int maxGrade, GradeFunction gf){
    	this.N = numClasses;
    	this.G = maxGrade;
    	this.F = gf; // assumed that gf is already initialized
    }
    
    @Override
    public int[] computeHours(int totalHours){
    	this.H = totalHours + 1;
    	this.A = new int[N];
    	fillMatrices();
    	fillReturnArray();
    	printM(N+1, H);
    	printK(N+1, H);
    	System.out.println("A: " + Arrays.toString(A));
    	return A;
    }
    
    @Override
    public int[] computeGrades(int totalHours){
    	this.H = totalHours + 1;
    	this.A = new int[N];
    	fillMatrices();
    	fillReturnArray();
    	// A[] has the hours to spend on each class
    	System.out.println("A: " + Arrays.toString(A));
    	for(int i=0; i < N; i++){
    		A[i] = F.grade(i, A[i]);
    	}
    	System.out.println("A: " + Arrays.toString(A));
    	//printM(N+1, H);
    	//printK(N+1, H);
    	return A;
    	
    }
    
    private void fillMatrices(){
    	this.M = new int[N+1][H];	// M[i][j] => i-1 = classID to include (i=0 is no class)
    	this.K = new int[N+1][H];	// K holds the hours to spend on class i-1
    	
    	// fill in initial known values for M
    	for(int i=1; i < N+1; i++){
    		M[i][0] = F.grade(i-1,0);
    	}
    	for(int i=0; i < H; i++){
    		M[0][i] = 0;
    	}
    	// fill in the rest
    	for(int j=1; j < H; j++){
    		for(int i=1; i < N+1; i++){
    			getMax(i, j);
    		}
    	}
    }
    
    // get the max of previous optimal solution + this class
    // this method fills in M[i][j] and K[i][j] for us
    private void getMax(int i, int j){
    	int tempmax = Integer.MIN_VALUE;
    	int k=0; // k = hours to spend on class i for optimal solution
    	for(int h=0; h < j+1; h++){
    		int x = F.grade(i-1, h) + M[i-1][j-h];
    		if(x > tempmax){
    			tempmax = x;
    			k = h;
    		}
    	}
    	if(tempmax != Integer.MIN_VALUE && tempmax >= M[i-1][j-k]){
    		M[i][j] = tempmax;
    		K[i][j] = k;
    	}
    }
    
    private void fillReturnArray(){
    	int j = 0;
    	for(int i=N; i > 0; i--){
    		j = (i==N) ? j=0 : K[i+1][H-j-1];
    		A[i-1] = K[i][H-j-1];
    	}
    }
    
    private void printM(int x, int y){
		System.out.println("M: " + x + " " + y);
    	for(int j = 0; j < y; j++){
    		for(int i = 0; i < x; i++){
    			System.out.print(M[i][j] + " ");
    		}
    		System.out.println("");
    	}
    }
    
    private void printK(int x, int y){
    	System.out.println("K: " + x + " " + y);
    	for(int j = 0; j < y; j++){
    		for(int i = 0; i < x; i++){
    			System.out.print(K[i][j] + " ");
    		}
    		System.out.println("");
    	}
    }

}
