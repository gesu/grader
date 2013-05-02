public class RealisticGrader implements GradeFunction{

    private int numClasses;
    private int maxGrade;

    public RealisticGrader(int n, int g){
		this.numClasses = n;
		this.maxGrade = g;
    }

    // three hours for every hour you put in
    public int grade(int classID, int hours){
    	if(classID == 1) return Math.min(hours, maxGrade);
    	return Math.min(3*hours, maxGrade);
    }

}