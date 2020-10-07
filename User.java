package ca.sheridancollege.bean;

import java.io.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = -1512076453473102549L;
private int userId;
private String userName;
private String encryptedPassword;





	
}
