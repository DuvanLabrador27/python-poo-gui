package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ufps.arqui.python.poo.gui.utility.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenu;

/**
 *
 * @author Sachikia
 */
public class ModalCrearProyecto {
    private IPanelMenu panelMenu;
    
    private String lastDir;
    private String selectDir;
    
    private JFrame frame;
    private JTextField txtName;
    private JTextField txtPath;
    private JLabel lblAbsolutePath;
    private JButton btnChoose;
    private JButton btnAceptar;
    private JButton btnCancel;

    public ModalCrearProyecto(IPanelMenu panelMenu) throws Exception {
        this.panelMenu = panelMenu;
        this.frame = new JFrame("Nuevo Proyecto");

        this.txtName = new JTextField();
        this.txtPath = new JTextField();
        this.lblAbsolutePath = new JLabel();
        this.btnChoose = new JButton("Choose");

        this.btnAceptar = new JButton("Aceptar");
        this.btnCancel = new JButton("Cancel");

        this.init();
        this.addEvents();
    }

    private void init() throws Exception {
        JPanel panelForm = new JPanel(new GridBagLayout());

        JLabel lblName = new JLabel("Nombre");
        JLabel lblPath = new JLabel("Localización");
        JLabel lblFullPath = new JLabel("Path");

        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ViewTool.insert(panelForm, lblName, 0, 0, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
        ViewTool.insert(panelForm, this.txtName, 1, 0, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 10);
        ViewTool.insert(panelForm, lblPath, 0, 1, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
        ViewTool.insert(panelForm, this.txtPath, 1, 1, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 10);
        ViewTool.insert(panelForm, this.btnChoose, 2, 1, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 0), 0, 0);
        ViewTool.insert(panelForm, lblFullPath, 0, 2, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
        ViewTool.insert(panelForm, this.lblAbsolutePath, 1, 2, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);

        JPanel panelOptions = new JPanel(new GridBagLayout());

        ViewTool.insert(panelOptions, this.btnAceptar, 0, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LAST_LINE_END, new Insets(0, 0, 0, 10), 0, 0);
        ViewTool.insert(panelOptions, this.btnCancel, 1, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LAST_LINE_END, null, 0, 0);

        ViewTool.insert(container, panelForm, 0, 0, 1, 0, 3, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, new Insets(10, 10, 10, 10), 0, 0);
        ViewTool.insert(container, panelOptions, 0, 1, 1, 1, 3, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_END, new Insets(0, 0, 10, 10), 0, 0);

        this.frame.setPreferredSize(new Dimension(500, 200));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    private void addEvents() {
        DocumentListener docEvent = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                eventChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                eventChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                eventChange();
            }

            public void eventChange() {
                lblAbsolutePath.setText(txtPath.getText() + txtName.getText());
            }
        };

        this.txtName.getDocument().addDocumentListener(docEvent);
        this.txtPath.getDocument().addDocumentListener(docEvent);

        this.btnChoose.addActionListener(e -> {
            this.askForDirectory();
        });
        
        this.btnAceptar.addActionListener(e -> {
            this.panelMenu.modalCrearProyectoEvento(this.txtName.getText(), this.txtPath.getText());
        });
    }
    
    private void askForDirectory(){
        JFileChooser chooser = new JFileChooser();
        if(this.lastDir != null) chooser.setCurrentDirectory(new File(this.lastDir));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        
        this.selectDir = chooser.getSelectedFile() != null ? chooser.getSelectedFile().getAbsolutePath() : this.selectDir;
        this.lastDir = chooser.getCurrentDirectory().getAbsolutePath();
        this.txtPath.setText(this.selectDir);
    }

    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}