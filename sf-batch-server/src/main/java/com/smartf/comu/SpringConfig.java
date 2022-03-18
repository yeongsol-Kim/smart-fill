package com.smartf.comu;

import com.smartf.comu.repository.*;
import com.smartf.comu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final SpringDataJpaCarRepository carRepository;

    @Autowired
    public SpringConfig(SpringDataJpaCarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Bean
    public CarService carService() {
        return new CarService(carRepository);
    }

}
