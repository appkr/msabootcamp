package com.vroong.msabootcamp.application.port.in;

import com.vroong.msabootcamp.application.port.out.AlbumRepository;
import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.domain.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumService {

  private final AlbumRepository repository;

  public Album createAlbum(Album album) {
    return repository.save(album);
  }

  @Transactional(readOnly = true)
  public Page<Album> listAlbums(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public void addSong(Long albumId, Song song) {
    repository.findById(albumId)
        .ifPresent(song::setAlbum);
  }

  public void removeSong(Long albumId, Long songId) {
    repository.findById(albumId)
        .ifPresent(album -> album.removeSong(songId));
  }
}
