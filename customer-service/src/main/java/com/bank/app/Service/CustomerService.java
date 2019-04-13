package com.bank.app.Service;

import java.util.List;

import com.bank.app.Domain.Customer;
import org.json.JSONException;

public interface CustomerService {

    public abstract boolean createCustomer(Customer customer);

    public abstract String getCustomerById(int id) throws JSONException;

    public abstract Customer getCustomerInfo(int id);

    public abstract boolean updateCustomer(Customer customer);

    public abstract void deleteCustomer(int CustomerId);

    public abstract List<Customer> getAllCustomers();
}
