package seu.complain.controller;


import java.util.HashMap;


import java.util.List;

import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import seu.complain.complainService.ComplainService;
import seu.complain.pojo.Complain;


@Controller  
@RequestMapping("/complain")  
public class ComplainController {
    @Resource  
    private	 ComplainService complainService;    
    //提交申诉
    @RequestMapping("/handupcomplain")
    public @ResponseBody Map<String, String> handupComplain(int applier_id, int orderid,String description,String type){
    	this.complainService.handupComplain(applier_id, orderid, description, type);
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("Result", "success");
    	return map;
    }
    //与我有关的申诉
    @RequestMapping("complainrelatetome")
    public @ResponseBody List<Complain> mycomplain(int userid){
    	return this.complainService.getComplainsRelateToMe(userid);
    }
}