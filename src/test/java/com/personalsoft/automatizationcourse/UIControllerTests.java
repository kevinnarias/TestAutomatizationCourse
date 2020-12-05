package com.personalsoft.automatizationcourse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ExtendWith({ ScreenshotOnFailureExtension.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UIControllerTests {
	@LocalServerPort
    private int port;
	@Container
	private BrowserWebDriverContainer container = new BrowserWebDriverContainer().withCapabilities(new ChromeOptions());

	@Test
	void Prueba1 () {
		this.container.getWebDriver().get("http://host.docker.internal:" + port + "/personalsoft/calculo?valor1=5&valor2=3");
				WebElement messageElement = this.container.getWebDriver().findElementById("suma");
				Assertions.assertEquals("El resultado de la suma es 10.0", messageElement.getText());
				
				messageElement = this.container.getWebDriver().findElementById("restar");
				Assertions.assertEquals("El resultado de la resta es 2.0", messageElement.getText());
		
		
	}
	@Test
	void PruebaError () {
		this.container.getWebDriver().get("http://host.docker.internal:" + port + "/personalsoft/calculoError?valor1=5&valor2=");
				WebElement messageElement = this.container.getWebDriver().findElementById("error");
				Assertions.assertEquals("Debes agregar ambos números", messageElement.getText());
				
						
		
	}
}



