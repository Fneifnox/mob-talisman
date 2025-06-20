package net.fneifnox.mobtalisman.component;

import org.ladysnake.cca.api.v3.component.Component;

public interface BooleanComponent extends Component {
    boolean getValue();
    void setValue(boolean value);
}

