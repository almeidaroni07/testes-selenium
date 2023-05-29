package br.com.roni.leilao.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class LoginControllerTest {
	
	private static final String HTTP_LOCALHOST_8080_LOGIN = "http://localhost:8080/login";
	private WebDriver browser = null;
	
	@BeforeAll
	public void beforeALL() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");	
	}

	@BeforeEach
	public void beforeEach() {	
		this.browser = new ChromeDriver();
	}
	
	@Test
	void deveriaRealizarOLoginComDadosValidos() {
		this.browser.navigate().to(HTTP_LOCALHOST_8080_LOGIN);
		this.browser.findElement(By.id("username")).sendKeys("fulano");
		this.browser.findElement(By.id("password")).sendKeys("pass");
		this.browser.findElement(By.id("login-form")).submit();
		
		assertFalse(this.browser.getCurrentUrl().equals(HTTP_LOCALHOST_8080_LOGIN));
		
		
	}
	
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemAutenticacao() {
		this.browser.navigate().to("http://localhost:8080/leiloes/2");
		
		assertTrue(this.browser.getCurrentUrl().equals(HTTP_LOCALHOST_8080_LOGIN));
		assertFalse(this.browser.getPageSource().contains("Dados do Leilão"));
	}
	
	@AfterEach
	public void afterEach() {
		this.browser.quit();
	}

}
	