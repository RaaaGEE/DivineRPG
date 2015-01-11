package net.divinerpg.utils.items;

import net.divinerpg.api.items.ItemHealingSword;
import net.divinerpg.api.items.ItemModBow;
import net.divinerpg.api.items.ItemModSword;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.items.vanilla.ItemAnchor;
import net.divinerpg.items.vanilla.ItemCorruptedCannon;
import net.divinerpg.items.vanilla.ItemInfernoSword;
import net.divinerpg.items.vanilla.ItemMaelstorm;
import net.divinerpg.items.vanilla.ItemProjectileShooter;
import net.divinerpg.items.vanilla.ItemScythe;
import net.divinerpg.items.vanilla.ItemSerenadeOfDeath;
import net.divinerpg.items.vanilla.ItemSerenadeOfHealth;
import net.divinerpg.items.vanilla.ItemSerenadeStriker;
import net.divinerpg.items.vanilla.ItemThrowable;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.material.ToolMaterialMod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class VanillaItemsWeapons {
	
	private static EntityResourceLocation x;

	public static final Item slimeSword         = new ItemModSword(ToolMaterialMod.Slime, "slimeSword");
    public static final Item oceanKnife         = new ItemModSword(ToolMaterialMod.OceanKnife, "oceanKnife");
    public static final Item aquaticMaul        = new ItemModSword(ToolMaterialMod.AquaMaul, "aquaMaul");
    public static final Item arlemiteSword      = new ItemModSword(ToolMaterialMod.Arlemite, "arlemiteStabber");
    public static final Item bedrockSword       = new ItemModSword(ToolMaterialMod.Bedrock, "bedrockSword");
    public static final Item realmiteSword      = new ItemModSword(ToolMaterialMod.Realmite, "realmiteSword");
    public static final Item rupeeSword         = new ItemModSword(ToolMaterialMod.Rupee, "rupeeRapier");
    public static final Item sandslash          = new ItemModSword(ToolMaterialMod.Sandslash, "sandslash");
    public static final Item divineSword        = new ItemModSword(ToolMaterialMod.Divine, "divineSword");
    public static final Item blueDivineSword    = new ItemModSword(ToolMaterialMod.Divine, "blueDivineSword");
    public static final Item yellowDivineSword  = new ItemModSword(ToolMaterialMod.Divine, "yellowDivineSword");
    public static final Item pinkDivineSword    = new ItemModSword(ToolMaterialMod.Divine, "pinkDivineSword");
    public static final Item purpleDivineSword  = new ItemModSword(ToolMaterialMod.Divine, "purpleDivineSword");
    public static final Item redDivineSword     = new ItemModSword(ToolMaterialMod.Divine, "redDivineSword");
    public static final Item greenDivineSword   = new ItemModSword(ToolMaterialMod.Divine, "greenDivineSword");
    public static final Item bedrockMaul        = new ItemModSword(ToolMaterialMod.Bedrock, "bedrockMaul");
    public static final Item jungleKnife        = new ItemModSword(ToolMaterialMod.JungleKnife, "jungleKnife");
    public static final Item donatorSword       = new ItemModSword(ToolMaterialMod.Donator, "donatorSword");
    public static final Item aquaticTrident     = new ItemModSword(ToolMaterialMod.AquaTrident, "aquaticTrident");
    public static final Item aquaticDagger      = new ItemModSword(ToolMaterialMod.AquaDagger, "aquaticDagger");
    public static final Item aquaton            = new ItemModSword(ToolMaterialMod.Aquaton, "aquaton");
    public static final Item sharkSword         = new ItemModSword(ToolMaterialMod.Shark, "sharkSword");
    public static final Item deathBringer       = new ItemModSword(ToolMaterialMod.DeathBringer, "deathBringer");
    public static final Item crabclawMaul       = new ItemModSword(ToolMaterialMod.CrabclawMaul, "crabclawMaul");
    public static final Item poisonSaber        = new ItemModSword(ToolMaterialMod.PoisonSaber, "poisonSaber");
    public static final Item furyMaul           = new ItemModSword(ToolMaterialMod.FuryMaul, "furyMaul");
    public static final Item corruptedMaul      = new ItemModSword(ToolMaterialMod.CorruptedMaul, "corruptedMaul");
    public static final Item frostSword         = new ItemModSword(ToolMaterialMod.Frost, "frostSword");
    public static final Item infernoSword       = new ItemInfernoSword(ToolMaterialMod.Inferno, "infernoSword");
    public static final Item bloodgemSword      = new ItemModSword(ToolMaterialMod.Bloodgem, "bloodgemSword");
    public static final Item moltenSword        = new ItemModSword(ToolMaterialMod.Molten, "moltenSword");
    public static final Item scorchingSword     = new ItemModSword(ToolMaterialMod.Scorching, "scorchingSword");
    public static final Item bluefireSword      = new ItemModSword(ToolMaterialMod.Bluefire, "bluefireSword");
    public static final Item enderSword         = new ItemModSword(ToolMaterialMod.Ender, "enderSword");
    public static final Item enderSwordBlue     = new ItemModSword(ToolMaterialMod.Ender, "enderSwordBlue");
    public static final Item enderSwordDark     = new ItemModSword(ToolMaterialMod.Ender, "enderSwordBlack");
    public static final Item enderSwordGreen    = new ItemModSword(ToolMaterialMod.Ender, "enderSwordGreen");
    public static final Item enderSwordRed      = new ItemModSword(ToolMaterialMod.Ender, "enderSwordRed");
    public static final Item enderSwordYellow   = new ItemModSword(ToolMaterialMod.Ender, "enderSwordYellow");
    public static final Item cyclopsianSword    = new ItemModSword(ToolMaterialMod.Cyclops, "cyclopsianSword");
    
    public static final Item hunterBow          = new ItemModBow("hunterBow", 2500, 2, 11, Items.arrow, "hunterArrow");
    public static final Item shadowBow          = new ItemModBow("shadowBow", 10000, 2, 11, 36000, Items.arrow, "shadowArrow");
    public static final Item enderBow           = new ItemModBow("enderBow", -1, 4, 16, "enderArrow");
    public static final Item bluefireBow        = new ItemModBow("bluefireBow", -1, 2, 11, "bluefireArrow");
    public static final Item infernoBow         = new ItemModBow("infernoBow", 10000, 2, 11, "infernoArrow");
    
    public static final Item shuriken       	= new ItemThrowable(4, "shuriken");
    public static final Item vileStorm       	= new ItemThrowable(4, "vileStorm");
    public static final Item scythe		       	= new ItemScythe("scythe");
    public static final Item frostCannon       	= new ItemProjectileShooter("frostCannon", 8F, Sounds.frostCannon.getPrefixedName(), Items.snowball, EntityResourceLocation.frostCannon.toString(), 15000, 0);
    public static final Item cyclopsianStaff    = new ItemProjectileShooter("cyclopsianStaff", 7F, Sounds.staff.getPrefixedName(), VanillaItemsOther.cyclopsEyeShards, 3000, 0);
    public static final Item maelstorm          = new ItemMaelstorm("maelstorm");
    public static final Item crabAnchor         = new ItemAnchor("crabAnchor", x.crabAnchor.toString(), 4);
    public static final Item sharkAnchor        = new ItemAnchor("sharkAnchor", x.bowheadAnchor.toString(), 5);
    public static final Item bowheadAnchor      = new ItemAnchor("bowheadAnchor", x.bowheadAnchor.toString(), 6);
    public static final Item goldenFury         = new ItemProjectileShooter("goldenFury", 32F, Sounds.blitz.getPrefixedName(), Items.gold_nugget, -1, 0);
    public static final Item corruptedCannon    = new ItemCorruptedCannon("corruptedCannon");
    public static final Item ghastCannon        = new ItemProjectileShooter("ghastCannon", 40F, Sounds.ghastCannon.getPrefixedName(), x.ghastCannon.toString(), 100, 5);
    public static final Item crabclawCannon     = new ItemProjectileShooter("crabclawCannon", 19F, Sounds.ghastCannon.getPrefixedName(), Util.toItem(Blocks.cactus), x.crabAnchor.toString(), 1000, 3);
    public static final Item bowheadCannon      = new ItemProjectileShooter("bowheadCannon", 19F, Sounds.ghastCannon.getPrefixedName(), Util.toItem(Blocks.cactus), x.bowheadAnchor.toString(), 1000, 3);

    public static final Item serenadeOfHealth 	= new ItemSerenadeOfHealth("serenadeOfHealth");
    public static final Item serenadeStriker 	= new ItemSerenadeStriker("serenadeStriker");
    public static final Item serenadeOfDeath 	= new ItemSerenadeOfDeath("serenadeOfDeath");
    
    public static final Item palavence 		    = new ItemHealingSword("palavence", ToolMaterialMod.Palavence, 1);
    public static final Item massivence 	    = new ItemHealingSword("massivence", ToolMaterialMod.Massivence, 2);
	public static final Item flamingFury		= new ItemModSword(ToolMaterialMod.FlamingFury, "flamingFury"); 
}
