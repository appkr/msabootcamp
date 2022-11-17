package com.vroong.msabootcamp.adapter.in.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vroong.msabootcamp.config.Constants;
import com.vroong.msabootcamp.rest.SingerApiController;
import com.vroong.msabootcamp.rest.SingerApiDelegate;
import com.vroong.msabootcamp.support.TestUtils;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class SingerApiDelegateImplTest {

  private MockMvc mvc;

  @Autowired
  private SingerApiDelegate apiDelegate;

  @Test
  @WithMockUser
  void createSinger() throws Exception {
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
