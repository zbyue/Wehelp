package seu.orders.mapping;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import seu.orders.pojo.Orders;


public interface OrdersMapper {
	//所有订单
	List<Orders> selectAllOrders();
	//通过订单id获取订单
	Orders selectByOrdersID(int ordersid);
	//第一刷新 降序获取前十订单
	List<Orders> selectFirstOrdersByTimeDesc();
	//刷新 降序订单
	List<Orders> selectOrdersByTimeDesc();
	//插入订单
	void insertOrders(Orders o);
	//通过userid获取订单
	List<Orders> selectOrdersByUserId(int userid);
	//获取状态为1的订单
	List<Orders> selectAllOrdersState1();
	//修改订单状态 超时处理 3
	void modifyOrdersState3(int ordersid);
	//修改订单状态 接受订单 1
	void modifyOrdersState1(@Param("ordersid")int ordersid,@Param("userid") int userid);
	//获取目标订单状态
	int getOrderState(int ordersid);
	//放弃订单 4
	void modifyOrderState4(int ordersid);
	//获取所有未接受订单
	List<Orders> selectAllOrdersState0();
	//确认完成订单 5
	void modifyOrderState5(int ordersid);
	//发出被接受超时
	void modifyOrderState2(int ordersid);
	//通过ordersid获取reward
	double getRewardById(int ordersid);
	//修改账单个人信息 昵称
	void modifyOrderNickname(@Param("nickname")String nickname,@Param("ordersid")int ordersid);
	//修改账单个人信息 头像
	void modifyOrderReleaserhead(@Param("releaserhead")String releaserhead, @Param("ordersid")int ordersid);
	//通过releaserid返回订单
	List<Orders> selectByReleaserId(int releaser_id);
	//设置私有权
	void setPrivateRight(int ordersid);
	//设置订单评分
	void setGradeOfOrder(@Param("ordersid")int ordersid,@Param("grade")int grade);
}
