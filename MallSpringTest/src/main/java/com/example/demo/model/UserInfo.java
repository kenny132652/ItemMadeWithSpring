//(Mantle)
package com.example.demo.model;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "userInfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "userEmail", nullable = false)
    private String userEmail;

    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    @Column(name = "userTel", nullable = false)
    private String userTel;

    @Column(name = "userStatus", nullable = false)
    private Integer userStatus;
    
    @Column(name = "userBirthday", nullable = false)
    private String userBirthday;
    
    @Column(name = "userIdNumber", nullable = false)
    private String userIdNumber;
    
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    // Getters and Setters
//    public Integer getUserId() { return userId; }
//    public void setUserId(Integer userId) { this.userId = userId; }
//
//    public String getUserName() { return userName; }
//    public void setUserName(String userName) { this.userName = userName; }
//
//    public String getUserEmail() { return userEmail; }
//    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
//
//    public String getUserPassword() { return userPassword; }
//    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
//
//    public String getUserTel() { return userTel; }
//    public void setUserTel(String userTel) { this.userTel = userTel; }
//    
//    public String getUserIdNumber() { return userIdNumber; }
//    public void setUserIdNumber(String userIdNumber) { this.userIdNumber = userIdNumber; }
//    
//    public String getUserBirthday() { return userBirthday; }
//    public void setUserBirthday(String userBirthday) { this.userBirthday = userBirthday; }
//
//    public Integer getUserStatus() { return userStatus; }
//    public void setUserStatus(Integer userStatus) { this.userStatus = userStatus; }
}