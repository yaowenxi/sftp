package cn.worthy.sftp.api;

import cn.worthy.sftp.common.sftp.SftpClient;
import com.jcraft.jsch.JSch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return  "hello world";
    }

}
