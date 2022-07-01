package me.Tamaninja.test.frontend;

import me.Tamaninja.test.entity.PalletContainer;
import me.Tamaninja.test.entity.PalletContent;

import java.io.Serializable;
import java.util.List;


public class FrontendForm implements Serializable {
    private List<PalletContent> palletContent;
    private List<PalletContainer> palletContainer;
    private List<PalletContainer> palletType;

    public FrontendForm(List<PalletContent> palletContent, List<PalletContainer> palletContainer, List<PalletContainer> palletType) {
        this.palletContent = palletContent;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
    }


    public List<PalletContent> getPalletContent() {
        return palletContent;
    }

    public List<PalletContainer> getPalletContainer() {
        return palletContainer;
    }

    public List<PalletContainer> getPalletType() {
        return palletType;
    }
}
