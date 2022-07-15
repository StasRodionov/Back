package com.trade_accounting.config;

import com.trade_accounting.models.dto.indicators.AuditDto;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Queue;
import java.util.Collections;

@EnableJms
@Configuration
public class ActiveMQConfig {

    @Bean("auditCreateOrUpdateQueue")
    public Queue auditCreateOrUpdateQueue() {
        return new ActiveMQQueue("auditCreateOrUpdateQueue");
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        var converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setTypeIdMappings(Collections.singletonMap("auditDto", AuditDto.class));
        return converter;
    }
}
