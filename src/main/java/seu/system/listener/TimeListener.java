package seu.system.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import seu.system.systemService.GetSystemToken;
import seu.system.systemService.OrderArbitrate;

public class TimeListener implements ServletContextListener{  
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			new GetSystemToken(arg0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new OrderArbitrate(arg0).run();
	}
	
}

