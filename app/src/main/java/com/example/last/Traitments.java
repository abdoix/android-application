package com.example.last;

import android.database.Cursor;

import java.util.ArrayList;

public class Traitments {

    public static void newuser(String id,String email ,String pass,String name,String tel ,String date ){
        dataacces.miseajour("delete from user");
        dataacces.miseajour("insert into user VALUES('"+id+"','"+email+"','"+pass+"','"+name+"','"+tel+"','"+date+"') ");
    }
    public static user affuser(){
        user myreturn=null;
        Cursor cur = dataacces.selection("select * from user");
        if(cur.moveToFirst()){
            myreturn = new user(cur.getString(0),cur.getString(1), cur.getString(3));
        }

        return myreturn;
    }


    //panier
    public static void additem(String codevoyage,String heuredepart , String villedepart , String heurearrivee,String villearrivee,String prixvoyage,String dated,String nbrplaces,String img){
        //dataacces.miseajour("INSERT OR REPLACE INTO panier VALUES ('"+codevoyage+"','"+heuredepart+"','"+villedepart+"','"+heurearrivee+"','"+villearrivee+"','"+prixvoyage+"','"+dated+"','"+nbrplaces+"','"+img+"','(SELECT CASE WHEN exists(SELECT 1  FROM panier WHERE id="+codevoyage+")THEN 'good' ELSE 'Hello' END)')");
        dataacces.miseajour("INSERT OR REPLACE INTO panier VALUES ('"+codevoyage+"','"+heuredepart+"','"+villedepart+"','"+heurearrivee+"','"+villearrivee+"','"+prixvoyage+"','"+dated+"','"+nbrplaces+"','"+img+"','1')");
    }
    public static void removeitem(String codevoyage){
        //dataacces.miseajour("INSERT OR REPLACE INTO panier VALUES ('"+codevoyage+"','"+heuredepart+"','"+villedepart+"','"+heurearrivee+"','"+villearrivee+"','"+prixvoyage+"','"+dated+"','"+nbrplaces+"','"+img+"','(SELECT CASE WHEN exists(SELECT 1  FROM panier WHERE id="+codevoyage+")THEN 'good' ELSE 'Hello' END)')");
        dataacces.miseajour("delete from panier where  codevoyage = '"+codevoyage+"'");
    }

    public static Cursor getitems(){
        return dataacces.selection("select * from panier");
    }


    public static float totalprix(){
        float result = 0;
        Cursor cur = dataacces.selection("select * from panier");
        if(cur.moveToFirst()){
            do{
                result += Float.parseFloat(cur.getString(5));
            }while(cur.moveToNext());
        }

        return result;
    }
    //out
    public static void logout(){
        dataacces.miseajour("delete from user");
        dataacces.miseajour("delete from panier");
    }
}
