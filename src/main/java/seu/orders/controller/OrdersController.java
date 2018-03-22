package seu.orders.controller;


import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import seu.orders.ordersService.OrdersService;
import seu.orders.pojo.Orders;


@Controller  
@RequestMapping("/orders")  
public class OrdersController { 
		@Resource  
	    private OrdersService ordersService;   
		
	    //上刷
	    @RequestMapping("/toprefresh")
	    public @ResponseBody List<Orders> topflash() throws IOException{
	    	return this.ordersService.topOrderFlash();
	    }
	    //下刷
	    @RequestMapping("/bottomrefresh")
	    public @ResponseBody List<Orders> bottomflash(int lastId){
	    	return this.ordersService.bottomOrderFlash(lastId);
	    }
	    //订单详情
	    @RequestMapping("/orderdetails")
	    public @ResponseBody List<Orders> orderdetails(int orderId){
	    	return this.ordersService.showOrderDetails(orderId);
	    }
	    //与我有关的订单详情
	    @RequestMapping("/ordersrelatetome")
	    public @ResponseBody List<Orders> ordersrelatetome(int userId){
	    	return this.ordersService.getOrdersRelateToMe(userId);
	    }
	    //订单发布
	    @RequestMapping("/orderrelease")
	    public @ResponseBody Map<String, String> release(@RequestParam(value="file1",required=false)MultipartFile file1, @RequestParam(value="file2",required=false)MultipartFile file2,@RequestParam(value="file3",required=false)MultipartFile file3, 
	        int userId,String title,String description,String reward,String timeLimit,String phoneNumber,String address,int privateright)throws IllegalStateException, IOException{
	    	return this.ordersService.releaseOrders(file1,file2,file3,userId, title, description, reward, timeLimit, phoneNumber, address,privateright);
	    }
	    //接受订单任务
	    @RequestMapping("/orderaccept")
	    public @ResponseBody Map<String, String> accept(int orderId, int userId) throws Exception{
	    	return this.ordersService.acceptOrders(orderId, userId);
	    }
	    //取消订单
	    @RequestMapping("/ordercancel")
	    public @ResponseBody Map<String, String> cancel(int orderId){
	    	return this.ordersService.cancel(orderId);
	    }
	    //确认结单
	    @RequestMapping("/confirmaccept")
	    public @ResponseBody Map<String, String> comfirm(int orderId)throws Exception{
	    	return this.ordersService.comfirmAccept(orderId);
	    }
	    //设置评分
	    @RequestMapping("/setgrade")
	    public @ResponseBody Map<String, String> setgrade(int orderId,int grade){
	    	return this.ordersService.setGradeToOrder(orderId, grade);
	    }
}