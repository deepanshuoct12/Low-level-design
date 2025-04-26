package practise.controller;

import practise.model.Menu;
import practise.service.RestrauntFactory;

public class RestrauntController {

   private RestrauntFactory restrauntFactory;

    public Menu getMenu(String id) {
       return restrauntFactory.getRestrauntService(id).getMenu();
    }
}
