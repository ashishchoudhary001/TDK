package model;

import java.util.List;

/**
 * Created by ParmeshMahore on 2/7/17.
 */

public class FoodTypeResponseModel {

    private List<FoodType> FoodType;

    public List<FoodType> getFoodType() {
        return FoodType;
    }

    public void setFoodType(List<FoodType> FoodType) {
        this.FoodType = FoodType;
    }

    @Override
    public String toString() {
        return "FoodTypeResponseModel [FoodType = " + FoodType + "]";
    }
}
