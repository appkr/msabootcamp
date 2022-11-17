package com.vroong.msabootcamp.adapter.in.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vroong.msabootcamp.config.Constants;
import com.vroong.msabootcamp.rest.AlbumApiController;
import com.vroong.msabootcamp.rest.AlbumApiDelegate;
import com.vroong.msabootcamp.support.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest
@WithMockUser
class AlbumApiDelegateImplTest {

  private MockMvc mvc;

  @Autowired
  private AlbumApiDelegate apiDelegate;

  @Test
  void createAlbum() throws Exception {
    final ResultActions res = mvc.perform(
        post("/api/albums")
            .contentType(Constants.V1_MEDIA_TYPE)
            .content(TestUtils.convertObjectToString(Fixtures.anAlbumDto()))
            .characterEncoding("utf-8")
    ).andDo(print());

    res.andExpect(status().is2xxSuccessful());
  }

  @Test
  void listAlbums() throws Exception {
    final ResultActions res = mvc.perform(
        get("/api/albums")
            .accept(Constants.V1_MEDIA_TYPE)
            .characterEncoding("utf-8")
    ).andDo(print());

    res.andExpect(status().is2xxSuccessful());
  }

  @Test
  void addSong() throws Exception {
    final ResultActions res = mvc.perform(
        post("/api/albums/{albumId}/songs", 1L)
            .contentType(Constants.V1_MEDIA_TYPE)
            .content(TestUtils.convertObjectToString(Fixtures.aSongDto()))
            .characterEncoding("utf-8")
    ).andDo(print());

    res.andExpect(status().is2xxSuccessful());
  }

  @Test
  void removeSong() throws Exception {
    final ResultActions res = mvc.perform(
        delete("/api/albums/{albumId}/songs/{songId}", 1L, 1L)
            .contentType(Constants.V1_MEDIA_TYPE)
            .characterEncoding("utf-8")
    ).andDo(print());

    res.andExpect(status().is2xxSuccessful());
  }

  @BeforeEach
  void setup() {
    this.mvc = MockMvcBuilders
        .standaloneSetup(new AlbumApiController(apiDelegate))
        .addFilters(new CharacterEncodingFilter("utf-8", true))
        .build();
  }
}
