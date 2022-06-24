package edu.poly.shop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Product;
import edu.poly.shop.global.GlobalData;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.ProductService;
@Controller
@RequestMapping("site/home")
public class HomeController {

	@Autowired
	AccountService accountService;
	
	@Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;
	
    @GetMapping("index")
    public String indexHome(Model model){
        
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "site/home/index";
    }//page admin home
    
    @GetMapping("shop")
    public String shop(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "site/home/shop";
    }
    
    @GetMapping("category/{id}")
    public String shopByCat(@PathVariable Long id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "site/home/shop";
    } //view Products By Category
//
    @GetMapping("viewproduct/{id}")
    public String viewProduct(@PathVariable long id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("product", productService.findById(id).get());
        return "site/home/viewProduct";
    } //view product Details
    
    
    @PostMapping("saveOrUpdate")
	public ModelAndView saverOrUpdate(ModelMap model,
			@Valid @ModelAttribute("account") 
		AccountDto dto, BindingResult result) {
		
		
		if(result.hasErrors()) {
			return new ModelAndView("site/siteLogin/changePassword");
		}
		
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		accountService.save(entity);
		
		model.addAttribute("message","Account is saved!");
		
		return new ModelAndView("site/siteLogin/changePassword");
	}
    
    @GetMapping("edit/{username}")
	public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {
		
		Optional<Account> opt = accountService.findById(username);
		AccountDto dto = new AccountDto();
		
		if(opt.isPresent()) {
			Account entity = opt.get();			
			BeanUtils.copyProperties(entity, dto);
		
			dto.setPassword("");
			
			dto.setIsEdit(true);
			
			model.addAttribute("account", dto);
			
			return new ModelAndView("site/siteLogin/changePassword");
		}
		
		model.addAttribute("message", "Account is not existed");
		
		return new ModelAndView("site/siteLogin/changePassword");
		
		
	}
    
    @GetMapping("search")
	public String search(ModelMap model,
			@RequestParam(name = "name" , required = false) String name) {
		
    	 model.addAttribute("cartCount", GlobalData.cart.size());
         model.addAttribute("categories", categoryService.getAllCategory());
         model.addAttribute("products", productService.getAllProduct());
    	
		List<Product> list = null;
 		
		if(StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name);
		}else {
			list = productService.findAll();
		}
		
		model.addAttribute("products",list);
		
		
		return "site/home/search";
	}
    
    
    
    
    
    
    
    
    
    
}
