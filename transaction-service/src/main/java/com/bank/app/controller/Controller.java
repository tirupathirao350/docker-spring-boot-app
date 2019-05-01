package com.bank.app.controller;

import com.bank.app.domain.Transaction;
import com.bank.app.serviceImpl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class Controller {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

	/*@Autowired
	Producer producer;*/

    @RequestMapping(value = "/getTransactionById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Transaction getTransactionById(@PathVariable Integer id) {
        return transactionServiceImpl.getTransactionById(id);
    }

    @RequestMapping(value = "/WithDrawlAndDeposit", method = RequestMethod.POST)
    public String WithDrawlAndDeposit(@RequestBody Transaction transaction) {
        //producer.send(transaction);
        return transactionServiceImpl.withdrawlsAndDeposit(transaction);
    }

    @RequestMapping(value = "/accToAccTransfer", method = RequestMethod.POST)
    public String accToAccTransfer(@RequestBody Transaction transaction) {
        //	producer.sendTransaction(transaction);
        return transactionServiceImpl.accountToAccountTransfer(transaction);
    }

}
