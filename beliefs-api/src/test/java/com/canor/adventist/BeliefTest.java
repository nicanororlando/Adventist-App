package com.canor.adventist;

// JUnit y Mockito.
// import static org.hamcrest.Matchers.*;
// import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.canor.adventist.controller.BeliefController;
import com.canor.adventist.model.Belief.Belief;
import com.canor.adventist.service.Belief.IBeliefService;
import com.canor.adventist.test.DataTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/* @WebMvcTest:
 *  Sirve para probar la capa del controlador y debe proporcionar las dependencias restantes
 * requeridas mediante 'mocks' En otras palabras le indicamos que esta va a ser una clase para
 * probar un controlador.
 */
@WebMvcTest(BeliefController.class)
// @DataMongoTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
public class BeliefTest {

  @Autowired
  // Nos va a permitir hacer llamadas a un controllador.
  private MockMvc mockMvc;

  @MockBean
  // Para crear un simulacro del servicio.
  private IBeliefService beliefService;

  // Para mapear un JSON a Objeto o viceversa. Provee funcionalidad para leer y escribir JSON.
  ObjectMapper objectMapper;

  @BeforeEach
  // Ejecuta antes de cada test.
  void config() {
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testFindBySlug() throws Exception {
    // Cuando llame a findById(111) va a retornar el belief por defecto.
    when(beliefService.findBySlug("belief-111"))
      .thenReturn(DataTest.createBelief111());

    mockMvc
      .perform(
        get("/api/beliefs/slug/belief-111")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").value(111))
      .andExpect(jsonPath("$.slug").value("belief-111"))
      .andExpect(jsonPath("$.title").value("Belief 111"))
      .andExpect(
        jsonPath("$.description")
          .value(
            new ArrayList<>(Arrays.asList("This", "is", "description", "1"))
          )
      )
      .andExpect(jsonPath("$.image").value("https://imagenbelief111.com"));

    verify(beliefService).findBySlug("belief-111");
  }

  @Test
  public void save() throws JsonProcessingException, Exception {
    Belief belief = DataTest.createBelief222().get();
    when(beliefService.save(any())).thenReturn(belief);

    mockMvc
      .perform(
        post("/api/beliefs")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(belief))
      )
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id").value(222))
      .andExpect(jsonPath("$.slug").value("belief-222"))
      .andExpect(jsonPath("$.title").value("Belief 222"))
      // .andExpect(jsonPath("$.description").value(belief.getDescription()))
      .andExpect(jsonPath("$.image").value("https://imagenbelief222.com"));
    //  .andExpect(jsonPath("$.moreDetails").value(details3));

    verify(beliefService).save(any());
    assertNotNull(beliefService.findBySlug(belief.getSlug()));
    // assertThat(beliefService.findBySlug(belief.getSlug())).isEqualTo(belief);
  }
}
