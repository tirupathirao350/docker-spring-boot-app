package com.bank.app.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "account")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {

    @Id
    @Column(name = "account_id", nullable = false)
    private int accountId;

    private String type;
    private double balance;
    private String status;
    private Date createdDate;
    private Date closeDate;

    @OneToMany(mappedBy = "account") // , cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonManagedReference // (value="transaction-account")
    private Set<Transaction> transactionSet = new HashSet<Transaction>();

    public Account() {
        super();
    }

    public Account(int accountId, String type, double balance, String status, Date createdDate, Date closeDate,
                   Set<Transaction> transactionSet) {
        super();
        this.accountId = accountId;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.createdDate = createdDate;
        this.closeDate = closeDate;
        this.transactionSet = transactionSet;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Set<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", type=" + type + ", balance=" + balance + ", status=" + status
                + ", createdDate=" + createdDate + ", closeDate=" + closeDate + ", transactionSet=" + transactionSet
                + "]";
    }

}
