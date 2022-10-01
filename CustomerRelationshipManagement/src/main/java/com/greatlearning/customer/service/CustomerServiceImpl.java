package com.greatlearning.customer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.customer.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	@Transactional
	public List<Customer> findAll() {

		List<Customer> customerList = null;
		try {
			// find all the records from the database table
			customerList = session.createQuery("from Customer").list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerList;
	}

	@Transactional
	public Customer findCustomer(int id) {
		Customer customer = null;
		try {
			customer = session.get(Customer.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customer;
	}

	@Transactional
	public void save(Customer customer) {

		try {
			Transaction tx = session.beginTransaction();

			session.saveOrUpdate(customer);

			
			if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
			    tx.commit();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional
	public void deleteCustomer(int id) {

		try {
			Transaction tx = session.beginTransaction();

			// get transaction
			Customer book = session.get(Customer.class, id);

			// delete record
			session.delete(book);
			
			if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
			    tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}