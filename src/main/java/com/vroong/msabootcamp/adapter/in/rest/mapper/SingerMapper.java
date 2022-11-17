package com.vroong.msabootcamp.adapter.in.rest.mapper;

import com.vroong.msabootcamp.domain.Singer;
import com.vroong.msabootcamp.rest.SingerDto;
import org.springframework.stereotype.Component;

@Component
public class SingerMapper {

  public SingerDto toDto(Singer entity) {
    if (entity == null) {
      return new SingerDto();
    }

    return new SingerDto()
        .singerId(entity.getId())
        .name(entity.getName());
  }
}
