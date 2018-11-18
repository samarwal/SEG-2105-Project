package com.example.group.ondemandhomerepair;

public class ProviderProfile {

    public String address,company,profiledescription, providerlicense;
    public int phonenumber;

    public ProviderProfile(){
    }

    public ProviderProfile(String address, int phonenumber, String company, String profiledescription, String providerlicense) {
        this.phonenumber = phonenumber;
        this.address = address;
        this.profiledescription = profiledescription;
        this.providerlicense = providerlicense;
        this.company = company;
    }

    public int getphonenumber(){
        return this.phonenumber;
    }

    public String getAdress(){
        return this.address;
    }
    public String getProfiledescription(){
        return this.profiledescription;
    }
    public String getProviderlicense(){
        return this.providerlicense;
    }
    public String getCompany(){
        return this.company;
    }

    public void setPhonenumber(int phonenumber){
        this.phonenumber = phonenumber;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setProfiledescription(String profiledescription){
        this.profiledescription = profiledescription;
    }

    public void setProviderlicense(String providerlicense){
        this.providerlicense = providerlicense;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String toString(){
        return "PhoneNumber: "+this.phonenumber+" | Address: "+this.address+ " | Description: "+this.profiledescription+
        " | License: "+this.providerlicense+" | Company: "+this.company;
    }
}
