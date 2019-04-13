package com.bank.app.Domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "branch")
@Data
@Configuration
@ComponentScan
public class Branch {

    @Id
    private int branchCode;

    private String branchName;
    private String branchType;

    @OneToMany(mappedBy = "branch")
    @JsonManagedReference
    private List<Customer> customerList;

    public Branch() {
        super();
    }

    public Branch(int branchCode, String branchName, String branchType, List<Customer> customerList) {
        super();
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.branchType = branchType;
        this.customerList = customerList;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}