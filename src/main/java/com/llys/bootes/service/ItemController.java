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
import com.llys.bootes.agent.ItemAgent;
import com.llys.bootes.model.Coffret;
import com.llys.bootes.model.CoffretCond;
import com.llys.bootes.model.Item;
import com.llys.bootes.model.ItemCond;
import com.llys.bootes.util.StringUtil;

@Controller
public class ItemController {

private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     * find item 
     * 
     * @param cond ItemCond class's attributes
     * @param req
     * @return
     * @throws Exception
     * @See ItemCond
     */
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public @ResponseBody String list(
           ItemCond cond,
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
            
            ItemAgent ia = new ItemAgent();
            List<Item> itemList = ia.findAll(cond);
            
            
            resultMap.put("results", itemList);
            logger.info("{} : {}", req.getRequestURI(), itemList);
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
     * Create a item.
     * for example
     * {
     * "subject": "item1",
     * "content": "test2",
     * "userId": 1,
     * "coffret" : { 
     *    "coffretId" : 2,
     *    "subject": "test6",
     *    "content": "test2",
     *   "userId": 1 }    
     * }
     * @param params Json post body that type is Item
     * @return
     * @throws Exception
     * @see Item
     */
    @RequestMapping(value = "/item/create", method = RequestMethod.POST)
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
            ItemAgent ia = new ItemAgent();
            
            Item item = mapper.readValue(params, Item.class);
            ia.create(item);
            
            resultMap.put("results", item);
            logger.info("{} : {}", req.getRequestURI(), item);
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", 1);
            resultMap.put("details", StringUtil.exception2Str(e));
            logger.error(StringUtil.exception2Str(e));
        }
        return mapper.writeValueAsString(resultMap); 
    }
    
}
