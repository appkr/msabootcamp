package com.vroong.msabootcamp.config;

import com.vroong.msabootcamp.application.port.in.ConsumerChannel;
import com.vroong.msabootcamp.application.port.out.ProducerChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = {ProducerChannel.class, ConsumerChannel.class})
public class MessagingConfiguration {
}
