package counter_simulation;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

public class counter_simulation {
    private final String counter_with_customer_icon_path = "C:\\Users\\nurub\\OneDrive\\Documents\\programming code\\intellij Idea for code\\Computer Simulation\\src\\counter_simulation\\counter_with_customer.png";
    private final String customer_icon_path = "C:\\Users\\nurub\\OneDrive\\Documents\\programming code\\intellij Idea for code\\Computer Simulation\\src\\counter_simulation\\customer.png";
    private final String counter_image_path = "C:\\Users\\nurub\\OneDrive\\Documents\\programming code\\intellij Idea for code\\Computer Simulation\\src\\counter_simulation\\counter.png";
    public counter_simulation()
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        Font font = new Font("Arial black",Font.BOLD,24);
        Font big_font = new Font("Arial black",Font.BOLD,40);

        JPanel p_heading = new JPanel(new FlowLayout());
        JLabel heading = new JLabel("Bank counter simulation");
        heading.setForeground(Color.blue);
        heading.setFont(big_font);
        p_heading.add(heading);
        panel.add(p_heading);


        JPanel p_palel_2 = new JPanel(new FlowLayout());
        JLabel l_customer_number = new JLabel("Customer number = ");
        JLabel l_service = new JLabel("Customer service");
        l_customer_number.setFont(font);
        l_service.setFont(font);
        p_palel_2.add(l_customer_number);
        p_palel_2.add(l_service);
        panel.add(p_palel_2);

        JPanel panel_counter_customer = new JPanel(new FlowLayout());
        JPanel palel_customer_line = new JPanel(new FlowLayout());
        panel_counter_customer.add(palel_customer_line);
        JLabel l_space = new JLabel("------------>");
        l_space.setForeground(Color.ORANGE);
        JLabel l_counter = new JLabel("Counter ");
        l_space.setFont(font);
        l_counter.setFont(font);
        panel_counter_customer.add(l_space);
        panel_counter_customer.add(l_counter);
        panel.add(panel_counter_customer);

        JPanel p_panel_4 = new JPanel(new FlowLayout());
        JLabel l_total_ideal = new JLabel("Total ideal time = ");
        JLabel l_total_busy = new JLabel("Total busy time = ");
        l_total_ideal.setFont(font);
        l_total_busy.setFont(font);
        p_panel_4.add(l_total_ideal);
        p_panel_4.add(l_total_busy);
        panel.add(p_panel_4);
        ImageIcon counter_icon = new ImageIcon(counter_image_path);
        l_counter.setIcon(counter_icon);

        frame.add(panel);
        frame.setSize(1000,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //simulation code
        int customer_number = 10;//number of customer---------------------------------------------------------------------------- customer number
        Random random = new Random();
        Vector<Integer> customer_inter_arrival_time = new Vector<>();

        customer_inter_arrival_time.add(0);// set Inter arrival time
        for(int i=1;i<customer_number;i++)
        {
            int value = random.nextInt()%4;// Entre  arrival time---------------------------------------------------------------- arrival
            if(value<0) customer_inter_arrival_time.add(value*(-1));
            else if(value==0) customer_inter_arrival_time.add(1);
            else customer_inter_arrival_time.add(value);
        }
        Vector<Integer> arrival_time = new Vector<>();
        arrival_time.add(0);// set all customer arrival time
        for(int i=1;i<customer_number;i++)
        {
            arrival_time.add(customer_inter_arrival_time.elementAt(i)+arrival_time.elementAt(i-1));
        }

        Vector<Integer> service_time = new Vector<>();
        for(int i=0;i<customer_number;i++) // set all customer service time-------------------------------------------------------- seivice
        {
            int value = random.nextInt()%8;
            if(value<0) service_time.add(value*(-1));
            else if(value==0) service_time.add(1);
            else  service_time.add(value);
        }
        for(int i=0;i<arrival_time.size();i++) System.out.println(arrival_time.elementAt(i)+ "  "+service_time.elementAt(i));
        int last_iteration = arrival_time.elementAt(arrival_time.size()-1)+service_time.elementAt(service_time.size()-1);
        // code for simulation--------------------------------- simulation code --------------------------------------------------------

        ImageIcon icon_counter_with_customer = new ImageIcon(counter_with_customer_icon_path);
        ImageIcon icon = new ImageIcon(customer_icon_path); //---------------------- add customer image icon------------------------------------
        int customer_id=1;
        int total_busy_time=0;
        int st=0;
        st = service_time.elementAt(0);
        if(st!=0) l_counter.setForeground(Color.red);
        Queue<Integer> customer_line = new LinkedList<>();
        for(int time=0,i=1;  ;time++)
        {
            if(st>0)
            {
                l_counter.setText("Busy");
                l_counter.setIcon(icon_counter_with_customer);
                l_counter.setForeground(Color.red);
            }
            if(st==0)
            {
                if(customer_line.size()!=0)
                {
                    customer_id++;
                    st=service_time.elementAt(customer_line.peek());
                    l_counter.setText("Busy");
                    l_counter.setIcon(icon_counter_with_customer);
                    l_counter.setForeground(Color.red);
                    customer_line.remove();
                    palel_customer_line.removeAll();
                    for(int j=1;j<=customer_line.size();j++) palel_customer_line.add(new JLabel(icon));
                }
                else
                {
                    l_counter.setText("Free");
                    l_counter.setIcon(counter_icon);
                    l_counter.setForeground(Color.BLUE);
                }
            }
            if(time==arrival_time.elementAt(i))
            {
                customer_line.add(i);
                palel_customer_line.removeAll();
                for(int j=1;j<=customer_line.size();j++) palel_customer_line.add(new JLabel(icon));
                if(i+1<arrival_time.size()) i++;
                if(st==0)
                {
                    if(customer_line.size()!=0)
                    {
                        customer_id++;
                        st=service_time.elementAt(customer_line.peek());
                        l_counter.setText("Busy");
                        l_counter.setIcon(icon_counter_with_customer);
                        l_counter.setForeground(Color.red);
                        customer_line.remove();
                        palel_customer_line.removeAll();
                        for(int j=1;j<=customer_line.size();j++) palel_customer_line.add(new JLabel(icon));
                    }
                    else
                    {
                        l_counter.setText("Free");
                        l_counter.setIcon(counter_icon);
                        l_counter.setForeground(Color.BLUE);
                    }
                }
            }
            if(customer_line.size()==0)
            {
                palel_customer_line.removeAll();
            }
            if(st>0)
            {
                total_busy_time++;
                st--;
            }
            //edit all label
            l_customer_number.setText("Customer number = "+customer_id);
            l_service.setText("      Customer service time running = "+st);
            l_total_ideal.setText("Total idle time = "+(time-total_busy_time+1));
            l_total_busy.setText("       Total busy time = "+(total_busy_time-1));
            //sleep
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void main(String[] args) {
        counter_simulation ob = new counter_simulation();
    }
}
