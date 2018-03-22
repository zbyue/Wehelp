package seu.system.systemService;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.support.WebApplicationContextUtils;

import seu.user.userService.UserService;

public class GetSystemToken {
	public ServletContextEvent arg0;
	public UserService userService;
	public GetSystemToken(ServletContextEvent arg0) throws Exception{
		this.arg0 = arg0;
		userService = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(UserService.class);
		userService.getToken("系统", "god", "http://223.3.118.162:8080/ssmdemo/images2/system.jpg");
	}

}
