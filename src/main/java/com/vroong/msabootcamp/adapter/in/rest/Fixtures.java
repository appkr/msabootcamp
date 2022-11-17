package com.vroong.msabootcamp.adapter.in.rest;

import static com.vroong.msabootcamp.domain.Fixtures.*;

import com.vroong.msabootcamp.rest.AlbumDto;
import com.vroong.msabootcamp.rest.PageDto;
import com.vroong.msabootcamp.rest.SingerDto;
import com.vroong.msabootcamp.rest.SongDto;
import java.time.OffsetDateTime;
import java.util.List;

public class Fixtures {

  public static PageDto aPageDto() {
    return new PageDto()
        .number(1)
        .size(1)
        .totalElements(1L)
        .totalPages(1);
  }

  public static SingerDto aSingerDto() {
    return new SingerDto()
        .singerId(DEFAULT_ID)
        .name(DEFAULT_SINGER_NAME);
  }

  public static SongDto aSongDto() {
    return new SongDto()
        .songId(DEFAULT_ID)
        .title(DEFAULT_SONG_TITLE)
        .playTime(DEFAULT_PLAY_TIME);
  }

  public static AlbumDto anAlbumDto() {
    return new AlbumDto()
        .albumId(DEFAULT_ID)
        .title(DEFAULT_ALBUM_TITLE)
        .published(OffsetDateTime.parse(DEFAULT_PUBLISHED_DATE))
        .singer(aSingerDto())
        .songs(List.of(aSongDto()));
  }
}
