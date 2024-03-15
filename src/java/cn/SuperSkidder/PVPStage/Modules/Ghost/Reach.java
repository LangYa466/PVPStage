package cn.SuperSkidder.PVPStage.Modules.Ghost;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.values.type.Num;

public class Reach extends Mod {
    public static Num<Float> range = new Num<Float>("Range",3.0F,0F,4.5F);
    public Reach(){
        super("Reach", ModCategory.Ghost);
        this.addValues(range);
    }
}
