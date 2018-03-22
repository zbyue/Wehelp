package seu.orders.ordersService;

import java.io.IOException;



import java.util.List;
import java.util.Map;
import seu.orders.pojo.*;

import org.springframework.web.multipart.MultipartFile;


public interface OrdersService {
	//刷新
	public List<Orders> topOrderFlash() throws IOException;
	public List<Orders> bottomOrderFlash(int orderid);
	//发布订单
	public Map<String, String> releaseOrders(MultipartFile file1,MultipartFile file2,MultipartFile file3,int userid,String title,String description,String reward,String timelimit,String phonenumber,String address,int privateright)throws IllegalStateException, IOException;
	//获取订单消息
	public List<Orders> showOrderDetails(int ordersid);
	public List<Orders> getOrdersRelateToMe(int userid);
	//获取被接的订单
	public List<Orders> getAllOrdersState1();
	//订单未被接超时
	public void orderNoAcceptedOuttime(int ordersid) throws Exception;
	//接受订单
	public Map<String, String> acceptOrders(int ordersid,int userid)  throws Exception;
	//查询订单状态
	public int getOrdersState(int ordersid);
	//取消订单
	public Map<String, String> cancel(int ordersid);
	//获取未被接订单
	public List<Orders> getAllOrdersState0();
	//确认收单
	public Map<String, String> comfirmAccept(int ordersid)throws Exception;
	//订单被接收超时
	public void orderAcceptedOuttime(int ordersid)throws Exception;
	//上传多个图片
	public String uploadOrderPics(List<MultipartFile> mf)throws IllegalStateException, IOException;
	//转账
	public void transferMoney(int outid1,int inid2,double money);
	//订单完成后获取评分
	public Map<String, String> setGradeToOrder(int ordersid,int grade);
	//系统消息提醒
	public void SystemMessage(String id,String text)throws Exception;
	//私信
	public void PrivateMessage(String fromid,String toid,String text) throws Exception;
}
