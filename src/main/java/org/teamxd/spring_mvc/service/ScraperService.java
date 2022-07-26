package org.teamxd.spring_mvc.service;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@AllArgsConstructor
public class ScraperService {
    private final String URL="https://www.pinterest.com/search/pins/?q=";

    private final ChromeDriver driver;

    public List<WebElement> getRelatedWords(String word) throws UnsupportedEncodingException {
        driver.get(URL + URLEncoder.encode(word, StandardCharsets.UTF_8.toString()));
        final WebElement element = driver.findElement(By.className("vbI"));
        return element.findElements(By.tagName("img"));
    }
}
