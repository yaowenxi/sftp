package cn.worthy.sftp.common.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.util.Properties;

/**
 * Author:yaowenxi
 * Date: 2018/2/2
 */
@Slf4j
public class SftpUtil {

    /**
     * Description: 向SFTP服务器上传文件
     *
     * @param host         FTP服务器hostname
     * @param port         FTP服务器端口
     * @param username     FTP登录账号
     * @param password     FTP登录密码
     * @param filePath     FTP服务器文件存放路径。例如分日期存放：/2017/07/06。文件的路径为basePath+filePath
     * @param filename     上传到FTP服务器上的文件名
     * @param fileContents 上传文件内容
     * @return 成功返回true，否则返回false
     */
    public static Boolean uploadFile(String host, int port, String username, String password,
                                     String filePath, String filename, byte[] fileContents) {
        boolean result = false;

        log.info("***   创建ftp session.   ***");
        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;

        //连接SFTP SERVER
        try {
            //通过用户名和密码创建session
            session = jsch.getSession(username, host, port);
            log.info("***   sftp session 创建   ");
            session.setPassword(password);

            //建立 Strict HostKeyChecking 避免 unknown host key exception
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            log.info("***   Session connected.   ***");

            //Open the SFTP channel
            log.info("打开 SFTP Channel");
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;

            //Change to the remote directory
            log.info("***   到FTP文件目录: " + filePath + "   ***");

            channelSftp.cd(filePath);
            try {
                log.info("***   传输文件到远程SFTP名称:" + filename + "   ***");
                channelSftp.put(new ByteArrayInputStream(fileContents), filename);
                result = true;
            } catch (Exception e) {
                log.error("***   远程传输文件失败. " + e.toString() + "   ***");
            }

        } catch (Exception e) {
            log.info("***  连接SFTP SERVER失败" + e.toString() + "   ***");
        } finally {
            //断开SFTP SERVER连接
            try {
                if (session != null)
                    session.disconnect();

                if (channel != null)
                    channel.disconnect();

                if (channelSftp != null)
                    channelSftp.quit();
            } catch (Exception e) {
                log.error("***   断开SFTP SERVER失败. " + e.toString() + "   ***");
            }
            log.info("***   SFTP 传输结束.   ***");
            return result;
        }
    }

}
