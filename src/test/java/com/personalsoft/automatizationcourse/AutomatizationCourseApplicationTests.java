package com.personalsoft.automatizationcourse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalsoft.automatizationcourse.controller.Controller;
import com.personalsoft.automatizationcourse.model.Person;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AutomatizationCourseApplication.class)
@WebMvcTest({ Controller.class})
class AutomatizationCourseApplicationTests {
	@Autowired
	private MockMvc mvc;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void contextLoads() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Apellido");
		persona.setName ("Nombre");
		persona.setOld (28);
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Person personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Person.class);
		//Then
		Assertions.assertEquals(30, personaResultado.getOld());
		Assertions.assertEquals("Diana", personaResultado.getName());
		Assertions.assertNull(personaResultado.getLastName());
	}

}
