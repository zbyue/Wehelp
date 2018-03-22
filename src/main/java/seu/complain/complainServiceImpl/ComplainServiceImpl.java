package seu.complain.complainServiceImpl;

import java.sql.Timestamp;

import java.util.List;


import javax.annotation.Resource;




import org.springframework.stereotype.Service;

import seu.complain.complainService.ComplainService;
import seu.complain.mapping.ComplainMapper;
import seu.complain.pojo.Complain;


@Service("ComplainService") 
public class ComplainServiceImpl implements ComplainService{
	@Resource
	private ComplainMapper complainDAO;
	
	//上交申诉
	@Override
	public void handupComplain(int applier_id, int orderid,String description,String type) {
		Complain complain = new Complain();
		complain.setApply_time(new Timestamp(System.currentTimeMillis()));
		complain.setTime(complain.getApply_time().toString());
		complain.setApplier_id(applier_id);
		complain.setOrderid(orderid);
		complain.setDescription(description);
		complain.setType(type);
		this.complainDAO.insertComplain(complain);
	}
	@Override
	public List<Complain> getComplainsRelateToMe(int userid) {
		return this.complainDAO.selectComplainsByUserId(userid);
	}
	
}
