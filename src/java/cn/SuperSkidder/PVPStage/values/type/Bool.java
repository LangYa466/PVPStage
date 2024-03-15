package cn.SuperSkidder.PVPStage.values.type;

import cn.SuperSkidder.PVPStage.values.Value;

public class Bool<B> extends Value<B> {
    public Bool(String name,B value){
        super(name);
        this.setValue(value);
    }
}
