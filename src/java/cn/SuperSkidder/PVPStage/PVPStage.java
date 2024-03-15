package cn.SuperSkidder.PVPStage;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.manager.EventManager;
import cn.SuperSkidder.PVPStage.manager.FileManager;
import cn.SuperSkidder.PVPStage.manager.ModuleManager;
import org.lwjgl.opengl.Display;

import java.util.List;

public class PVPStage {
    public static final String CLIENT_NAME = "PVPStage";
    public static final String CLIENT_Ver = "Beta";
    public static final PVPStage INSTANCE = new PVPStage();
    public static ModuleManager moduleManager;
    public static FileManager fileManager;

    public PVPStage(){
        System.out.println(CLIENT_NAME+" | "+CLIENT_Ver+" Client Loaded!");
        moduleManager = new ModuleManager();
        fileManager = new FileManager();
    }


    public void onLaunch(){
        Display.setTitle(CLIENT_NAME+" "+CLIENT_Ver);
        EventManager.register(this);
        List<String> enabled = FileManager.read("Enabled.txt");
        for (String v : enabled) {
            Mod m = ModuleManager.getModsByName(v);
            if (m == null) continue;
            m.setStage(true);
        }
    }

    public void onExit(){
        EventManager.unregister(this);
        System.out.println(CLIENT_NAME+" | "+CLIENT_Ver+" Client Exit!");
        String enable = "";
        for (Mod m : ModuleManager.modList) {
            if (!m.getState()) continue;
            enable = String.valueOf(enable) + String.format("%s%s", m.getName(), System.lineSeparator());
        }
        FileManager.save("Enabled.txt", enable, false);
    }
}
