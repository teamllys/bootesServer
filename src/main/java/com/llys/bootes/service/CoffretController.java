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

import com.llys.bootes.agent.CoffretAgent;
import com.llys.bootes.model.Board;
import com.llys.bootes.model.Coffret;
import com.llys.bootes.model.CoffretCond;
import com.llys.bootes.model.Test;
import com.llys.bootes.util.GenericDao;
import com.llys.bootes.util.StringUtil;

@Controller
public class CoffretController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     * Find coffret by given conditions.
     *
     * @param cond specify key=value parameters in url.  
     * @return
     * @throws Exception
     * @see CoffretCond
     */
    @RequestMapping(value = "/coffret", method = RequestMethod.GET)
    public @ResponseBody String list(
           CoffretCond cond,
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
            
            CoffretAgent cm = new CoffretAgent();
            List<Coffret> coffretList = cm.findAll(cond);
            
            
            resultMap.put("results", coffretList);
            logger.info("{} : {}", req.getRequestURI(), coffretList);
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", 1);
            resultMap.put("details", StringUtil.exception2Str(e));
            logger.error("error : {}", StringUtil.exception2Str(e));
        }
        return mapper.writeValueAsString(resultMap); 
    }
    /**
     * 
     * create a coffret.
     * for example
     * {
     * "subject": "test6",
     * "content": "test2",
     * "userId": 1
     * }
     * @param params Json post body that type is Coffret
     * @return
     * @throws Exception
     * @see Coffret
     */
    @RequestMapping(value = "/coffret/create", method = RequestMethod.POST)
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
            CoffretAgent cm = new CoffretAgent();
            Coffret coffret = mapper.readValue(params, Coffret.class);
            cm.create(coffret);
            
            resultMap.put("results", coffret);
            logger.info("{} : {}", req.getRequestURI(), coffret);
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", 1);
            resultMap.put("details", StringUtil.exception2Str(e));
            logger.error("error : {}", StringUtil.exception2Str(e));
        }
        return mapper.writeValueAsString(resultMap); 
    }
}
