package com.vroong.msabootcamp.adapter.in.rest;

import static com.vroong.msabootcamp.domain.Fixtures.aSinger;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vroong.msabootcamp.application.port.in.SingerService;
import com.vroong.msabootcamp.config.Constants;
import com.vroong.msabootcamp.rest.SingerApiController;
import com.vroong.msabootcamp.rest.SingerApiDelegate;
import com.vroong.msabootcamp.support.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class SingerApiDelegateImplTest {

  private MockMvc mvc;

  @Autowired
  private SingerApiDelegate apiDelegate;
  @MockBean
  private SingerService mockService;

  @Test
  @WithMockUser
  void createSinger() throws Exception {
    when(mockService.createSinger(anyString())).thenReturn(aSinger());

    final ResultActions res = mvc.perform(
        post("/api/singers")
            .contentType(Constants.V1_MEDIA_TYPE)
            .content(TestUtils.convertObjectToString(Fixtures.aSingerDto()))
            .characterEncoding("utf-8")
    ).andDo(print());

    res.andExpect(status().is2xxSuccessful());
  }

  @BeforeEach
  void setup() {
    this.mvc = MockMvcBuilders
        .standaloneSetup(new SingerApiController(apiDelegate))
        .build();
  }
}
