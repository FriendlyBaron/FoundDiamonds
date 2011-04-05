package me.itsatacoshop247.FoundDiamonds;

import java.io.BufferedWriter;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.itsatacoshop247.FoundDiamonds.FoundDiamondsLoadSettings;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class FoundDiamondsBlockListener extends BlockListener  {
	public static FoundDiamonds plugin;
	private long lastTimediamonds=0;
	private long lastTimeredstone=0;
	private long lastTimeiron=0;
	private long lastTimegold=0;
	private long lastTimelapis=0;
	private String playername;
	private String blockname;
	public static PermissionHandler Permissions;
	public FoundDiamondsBlockListener(FoundDiamonds instance) {
		plugin = instance;
	}
	@SuppressWarnings("deprecation")
	public void onBlockBreak(BlockBreakEvent event){
		
		int randomnumber = (int)(Math.random()*100);
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		playername = event.getPlayer().getName();
		blockname = event.getBlock().getType().toString();
		blockname = blockname.toLowerCase().replace("_"," ");
		
		Date todaysDate = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss");
		String formattedDate = formatter.format(todaysDate);
		
		if(block.getType() == Material.DIAMOND_ORE && FoundDiamondsLoadSettings.logging){
			try {
			    BufferedWriter out = new BufferedWriter(new FileWriter("plugins/FoundDiamonds/logs.txt", true));
			    out.write("["+formattedDate+"] "+ block.getType() + " broken by "+player.getName()+" at (x-"+block.getX()+", y-"+block.getY()+", z-"+block.getZ()+")");
			    out.newLine();
			    out.close();
			} catch (IOException e) {
			}
		}
		if(block.getType() == Material.DIAMOND_ORE && FoundDiamondsLoadSettings.diamondadmin){
			for(Player p: plugin.getServer().getOnlinePlayers()){
				if(hasPermission(p, "FD.admin")){
				p.sendMessage(ChatColor.DARK_RED + player.getName() + " just found Diamonds(AdminMsg)");
				}
			}
		}
		if(block.getType() == Material.IRON_ORE  && FoundDiamondsLoadSettings.ironadmin){
			for(Player p: plugin.getServer().getOnlinePlayers()){
				if(hasPermission(p, "FD.admin")){
				p.sendMessage(ChatColor.DARK_RED + player.getName() + " just found Iron(AdminMsg)");
				}
			}
		}
		if(block.getType() == Material.REDSTONE_ORE  && FoundDiamondsLoadSettings.redstoneadmin){
			for(Player p: plugin.getServer().getOnlinePlayers()){
				if(hasPermission(p, "FD.admin")){
				p.sendMessage(ChatColor.DARK_RED + player.getName() + " just found Redstone(AdminMsg)");
				}
			}
		}
		if(block.getType() == Material.GOLD_ORE  && FoundDiamondsLoadSettings.goldadmin){
			for(Player p: plugin.getServer().getOnlinePlayers()){
				if(hasPermission(p, "FD.admin")){
				p.sendMessage(ChatColor.DARK_RED + player.getName() + " just found Gold(AdminMsg)");
				}
			}
		}
		if(block.getType() == Material.LAPIS_ORE  && FoundDiamondsLoadSettings.lupuslazuliadmin){
			for(Player p: plugin.getServer().getOnlinePlayers()){
				if(hasPermission(p, "FD.admin")){
				p.sendMessage(ChatColor.DARK_RED + player.getName() + " just found Lapis Lazul(AdminMsg)");
				}
			}
		}
		
		if(block.getType() == Material.DIAMOND_ORE && FoundDiamondsLoadSettings.diamond && System.currentTimeMillis()-lastTimediamonds > 25000){ //this if statement is entered when the block broken is Diamond
			if(FoundDiamondsLoadSettings.thirtysecondwait){
				lastTimediamonds = System.currentTimeMillis();
			}
			if(FoundDiamondsLoadSettings.showmessage){
				plugin.getServer().broadcastMessage(ChatColor.BLUE + FoundDiamondsLoadSettings.broadcastmessage.replace("@Player@", playername).replace("@BlockName@", blockname));
			}
			//plugin.getServer().broadcastMessage(ChatColor.RED + "DEBUGINNG: " + randomnumber + ");
			if (FoundDiamondsLoadSettings.randomitems){
				if(randomnumber < 11 && randomnumber > 5){
					if(FoundDiamondsLoadSettings.sixtoten == 263) {
						plugin.getServer().broadcastMessage(ChatColor.RED + "Everyone else got somme coal!");
					} else {
						plugin.getServer().broadcastMessage(ChatColor.RED + "Everyone else got somme " +FoundDiamondsLoadSettings.sixtoten + "!");
					}
					for(Player p: plugin.getServer().getOnlinePlayers()){

						p.getInventory().addItem(new ItemStack(FoundDiamondsLoadSettings.sixtoten, randomnumber));
						p.updateInventory();
					}
				}	
				if(randomnumber < 6){
					if(FoundDiamondsLoadSettings.onetofive == 263) {
						plugin.getServer().broadcastMessage(ChatColor.RED + "Everyone else got somme Iron Ignots!");
					} else {
						plugin.getServer().broadcastMessage(ChatColor.RED + "Everyone else got somme " +FoundDiamondsLoadSettings.onetofive + "!");
					}
					for(Player p: plugin.getServer().getOnlinePlayers()){

						p.getInventory().addItem(new ItemStack(FoundDiamondsLoadSettings.onetofive, randomnumber));
						p.updateInventory();
					}
				}
			}
		}
		if(block.getType() == Material.REDSTONE_ORE && FoundDiamondsLoadSettings.redstone && System.currentTimeMillis()-lastTimeredstone > 30000){ //this if statement is entered when the block broken is Diamond
			if(FoundDiamondsLoadSettings.thirtysecondwait){
				lastTimeredstone = System.currentTimeMillis();
			}
			if(FoundDiamondsLoadSettings.showmessage){
				plugin.getServer().broadcastMessage(ChatColor.RED + FoundDiamondsLoadSettings.broadcastmessage.replace("@Player@", playername).replace("@BlockName@", blockname));
			}
			//plugin.getServer().broadcastMessage(ChatColor.RED + "DEBUGINNG: " + randomnumber + ");
		}
		if(block.getType() == Material.GOLD_ORE && FoundDiamondsLoadSettings.gold && System.currentTimeMillis()-lastTimegold > 30000){ //this if statement is entered when the block broken is Diamond
			if(FoundDiamondsLoadSettings.thirtysecondwait){
				lastTimegold = System.currentTimeMillis();
			}
			if(FoundDiamondsLoadSettings.showmessage){
				plugin.getServer().broadcastMessage(ChatColor.GOLD + FoundDiamondsLoadSettings.broadcastmessage.replace("@Player@", playername).replace("@BlockName@", blockname));
			}
			//plugin.getServer().broadcastMessage(ChatColor.RED + "DEBUGINNG: " + randomnumber + ");
		}
		if(block.getType() == Material.IRON_ORE && FoundDiamondsLoadSettings.iron && System.currentTimeMillis()-lastTimeiron > 30000){ //this if statement is entered when the block broken is Diamond
			if(FoundDiamondsLoadSettings.thirtysecondwait){
				lastTimeiron = System.currentTimeMillis();
			}
			if(FoundDiamondsLoadSettings.showmessage){
				plugin.getServer().broadcastMessage(ChatColor.DARK_GRAY + FoundDiamondsLoadSettings.broadcastmessage.replace("@Player@", playername).replace("@BlockName@", blockname));
			}
			//plugin.getServer().broadcastMessage(ChatColor.RED + "DEBUGINNG: " + randomnumber + ");
		}
		if(block.getType() == Material.LAPIS_ORE && FoundDiamondsLoadSettings.lupuslazuli && System.currentTimeMillis()-lastTimelapis > 30000){ //this if statement is entered when the block broken is Diamond
			if(FoundDiamondsLoadSettings.thirtysecondwait){
				lastTimelapis = System.currentTimeMillis();
			}
			if(FoundDiamondsLoadSettings.showmessage){
				plugin.getServer().broadcastMessage(ChatColor.AQUA + FoundDiamondsLoadSettings.broadcastmessage.replace("@Player@", playername).replace("@BlockName@", blockname));
			}
			//plugin.getServer().broadcastMessage(ChatColor.RED + "DEBUGINNG: " + randomnumber + ");
		}
		
	}
    public Boolean hasPermission(CommandSender sender, String node) {
        if (!(sender instanceof Player)) return true;
        
        Player player = (Player) sender;
        if (Permissions != null) return Permissions.has(player, node);
        else {
                Plugin test = plugin.getServer().getPluginManager().getPlugin("Permissions");
                if (test != null) {
                        Permissions = ((Permissions) test).getHandler();
                        return Permissions.has(player, node);
                }
        }
        return player.isOp();
}
}
