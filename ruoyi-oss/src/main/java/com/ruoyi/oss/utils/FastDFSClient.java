package com.ruoyi.oss.utils;

import com.ruoyi.oss.cloud.CloudStorageConfig;
import com.ruoyi.oss.cloud.CloudStorageService;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * FastDFS客户端(基于org.csource fastdfs-client-java)
 *
 * @author gf
 * @date 2020-05-28 14:48
 */
public class FastDFSClient  {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public static String[] upload(FastDFSFile file) {
        logger.info("文件名称: " + file.getName() + "文件长度:" + file.getContent().length);

        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        StorageClient storageClient = null;
        try {
            storageClient = getTrackerClient();
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        } catch (Exception e) {
            logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
        }
        logger.info("上传文件使用:" + (System.currentTimeMillis() - startTime) + " ms");

        if (uploadResults == null && storageClient != null) {
            logger.error("上传文件失败, 错误码:" + storageClient.getErrorCode());
        }
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        logger.info("上传文件成功!" + "组名称:" + groupName + ", 远程文件名称:" + " " + remoteFileName);
        return uploadResults;
    }

    /**
     * 获取文件
     *
     * @param groupName      组名称
     * @param remoteFileName 远程文件名称
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getTrackerClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: 从FastDFS获取文件失败", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: 从FastDFS获取文件失败", e);
        }
        return null;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名称
     * @param remoteFileName 远程文件名称
     * @return
     */
    public static Map<String, Object> downFile(String groupName, String remoteFileName) {
        try {
            Map<String, Object> result = new HashMap<>();
            StorageClient storageClient = getTrackerClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            result.put("ins", ins);
            result.put("size", fileByte.length);
            return result;
        } catch (IOException e) {
            logger.error("IO Exception: 从FastDFS获取文件失败", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: 从FastDFS获取文件失败", e);
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param groupName      组名称
     * @param remoteFileName 远程文件名称
     * @throws Exception
     */
    public static void deleteFile(String groupName, String remoteFileName)
            throws Exception {
        StorageClient storageClient = getTrackerClient();
        int i = storageClient.delete_file(groupName, remoteFileName);
        logger.info("删除文件成功!" + i);
    }

    /**
     * 获取存储服务器
     *
     * @param groupName
     * @return
     * @throws IOException
     */
    public static StorageServer[] getStoreStorages(String groupName)
            throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }

    /**
     * 获取服务器信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     */
    public static ServerInfo[] getFetchStorages(String groupName,
                                                String remoteFileName) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /**
     * 获取TrackerUrl
     *
     * @return
     * @throws IOException
     */
    public static String getTrackerUrl() throws IOException {
        return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

    /**
     * 获取TrackerClient
     *
     * @return
     * @throws IOException
     */
    private static StorageClient getTrackerClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 获取TrackerServer
     *
     * @return
     * @throws IOException
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

}