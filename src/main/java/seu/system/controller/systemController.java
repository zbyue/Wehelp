package seu.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import seu.orders.ordersService.OrdersService;
import seu.util.rongyun.io.rong.RongCloud;
import seu.util.rongyun.io.rong.messages.TxtMessage;
import seu.util.rongyun.io.rong.models.CodeSuccessResult;
import seu.util.rongyun.io.rong.models.TokenResult;

@Controller  
@RequestMapping("/system")  
public class systemController {
	//管理员广播
	private  OrdersService ordersService;
	
	@RequestMapping("/adminbroadcast")
	public String broadcast(HttpServletRequest request) throws Exception{
		String search = request.getParameter("search");
		String text = request.getParameter("text");
		if(search=="all"){
			String appKey="cpj2xarlc1t0n";
			String appSecret="dRX8xfk05g";
			RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
			System.out.println(text);
			TxtMessage messageBroadcastTxtMessage = new TxtMessage(text,"【我帮你】系统公告");
			CodeSuccessResult messageBroadcastResult = rongCloud.message.broadcast("系统", messageBroadcastTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", "Android");
			System.out.println("broadcast:  " + messageBroadcastResult.toString());
		}else if(search=="id"){
			String toid = request.getParameter("toid");
			this.ordersService.PrivateMessage("1", toid, text);
		}
		return null;
	}
	//android获取返回令牌
	@RequestMapping("/gettoken")
	public @ResponseBody Map<String, String> gettoken(String userId,String name,String url) throws Exception{
		String appKey="cpj2xarlc1t0n";
		String appSecret="dRX8xfk05g";
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		TokenResult userGetTokenResult = rongCloud.user.getToken(userId,name,url);
		System.out.println("getToken:  " + userGetTokenResult.toString());
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", userGetTokenResult.getToken());
		return map;
	}
	
	//转跳发布公告
	@RequestMapping("/broadcast")
	public String pushbroadcast(HttpServletRequest request){
		return "broadcast";
	}
}
