package com.vroong.msabootcamp.adapter.in.rest;

import static com.vroong.msabootcamp.domain.Fixtures.anAlbum;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vroong.msabootcamp.application.port.in.AlbumService;
import com.vroong.msabootcamp.config.Constants;
import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.domain.Song;
import com.vroong.msabootcamp.rest.AlbumApiController;
import com.vroong.msabootcamp.rest.AlbumApiDelegate;
import com.vroong.msabootcamp.support.TestUtils;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  @MockBean
  private AlbumService mockService;

  @Test
  void createAlbum() throws Exception {
    when(mockService.createAlbum(any(Album.class))).thenReturn(anAlbum());

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
    final PageImpl<Album> pagedEntityList = new PageImpl<>(List.of(anAlbum()), PageRequest.of(0, 1), 1L);
    when(mockService.listAlbums(any(Pageable.class))).thenReturn(pagedEntityList);

    final ResultActions res = mvc.perform(
        get("/api/albums")
            .accept(Constants.V1_MEDIA_TYPE)
            .characterEncoding("utf-8")
    ).andDo(print());

    res.andExpect(status().is2xxSuccessful());
  }

  @Test
  void addSong() throws Exception {
    doNothing().when(mockService).addSong(anyLong(), any(Song.class));

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
    doNothing().when(mockService).removeSong(anyLong(), anyLong());

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
