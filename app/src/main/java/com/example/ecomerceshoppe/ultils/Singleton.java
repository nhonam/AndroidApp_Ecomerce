package com.example.ecomerceshoppe.ultils;

import com.example.ecomerceshoppe.model.User;

public class Singleton {
    // Static variable reference of single_instance
    // of type Singleton
    private static Singleton single_instance = null;

    // Declaring a variable of type String
    public User userCurrent;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private Singleton(User user)
    {

        userCurrent =user;
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized Singleton getInstance(User user)
    {
        if (single_instance == null)
            single_instance = new Singleton(user);

        return single_instance;
    }
}