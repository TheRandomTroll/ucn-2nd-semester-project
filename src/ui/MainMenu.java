/*
 * Created by JFormDesigner on Thu May 13 15:55:52 CEST 2021
 */

package ui;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class MainMenu extends JPanel {
    public MainMenu() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        title1 = new JLabel();
        employeeMenuB = new JButton();
        customerMenuB = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
        setLayout(null);

        //---- title1 ----
        title1.setText("Main Menu");
        add(title1);
        title1.setBounds(new Rectangle(new Point(160, 10), title1.getPreferredSize()));

        //---- employeeMenuB ----
        employeeMenuB.setText("Employee Menu");
        add(employeeMenuB);
        employeeMenuB.setBounds(new Rectangle(new Point(270, 75), employeeMenuB.getPreferredSize()));

        //---- customerMenuB ----
        customerMenuB.setText("Customer Menu");
        add(customerMenuB);
        customerMenuB.setBounds(new Rectangle(new Point(270, 120), customerMenuB.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel title1;
    private JButton employeeMenuB;
    private JButton customerMenuB;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
