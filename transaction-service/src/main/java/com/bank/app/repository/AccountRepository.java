package com.bank.app.repository;

import com.bank.app.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

/*	@Query
	Account getAccount(int accountId);*/

    @Modifying
    @Query(value = "SELECT * FROM thinkpro.account where (account_id = :account_id)", nativeQuery = true)
    Collection<Account> getAccount(@Param("account_id") String account_id);

    //SELECT * FROM thinkpro.account where account_id=101;


}
