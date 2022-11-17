package com.vroong.msabootcamp.application.port.out;

import com.vroong.msabootcamp.domain.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer, Long> {
}
