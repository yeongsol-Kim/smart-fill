package com.smartf.comu.repository;


import com.smartf.comu.entity.ReceiveGoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiveGoodsRepository extends JpaRepository<ReceiveGoods, Long> {

    Optional<ReceiveGoods> findByUsername(String username);
    void deleteByUsername(String username);

}
