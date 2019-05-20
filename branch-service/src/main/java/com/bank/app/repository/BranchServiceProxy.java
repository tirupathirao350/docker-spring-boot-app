package com.bank.app.repository;

import com.bank.app.domain.Bank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@FeignClient(name="bank-service")
public interface BranchServiceProxy {

    @RequestMapping("/bank/getAllBanks")
    public Collection<Bank> findAll();
}
