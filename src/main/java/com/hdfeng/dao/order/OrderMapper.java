package com.hdfeng.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.hdfeng.domain.order.Order;
@Mapper
public interface OrderMapper {

	
	@Insert({ "insert into orders", "(code,quantity)",
			"values (#{code,jdbcType=INTEGER},#{quantity,jdbcType=INTEGER})"})
	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	int insertOrder(Order order);

	/**
	 * 查询全部信息
	 * 
	 * @return
	 */
	@Select({ "select id,code,quantity", "from orders" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "code", property = "code", jdbcType = JdbcType.INTEGER),
		@Result(column = "quantity", property = "quantity", jdbcType = JdbcType.INTEGER)})
	List<Order> findAll();
}
