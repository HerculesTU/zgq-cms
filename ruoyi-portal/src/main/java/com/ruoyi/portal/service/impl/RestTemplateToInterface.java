package com.ruoyi.portal.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.portal.domain.Platsystemsysuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateToInterface {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 以get方式请求第三方http接口 getForEntity
     * @param url
     * @return
     */
    public Platsystemsysuser doGetWith1(String url){
        ResponseEntity<Platsystemsysuser> responseEntity = restTemplate.getForEntity(url, Platsystemsysuser.class);
        Platsystemsysuser user = responseEntity.getBody();
        return user;
    }

    /**
     * 以get方式请求第三方http接口 getForObject
     * 返回值返回的是响应体，省去了我们再去getBody()
     * @param url
     * @return
     */
    public Platsystemsysuser doGetWith2(String url){
        Platsystemsysuser user  = restTemplate.getForObject(url, Platsystemsysuser.class);
        return user;
    }

    /**
     * 以post方式请求第三方http接口 postForEntity
     * @param url
     * @return
     */
    public String doPostWith1(String url,MultiValueMap<String, String> user){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, user, String.class);
        String body = responseEntity.getBody();
        return body;
    }

    /**
     * 以post方式请求第三方http接口 postForEntity
     * @param url
     * @return
     */
    public String doPostWith2(String url){
        Platsystemsysuser user = new Platsystemsysuser();
        String body = restTemplate.postForObject(url, user, String.class);
        return body;
    }

    /**
     * exchange
     * @return
     */
    public String doExchange(String url, Integer age, String name){
        //header参数
        HttpHeaders headers = new HttpHeaders();
        String token = "asdfaf2322";
        headers.add("authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //放入body中的json参数
        JSONObject obj = new JSONObject();
        obj.put("age", age);
        obj.put("name", name);

        //组装
        HttpEntity<JSONObject> request = new HttpEntity<>(obj, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        String body = responseEntity.getBody();
        return body;
    }

}
