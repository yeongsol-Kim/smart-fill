package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.CarService;
import hello.hellospring.service.FillLogService;
import hello.hellospring.service.MemberService;
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



    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

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
