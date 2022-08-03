package unlimited.litehacks.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.message.ChatMessageSigner;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ClientPlayerEntity.class)
public class MixinPlayerClientEntity {




    @Inject(method = "sendChatMessagePacket", at = @At("HEAD"), cancellable = true)
    private void onChatSent(ChatMessageSigner signer, String message, Text preview, CallbackInfo ci){
/*
        FlightCommand.onCommand(message, ci);
        CrashCommand.onCommand(message, ci);
        CreativeCommand.onCommand(message, ci);
        GhostCommand.onCommand(message, ci);
        VanishCommand.onCommand(message, ci);
        VisionCommand.onCommand(message, ci);

 */


    }

}
