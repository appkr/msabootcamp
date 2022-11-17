package com.vroong.msabootcamp.application.port.out;

import static com.vroong.msabootcamp.domain.Fixtures.aSinger;
import static org.junit.jupiter.api.Assertions.*;

import com.vroong.msabootcamp.domain.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SingerRepositoryTest {

  @Autowired
  SingerRepository repository;

  @Test
  void save() {
    final Singer singer = aSinger();
    repository.save(singer);
    assertNotNull(singer.getId());
  }
}
