package practise.service;

import practise.dao.IRestrauntDao;
import practise.model.Menu;

import static practise.constants.ResatrauntConstants.DARYAGANJ;

public class RestrauntDaryaganj implements IRestrauntService{
    private IRestrauntDao iRestrauntDao;

    @Override
    public String getRestrauntId() {
        return DARYAGANJ;
    }

    @Override
    public Menu getMenu() {
        return iRestrauntDao.getRestraunt(getRestrauntId()).getMenu();
    }
}
