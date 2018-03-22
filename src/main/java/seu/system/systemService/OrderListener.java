package seu.system.systemService;

import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.support.WebApplicationContextUtils;


import seu.orders.ordersService.OrdersService;
import seu.orders.pojo.Orders;
import seu.user.userService.UserService;

public class OrderListener extends TimerTask{
	public ServletContextEvent sce;
	public OrdersService ordersService;
	public UserService userService;
	public OrderListener(ServletContextEvent sce){
		this.sce = sce;
	}
	public void initService(){
		ordersService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(OrdersService.class);
		userService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(UserService.class);
	}
	public void run() {  
		initService();
		List<Orders> orderslist = this.ordersService.getAllOrdersState0();
		for(Orders o:orderslist){
			if(o.getDeadtime().getTime()<System.currentTimeMillis()){
				try {
					this.ordersService.orderNoAcceptedOuttime(o.getOrderid());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		List<Orders> orderslist2 = this.ordersService.getAllOrdersState1();
		for(Orders o:orderslist2){
			if(o.getDeadtime().getTime()<System.currentTimeMillis()){
				try {
					this.ordersService.orderAcceptedOuttime(o.getOrderid());
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.userService.decreaseUserCredit(o.getTaker_id());
				this.userService.freezeUserCreditLower0(o.getTaker_id());
			}
		}
    } 
}