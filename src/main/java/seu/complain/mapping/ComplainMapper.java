package seu.complain.mapping;

import java.util.List;


import seu.complain.pojo.Complain;

public interface ComplainMapper {

	//加入申诉
	void insertComplain(Complain complain);
	//获取与我有关申诉
	List<Complain> selectComplainsByUserId(int userid);
}
