package ca.sheridancollege.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.bean.Item;
import ca.sheridancollege.bean.User;
import ca.sheridancollege.database.DataAccess;

@Controller
public class HomeController {

	@Autowired 
	private DataAccess da;
	
@GetMapping("/")
	public String goHome() {
	return "home.html";
}

@GetMapping("/sear")
public String goHomes() {
return "home.html";
}

@GetMapping("/bill")
public String goBill()
{
	return "Billing.html";
}

@GetMapping("/description")
public String goDescription()
{
	return "Description.html";
}



@GetMapping("/car")
public String goCart(Model model)
{
	
	return "AddToCart.html";
}



@GetMapping("/men")
public String goMen()
{
	return "Mens.html";
}
@GetMapping("/girl")
public String goWomen()
{
	return "Women.html";
}


@GetMapping("/boy")
public String goMan()
{
	return "Mens.html";
}
@GetMapping("/kidding")
public String goKidding()
{
	return "Kids.html";
}

@GetMapping("/view/{id}")
private String doAdd(Model model, @PathVariable int id)
{
	da.View(id);
	model.addAttribute("im",da.View(id));
	
	return "Description.html";

}

@GetMapping("/cart")
public String getAdd(Model modl, @RequestParam String name,@RequestParam String model,
		@RequestParam String type,@RequestParam double price,@RequestParam String status,@RequestParam int quantity)
{
	Item item = new Item();
	item.setName(name);
	item.setModel(model);
	item.setType(type);
	item.setPrice(price);
	item.setStatus(status);
	item.setQuantity(quantity);
	
	da.getAdding(item);
	modl.addAttribute("hi",da.getCart());
	return "AddToCart.html";
	
	
}

@GetMapping("/search")

public String getSearch()
{
   return "search.html";
}

@GetMapping("/dummy")
public String goDummy()
{
	da.generateRecord();
	return "home.html";
}


//@GetMapping("/editlink/{id}")
//public String getEdit(Model model, @PathVariable int id)
//{
//	Item b = da.getEditLink(id);
//	model.addAttribute("contact", b);
//	
//	return "editContact.html";
//	 
//}
//@GetMapping("/modify")
//public String getModify(Model model,@RequestParam int id,@RequestParam String title,@RequestParam String author,@RequestParam int  price,@RequestParam int quantity,@RequestParam String course,
//		@RequestParam String campus)
//{
//	Item b = new Item(id,title,author,price,quantity,course,campus);
//	
//	da.getUpdate(b);
//   model.addAttribute("hi",da.getView());
//   
//   return "View.html";
// 
//	
//}
@GetMapping("/deletelink/{id}")
public String getDelete(@PathVariable int id,Model model)
{
	da.getDelete(id);
	model.addAttribute("hi",da.getCart());
	
	return "AddToCart.html";
}

@GetMapping("/purchaselink/{id}")
public String dopurchases(@PathVariable int id,Model model)
		{
	        da.getPurchases(id);
	        model.addAttribute("hi", da.getCart());
	        
	        return "AddToCart.html";
		}

@GetMapping("/purchaseslink/{id}")
public String dopurchase(@PathVariable int id,Model model)
		{
	        da.getPurchase(id);
	        model.addAttribute("hi", da.getView());
	        
	        return "View.html";
		}

@GetMapping("/searchmodel")
public String getSearchPrices(Model modl,@RequestParam String model)
{
	
	ArrayList<Item> b = da.getSearchModel(model);
	modl.addAttribute("him",b);
	return "searchview.html";	
}

@GetMapping("/searchprice")
public String getSearchPrices(Model model,@RequestParam int price1,@RequestParam int price2)
{
	
	ArrayList<Item> b = da.getSearchPrice(price1,price2);
	model.addAttribute("hi",b);
	return "searchview.html";	
}
@GetMapping("/searchname")
public String getSearchNames(Model model, @RequestParam String name)
{
   model.addAttribute("hin",da.getSearchName(name));
   return "searchview.html";	
 } 
@GetMapping("/searchstatus")
public String getSearchStatuses(Model model, @RequestParam String status)
{
	
	
	model.addAttribute("his",da.getSearching(status));
	return "searchview.html";	
}

@GetMapping("/searchquantity")
public String getSearchQuantity(Model model, @RequestParam int quantity1,@RequestParam int quantity2)
{
	
	model.addAttribute("hiq",
			da.getSearchQuantityc(quantity1,quantity2));
	return "searchview.html";	

}






@GetMapping("/login")
public String login()
{
	return "login.html";
}

@GetMapping("/access-denied")
public String error()
{
	return "/error/access-denied.html";
}

@GetMapping("/logout")
public String logout()
{
	return "login.html";
}


@GetMapping("/register")
public String goRegistration(Model model)
{
	model.addAttribute("user",new User());
	return "register.html";
}

@PostMapping("/register")
public String regUser(Model model, @RequestParam String userName,@RequestParam String encryptedPassword,@RequestParam String[] role)
{
	da.addNewUser(userName,encryptedPassword);
	long userId = da.findUserAccount(userName).getUserId();


	for(int i=0;i< role.length;i++)
	{
		

	if(role[i].equalsIgnoreCase("ROLE_ADMIN"))
	{
		da.addUserRoles(userId,(long) 1);
	}
	
	if(role[i].equalsIgnoreCase("ROLE_MEMBER"))
	{
		da.addUserRoles(userId,(long) 2);
	}
		

	}
	return "/register.html";
}


@GetMapping("/admin")
public String loadAdminPage(Authentication authentication,Model model)
{
	ArrayList<String> roles = new ArrayList<String>();
	for(GrantedAuthority ga :authentication.getAuthorities()) {
		roles.add(ga.getAuthority());
	}
    



	model.addAttribute("roles",roles);
//	model.addAttribute("ad",con);
	model.addAttribute("hi",da.getCart());
	return "AddToCart.html";
}
@GetMapping("/member")
public String loadMemberPage(Authentication authentication,Model model)
{
	ArrayList<String> roles = new ArrayList<String>();
	for(GrantedAuthority ga :authentication.getAuthorities()) {
		roles.add(ga.getAuthority());
	}
    



	model.addAttribute("roles",roles);
//	model.addAttribute("ad",con);
	model.addAttribute("hi",da.getCart());
	return "AddToCart.html";
}




@GetMapping("/editlink/{id}")
public String editContact(Model model, @PathVariable int id)
{
	Item db = da.getEditLink(id);
	model.addAttribute("contact", db);

	return "editContact.html";
}

@GetMapping("/modify")
public String modifyCo(Authentication authentication,Model model, @ModelAttribute Item databean)
{

	
	da.modifyCon(databean);
	ArrayList<String> roles = new ArrayList<String>();
	for(GrantedAuthority ga :authentication.getAuthorities()) {
		roles.add(ga.getAuthority());
	}


	


	model.addAttribute("roles",roles);
	model.addAttribute("hi",da.getCart());
	return "/AddToCart.html";


}

@GetMapping("/checkout/{id}")
public String Checkout(Model model, @PathVariable int id)
{
	
	Item db = da.getEditLink(id);
    model.addAttribute("hi", db);
	model.addAttribute("finalprice", da.getCheckout(id));

	return "Billing.html";
}

@GetMapping("/return")
public String Checkout(Model model1) {

	return "Return.html";
}
@GetMapping("/searchmodels")
public String goReturn(Model model1,@RequestParam String model)
{
	ArrayList<Item> db = da.getSearchModel(model);
	
	if(db.size() > 0)
	{
	model1.addAttribute("hi",db);
	  String message="The item is selected for return.When it is approved, you will be notified.Thanks!!";
	model1.addAttribute("g", message);
	}
	
	
		
	
	return "Return.html";
}
}
