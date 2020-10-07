package ca.sheridancollege.bean;

import java.io.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Item implements Serializable {

	
	private int id;
	private String path;
	private String name;
	private String model;
	private String type;
	private String overview;
	private double price;
	private String status;
	private int quantity;
	
	
	public Item(String path,String name,String model,String type, String overview,double price,String status, int quantity)
	{
		this.path=path;
		this.name=name;
		this.model=model;
		this.type=type;
		this.overview = overview;
		this.price = price;
		this.status = status;
		this.quantity = quantity;
	}
}
