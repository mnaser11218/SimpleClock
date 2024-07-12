//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements Runnable {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;
        boolean twelveHourFormat = false;
        boolean localTimeFormat = true;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(450, 350);
            this.setResizable(false);


            timeFormat = new SimpleDateFormat("hh:mm:ss a"); // replace hh with HH to make it 24hour format
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            // button
            JButton button = new JButton("switch to 12/24 hr format");

            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(twelveHourFormat == false){
                        timeFormat = new SimpleDateFormat("HH:mm:ss a");
                        twelveHourFormat = true;
                    }else{
                        timeFormat = new SimpleDateFormat("hh:mm:ss a");
                        twelveHourFormat = false;
                    }
                    //Do what you like here after button is clicked, for example:
                    System.out.println("Button clicked");
                }
            });

            JButton button2 = new JButton("switch to gmt/local time");

            button2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(localTimeFormat == true){
                   //     timeFormat = new SimpleDateFormat("HH:mm:ss a");
                        DateFormat gmtFormat = new SimpleDateFormat("hh:mm:ss a");
                        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
                        gmtFormat.setTimeZone(gmtTime);
                        timeFormat = (SimpleDateFormat) gmtFormat;
                        localTimeFormat = false;
                    }else{
                        timeFormat = new SimpleDateFormat("hh:mm:ss a");
                        localTimeFormat = true;
                    }
                    //Do what you like here after button is clicked, for example:
                    System.out.println("Button clicked");
                }
            });

            this.add(button);
            this.add(button2);
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.setVisible(true);
    
            setTimer();

           // ButtonExample frame = new ButtonExample();
            //Creating the button with the label "Click me!"

        }
    
        public void setTimer() {
         run();
        }
        public static void main(String[] args) {
            new SimpleClock();
        }

    @Override
    public void run() {
        while (true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
