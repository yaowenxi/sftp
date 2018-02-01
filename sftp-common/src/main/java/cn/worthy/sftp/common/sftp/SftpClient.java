package cn.worthy.sftp.common.sftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author:yaowenxi
 * Date: 2018/2/2
 */
@Component
public class SftpClient {

    @Autowired
    private SftpConfig config;

    /**
     * 上传pdf文件到FTP
     */
    public boolean uploadToSftp(String filename, byte[] contents) {
        return SftpUtil.uploadFile(config.getHost(), config.getPort(), config.getUsername(), config.getPassword(), config.getFilePath(), filename, contents);
    }
}
