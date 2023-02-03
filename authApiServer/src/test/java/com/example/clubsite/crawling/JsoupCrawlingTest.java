package com.example.clubsite.crawling;


import lombok.extern.java.Log;
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

import javax.imageio.ImageIO;
import javax.persistence.Column;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class JsoupCrawlingTest {




    @Test
    public void MovieTest(){ //cgv 영화목록 가져오기
        String url = "http://www.cgv.co.kr/movies/"; //크롤링할 url지정
        Document doc = null;        //Document에는 페이지의 전체 소스가 저장된다

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
        Elements element = doc.select("div.sect-movie-chart");

        System.out.println("============================================================");

        //Iterator을 사용하여 하나씩 값 가져오기
        Iterator<Element> ie1 = element.select("strong.rank").iterator();
        Iterator<Element> ie2 = element.select("strong.title").iterator();

        while (ie1.hasNext()) {
            log.info(ie1.next().text()+"\t"+ie2.next().text());
        }
        log.info("--------------------------------------");
        //System.out.println("============================================================");


    }


    @Test
    public void crawlNaverData() {//naver ( jsoup )
        String NAVER_END_POINT = "https://comic.naver.com/webtoon/weekday.nhn";

        String NAVER_WEBTOON_LIST_TAG = ".col_inner h4";
        String naverLink="https://comic.naver.com";
        // Exception 필요

        // 추후 따로 전역으로 따로 빼서 쓰는게 좋을 듯
        boolean flag = true;
        String provider = "NAVER";
        // 크롤링한 데이터를 담을 List
        //List<Toons> info = new ArrayList<>();

        try {
            // NAVER 요일별 웹툰 리스트 크롤링 (provider : https://comic.naver.com)
            Document doc = Jsoup.connect(NAVER_END_POINT).get();
            Elements contents = doc.select(NAVER_WEBTOON_LIST_TAG);

            for (Element e : contents) {
                String dayInfo = e.className().toUpperCase();
                Elements a = e.nextElementSibling().select(".thumb");

                for (Element i : a) {

                     // code         =>      웹툰 고유코드
                    // provider     =>      웹툰 플랫폼
                     // dayInfo      =>      웹툰 연재일
                     // href         =>      웹툰 링크
                     // src          =>      웹툰 썸네일 이미지 링크
                     // name         =>      웹툰 이름


                    Element aTag = i.selectFirst("a");
                    Element img = aTag.selectFirst("img");
                    String href = aTag.attr("href");

                   // byte serial = map.get(dayInfo);
                    Pattern p = Pattern.compile("(\\d+\\d)");
                    Matcher m = p.matcher(href);


                    String code = "";
                    // 정규식에 맞는 부분 찾기
                    while (m.find()) {
                        code = m.group();   //식별 코드
                    }
                    String name = img.attr("title"); //웹툰 제목
                    String webtoonLink=naverLink+href;  //웹툰 링크
                    String src = img.attr("src"); //웹툰 섬네일 주소소

                    log.info("code: "+code);
                   log.info("=====================================");
                    log.info("name: "+name);
                    log.info("webtoon link: "+webtoonLink);
                    log.info("thumnail: "+src);
                    log.info("=====================================");

                    // Error Exception

                    // 연재일이 하루가 아닌 웹툰들을 위해 OR연산을 통해 데이터 생성

                    /*
                    if (info.size() != 0) {
                        for (Toons t : info) {
                            if (t.getToonCode().equals(code)) {
                                serial = (byte) (t.getSerializeDay() | serial);
                                t.setSerializeDay(serial);
                                flag = false;
                                break;
                            }
                        }
                    }



                    if (flag) {
                        info.add(Toons.builder()
                                .toonCode(code)
                                .toonName(name)
                                .serializeDay(serial)
                                .toonProvider(provider)
                                .toonHref(href)
                                .toonImgsrc(src)
                                .build());
                    } else {
                        flag = true;
                    }
                    */
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage());
           // return null;
        }
       // return toonRepository.saveAll(info);
    }







    @Test
    void saveImagerToDir(){   //로컬 저장소에 섬네일 이미지 저장. -> 프론트에서 img로 src 바로 렌더링도 가능하다.
        String name;
        String mimeType;

        Long length;
        String saveFileName;
        String platform="Naver";
        String title="abc";
        String url="https://shared-comic.pstatic.net/thumb/webtoon/783052/thumbnail/thumbnail_IMAG21_e14cbea7-8416-40e7-9aae-ccff970ac88f.jpg";
        //String url= "https://shared-comic.pstatic.net/thumb/webtoon/648419/thumbnail/thumbnail_IMAG21_d9398229-cbfd-47dc-9208-0a6fb936f3a7.jpg";
        String dir = "imagefile/webtoon/";
        Calendar calendar = Calendar.getInstance();
        dir = dir + calendar.get(Calendar.YEAR);
        dir = dir + "/";
        dir = dir + (calendar.get(Calendar.MONTH) + 1);
        dir = dir + "/";
        dir = dir + calendar.get(Calendar.DAY_OF_MONTH);
        dir = dir + "/";
        dir = dir + platform + "/"; // 플랫폼 디렉토리..
        File dirFile = new File(dir);
        dirFile.mkdirs(); // 디렉토리가 없을 경우 만든다. 퍼미션이 없으면 생성안될 수 있다.
        dir = dir + title;
        try{
            URL imgUrl = new URL(url);
            BufferedImage jpg = ImageIO.read(imgUrl);
            File file = new File(dir+".jpg");
            ImageIO.write(jpg, "jpg", file);
            System.out.println("file length : "+file.length());

            saveFileName=dir+".jpg";
            length=file.length();
            name=title;
            mimeType="image/jpeg";



        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    /*
    @Test
    void imagerRender(){
        String name;
        String mimeType;

        Long length;
        String saveFileName;
        String platform="Naver";
        String title="abc";
        String url= "https://shared-comic.pstatic.net/thumb/webtoon/648419/thumbnail/thumbnail_IMAG21_d9398229-cbfd-47dc-9208-0a6fb936f3a7.jpg";
        String dir = "imagefile/webtoon/";
        Calendar calendar = Calendar.getInstance();
        dir = dir + calendar.get(Calendar.YEAR);
        dir = dir + "/";
        dir = dir + (calendar.get(Calendar.MONTH) + 1);
        dir = dir + "/";
        dir = dir + calendar.get(Calendar.DAY_OF_MONTH);
        dir = dir + "/";
        dir = dir + platform + "/"; // 플랫폼 디렉토리..
        File dirFile = new File(dir);
        dirFile.mkdirs(); // 디렉토리가 없을 경우 만든다. 퍼미션이 없으면 생성안될 수 있다.
        dir = dir + title;
        try{
            URL imgUrl = new URL(url);
            BufferedImage jpg = ImageIO.read(imgUrl);
            File file = new File(dir+".jpg");
            ImageIO.write(jpg, "jpg", file);
            System.out.println("file length : "+file.length());

            saveFileName=dir+".jpg";
            length=file.length();
            name=title;
            mimeType="image/jpeg";



        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
     */





    /*
     @Test
    public void crawlNaverData() {
        String NAVER_END_POINT = "https://comic.naver.com/webtoon/weekday.nhn";

        String NAVER_WEBTOON_LIST_TAG = ".col_inner h4";
        // Exception 필요

        // 추후 따로 전역으로 따로 빼서 쓰는게 좋을 듯
        boolean flag = true;
        String provider = "NAVER";
        // 크롤링한 데이터를 담을 List
        List<Toons> info = new ArrayList<>();

        try {
            // NAVER 요일별 웹툰 리스트 크롤링 (provider : https://comic.naver.com)
            Document doc = Jsoup.connect(NAVER_END_POINT).get();
            Elements contents = doc.select(NAVER_WEBTOON_LIST_TAG);

            for (Element e : contents) {
                String dayInfo = e.className().toUpperCase();
                Elements a = e.nextElementSibling().select(".thumb");

                for (Element i : a) {

                     // code         =>      웹툰 고유코드
                    // provider     =>      웹툰 플랫폼
                     // dayInfo      =>      웹툰 연재일
                     // href         =>      웹툰 링크
                     // src          =>      웹툰 썸네일 이미지 링크
                     // name         =>      웹툰 이름


                    Element aTag = i.selectFirst("a");
                    Element img = aTag.selectFirst("img");
                    String href = aTag.attr("href");
                    String src = img.attr("src");
                    String name = img.attr("title");
                    String code = "";
                    byte serial = map.get(dayInfo);
                    Pattern p = Pattern.compile("(\\d+\\d)");
                    Matcher m = p.matcher(href);

                    // 정규식에 맞는 부분 찾기
                    while (m.find()) {
                        code = m.group();
                    }

                    // Error Exception

                    // 연재일이 하루가 아닌 웹툰들을 위해 OR연산을 통해 데이터 생성
                    if (info.size() != 0) {
                        for (Toons t : info) {
                            if (t.getToonCode().equals(code)) {
                                serial = (byte) (t.getSerializeDay() | serial);
                                t.setSerializeDay(serial);
                                flag = false;
                                break;
                            }
                        }
                    }

                    if (flag) {
                        info.add(Toons.builder()
                                .toonCode(code)
                                .toonName(name)
                                .serializeDay(serial)
                                .toonProvider(provider)
                                .toonHref(href)
                                .toonImgsrc(src)
                                .build());
                    } else {
                        flag = true;
                    }
                }
            }
        } catch (IOException e) {
            log.debug(e.getMessage());
            return null;
        }
        return toonRepository.saveAll(info);
    }
     */




}
