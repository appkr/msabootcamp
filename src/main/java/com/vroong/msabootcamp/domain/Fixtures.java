package com.vroong.msabootcamp.domain;

import static com.vroong.msabootcamp.config.Constants.UNKNOWN_USER_ID;

import java.time.Duration;
import java.time.Instant;

public class Fixtures {

  public static final Long DEFAULT_ID = 1L;
  public static final String DEFAULT_SINGER_NAME = "이문세";
  public static final String DEFAULT_ALBUM_TITLE = "이문세 5집";
  public static final String DEFAULT_PUBLISHED_DATE = "1988-01-01T00:00:00+09:00";
  public static final String DEFAULT_SONG_TITLE = "시를 위한 시";
  public static final String DEFAULT_PLAY_TIME = "PT4M1S";

  public static Singer aSinger() {
    final Singer singer = new Singer();
    singer.setName(DEFAULT_SINGER_NAME);

    return singer;
  }

  public static Song aSong() {
    final Song song = new Song();
    song.setTitle(DEFAULT_SONG_TITLE);
    song.setPlayTime(Duration.parse(DEFAULT_PLAY_TIME));

    return song;
  }

  public static Album anAlbum() {
    final Album album = new Album();
    album.setTitle(DEFAULT_ALBUM_TITLE);
    album.setPublished(Instant.parse(DEFAULT_PUBLISHED_DATE));
    album.setSinger(aSinger());
    album.addSong(aSong());
    album.setCreatedBy(UNKNOWN_USER_ID);
    album.setUpdatedBy(UNKNOWN_USER_ID);

    return album;
  }
}
