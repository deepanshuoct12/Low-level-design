package practise.dao;

import practise.model.Restraunt;

import java.util.ArrayList;
import java.util.List;

public class RestrauntDao implements IRestrauntDao{
    private static List<Restraunt> restraunts = new ArrayList<>();


    public Restraunt getRestraunt(String id) {
       return restraunts.stream().filter(restraunt -> restraunt.getId().equals(id)).findFirst().orElse(null);
    }
}
