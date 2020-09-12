package com.snowypeaksystems.mobportals.mock;

import com.destroystokyo.paper.entity.ai.MobGoals;
import com.destroystokyo.paper.profile.PlayerProfile;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;
import org.bukkit.BanList;
import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.StructureType;
import org.bukkit.Tag;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

/**
 * Dummy class for server implementation.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings
public class FakeServer implements Server {
  private final World world;

  public FakeServer(World world) {
    this.world = world;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getVersion() {
    return null;
  }

  @Override
  public String getBukkitVersion() {
    return null;
  }

  @Override
  public String getMinecraftVersion() {
    return null;
  }

  @Override
  public Collection<? extends Player> getOnlinePlayers() {
    return null;
  }

  @Override
  public int getMaxPlayers() {
    return 0;
  }

  @Override
  public void setMaxPlayers(int maxPlayers) {

  }

  @Override
  public int getPort() {
    return 0;
  }

  @Override
  public int getViewDistance() {
    return 0;
  }

  @Override
  public String getIp() {
    return null;
  }

  @Override
  public String getWorldType() {
    return null;
  }

  @Override
  public boolean getGenerateStructures() {
    return false;
  }

  @Override
  public boolean getAllowEnd() {
    return false;
  }

  @Override
  public boolean getAllowNether() {
    return false;
  }

  @Override
  public boolean hasWhitelist() {
    return false;
  }

  @Override
  public void setWhitelist(boolean value) {

  }

  @Override
  public Set<OfflinePlayer> getWhitelistedPlayers() {
    return null;
  }

  @Override
  public void reloadWhitelist() {

  }

  @Override
  public int broadcastMessage(String message) {
    return 0;
  }

  @Override
  public String getUpdateFolder() {
    return null;
  }

  @Override
  public File getUpdateFolderFile() {
    return null;
  }

  @Override
  public long getConnectionThrottle() {
    return 0;
  }

  @Override
  public int getTicksPerAnimalSpawns() {
    return 0;
  }

  @Override
  public int getTicksPerMonsterSpawns() {
    return 0;
  }

  @Override
  public int getTicksPerWaterSpawns() {
    return 0;
  }

  @Override
  public int getTicksPerWaterAmbientSpawns() {
    return 0;
  }

  @Override
  public int getTicksPerAmbientSpawns() {
    return 0;
  }

  @Override
  public Player getPlayer(String name) {
    return null;
  }

  @Override
  public Player getPlayer(UUID id) {
    return null;
  }

  @Override
  public Player getPlayerExact(String name) {
    return null;
  }

  @Override
  public List<Player> matchPlayer(String name) {
    return null;
  }

  @Override
  public UUID getPlayerUniqueId(String playerName) {
    return null;
  }

  @Override
  public PluginManager getPluginManager() {
    return null;
  }

  @Override
  public BukkitScheduler getScheduler() {
    return null;
  }

  @Override
  public ServicesManager getServicesManager() {
    return null;
  }

  @Override
  public List<World> getWorlds() {
    return null;
  }

  @Override
  public World createWorld(WorldCreator creator) {
    return null;
  }

  @Override
  public boolean unloadWorld(String name, boolean save) {
    return false;
  }

  @Override
  public boolean unloadWorld(World world, boolean save) {
    return false;
  }

  @Override
  public World getWorld(String name) {
    return world;
  }

  @Override
  public World getWorld(UUID uid) {
    return null;
  }

  @Override
  public MapView getMap(int id) {
    return null;
  }

  @Override
  public MapView createMap(World world) {
    return null;
  }

  @Override
  public ItemStack createExplorerMap(World world, Location location, StructureType structureType) {
    return null;
  }

  @Override
  public ItemStack createExplorerMap(World world, Location location, StructureType structureType,
                                     int radius, boolean findUnexplored) {
    return null;
  }

  @Override
  public void reload() {

  }

  @Override
  public void reloadData() {

  }

  @Override
  public Logger getLogger() {
    return null;
  }

  @Override
  public PluginCommand getPluginCommand(String name) {
    return null;
  }

  @Override
  public void savePlayers() {

  }

  @Override
  public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
    return false;
  }

  @Override
  public boolean addRecipe(Recipe recipe) {
    return false;
  }

  @Override
  public List<Recipe> getRecipesFor(ItemStack result) {
    return null;
  }

  @Override
  public Recipe getRecipe(NamespacedKey recipeKey) {
    return null;
  }

  @Override
  public Iterator<Recipe> recipeIterator() {
    return null;
  }

  @Override
  public void clearRecipes() {

  }

  @Override
  public void resetRecipes() {

  }

  @Override
  public boolean removeRecipe(NamespacedKey key) {
    return false;
  }

  @Override
  public Map<String, String[]> getCommandAliases() {
    return null;
  }

  @Override
  public int getSpawnRadius() {
    return 0;
  }

  @Override
  public void setSpawnRadius(int value) {

  }

  @Override
  public boolean getOnlineMode() {
    return false;
  }

  @Override
  public boolean getAllowFlight() {
    return false;
  }

  @Override
  public boolean isHardcore() {
    return false;
  }

  @Override
  public void shutdown() {

  }

  @Override
  public int broadcast(String message, String permission) {
    return 0;
  }

  @Override
  public OfflinePlayer getOfflinePlayer(String name) {
    return null;
  }

  @Override
  public OfflinePlayer getOfflinePlayer(UUID id) {
    return null;
  }

  @Override
  public Set<String> getIPBans() {
    return null;
  }

  @Override
  public void banIP(String address) {

  }

  @Override
  public void unbanIP(String address) {

  }

  @Override
  public Set<OfflinePlayer> getBannedPlayers() {
    return null;
  }

  @Override
  public BanList getBanList(BanList.Type type) {
    return null;
  }

  @Override
  public Set<OfflinePlayer> getOperators() {
    return null;
  }

  @Override
  public GameMode getDefaultGameMode() {
    return null;
  }

  @Override
  public void setDefaultGameMode(GameMode mode) {

  }

  @Override
  public ConsoleCommandSender getConsoleSender() {
    return null;
  }

  @Override
  public File getWorldContainer() {
    return null;
  }

  @Override
  public OfflinePlayer[] getOfflinePlayers() {
    return new OfflinePlayer[0];
  }

  @Override
  public Messenger getMessenger() {
    return null;
  }

  @Override
  public HelpMap getHelpMap() {
    return null;
  }

  @Override
  public Inventory createInventory(InventoryHolder owner, InventoryType type) {
    return null;
  }

  @Override
  public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
    return null;
  }

  @Override
  public Inventory createInventory(InventoryHolder owner, int size)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public Inventory createInventory(InventoryHolder owner, int size, String title)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public Merchant createMerchant(String title) {
    return null;
  }

  @Override
  public int getMonsterSpawnLimit() {
    return 0;
  }

  @Override
  public int getAnimalSpawnLimit() {
    return 0;
  }

  @Override
  public int getWaterAnimalSpawnLimit() {
    return 0;
  }

  @Override
  public int getWaterAmbientSpawnLimit() {
    return 0;
  }

  @Override
  public int getAmbientSpawnLimit() {
    return 0;
  }

  @Override
  public boolean isPrimaryThread() {
    return false;
  }

  @Override
  public String getMotd() {
    return null;
  }

  @Override
  public String getShutdownMessage() {
    return null;
  }

  @Override
  public Warning.WarningState getWarningState() {
    return null;
  }

  @Override
  public ItemFactory getItemFactory() {
    return null;
  }

  @Override
  public ScoreboardManager getScoreboardManager() {
    return null;
  }

  @Override
  public CachedServerIcon getServerIcon() {
    return null;
  }

  @Override
  public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception {
    return null;
  }

  @Override
  public CachedServerIcon loadServerIcon(BufferedImage image) throws IllegalArgumentException,
      Exception {
    return null;
  }

  @Override
  public void setIdleTimeout(int threshold) {

  }

  @Override
  public int getIdleTimeout() {
    return 0;
  }

  @Override
  public ChunkGenerator.ChunkData createChunkData(World world) {
    return null;
  }

  @Override
  public ChunkGenerator.ChunkData createVanillaChunkData(World world, int x, int z) {
    return null;
  }

  @Override
  public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
    return null;
  }

  @Override
  public KeyedBossBar createBossBar(NamespacedKey key, String title, BarColor color, BarStyle style,
                                    BarFlag... flags) {
    return null;
  }

  @Override
  public Iterator<KeyedBossBar> getBossBars() {
    return null;
  }

  @Override
  public KeyedBossBar getBossBar(NamespacedKey key) {
    return null;
  }

  @Override
  public boolean removeBossBar(NamespacedKey key) {
    return false;
  }

  @Override
  public Entity getEntity(UUID uuid) {
    return null;
  }

  @Override
  public double[] getTPS() {
    return new double[0];
  }

  @Override
  public long[] getTickTimes() {
    return new long[0];
  }

  @Override
  public double getAverageTickTime() {
    return 0;
  }

  @Override
  public CommandMap getCommandMap() {
    return null;
  }

  @Override
  public Advancement getAdvancement(NamespacedKey key) {
    return null;
  }

  @Override
  public Iterator<Advancement> advancementIterator() {
    return null;
  }

  @Override
  public BlockData createBlockData(Material material) {
    return null;
  }

  @Override
  public BlockData createBlockData(Material material, Consumer<BlockData> consumer) {
    return null;
  }

  @Override
  public BlockData createBlockData(String data) throws IllegalArgumentException {
    return null;
  }

  @Override
  public BlockData createBlockData(Material material, String data) throws IllegalArgumentException {
    return null;
  }

  @Override
  public <T extends Keyed> Tag<T> getTag(String registry, NamespacedKey tag, Class<T> clazz) {
    return null;
  }

  @Override
  public <T extends Keyed> Iterable<Tag<T>> getTags(String registry, Class<T> clazz) {
    return null;
  }

  @Override
  public LootTable getLootTable(NamespacedKey key) {
    return null;
  }

  @Override
  public List<Entity> selectEntities(CommandSender sender, String selector)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public UnsafeValues getUnsafe() {
    return null;
  }

  @Override
  public Spigot spigot() {
    return null;
  }

  @Override
  public void reloadPermissions() {

  }

  @Override
  public boolean reloadCommandAliases() {
    return false;
  }

  @Override
  public boolean suggestPlayerNamesWhenNullTabCompletions() {
    return false;
  }

  @Override
  public String getPermissionMessage() {
    return null;
  }

  @Override
  public PlayerProfile createProfile(UUID uuid) {
    return null;
  }

  @Override
  public PlayerProfile createProfile(String name) {
    return null;
  }

  @Override
  public PlayerProfile createProfile(UUID uuid, String name) {
    return null;
  }

  @Override
  public int getCurrentTick() {
    return 0;
  }

  @Override
  public boolean isStopping() {
    return false;
  }

  @Override
  public MobGoals getMobGoals() {
    return null;
  }

  @Override
  public void sendPluginMessage(Plugin source, String channel, byte[] message) {

  }

  @Override
  public Set<String> getListeningPluginChannels() {
    return null;
  }
}
