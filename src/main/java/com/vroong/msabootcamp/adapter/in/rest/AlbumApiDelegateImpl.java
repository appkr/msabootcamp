package com.vroong.msabootcamp.adapter.in.rest;

import static com.vroong.msabootcamp.adapter.in.rest.Fixtures.aPageDto;
import static com.vroong.msabootcamp.adapter.in.rest.Fixtures.anAlbumDto;

import com.vroong.msabootcamp.rest.AlbumApiDelegate;
import com.vroong.msabootcamp.rest.AlbumDto;
import com.vroong.msabootcamp.rest.AlbumListDto;
import com.vroong.msabootcamp.rest.SongDto;
import com.vroong.msabootcamp.support.HeaderUtils;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AlbumApiDelegateImpl implements AlbumApiDelegate {

  @Override
  public ResponseEntity<Void> createAlbum(AlbumDto albumDto) {
    return ResponseEntity
        .created(HeaderUtils.uri("/{albumId}", 1L))
        .build();
  }

  @Override
  public ResponseEntity<AlbumListDto> listAlbums(Integer page, Integer size) {
    final AlbumListDto dto = new AlbumListDto()
        .data(List.of(anAlbumDto()))
        .page(aPageDto());

    return ResponseEntity.ok(dto);
  }

  @Override
  public ResponseEntity<Void> addSong(Long albumId, SongDto songDto) {
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> removeSong(Long songId, SongDto songDto) {
    return ResponseEntity.noContent().build();
  }
}
