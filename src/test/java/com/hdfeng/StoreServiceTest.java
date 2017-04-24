package com.hdfeng;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hdfeng.dao.customer.CustomerMapper;
import com.hdfeng.dao.order.OrderMapper;
import com.hdfeng.domain.customer.Customer;
import com.hdfeng.domain.order.Order;
import com.hdfeng.exception.NoRollbackException;
import com.hdfeng.exception.StoreException;
import com.hdfeng.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class StoreServiceTest {

	@Autowired
	private StoreService storeService;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Test(expected=Exception.class)
	@Transactional
	public void testStoreCustomer() throws Exception {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		storeService.storeCustomer(c);

		Assert.assertNotNull(c.getId());

		Assert.assertEquals(1, customerMapper.findAll().size());
	}
	
	@Test
	@Transactional
	public void testStore() throws Exception {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Order o = new Order();
		o.setCode(1);
		o.setQuantity(7);

		storeService.store(c, o);

		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(o.getId());

		Assert.assertEquals(1, customerMapper.findAll().size());
		Assert.assertEquals(1, orderMapper.findAll().size());
	}

	@Test(expected = StoreException.class)
	public void testStoreWithStoreException() throws StoreException {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Order o = new Order();
		o.setCode(1);
		o.setQuantity(7);

		Assert.assertEquals(0, customerMapper.findAll().size());
		Assert.assertEquals(0, orderMapper.findAll().size());

		storeService.storeWithStoreException(c, o);
	}

	@Test(expected = NoRollbackException.class)
	@Transactional
	public void testStoreWithNoRollbackException() throws NoRollbackException {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Order o = new Order();
		o.setCode(1);
		o.setQuantity(7);

		Assert.assertEquals(0, customerMapper.findAll().size());
		Assert.assertEquals(0, orderMapper.findAll().size());

		try {
			storeService.storeWithNoRollbackException(c, o);
		} catch (NoRollbackException e) {
			e.printStackTrace();
			Assert.assertEquals(1, customerMapper.findAll().size());
			Assert.assertEquals(1, orderMapper.findAll().size());
			throw e;
		}
	}

}
