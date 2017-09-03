package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.business.domain.Tweet;
import com.example.business.repository.TweetRepository;
import com.example.support.Pager;

import java.util.List;

@Controller
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;
    
    private static final int BUTTONS_TO_SHOW = 5;

    private static final int INITIAL_PAGE = 0;

    private static final int INITIAL_PAGE_SIZE = 5;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "page", required = false) Integer page, ModelAndView mav) {
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;
        Page<Tweet> tweets = tweetRepository.findAllByOrderByIdDesc(new PageRequest(evalPage, INITIAL_PAGE_SIZE));
        Pager pager = new Pager(tweets.getTotalPages(), tweets.getNumber() + 1, BUTTONS_TO_SHOW);
        mav.addObject("tweets", tweets);
        mav.addObject("pager", pager);
        mav.setViewName("tweet/index");
        return mav;
    }
    
    @RequestMapping(value = "/tweet/new", method = RequestMethod.GET)
    public ModelAndView newTweet(ModelAndView mav) {
        mav.setViewName("tweet/new");
        return mav;
    }
    @RequestMapping(value = "/tweet/new", method = RequestMethod.POST)
    public ModelAndView createTweet(Tweet newTweet, ModelAndView mav) {
        tweetRepository.saveAndFlush(newTweet);
        mav.setViewName("tweet/create");
        return mav;
    }

}