package com.bank.app.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "branch")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Branch {

    @Id
    private int branchCode;

    private String branchName;
    private String branchType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Customer> customerList;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bank.class)
    @JoinColumn(name = "bank_id")
    @JsonBackReference
    private Bank bank;

    public Branch() {
    }

    public Branch(int branchCode, String branchName, String branchType, Bank bank, Address address,
                  List<Customer> customerList) {
        super();
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.branchType = branchType;
        this.bank = bank;
        this.address = address;
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

/*	@Override
	public String toString() {
		return "Branch [branchCode=" + branchCode + ", branchName=" + branchName + ", branchType=" + branchType
				+ ", address=" + address + "]";
	}*/

    @Override
    public String toString() {
        return " {" +
                "branchCode:" + branchCode +
                ", branchName:'" + branchName + '\'' +
                ", branchType:'" + branchType + '\'' +
                ", bank:" + bank  +
                ", address:" + address +
                '}';
    }

}

