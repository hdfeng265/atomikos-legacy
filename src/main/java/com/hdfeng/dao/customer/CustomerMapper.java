package com.hdfeng.dao.customer;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.hdfeng.domain.customer.Customer;
@Mapper
public interface CustomerMapper {

	
	@Insert({ "insert into customer", "(name,age)",
			"values (#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})"})
	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	int insertCustomer(Customer customer);

	/**
	 * 查询全部信息
	 * 
	 * @return
	 */
	@Select({ "select id,name,age", "from customer" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
		@Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER)})
	List<Customer> findAll();
}
