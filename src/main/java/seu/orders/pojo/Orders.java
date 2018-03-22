package seu.orders.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Orders implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderid;
	private String title;
	private double reward;
	private String nickname;
	private String deadline;
	private String imgs;
	private String releaserhead;
	private String releasetime2;
	private int privateright;
	private String type;
	private int time_limit;	
	private Timestamp deadtime;
	private int taker_id;
	private int releaser_id;
	private int state;
	private String address;
	private String phonenumber;
	private int picture_id;
	private int grade;
	private double realprice;
	private String description;
	private Timestamp releasetime;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrivateright() {
		return privateright;
	}
	public void setPrivateright(int privateright) {
		this.privateright = privateright;
	}
	public String getReleasetime2() {
		return releasetime2;
	}
	public void setReleasetime2(String releasetime2) {
		this.releasetime2 = releasetime2;
	}
	public String getReleaserhead() {
		return releaserhead;
	}
	public void setReleaserhead(String releaserhead) {
		this.releaserhead = releaserhead;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public double getReward() {
		return reward;
	}
	public void setReward(double reward) {
		this.reward = reward;
	}
	
	public Timestamp getDeadtime() {
		return deadtime;
	}
	public void setDeadtime(Timestamp deadtime) {
		this.deadtime = deadtime;
	}
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getRealprice() {
		return realprice;
	}
	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(Timestamp releasetime) {
		this.releasetime = releasetime;
	}
	public int getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}
	public int getTaker_id() {
		return taker_id;
	}
	public void setTaker_id(int taker_id) {
		this.taker_id = taker_id;
	}
	public int getReleaser_id() {
		return releaser_id;
	}
	public void setReleaser_id(int releaser_id) {
		this.releaser_id = releaser_id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getPicture_id() {
		return picture_id;
	}
	public void setPicture_id(int picture_id) {
		this.picture_id = picture_id;
	}

	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
