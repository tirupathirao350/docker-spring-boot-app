package com.bank.app.Repository;

import com.bank.app.Domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

 /*   @Modifying
    @Query("DELETE FROM Address WHERE address_id = :address_id")
    void deleteById(@Param("address_id") int addressId);
*/
}
