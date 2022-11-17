package com.vroong.msabootcamp.adapter.in.rest.mapper;

import com.vroong.msabootcamp.domain.Song;
import com.vroong.msabootcamp.rest.SongDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {

  public List<SongDto> toDto(Set<Song> entityList) {
    final List<SongDto> dtoList = new ArrayList<>();
    if (entityList == null) {
      return dtoList;
    }

    for (Song entity : entityList) {
      dtoList.add(toDto(entity));
    }

    return dtoList;
  }

  public SongDto toDto(Song entity) {
    if (entity == null) {
      return new SongDto();
    }

    return new SongDto()
        .songId(entity.getId())
        .title(entity.getTitle())
        .playTime(entity.toString());
  }
}
