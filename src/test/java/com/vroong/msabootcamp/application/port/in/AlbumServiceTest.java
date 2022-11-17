package com.vroong.msabootcamp.application.port.in;

import static com.vroong.msabootcamp.domain.Fixtures.aSong;
import static com.vroong.msabootcamp.domain.Fixtures.anAlbum;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.vroong.msabootcamp.application.port.out.AlbumRepository;
import com.vroong.msabootcamp.domain.Album;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
@Slf4j
class AlbumServiceTest {

  @MockBean
  AlbumRepository mockRepository;
  @Autowired
  AlbumService service;

  @Test
  void createAlbum() {
    when(mockRepository.save(any(Album.class))).thenReturn(anAlbum());

    final Album album = service.createAlbum(anAlbum());

    log.info("{}", album);
  }

  @Test
  void listAlbums() {
    final Pageable pageable = PageRequest.of(0, 1);
    Page<Album> pagedEntityList = new PageImpl(List.of(anAlbum()), pageable, 1L);
    when(mockRepository.findAll(any(Pageable.class))).thenReturn(pagedEntityList);

    pagedEntityList = service.listAlbums(pageable);

    log.info("{}", pagedEntityList);
  }

  @Test
  void addSong() {
    when(mockRepository.findById(anyLong())).thenReturn(Optional.of(anAlbum()));

    service.addSong(1L, aSong());
  }

  @Test
  void removeSong() {
    when(mockRepository.findById(anyLong())).thenReturn(Optional.of(anAlbum()));

    service.removeSong(1L, 1L);
  }
}
