package edu.poly.shop.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.ProductService;
@Controller
@RequestMapping("site/login")
public class SiteLoginController {
		
	@Autowired
	AccountService accountService;
	
	@Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;
	
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("account", new AccountDto());
		return "site/siteLogin/register";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saverOrUpdate(ModelMap model,
			@Valid @ModelAttribute("account") 
		AccountDto dto, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("site/siteLogin/register");
		}
		
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		accountService.save(entity);
		
		model.addAttribute("message","Account is saved!");
		
		return new ModelAndView("site/siteLogin/register");
	}
    
	
}
