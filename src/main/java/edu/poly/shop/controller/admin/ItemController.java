package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Order;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.service.CartService;

@Controller
@RequestMapping("admin/items")
public class ItemController {
	@Autowired
	CartService cartService;
	
	
	
	
	@GetMapping("delete/{username}")
	public ModelAndView delete(ModelMap model, @PathVariable("username") Long username) {
		
		cartService.deleteById(username);
		
		model.addAttribute("message","Order is deleted!");
		
		return new ModelAndView( "forward:/admin/items", model);
	}
//	
//	
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Order> list = cartService.findAll();
		
		model.addAttribute("cartss",list);
		
		 return "admin/ItemOder/list";
	}
	
	
}
