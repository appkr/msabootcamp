package com.vroong.msabootcamp.adapter.in.rest;

import com.vroong.msabootcamp.adapter.in.rest.mapper.AlbumFactory;
import com.vroong.msabootcamp.adapter.in.rest.mapper.AlbumMapper;
import com.vroong.msabootcamp.adapter.in.rest.mapper.SongFactory;
import com.vroong.msabootcamp.application.port.in.AlbumService;
import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.domain.Song;
import com.vroong.msabootcamp.rest.AlbumApiDelegate;
import com.vroong.msabootcamp.rest.AlbumDto;
import com.vroong.msabootcamp.rest.AlbumListDto;
import com.vroong.msabootcamp.rest.PageDto;
import com.vroong.msabootcamp.rest.SongDto;
import com.vroong.msabootcamp.support.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumApiDelegateImpl implements AlbumApiDelegate {

  final AlbumFactory albumFactory;
  final SongFactory songFactory;
  final AlbumMapper mapper;
  final AlbumService service;

  @Override
  public ResponseEntity<Void> createAlbum(AlbumDto albumDto) {
    final Album album = service.createAlbum(albumFactory.createFrom(albumDto));

    return ResponseEntity
        .created(HeaderUtils.uri("/{albumId}", album.getId()))
        .build();
  }

  @Override
  public ResponseEntity<AlbumListDto> listAlbums(Integer page, Integer size) {
    final Pageable pageable = PageRequest.of(page - 1, size);
    final Page<Album> pagedEntityList = service.listAlbums(pageable);
    final AlbumListDto dto = new AlbumListDto()
        .data(mapper.toDto(pagedEntityList.getContent()))
        .page(new PageDto()
            .number(pagedEntityList.getNumber() + 1)
            .size(pagedEntityList.getSize())
            .totalElements(pagedEntityList.getTotalElements())
            .totalPages(pagedEntityList.getTotalPages()));

    return ResponseEntity.ok(dto);
  }

  @Override
  public ResponseEntity<Void> addSong(Long albumId, SongDto songDto) {
    final Song song = songFactory.createFrom(songDto);
    service.addSong(albumId, song);

    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> removeSong(Long albumId, Long songId) {
    service.removeSong(albumId, songId);
    return ResponseEntity.noContent().build();
  }
}
