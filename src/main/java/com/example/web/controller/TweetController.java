package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.business.domain.Tweet;
import com.example.business.domain.User;
import com.example.business.repository.TweetRepository;
import com.example.business.repository.UserRepository;
import com.example.support.Pager;
import com.example.util.UserCustom;

import java.util.List;

@Controller
public class TweetController {

	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired UserRepository userRepository;

	private static final int BUTTONS_TO_SHOW = 5;

	private static final int INITIAL_PAGE = 0;

	private static final int INITIAL_PAGE_SIZE = 5;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@PageableDefault(size = 5) Pageable pageable, ModelAndView mav) {
        Page<Tweet> tweets = tweetRepository.findAllByOrderByIdDesc(pageable);
        mav.addObject("tweets", tweets);
        mav.setViewName("tweet/index");
        return mav;
    }

	@RequestMapping(value = "/tweet/new", method = RequestMethod.GET)
	public ModelAndView newTweet(ModelAndView mav) {
		mav.setViewName("tweet/new");
		return mav;
	}

	@RequestMapping(value = "/tweet/new", method = RequestMethod.POST)
	public ModelAndView createTweet(@ModelAttribute Tweet tweet, @AuthenticationPrincipal UserCustom userCustom, ModelAndView mav) {
	    User user = userRepository.findOne(userCustom.getId());
	    tweet.setUser(user);		
		tweetRepository.saveAndFlush(tweet);
		mav.setViewName("tweet/create");
		return mav;
	}
	
	@ModelAttribute(name = "login_user")
	public UserDetails setLoginUser(@AuthenticationPrincipal UserCustom userCustom) {
	  return userCustom;
	}

}