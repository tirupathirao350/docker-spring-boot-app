package com.bank.app.Controller;

import java.util.List;

import com.bank.app.Domain.Customer;
import com.bank.app.Repository.CustomerRepository;
import com.bank.app.ServiceImpl.CustomerServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class Controller {

    private static final String Topic = "test";

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private CustomerRepository customerRepo;
/*
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;*/

    JSONObject jsonObj;

    @RequestMapping(value = "/getCustomerById/{customerId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getCustomerById(@PathVariable Integer customerId) throws JSONException {
        String customer = customerServiceImpl.getCustomerById(customerId);
        return customer;
    }

    @RequestMapping(value = "/getCustomerInfo/{customerId}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomerInfo(@PathVariable Integer customerId) {
        Customer customer = customerServiceImpl.getCustomerInfo(customerId);
        return customer;
    }

    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerServiceImpl.getAllCustomers();
    }

    @RequestMapping(value = "/insertCustomer", method = RequestMethod.POST)
    public HttpStatus insertCustomer(@RequestBody Customer customer) {
        // kafkaTemplate.send(Topic, String.valueOf(customer));
        return customerServiceImpl.createCustomer(customer) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
    public HttpStatus updateCustomer(@RequestBody Customer customer) {
        return customerServiceImpl.updateCustomer(customer) ? HttpStatus.LOCKED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/deleteCustomerById/{customerId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerServiceImpl.deleteCustomer(customerId);
    }

}
