package cn.worthy.sftp.common.sftp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author:yaowenxi
 * Date: 2018/2/2
 */
@Component
@Data
@ConfigurationProperties(prefix = "cn.worthy.sftp")
public class SftpConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    private String filePath;
}
