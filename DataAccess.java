package ca.sheridancollege.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bean.Item;
import ca.sheridancollege.bean.User;

@Repository
public class DataAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

private double finalprice, price;





	public Item View(int id)
	{
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "Select * from items where id = :id";

		parameter.addValue("id", id);

		ArrayList<Item> bk = (ArrayList<Item>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Item>(Item.class));

		if(bk.size() > 0)
		{
			return bk.get(0);
		}
		return null;	
	}

	public ArrayList<Item> getCart()
	{
		ArrayList<Item> bk = new ArrayList<Item>();
		String q = "Select * from good";

		List<Map<String,Object>> rows = jdbc.queryForList(q, new HashMap<String,Object>());

		for(Map<String,Object> row:rows)
		{
			Item c = new Item();
			c.setId((Integer)(row.get("ID")));
			c.setName((String)(row.get("name")));
			c.setModel((String)(row.get("model")));
			c.setType((String) row.get("type"));
			c.setPrice(((BigDecimal)(row.get("price"))).doubleValue());
			c.setStatus((String)(row.get("status")));
			c.setQuantity((int)(row.get("quantity")));

			bk.add(c);
		}
		return bk;



	}

	public void getAdding(Item item) 
	{
		MapSqlParameterSource  parameter = new MapSqlParameterSource();

		String query = "insert into good(name,model,type,price,status,quantity) values(:name,:model,:type,:price,:status,:quantity)";

		parameter.addValue("name", item.getName());
		parameter.addValue("model", item.getModel());
		parameter.addValue("type", item.getType());
		parameter.addValue("price",item.getPrice());
		parameter.addValue("status",item.getStatus());
		parameter.addValue("quantity", item.getQuantity());

		jdbc.update(query,parameter);
	}
	public void getAdd(Item item) 
	{
		MapSqlParameterSource  parameter = new MapSqlParameterSource();

		String query = "insert into items(path,name,model,type,overview,price,status,quantity) values(:path,:name,:model,:type,:overview,:price,:status,:quantity)";

		parameter.addValue("path", item.getPath());
		parameter.addValue("name", item.getName());
		parameter.addValue("model", item.getModel());
		parameter.addValue("type", item.getType());
		parameter.addValue("overview", item.getOverview());
		parameter.addValue("price",item.getPrice());
		parameter.addValue("status",item.getStatus());
		parameter.addValue("quantity", item.getQuantity());

		jdbc.update(query,parameter);
	}















	String[] name=  {"Yellow furry Jacket", "Long Black Water-Resistant Coat", "Black Silk Bachelor Suit","White Casual Crop Top with Black Heart Beat Line","Red short one-piece dress with black mini polka dots",
			"Classic Green Silk 3-piece women suit","Blue and white elegant top-skirt","Boy's 3-piece suit","Girl's short 2-piece dress"};



	String [] path= {"/images/menJacket.jpg","/images/menCoat.jpg","/images/suit.jpg","/images/croptop.jpg",
			"/images/top.jpg","/images/wsuit.jpg","/images/kdress.jpg","/images/ksuit.jpg","/images/girl.jpg"};

	String []model = {"MEN1","MEN2","MEN3","WMN1","WMN2","WMN3","KID1","KID2","KID3"};
	String []type = {"Woollen and polyster","Woollen and polyster","Woollen and polyster","Woollen and polyster","Woollen and polyster","Woollen and polyster",
			"Woollen and polyster","Woollen and polyster","Woollen and polyster"};
	double []prices= {50.65,25.50,29.60,99.60,29.60,99.00,35.99,40.99,55.60};

	String []status = {"available","available","available","available","available","available","available","available","available"};
	int [] quantity = {10,7,5,15,10,20,5,13,10};

	String [] overview= {" 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
			"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
			"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
			" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
			" Features soft fleece interior, hood and pocket."," 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
					"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
					"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
					" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
					" Features soft fleece interior, hood and pocket.'"," 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
							"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
							"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
							" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
							" Features soft fleece interior, hood and pocket.'"," 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
									"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
									"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
									" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
									" Features soft fleece interior, hood and pocket.'"," 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
											"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
											"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
											" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
											" Features soft fleece interior, hood and pocket.'"," 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
													"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
													"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
													" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
													" Features soft fleece interior, hood and pocket.'"," 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
															"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
															"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
															" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
															" Features soft fleece interior, hood and pocket.'"," 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
																	"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
																	"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
																	" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
																	" Features soft fleece interior, hood and pocket.'"," 12 oz., 70/30 cotton/poly blend fleece. Label free. \r\n" + 
																			"Two-ply hood with double-needle hem, metal grommets and matching tipped drawcord. Ribbed \r\n" + 
																			"cuffs and waistband with spandex. Double-needle coverseaming on neck, armholes and waistband.\r\n" + 
																			" Nothing says mini workout buddy like a stylish active hoodie for your little man.\r\n" + 
	" Features soft fleece interior, hood and pocket.'"};


	public void generateRecord()
	{
		

		for(int i = 0;i < 9; i++)
		{
			
			getAdd(new Item(path[i],name[i],model[i],
					type[i],overview[i],prices[i],status[i],quantity[i]));
			
		}
	}


		public Item getEditLink(int id)
		{
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "Select * from good where id = :id";
			
			parameter.addValue("id", id);
			
			ArrayList<Item> bk = (ArrayList<Item>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Item>(Item.class));
			
			if(bk.size() > 0)
			{
				return bk.get(0);
			}
			return null;
		}
		public void getUpdate(Item b)
		{
		
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "update good set name=:name,model=:model,type=:type,price=:price,status=:status,quantity=:quantity where id=:id";
			
			parameter.addValue("id", b.getId());
			parameter.addValue("name",b.getName());
			parameter.addValue("model",b.getModel());
			parameter.addValue("type", b.getType());
			
			parameter.addValue("price", b.getPrice());
			parameter.addValue("status", b.getStatus());
			parameter.addValue("quantity", b.getQuantity());
			
			
			jdbc.update(q,parameter);
			
		}
		public void getDelete(int id)
		{
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "delete from good where id=:id";
			
			parameter.addValue("id",id);
			
			jdbc.update(q,parameter);
			
			
		}
		
		public void getPurchases(int id)
		{
			MapSqlParameterSource  parameter = new MapSqlParameterSource();
			String q = "update good set quantity = quantity - 1 where id=:id";
	       
			parameter.addValue("id", id);
			
			
			jdbc.update(q, parameter);
		}
		public Item getSearching(String status)
		{
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "Select * from items where status=:status";
			
			parameter.addValue("status",status);
			
			ArrayList<Item> bk = (ArrayList<Item>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Item>(Item.class));
			
			if(bk.size() > 0)
			{
				return bk.get(0);
			}
			return null;
			
			
			
			
		}
		
		public ArrayList<Item> getSearchModel(String model)
		{
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "Select * from items where model=:model";
			
			parameter.addValue("model",model);
			
			ArrayList<Item> bk = (ArrayList<Item>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Item>(Item.class));
			
			
			if(bk.size() > 0)
			{
				return bk;
			}
			return null;
			
			
			
		}
		public ArrayList<Item> getSearchName(String name)
		{
			
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "Select * from items where name=:name";
			
			parameter.addValue("name",name);
			
			ArrayList<Item> bk = (ArrayList<Item>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Item>(Item.class));
			
			
			if(bk.size() > 0)
			{
				return bk;
			}
			return null;
	//		
			
			
		}
		
		public void getPurchase(int id)
		{
			MapSqlParameterSource  parameter = new MapSqlParameterSource();
			String q = "update items set quantity = quantity - 1 where id=:id";
	       
			parameter.addValue("id", id);
			
			
			jdbc.update(q, parameter);
		}
	
		public ArrayList<Item> getSearchQuantityc(int quantity1,int quantity2)
		{
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "Select * from items where quantity between :quantity1 and :quantity2";
			
			parameter.addValue("quantity1",quantity1);
			parameter.addValue("quantity2", quantity2);
			
			ArrayList<Item> bk = (ArrayList<Item>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Item>(Item.class));
			
			
			if(bk.size() > 0)
			{
				return bk;
			}
			return null;	
			
			
		}
		public ArrayList<Item> getSearchPrice(int price1,int price2)
		{
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "Select * from items where price between :price1 and :price2";
			
			parameter.addValue("price1",price1);
			parameter.addValue("price2", price2);
			
			ArrayList<Item> bk = (ArrayList<Item>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Item>(Item.class));
			
			
			if(bk.size() > 0)
			{
				return bk;
			}
			return null;	
			
			
		}
	//	
	//	
	//	
	//	
	//	
	//	
	//	
	//	
	//	}
	//	
	//	public void getPurchase(int id)
	//	{
	//		MapSqlParameterSource  parameter = new MapSqlParameterSource();
	//		String q = "update library set quantity = quantity - 1 where id=:id";
	//       
	//		parameter.addValue("id", id);
	//		
	//		
	//		jdbc.update(q, parameter);
	//	}
	//	public void getPurchases(int id)
	//	{
	//		MapSqlParameterSource  parameter = new MapSqlParameterSource();
	//		String q = "update library set quantity = quantity - 1 where id=:id";
	//       
	//		parameter.addValue("id", id);
	//		
	//		
	//		jdbc.update(q, parameter);
	//	}
	//	
	//	
		
		
		
		public ArrayList<Item> getView()
		{
			ArrayList<Item> bk = new ArrayList<Item>();
			String q = "Select * from items";
			
			List<Map<String,Object>> rows = jdbc.queryForList(q, new HashMap<String,Object>());
			
			for(Map<String,Object> row:rows)
			{
				Item c = new Item();
				c.setId((Integer)(row.get("id")));
				c.setName((String)(row.get("name")));
				c.setModel((String)(row.get("model")));
				c.setType((String) row.get("type"));
				c.setOverview((String) row.get("overview"));
				c.setPrice(((BigDecimal)(row.get("price"))).doubleValue());
				c.setStatus((String) row.get("status"));
				c.setQuantity((Integer)(row.get("quantity")));
				
				bk.add(c);
			}
			return bk;
			
			
			
		}
		public List<String> getRolesByID(long userId)
		{
			MapSqlParameterSource parameters= new MapSqlParameterSource();
			ArrayList<String> roles = new ArrayList<String>();
			String query = "SELECT roleName FROM sec_role inner join user_role on (sec_role.roleId = user_role.roleId) where  user_role.userId=:userId";
			parameters.addValue("userId",userId);
			List<Map<String,Object>> rows = jdbc.queryForList(query ,parameters);
			for(Map<String,Object> row:rows)
			{
				roles.add((String)row.get("roleName"));
			}
			return roles;
		}
		
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;

		public User findUserAccount(String userName)
		{
			MapSqlParameterSource parameters= new MapSqlParameterSource();
			String query = "SELECT * FROM sec_user WHERE userName=:user";
			parameters.addValue("user",userName);
			ArrayList<User> users = (ArrayList<User>)jdbc.query(query,parameters,new BeanPropertyRowMapper<User>(User.class));
			if(users.size()>0)
			{
				return users.get(0);
			}
			else
			{
				return null;
			}

		}

		public void addNewUser(String userName,String encryptedPassword)
		{
			MapSqlParameterSource parameters= new MapSqlParameterSource();
			String query="insert into SEC_User (userName, encryptedPassword, ENABLED)" + 
					"values (:un,:pass, 1);";
			parameters.addValue("un",userName);
			parameters.addValue("pass",passwordEncoder.encode(encryptedPassword));
			jdbc.update(query,parameters);
		}

		public void addUserRoles(long userId, long rolId)
		{
			MapSqlParameterSource parameters= new MapSqlParameterSource();
			String query="INSERT INTO user_role(userId,roleId) values(:userId,:roleId)";
			parameters.addValue("userId",userId);
			parameters.addValue("roleId",rolId);
			jdbc.update(query,parameters);
		}
		
		
		
		
		
		public void modifyCon(Item b)
		{

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			String q = "update good set name=:name,model=:model,type=:type,price=:price,quantity=:quantity where id=:id ";

			parameter.addValue("id",b.getId());
			parameter.addValue("name",b.getName());
			parameter.addValue("model", b.getModel());
			parameter.addValue("type",b.getType());
			parameter.addValue("price", b.getPrice());
			parameter.addValue("quantity", b.getQuantity());


			jdbc.update(q,parameter);



		}
		
		public double getCheckout(int id)
		{
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			ArrayList<Item> bk = new ArrayList<Item>();
			String q = "Select price from good where id=:id";
			
			parameter.addValue("id",id);
ArrayList<Item> bk1 = (ArrayList<Item>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Item>(Item.class));
			
			
			if(bk1.size() > 0)
			{
				
				Item ik = bk1.get(0);
				price  = ik.getPrice();
			}
			
			
			finalprice  = ((price * 13)/100)+price;
			
			return finalprice;
			
		}
				
			
			
			
		
		
}
