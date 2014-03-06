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
        	initReplyBoard();
        	initReplyMessage();
        	initCoffret(user);
            
        	logger.info("{} : {}", req.getRequestURI(), itemList);
        	resultMap.put("message", "success");
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", 1);
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
    	String json = mapper.writeValueAsString(data);
    	String result = HttpUtil.sendHttpPut("http://localhost:8080/bootes/user/create", json, 5000,5000);
    	User user = mapper.readValue(result,  User.class);
    	logger.info("init User : {}", result);
    	return user;
    }
    private void initReplyBoard() throws Exception {
    	Map<String, Object> data = new LinkedHashMap<String, Object>();
        private Long replyBoardId;
        private String name;
        private Integer valid;
        private String boardType;
        private Date createTime;
    	
    }
    private void initCoffret(User user) throws Exception {
    	Map<String, Object> data = new LinkedHashMap<String, Object>();
    	data.put("subject", "my coffret");
    	data.put("content", "my coffret first");
    	data.put("userId", user.getUserId());
    
    	List<Map<String, Object>> itemList = new LinkedList<Map<String, Object>>();
    	Map<String, Object> item = new LinkedHashMap<String, Object>();
    	item.put("subject", "myItem");
    	item.put("content", "myItem content");
    	item.put("userId", user.getUserId());
    	
    	Map<String, Object> item2 = new LinkedHashMap<String, Object>();
    	item2.put("subject", "myItem");
    	item2.put("content", "myItem content");
    	item2.put("userId", user.getUserId());
    	
    	itemList.add(item);
    	itemList.add(item2);
    	
    	data.put("item", itemList);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String json = mapper.writeValueAsString(data);
    	String result = HttpUtil.sendHttpPut("http://localhost:8080/bootes/coffret/create", json, 5000,5000);
    	logger.info("init coffret : {}", result);
    	        
        
    }

}
