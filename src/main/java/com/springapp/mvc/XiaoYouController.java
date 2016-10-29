package com.springapp.mvc;

import com.springapp.mvc.entity.*;
import com.sun.tools.internal.ws.processor.model.Response;
import com.websocket.MyWebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuqinghai on 16/10/23.
 */

@Controller
@RequestMapping("/Hello")
public class XiaoYouController extends BaseController{

    private final String baseLoadUrl="http://192.168.2.246:88";

    @RequestMapping( value = "/getSoftVerList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResponseWrapper apks(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
        //ModelMap 用于返回时，传输值给页面的，不是用于获取前台传送过来的数据，默认为空，如果 response 返回的页面，可以放数据里面，到时候可以在页面里面取出来，显示在页面上面
        //logger.info(model);
        RequestWrapper requestWrapper=getRequestWrapper(request);
        String content=getInputStreamContent(request);
        List<APKEntity> list=new ArrayList<APKEntity>();
        APKEntity entity=new APKEntity();
        entity.setName("百度安全卫士");
        entity.setVerNo("1.1");
        entity.setPackName("ebeijia.android.com.mycontent.baidu");
        entity.setPackUrl(baseLoadUrl+"/apks/ebeijia.android.com.mycontent.baiduDebug.apk");

        list.add(entity);
         entity=new APKEntity();
        entity.setName("益倍嘉app");
        entity.setVerNo("1.1");
        entity.setPackName("ebeijia.android.com.mycontent.ebj");
        entity.setPackUrl(baseLoadUrl+"/apks/ebeijia.android.com.mycontent.ebjDebug.apk");
        list.add(entity);

        entity=new APKEntity();
        entity.setName("好玩app");
        entity.setVerNo("1.1");
        entity.setPackName("ebeijia.android.com.mycontent.haowan");
        entity.setPackUrl(baseLoadUrl+"/apks/ebeijia.android.com.mycontent.haowanDebug.apk");
        list.add(entity);

        entity=new APKEntity();
        entity.setName("豌豆荚");
        entity.setVerNo("1.1");
        entity.setPackName("ebeijia.android.com.mycontent.wandoujia");
        entity.setPackUrl(baseLoadUrl+"/apks/ebeijia.android.com.mycontent.wandoujiaDebug.apk");
        list.add(entity);
        ///Users/wuqinghai/Desktop/工作/兴业数字企业OA/Robot/app/src/main/assets/apk/

        ResponseWrapper<SoftList> wrapper=new ResponseWrapper<SoftList>();
        SoftList softs=new SoftList();
        softs.setCount("1");
        softs.setList(list);
        wrapper.setData(softs);
        wrapper.setRspCode("0000");
        wrapper.setRspMsg("操作成功");
        return wrapper;
    }
}
