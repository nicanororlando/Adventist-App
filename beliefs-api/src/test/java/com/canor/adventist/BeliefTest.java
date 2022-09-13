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
import com.canor.adventist.model.Belief.BeliefDetails;
import com.canor.adventist.service.Belief.IBeliefService;
import com.canor.adventist.test.DataTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        get("/api/beliefs/belief-111").contentType(MediaType.APPLICATION_JSON)
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
    List<String> description3 = new ArrayList<>(
      Arrays.asList("This", "is", "description", "3")
    );
    List<BeliefDetails> details3 = new ArrayList<>(
      Arrays.asList(
        new BeliefDetails(
          "First title",
          Arrays.asList("p1", "p2", "p3"),
          "firstImageDetail.com"
        ),
        new BeliefDetails(
          "Second title",
          Arrays.asList("p1", "p2", "p3"),
          "secondImageDetail.com"
        )
      )
    );

    Belief belief = new Belief(
      333,
      "belief-333",
      "Belief 333",
      description3,
      "https://imagenbelief333.com",
      details3
    );

    when(beliefService.save(any())).thenReturn(belief);

    mockMvc
      .perform(
        post("/api/beliefs")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(belief))
      )
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id").value(333))
      .andExpect(jsonPath("$.slug").value("belief-333"))
      .andExpect(jsonPath("$.title").value("Belief 333"))
      .andExpect(jsonPath("$.description").value(description3))
      .andExpect(jsonPath("$.image").value("https://imagenbelief333.com"));
    // .andExpect(jsonPath("$.moreDetails").value(details3));

    verify(beliefService).save(any());
    assertNotNull(beliefService.findBySlug(belief.getSlug()));
    // assertThat(beliefService.findBySlug(belief.getSlug())).isEqualTo(belief);
  }
}
