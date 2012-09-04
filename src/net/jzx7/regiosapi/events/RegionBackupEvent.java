package net.jzx7.regiosapi.events;

import net.jzx7.regiosapi.regions.Region;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RegionBackupEvent extends Event {
	
	private Region region;
	private Player player;
	private String backupname;
	private static final HandlerList handlers = new HandlerList();

    public RegionBackupEvent(String name) {
        super();
    }
    
    public Region getRegion(){
    	return this.region;
    }
    
    public Player getPlayer(){
    	return this.player;
    }
    
    public String getBackupName(){
    	return this.backupname;
    }

    public void setProperties(Region region, String backupname, Player p) {
    	this.player = p;
        this.region = region;
        this.backupname = backupname;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
     
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
