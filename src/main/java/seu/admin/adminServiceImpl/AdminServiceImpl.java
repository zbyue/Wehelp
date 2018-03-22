package seu.admin.adminServiceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import seu.admin.adminService.AdminService;
import seu.admin.mapping.AdminMapper;
import seu.admin.pojo.Admin;
import seu.complain.pojo.Complain;
import seu.orders.pojo.Orders;
import seu.user.pojo.User;

@Service("AdminService")  
public class AdminServiceImpl implements AdminService {  
    
	@Resource  
    private AdminMapper adminDao; 

	@Cacheable(value="admincache",key="#username")
	public Admin selectByUsernameAndPwd(String username,String password){
		return this.adminDao.selectByUsernameAndPwd(username, password);
	}

	@Override
	public int countAllUsers() {
		// TODO Auto-generated method stub
		return this.adminDao.countAllUsers();
	}

	@Override
	public List<User> selectAllUsersFromMtoN(int m, int n) {
		// TODO Auto-generated method stub
		return this.adminDao.selectAllUsersFromMtoN(m,n);
	}

	@Override
	public User selectUserById(int uid) {
		// TODO Auto-generated method stub
		return this.adminDao.selectUserById(uid);
	}

	@Override
	public User selectUserByName(String name) {
		// TODO Auto-generated method stub
		return this.adminDao.selectUserByName(name);
	}

	@Override
	public List<User> selectUsersByState(int state) {
		// TODO Auto-generated method stub
		return this.adminDao.selectUsersByState(state);
	}

	@Override
	public List<User> selectUsersByCredit(int credit) {
		// TODO Auto-generated method stub
		return this.adminDao.selectUsersByCredit(credit);
	}

	@Override
	public void deleteUser(int uid) {
		// TODO Auto-generated method stub
		this.adminDao.deleteUser(uid);
	}

	@Override
	public void updateUser(int uid, String name, String password, String phone, String address, String sex, int state,
			int credit, double money, int evaluation, int orderscount, String description) {
		// TODO Auto-generated method stub
		this.adminDao.updateUser(uid, name, password, phone, address, sex, state, credit, money, evaluation, orderscount, description);
	}

	@Override
	public int maxId() {
		// TODO Auto-generated method stub
		return this.adminDao.maxId();
	}

	@Override
	public void insertUser(int uid, String name, String password, String phone, String address, String sex, int state,
			int credit,double money, int evaluation, int orderscount, String description) {
		// TODO Auto-generated method stub
		this.adminDao.insertUser(uid, name, password, phone, address, sex, state, credit, money, evaluation, orderscount, description);
	}

	@Override
	public int countAllOrders() {
		// TODO Auto-generated method stub
		return this.adminDao.countAllOrders();
	}

	@Override
	public List<Orders> selectAllOrdersFromMtoN(int m, int n) {
		// TODO Auto-generated method stub
		return this.adminDao.selectAllOrdersFromMtoN(m, n);
	}

	@Override
	public Orders selectOrderById(int oid) {
		// TODO Auto-generated method stub
		return this.adminDao.selectOrderById(oid);
	}

	@Override
	public List<Orders> selectOrdersByState(int state) {
		// TODO Auto-generated method stub
		return this.adminDao.selectOrdersByState(state);
	}

	@Override
	public int countAllComplains() {
		// TODO Auto-generated method stub
		return this.adminDao.countAllComplains();
	}

	@Override
	public List<Complain> selectAllComplainsFromMtoN(int m, int n) {
		// TODO Auto-generated method stub
		return this.adminDao.selectAllComplainsFromMtoN(m, n);
	}

	@Override
	public Complain selectComplainById(int oid) {
		// TODO Auto-generated method stub
		return this.adminDao.selectComplainById(oid);
	}

	@Override
	public List<Complain> selectComplainsByState(int state) {
		// TODO Auto-generated method stub
		return this.adminDao.selectComplainsByState(state);
	}

	@Override
	public List<Orders> selectOrdersByType(int type) {
		// TODO Auto-generated method stub
		return this.adminDao.selectOrdersByType(type);
	}

	@Override
	public List<Orders> selectOrdersByRsid(int rsid) {
		// TODO Auto-generated method stub
		return this.adminDao.selectOrdersByRsid(rsid);
	}

	@Override
	public List<Orders> selectOrdersByTkid(int tkid) {
		// TODO Auto-generated method stub
		return this.adminDao.selectOrdersByTkid(tkid);
	}

	@Override
	public void deleteOrder(int oid) {
		// TODO Auto-generated method stub
		this.adminDao.deleteOrder(oid);
	}

	@Override
	public List<Complain> selectComplainsByApplierId(int caid) {
		// TODO Auto-generated method stub
		return this.adminDao.selectComplainsByApplierId(caid);
	}

	@Override
	public List<Complain> selectComplainsByOrderId(int coid) {
		// TODO Auto-generated method stub
		return this.adminDao.selectComplainsByOrderId(coid);
	}

	@Override
	public void deleteComplain(int cid) {
		// TODO Auto-generated method stub
		this.adminDao.deleteComplain(cid);
	}

	@Override
	public void updateComplain(int cid, int state) {
		// TODO Auto-generated method stub
		this.adminDao.updateComplain(cid,state);
	}

	@Override
	public List<Complain> selectRecentComplains() {
		// TODO Auto-generated method stub
		return this.adminDao.selectRecentComplains();
	}
	
}
