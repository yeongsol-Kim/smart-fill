package com.smartf.comu.controller;

import com.smartf.comu.dto.ReceiveGoodsDto;
import com.smartf.comu.dto.ReceiveGoodsLogDto;
import com.smartf.comu.entity.ReceiveGoods;
import com.smartf.comu.entity.ReceiveGoodsLog;
import com.smartf.comu.service.ReceiveGoodsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ReceiveGoodsController {

    private final ReceiveGoodsService receiveGoodsService;

    public ReceiveGoodsController(ReceiveGoodsService receiveGoodsService) {
        this.receiveGoodsService = receiveGoodsService;
    }

    @PostMapping("/receive-goods/login")
    public Boolean receiveGoodsLogin(@Valid @RequestBody ReceiveGoodsDto receiveGoods) {
        return receiveGoodsService.loginReceiveGoods(receiveGoods.getUsername(), receiveGoods.getPassword());
    }

    @PostMapping("/receive-goods/is-tank-id")
    public Boolean matchTank(@Valid @RequestBody ReceiveGoodsDto receiveGoodsDto) {
        return receiveGoodsService.isMatchTank(receiveGoodsDto);
    }

    @PostMapping("/receive-goods")
    public ReceiveGoodsLogDto insertReceiveLog(@Valid @RequestBody ReceiveGoodsLogDto receiveGoodsLogDto) {
//        if (matchTank) {
//            System.out.printf("asd");
//        }

        ReceiveGoodsLog receiveGoodsLog = ReceiveGoodsLog.builder()
                .receiveGoodsDatetime(LocalDateTime.now())
                .companyId(receiveGoodsLogDto.getCompanyId())
                .amount(receiveGoodsLogDto.getAmount())
                .tankId(receiveGoodsLogDto.getTankId())
                .build();

        ReceiveGoodsLog returnReceiveGoodsLog = receiveGoodsService.insertReceiveGoodsLog(receiveGoodsLog);


        ReceiveGoodsLogDto returnReceiveGoodsLogDto = ReceiveGoodsLogDto.builder()
                .ReceiveGoodsDatetime(returnReceiveGoodsLog.getReceiveGoodsDatetime())
                .amount(returnReceiveGoodsLog.getAmount())
                .companyId(returnReceiveGoodsLog.getCompanyId())
                .tankId(returnReceiveGoodsLog.getTankId())
                .build();

        return returnReceiveGoodsLogDto;
    }


    @DeleteMapping("/receive-goods")
    public void deleteReceiveGoods(@Valid @RequestBody ReceiveGoodsDto receiveGoodsDto) {
        System.out.println(receiveGoodsDto.getUsername());
        receiveGoodsService.deleteReceiveGoods(receiveGoodsDto.getUsername());
    }

}
