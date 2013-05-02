public class SpendAllTimeClass3 implements GradeFunction{

    private int numClasses;
    private int maxGrade;

    public SpendAllTimeClass3(int n, int g){
		this.numClasses = n;
		this.maxGrade = g;
    }

    // spend all your time on class 3, it's the best
    public int grade(int classID, int hours){
    	if(classID == 3) return Math.min(3*hours, maxGrade);
    	else return 0;
    }

}