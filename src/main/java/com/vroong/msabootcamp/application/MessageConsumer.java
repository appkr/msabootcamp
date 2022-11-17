package com.vroong.msabootcamp.application;

import com.vroong.msabootcamp.application.port.in.ConsumerChannel;
import com.vroong.msabootcamp.domain.Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

  @StreamListener(value = ConsumerChannel.CHANNEL, condition = "headers['messageType']=='ALBUM_CREATED'")
  public void onReceiveMessage(Album message) {
    log.info("Message received: {}", message);
  }
}
