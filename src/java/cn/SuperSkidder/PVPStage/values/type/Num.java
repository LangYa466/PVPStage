package cn.SuperSkidder.PVPStage.values.type;

import cn.SuperSkidder.PVPStage.values.Value;

public class Num<N extends Number> extends Value<N> {
    N max,min;
    public Num(String name,N val,N min,N max) {
        super(name);
        this.setValue(val);
        this.max = max;
        this.min = min;
    }
    public N getMax(){
        return this.max;
    }
    public N getMin(){
        return this.min;
    }
}
