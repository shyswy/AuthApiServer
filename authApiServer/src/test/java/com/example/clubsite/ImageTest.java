package com.example.clubsite;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class ImageTest {

    @Test
    void imageUrlSave(){
        String imageURL = "https://blog.kakaocdn.net/dn/VIxFi/btqZqqf3QFS/n2otuLtHQo8TQVOwMAmmbk/img.png";
        //https://ccdn.lezhin.com/v2/comics/5791250995609600/images/tall.webp?updated=1618363369165&width=720

        try {
            URL imgURL = new URL(imageURL);
            String extension = imageURL.substring(imageURL.lastIndexOf(".")+1); // 확장자
            String fileName = "나를_업로드_해봐"; // 이미지 이름

            BufferedImage image = ImageIO.read(imgURL);
            File file = new File("myImage/" + fileName + "." + extension);
            if(!file.exists()) { // 해당 경로의 폴더가 존재하지 않을 경우
                file.mkdirs(); // 해당 경로의 폴더 생성
            }

            ImageIO.write(image, extension, file); // image를 file로 업로드
            System.out.println("이미지 업로드 완료!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*
    void saveImage(){

        String dir = "imagefile/webtoon/";
        Calendar calendar = Calendar.getInstance();
        dir = dir + calendar.get(Calendar.YEAR);
        dir = dir + "/";
        dir = dir + (calendar.get(Calendar.MONTH) + 1);
        dir = dir + "/";
        dir = dir + calendar.get(Calendar.DAY_OF_MONTH);
        dir = dir + "/";
        File dirFile = new File(dir);
        dirFile.mkdirs(); // 디렉토리가 없을 경우 만든다. 퍼미션이 없으면 생성안될 수 있다.
        dir = dir + UUID.randomUUID().toString();

        try(FileOutputStream fos = new FileOutputStream(dir);
            InputStream in = image.getInputStream()
        ){
            byte[] buffer = new byte[1024];
            int readCount = 0;
            while((readCount = in.read(buffer)) != -1){
                fos.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return dir;

    }

     */
}
