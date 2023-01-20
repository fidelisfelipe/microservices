package com.example.microservices.limitsservices;

import com.example.microservices.limitsservices.bean.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitsConfiguration retriwveLimitsFromConfigurations(){
        return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}
