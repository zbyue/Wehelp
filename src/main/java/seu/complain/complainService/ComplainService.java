package seu.complain.complainService;

import java.util.List;

import seu.complain.pojo.Complain;

public interface ComplainService {

	//上交申诉
	public void handupComplain(int applier_id,int orderid,String description,String type);
	//查看与我有关申诉
	public List<Complain> getComplainsRelateToMe(int userid);
}
