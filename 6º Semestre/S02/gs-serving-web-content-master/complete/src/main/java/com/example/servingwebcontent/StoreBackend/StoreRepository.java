package com.example.servingwebcontent.StoreBackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.ResultSet;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByUsername(String chars);

    @Query(value = "delete  from store where date < now() - interval '1hours 0 minutes'", nativeQuery = true)
    List<Store> clearOldPlaces();

    @Query(value = "SELECT row_number() over() as id, store_name, latitude, longitude, sum(empty) empty, sum(enough_space) enough_space, sum(full_space) full_space, sum(queue) queue , now() as date, 'user' as username FROM store GROUP  BY 2,3,4;", nativeQuery = true)
    List<Store> selectItems();

    @Query(value = "SELECT  row_number() over() as id,  now() as date, 'user' as username, sum(empty) empty, sum(enough_space) enough_space, sum(full_space) full_space, latitude, longitude, sum(queue) queue, store_name,  \n" +
            "SQRT(POW(69.1 * (latitude - :lat ), 2)+POW(69.1 * (:longit - longitude) * COS(latitude / 57.3), 2)) as distance\n" +
            "FROM public.store group by store_name, latitude, longitude order by distance\n", nativeQuery = true)
    List<Store> findByLocation(@Param("lat")float latitude, @Param("longit")float longitude);
}