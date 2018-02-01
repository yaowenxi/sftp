package cn.worthy.sftp.api;

import cn.worthy.sftp.common.sftp.SftpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Author:yaowenxi
 * Date: 2018/2/2
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushTest {

    @Autowired
    private SftpClient sftpClient;

    @Test
    public void test001() {
        File file = new File("src/main/resources/test.txt");
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sftpClient.uploadToSftp(file.getName(), filecontent);
    }
}
