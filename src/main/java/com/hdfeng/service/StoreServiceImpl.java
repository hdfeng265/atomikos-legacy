package com.hdfeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdfeng.dao.customer.CustomerMapper;
import com.hdfeng.dao.order.OrderMapper;
import com.hdfeng.domain.customer.Customer;
import com.hdfeng.domain.order.Order;
import com.hdfeng.exception.NoRollbackException;
import com.hdfeng.exception.StoreException;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@SuppressWarnings("unused")
	@Transactional
	public void storeCustomer(Customer customer) throws Exception {
		customerMapper.insertCustomer(customer);
		int i=1/0;
	}
	
	@Transactional
	public void store(Customer customer, Order order) {
		customerMapper.insertCustomer(customer);
		orderMapper.insertOrder(order);
	}

	@Transactional(rollbackFor = StoreException.class)
	public void storeWithStoreException(Customer customer, Order order) throws StoreException {
		customerMapper.insertCustomer(customer);
		orderMapper.insertOrder(order);
		throw new StoreException();
	}

	@Transactional(noRollbackFor = NoRollbackException.class, rollbackFor = StoreException.class)
	public void storeWithNoRollbackException(Customer customer, Order order) throws NoRollbackException {
		customerMapper.insertCustomer(customer);
		orderMapper.insertOrder(order);
		throw new NoRollbackException();
	}

}
