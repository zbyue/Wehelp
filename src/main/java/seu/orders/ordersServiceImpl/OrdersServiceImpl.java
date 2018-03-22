package seu.orders.ordersServiceImpl;

import java.io.File;


import java.io.IOException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import seu.util.rongyun.io.rong.RongCloud;
import seu.util.rongyun.io.rong.messages.TxtMessage;
import seu.util.rongyun.io.rong.models.CodeSuccessResult;
import seu.orders.mapping.OrdersMapper;
import seu.orders.ordersService.OrdersService;
import seu.orders.pojo.Orders;
import seu.system.systemService.EvaluateUser;
import seu.user.mapping.UserMapper;
@Service("OrdersService")  
public class OrdersServiceImpl implements OrdersService{
	@Resource  
    private OrdersMapper orderDAO;
	@Resource
	private UserMapper userDao;
	
	//上刷
	public List<Orders> topOrderFlash() throws IOException {
		List<Orders> orderlist = this.orderDAO.selectFirstOrdersByTimeDesc();
		System.out.println(orderlist);
		return orderlist;
	}
	//下刷
	public List<Orders> bottomOrderFlash(int orderid) {
		int index = 0;
		List<Orders> orderlist = this.orderDAO.selectOrdersByTimeDesc();
		for(Orders orders:orderlist){
			if(orders.getOrderid()==orderid){
				index = orderlist.indexOf(orders);
			}
		}
		System.out.println("bottomRefresh sucess");
		if(index==orderlist.size()-1){
			System.out.println(index);
			return null;
		}
		else if((index+9)<orderlist.size()){
			System.out.println(orderlist.subList(index+1, index+11));
			return orderlist.subList(index+1, index+11);
		}else{
			System.out.println(orderlist.subList(index+1, orderlist.size()));
			return orderlist.subList(index+1, orderlist.size());
		}
	}
	
	
	//发布
	@Override
	public Map<String, String> releaseOrders(MultipartFile file1,MultipartFile file2,MultipartFile file3,int userid, String title, String description,
			String reward, String timelimit, String phonenumber, String address,int privateright)throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		if(this.userDao.getUserMoney(userid)>=Double.parseDouble(reward)){
			this.transferMoney(userid, 1, Double.parseDouble(reward));
			List<MultipartFile> mf = new ArrayList<MultipartFile>();
			mf.add(file1);
			mf.add(file2);
			mf.add(file3);
			Orders o = new Orders();
			long l = System.currentTimeMillis()+60*1000*(Integer.parseInt(timelimit));
			o.setReleasetime(new Timestamp(System.currentTimeMillis()));
			o.setReleasetime2(o.getReleasetime().toString());
			System.out.println(o.getReleasetime2());
			o.setDeadtime(new Timestamp(l));
			System.out.println(o.getDeadtime());
			o.setDeadline(o.getDeadtime().toString());
			o.setReleaser_id(userid);
			o.setTitle(title);
			o.setNickname(this.userDao.findUserById(userid).getNickname());
			o.setReleaserhead(this.userDao.findUserById(userid).getImg());
			o.setDescription(description);
			o.setReward(Double.parseDouble(reward));
			o.setTime_limit(Integer.parseInt(timelimit));
			o.setPhonenumber(phonenumber);
			o.setAddress(address);
			o.setDeadtime(Timestamp.valueOf(o.getDeadline()));
			o.setImgs(this.uploadOrderPics(mf));
			o.setPrivateright(privateright);
			this.orderDAO.insertOrders(o);
			
			map.put("ReleaseResult", "success");
		}else{
			map.put("ReleaseResult", "fail");
		}
		return map;
	}
	
	//显示订单详情
	@Override
	public List<Orders> showOrderDetails(int ordersid) {
		Orders orders = this.orderDAO.selectByOrdersID(ordersid);
		List<Orders> list = new ArrayList<>();
		list.add(orders);
		System.out.println(list);
		return list;
	}
	//显示与我有关的订单详情
	@Override
	public List<Orders> getOrdersRelateToMe(int userid) {
		return this.orderDAO.selectOrdersByUserId(userid);
	}
	//返回已经被接的订单
	public List<Orders> getAllOrdersState1(){
		return this.orderDAO.selectAllOrdersState1();
	}
	//未被接订单超时处理
	@Override
	public void orderNoAcceptedOuttime(int ordersid) throws Exception {
		this.transferMoney(1, this.orderDAO.selectByOrdersID(ordersid).getReleaser_id(),
		this.orderDAO.selectByOrdersID(ordersid).getReward());
		this.SystemMessage(String.valueOf(this.orderDAO.selectByOrdersID(ordersid).getReleaser_id()), "您的订单超时被搁置");
		this.orderDAO.modifyOrdersState3(ordersid);
	}
	//接受订单任务
	@Override
	public Map<String, String> acceptOrders(int ordersid,int userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if(this.getOrdersState(ordersid)==0){
			this.orderDAO.modifyOrdersState1(ordersid, userid);
			Orders orders =this.orderDAO.selectByOrdersID(ordersid);
			this.PrivateMessage(String.valueOf(orders.getTaker_id()),String.valueOf(orders.getReleaser_id()),"我接了你的订单  标题为"+orders.getTitle() );
			map.put("Result", "success");
		}
		else{
			map.put("Result", "fail");
		}
		return map;
	}
	//获取订单状态
	@Override
	public int getOrdersState(int ordersid) {
		return this.orderDAO.getOrderState(ordersid);
	}
	//放弃订单
	@Override
	public Map<String, String> cancel(int ordersid) {
		int state=this.getOrdersState(ordersid);
		Map<String, String> map = new HashMap<String, String>();
		if(state==0||state==3){
			this.orderDAO.modifyOrderState4(ordersid);
			this.transferMoney(1, this.orderDAO.selectByOrdersID(ordersid).getReleaser_id(),
					this.orderDAO.selectByOrdersID(ordersid).getReward());
			map.put("Result", "success");
			return map;
		}else{
			map.put("Result", "fail");
			return map;
		}
	}
	//获取未被接订单
	@Override
	public List<Orders> getAllOrdersState0() {
		return this.orderDAO.selectAllOrdersState0();
	}
	//确认完成订单
	@Override
	public Map<String, String> comfirmAccept(int ordersid) throws Exception {
		this.orderDAO.modifyOrderState5(ordersid);
		Orders o = this.orderDAO.selectByOrdersID(ordersid);
		this.transferMoney(1, o.getTaker_id(), o.getReward());
		this.PrivateMessage(String.valueOf(o.getReleaser_id()), String.valueOf(o.getTaker_id()), "对方已确认  标题为"+o.getTitle());
		Map<String, String> map = new HashMap<String, String>();
		map.put("Result", "success");
		return map;
	}
	//订单接受但超时
	@Override
	public void orderAcceptedOuttime(int ordersid) throws Exception {
		this.transferMoney(1, this.orderDAO.selectByOrdersID(ordersid).getReleaser_id(), this.orderDAO.selectByOrdersID(ordersid).getReward());
		this.SystemMessage(String.valueOf(this.orderDAO.selectByOrdersID(ordersid).getReleaser_id()), "接单人未按时完成任务");
		this.orderDAO.modifyOrderState2(ordersid);
	}
	//上传照片
	@Override
	public String uploadOrderPics(List<MultipartFile> picfiles) throws IllegalStateException, IOException {
		String path="";
		String flag="images";
		System.out.println(picfiles.size());
		for(MultipartFile pic:picfiles){
			if(pic!=null){
				String pictureFile_name =  pic.getOriginalFilename();
				//新文件名称
				String newFileName = UUID.randomUUID().toString()+pictureFile_name.substring(pictureFile_name.lastIndexOf("."));
				//上传图片
				File uploadPic = new java.io.File("D:/apache-tomcat-7.0.79-windows-x64/apache-tomcat-7.0.79/webapps/ssmdemo/images/"+newFileName);
				if(!uploadPic.exists()){
					uploadPic.mkdirs();
				}
				//向磁盘写文件
				try {
					pic.transferTo(uploadPic);
					path=path+"&"+uploadPic.getAbsolutePath().substring(uploadPic.getAbsolutePath().indexOf(flag));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path;
	}
	//转账
	@Override
	public void transferMoney(int outid1, int inid2,double money) {
		this.userDao.reduceUserMoney(outid1, money);
		this.userDao.addUserMoney(inid2, money);
	}
	//发送系统消息
	@Override
	public void SystemMessage(String id, String text) throws Exception {
		String appkey="cpj2xarlc1t0n";
    	String secret="dRX8xfk05g";
		RongCloud rongCloud = RongCloud.getInstance(appkey, secret);
		String[] messagePublishSystemToUserId = {id};
		TxtMessage messagePublishSystemTxtMessage = new TxtMessage("【我帮你】您有新的系统提醒", text);
		CodeSuccessResult messagePublishSystemResult = rongCloud.message.PublishSystem("系统", messagePublishSystemToUserId, messagePublishSystemTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 0, 0);
		System.out.println("PublishSystem:  " + messagePublishSystemResult.toString());
	}
	//私信消息
	@Override
	public void PrivateMessage(String fromid, String toid, String text)
			throws Exception {
		String appkey="cpj2xarlc1t0n";
    	String secret="dRX8xfk05g";
		RongCloud rongCloud = RongCloud.getInstance(appkey, secret);
		String[] messagePublishSystemToUserId = {toid};
		TxtMessage messagePublishSystemTxtMessage = new TxtMessage(text, "您有一份私信");
		CodeSuccessResult messagePublishSystemResult = rongCloud.message.PublishSystem(fromid, messagePublishSystemToUserId, messagePublishSystemTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 0, 0);
		System.out.println("PublishSystem:  " + messagePublishSystemResult.toString());
		
	}
	//订单完成后获取评分
	@Override
	public Map<String, String> setGradeToOrder(int ordersid,int grade) {
		this.orderDAO.setGradeOfOrder(ordersid, grade);
		int id=this.orderDAO.selectByOrdersID(ordersid).getTaker_id();
		this.userDao.setUserEvaluation(id,new EvaluateUser(grade, this.userDao.getUserEvaluationById(id), this.userDao.getUserOrdersCountById(id)).Calculate() );
		this.userDao.setOrdersCountById(id);
		Map<String, String> map = new HashMap<>();
		map.put("Result", "success");
		return map;
	}

	
}
