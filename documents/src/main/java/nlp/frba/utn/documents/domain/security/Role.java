package nlp.frba.utn.documents.domain.security;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Role {
	
	public enum RoleName {
	    ROLE_USER,
	    ROLE_ADMIN
	}

	@Id
    private RoleName name;

    public Role() {

    }

    public Role(RoleName name) {
        this.name = name;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

}