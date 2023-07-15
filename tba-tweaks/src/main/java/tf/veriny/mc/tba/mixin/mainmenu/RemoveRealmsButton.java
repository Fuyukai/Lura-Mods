package tf.veriny.mc.tba.mixin.mainmenu;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public class RemoveRealmsButton extends Screen {
    protected RemoveRealmsButton(Text title) {
        super(title);
    }

    @Inject(method = "isRealmsNotificationsGuiDisplayed", at = @At("HEAD"), cancellable = true)
    void hijackRealmsNotifs(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Redirect(
            method = "initWidgetsNormal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
                    ordinal = 2
            )
    )
    Element dontAddRealmsButton(TitleScreen instance, Element element) {
        // we do have to actually return an element so we just return a dummy one.
        // thanks

        return ButtonWidget.builder(null, null).build();
    }
}
