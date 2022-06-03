package com.smartf.comu.service;

import com.smartf.comu.dto.ReceiveGoodsDto;
import com.smartf.comu.entity.ReceiveGoods;
import com.smartf.comu.entity.ReceiveGoodsLog;
import com.smartf.comu.repository.ReceiveGoodsLogRepository;
import com.smartf.comu.repository.ReceiveGoodsRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ReceiveGoodsService {

    private final ReceiveGoodsRepository receiveGoodsRepository;
    private final ReceiveGoodsLogRepository receiveGoodsLogRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ReceiveGoodsService(ReceiveGoodsRepository receiveGoodsRepository, ReceiveGoodsLogRepository receiveGoodsLogRepository, BCryptPasswordEncoder passwordEncoder) {
        this.receiveGoodsRepository = receiveGoodsRepository;
        this.receiveGoodsLogRepository = receiveGoodsLogRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean loginReceiveGoods(String username, String password) {

        Optional<ReceiveGoods> receiveGoods = receiveGoodsRepository.findByUsername(username);

        // 해당 유저가 없을 경우 false
        if (receiveGoods.orElse(null) == null) return false;

        // 패스워드가 다를 경우 false
        // 추후 bcrypt 등으로 변경
        if (!receiveGoods.orElse(null).getPassword().equals(password)) {
            return false;
        }

        return true;
    }

    public Boolean isMatchTank(ReceiveGoodsDto receiveGoodsDto) {

        Optional<ReceiveGoods> receiveGoods = receiveGoodsRepository.findByUsername(receiveGoodsDto.getUsername());

        // 해당 유저가 없을 경우 false
        if (receiveGoods.orElse(null) == null) return false;


        if (!receiveGoods.orElse(null).getTankId().equals(receiveGoodsDto.getTankId())) {
            return false;
        }

        return true;
    }

    public ReceiveGoodsLog insertReceiveGoodsLog(ReceiveGoodsLog receiveGoodsLog) {
        return receiveGoodsLogRepository.save(receiveGoodsLog);
    }

    public void deleteReceiveGoods(String username) {
        System.out.println(username);
        receiveGoodsRepository.deleteByUsername(username);
    }

}
