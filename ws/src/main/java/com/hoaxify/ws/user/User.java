package com.hoaxify.ws.user;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.hoaxify.ws.auth.Token;
import com.hoaxify.ws.hoax.Hoax;

import lombok.Data;

@Data //Lombok ile getter setter vb. oluşturulur
@Entity
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8421768845853099274L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "{hoaxify.constraints.username.NotNull.message}")
	@Size(min = 4, max= 15)
	@UniqueUsername
	private String username;
	
	@NotNull
	@Size(min = 4, max= 255)
	private String displayName;
	
	@NotNull
	@Size(min = 8, max= 255)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", // Büyük, küçük harf ve sayı içermeli
	message = "{hoaxify.constraints.username.Pattern.message}")
	private String password;
	
	private String image;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Hoax> hoaxes;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Token> tokens;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("Role_user");
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}
