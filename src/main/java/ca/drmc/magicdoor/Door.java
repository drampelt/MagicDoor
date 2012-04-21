package ca.drmc.magicdoor;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Door {
	int basex, basey, basez;
	String world, name;
	Boolean open;
	ArrayList<Block> blocks = new ArrayList<Block>();
	private MagicDoor plugin;
	public Door(int basex, int basey, int basez, String world, String name, Boolean open, MagicDoor plugin){
		this.basex = basex;
		this.basey = basey;
		this.basez = basez;
		this.world = world;
		this.name = name;
		this.open = open;
		this.plugin = plugin;
		
		World w = plugin.getServer().getWorld(world);
		blocks.add(w.getBlockAt(basex, basey + 1, basez));
		blocks.add(w.getBlockAt(basex, basey + 2, basez));
		blocks.add(w.getBlockAt(basex, basey + 1, basez + 1));
		blocks.add(w.getBlockAt(basex, basey + 2, basez + 1));
		blocks.add(w.getBlockAt(basex, basey + 1, basez - 1));
		blocks.add(w.getBlockAt(basex, basey + 2, basez - 1));
	}
	
	public void update(){
		for(int i = 0; i < blocks.size(); i++){
			if(open){
				blocks.get(i).setType(Material.AIR);
			}else{
				blocks.get(i).setType(Material.GLASS);
			}
		}
	}
	
	public int getBasex() {
		return basex;
	}
	public void setBasex(int basex) {
		this.basex = basex;
	}
	public int getBasey() {
		return basey;
	}
	public void setBasey(int basey) {
		this.basey = basey;
	}
	public int getBasez() {
		return basez;
	}
	public void setBasez(int basez) {
		this.basez = basez;
	}
	public String getWorld() {
		return world;
	}
	public void setWorld(String world) {
		this.world = world;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	
	
	
}
