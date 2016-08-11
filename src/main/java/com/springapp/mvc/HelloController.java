package com.springapp.mvc;

import com.ebeijia.base64.BackAES;
import com.springapp.mvc.entity.LoginInfo;
import com.springapp.mvc.entity.Person;
import com.springapp.mvc.entity.RequestWrapper;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.groovy.JsonSlurper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * http://www.admin10000.com/document/6436.html spring mvc 介绍
 */
@Controller
@RequestMapping("/Hello")
public class HelloController {
	public  static String encode="UTF-8";
	public  static String password = "RgbN5PzsRgbN5Pzs";
	private final static Log logger = LogFactory.getLog(HelloController.class);



	@RequestMapping( value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public LoginInfo login(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		//ModelMap 用于返回时，传输值给页面的，不是用于获取前台传送过来的数据，默认为空，如果 response 返回的页面，可以放数据里面，到时候可以在页面里面取出来，显示在页面上面
		//logger.info(model);
		model.put("message","wuqinghai");
		Map<String,String> map=getKeyValue(request);
		String content=getInputStreamContent(request);
		LoginInfo info = new LoginInfo();
		info.setMobile("18321841049");
		info.setPassword("1234567");
		//返回jsp页面 hello.jsp
		return info;
	}

	@RequestMapping( value = "/jsp",method = {RequestMethod.POST,RequestMethod.GET})
	public String JSP(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		//ModelMap 用于返回时，传输值给页面的，不是用于获取前台传送过来的数据，默认为空，如果 response 返回的页面，可以放数据里面，到时候可以在页面里面取出来，显示在页面上面
		//logger.info(model);
		model.put("message","wuqinghai");
        Map<String,String> map=getKeyValue(request);
		String content=getInputStreamContent(request);
		LoginInfo info = new LoginInfo();
		info.setMobile("18321841049");
		info.setPassword("1234567");
		//返回jsp页面 hello.jsp
		return "hello";
	}
     private Map<String,String > getKeyValue(HttpServletRequest request){
		 Map<String,String> map=new HashMap<String, String>();
		 Map<String,String[]> content=   request.getParameterMap();
		Set<String> contentSet=content.keySet();
		 for(String key :contentSet){
		   map.put(key,content.get(key)[0])	 ;
		 }
		 logger.info("map========="+map);
		 return map;
	 }
	private String  getInputStreamContent(HttpServletRequest request) {
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
			 * end
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

	@RequestMapping( value = "/welcome",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody/*　该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区 */
	public ResponseEntity<String> printWelcome(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
    /*  try {
		  request.setCharacterEncoding(encode);
		  // 设置相应信息
		  response.setContentType("application/json;charset=" + encode);
		  response.setCharacterEncoding(encode);
	  }catch (Exception e){
		  e.printStackTrace();
	  }*/


		JSONObject a1=new JSONObject();
		JSONObject a2=new JSONObject();
		JSONObject content1=null;
		try {
			a1.put("mobile","18321854130");
			a1.put("age","23");
			a2.put("a1",a1);
			a2.put("a2", "ccccccc");
			a2.put("a3",a1.toString());
			String bbbbbbb=a2.toString();
            String aaaaa= (String) a2.get("a2");
			content1=JSONObject.fromObject(a2.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}




		Person person=new Person();
		person.setAge("24");
		person.setName("wuqinghai");

		//model.addAttribute("message", "Hello world!");
	    Map<String ,String []> map=	request.getParameterMap();
		RequestWrapper wrapper=new RequestWrapper();
		try {
			BeanUtils.populate(wrapper,map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    JSONObject o=   JSONObject.fromObject(wrapper.getBizContent());

		JSONObject ooo=o.getJSONObject("type");

		logger.info("query:"+request.getQueryString());
		logger.info("" + map);
		logger.error(JSONObject.fromObject(map).toString());

		  logger.info(person);


		byte[] data=new byte[0];
		/**
		 * start
		 */
		ServletInputStream inputStream=null;
		InputStreamReader reader=null;
		int len=-1;
		char [] b=new  char[1024];
		StringBuilder sb=new StringBuilder();
		JSONObject object=null;
		String content=null;
		try {
			inputStream=request.getInputStream();
			reader=new InputStreamReader(inputStream,"UTF-8");

			while ((len=reader.read(b,0,b.length))!=-1){
				for(int i=0;i<len;i++){
					sb.append(b[i]);
				}

			}

			/**
			 * end
			 */
			person.setAge("24");
			person.setName("成功");
			content=sb.toString();
			System.out.println("inputStream---------" + content);
			JSONObject oo=JSONObject.fromObject(content);
			System.out.print(o.toString()+"---------------------");

			/*object=JSONObject.fromObject(content);
			data=BackAES.encrypt(object.toString(), password, 0);
			System.out.println("inputStream---------" + data);*/


		}catch (Exception e){
         //e.printStackTrace();
		}
		finally {
			if(reader!=null){
				try {
					reader.close();
				} catch (Exception e) {

				}
			}
			if(inputStream!=null){
				try {
					inputStream.close();
				}catch (Exception e){
				}
			}
		}
		return new ResponseEntity<String>(content, HttpStatus.OK);
	}
}