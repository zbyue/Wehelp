package seu.system.systemService;

import seu.system.systemService.OrderListener;

import java.util.Timer;
import javax.servlet.ServletContextEvent;

public class OrderArbitrate {
		public ServletContextEvent arg0;
		public OrderArbitrate(ServletContextEvent arg0){
			this.arg0=arg0;
		}
		
		public void run() {  
	        Timer timer = new Timer();  
	        OrderListener ol = new OrderListener(arg0);
	        //程序运行后立刻执行任务，每隔100ms执行一次  
	        timer.schedule(ol, 0, 10000);  
	    }
	}

