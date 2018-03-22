package seu.complain.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Complain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int complain_id;
	//未按订单要求完成任务     完成订单未得到确认    其他
	private String type;
	private String time;
	private int applier_id;
	private int orderid;
	private String description;
	private Timestamp apply_time;
	private int state;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getComplain_id() {
		return complain_id;
	}
	public void setComplain_id(int complain_id) {
		this.complain_id = complain_id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getApplier_id() {
		return applier_id;
	}
	public void setApplier_id(int applier_id) {
		this.applier_id = applier_id;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public Timestamp getApply_time() {
		return apply_time;
	}
	public void setApply_time(Timestamp apply_time) {
		this.apply_time = apply_time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
