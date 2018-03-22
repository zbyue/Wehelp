package seu.system.systemService;

public class EvaluateUser {
	public int grade;
	public int evaluation;
	public int orderscount;
	public EvaluateUser(int grade,int evaluation,int orderscount){
		this.grade=grade;
		this.evaluation=evaluation;
		this.orderscount=orderscount;
	}
	public int Calculate(){
		return (evaluation*orderscount+grade)/(orderscount+1);
	}
}
