package com.llys.bootes.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llys.bootes.model.Board;
import com.llys.bootes.model.Test;
import com.llys.bootes.util.GenericDao;

@Controller
public class CoffretController {
    private Logger logger = LoggerFactory.getLogger(CoffretController.class);
    

    /**
     * 
     * coffret의 리스트를 리턴
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/coffret/list", method = RequestMethod.GET)
    public @ResponseBody String list() throws Exception {
        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            
            resultMap.put("data", 1);
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", 1);
        }
        return mapper.writeValueAsString(resultMap); 
    }
    /**
     * 
     * coffret 하나의 자세한 정보를 리턴
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/coffret", method = RequestMethod.GET)
    public @ResponseBody String show(@RequestParam String id) throws Exception {
        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            
            resultMap.put("data", 1);
        } catch(Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", 1);
        }
        return mapper.writeValueAsString(resultMap); 
    }
}
