
package com.example.finallaptrinhweb.model;
import java.util.Date;
import com.example.finallaptrinhweb.model.Util;


public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private Date dateOfBirth;
    private String city;
    private String district;
    private String ward;
    private String detail_address;
    private String phone;
    private String verifyStatus;
    private String firebase_uid;
    private int roleId;
    private Date date_created;


    public User() {
    }

    public User(int id, String username, String email, String password, String fullName, Date dateOfBirth, String city, String district, String ward, String detail_address, String phone, String verifyStatus,String firebase_uid, int roleId, Date date_created) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.detail_address = detail_address;
        this.phone = phone;
        this.verifyStatus = verifyStatus;
        this.firebase_uid = firebase_uid;
        this.roleId = roleId;
        this.date_created = date_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirebase_uid() {
        return firebase_uid;
    }

    public void setFirebase_uid(String firebase_uid) {
        this.firebase_uid = firebase_uid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
    public String getFormattedDateOfBirth() {
        return Util.dateFormatNoTime(this.dateOfBirth);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", ward='" + ward + '\'' +
                ", detail_address='" + detail_address + '\'' +
                ", phone='" + phone + '\'' +
                ", verifyStatus='" + verifyStatus + '\'' +
                ", roleId=" + roleId +
                ", date_created=" + date_created +
                '}';
    }
}