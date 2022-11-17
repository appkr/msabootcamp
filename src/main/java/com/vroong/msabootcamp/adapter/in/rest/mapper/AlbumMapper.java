package com.vroong.msabootcamp.adapter.in.rest.mapper;

import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.rest.AlbumDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumMapper {

  final DateTimeMapper dateTimeMapper;
  final SingerMapper singerMapper;
  final SongMapper songMapper;

  public AlbumDto toDto(Album entity) {
    if (entity == null) {
      return new AlbumDto();
    }

    return new AlbumDto()
        .albumId(entity.getId())
        .title(entity.getTitle())
        .published(dateTimeMapper.toOffsetDateTime(entity.getPublished()))
        .singer(singerMapper.toDto(entity.getSinger()))
        .songs(songMapper.toDto(entity.getSongs()));
  }

  public List<AlbumDto> toDto(List<Album> entityList) {
    final List<AlbumDto> dtoList = new ArrayList<>();
    if (entityList == null) {
      return dtoList;
    }

    for (Album entity : entityList) {
      dtoList.add(toDto(entity));
    }

    return dtoList;
  }
}
