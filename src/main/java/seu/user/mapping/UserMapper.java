package seu.user.mapping;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import seu.user.pojo.User;


public interface UserMapper {
	List<User> selectAllUsers();
	void updateUserState_Approve(int userid);
	void updateUserState_Freeze(int userid);
	//注册
	//根据用户id查询用户信息
	public User findUserById(int id) ;
	//根据用户名查询用户信息
	public User findUserByUsername(String username);
		//根据手机号查询用户信息
	public User findUserByPhone(String phone) ;
	//根据nickname查询用户信息
	public User findUserByNickname(String nickname) ;
		//查询用户列表
	public List<User> findUsersByUsername(String username) ;
		//添加用户信息
	public void insertUser(User u); 
	//降低信誉
	public void modifyCredit(int userid);
	//上传照片路径
	public void modifyImg(@Param("filepath")String filepath,@Param("userid")int userid);
	//自动冻结账户  credit<0 state=3
	public void modifyUserState3(int userid);
	//获取账户信誉
	public int getUserCreditById(int userid);
	//修改用户信息
	public void modifyInfo(User u);
	//获取用户金钱数
	public double getUserMoney(int userid);
	//修改金钱 加
	public void addUserMoney(@Param("userid")int userid,@Param("money")double money);
	//减
	public void reduceUserMoney(@Param("userid")int userid,@Param("money")double money);
	//获取用户的评级
	public int getUserEvaluationById(int userid);
	//获取用户的完成数
	public int getUserOrdersCountById(int userid);
	//设置评级
	public void setUserEvaluation(@Param("userid")int userid,@Param("evaluation")int evaluation);
	//完成数加
	public void setOrdersCountById(int userid);
}