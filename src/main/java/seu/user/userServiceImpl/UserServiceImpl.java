package seu.user.userServiceImpl;

import java.io.File;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import seu.util.rongyun.io.rong.RongCloud;
import seu.util.rongyun.io.rong.models.TokenResult;
import seu.orders.mapping.OrdersMapper;
import seu.orders.pojo.Orders;
import seu.system.systemService.EvaluateUser;
import seu.user.mapping.UserMapper;
import seu.user.pojo.User;
import seu.user.userService.UserService;

@Service("UserService")  
public class UserServiceImpl implements UserService{
	@Resource  
    private UserMapper userDAO;
	
	@Resource
	private OrdersMapper orderDAO;
	
	
	//短信验证
	public Map<String, String> messageComfirm(String phone){
		Map<String, String> map = new HashMap<>();
		String apikey="15907af3aa8662fb40e354caa13f1692";
		String code="";
		Random d = new Random();
		for(int i =0;i<5;i++){
			d = new Random();
			int num = d.nextInt(10);
			code+=num;
		}
		YunpianClient clnt = new YunpianClient(apikey).init();
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, phone);
		param.put(YunpianClient.TEXT, "【我帮你APP】感谢您注册我帮你，您的验证码是"+code);
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		clnt.close();
		System.out.println(code);
		System.out.println(r);
		map.put("code", code);
		return map;
	}
	
	//注册
	@Override
	public String Regist(User u){
		User user = this.userDAO.findUserByUsername(u.getUsername());
		if(user != null){
			return "nameError";
		}
		user =this.userDAO.findUserByPhone(u.getPhone());
		if(user != null){
	        return "phoneError";
	    }user =this.userDAO.findUserByNickname(u.getNickname());
		if(user != null){
	        return "nicknameError";
	    }
		return "success";
	}
	//登陆
	@Override
	public Map<String,String> Login(String loginType,String para,String password) throws Exception  {
		String result = "error";
		String url="";
		Map<String,String> resMap = new HashMap<String,String>();
		if(loginType.equals("name")){	
			User user = this.userDAO.findUserByUsername(para);
			//url="http://223.3.118.162:8080/ssmdemo/"+user.getImg().replace("\\","/");
			System.out.println(user);
			if(user != null){//如果该用户名存在
				if(user.getPassword().equals(password)){//如果密码正确
					int state = user.getState();
					if(state == 0){
						result = "success";
						resMap.put("token", this.getToken(String.valueOf(user.getUserid()), String.valueOf(user.getUsername()), url));
						resMap.put("userId", String.valueOf(user.getUserid()));
						resMap.put("username", user.getUsername());
						resMap.put("nickname",user.getNickname());
						resMap.put("address",user.getAddress());
						resMap.put("phone",user.getPhone() );
						resMap.put("sex",user.getSex());
						resMap.put("state", String.valueOf(user.getState()));
						resMap.put("credit", String.valueOf(user.getCredit()));
						resMap.put("img", user.getImg());
						resMap.put("description",user.getDescription());
						resMap.put("money", user.getMoney());
						resMap.put("evaluation", String.valueOf(user.getEvaluation()));
						resMap.put("orderscount", String.valueOf(user.getOrderscount()));
					}else if(state == 1){	
						result =  "unAudited";
					}else if(state == 2){	
						result = "auditNotPass";
					}else if(state == 3){
						result = "frozen";
					}else{
						result = "error";
						}	
				}else{   //用户名存在但密码不正确
					result = "passwordError";
				}
			}else{  //如果用户名不存在
				result = "nameError";
			}
			
			}else if(loginType.equals("phone")){
				User user = this.userDAO.findUserByPhone(para);
				System.out.println(user);
				if(user != null){//如果该手机号存在
					if(user.getPassword().equals(password)){//如果密码正确
						int state = user.getState();
						if(state == 0){
							result = "success";
							resMap.put("token", this.getToken(String.valueOf(user.getUserid()), String.valueOf(user.getUsername()), ""));
							resMap.put("userId", user.getUserid() + "");
							resMap.put("userName", user.getUsername());
							resMap.put("nickName",user.getNickname());
							resMap.put("address",user.getAddress());
							resMap.put("phone",user.getPhone() );
							resMap.put("sex",user.getSex());
							resMap.put("state", String.valueOf(user.getState()));
							resMap.put("credit", String.valueOf(user.getCredit()));
							resMap.put("img", user.getImg());
							resMap.put("description",user.getDescription());
							resMap.put("money", user.getMoney());
							resMap.put("evaluation", String.valueOf(user.getEvaluation()));
							resMap.put("orderscount", String.valueOf(user.getOrderscount()));
						}else if(state == 1){	
							result = "unAudited";
						}else if(state == 2){	
							result = "auditNotPass";
						}else if(state == 3){
							result = "frozen";
						}else{
							result = "error";}
					}else{   //手机号存在但密码不正确
						result = "passwordError";
					}
				}else{  //如果手机号不存在
					result = "phoneError";
				}
			}
			
			resMap.put("LoginResult", result);
			return resMap;
		}
	//加入user
	@Override
	public void Insert(User u) throws Exception {
		this.userDAO.insertUser(u);
		System.out.println("insert success!");
		
	}
	//减少信誉值
	@Override
	public void decreaseUserCredit(int userid) {
		this.userDAO.modifyCredit(userid);
	}
	//上传照片
	@Override
	public Map<String, String> uploadheadpic(MultipartFile picfile,int userid) {
		Map<String, String> map = new HashMap<>();
		//原始文件名称
		String flag="images2";
		String pictureFile_name =  picfile.getOriginalFilename();
		//新文件名称
		String newFileName = UUID.randomUUID().toString()+pictureFile_name.substring(pictureFile_name.lastIndexOf("."));
		System.out.println(newFileName);
		//上传图片
		File uploadPic = new java.io.File("D:/apache-tomcat-7.0.79-windows-x64/apache-tomcat-7.0.79/webapps/ssmdemo/images2/"+newFileName);
		if(!uploadPic.exists()){
			uploadPic.mkdirs();
		}
		//向磁盘写文件
		try {
			picfile.transferTo(uploadPic);
			String url = uploadPic.getAbsolutePath().substring(uploadPic.getAbsolutePath().indexOf(flag));
			this.userDAO.modifyImg(url, userid);
			map.put("Result", "success");
			map.put("picurl", url);
			List<Orders> list = this.orderDAO.selectByReleaserId(userid);
			for(Orders o : list){
				this.orderDAO.modifyOrderReleaserhead(url, o.getOrderid());
			}
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return map;

	}
	//获取信誉值
	@Override
	public int getCredit(int userid) {
		return this.userDAO.getUserCreditById(userid);
	}
	//冻结信誉低于0的用户
	@Override
	public void freezeUserCreditLower0(int userid) {
		if(this.getCredit(userid)<0){
			this.userDAO.modifyUserState3(userid);
		}
	}
	//修改用户信息
	@Override
	public Map<String, String> modifyUserInfo(User u) {
		this.userDAO.modifyInfo(u);
		List<Orders> list = this.orderDAO.selectByReleaserId(u.getUserid());
		for(Orders o : list){
			this.orderDAO.modifyOrderNickname(u.getNickname(), o.getOrderid());
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("Result", "savesuccess");
		return map;
	}
	//获取令牌
	@Override
	public String getToken(String userId, String name, String url) throws Exception {
		String appKey="cpj2xarlc1t0n";
		String appSecret="dRX8xfk05g";
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		TokenResult userGetTokenResult = rongCloud.user.getToken(userId,name,url);
		System.out.println("getToken:  " + userGetTokenResult.toString());
		return userGetTokenResult.getToken();
	}

	@Override
	public List<User> getUser(int userId) {
		List<User> list = new ArrayList<User>();
		list.add(this.userDAO.findUserById(userId));
		return list;
	}
	//获取余额
	@Override
	public double getMoney(int userid) {
		return this.userDAO.getUserMoney(userid);
	}
	//设置用户评价
	@Override
	public void setUserEvaluation(int userid, int grade) {
		this.userDAO.setUserEvaluation(userid, new EvaluateUser(grade, this.userDAO.getUserEvaluationById(userid), this.userDAO.getUserOrdersCountById(userid)).Calculate());
	}

	//返回用户评级和接单数
	public Map<String, String> getEvaluationAndCount(int userid) {
		int evaluation = this.userDAO.getUserEvaluationById(userid);
		int count = this.userDAO.getUserOrdersCountById(userid);
		Map<String, String> map = new HashMap<>();
		map.put("evaluation", String.valueOf(evaluation));
		map.put("orderscount", String.valueOf(count));
		return map;
	}


	@Override
	public Map<String, String> getNicknameAndUrl(int userid) {
		User user = this.userDAO.findUserById(userid);
		String nickname= user.getNickname();
		String url= user.getImg();
		Map<String, String> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("url",url );
		return map;
	}

	
}