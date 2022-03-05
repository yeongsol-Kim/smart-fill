package com.smartf.comu;

import com.smartf.comu.repository.*;
import com.smartf.comu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final SpringDataJpaMemberRepository memberRepository;
    private final SpringDataJpaCarRepository carRepository;
    private final FillLogRepository fillLogRepository;

    @Autowired
    public SpringConfig(SpringDataJpaMemberRepository memberRepository, SpringDataJpaCarRepository carRepository, FillLogRepository fillLogRepository) {
        this.memberRepository = memberRepository;
        //this.carRepository = null;
        this.carRepository = carRepository;
        this.fillLogRepository = fillLogRepository;
    }

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository, passwordEncoder);
//    }

    @Bean
    public CarService carService() {
        return new CarService(carRepository);
    }

//    @Bean
//    public FillLogService fillLogService() {
//        return new FillLogService(fillLogRepository());
//    }

//    public FillLogRepository fillLogRepository() {
//        return new MemoryFillLogRepository();
//    }

}
