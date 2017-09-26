/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

/**
 *
 * @author utkarsh
 */
public class Library {
    private String name;
    private String owner_name;
    private String address;
    
    public Library(String name , String owner_name , String address){
        this.name = name;
        this.owner_name = owner_name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getAddress() {
        return address;
    }
    
    @Override
    public String toString(){
        return String.format("%n%-30s%-30s%-30s%n", this.name , this.owner_name , this.address);
    }
}
