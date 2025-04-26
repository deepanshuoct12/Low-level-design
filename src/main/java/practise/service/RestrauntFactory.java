package practise.service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

public class RestrauntFactory {
    List <IRestrauntService> restrauntServiceList;
    HashMap<String, IRestrauntService> restrauntServiceMap = new HashMap<>();

    @PostConstruct
    private void init() {
      restrauntServiceList.forEach(iRestrauntService -> restrauntServiceMap.put(iRestrauntService.getRestrauntId(), iRestrauntService));
    }

    public IRestrauntService getRestrauntService(String restrauntId) {
        return restrauntServiceMap.get(restrauntId);
    }

}
