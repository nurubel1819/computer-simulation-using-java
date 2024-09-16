package inventory_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class inventory extends JFrame {

    //method Random number generator
    public static int random_demand() {
        // Creating an instance of Random class
        Random rand = new Random();

        // Generating a random integer between 1 and 10 (inclusive)
        int number_from=1,number_to=3;
        int randomNumber = rand.nextInt(number_to) + number_from;
        return randomNumber;
    }

    public static int random_arrival_time() {
        // Creating an instance of Random class
        Random rand = new Random();

        // Generating a random integer between 1 and 10 (inclusive)
        int number_from=1,number_to=2;
        int randomNumber = rand.nextInt(number_to) + number_from;

        return randomNumber;
    }

    // all value
    public static boolean flag = false;
    private static int capacity =10,order_quantity=0;
    private static int cycle = 1,day=0,beginning_inventory = 5,demand=0,ending_inventory=0;
    private static int shortage = 0,order = 0,order_arrival_time=0;

    public static void main(String[] args) {
        inventory ob = new inventory();

        Font font = new Font("Arial black",Font.BOLD,15);
        Font btn_font = new Font("Arial black",Font.BOLD,40);

        //create table for data view
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(font);
        table.setBackground(Color.orange);
        String[] columnNames = {"Cycle/week","Day","Beginning inventory","Demand","End inventory","Shortage","Order","Order waiting time"};
        model.setColumnIdentifiers(columnNames);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel,BoxLayout.Y_AXIS));

        JButton button_next = new JButton();
        button_next.setText("Next");
        button_next.setBackground(Color.BLACK);
        button_next.setForeground(Color.white);
        button_next.setFont(btn_font);
        main_panel.add(button_next);

        model.addRow(new Object[]{Integer.toString(cycle),Integer.toString(day),Integer.toString(beginning_inventory),
                Integer.toString(demand),Integer.toString(ending_inventory),Integer.toString(shortage),
                Integer.toString(order),Integer.toString(order_arrival_time)});

        button_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                day++;
                if(order_arrival_time<0)
                {
                    order_arrival_time =0;
                    flag = false;
                    shortage = shortage-order_quantity;
                    if(shortage<0)
                    {
                        beginning_inventory = shortage*(-1);
                        shortage=0;
                    }
                }
                if(day%5==0) cycle++;
                demand = inventory.random_demand();
                ending_inventory = beginning_inventory-demand;
                if(ending_inventory<0)
                {
                    shortage += ending_inventory*(-1);
                    //shortage += 0-ending_inventory;
                    ending_inventory=0;
                }
                if(ending_inventory>0) shortage=0;
                if(day%5==0)
                {
                    order = shortage+(capacity-ending_inventory);
                    flag = true;
                    order_arrival_time = inventory.random_arrival_time();
                    order_quantity=order;
                }
                //table update
                model.addRow(new Object[]{Integer.toString(cycle),Integer.toString(day),Integer.toString(beginning_inventory),
                        Integer.toString(demand),Integer.toString(ending_inventory),Integer.toString(shortage),
                        Integer.toString(order),Integer.toString(order_arrival_time)});
                order = 0;
                if(flag) order_arrival_time--;
                beginning_inventory=ending_inventory;
            }
        });
        

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        main_panel.setBackground(Color.red);

        main_panel.add(button_next);
        main_panel.add(panel);

        ob.getContentPane().add(main_panel);
        ob.getContentPane();
        ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ob.setSize(1000,600);
        ob.setVisible(true);
    }
}
