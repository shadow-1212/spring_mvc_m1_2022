package org.teamxd.spring_mvc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.teamxd.spring_mvc.service.ScraperService;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootApplication()
@RestController
public class SpringMvcApplication {

	@CrossOrigin
	@RequestMapping(value = "/images/{value}", method = RequestMethod.GET)
	public List<String> getImages(
			@PathVariable("value") String value) throws UnsupportedEncodingException {
		if (!Objects.equals(value, " ")) {
			ScraperService scraperService = new ScraperService(new ChromeDriver());
			List<WebElement> words = scraperService.getRelatedWords(value);
			List<String> result = Arrays.asList(words.stream().map(webElement -> webElement.getAttribute("src")).toArray(String[]::new));
			if(result.size() > 0) {
				return result;
			}
			return List.of();
		}
		return List.of("word is null");

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcApplication.class, args);
	}

}