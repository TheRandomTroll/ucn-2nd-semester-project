/*
 * Created by JFormDesigner on Thu May 13 15:57:18 CEST 2021
 */

package ui;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class EmployeeMenu extends JPanel {
    public EmployeeMenu() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
        . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax. swing
        .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
        Font ( "Dialo\u0067", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
        ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
        public void propertyChange (java . beans. PropertyChangeEvent e) { if( "borde\u0072" .equals ( e. getPropertyName (
        ) ) )throw new RuntimeException( ) ;} } );
        setLayout(null);

        //---- label1 ----
        label1.setText("Employee Menu");
        add(label1);
        label1.setBounds(new Rectangle(new Point(160, 10), label1.getPreferredSize()));

        //---- button1 ----
        button1.setText("Create Order");
        add(button1);
        button1.setBounds(new Rectangle(new Point(290, 50), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("Find Customer");
        add(button2);
        button2.setBounds(290, 85, 101, 30);

        //---- button3 ----
        button3.setText("Find Product");
        add(button3);
        button3.setBounds(290, 120, 101, 30);

        //---- button4 ----
        button4.setText("Add Order Line");
        add(button4);
        button4.setBounds(290, 155, 101, 30);

        //---- button5 ----
        button5.setText("Find Voucher");
        add(button5);
        button5.setBounds(290, 190, 101, 30);

        //---- button6 ----
        button6.setText("Add Voucher");
        add(button6);
        button6.setBounds(290, 225, 101, 30);

        //---- button7 ----
        button7.setText("Create Invoice");
        add(button7);
        button7.setBounds(290, 260, 101, 30);

        //---- button8 ----
        button8.setText("Dispatch Order");
        add(button8);
        button8.setBounds(290, 295, 101, 30);

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
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
