package com.krafty.kraftkapitator;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;



public class TreeHandler implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	  public void breakingBlock(BlockBreakEvent e) {
	    if (e.isCancelled())
	      return; 
	    if (e.getBlock().getType() != Material.ACACIA_LOG && 
	    		e.getBlock().getType() != Material.BIRCH_LOG &&
	    		e.getBlock().getType() != Material.DARK_OAK_LOG &&
	    		e.getBlock().getType() != Material.OAK_LOG &&
	    		e.getBlock().getType() != Material.JUNGLE_LOG &&
	    		e.getBlock().getType() != Material.SPRUCE_LOG) {
	      System.out.println("Item broken but not log");
	      return; 
	    }
	    if (!isAxe(e.getPlayer().getInventory().getItemInMainHand()) || !isAxe(e.getPlayer().getInventory().getItemInOffHand()))
	      return; 
	    if (!e.getPlayer().getGameMode().equals(GameMode.SURVIVAL))
	      return; 
	    breakBlock(e.getBlock(), e.getPlayer());
	  }
	  
	  public void breakBlock(Block b, Player p) {
	    b.breakNaturally();
	    Location above = new Location(b.getWorld(), b.getLocation().getBlockX(), (b.getLocation().getBlockY() + 1), b.getLocation().getBlockZ());
	    Block blockAbove = above.getBlock();
	    if (blockAbove.getType() == Material.ACACIA_LOG || 
	    	blockAbove.getType() == Material.BIRCH_LOG ||
	    	blockAbove.getType() == Material.DARK_OAK_LOG ||
	    	blockAbove.getType() == Material.OAK_LOG ||
	    	blockAbove.getType() == Material.JUNGLE_LOG ||
	    	blockAbove.getType() == Material.SPRUCE_LOG) {

	    	breakBlock(blockAbove, p);
	      
	    	p.getInventory().getItemInMainHand().setDurability((short)(p.getInventory().getItemInMainHand().getDurability() + 1));
	    	if (p.getInventory().getItemInMainHand().getDurability() > p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
	    		p.getInventory().remove(p.getInventory().getItemInMainHand());
	    		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	    	} 
	    } 
	  }
	  
	  public boolean isAxe(ItemStack item) {
	    if (item.getType().equals(Material.WOODEN_AXE) || item
	      .getType().equals(Material.STONE_AXE) || item
	      .getType().equals(Material.IRON_AXE) || item
	      .getType().equals(Material.GOLDEN_AXE) || item
	      .getType().equals(Material.DIAMOND_AXE) || item
	      .getType().equals(Material.NETHERITE_AXE)
	      )
	      return true; 
	    return false;
	  }
}
