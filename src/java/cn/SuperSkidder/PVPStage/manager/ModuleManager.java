package cn.SuperSkidder.PVPStage.manager;

import cn.SuperSkidder.PVPStage.Modules.Ghost.Reach;
import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.Modules.combat.*;
import cn.SuperSkidder.PVPStage.Modules.gui.*;
import cn.SuperSkidder.PVPStage.Modules.player.*;
import cn.SuperSkidder.PVPStage.Modules.render.*;
import cn.SuperSkidder.PVPStage.events.EventKey;
import cn.SuperSkidder.PVPStage.guis.Compass;
import cn.SuperSkidder.PVPStage.manager.event.EventTarget;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static List<Mod> modList = new ArrayList<>();
    public ModuleManager(){
        //RegisterModsHere
        modList.add(new Sprint());
        modList.add(new ClickGui());
        modList.add(new OldBlock());
        modList.add(new FastGuis());
        modList.add(new KeyStrokes());
        modList.add(new ReachDisplay());
        modList.add(new HackerChecker());
        modList.add(new Information());
        modList.add(new Compass());
        modList.add(new Reach());
        EventManager.register(this);
    }

    public static List<Mod> getModsByCategory(ModCategory m) {
        List<Mod> findList = new ArrayList<>();
        for(Mod mod:modList){
            if(mod.getType() == m){
                findList.add(mod);
            }
        }
        return findList;
    }

    public static Mod getModsByName(String i) {
        for (Mod m : modList) {
            if (!m.getName().equalsIgnoreCase(i)) continue;
            return m;
        }
        return null;
    }

    public static Mod getModByClass(Class<? extends Mod> cls) {
        for (Mod m : modList) {
            if (m.getClass() != cls) continue;
            return m;
        }
        return null;
    }


    @EventTarget
    public void onKey(EventKey e){
        for (Mod m:modList){
            if(m.getKey() == e.getKey()){
                m.toogle();
            }
        }
    }
}
