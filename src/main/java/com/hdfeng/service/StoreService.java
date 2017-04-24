package com.hdfeng.service;

import com.hdfeng.domain.customer.Customer;
import com.hdfeng.domain.order.Order;
import com.hdfeng.exception.NoRollbackException;
import com.hdfeng.exception.StoreException;

public interface StoreService {
	
	void storeCustomer(Customer customer) throws Exception;
	
	void store(Customer customer, Order order) throws Exception;
	
	void storeWithStoreException(Customer customer, Order order) throws StoreException;
	
	void storeWithNoRollbackException(Customer customer, Order order) throws NoRollbackException;

}
