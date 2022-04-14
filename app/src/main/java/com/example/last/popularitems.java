package com.example.last;

public class popularitems extends ItemsModel{
    String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public popularitems(String name, String desc, String image, String id, String heuredepart, String villedepart, String heurearrivee, String villearrivee, String prixvoyage, String dated, String nbrplaces, String price) {
        super(name, desc, image, id, heuredepart, villedepart, heurearrivee, villearrivee, prixvoyage, dated, nbrplaces);
        this.price = price;
    }
}
