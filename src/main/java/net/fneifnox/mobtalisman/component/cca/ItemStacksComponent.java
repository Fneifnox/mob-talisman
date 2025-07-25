package net.fneifnox.mobtalisman.component.cca;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.ladysnake.cca.api.v3.component.Component;

public interface ItemStacksComponent extends Component {
    DefaultedList<ItemStack> getValue();
    void setValue(DefaultedList<ItemStack> value);
}
