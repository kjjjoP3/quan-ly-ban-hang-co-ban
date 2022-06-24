package edu.poly.shop.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.global.GlobalData;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.service.AccountService;

@Controller
public class AdminLoginController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	Account _userBean;
	
	@GetMapping("alogin")
	public String login(ModelMap model) {
		model.addAttribute("account", new AdminLoginDto());
		return "/admin/accounts/login";
	}
	
	@PostMapping("alogin")
	public ModelAndView login(ModelMap model,
			@Valid @ModelAttribute("account") AdminLoginDto dto,
			BindingResult result
			
			) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/accounts/login",model);
		}
		
		Account account = accountService.login(dto.getUsername(),dto.getPassword());
		
		if(account == null) {
			model.addAttribute("message", "Invalid username or password");
			return new ModelAndView("/admin/accounts/login",model);
		}
		
		
		else {
		
		session.setAttribute("username", account.getUsername());
		session.setAttribute("isLogin", true);
		Object ruri = session.getAttribute("redirect-uri");
		
		if(ruri != null) {
			
			session.removeAttribute("redirect-uri");
			
			return new ModelAndView("redirect:" + ruri);
		}
		
		
		return new ModelAndView("redirect:/site/home/index",model);
		}
	}
	
	@GetMapping("alogout")
	public String logout() {
		GlobalData.cart.clear();
		session.removeAttribute("username");
		session.removeAttribute("password");
		session.removeAttribute("isLogin");
		
		return "redirect:/site/home/index";
	}
	
	
	
	
	
	@GetMapping("twlogout")
	public String logouttw() {
		GlobalData.cart.clear();
		session.removeAttribute("username");
		session.removeAttribute("password");
		
		return "redirect:/site/home/index";
	}
	

	
	
	
}
