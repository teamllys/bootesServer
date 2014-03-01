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

import com.llys.bootes.agent.ReplyBoardAgent;
import com.llys.bootes.agent.ReplyMessageAgent;
import com.llys.bootes.model.ReplyBoard;
import com.llys.bootes.model.ReplyBoardCond;
import com.llys.bootes.model.ReplyMessage;
import com.llys.bootes.model.ReplyMessageCond;
import com.llys.bootes.util.StringUtil;


 
@Controller
public class ReplyMessageController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     * list message by ReplyMessageCond. 
     * 
     * @param cond
     * @param req
     * @return
     * @throws Exception
     * @see ReplyMessageCond
     */
    @RequestMapping(value = "/replymessage", method = RequestMethod.GET)
    public @ResponseBody String list(
           ReplyMessageCond cond,
           HttpServletRequest req) throws Exception {
        
        logger.info("{}, {}, params : {}", 
                new Object[]{
                    req.getRequestURI(),
                    req.getRemoteAddr(),
                    cond});
        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            
            ReplyMessageAgent agent = new ReplyMessageAgent();
            List<ReplyMessage> resultList = agent.find(cond);
            
            resultMap.put("results", resultList);
            logger.info("{} : {}", req.getRequestURI(), resultList);
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", 1);
            resultMap.put("details", StringUtil.exception2Str(e));
            logger.error("error : {}", StringUtil.exception2Str(e));
        }
        return mapper.writeValueAsString(resultMap); 
    }

    /**
     * create message of reply board.
     * 
     * @param params Json represent of ReplyMessage class.
     * @param req
     * @return
     * @throws Exception
     * @see ReplyMessage
     */
    @RequestMapping(value = "/replymessage/create", method = RequestMethod.POST)
    public @ResponseBody String create(
            @RequestBody String params,
            HttpServletRequest req) throws Exception {
        
        logger.info("{}, {}, params : {}", 
                new Object[]{
                    req.getRequestURI(),
                    req.getRemoteAddr(),
                    params});
        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            ReplyMessageAgent agent = new ReplyMessageAgent();
            
            ReplyMessage rb = mapper.readValue(params, ReplyMessage.class);
            agent.create(rb);
            
            resultMap.put("results", rb);
            logger.info("{} : {}", req.getRequestURI(), rb);
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", 1);
            resultMap.put("details", StringUtil.exception2Str(e));
            logger.error(StringUtil.exception2Str(e));
        }
        return mapper.writeValueAsString(resultMap); 
    }
    
}
