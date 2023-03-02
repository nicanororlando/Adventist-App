package com.adventists.beliefsapi;

// JUnit y Mockito.
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.adventists.beliefsapi.DataTest.BeliefDataTest;
import com.adventists.beliefsapi.controller.BeliefController;
import com.adventists.beliefsapi.model.Belief.Belief;
import com.adventists.beliefsapi.service.Belief.IBeliefService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// @SpringBootTest(classes = BeliefsApplication.class)
// @DataMongoTest
// @AutoConfigureDataMongo
// @AutoConfigureMockMvc

/* @WebMvcTest:
 *  Sirve para probar la capa del controlador y debe proporcionar las dependencias restantes
 * requeridas mediante 'mocks' En otras palabras le indicamos que esta va a ser una clase para
 * probar un controlador.
 */
@WebMvcTest(BeliefController.class)
public class BeliefTest {

  @Autowired
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
  public void testFindAll() throws Exception {
    List<Belief> beliefs = Arrays.asList(
      BeliefDataTest.createBelief111().orElseThrow(),
      BeliefDataTest.createBelief222().orElseThrow()
    );

    when(beliefService.findAll()).thenReturn(beliefs);

    mockMvc
      .perform(get("/api/beliefs").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$", hasSize(2)))
      .andExpect(content().json(objectMapper.writeValueAsString(beliefs)));

    verify(beliefService).findAll();
  }

  @Test
  public void testFindBySlug() throws Exception {
    Belief belief = BeliefDataTest.createBelief111().get();

    // Cuando llame a findById(111) va a retornar el belief por defecto.
    when(beliefService.findBySlug(belief.getSlug()))
      .thenReturn(BeliefDataTest.createBelief111());

    mockMvc
      .perform(
        get("/api/beliefs/slug/" + belief.getSlug())
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").value(belief.getId()))
      .andExpect(jsonPath("$.slug").value(belief.getSlug()))
      .andExpect(jsonPath("$.title").value(belief.getTitle()))
      .andExpect(jsonPath("$.description").value(belief.getDescription()))
      .andExpect(jsonPath("$.image").value(belief.getImage()));

    verify(beliefService).findBySlug(belief.getSlug());
  }

  @Test
  public void testSave() throws JsonProcessingException, Exception {
    Belief belief = BeliefDataTest.createBelief111().get();

    when(beliefService.save(any())).thenReturn(belief);

    mockMvc
      .perform(
        post("/api/beliefs")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(belief))
      )
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").value(belief.getId()))
      .andExpect(jsonPath("$.slug").value(belief.getSlug()))
      .andExpect(jsonPath("$.title").value(belief.getTitle()))
      .andExpect(jsonPath("$.description").value(belief.getDescription()))
      .andExpect(jsonPath("$.image").value(belief.getImage()));

    verify(beliefService).save(any());
  }

  @Test
  public void testUpdateById() throws JsonProcessingException, Exception {
    Belief belief = BeliefDataTest.createBelief111().get();
    belief.setSlug("belief-updated");
    belief.setTitle("Belief updated");

    Belief newBelief = new Belief();
    newBelief.setSlug("belief-updated");
    newBelief.setTitle("Belief updated");

    when(beliefService.update(belief.getId(), newBelief)).thenReturn(belief);

    mockMvc
      .perform(
        patch("/api/beliefs/" + belief.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .param("id", belief.getId().toString())
          .content(objectMapper.writeValueAsString(belief))
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").value(belief.getId()))
      .andExpect(jsonPath("$.slug").value(belief.getSlug()))
      .andExpect(jsonPath("$.title").value(belief.getTitle()))
      .andExpect(jsonPath("$.description").value(belief.getDescription()))
      .andExpect(jsonPath("$.image").value(belief.getImage()));
    // .andExpect(jsonPath("$.moreDetails").value(belief.getMoreDetails()));

    verify(beliefService).update(111, belief);
  }
}
