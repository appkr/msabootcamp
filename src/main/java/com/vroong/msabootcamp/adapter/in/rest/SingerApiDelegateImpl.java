package com.vroong.msabootcamp.adapter.in.rest;

import com.vroong.msabootcamp.application.port.in.SingerService;
import com.vroong.msabootcamp.domain.Singer;
import com.vroong.msabootcamp.rest.SingerApiDelegate;
import com.vroong.msabootcamp.rest.SingerDto;
import com.vroong.msabootcamp.support.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SingerApiDelegateImpl implements SingerApiDelegate {

  private final SingerService service;

  @Override
  public ResponseEntity<Void> createSinger(SingerDto singerDto) {
    final Singer entity = service.createSinger(singerDto.getName());

    return ResponseEntity
        .created(HeaderUtils.uri("/{singerId}", entity.getId()))
        .build();
  }
}
