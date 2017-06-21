import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Tutorial extends JPanel
{
     JTable jt;
 
     // Constructors are usually used to initialize components in classes. 
     // That's it. This example is a bad practice. Use functions instead for 
     // production code
     public Tutorial()
     {
          // Columns for table
          String[] columns = {"Name", "Age", "Gender"};
          
          // 2D array is used for data in table
          String[][] data = {{"John", "18", "Male"},
                  {"Daisy", "19", "Female"},
                  {"Dave", "23", "Male"},
                  {"Jake", "30", "Male"}};
  
          // Creates Table
          jt = new JTable(data, columns)
          {
               // Determines if data can be entered by users
               public boolean isCellEditable(int data, int columns)
               {
                   return false;
               }
   
               //  Creates cells for the table         
               public Component prepareRenderer(
                            TableCellRenderer r, int data, int columns)
               {
                   Component c = super.prepareRenderer(r, data, columns);
                  
                   // Every even numbers
                   if (data % 2 == 0)
                       c.setBackground(Color.WHITE);
    
                   else
                       c.setBackground(Color.LIGHT_GRAY);
    
                   return c;
               }
         };
  
         // Set size of table     
         jt.setPreferredScrollableViewportSize(new Dimension(450, 63));

         // This will resize the height of the table automatically 
         // to all data without scrolling. 
         jt.setFillsViewportHeight(true);

         JScrollPane jps = new JScrollPane(jt);
         add(jps);
     }
 
     // Creates Window
     public static void main(String[] args)
     {
         JFrame jf = new JFrame("Tutorial");
         Tutorial t = new Tutorial();
         jf.setSize(500, 500);
         jf.setVisible(true);
         jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         jf.add(t);
     }
}