package com.llys.bootes.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	private Logger logger = LoggerFactory.getLogger(getClass());
    
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(
            HttpServletRequest req) throws Exception {
    	ModelAndView mv = new ModelAndView("home");
    	return mv;
    }
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ModelAndView comment(
            HttpServletRequest req) throws Exception {
    	ModelAndView mv = new ModelAndView("comment");
    	return mv;
    }
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test(
            HttpServletRequest req) throws Exception {
    	ModelAndView mv = new ModelAndView("test");
    	return mv;
    }
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ModelAndView test2(
            HttpServletRequest req) throws Exception {
    	ModelAndView mv = new ModelAndView("test2");
    	return mv;
    }
}
