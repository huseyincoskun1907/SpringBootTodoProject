package com.hc.todoproje.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.AssertTrue;


@Entity
public class User implements Serializable {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokensms;
    private String phone;
    private boolean smsEnabled;
    @Column(nullable = false)
    @Size(min = 5, max = 26)
    @NotNull
    private String username;

    @Column(nullable = false)
    @Size(min = 5)
    @NotNull
    private String password;
    
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    private String verifyKey;
    private Boolean active;
    public String getVerifyKey() {
		return verifyKey;
	}

	public void setVerifyKey(String verifyKey) {
		this.verifyKey = verifyKey;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	private String passwordConfirm;

    @Column(nullable = false)
    @Size(min = 5, max = 26)
    @NotNull
    private String firstName;

    @Column(nullable = false)
    @Size(min = 5, max = 26)
    @NotNull
    private String lastName;
    
    
    
  
	

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Todos> todoses;
    
    

    public User() {
    }

    public String getTokensms() {
		return tokensms;
	}

	public void setTokensms(String tokensms) {
		this.tokensms = tokensms;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isSmsEnabled() {
		return smsEnabled;
	}

	public void setSmsEnabled(boolean smsEnabled) {
		this.smsEnabled = smsEnabled;
	}

	public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstName, String lastName,String phone,String tokensms,boolean smsEnabled) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone=phone;
        this.smsEnabled=smsEnabled;
        this.tokensms=tokensms;
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }



    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

}