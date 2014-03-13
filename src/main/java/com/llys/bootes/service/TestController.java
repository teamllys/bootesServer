package com.llys.bootes.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llys.bootes.agent.ItemAgent;
import com.llys.bootes.model.Coffret;
import com.llys.bootes.model.Item;
import com.llys.bootes.model.ItemCond;
import com.llys.bootes.model.ReplyBoard;
import com.llys.bootes.model.ReplyMessage;
import com.llys.bootes.model.User;
import com.llys.bootes.util.HttpUtil;
import com.llys.bootes.util.StringUtil;

@Controller
public class TestController {

	private Logger logger = LoggerFactory.getLogger(getClass());
    
    
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public @ResponseBody String init(
           HttpServletRequest req) throws Exception {
        
        logger.info("{}, {}, params : {}", 
                new Object[]{
                    req.getRequestURI(),
                    req.getRemoteAddr()});
        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            
        	User user = initUser();
        	ReplyBoard rb = initReplyBoard();
	        makeReplyMessages(user, rb);
	        makeCoffrets(user, rb);
            
        	logger.info("{}", req.getRequestURI());
        	resultMap.put("message", "success");
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", e.getMessage());
            resultMap.put("details", StringUtil.exception2Str(e));
            logger.error("error : {}", StringUtil.exception2Str(e));
        }
        return mapper.writeValueAsString(resultMap); 
    }
    private User initUser() throws Exception {
    	Map<String, Object> data = new LinkedHashMap<String, Object>();
    	data.put("emailId", "lons@teramail.com");
    	data.put("password", "1234");
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        
    	String json = mapper.writeValueAsString(data);
    	String result = HttpUtil.sendHttpPut("http://localhost:8080/bootes/user/create", json, 5000,5000);
    	Map tempMap = mapper.readValue(result, Map.class);
    	Object error = tempMap.get("error");
    	if(error != null && (Integer)error == 1) {
    		throw new Exception((String)tempMap.get("message"));
    	}
    	User user = mapper.convertValue((Map)tempMap.get("results"),  User.class);
    	logger.info("init User : {}", user);
    	return user;
    }
    private ReplyBoard initReplyBoard() throws Exception {
    	
        Map<String, Object> data = new LinkedHashMap<String, Object>();
    	data.put("name", "coffret board");
    	data.put("valid", 1);
    	data.put("boardType", "normal");
    	
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        
    	String json = mapper.writeValueAsString(data);
    	String result = HttpUtil.sendHttpPut("http://localhost:8080/bootes/replyboard/create", json, 5000,5000);
    	
    	Map tempMap = mapper.readValue(result, Map.class);
    	Object error = tempMap.get("error");
    	if(error != null && (Integer)error == 1) {
    		throw new Exception((String)tempMap.get("message"));
    	}
    	
    	ReplyBoard rb = mapper.convertValue((Map)tempMap.get("results"),  ReplyBoard.class);
    	logger.info("init ReplyBoard : {}", rb);
    	return rb;
    }
    private void makeReplyMessages(User user, ReplyBoard rb) throws Exception {
    	
    	ReplyMessage rm1 = initReplyMessage(user, rb, "My reply subject", "My reply content", null);
    	ReplyMessage rm2 = initReplyMessage(user, rb, "My reply subject 2 ", "My reply content 2", null);
    	ReplyMessage rm3 = initReplyMessage(user, rb, "My reply subject 3 ", "My reply content 3", rm1.getReplyBoardId());
    	ReplyMessage rm4 = initReplyMessage(user, rb, "My reply subject 4 ", "My reply content 4", rm1.getReplyBoardId());
    }
    private ReplyMessage initReplyMessage(User user, ReplyBoard rb, String subject, String content, Long parentId) throws Exception {
        Map<String, Object> data = new LinkedHashMap<String, Object>();
    	data.put("replyBoardId", rb.getReplyBoardId());
    	data.put("subject", subject);
    	data.put("content", content);
    	data.put("userId", user.getUserId());
    	data.put("parentMessageId", parentId);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        
    	String json = mapper.writeValueAsString(data);
    	String result = HttpUtil.sendHttpPut("http://localhost:8080/bootes/replymessage/create", json, 5000,5000);
    	
    	Map tempMap = mapper.readValue(result, Map.class);
    	Object error = tempMap.get("error");
    	if(error != null && (Integer)error == 1) {
    		throw new Exception((String)tempMap.get("message"));
    	}
    	
    	ReplyMessage rm = mapper.convertValue((Map)tempMap.get("results"),  ReplyMessage.class);
    	logger.info("init ReplyMessage : {}", rm);    	
    	return rm;
    }
    private void makeCoffrets(User user, ReplyBoard rb) throws Exception {
    	initCoffret(user, rb, "message 1", "message content 1");
    	initCoffret(user, rb, "message 2", "message content 2");
    }
    private void initCoffret(User user, ReplyBoard rb, String subject, String content) throws Exception {
    	Map<String, Object> data = new LinkedHashMap<String, Object>();
    	data.put("subject", subject);
    	data.put("content", content);
    	data.put("userId", user.getUserId());
    
    	List<Map<String, Object>> itemList = new LinkedList<Map<String, Object>>();
    	Map<String, Object> item = new LinkedHashMap<String, Object>();
    	item.put("subject", subject + " myItem");
    	item.put("content", content + " myItem content");
    	item.put("userId", user.getUserId());
    	
    	Map<String, Object> item2 = new LinkedHashMap<String, Object>();
    	item2.put("subject", subject + " myItem");
    	item2.put("content", content + " myItem content");
    	item2.put("userId", user.getUserId());
    	
    	itemList.add(item);
    	itemList.add(item2);
    	
    	data.put("items", itemList);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        
    	String json = mapper.writeValueAsString(data);
    	String result = HttpUtil.sendHttpPut("http://localhost:8080/bootes/coffret/create", json, 5000,5000);
    	
    	
    	Map tempMap = mapper.readValue(result, Map.class);
    	Object error = tempMap.get("error");
    	if(error != null && (Integer)error == 1) {
    		throw new Exception((String)tempMap.get("message"));
    	}
    	
    	logger.info("init coffret : {}", result);
    }
}
