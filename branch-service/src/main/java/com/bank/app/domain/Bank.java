package com.bank.app.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "bank")
@Data
@ResponseBody
@JsonIgnoreProperties
public class Bank {

    @Id
    private int bankId;

    private String name;


    @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true,
            targetEntity = Branch.class)
    @JsonManagedReference
    private List<Branch> branchList;

    public Bank() {
        super();
    }

    public Bank(int bankId, String name, List<Branch> branchList) {
        super();
        this.bankId = bankId;
        this.name = name;
        this.branchList = branchList;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    @Override
    public String toString() {
        return " {" +
                "bankId=" + bankId +
                ", name='" + name + '\'' +
                '}';
    }
}
