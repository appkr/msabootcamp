package com.vroong.msabootcamp.adapter.in.rest;

import com.vroong.msabootcamp.rest.AlbumDto;
import com.vroong.msabootcamp.rest.PageDto;
import com.vroong.msabootcamp.rest.SingerDto;
import com.vroong.msabootcamp.rest.SongDto;
import java.time.OffsetDateTime;
import java.util.List;

public class Fixtures {

  static final Long DEFAULT_ID = 1L;
  static final String DEFAULT_SINGER_NAME = "이문세";
  static final String DEFAULT_ALBUM_TITLE = "이문세 5집";
  static final String DEFAULT_PUBLISHED_DATE = "1988-01-01T00:00:00+09:00";
  static final String DEFAULT_SONG_TITLE = "시를 위한 시";
  static final String DEFAULT_PLAY_TIME = "PT4M1S";

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
