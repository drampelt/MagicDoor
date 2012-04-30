package ca.drmc.magicdoor;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicDoor extends JavaPlugin implements Listener {
	ArrayList<Door> doors;
    public void onDisable() {
        // TODO: Place any custom disable code here.
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        doors = new ArrayList<Door>();
        //loadConfiguration();
        doors.add(new Door(245, 63, 254, "world", "test", true, this));
        doors.add(new Door(245, 63, 259, "world", "space", true, this));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        for(int i = 0; i < doors.size(); i++){
        	Player p = event.getPlayer();
        	Door d = doors.get(i);
        	World w = getServer().getWorld(d.getWorld());
        	Block b = w.getBlockAt(d.getBasex(), d.getBasey(), d.getBasez());
        	Location l = b.getLocation();
        	if(p.getWorld() == w){
	        	if(p.getLocation().distance(l) < 3 && p.hasPermission("magicdoor.use." + d.getName())){
	        		d.setOpen(true);
	        	}
        	}
        	d.update();
        }
    }
    
    public void loadConfiguration(){
    	getConfig().addDefault("doors", "245,95,254,world,doorone");
    	getConfig().options().copyDefaults(true);
    	String[] doorlist = getConfig().getString("doors").split(";");
    	for(int i = 0; i < doorlist.length; i++){
    		String[] dooroptions = doorlist[i].split(",");
    		doors.add(new Door(Integer.parseInt(dooroptions[0]), Integer.parseInt(dooroptions[1]), Integer.parseInt(dooroptions[2]), dooroptions[3], dooroptions[4], false, this));
    	}
    	saveConfig();
    }
}

