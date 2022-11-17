package com.vroong.msabootcamp.adapter.in.rest;

import com.vroong.msabootcamp.rest.SingerApiDelegate;
import com.vroong.msabootcamp.rest.SingerDto;
import com.vroong.msabootcamp.support.HeaderUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SingerApiDelegateImpl implements SingerApiDelegate {

  @Override
  public ResponseEntity<Void> createSinger(SingerDto singerDto) {
    return ResponseEntity
        .created(HeaderUtils.uri("/{singerId}", 1L))
        .build();
  }
}
