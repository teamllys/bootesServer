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
import org.springframework.web.bind.annotation.ResponseBody;

import com.llys.bootes.model.Board;
import com.llys.bootes.model.Test;
import com.llys.bootes.util.GenericDao;

@Controller
public class BoardController {

private Logger logger = LoggerFactory.getLogger(BoardController.class);
    
    @RequestMapping(value = "/board/list", method = RequestMethod.GET)
    public @ResponseBody String listBoard() throws Exception {
        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        Board board = new Board();
        board.setService("bootes");
        board.setCategory("root");
        board.setName("coffret");
        
        List<Test> testList = GenericDao.select("Test");
        
        resultMap.put("myboard", board);
        resultMap.put("db select", testList.get(0).getC1());
        ObjectMapper mapper = new ObjectMapper();
        
        return mapper.writeValueAsString(resultMap); 
    }

}
