package com.sinosoft.nimg.util;

import com.sinosoft.nimg.plugin.UpdateTimeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AutoConfiguration {
    @Bean
    public UpdateTimeInterceptor updateTimeInterceptor() {
        return new UpdateTimeInterceptor();
    }
}
