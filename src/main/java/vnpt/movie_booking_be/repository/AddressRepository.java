package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.models.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(value = "SELECT a.* FROM address a JOIN cinema c ON a.id = c.address_id" ,nativeQuery = true)
    List<Address> findAddressHasCinema();
}
