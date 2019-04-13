package com.bank.app.serviceImpl;

import com.bank.app.domain.Account;
import com.bank.app.domain.Transaction;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.TransactionRepository;
import com.bank.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Transactional
    public Transaction getTransactionById(int transactionId) {
        return transactionRepo.getOne(transactionId);
    }

    @Transactional
    public Collection<Transaction> getAllTransactionById(int account_id) {
        return transactionRepo.findAllTransactionsOfAccount(account_id);
    }

    @Transactional
    public String withdrawlsAndDeposit(Transaction transaction) {
        String status = "";
        String transactionDescription = "";
        if (transaction != null) {
            double transactionAmount = transaction.getTransactionAmount();
            Account account = accountRepo.getOne(transaction.getAccount().getAccountId());
            if (account != null) {
                double balanceAmount = account.getBalance();
                String transactionType = transaction.getTransactionType();
                if (transactionType.toUpperCase().equalsIgnoreCase("WITHDRAWL")) {
                    if (balanceAmount > transactionAmount) {
                        balanceAmount = balanceAmount - transactionAmount;
                        account.setBalance(balanceAmount);
                        accountRepo.save(account);
                        transactionDescription = "WithDrawl of amount " + transactionAmount + "is success and current balance is : " + balanceAmount;
                        status = "Successful";
                    } else {
                        transactionDescription = "Amount withDrawl failed due to insufficient funds";
                        status = "Failed";
                    }
                } else if (transactionType.toUpperCase().equalsIgnoreCase("DEPOSIT")) {
                    balanceAmount = balanceAmount + transactionAmount;
                    account.setBalance(balanceAmount);
                    accountRepo.save(account);
                    transactionDescription = "Deposit of amount " + transactionAmount + "Success and current balance is : " + balanceAmount;
                    status = "Successful";
                }

            } else {
                transactionDescription = "Invalid AccountID transaction got rollback";
                status = "Failed";
            }
        } else {
            transactionDescription = "Insufficient details transaction got rollback";
            status = "Failed";
        }
        transaction.setTransactionDescription(transactionDescription);
        transaction.setTransactionStatus(status);

        if (!status.toLowerCase().contains("failed")) {
            transactionRepo.save(transaction);
        }
        return transactionDescription;
    }

    @Transactional
    public String accountToAccountTransfer(Transaction transaction) {
        String status = "";
        String transactionDescription = "";
        if (transaction != null) {
            double transactionAmount = transaction.getTransactionAmount();
            Account fromAccount = accountRepo.getOne(transaction.getAccount().getAccountId());
            Account toAccount = accountRepo.getOne(transaction.getToAccount());
            double fromAccountBalance = fromAccount.getBalance();
            if (fromAccountBalance > transactionAmount) {
                fromAccount.setBalance(fromAccount.getBalance() - transactionAmount);
                accountRepo.save(fromAccount);
                toAccount.setBalance(toAccount.getBalance() + transactionAmount);
                accountRepo.save(toAccount);
                transactionDescription = "Transaction with ACC_ID: " + transaction.getToAccount() + " had successfully " +
                        "completed and current balance is :" + fromAccount.getBalance();
                status = "Successful";
            } else {
                transactionDescription = "Transaction failed due to insufficient funds";
                status = "Failed";
            }
        } else {
            transactionDescription = "Insufficient transaction details..";
            status = "Failed";
        }
        transaction.setTransactionDescription(transactionDescription);
        transaction.setTransactionStatus(status);
        if (!status.toLowerCase().contains("failed")) {
            transactionRepo.save(transaction);
        }
        return transactionDescription;
    }

}
