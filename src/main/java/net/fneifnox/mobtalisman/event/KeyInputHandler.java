package net.fneifnox.mobtalisman.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fneifnox.mobtalisman.networking.packet.*;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;


public class KeyInputHandler {

    private final PlayerEntity player;

    public KeyInputHandler(PlayerEntity player) {
        this.player = player;
    }


    public static final String KEY_CATEGORY_MOB_TALISMAN = "key.mob-talisman.category.mob-talisman";
    public static final String KEY_BREEZE_ABILITY = "key.mob-talisman.breeze";
    public static final String KEY_GHAST_ABILITY = "key.mob-talisman.ghast";
    public static final String KEY_CAMEL_ABILITY = "key.mob-talisman.camel";
    public static final String KEY_IRON_GOLEM_ABILITY = "key.mob-talisman.iron_golem";
    public static final String KEY_DONKEY_ABILITY = "key.mob-talisman.donkey";

    public static KeyBinding breezeAbilityKey;
    public static KeyBinding ghastAbilityKey;
    public static KeyBinding camelAbilityKey;
    public static KeyBinding ironGolemAbilityKey;
    public static KeyBinding donkeyAbilityKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (breezeAbilityKey.wasPressed()) {
                ClientPlayNetworking.send(new BreezeC2SPacket());
            }
            if (ghastAbilityKey.wasPressed()) {
                ClientPlayNetworking.send(new GhastC2SPacket());
            }
            if (camelAbilityKey.wasPressed()) {
                ClientPlayNetworking.send(new CamelC2SPacket());
            }
            if (ironGolemAbilityKey.wasPressed()) {
                ClientPlayNetworking.send(new IronGolemC2SPacket());
            }
            if (donkeyAbilityKey.wasPressed()) {
                ClientPlayNetworking.send(new DonkeyC2SPacket());
            }
        });
    }

    public static void register() {
        breezeAbilityKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_BREEZE_ABILITY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY_MOB_TALISMAN
        ));
        camelAbilityKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CAMEL_ABILITY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_MOB_TALISMAN
        ));
        ghastAbilityKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_GHAST_ABILITY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY_MOB_TALISMAN
        ));
        ironGolemAbilityKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_IRON_GOLEM_ABILITY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, KEY_CATEGORY_MOB_TALISMAN
        ));
        donkeyAbilityKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DONKEY_ABILITY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_MOB_TALISMAN
        ));

        registerKeyInputs();
    }
}
