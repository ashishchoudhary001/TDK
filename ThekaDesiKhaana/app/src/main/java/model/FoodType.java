package model;

/**
 * Created by ParmeshMahore on 2/7/17.
 */

public class FoodType {

    private NorthIndian NorthIndian;

    private Bengali Bengali;

    private Odia Odia;

    private Punjabi Punjabi;


    public NorthIndian getNorthIndian() {
        return NorthIndian;
    }

    public void setNorthIndian(NorthIndian NorthIndian) {
        this.NorthIndian = NorthIndian;
    }

    public Bengali getBengali() {
        return Bengali;
    }

    public void setBengali(Bengali Bengali) {
        this.Bengali = Bengali;
    }

    public model.Odia getOdia() {
        return Odia;
    }

    public void setOdia(model.Odia odia) {
        Odia = odia;
    }

    public model.Punjabi getPunjabi() {
        return Punjabi;
    }

    public void setPunjabi(model.Punjabi punjabi) {
        Punjabi = punjabi;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "NorthIndian=" + NorthIndian +
                ", Bengali=" + Bengali +
                ", Odia=" + Odia +
                ", Punjabi=" + Punjabi +
                '}';
    }
}
