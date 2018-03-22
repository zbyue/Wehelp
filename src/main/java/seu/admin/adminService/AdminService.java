package seu.admin.adminService;

import java.util.List;

import seu.admin.pojo.Admin;
import seu.complain.pojo.Complain;
import seu.orders.pojo.Orders;
import seu.user.pojo.User;

public interface AdminService {  
    public Admin selectByUsernameAndPwd(String username,String password);

    //user
    public int countAllUsers();
    
    public int maxId();
    
    public List<User> selectAllUsersFromMtoN(int m,int n);
    
    public User selectUserById(int uid);
    
    public User selectUserByName(String name);
    
    public List<User> selectUsersByState(int state);
    
    public List<User> selectUsersByCredit(int credit);
    
    public void deleteUser(int uid);
    
    public void updateUser(int uid, String name, String password, String phone, String address, String sex,
    		int state, int credit, double money, int evaluation, int orderscount, String description);
    
    public void insertUser(int uid, String name, String password, String phone, String address, String sex,
    		int state, int credit, double money, int evaluation, int orderscount, String description);
    
    //order
    public int countAllOrders();
    
    public List<Orders> selectAllOrdersFromMtoN(int m, int n);
    
    public Orders selectOrderById(int oid);
    
    public List<Orders> selectOrdersByType(int type);
    
    public List<Orders> selectOrdersByState(int state);
    
    public List<Orders> selectOrdersByRsid(int rsid);
    
    public List<Orders> selectOrdersByTkid(int tkid);
    
    public void deleteOrder(int oid);
    
    //feedback
    public int countAllComplains();
    
    public List<Complain> selectAllComplainsFromMtoN(int m,int n);
    
    public Complain selectComplainById(int cid);
    
    public List<Complain> selectComplainsByApplierId(int caid);
    
    public List<Complain> selectComplainsByState(int state);
    
    public List<Complain> selectComplainsByOrderId(int coid);
    
    public void deleteComplain(int cid);
    
    public void updateComplain(int cid, int state);
    
    public List<Complain> selectRecentComplains();
}  



