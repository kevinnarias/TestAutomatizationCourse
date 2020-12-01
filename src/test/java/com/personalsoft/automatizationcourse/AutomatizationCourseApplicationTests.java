package com.personalsoft.automatizationcourse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Map;

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
	//1.Cuando envio numero de documento entre 8 y 10 caracteres entonces ok
	void contextLoads() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Apellido");
		persona.setName ("Nombre");
		persona.setOld (28);
		persona.setDocumentNumber("112345678");
		persona.setDocumentType("TI");
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Person personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Person.class);
		//Then
		Assertions.assertEquals(30, personaResultado.getOld());
		Assertions.assertEquals("Nombre", personaResultado.getName());
		Assertions.assertNull(personaResultado.getLastName());
		Assertions.assertEquals("112345678", personaResultado.getDocumentNumber());
		Assertions.assertEquals("CC", personaResultado.getDocumentType());
	}
	@Test
	//2.Cuando envio numero de documento menor a 8 entonces sacar validacion
	void whenDocumentNumberMinor8ThenValidation() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Apellido");
		persona.setName ("Nombre");
		persona.setOld (18);
		persona.setDocumentNumber("112");
		persona.setDocumentType("CC");
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Person personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Person.class);
		//Then
		
		Assertions.assertEquals("Must be between 8 and 10 characters long", personaResultado.getDocumentNumber());
		
	}
	
	@Test
	//3.Cuando envio numero de documento mayor a 10 entonces sacar validacion
	void whenDocumentNumberMayor10ThenValidation() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Apellido");
		persona.setName ("Nombre");
		persona.setOld (18);
		persona.setDocumentNumber("11234567891");
		persona.setDocumentType("CC");
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Person personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Person.class);
		//Then
		
		Assertions.assertEquals("Must be between 8 and 10 characters long", personaResultado.getDocumentNumber());
		
	}
	@SuppressWarnings("unchecked")
	@Test
	//4.Cuando envio edad menor a 18 entonces sacar validacion
	void whenOldMenor18ThenValidation() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Apellido");
		persona.setName ("Nombre");
		persona.setOld (17);
		persona.setDocumentNumber("112345678");
		persona.setDocumentType("CC");
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Map<String, String> personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Map.class);
		//Then
		
		Assertions.assertEquals("There must be at least 18 in the test case", personaResultado.get("old"));
		
	}
	@Test
	//5.Validar el cambio de tipo de documento, enviando un valor diferente de CC y transformndolo a CC.
	   void whenSendDiferentCCThenReturnCC() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Apellido");
		persona.setName ("Nombre");
		persona.setOld (28);
		persona.setDocumentNumber("112345678");
		persona.setDocumentType("TI");
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Person personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Person.class);
		//Then
		Assertions.assertEquals("CC", personaResultado.getDocumentType());
	}

	@SuppressWarnings("unchecked")
	@Test
	//6.Cuando envio campos vacios entonces validacion
	void whenBlankThenValidation() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("");
		persona.setName ("");
		persona.setDocumentNumber("");
		persona.setDocumentType("");
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Map<String, String> personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Map.class);
		//Then
		//Assertions.assertEquals( "Name is mandatory", personaResultado.get("name"));
		Assertions.assertEquals( "lastName is mandatory", personaResultado.get("lastName"));
		Assertions.assertEquals("There must be at least 18 in the test case", personaResultado.get("old"));
		Assertions.assertEquals("Must be between 8 and 10 characters long", personaResultado.get("documentNumber"));
		Assertions.assertEquals("Must be 2 characters long", personaResultado.get("documentType"));
	}
	
	
	@Test
	//7.Cuando envio formato incorrecto de email entonces validacion
	void whenSendEmailIncorretThenValidation() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Lopera");
		persona.setName ("Diana");
		persona.setDocumentNumber("12345678");
		persona.setOld (28);
		persona.setDocumentType("CC");
		persona.setEmail ("emailinvalido.com");
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Person personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Person.class);
		//Then
		Assertions.assertEquals( "The field must be valid email", personaResultado.getEmail());
		
	}
	
	@Test
	//8.Cuando envio en tipo documento CC entonces ok
	void WhenSendCCThenOK() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Apellido");
		persona.setName ("Nombre");
		persona.setOld (28);
		persona.setDocumentNumber("112345678");
		persona.setDocumentType("CC");
		//When
		MvcResult result = mvc.perform(post("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
		
		Person personaResultado = mapper.readValue(result.getResponse().getContentAsString(),Person.class);
		//Then
		Assertions.assertEquals(30, personaResultado.getOld());
		Assertions.assertEquals("Nombre", personaResultado.getName());
		Assertions.assertNull(personaResultado.getLastName());
		Assertions.assertEquals("112345678", personaResultado.getDocumentNumber());
		Assertions.assertEquals("CC", personaResultado.getDocumentType());
	}
	
	@Test
	//9.Cuando envio en tipo documento CC entonces ok con put
	void SuccessCasePut() throws JsonProcessingException, Exception {
		//given
		Person persona = new Person ();
		persona.setLastName ("Apellido");
		persona.setName ("Nombre");
		persona.setOld (28);
		persona.setDocumentNumber("112345678");
		persona.setDocumentType("CC");
		//When
		MvcResult result = mvc.perform(put("/api/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapper.writeValueAsString(persona))).andReturn();
				
		//Then
		Assertions.assertNotEquals("", result.getResponse().getContentAsString());
		
	}
}
