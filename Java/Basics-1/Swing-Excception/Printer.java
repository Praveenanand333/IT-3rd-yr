import javax.swing.*;
public class Printer extends JFrame{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Printer");
        JLabel label1 = new JLabel("Printer:MyPrinter", null, 0);
        JLabel label2 = new JLabel("Print Quality:", null, 0);
        JTextArea txt1 = new JTextArea();
        JTextArea txt2 = new JTextArea();
        JTextArea txt3 = new JTextArea();

        JCheckBox chk1 = new JCheckBox("Image", null);
        JCheckBox chk2 = new JCheckBox("Text", null);
        JCheckBox chk3 = new JCheckBox("Code", null);
        JCheckBox chk4 = new JCheckBox("Print to File", null);

        JRadioButton rad1 = new JRadioButton("Selection", null);
        JRadioButton rad2 = new JRadioButton("All", null);
        JRadioButton rad3 = new JRadioButton("Applet", null);

        String options[] = {"High", "Medium", "Low"};
        JComboBox<String> cbox = new JComboBox<String>(options);

        JButton ok=new JButton("OK");
        JButton cancel=new JButton("Canel");
        JButton setup=new JButton("Setup...");
        JButton help=new JButton("Help");

        label1.setBounds(0, 10, 200, 20);
        txt1.setBounds(40,50,50,80);
        chk1.setBounds(100,50,70,20);
        chk2.setBounds(100,75,70,20);
        chk3.setBounds(100,100,70,20);
        txt2.setBounds(175,50,40,80);
        rad1.setBounds(225,50,100,30);
        rad2.setBounds(225,75,100,30);
        rad3.setBounds(225,100,100,30);
        txt3.setBounds(325,50,70,80);
        label2.setBounds(0, 130, 200, 20);
        cbox.setBounds(160,130,75,20);
        chk4.setBounds(250,130,125,20);


        ok.setBounds(450,10,100, 30);
        cancel.setBounds(450,50,100, 30);
        setup.setBounds(450,90,100, 30);
        help.setBounds(450,130,100, 30);

        frame.add(label1);
        frame.add(txt1);
        frame.add(ok);
        frame.add(cancel);
        frame.add(setup);
        frame.add(help);
        frame.add(chk1);
        frame.add(chk2);
        frame.add(chk3);
        frame.add(txt2);
        frame.add(rad1);
        frame.add(rad2);
        frame.add(rad3);
        frame.add(txt3);
        frame.add(label2);
        frame.add(cbox);
        frame.add(chk4);


        frame.setSize(600,250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}