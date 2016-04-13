package cyano.lootable;


import cyano.lootable.entities.EntityLootableBody;
import cyano.lootable.events.PlayerDeathEventHandler;
import cyano.lootable.graphics.GUIHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = LootableBodies.MODID, name=LootableBodies.NAME, version = LootableBodies.VERSION)
public class LootableBodies {
    public static final String MODID = "lootablebodies";
    public static final String NAME ="DrCyano's Lootable Bodies";
    public static final String VERSION = "2.0.0";
	private static LootableBodies INSTANCE = null;

	public static boolean displayNameTag = true;
    public static boolean addBonesToCorpse = true;
    public static boolean pileOfBones = false; // TODO: non-biped model of a skull
	public static long ticksPerItemDecay = 10 * 60 * 20; // -1 to disable
	public static boolean hurtByEnvironment = false;
	public static boolean hurtByAttacks = false;
	public static boolean hurtByOther = false;
	public static boolean hurtByMisc = false;
	public static boolean completelyInvulnerable = false;
	public static float corpseHP = 20;
    
    public static boolean allowCorpseDecay = true;
    public static boolean decayOnlyWhenEmpty = true;
    public static long corpseDecayTime = 3600*20;
    
    @SidedProxy(clientSide="cyano.lootable.ClientProxy", serverSide="cyano.lootable.ServerProxy")
    public static Proxy proxy;
    
	
	// Mark this method for receiving an FMLEvent (in this case, it's the FMLPreInitializationEvent)
    @EventHandler public void preInit(FMLPreInitializationEvent event)
    {
		INSTANCE = this;
        // Do stuff in pre-init phase (read config, create blocks and items, register them)
    	// load config
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	
    	boolean invulnderable = true;
		// TODO: config
/*
    	corpseHP = config.getFloat("corpse_HP", "options", 50, 1,Short.MAX_VALUE,
				"The amount of damage a corpse can suffer before being \n"
				+ "destroyed and releasing its items. \n"
				+ "Note that 10 hearts = 20 HP.");
    	fancyCorpses = config.getBoolean("use_player_skin", "options", true,
				"If true, corpses will have the skins of the player who \n"
				+ "died. If false, then skeletons will be used instead.");
    	displayNameTag = config.getBoolean("display_nametag", "options", displayNameTag,
				"If true, corpses will show their owner's name");
    	
	addBonesToCorpse = config.getBoolean("add_bones_to_corpse", "options", true,
			"If true, corpses will have bones and rotten flesh added to them.");

    	corpseAuxilleryInventorySize = Math.max(
    			config.getInt("corpse_inventory_size", "options", 
    					corpseAuxilleryInventorySize + EntityLootableBody.INVENTORY_SIZE, 
    					EntityLootableBody.INVENTORY_SIZE,Short.MAX_VALUE/2,
    					"The maximum number of items that can be stored in a \n"
    					+ "corpse. Note that only 54 can be seen at a time")
    			- EntityLootableBody.INVENTORY_SIZE,0);

    	EntityLootableBody.hurtByAll = config.getBoolean("hurt_by_all", "corpse damage", false,
				"If true, corpses will be damaged by anything that damages a player.");
    	EntityLootableBody.hurtByFire = config.getBoolean("hurt_by_fire", "corpse damage", false,
				"If true, corpses will be damaged by fire and lava.");
    	EntityLootableBody.hurtByBlast = config.getBoolean("hurt_by_explosions", "corpse damage", false,
				"If true, corpses will be damaged by creepers and TNT. \n"
				+ "If you don't want bodies to be destroyed by explosions, \n"
				+ "also disable fall damage.");
    	EntityLootableBody.hurtByFall = config.getBoolean("hurt_by_fall", "corpse damage", false,
				"If true, corpses will be damaged by falling long distances.");
    	EntityLootableBody.hurtByCactus = config.getBoolean("hurt_by_cactus", "corpse damage", false,
				"If true, corpses will be damaged by cacti.");
    	EntityLootableBody.hurtByWeapons = config.getBoolean("hurt_by_weapons", "corpse damage", false,
				"If true, corpses will be damaged by attacking it.");
    	EntityLootableBody.hurtByBlockSuffocation = config.getBoolean("hurt_by_block_suffocation", "corpse damage", false,
				"If true, corpses will be damaged by being stuck inside a block.");
    	EntityLootableBody.hurtByOther = config.getBoolean("hurt_by_other", "corpse damage", false,
				"If true, corpses will be damaged by damage sources not covered by the other options in this section.");
    	
    	
    	EntityLootableBody.invulnerable = !or(
    			EntityLootableBody.hurtByAll, 
    			EntityLootableBody.hurtByBlast, 
    			EntityLootableBody.hurtByBlockSuffocation, 
    			EntityLootableBody.hurtByCactus, 
    			EntityLootableBody.hurtByFall, 
    			EntityLootableBody.hurtByFire, 
    			EntityLootableBody.hurtByOther, 
    			EntityLootableBody.hurtByWeapons);
    	
    	
    	allowCorpseDecay = config.getBoolean("enable_corpse_decay", "corpse decay", false,
				"If true, corpses will self-destruct after a period of time.");
    	decayOnlyWhenEmpty = config.getBoolean("empty_only_decay", "corpse decay", false,
				"If true and enable_corpse_decay is also true, corpses will \n"
				+ "self-destruct after being empty for a period of time (will \n"
				+ "not decay if there are any items on the corpse). If using this \n"
				+ "option, you will probably want to also disable the \n"
				+ "add_bones_to_corpse option.");
    	String decayTime = config.getString("corpse_decay_time", "corpse decay", "1:00:00",
				"Time after death before a corpse will self-destruct (if the \n"
				+ "enable_corpse_decay option is set to true). \n"
				+ "The format is hours:minutes:seconds or just hours:minutes");
    	corpseDecayTime = Math.max(parseTimeInSeconds(decayTime),2)*20; // 2 second minimum
*/

		config.save();
		proxy.preInit(event);
    }

	
	private int parseTimeInSeconds(String time) {
		String[] component = time.split(":");
		int hr = 0,min=0,sec=0;
		if(component.length > 0)hr = Integer.parseInt(component[0].trim());
		if(component.length > 1)min = Integer.parseInt(component[1].trim());
		if(component.length > 2)sec = Integer.parseInt(component[2].trim());
		return 3600 * hr + 60 * min + sec;
	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
		// register entities
		registerItemRenders();
		
		registerEntity(EntityLootableBody.class);
		MinecraftForge.EVENT_BUS.register(new PlayerDeathEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler( LootableBodies.getInstance(), GUIHandler.getInstance());
 		
		proxy.init(event);
		
		
	}
	private int entityIndex = 0;
	private void registerEntity(Class entityClass){
		String idName = "Corpse";
 		EntityRegistry.registerModEntity(entityClass, idName, entityIndex++/*mod-specific entity id*/, this, 32/*trackingRange*/, 1/*updateFrequency*/, true/*sendsVelocityUpdates*/);
 		
	}
    
    private void registerItemRenders() {
    	// client-side only
    	if(proxy instanceof ServerProxy) return;
    //	registerItemRender(wandGeneric,OrdinaryWand.itemName);
	}


	
	@EventHandler public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static LootableBodies getInstance(){
		return INSTANCE;
	}
	
	
	private static boolean or(boolean... bools){
		for(int i = 0; i < bools.length; i++){
			if(bools[i] == true) return true;
		}
		return false;
	}
	
	private static boolean and(boolean... bools){
		for(int i = 0; i < bools.length; i++){
			if(bools[i] == false) return false;
		}
		return true;
	}
}
