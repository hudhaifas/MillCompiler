/*
 * @(#)MenuCreator.java
 * No copyright reserved !© 2006
 * Created on 14 February 2006, 22:41
 */

package creator;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @version 2.0
 * @author Hudhaifa Shatnawi
 */
public class MenuCreator {

    /**
     * Creates a new instance of MenuCreator
     * @author Mazen Hajri
     * @since 2.0
     */
    public MenuCreator() {
    } // END: MenuCreator constructor

    public static JMenu creatMenu( String label, char letter ) {
        JMenu menu = new JMenu( label );
        menu.setMnemonic( letter );

        return menu;

    } // END: creatMenu

    public static JMenuItem creatItem( String label, int keyEvent, int inputEvent, char letter ) {
        JMenuItem item = new JMenuItem( label );
        item.setAccelerator( KeyStroke.getKeyStroke( keyEvent, inputEvent ) );
        item.setMnemonic( letter );

        return item;

    } // END: createItem

    public static JMenuItem creatItem( String label, char letter ) {
        JMenuItem item = new JMenuItem( label );
        item.setMnemonic( letter );

        return item;

    } // END: createItem

} // END: class MenuCreator