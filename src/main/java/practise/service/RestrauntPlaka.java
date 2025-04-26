package practise.service;

import practise.dao.IRestrauntDao;
import practise.model.Menu;

import static practise.constants.ResatrauntConstants.PLAKA;

public class RestrauntPlaka implements IRestrauntService {

    private IRestrauntDao iRestrauntDao;

    @Override
    public String getRestrauntId() {
        return PLAKA;
    }

    @Override
    public Menu getMenu() {
        return iRestrauntDao.getRestraunt(getRestrauntId()).getMenu();
    }
}
