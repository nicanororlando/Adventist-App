// package com.canor.adventist;

// // JUnit y Mockito.
// import static org.hamcrest.Matchers.*;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import com.adventists.biblestudies.controller.BibleStudyController;
// import com.adventists.biblestudies.service.StudyBible.IBibleStudyService;
// import com.canor.adventist.DataTest.BibleStudyDataTest;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import java.util.Arrays;
// import java.util.List;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// /* @WebMvcTest:
//  *  Sirve para probar la capa del controlador y debe proporcionar las dependencias restantes
//  * requeridas mediante 'mocks' En otras palabras le indicamos que esta va a ser una clase para
//  * probar un controlador.
//  */
// @WebMvcTest(BibleStudyController.class)
// public class BibleStudy {

//   @Autowired
//   private MockMvc mockMvc;

//   @MockBean
//   // Para crear un simulacro del servicio.
//   private IBibleStudyService iBibleStudyService;

//   // Para mapear un JSON a Objeto o viceversa. Provee funcionalidad para leer y escribir JSON.
//   ObjectMapper objectMapper;

//   @BeforeEach
//   // Ejecuta antes de cada test.
//   void config() {
//     objectMapper = new ObjectMapper();
//   }

//   @Test
//   public void testFindAll() throws Exception {
//     List<BibleStudy> bibleStudies = Arrays.asList(
//       BibleStudyDataTest.createBibleStudy111(),
//       BibleStudyDataTest.createBibleStudy222()
//     );

//     when(iBibleStudyService.findAll()).thenReturn(bibleStudies);

//     mockMvc
//       .perform(get("/api/beliefs").contentType(MediaType.APPLICATION_JSON))
//       .andExpect(status().isOk())
//       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//       .andExpect(jsonPath("$", hasSize(2)))
//       .andExpect(content().json(objectMapper.writeValueAsString(bibleStudies)));

//     verify(iBibleStudyService).findAll();
//   }

//   @Test
//   public void testFindBySlug() throws Exception {
//     BibleStudy bibleStudy = BibleStudyDataTest.createBelief111().get();

//     // Cuando llame a findById(111) va a retornar el belief por defecto.
//     when(iBibleStudyService.findBySlug(bibleStudy.getSlug()))
//       .thenReturn(BibleStudyDataTest.createBelief111());

//     mockMvc
//       .perform(
//         get("/api/beliefs/slug/" + bibleStudy.getSlug())
//           .contentType(MediaType.APPLICATION_JSON)
//       )
//       .andExpect(status().isOk())
//       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//       .andExpect(jsonPath("$.id").value(bibleStudy.getId()))
//       .andExpect(jsonPath("$.slug").value(bibleStudy.getSlug()))
//       .andExpect(jsonPath("$.title").value(bibleStudy.getTitle()))
//       .andExpect(jsonPath("$.description").value(bibleStudy.getDescription()))
//       .andExpect(jsonPath("$.image").value(bibleStudy.getImage()));

//     verify(iBibleStudyService).findBySlug(bibleStudy.getSlug());
//   }

//   @Test
//   public void testSave() throws JsonProcessingException, Exception {
//     BibleStudy bibleStudy = BibleStudyDataTest.createBelief111().get();

//     when(iBibleStudyService.save(any())).thenReturn(bibleStudy);

//     mockMvc
//       .perform(
//         post("/api/beliefs")
//           .contentType(MediaType.APPLICATION_JSON)
//           .content(objectMapper.writeValueAsString(bibleStudy))
//       )
//       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//       .andExpect(status().isCreated())
//       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//       .andExpect(jsonPath("$.id").value(bibleStudy.getId()))
//       .andExpect(jsonPath("$.slug").value(bibleStudy.getSlug()))
//       .andExpect(jsonPath("$.title").value(bibleStudy.getTitle()))
//       .andExpect(jsonPath("$.description").value(bibleStudy.getDescription()))
//       .andExpect(jsonPath("$.image").value(bibleStudy.getImage()));

//     verify(iBibleStudyService).save(any());
//   }

//   @Test
//   public void testUpdateById() throws JsonProcessingException, Exception {
//     BibleStudy bibleStudy = BibleStudyDataTest.createBelief111().get();
//     belief.setSlug("belief-updated");
//     belief.setTitle("Belief updated");

//     Belief newBibleStudy = new BibleStudy();
//     newBibleStudy.setSlug("belief-updated");
//     newBibleStudy.setTitle("Belief updated");

//     when(iBibleStudyService.update(bibleStudy.getId(), newBibleStudy))
//       .thenReturn(bibleStudy);

//     mockMvc
//       .perform(
//         patch("/api/beliefs/" + bibleStudy.getId())
//           .contentType(MediaType.APPLICATION_JSON)
//           .param("id", bibleStudy.getId().toString())
//           .content(objectMapper.writeValueAsString(bibleStudy))
//       )
//       .andExpect(status().isOk())
//       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//       .andExpect(jsonPath("$.id").value(bibleStudy.getId()))
//       .andExpect(jsonPath("$.slug").value(bibleStudy.getSlug()))
//       .andExpect(jsonPath("$.title").value(bibleStudy.getTitle()))
//       .andExpect(jsonPath("$.description").value(bibleStudy.getDescription()))
//       .andExpect(jsonPath("$.image").value(bibleStudy.getImage()));
//     // .andExpect(jsonPath("$.moreDetails").value(belief.getMoreDetails()));

//     verify(iBibleStudyService).update(111, bibleStudy);
//   }
// }
