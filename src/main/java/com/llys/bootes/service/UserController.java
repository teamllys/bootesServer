package com.llys.bootes.service;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llys.bootes.agent.UserAgent;
import com.llys.bootes.model.User;
import com.llys.bootes.util.StringUtil;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    
    /**
     * 
     * @param params : {@link com.llys.bootes.model.User} class의 속성들이 json으로 표시
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public @ResponseBody String create(
            @RequestBody String params,
            HttpServletRequest req) throws Exception {
        
        logger.info("create user. sender : {}, " +
                    "params : {}", 
                    new Object[]{
                        req.getRemoteAddr(), 
                        params});
          
          Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
          ObjectMapper mapper = new ObjectMapper();
          mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
          try {
              UserAgent uc = new UserAgent();
              
              User user = mapper.readValue(params, User.class);
              uc.create(user);
              
              resultMap.put("results", user);
          } catch(Exception e) {
              resultMap.put("error", 1);
              resultMap.put("message", e.getMessage());
              resultMap.put("details", StringUtil.exception2Str(e));
              logger.error(StringUtil.exception2Str(e));
          }
          return mapper.writeValueAsString(resultMap); 
    }
    /**
     * 
     * userId와 password가 맞으면 해당 user의 정보를 리턴
     * 
     * @param emailId 
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody String list(
            @RequestParam String emailId,
            @RequestParam String password,
            HttpServletRequest req) throws Exception {
          
        
        logger.info("{} sender : {}, " +
                    "emailId : {}, password : {}", 
                    new Object[]{
                        req.getRequestURI(),
                        req.getRemoteAddr(), 
                        emailId, password});
        
          Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
          ObjectMapper mapper = new ObjectMapper();
          mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
          try {
              UserAgent uc = new UserAgent();
              User user = new User();
              user.setEmailId(emailId);
              user.setPassword(password);
              User returnUser = uc.find(user);
              
              resultMap.put("results", returnUser);
              logger.info("{} : {}", req.getRequestURI(), returnUser);
              
          } catch(Exception e) {
              resultMap.put("error", 1);
              resultMap.put("message", e.getMessage());
              resultMap.put("details", StringUtil.exception2Str(e));
              logger.error(StringUtil.exception2Str(e));
          }
          
          return mapper.writeValueAsString(resultMap);
          
    }

}
