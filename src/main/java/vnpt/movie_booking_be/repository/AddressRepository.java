package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vnpt.movie_booking_be.models.Address;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(value = "SELECT a.* FROM address a JOIN cinema c ON a.id = c.address_id" ,nativeQuery = true)
    List<Address> findAddressHasCinema();
    @Query("SELECT DISTINCT a.city FROM Address a")
    List<String> findDistinctCities();

    @Query("SELECT DISTINCT a.district FROM Address a WHERE a.city = :city")
    List<String> findDistrictsByCity(@Param("city") String city);

    @Query("SELECT DISTINCT a.ward FROM Address a WHERE a.city = :city AND a.district = :district")
    List<String> findWardsByCityAndDistrict(@Param("city") String city, @Param("district") String district);

    @Query("SELECT DISTINCT a.street FROM Address a WHERE a.city = :city AND a.district = :district AND a.ward = :ward")
    List<String> findStreetsByCityDistrictAndWard(@Param("city") String city, @Param("district") String district, @Param("ward") String ward);
}
