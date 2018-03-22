package seu.user.controller;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import seu.user.pojo.User;
import seu.user.userService.UserService;
@Controller  
@RequestMapping("/user")  
public class UserController { 
		@Resource  
	    private	UserService userService;   
		
		
	  
	    //短信验证
	    @RequestMapping("/messageconfirm")
	    public @ResponseBody Map<String, String> messagecomfirm(String phone){
	    	return this.userService.messageComfirm(phone);
	    }
	    //登陆
		@RequestMapping("/login")
		public @ResponseBody Map<String,String> Login(String loginType,String para,String password) throws Exception{
			System.out.println("Login type: " + loginType);
			System.out.println("param: " + para);
			System.out.println("password: " + password);
			Map<String,String> response =this.userService.Login(loginType,para,password);
			System.out.println("response type: " + response.get("LoginResult"));
			return response;
		}
		//注册
		@RequestMapping("/regist")
		public @ResponseBody Map<String,String> Regist(User user) throws Exception{
			System.out.println("userName: " + user.getUsername());
			System.out.println("password: " + user.getPassword());	
			System.out.println("address: " + user.getAddress());
			System.out.println("phoneNumber: " + user.getPhone());
			String response = this.userService.Regist(user);
			System.out.println("response type: " + response);
			if(response.equals("success"))
				this.userService.Insert(user);
			//RegistResult
			Map<String,String> m = new HashMap<String,String>();
			m.put("RegistResult", response);
			return m;

		}
		
		//上传照片
		@RequestMapping("/uploadheadpic")
		public  @ResponseBody Map<String, String> upload(@RequestParam(value="image",required=false)MultipartFile image,  
	            @RequestParam("userId")int userId){  
			return this.userService.uploadheadpic(image, userId);
	     } 
		//修改用户信息
		@RequestMapping("/modifymyinfo")
		public @ResponseBody Map<String, String>modifyinfo(User user){
			System.out.println(user.getSex());
			System.out.println(user.getNickname());
			System.out.println(user.getDescription());
			return this.userService.modifyUserInfo(user);
		}
		//点击头像或ID返回用户详情
		@RequestMapping("/getUserInfo")
		public @ResponseBody List<User> getUserInfo(int userId){
			return this.userService.getUser(userId);
		}
		//获取钱包
		@RequestMapping("/getusermoney")
		public @ResponseBody Map<String, String> getUserMoney(int userId){
			Map<String, String> map = new HashMap<>();
			map.put("money", String.valueOf(this.userService.getMoney(userId)));
			return map;
		}
		//刷新获取评级和接单数
		@RequestMapping("/getevaluationandcount")
		public @ResponseBody Map<String, String> getEvaluationAndCount(int userId){
			return this.userService.getEvaluationAndCount(userId);
		}
		//
		@RequestMapping("/getnicknameandurl")
		public @ResponseBody Map<String, String> getnicknameandurl(int userid){
			return this.userService.getNicknameAndUrl(userid);
		}
}
