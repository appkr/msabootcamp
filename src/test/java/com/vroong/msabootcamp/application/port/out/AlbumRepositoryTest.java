package com.vroong.msabootcamp.application.port.out;

import static com.vroong.msabootcamp.domain.Fixtures.anAlbum;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.vroong.msabootcamp.domain.Album;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
class AlbumRepositoryTest {

  @Autowired
  SingerRepository singerRepository;
  @Autowired
  AlbumRepository albumRepository;

  @Test
  void save() throws IOException {
    final Album album = anAlbum();
    singerRepository.save(album.getSinger());

    albumRepository.save(album);

    assertNotNull(album.getId());
    assertNotNull(album.getSinger().getId());
    album.getSongs().stream()
        .forEach(s -> assertNotNull(s.getId()));

    log.info("{}", album);
    // Album(
    //   id=1,
    //   title=이문세 5집,
    //   published=1987-12-31T15:00:00Z,
    //   singer=Singer(
    //     id=1,
    //     name=이문세
    //   ),
    //   songs=[
    //     Song(
    //       id=1,
    //       title=시를 위한 시,
    //       playTime=PT4M1S
    //     )
    //   ]
    // )
  }
}
