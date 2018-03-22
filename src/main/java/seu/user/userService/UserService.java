package seu.user.userService;

import java.util.List;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import seu.user.pojo.User;

public interface UserService {
	//短信验证
	public Map<String, String> messageComfirm(String phone);
	//注册
	public String Regist(User u);
	public Map<String,String> Login(String loginType,String para,String password) throws Exception;
	public void Insert(User u) throws Exception;
	//信誉值减少
	public void decreaseUserCredit(int userid);
	//上传个人头像
	public Map<String, String> uploadheadpic(MultipartFile file,int userid);
	//获取信誉值
	public int getCredit(int userid);
	//冻结账户
	public void freezeUserCreditLower0(int userid);
	//修改用户信息
	public Map<String,String> modifyUserInfo(User u);
	//返回令牌
	public String getToken(String userId,String name,String url)throws Exception;
	//通过ID获取对象
	public List<User> getUser(int userId);
	//获取钱
	public double getMoney(int userid);
	//设置评价分数
	public void setUserEvaluation(int userid,int grade);
	//获得评级和接单数
	public Map<String, String> getEvaluationAndCount(int userid);
	//获取nickname和url
	public Map<String, String> getNicknameAndUrl(int userid);
}
