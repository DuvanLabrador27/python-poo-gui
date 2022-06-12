package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.utility.ViewTool;
import ufps.arqui.python.poo.gui.utils.impl.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelMundo;
import ufps.arqui.python.poo.gui.views.IPanelMundoTerminal;
import ufps.arqui.python.poo.gui.views.IPanelTerminal;

/**
 *
 * @author Sachikia
 */
public class PanelMundoTerminal implements IPanelMundoTerminal{
    private final IPanelMundo panelMundo;
    private final IPanelTerminal panelTerminal;
    private final JPanel panel;
    
    // elementos de GUI

    public PanelMundoTerminal(IPanelMundo panelMundo, IPanelTerminal panelTerminal) {
        this.panel = new JPanel(new GridBagLayout());
        
        this.panelMundo = panelMundo;
        this.panelTerminal = panelTerminal;
        
        this.inicializarContenido();
    }
    
    @Override
    public void inicializarContenido() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        splitPane.add(this.panelMundo.getPanel());
        splitPane.add(this.panelTerminal.getPanel());
        splitPane.setResizeWeight(0.6);

        ConfGrid config = new ConfGrid(this.panel, splitPane);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setFill(GridBagConstraints.BOTH);

        ViewTool.insert(config);
//        ViewTool.insert(this.panel, splitPane, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, null, 0, 0);
    }
    
    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
