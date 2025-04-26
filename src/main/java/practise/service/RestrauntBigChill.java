package practise.service;

import practise.dao.IRestrauntDao;
import practise.model.Menu;

import static practise.constants.ResatrauntConstants.BIG_CHILL;

public class RestrauntBigChill implements IRestrauntService{
    private IRestrauntDao iRestrauntDao;

    @Override
    public String getRestrauntId() {
        return BIG_CHILL;
    }

    @Override
    public Menu getMenu() {
        return iRestrauntDao.getRestraunt(getRestrauntId()).getMenu();
    }
}
