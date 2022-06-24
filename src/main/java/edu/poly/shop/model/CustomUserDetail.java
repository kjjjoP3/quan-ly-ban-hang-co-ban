package edu.poly.shop.model;

import edu.poly.shop.domain.Account;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail extends Account implements UserDetails {

	public CustomUserDetail(Account user){
        super();
    }//ke thua lai model user
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        
//        code tuong duong:
//        for (Role role: super.getRoles()) {
//            authorityList.add(new SimpleGrantedAuthority(role.getName()));
//        }
        return authorityList;
    } //load menu role cho GrantedAuthority

    @Override
    public String getUsername() {
        return super.getUsername();
    }
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	
}
