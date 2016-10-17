package com.websocket;

import net.sf.json.JSONObject;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by wuqinghai on 16/8/19.
 */
@ServerEndpoint(value="/websocket/{user}")
public class MyWebSocket implements MessageHandler.Partial<String>{

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    //private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
   // public static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    public  static Map<String,MyWebSocket> clients=new ConcurrentHashMap<String, MyWebSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String user;

    private boolean isLoop=true;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("user") final String user, final Session session ,EndpointConfig endpointConfig){
        System.out.println(endpointConfig.getDecoders());
        System.out.println(endpointConfig.getEncoders());
        System.out.println(endpointConfig.getUserProperties());
        this.session = session;
        this.user=user;
        this.session.addMessageHandler(this);
        this.session.addMessageHandler(new MessageHandler.Whole<PongMessage>(){

            @Override
            public void onMessage(PongMessage message) {
             Long beforeTime= (Long) session.getUserProperties().get("time");
                System.out.println("receiverPong:"+"---"+(System.currentTimeMillis()-beforeTime) + "-----"+new String(message.getApplicationData().array()));
            }
        });

        new Thread(){
            @Override
            public void run() {
                while (isLoop){
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String ping=new String (user);
                    try {
                        session.getUserProperties().put("time",System.currentTimeMillis());
                        session.getBasicRemote().sendPing(ByteBuffer.wrap(ping.getBytes()));
                    } catch (IOException e) {
                         isLoop=false;
                        try {
                            session.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            }
        }.start();
        //this.session.setMaxTextMessageBufferSize(20000);
        MyWebSocket removeSocket= clients.remove(user);
        if(removeSocket!=null) {
            try {
                removeSocket.session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clients.put(user, this);     //加入set中
        //addOnlineCount();           //在线数加1
        System.out.println("有新的客户端"+user+"加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("欢迎来到益倍嘉");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("user")String user){
        System.out.println("onClose--start");
        closeSocket(user);
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        System.out.println("onClose--end");

    }

    private void closeSocket(String user) {
        isLoop=false;
        MyWebSocket myWebSocket=  clients.get(user);
        /**
         * 如果当前报错的session在clients列表里面
         * 并且不是相同的key新增的session 就移除
         */
        if(myWebSocket!=null&&myWebSocket.session.equals(this.session)) {

            clients.remove(user) ; //从列表中删除
            System.out.println("移除客户端:" + user);

        }
        //报错的session 就关闭
        try {
            this.session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
   /* @OnMessage
    public void onMessage(@PathParam("user")String user,String message, Session session) {
        System.out.println("来自客户端" + user + "+的消息:" + message);

        //群发消息
      Collection<MyWebSocket> all= clients.values();
        for(MyWebSocket item: all){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }*/

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(@PathParam("user")String user,Session session, Throwable error){
        System.out.println("onError---start");
        error.printStackTrace();
        closeSocket(user);
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        System.out.println("onError---end");

    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */

    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);


        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return clients.size();
    }

   /* @Override
    public void onMessage(String s, boolean b) {

        System.out.print("是完整的数据吗？:"+b);
        System.out.print(s);

    }
*/
    private List<String> partMsgs=new ArrayList<String>();
    @Override
    public void onMessage(String message, boolean isLastData) {
        System.out.print("断点数据:"+message);
         partMsgs.add(message);

        if(isLastData){
            System.out.println("来自客户端" + user + "的消息:" + message);
            StringBuilder sb=new StringBuilder();
            for(String msg:partMsgs){
                sb.append(msg);
            }
            partMsgs.clear();
            //群发消息
            String content=sb.toString();

            Collection<MyWebSocket> all= clients.values();
            for(MyWebSocket item: all){
                if(item.equals(this)){
                    JSONObject object=new JSONObject();

                    try {
                        object.put("success","0000");
                        object.put("errorInfo","");
                        object.put("content","");
                        item.sendMessage(object.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }

                }
                else {
                    try {
                        item.sendMessage(sb.toString());
                    } catch (IOException e) {
                        closeSocket(item.user);
                        continue;
                    }
                }
            }
        }
    }


}
