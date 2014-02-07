package com.llys.bootes.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    private Logger logger = LoggerFactory.getLogger(ItemController.class);
    
    @RequestMapping(value = "/item", method = RequestMethod.GET)
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
