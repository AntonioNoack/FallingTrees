package me.corperateraider.fallingtrees.protection;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

/**
 * catches all unwanted messages :3
 * */
public class BlockBreakTestPlayer implements org.bukkit.entity.Player {
	
	@Override
	public int getLastDamage() {
		return o.getLastDamage();
	}

	@Override
	public int getHealth() {
		return o.getHealth();
	}

	@Override
	public int getMaxHealth() {
		return o.getMaxHealth();
	}
	
	private Player o;
	public BlockBreakTestPlayer(Player original){this.o = original;}
	
	public void closeInventory() {}
	public Inventory getEnderChest() {return o.getEnderChest();}
	public int getExpToLevel() {return o.getExpToLevel();}
	public GameMode getGameMode() {return o.getGameMode();}
	public PlayerInventory getInventory() {return o.getInventory();}
	public ItemStack getItemInHand() {return o.getItemInHand();}
	public ItemStack getItemOnCursor() {return o.getItemOnCursor();}
	public String getName() {return o.getName();}
	public InventoryView getOpenInventory() {return o.getOpenInventory();}
	public int getSleepTicks() {return o.getSleepTicks();}
	public boolean isBlocking() {return o.isBlocking();}
	public boolean isSleeping() {return o.isSleeping();}
	public InventoryView openEnchanting(Location arg0, boolean arg1) {return null;}
	public InventoryView openInventory(Inventory arg0) {return null;}

	public void openInventory(InventoryView arg0) {}
	public InventoryView openWorkbench(Location arg0, boolean arg1) {return null;}

	public void setGameMode(GameMode arg0) {}
	public void setItemInHand(ItemStack arg0) {}
	public void setItemOnCursor(ItemStack arg0) {}
	public boolean setWindowProperty(Property arg0, int arg1) {return false;}

	public boolean addPotionEffect(PotionEffect arg0) {return false;}
	public boolean addPotionEffect(PotionEffect arg0, boolean arg1) {return false;}
	public boolean addPotionEffects(Collection<PotionEffect> arg0) {return false;}
	
	public Collection<PotionEffect> getActivePotionEffects() {return o.getActivePotionEffects();}

	public boolean getCanPickupItems() {return o.getCanPickupItems();}
	public String getCustomName() {return o.getCustomName();}
	public EntityEquipment getEquipment() {return o.getEquipment();}
	public double getEyeHeight() {return o.getEyeHeight();}
	public double getEyeHeight(boolean arg0) {return o.getEyeHeight(arg0);}
	public Location getEyeLocation() {return o.getEyeLocation();}
	public Player getKiller() {return o.getKiller();}
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> arg0, int arg1) {return o.getLastTwoTargetBlocks(arg0, arg1);}
	public Entity getLeashHolder() throws IllegalStateException {return o.getLeashHolder();}
	public List<Block> getLineOfSight(HashSet<Byte> arg0, int arg1) {return o.getLineOfSight(arg0, arg1);}
	public int getMaximumAir() {return o.getMaximumAir();}
	public int getMaximumNoDamageTicks() {return o.getMaximumNoDamageTicks();}
	public int getNoDamageTicks() {return o.getNoDamageTicks();}
	public int getRemainingAir() {return o.getRemainingAir();}
	public boolean getRemoveWhenFarAway() {return o.getRemoveWhenFarAway();}
	public Block getTargetBlock(HashSet<Byte> arg0, int arg1) {return o.getTargetBlock(arg0, arg1);}

	public boolean hasLineOfSight(Entity arg0) {return o.hasLineOfSight(arg0);}
	public boolean hasPotionEffect(PotionEffectType arg0) {return o.hasPotionEffect(arg0);}
	
	public boolean isCustomNameVisible() {return o.isCustomNameVisible();}
	public boolean isLeashed() {return o.isLeashed();}

	public void removePotionEffect(PotionEffectType arg0) {}
	
	public void setCanPickupItems(boolean arg0) {}
	public void setCustomName(String arg0) {}
	public void setCustomNameVisible(boolean arg0) {}
	public void setLastDamage(double arg0) {}
	public void setLastDamage(int arg0) {}
	public boolean setLeashHolder(Entity arg0) {return false;}
	public void setMaximumAir(int arg0) {}
	public void setMaximumNoDamageTicks(int arg0) {}
	public void setNoDamageTicks(int arg0) {}
	public void setRemainingAir(int arg0) {}
	public void setRemoveWhenFarAway(boolean arg0) {}
	
	public Arrow shootArrow() {return null;}
	public Egg throwEgg() {return null;}
	public Snowball throwSnowball() {return null;}

	public boolean eject() {return false;}

	public int getEntityId() {return o.getEntityId();}
	public float getFallDistance() {return o.getFallDistance();}
	public int getFireTicks() {return o.getFireTicks();}
	public EntityDamageEvent getLastDamageCause() {return o.getLastDamageCause();}
	public Location getLocation() {return o.getLocation();}
	public Location getLocation(Location arg0) {return o.getLocation(arg0);}
	public int getMaxFireTicks() {return o.getMaxFireTicks();}
	public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {return o.getNearbyEntities(arg0, arg1, arg2);}
	public Entity getPassenger() {return o.getPassenger();}
	public Server getServer() {return o.getServer();}
	public int getTicksLived() {return o.getTicksLived();}
	public EntityType getType() {return o.getType();}
	public UUID getUniqueId() {return o.getUniqueId();}
	public Entity getVehicle() {return o.getVehicle();}
	public Vector getVelocity() {return o.getVelocity();}
	public World getWorld() {return o.getWorld();}
	
	public boolean isDead() {return o.isDead();}
	public boolean isEmpty() {return o.isEmpty();}
	public boolean isInsideVehicle() {return o.isInsideVehicle();}
	public boolean isValid() {return o.isValid();}
	public boolean leaveVehicle() {return false;}
	
	public void playEffect(EntityEffect arg0) {}
	public void remove() {}

	public void setFallDistance(float arg0) {}
	public void setFireTicks(int arg0) {}
	public void setLastDamageCause(EntityDamageEvent arg0) {}
	public boolean setPassenger(Entity arg0) {return false;}
	public void setTicksLived(int arg0) {}
	public void setVelocity(Vector arg0) {}
	public boolean teleport(Location arg0) {return false;}
	public boolean teleport(Entity arg0) {return false;}
	public boolean teleport(Location arg0, TeleportCause arg1) {return false;}
	public boolean teleport(Entity arg0, TeleportCause arg1) {return false;}

	public List<MetadataValue> getMetadata(String arg0) {return o.getMetadata(arg0);}
	public boolean hasMetadata(String arg0) {return o.hasMetadata(arg0);}
	public void removeMetadata(String arg0, Plugin arg1) {}
	public void setMetadata(String arg0, MetadataValue arg1) {}

	public void damage(double arg0) {}
	public void damage(int arg0) {}
	public void damage(double arg0, Entity arg1) {}
	public void damage(int arg0, Entity arg1) {}

	public void resetMaxHealth() {}
	
	public void setHealth(double arg0) {}
	public void setHealth(int arg0) {}
	public void setMaxHealth(double arg0) {}
	public void setMaxHealth(int arg0) {}

	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0) {return null;}
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1) {return null;}

	public PermissionAttachment addAttachment(Plugin arg0) {return null;}
	public PermissionAttachment addAttachment(Plugin arg0, int arg1) {return null;}
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {return null;}
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {return null;}

	public Set<PermissionAttachmentInfo> getEffectivePermissions() {return o.getEffectivePermissions();}
	public boolean hasPermission(String arg0) {return o.hasPermission(arg0);}
	public boolean hasPermission(Permission arg0) {return o.hasPermission(arg0);}
	public boolean isPermissionSet(String arg0) {return o.isPermissionSet(arg0);}
	public boolean isPermissionSet(Permission arg0) {return o.isPermissionSet(arg0);}
	public void recalculatePermissions() {o.recalculatePermissions();}

	public void removeAttachment(PermissionAttachment arg0) {}

	public boolean isOp() {return o.isOp();}
	public void setOp(boolean arg0) {}

	public void abandonConversation(Conversation arg0) {}
	public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {}
	public void acceptConversationInput(String arg0) {}
	public boolean beginConversation(Conversation arg0) {return false;}
	public boolean isConversing() {return o.isConversing();}

	public void sendMessage(String arg0) {}
	public void sendMessage(String[] arg0) {}
	
	public long getFirstPlayed() {return o.getFirstPlayed();}
	public long getLastPlayed() {return o.getLastPlayed();}

	public Player getPlayer() {return this;}
	
	public boolean hasPlayedBefore() {return o.hasPlayedBefore();}
	public boolean isBanned() {return o.isBanned();}
	public boolean isOnline() {return o.isOnline();}
	public boolean isWhitelisted() {return o.isWhitelisted();}

	public void setBanned(boolean arg0) {}
	public void setWhitelisted(boolean arg0) {}

	public Map<String, Object> serialize() {return o.serialize();}
	public Set<String> getListeningPluginChannels() {return o.getListeningPluginChannels();}
	
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {}
	public void awardAchievement(Achievement arg0) {}

	public boolean canSee(Player arg0) {return o.canSee(arg0);}
	public void chat(String arg0) {}

	public void decrementStatistic(Statistic arg0) {}
	public void decrementStatistic(Statistic arg0, int arg1) {}
	public void decrementStatistic(Statistic arg0, Material arg1) {}
	public void decrementStatistic(Statistic arg0, EntityType arg1) {}
	public void decrementStatistic(Statistic arg0, Material arg1, int arg2) {}
	public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2) {}

	public InetSocketAddress getAddress() {return o.getAddress();}
	public boolean getAllowFlight() {return o.getAllowFlight();}
	public Location getBedSpawnLocation() {return o.getBedSpawnLocation();}
	public Location getCompassTarget() {return o.getCompassTarget();}
	public String getDisplayName() {return o.getDisplayName();}

	public float getExhaustion() {return o.getExhaustion();}
	public float getExp() {return o.getExp();}
	public float getFlySpeed() {return o.getFlySpeed();}
	public int getFoodLevel() {return o.getFoodLevel();}
	public double getHealthScale() {return o.getHealthScale();}
	public int getLevel() {return o.getLevel();}
	public String getPlayerListName() {return o.getPlayerListName();}
	public long getPlayerTime() {return o.getPlayerTime();}
	public long getPlayerTimeOffset() {return o.getPlayerTimeOffset();}
	public WeatherType getPlayerWeather() {return o.getPlayerWeather();}
	public float getSaturation() {return o.getSaturation();}
	public Scoreboard getScoreboard() {return o.getScoreboard();}
	
	public int getStatistic(Statistic arg0) throws IllegalArgumentException {return o.getStatistic(arg0);}
	public int getStatistic(Statistic arg0, Material arg1) {return o.getStatistic(arg0, arg1);}
	public int getStatistic(Statistic arg0, EntityType arg1) {return o.getStatistic(arg0, arg1);}
	
	public int getTotalExperience() {return o.getTotalExperience();}
	public float getWalkSpeed() {return o.getWalkSpeed();}

	public void giveExp(int arg0) {}
	public void giveExpLevels(int arg0) {}

	public boolean hasAchievement(Achievement arg0) {return o.hasAchievement(arg0);}

	public void hidePlayer(Player arg0) {}

	public void incrementStatistic(Statistic arg0) {}
	public void incrementStatistic(Statistic arg0, int arg1) {}
	public void incrementStatistic(Statistic arg0, Material arg1) {}
	public void incrementStatistic(Statistic arg0, EntityType arg1) {}
	public void incrementStatistic(Statistic arg0, Material arg1, int arg2) {}
	public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) {}
	
	public boolean isFlying() {return o.isFlying();}
	public boolean isHealthScaled() {return o.isHealthScaled();}
	public boolean isOnGround() {return o.isOnGround();}	
	public boolean isPlayerTimeRelative() {return o.isPlayerTimeRelative();}
	public boolean isSleepingIgnored() {return o.isSleepingIgnored();}
	public boolean isSneaking() {return o.isSneaking();}
	public boolean isSprinting() {return o.isSprinting();}
	
	public void kickPlayer(String arg0) {}
	public void loadData() {o.loadData();}
	public boolean performCommand(String arg0) {return o.performCommand(arg0);}
	
	public void playEffect(Location arg0, Effect arg1, int arg2) {}
	public <T> void playEffect(Location arg0, Effect arg1, T arg2) {}
	public void playNote(Location arg0, byte arg1, byte arg2) {}
	public void playNote(Location arg0, Instrument arg1, Note arg2) {}
	public void playSound(Location arg0, Sound arg1, float arg2, float arg3) {}
	public void playSound(Location arg0, String arg1, float arg2, float arg3) {}
	public void removeAchievement(Achievement arg0) {}
	public void resetPlayerTime() {}
	public void resetPlayerWeather() {}
	public void saveData() {}
	public void sendBlockChange(Location arg0, Material arg1, byte arg2) {}
	public void sendBlockChange(Location arg0, int arg1, byte arg2) {}
	public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3, byte[] arg4) {return false;}
	public void sendMap(MapView arg0) {}
	public void sendRawMessage(String arg0) {}
	public void setAllowFlight(boolean arg0) {}
	public void setBedSpawnLocation(Location arg0) {}
	public void setBedSpawnLocation(Location arg0, boolean arg1) {}
	public void setCompassTarget(Location arg0) {}
	public void setDisplayName(String arg0) {}
	public void setExhaustion(float arg0) {}
	public void setExp(float arg0) {}
	public void setFlySpeed(float arg0) throws IllegalArgumentException {}
	public void setFlying(boolean arg0) {}
	public void setFoodLevel(int arg0) {}
	public void setHealthScale(double arg0) throws IllegalArgumentException {}
	public void setHealthScaled(boolean arg0) {}
	public void setLevel(int arg0) {}
	public void setPlayerListName(String arg0) {}
	public void setPlayerTime(long arg0, boolean arg1) {}
	public void setPlayerWeather(WeatherType arg0) {}
	public void setResourcePack(String arg0) {}
	public void setSaturation(float arg0) {}
	public void setScoreboard(Scoreboard arg0) throws IllegalArgumentException, IllegalStateException {}
	public void setSleepingIgnored(boolean arg0) {}
	public void setSneaking(boolean arg0) {}
	public void setSprinting(boolean arg0) {}
	public void setStatistic(Statistic arg0, int arg1)throws IllegalArgumentException {}
	public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {}
	public void setStatistic(Statistic arg0, EntityType arg1, int arg2) {}
	public void setTexturePack(String arg0) {}
	public void setTotalExperience(int arg0) {}
	public void setWalkSpeed(float arg0) throws IllegalArgumentException {}
	public void showPlayer(Player arg0) {}
	public void updateInventory() {o.updateInventory();}
}
