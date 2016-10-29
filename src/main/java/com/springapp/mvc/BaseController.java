package com.springapp.mvc;

import com.springapp.mvc.entity.RequestWrapper;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wuqinghai on 16/10/23.
 */
public abstract class BaseController {
    protected final static Log logger = LogFactory.getLog(BaseController.class);


    protected RequestWrapper getRequestWrapper(HttpServletRequest request){

        Map<String,String > map=getKeyValue(request);
        JSONObject o=JSONObject.fromObject(map);
       return (RequestWrapper) JSONObject.toBean(o,RequestWrapper.class);
    }

    protected Map<String,String > getKeyValue(HttpServletRequest request){
        Map<String,String> map=new HashMap<String, String>();
        Map<String,String[]> content=   request.getParameterMap();
        Set<String> contentSet=content.keySet();
        for(String key :contentSet){
            map.put(key,content.get(key)[0])	 ;
        }
        logger.info("map=========" + map);
        return map;
    }
    protected String  getInputStreamContent(HttpServletRequest request) {
        ServletInputStream inputStream = null;
        InputStreamReader reader = null;
        int len = -1;
        char[] b = new char[1024];
        StringBuilder sb = new StringBuilder();
        JSONObject object = null;
        String content = null;
        try {
            inputStream = request.getInputStream();
            reader = new InputStreamReader(inputStream, "UTF-8");

            while ((len = reader.read(b, 0, b.length)) != -1) {
                for (int i = 0; i < len; i++) {
                    sb.append(b[i]);
                }

            }

            /**
             * end wuqinghai
             */

            content = sb.toString();
            logger.info("流内容========="+content);




        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
