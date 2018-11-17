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


}
