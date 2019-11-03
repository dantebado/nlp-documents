package nlp.frba.utn.documents.domain.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {
	
	@Id
	private Long id;
	
    private String name;
    
    private String username;

    private String email;

    private String password;
    
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String name, String username, String email, String password) {
    	this.id = System.currentTimeMillis();
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}