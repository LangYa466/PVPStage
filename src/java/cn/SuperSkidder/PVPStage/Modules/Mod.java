package cn.SuperSkidder.PVPStage.Modules;

import cn.SuperSkidder.PVPStage.manager.EventManager;
import cn.SuperSkidder.PVPStage.values.Value;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class Mod {
    public String Name;
    public String DisplayName;
    public boolean Stage;
    public ModCategory Type;
    public int key;
    public static Minecraft mc = Minecraft.getMinecraft();
    public List<Value> values = new ArrayList<>();

    public Mod(String name, ModCategory type) {
        this.Name = name;
        this.Type = type;
    }

    public String getName() {
        return this.Name;
    }

    public String getDisplayName() {
        return this.DisplayName;
    }

    public boolean getState() {
        return this.Stage;
    }

    public ModCategory getType() {
        return this.Type;
    }

    public void setDisplayName(String name) {
        this.DisplayName = name;
    }

    public void setStage(boolean state) {
        this.Stage = state;
        if(state){
            onEnabled();
            EventManager.register(this);
        }else{
            onDisable();
            EventManager.unregister(this);
        }
    }

    public void setKey(int k) {
        this.key = k;
    }

    public int getKey() {
        return this.key;
    }

    public void toogle() {
        this.setStage(!this.getState());
    }


    public void onEnabled(){

    }

    public void onDisable(){

    }

    public void addValues(Value... values) {
        Value[] v1 = values;
        int vl = values.length;

        for(int i = 0; i < vl; ++i) {
            Value value = v1[i];
            this.values.add(value);
        }
    }

    public List<Value> getValues(){
        return this.values;
    }
}
