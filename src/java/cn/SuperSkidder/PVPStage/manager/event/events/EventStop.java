package cn.SuperSkidder.PVPStage.manager.event.events;

public abstract class EventStop {
    private boolean stoped;

    protected EventStop(){}

    public void stop(){
        stoped = true;
    }

    public boolean getstop(){
        return stoped;
    }
}
