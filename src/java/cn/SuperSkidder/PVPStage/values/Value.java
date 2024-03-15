package cn.SuperSkidder.PVPStage.values;

public class Value<V> {
    public String name;
    public V value;

    public Value(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public V getValue(){
        return this.value;
    }
    public void setValue(V val){
        this.value = val;
    }
}
