package com.ruoyi.oss.cloud;

import com.aliyun.oss.OSSClient;
import com.ruoyi.oss.utils.FastDFSClient;
import com.ruoyi.oss.utils.FastDFSFile;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class FastDFSStorageService extends CloudStorageService {


    public FastDFSStorageService(CloudStorageConfig config) {
        this.config = config;
        // 初始化
        init();
    }

    private void init() {
        Properties props = new Properties();
        props.setProperty("fastdfs.tracker_servers", config.getTracker_servers());
        props.setProperty("fastdfs.connect_timeout_in_seconds", config.getConnect_timeout_in_seconds());
        props.setProperty("fastdfs.network_timeout_in_seconds", config.getNetwork_timeout_in_seconds());
        props.setProperty("fastdfs.charset", config.getCharset());
        props.setProperty("fastdfs.http_anti_steal_token", config.getHttp_anti_steal_token());
        //props.setProperty("fastdfs.http_secret_key", config.getHttp_secret_key());
        props.setProperty("fastdfs.http_tracker_http_port", config.getHttp_tracker_http_port());
        try {
            ClientGlobal.initByProperties(props);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String upload(byte[] data, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return null;
    }

    @Override
    public String uploadSuffix(MultipartFile file, String suffix) {
        String filePath = null;
        try {
            filePath = saveFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 文件下载
     *
     * @param fileUrl
     * @return
     */
    @Override
    public Map<String,Object> downloadFile(String fileUrl) {
        String[] filePathInfo = fileUrl.split(";");
        String groupName = filePathInfo[1].replace("/", "");
        String remoteFileName = filePathInfo[2];
        Map<String,Object> result = FastDFSClient.downFile(groupName, remoteFileName);
        return result;
    }


    /**
     * 保存文件
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            //上传到FastDFS服务器
            fileAbsolutePath = FastDFSClient.upload(file);
        } catch (Exception e) {
            //logger.error("上传文件异常!", e);
        }
        if (fileAbsolutePath == null) {
            //logger.error("上传文件失败,请重新上传!");
        }
        // 把组和文件名称用";"分割开，便于下载和删除时用
        String path = FastDFSClient.getTrackerUrl() + ";" + fileAbsolutePath[0] + "/;" + fileAbsolutePath[1];
        return path;
    }


}
