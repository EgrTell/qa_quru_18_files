package quru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.*;

public class FileDownoadTest {

    @Test
    void downloadTest () throws Exception {
        open("https://github.com/qa-guru/niffler/blob/master/README.md");
        File download = $("a[href*='/qa-guru/niffler/raw/master/README.md']").download();

        try (InputStream is = new FileInputStream(download)) {
            byte [] bytes = is.readAllBytes();
            String fileAsString = new String (bytes, StandardCharsets.UTF_8);
            Assertions.assertTrue(fileAsString.contains("Технологии, использованные в Niffler"));
        }
    }

    @Test
    void uploadTest () throws Exception {
        open("https://tus.io/demo");
        $("input[type='file']").uploadFromClasspath("photo_2023-05-21_13-03-23.jpg");
        $("._root_gq6c0_1").shouldHave(Condition.text("The upload is complete!"));


    }


}
