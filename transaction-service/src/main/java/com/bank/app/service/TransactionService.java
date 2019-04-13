package com.bank.app.service;

import com.bank.app.domain.Transaction;

public interface TransactionService {

    public Transaction getTransactionById(int transactionId);

    public String withdrawlsAndDeposit(Transaction transaction);

    public String accountToAccountTransfer(Transaction transaction);
}
