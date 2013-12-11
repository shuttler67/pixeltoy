/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 18.okt.2013, 20:50:12
 */
package gui;

import java.awt.Font;

/**
 *
 * @author Bart
 */
public class MainFrame extends javax.swing.JFrame {

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        mainSplitPane.setDividerLocation(300);
        mainSplitPane.setName("mainSplitPane"); // NOI18N

        editorScrollPane.setName("editorScrollPane"); // NOI18N
        
        Font editorFont = new Font("Courier New", Font.BOLD, 14);
        
        editorPane.setFont(editorFont); // NOI18N
        editorPane.setName("editorPane"); // NOI18N
        editorScrollPane.setViewportView(editorPane);

        mainSplitPane.setTopComponent(editorScrollPane);

        renderPanel.setName("renderPanel"); // NOI18N
        renderPanel.setPreferredSize(new java.awt.Dimension(100, 100));

        canvas.setName("canvas"); // NOI18N

        javax.swing.GroupLayout renderPanelLayout = new javax.swing.GroupLayout(renderPanel);
        renderPanel.setLayout(renderPanelLayout);
        renderPanelLayout.setHorizontalGroup(
            renderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );
        renderPanelLayout.setVerticalGroup(
            renderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainSplitPane.setRightComponent(renderPanel);

        menubar.setName("menubar"); // NOI18N

        fileMenu.setText("File"); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newItem.setText("New"); // NOI18N
        newItem.setName("newItem"); // NOI18N
        fileMenu.add(newItem);

        openItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openItem.setText("Open"); // NOI18N
        openItem.setName("openItem"); // NOI18N
        fileMenu.add(openItem);

        saveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveItem.setText("Save"); // NOI18N
        saveItem.setName("saveItem"); // NOI18N
        fileMenu.add(saveItem);

        saveAsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsItem.setText("Save As"); // NOI18N
        saveAsItem.setName("saveAsItem"); // NOI18N
        fileMenu.add(saveAsItem);

        quitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitItem.setText("Quit"); // NOI18N
        quitItem.setName("quitItem"); // NOI18N
        fileMenu.add(quitItem);

        menubar.add(fileMenu);

        programMenu.setText("Program"); // NOI18N
        programMenu.setName("programMenu"); // NOI18N

        runItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.CTRL_MASK));
        runItem.setText("Run"); // NOI18N
        runItem.setName("runItem"); // NOI18N
        programMenu.add(runItem);

        stopItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        stopItem.setText("Stop"); // NOI18N
        stopItem.setName("stopItem"); // NOI18N
        programMenu.add(stopItem);

        menubar.add(programMenu);

        helpMenu.setText("Help"); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        functionReferenceItem.setText("Function Reference"); // NOI18N
        functionReferenceItem.setName("functionReferenceItem"); // NOI18N
        helpMenu.add(functionReferenceItem);

        languageReferenceItem.setText("Language Reference"); // NOI18N
        languageReferenceItem.setName("languageReferenceItem"); // NOI18N
        helpMenu.add(languageReferenceItem);

        menubar.add(helpMenu);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );

        canvas.setIgnoreRepaint(true);
        pack();
    }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
    public final java.awt.Canvas canvas = new java.awt.Canvas();
    public final javax.swing.JEditorPane editorPane = new javax.swing.JEditorPane();
    public final javax.swing.JScrollPane editorScrollPane = new javax.swing.JScrollPane();
    public final javax.swing.JMenu fileMenu = new javax.swing.JMenu();
    public final javax.swing.JMenuItem functionReferenceItem = new javax.swing.JMenuItem();
    public final javax.swing.JMenu helpMenu = new javax.swing.JMenu();
    public final javax.swing.JMenuItem languageReferenceItem = new javax.swing.JMenuItem();
    public final javax.swing.JSplitPane mainSplitPane = new javax.swing.JSplitPane();
    public final javax.swing.JMenuBar menubar = new javax.swing.JMenuBar();
    public final javax.swing.JMenuItem newItem = new javax.swing.JMenuItem();
    public final javax.swing.JMenuItem openItem = new javax.swing.JMenuItem();
    public final javax.swing.JMenu programMenu = new javax.swing.JMenu();
    public final javax.swing.JMenuItem quitItem = new javax.swing.JMenuItem();
    public final javax.swing.JPanel renderPanel = new javax.swing.JPanel();
    public final javax.swing.JMenuItem runItem = new javax.swing.JMenuItem();
    public final javax.swing.JMenuItem saveAsItem = new javax.swing.JMenuItem();
    public final javax.swing.JMenuItem saveItem = new javax.swing.JMenuItem();
    public final javax.swing.JMenuItem stopItem = new javax.swing.JMenuItem();
    // End of variables declaration//GEN-END:variables
}