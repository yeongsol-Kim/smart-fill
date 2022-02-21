package com.smartf.comu;

import com.smartf.comu.repository.*;
import com.smartf.comu.service.CarService;
import com.smartf.comu.service.FillLogService;
import com.smartf.comu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final SpringDataJpaMemberRepository memberRepository;
    private final SpringDataJpaCarRepository carRepository;

    @Autowired
    public SpringConfig(SpringDataJpaMemberRepository memberRepository, SpringDataJpaCarRepository carRepository) {
        this.memberRepository = memberRepository;
        //this.carRepository = null;
        this.carRepository = carRepository;
    }

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository, passwordEncoder);
//    }

    @Bean
    public CarService carService() {
        return new CarService(carRepository);
    }

    @Bean
    public FillLogService fillLogService() {
        return new FillLogService(fillLogRepository());
    }

    public FillLogRepository fillLogRepository() {
        return new MemoryFillLogRepository();
    }

}
