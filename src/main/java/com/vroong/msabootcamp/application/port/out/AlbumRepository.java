package com.vroong.msabootcamp.application.port.out;

import com.vroong.msabootcamp.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
