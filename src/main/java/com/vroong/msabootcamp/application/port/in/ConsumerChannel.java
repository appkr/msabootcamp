package com.vroong.msabootcamp.application.port.in;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel {

  String CHANNEL = "subscribableChannel";

  @Input
  SubscribableChannel subscribableChannel();
}
