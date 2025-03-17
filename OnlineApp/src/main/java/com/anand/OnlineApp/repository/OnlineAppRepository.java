package com.anand.OnlineApp.repository;

import com.anand.OnlineApp.entity.OnlineApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OnlineAppRepository extends JpaRepository<OnlineApp,Long> {
    Optional<OnlineApp> findById(long id);
    OnlineApp findByOwnerId(Long id);
    @Query(
            "select o from OnlineApp o where "+
                    "(lower(o.city) like lower(concat('%',:keyword,'%')) OR " +
                    "lower(o.name) like lower(concat('%',:keyword,'%')) OR "
                    + "lower(o.address) like lower(concat('%',:keyword,'%')))"
    )
    List<OnlineApp> searchOnlineApp(@Param("keyword") String keyword);
}
