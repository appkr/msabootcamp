package com.vroong.msabootcamp.adapter.in.rest.mapper;

import com.vroong.msabootcamp.domain.Song;
import com.vroong.msabootcamp.rest.SongDto;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class SongFactory {

  public Song createFrom(SongDto dto) {
    final Song song = new Song();
    song.setTitle(dto.getTitle());
    song.setPlayTime(Duration.parse(dto.getPlayTime()));

    return song;
  }

  public Set<Song> createFrom(List<SongDto> dtoList) {
    final Set<Song> entitySet = new HashSet<>();
    if (dtoList == null) {
      return entitySet;
    }

    for (SongDto dto : dtoList) {
      entitySet.add(createFrom(dto));
    }

    return entitySet;
  }
}
