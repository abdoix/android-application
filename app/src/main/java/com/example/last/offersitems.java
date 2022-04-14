package com.example.last;

public class offersitems extends ItemsModel{
    private float oldprice,newprice;

    public float getOldprice() {
        return oldprice;
    }

    public void setOldprice(float oldprice) {
        this.oldprice = oldprice;
    }

    public float getNewprice() {
        return newprice;
    }

    public void setNewprice(float newprice) {
        this.newprice = newprice;
    }

    public offersitems(String name, String desc, String image, String id, String heuredepart, String villedepart, String heurearrivee, String villearrivee, String prixvoyage, String dated, String nbrplaces, float oldprice, float newprice) {
        super(name, desc, image, id, heuredepart, villedepart, heurearrivee, villearrivee, prixvoyage, dated, nbrplaces);
        this.oldprice = oldprice;
        this.newprice = newprice;
    }
}
