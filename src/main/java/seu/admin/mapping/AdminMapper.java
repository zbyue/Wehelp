package seu.admin.mapping;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import seu.admin.pojo.Admin;
import seu.complain.pojo.Complain;
import seu.orders.pojo.Orders;
import seu.user.pojo.User;
public interface AdminMapper {
	public Admin selectByUsernameAndPwd(@Param("username") String username,@Param("password") String password);

	//user
	public int countAllUsers();

	public List<User> selectAllUsersFromMtoN(@Param("m") int m, @Param("n") int n);

	public User selectUserById(int id);

	public User selectUserByName(String name);

	public List<User> selectUsersByState(int state);

	public List<User> selectUsersByCredit(int credit);

	public void deleteUser(int id);

	public void updateUser(@Param("id") int id, @Param("name") String name, @Param("password") String password, 
			@Param("phone") String phone, @Param("address") String address, 
			@Param("sex") String sex, @Param("state") int state,
			@Param("credit") int credit,@Param("money") double money,
			@Param("evaluation") int evaluation,
			@Param("orderscount") int orderscount, 
			@Param("description") String description);

	public int maxId();

	public void insertUser(@Param("id") int id, @Param("name") String name, @Param("password") String password, 
			@Param("phone") String phone, @Param("address") String address, 
			@Param("sex") String sex, @Param("state") int state,
			@Param("credit") int credit,@Param("money") double money,
			@Param("evaluation") int evaluation,
			@Param("orderscount") int orderscount, 
			@Param("description") String description);

	//order
	public int countAllOrders();

	public List<Orders> selectAllOrdersFromMtoN(@Param("m") int m, @Param("n") int n);

	public Orders selectOrderById(int id);

	public List<Orders> selectOrdersByState(int state);
	
	public List<Orders> selectOrdersByType(int type);
	
	public List<Orders> selectOrdersByRsid(int rsid);
	
	public List<Orders> selectOrdersByTkid(int tkid);
	
	public void deleteOrder(int id);
	
	//complain
	public int countAllComplains();

	public List<Complain> selectAllComplainsFromMtoN(@Param("m") int m, @Param("n") int n);

	public Complain selectComplainById(int id);

	public List<Complain> selectComplainsByState(int state);

	public List<Complain> selectComplainsByApplierId(int caid);

	public List<Complain> selectComplainsByOrderId(int coid);

	public void deleteComplain(int cid);

	public void updateComplain(@Param("cid")int cid, @Param("state")int state);

	public List<Complain> selectRecentComplains();


	
}
