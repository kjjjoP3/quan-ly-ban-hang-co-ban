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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.Product;
import edu.poly.shop.global.GlobalData;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.model.OderDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CartService;
import edu.poly.shop.service.ProductService;

@Controller
@RequestMapping("site/cart")
public class CartController {
	@Autowired
    ProductService productService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HttpSession session;
	

	
	@Autowired
    CartService cartService;
//
    @GetMapping("itemCart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getUnitPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        
        return "site/cart/itemCart";
    }

    @GetMapping("addToCart/{id}")
    public String addToCart(@PathVariable Long id){
        GlobalData.cart.add(productService.findById(id).get());
        return "redirect:/site/home/shop";
    }
   

    @GetMapping("removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/site/cart/itemCart";
    } 
//
    @GetMapping("checkout")
    public ModelAndView checkout(ModelMap model,
    		@Valid @ModelAttribute("itemCart") 
    OderDto dto, BindingResult result
    		){
    	
    	if (result.hasErrors()) {
			return new ModelAndView("/site/cart/itemCart",model);
		}
    	

        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getUnitPrice).sum());

        
        
        if (GlobalData.cart.size() == 0) {
        	model.addAttribute("message","You are not add any product at here!");
        	return new ModelAndView("/site/cart/itemCart",model);
		}else {
			Order entity = new Order();        
			BeanUtils.copyProperties(dto, entity);
			entity.setAmount(GlobalData.cart.size());
			entity.setStatus(GlobalData.cart.stream().mapToDouble(Product::getUnitPrice).sum());
			entity.setUsername((String) session.getAttribute("username"));
			cartService.save(entity);
			model.addAttribute("message","Order is saved!");
			GlobalData.cart.clear();
			
		}
        
        
		
		
	
        return new ModelAndView("/site/cart/checkout",model);
//        return "site/cart/checkout";
    } 
    
    
    
}
