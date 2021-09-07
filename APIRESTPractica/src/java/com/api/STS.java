/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;

import com.api.bean.Json;
import com.api.bean.Persona;
import com.api.bean.Telefono;
import com.api.conexion.conexiondb;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.gte;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.bson.Document;

/**
 * REST Web Service
 *
 * @author BRANDON RODRIGUEZ
 */
@Path("PracticSTS")
public class STS {
DB db;
DBCollection tabla;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of STS
     */
    public STS() {
    }

    /**
     * Retrieves representation of an instance of com.api.STS
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/personas/allPersonas")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String consultaPersona(@HeaderParam("user") String user, @HeaderParam("ps") String ps) {
    
    String status = "";
    List<BasicDBObject> objPersonas = new ArrayList<>();
    
    try{     
        MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
        MongoClient  mongo = new MongoClient(uri);
           db = mongo.getDB("dbSTS");   
        tabla = db.getCollection("personas");
                 
        DBCursor arregloDB = tabla.find();
        while(arregloDB.hasNext()){
             BasicDBObject docObj       = (BasicDBObject) arregloDB.next();
             
             String _cedula    = (String) docObj.get("cedula");
             BasicDBObject doc = new BasicDBObject("persona", new BasicDBObject("_id", docObj.get("_id"))
                                                   .append("cedula", _cedula)
                                                   .append("nombre", docObj.get("nombre"))
                                                   .append("telefono",docObj.get("telefono"))
                                                   .append("operador",docObj.get("operador"))
                                                   .append("links", new BasicDBObject("imagen", new BasicDBObject("href", "/imagenes/" + _cedula))));
             objPersonas.add(doc);
            }
       } catch(Exception ex){
              System.out.println(ex);
       }
          
    Gson gson = new Gson();
    String jsonString = gson.toJson(objPersonas);
    return jsonString;
    }

    @POST
    @Path("/personas/addPersona")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON) 
     public String insertarPersona(@HeaderParam("user") String user,@HeaderParam("ps") String ps, @QueryParam("cedula") String cedula, @QueryParam("nombre") String nombre, @QueryParam("telefono") String telefono, @QueryParam("operador") String operador) {
         String status = "";
         try{      
                 /*conexiondb con = new conexiondb();  
                 MongoClient mongo = con.conexion*/
                 MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
                 MongoClient  mongo = new MongoClient(uri);
                 db = mongo.getDB("dbSTS");    
                 tabla = db.getCollection("personas");
         
                 DBCursor arregloDB = tabla.find();
                 
                 int _id = 0;
                 while(arregloDB.hasNext()){
                     DBObject obj = arregloDB.next();
                     _id = (Integer) obj.get("_id");
                     if(_id < 1){
                         _id = 1;
                     } 
                 }
                 BasicDBObject doc = new BasicDBObject();
                 doc.put("_id", _id + 1);
                 doc.put("cedula",  cedula);
                 doc.put("nombre",  nombre);
                 doc.put("telefono",  telefono);
                 doc.put("operador",  operador);
                 tabla.insert(doc);
                 status = "true";
                } catch(Exception ex){
                  status = "false";
                }
         
         BasicDBObject respuesta = new BasicDBObject("message", status);
         Gson gson = new Gson();
         String jsonString = gson.toJson(respuesta);
         return jsonString;
     }
     
    @PUT
    @Path("/personas/updatePersona")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON) 
     public String editarPersona(@HeaderParam("user") String user,@HeaderParam("ps") String ps, @QueryParam("id") Integer id,@QueryParam("cedula") String cedula, @QueryParam("nombre") String nombre, @QueryParam("telefono") String telefono, @QueryParam("operador") String operador) {
         String status = "";
         try{      
                 /*conexiondb con = new conexiondb();  
                 MongoClient mongo = con.conexion*/
                 MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
                 MongoClient  mongo = new MongoClient(uri);
                 db = mongo.getDB("dbSTS");    
                 tabla = db.getCollection("personas");
         
                 BasicDBObject doc = new BasicDBObject();
                 doc.put("_id", id);
                 doc.put("cedula",  cedula);
                 doc.put("nombre",  nombre);
                 doc.put("telefono",  telefono);
                 doc.put("operador",  operador);
                 tabla.save(doc);
                 status = "true";
                } catch(Exception ex){
                  status = "false";
                }
         BasicDBObject respuesta = new BasicDBObject("message", status);
         Gson gson = new Gson();
         String jsonString = gson.toJson(respuesta);
         return jsonString;
     }
    
    @DELETE
    @Path("/personas/deletePersona")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON) 
     public String eliminarPersona(@HeaderParam("user") String user,@HeaderParam("ps") String ps, @QueryParam("id") Integer id) {
         String status = "";
         try{      
                 /*conexiondb con = new conexiondb();  
                 MongoClient mongo = con.conexion*/
                 MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
                 MongoClient  mongo = new MongoClient(uri);
                 db = mongo.getDB("dbSTS");    
                 tabla = db.getCollection("personas");
         
                 BasicDBObject doc = new BasicDBObject("_id", id);
                 tabla.remove(doc);

                 status = "true";
                } catch(Exception ex){
                  status = "false";
                }
         
         BasicDBObject respuesta = new BasicDBObject("message", status);
         Gson gson = new Gson();
         String jsonString = gson.toJson(respuesta);
         return jsonString;
     }
     
    @GET
    @Path("/imagenes/{cedula}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String imagenPersona(@HeaderParam("user") String user, @HeaderParam("ps") String ps,@PathParam("cedula") String cedula) {
    
    String status = "";
    List<DBObject> objPersonas = new ArrayList<>();
    
    try{     
        MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
        MongoClient  mongo = new MongoClient(uri);
           db = mongo.getDB("dbSTS");   
        tabla = db.getCollection("imagenesPersonas");
                 
        DBObject query = new BasicDBObject("cedula", cedula);
        DBCursor arregloDB = tabla.find(query);
        while(arregloDB.hasNext()){
            DBObject rs = arregloDB.next();
            objPersonas.add(rs);
        }
       } catch(Exception ex){
              System.out.println(ex);
       }
          
    Gson gson = new Gson();
    String jsonString = gson.toJson(objPersonas);
    return jsonString;
    }
    
    @GET
    @Path("/personas/getPersona")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String consultaUnaPersona(@HeaderParam("user") String user, @HeaderParam("ps") String ps,@QueryParam("id") Integer id) {
    
    String status = "";
    List<DBObject> objPersonas = new ArrayList<>();
    
    try{     
        MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
        MongoClient  mongo = new MongoClient(uri);
           db = mongo.getDB("dbSTS");   
        tabla = db.getCollection("personas");
                 
        DBObject query = new BasicDBObject("_id", id);
        DBCursor arregloDB = tabla.find(query);
        while(arregloDB.hasNext()){
            DBObject docObj = arregloDB.next();
            
            
             String _cedula    = (String) docObj.get("cedula");
             BasicDBObject doc = new BasicDBObject("persona", new BasicDBObject("_id", docObj.get("_id"))
                                                   .append("cedula", _cedula)
                                                   .append("nombre", docObj.get("nombre"))
                                                   .append("telefono",docObj.get("telefono"))
                                                   .append("operador",docObj.get("operador"))
                                                   .append("links", new BasicDBObject("imagen", new BasicDBObject("href", "/imagenes/" + _cedula))));
            objPersonas.add(doc);
        }
       } catch(Exception ex){
              System.out.println(ex);
       }
          
    Gson gson = new Gson();
    String jsonString = gson.toJson(objPersonas);
    return jsonString;
    } 
    
    @POST
    @Path("/imagenes/addImagenPersona")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
     public String insertarImagenPersona(@HeaderParam("user") String user,@HeaderParam("ps") String ps, @QueryParam("cedula") String cedula,@HeaderParam("imagen") String imagen) {
         
         String status = "";
         try{      
                 MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
                 MongoClient  mongo = new MongoClient(uri);
                 db = mongo.getDB("dbSTS");    
                 tabla = db.getCollection("imagenesPersonas");
                 
                 DBCursor arregloDB = tabla.find();
                 int id = 0;
                 while(arregloDB.hasNext()){
                     DBObject obj = arregloDB.next();
                     id = (Integer) obj.get("_id");
                     if(id < 1){
                        id = 1;
                     }
                 }
         
                 BasicDBObject doc = new BasicDBObject();
                 doc.put("_id", id + 1);
                 doc.put("cedula",  cedula);
                 doc.put("imagen",  imagen);
                 tabla.insert(doc);
                 status = "true";
                } catch(Exception ex){
                  status = "false";
                }
         
         BasicDBObject respuesta = new BasicDBObject("message", status); 
         Gson gson = new Gson();
         String jsonString = gson.toJson(respuesta);
         return jsonString;
     }
    
     @PUT
     @Path("/imagenes/updateImagenPersona")
     @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON) 
     public String editarImagenPersona(@HeaderParam("user") String user,@HeaderParam("ps") String ps, @QueryParam("id") Integer id,@QueryParam("cedula") String cedula, @QueryParam("imagen") String imagen) {

         String status = "";
         try{      
                 /*conexiondb con = new conexiondb();  
                 MongoClient mongo = con.conexion*/
                 MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
                 MongoClient  mongo = new MongoClient(uri);
                 db = mongo.getDB("dbSTS");    
                 tabla = db.getCollection("imagenesPersonas");
         
                 BasicDBObject doc = new BasicDBObject();
                 doc.put("_id", id);
                 doc.put("cedula",  cedula);
                 doc.put("imagen",  imagen);
                 tabla.save(doc);
                 status = "true";
                } catch(Exception ex){
                  status = "false";
                }
         
         BasicDBObject respuesta = new BasicDBObject("message", status);
         Gson gson = new Gson();
         String jsonString = gson.toJson(respuesta);
         return jsonString;
         
     }   
     
     
    @DELETE
    @Path("/imagenes/deleteImagenPersona")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON) 
     public String eliminarImagenPersona(@HeaderParam("user") String user,@HeaderParam("ps") String ps, @QueryParam("id") Integer id) {
         String status = "";
         try{      
                 /*conexiondb con = new conexiondb();  
                 MongoClient mongo = con.conexion*/
                 MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ ps +"@localhost:27017/admin");
                 MongoClient  mongo = new MongoClient(uri);
                 db = mongo.getDB("dbSTS");    
                 tabla = db.getCollection("imagenesPersonas");
         
                 BasicDBObject doc = new BasicDBObject("_id", id);
                 tabla.remove(doc);

                 status = "true";
                } catch(Exception ex){
                  status = "false";
                }
         
         BasicDBObject respuesta = new BasicDBObject("message", status);
         Gson gson = new Gson();
         String jsonString = gson.toJson(respuesta);
         return jsonString;
     }
     
}
