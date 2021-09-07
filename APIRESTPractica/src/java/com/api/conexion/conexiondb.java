/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.conexion;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 *
 * @author BRANDON RODRIGUEZ
 */
public class conexiondb {
 
    public MongoClient conexion(){
       MongoClient mongo = null;
       try{
         mongo = new MongoClient("localhost",27017);
       } catch(MongoException ex){
           ex.printStackTrace();
       }
       return mongo;
    } 
    
}
