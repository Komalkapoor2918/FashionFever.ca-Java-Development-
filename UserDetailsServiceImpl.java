package ca.sheridancollege.security;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.database.DataAccess;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	@Lazy
     private DataAccess da;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
	{
		ca.sheridancollege.bean.User user = 
				da.findUserAccount(userName);
		
		if(user == null)
		{
			System.out.println("User not found:" + userName);
			throw new UsernameNotFoundException("User " + userName + "was not found in the database");
		}
		List<String> roleNames = da.getRolesByID(user.getUserId());
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		if(roleNames != null)
		{
			for(String role:roleNames)
			{
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		
		UserDetails userDetails = (UserDetails)
				new User(user.getUserName(),user.getEncryptedPassword(),grantList);
		return userDetails;
	}
	
	
	

}
