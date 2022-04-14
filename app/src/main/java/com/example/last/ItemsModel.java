package com.example.last;

import android.graphics.drawable.Drawable;

public class ItemsModel {
    String name,desc,image,id,heuredepart,villedepart,heurearrivee,villearrivee,prixvoyage,dated,nbrplaces;


    public ItemsModel(String name, String desc,String image, String id, String heuredepart, String villedepart, String heurearrivee, String villearrivee, String prixvoyage, String dated, String nbrplaces) {
        this.name = name;
        this.desc = desc;
        this.id = id;
        this.heuredepart = heuredepart;
        this.villedepart = villedepart;
        this.heurearrivee = heurearrivee;
        this.villearrivee = villearrivee;
        this.prixvoyage = prixvoyage;
        this.dated = dated;
        this.nbrplaces = nbrplaces;
        this.image = image;
    }

    public String getHeuredepart() {
        return heuredepart;
    }

    public void setHeuredepart(String heuredepart) {
        this.heuredepart = heuredepart;
    }

    public String getVilledepart() {
        return villedepart;
    }

    public void setVilledepart(String villedepart) {
        this.villedepart = villedepart;
    }

    public String getHeurearrivee() {
        return heurearrivee;
    }

    public void setHeurearrivee(String heurearrivee) {
        this.heurearrivee = heurearrivee;
    }

    public String getVillearrivee() {
        return villearrivee;
    }

    public void setVillearrivee(String villearrivee) {
        this.villearrivee = villearrivee;
    }

    public String getPrixvoyage() {
        return prixvoyage;
    }

    public void setPrixvoyage(String prixvoyage) {
        this.prixvoyage = prixvoyage;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getNbrplaces() {
        return nbrplaces;
    }

    public void setNbrplaces(String nbrplaces) {
        this.nbrplaces = nbrplaces;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
