package com.wajatto.millcompiler;

/*
 * SelectorScroller.java
 *
 * Created on February 25, 2006, 12:30 PM
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import creator.MenuCreator;

/**
 *
 * @author Hudhaifa
 */
public class SelectorScroller
        extends JScrollPane {

    /**
     * Creates new form SelectorScroller
     */
    public SelectorScroller() {
        setupMenus();
        addMenus();
        registrationMenus();
        setupComponents();
        addComponents();
        registration();
        setupScroller();

    } // END: SelectorScroller constructor

    public void setupMenus() {
        popupMenu = new JPopupMenu();

        // set up popup menu and its items
        popupMenu = new JPopupMenu();
        closeMenuItem = MenuCreator.creatItem("Close document", 'C');

    } // END: setupMenus

    public void addMenus() {
        popupMenu.add(closeMenuItem);

    } // END: addMenus

    public void registrationMenus() {
        closeMenuItem.addActionListener(new SelectorScrollerListener());

    } // END: registrationMenus

    /**
     * @see javax.swing.JTextArea#setFont
     * @see javax.swing.JTextComponent#setDisabledTextColor
     * @see javax.swing.JTextComponent#setEditable
     * @since 1.0
     */
    private void setupComponents() {
        mainFrame = MillStatic.getMainFrameInstance();
        MillStatic.documentsList = new JList(MillStatic.documents);
        MillStatic.documentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    } // END: setupComponents

    /**
     * @see javax.swing.JScrollPane#setViewportView
     * @since 1.0
     */
    private void addComponents() {
        setViewportView(MillStatic.documentsList);

    } // END: addComponents

    /**
     * @since 1.0
     */
    private void registration() {
        MillStatic.documentsList.addListSelectionListener(new SelectorScrollerListener());
        MillStatic.documentsList.addMouseListener(new SelectorScrollerListener());

    } // END: registration

    /**
     * @since 1.0
     */
    private void setupScroller() {
        setPreferredSize(new Dimension(150, 200));
        setBorder(BorderFactory.createEtchedBorder());

    } // END: setupScroller

    private class SelectorScrollerListener
            extends MouseAdapter
            implements ActionListener, ListSelectionListener {

        public void actionPerformed(ActionEvent ae) {
            Object source = ae.getSource();
            MillStatic.closeEditor();

        } // END: actionPerformed

        /**
         * Invoked when an action occurs.
         *
         * @since 1.0
         */
        public void valueChanged(ListSelectionEvent lse) {
            try {
                int index = MillStatic.documentsList.getSelectedIndex();
                if (index != -1) {
                    EditorFrame editor = (EditorFrame) MillStatic.panels.get(index);
                    editor.setSelected(true);
                    mainFrame.setSelectedEditor(editor);

                } // END: if

            } catch (Exception exception) {
            } // END: try..catch

        } // END: actionPerformed

        // handle mouse press event
        public void mousePressed(MouseEvent me) {
            if (MillStatic.documents.size() > 0 && MillStatic.documentsList.getSelectedIndex() != -1) {
                checkForTriggerEvent(me);
            }

        } // END: mousePressed

        // handle mouse release event
        public void mouseReleased(MouseEvent me) {
            if (MillStatic.documents.size() > 0 && MillStatic.documentsList.getSelectedIndex() != -1) {
                checkForTriggerEvent(me);
            }

        } // END: mouseReleased

        // determine whether event should trigger popup menu
        private void checkForTriggerEvent(MouseEvent me) {
            if (me.isPopupTrigger()) {
                popupMenu.show(me.getComponent(), me.getX(), me.getY());
            }

        } // END: checkForTriggerEvent

        public void mouseClicked(MouseEvent me) {
        } // END: mouseClicked

        private Object source;

    } // END: inner class SelectorScrollerListener

    protected MainFrame mainFrame;
    protected JPopupMenu popupMenu;
    protected JMenuItem closeMenuItem;

} // END: class SelectorScroller
