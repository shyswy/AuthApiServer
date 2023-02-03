package com.example.clubsite.crawling;


import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Before;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class SeleniumCrawlingTest {
    private WebDriver driver;

    @BeforeEach
    public void init(){
        //System.setProperty("webdriver.chrome.driver",
          //      "C:/webtoon/chromedriver.exe/");

        System.setProperty("webdriver.chrome.driver",
                "C:/Users/sanghyun/ClubSite/clubSite/driver/chromedriver.exe/");

       // C:\Users\sanghyun\ClubSite

        ChromeOptions options = new ChromeOptions();
//        options.setBinary("/usr/bin/google-chrome-stable");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("window-size=1920x1080");
        // options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        //return new ChromeDriver(chromeDriverService, options);
        driver= new ChromeDriver(options);

    }

    @After
    public void after() throws Exception{
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void 크롤링테스트() {
        driver.get("https://www.lezhin.com/ko/comic/sparrow");
        // String title = driver.findElement(By.cssSelector("#comic-info > div > h2")).getText();
         String title = driver.findElement(By.className("comicInfo__title")).getText();


        List<WebElement> genre = driver.findElements(By.className("comicInfo__genre")); //Element + "s" 여러개!


        List<WebElement> authors = driver.findElement(By.className("comicInfo__artist")).findElements(By.tagName("a"));



        String imageUrl= driver.findElement(By.cssSelector("#comic-info > picture > source:nth-child(1)")).getAttribute("srcset");
       // String imageUrl = driver.findElement(By.className("comicInfo__cover"))
         //       .getAttribute("srcset");

        // 썸네일 이미지     https://ccdn.lezhin.com/v2/comics/5791250995609600/images/tall.webp?updated=1618363369165&width=720

        log.info("title : "+ title);

        for(WebElement a : authors)
            log.info("author : " + a.getText());
        for(WebElement a : genre)
            log.info("genre : " + a.getText());
        log.info("imageUrl: "+imageUrl);



    }








}
