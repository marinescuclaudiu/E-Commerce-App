package com.cmarinescu.backend.common.repository;

import com.cmarinescu.backend.common.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByStreetAndCityAndZipCodeAndCountry(String street, String city, String zipCode, String country);
}
