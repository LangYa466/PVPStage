package cn.SuperSkidder.PVPStage.guis;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.events.EventRender2D;
import cn.SuperSkidder.PVPStage.manager.event.EventTarget;
import cn.SuperSkidder.PVPStage.utils.CompassUtil;
import net.minecraft.client.gui.ScaledResolution;

public class Compass extends Mod {
	public Compass() {
		super("Compass", ModCategory.Gui);
	}

	@EventTarget
	private void onrender(EventRender2D e) {
		CompassUtil cpass = new CompassUtil(325, 325, 1, 2, true);
		ScaledResolution sc = new ScaledResolution(mc);
		cpass.draw(sc);
	}
}