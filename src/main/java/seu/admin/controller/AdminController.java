package seu.admin.controller;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import seu.admin.adminService.AdminService;
import seu.admin.pojo.Admin;
import seu.complain.pojo.Complain;
import seu.orders.ordersService.OrdersService;
import seu.orders.pojo.Orders;
import seu.system.controller.systemController;
import seu.user.pojo.User;

@Controller  
@RequestMapping("/admin")  
public class AdminController {  
	@Resource  
    private	 AdminService adminService;
	
	@RequestMapping("/login")  
    public String toLogin(HttpServletRequest request){  
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin=this.adminService.selectByUsernameAndPwd(username, password);
        if(admin!=null){
        	 request.getSession().setAttribute("admin", admin);
        	 List<Complain> recentComplainList = this.adminService.selectRecentComplains();
 			 request.setAttribute("recentComplainList", recentComplainList);
             return "main";  
        }
        else return "login";
    }
    @RequestMapping("/edit")  
    public String toEdit(HttpServletRequest request){  
    	int uid = (int) request.getSession().getAttribute("uid");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String sex = request.getParameter("sex");
        String stateString = request.getParameter("state");
        String creditString = request.getParameter("credit");
        String orderscountString = request.getParameter("orderscount");
        String description = request.getParameter("description");
        String moneyString = request.getParameter("money");
        String evaluationString = request.getParameter("evaluation");
        
        //System.out.print("sex="+sex);
        int state=1;
        int credit=100;
        int orderscount=0;
        double money=0.0;
        int evaluation=100;
		try{
			state = Integer.parseInt(stateString);
			credit = Integer.parseInt(creditString);
			orderscount = Integer.parseInt(orderscountString);
			money = Double.parseDouble(moneyString);
			evaluation = Integer.parseInt(evaluationString);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(uid>this.adminService.maxId()){
			this.adminService.insertUser(uid, name, password, phone, address, sex, state, credit, money, evaluation, orderscount, description);
		}else{
			this.adminService.updateUser(uid, name, password, phone, address, sex, state, credit, money, evaluation, orderscount, description);
		}
        return "userInfor";
    }
    @RequestMapping("/main")
    public String toMain(HttpServletRequest request){
    	String action = request.getParameter("action");
    	switch(action){
    		case "home":
    		{
    			List<Complain> recentComplainList = this.adminService.selectRecentComplains();
    			request.setAttribute("recentComplainList", recentComplainList);
    			System.out.println(recentComplainList);
    			return "main";
    		}
    		case "user":
    		{
    			int countUsers = this.adminService.countAllUsers();
    			int countPages;
    			if(countUsers%100==0){
    				countPages = countUsers/100;
    			}else{
    				countPages = countUsers/100+1;
    			}
    			request.setAttribute("countPages", countPages);
    			request.setAttribute("countUsers", countUsers);
    			List<User> userlist = this.adminService.selectAllUsersFromMtoN(0, 99);
    			request.setAttribute("userlist", userlist);
    			return "user";
    		}
    		case "userid":
    		{
    			String idString = request.getParameter("uid");
    			int countUsers = 1;
    			int countPages = 1;
    			request.setAttribute("countPages", countPages);
    			request.setAttribute("countUsers", countUsers);
    			List<User> userlist = new ArrayList<User>();
    			int uid=1;
    			try{
    				uid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			userlist.add(this.adminService.selectUserById(uid));
    			request.setAttribute("userlist", userlist);
    			return "user";
    		}
    		case "username":
    		{
    			String name = request.getParameter("name");
    			int countUsers = 1;
    			int countPages = 1;
    			request.setAttribute("countPages", countPages);
    			request.setAttribute("countUsers", countUsers);
    			List<User> userlist = new ArrayList<User>();
    			userlist.add(this.adminService.selectUserByName(name));
    			request.setAttribute("userlist", userlist);
    			return "user";
    		}
    		case "userstate":
    		{
    			String stateString = request.getParameter("state");
    			//int countUsers = 1;
    			//int countPages = 1;
    			//request.setAttribute("countPages", countPages);
    			//request.setAttribute("countUsers", countUsers);
    			int state=1;
    			try{
    				state = Integer.parseInt(stateString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<User> userlist = this.adminService.selectUsersByState(state);
    			request.setAttribute("userlist", userlist);
    			return "user";
    		}
    		case "usercredit":
    		{
    			String creditString = request.getParameter("credit");
    			//int countUsers = 1;
    			//int countPages = 1;
    			//request.setAttribute("countPages", countPages);
    			//request.setAttribute("countUsers", countUsers);
    			int credit=100;
    			try{
    				credit = Integer.parseInt(creditString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<User> userlist = this.adminService.selectUsersByCredit(credit);
    			request.setAttribute("userlist", userlist);
    			return "user";
    		}
    		case "deleteuser":
    		{
    			String idString = request.getParameter("uid");
    			int uid=0;
    			try{
    				uid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			this.adminService.deleteUser(uid);
    			
    			int countUsers = this.adminService.countAllUsers();
    			int countPages;
    			if(countUsers%100==0){
    				countPages = countUsers/100;
    			}else{
    				countPages = countUsers/100+1;
    			}
    			request.setAttribute("countPages", countPages);
    			request.setAttribute("countUsers", countUsers);
    			List<User> userlist = this.adminService.selectAllUsersFromMtoN(0, 99);
    			request.setAttribute("userlist", userlist);
    			return "user";
    		}
    		case "edituserinfor":
    		{
    			String idString = request.getParameter("uid");
    			int uid=1;
    			try{
    				uid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			User user = null;
    			if(uid!=0){
    				user = this.adminService.selectUserById(uid);
    				request.getSession().setAttribute("uid", uid);
    			}else{
    				request.getSession().setAttribute("uid", this.adminService.maxId()+1);
    			}
    			request.getSession().setAttribute("user", user);
    			
    			return "userInfor";
    		}
    		case "order":
    		{
    			int countOrders = this.adminService.countAllOrders();
    			int countPages;
    			if(countOrders%100==0){
    				countPages = countOrders/100;
    			}else{
    				countPages = countOrders/100+1;
    			}
    			request.setAttribute("countPages", countPages);
    			request.setAttribute("countOrders", countOrders);
    			List<Orders> orderlist = this.adminService.selectAllOrdersFromMtoN(0, 99);
    			request.setAttribute("orderlist", orderlist);
    			return "order";
    		}
    		case "orderid":
    		{
    			String idString = request.getParameter("oid");
    			List<Orders> orderlist = new ArrayList<Orders>();
    			int oid=1;
    			try{
    				oid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			orderlist.add(this.adminService.selectOrderById(oid));
    			request.setAttribute("orderlist", orderlist);
    			return "order";
    		}
    		case "ordertype":
    		{
    			String typeString = request.getParameter("type");
    			int type=1;
    			try{
    				type = Integer.parseInt(typeString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<Orders> orderlist = this.adminService.selectOrdersByType(type);
    			request.setAttribute("orderlist", orderlist);
    			return "order";
    		}
    		case "orderstate":
    		{
    			String stateString = request.getParameter("state");
    			int state=1;
    			try{
    				state = Integer.parseInt(stateString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<Orders> orderlist = this.adminService.selectOrdersByState(state);
    			request.setAttribute("orderlist", orderlist);
    			return "order";
    		}
    		case "orderrsid":
    		{
    			String rsidString = request.getParameter("rsid");
    			int rsid=1;
    			try{
    				rsid = Integer.parseInt(rsidString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<Orders> orderlist = this.adminService.selectOrdersByRsid(rsid);
    			request.setAttribute("orderlist", orderlist);
    			return "order";
    		}
    		case "ordertkid":
    		{
    			String tkidString = request.getParameter("tkid");
    			int tkid=1;
    			try{
    				tkid = Integer.parseInt(tkidString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<Orders> orderlist = this.adminService.selectOrdersByTkid(tkid);
    			request.setAttribute("orderlist", orderlist);
    			return "order";
    		}
    		case "deleteorder":
    		{
    			String idString = request.getParameter("oid");
    			int oid=0;
    			try{
    				oid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			this.adminService.deleteOrder(oid);
    			
    			int countOrders = this.adminService.countAllOrders();
    			int countPages;
    			if(countOrders%100==0){
    				countPages = countOrders/100;
    			}else{
    				countPages = countOrders/100+1;
    			}
    			request.setAttribute("countPages", countPages);
    			request.setAttribute("countOrders", countOrders);
    			List<Orders> orderlist = this.adminService.selectAllOrdersFromMtoN(0, 99);
    			request.setAttribute("orderlist", orderlist);
    			return "order";
    		}
    		case "detailinfor":
    		{
    			String idString = request.getParameter("oid");
    			int oid=1;
    			try{
    				oid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			Orders order = this.adminService.selectOrderById(oid);
    			String imgs = order.getImgs();
    			String[] img = imgs.split(";");
    			request.getSession().setAttribute("order", order);
    			request.getSession().setAttribute("img", img);
    			return "orderInfor";
    		}
    		case "feedback":
    		{
    			int countComplains = this.adminService.countAllComplains();
    			int countPages;
    			if(countComplains%100==0){
    				countPages = countComplains/100;
    			}else{
    				countPages = countComplains/100+1;
    			}
    			request.setAttribute("countPages", countPages);
    			request.setAttribute("countComplains", countComplains);
    			List<Complain> complainlist = this.adminService.selectAllComplainsFromMtoN(0, 99);
    			request.setAttribute("complainlist", complainlist);
    			return "feedback";
    		}
    		case "feedbackid":
    		{
    			String idString = request.getParameter("fid");
    			List<Complain> complainlist = new ArrayList<Complain>();
    			int fid=1;
    			try{
    				fid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			complainlist.add(this.adminService.selectComplainById(fid));
    			request.setAttribute("complainlist", complainlist);
    			return "feedback";
    		}
    		case "feedbackapplierid":
    		{
    			String idString = request.getParameter("faid");
    			int faid=1;
    			try{
    				faid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<Complain> complainlist = this.adminService.selectComplainsByApplierId(faid);
    			request.setAttribute("complainlist", complainlist);
    			return "feedback";
    		}
    		case "feedbackstate":
    		{
    			String stateString = request.getParameter("state");
    			int state=1;
    			try{
    				state = Integer.parseInt(stateString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<Complain> complainlist = this.adminService.selectComplainsByState(state);
    			request.setAttribute("complainlist", complainlist);
    			return "feedback";
    		}	
    		case "feedbackorderid":
    		{
    			String idString = request.getParameter("foid");
    			int foid=1;
    			try{
    				foid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			List<Complain> complainlist = this.adminService.selectComplainsByOrderId(foid);
    			request.setAttribute("complainlist", complainlist);
    			return "feedback";
    		}	
    		case "deletefeedback":
    		{
    			String idString = request.getParameter("fid");
    			int fid=0;
    			try{
    				fid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			this.adminService.deleteComplain(fid);
    			
    			int countComplains = this.adminService.countAllComplains();
    			int countPages;
    			if(countComplains%100==0){
    				countPages = countComplains/100;
    			}else{
    				countPages = countComplains/100+1;
    			}
    			request.setAttribute("countPages", countPages);
    			request.setAttribute("countOrders", countComplains);
    			List<Complain> complainlist = this.adminService.selectAllComplainsFromMtoN(0, 99);
    			request.setAttribute("complainlist", complainlist);
    			return "order";
    		}
    		case "checkfeedback":
    		{
    			String idString = request.getParameter("fid");
    			int fid=1;
    			try{
    				fid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			Complain complain = this.adminService.selectComplainById(fid);
    			request.getSession().setAttribute("complain", complain);
    			return "feedbackInfor";
    		}
    		case "auditfeedback":
    		{
    			String idString = request.getParameter("fid");
    			int fid=1;
    			try{
    				fid = Integer.parseInt(idString);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			this.adminService.updateComplain(fid, 1);
    			Complain complain = this.adminService.selectComplainById(fid);
    			request.getSession().setAttribute("complain", complain);
    			return "feedbackInfor";
    		}
    		case "message":
    			return "message";
    		default:
    			return "main";
    	}
    }
}

