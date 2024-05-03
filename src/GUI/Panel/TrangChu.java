package GUI.Panel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Component.PanelShadow;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class TrangChu extends JPanel {

    JPanel top, center, bar1, bar2;
    PanelShadow content[];
    JPanel info[];
    JLabel title, subTit, infoDetail[], objDetail[], objDetail1[], infoIcon[];
        String[][] getSt = {
        {"Tính <br><br>tiện <br><br>lợi", "convenient_100px.svg", "<html>Có tính năng tìm kiếm nhanh chóng giúp người dùng dễ dàng<br><br> tìm sách theo tiêu chí cụ thể như tiêu đề, tác giả thể loại hoặc <br><br>theo mã ISBN sẽ đảm bảo tính chính xác và độ tin cậy cao hơn .</html>"},
        {"Tính <br><br>bảo <br><br>mật", "secure_100px.svg", "<html>Thông tin cá nhân và thông tin liên quan đến sách mượn thường <br><br>được bảo mật và chỉ được truy cập bởi người dùng hoặc những <br><br>người được ủy quyền.</html>"},
        {"Tính <br><br>hiệu <br><br>quả", "effective_100px.svg", "<html>Nhờ vào tính năng đặc biệt của mã ISBN giúp xác định được thông <br><br>tin về từng cuốn sách một cách nhanh chóng và chính xác, giúp <br><br>cho việc quản lý sách được thực hiện một cách hiệu quả hơn.</html>"},
    };
    Color MainColor = new Color(193,237,220);
    Color FontColor = new Color(96, 125, 139);
    Color BackgroundColor = new Color(240, 247, 250);
    Color HowerFontColor = new Color(225, 230, 232);

    private void initComponent() {
        this.setBackground(new Color(24, 24, 24));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        top = new JPanel();
        top.setBackground(MainColor);
        top.setPreferredSize(new Dimension(1100, 200));
        top.setLayout(new FlowLayout(1, 0, 10));

        JLabel slogan = new JLabel();
        slogan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/slogan.png")));
        
        top.add(slogan);

        this.add(top, BorderLayout.NORTH);

        center = new JPanel();
        center.setBackground(BackgroundColor);
        center.setPreferredSize(new Dimension(1100, 800));
        center.setLayout(new GridLayout(3 , 1 ,0,20));
        center.setBorder(new EmptyBorder(30,110,30,220));
        // center.setLayout(new FlowLayout(1, 50, 50));


        content = new PanelShadow[getSt.length];
        info = new JPanel[3];
        infoDetail = new JLabel[3];
        objDetail = new JLabel[3];
        objDetail1 = new JLabel[3];

        // infoIcon = new JLabel[3];

        for (int i = 0; i < getSt.length; i++) {
            //   infoDetail[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(getSt[i][1])));
            //   objDetail[i].setText(getSt[i][0]);
            //   objDetail1[i].setText(getSt[i][2]);
            //   info[i].setLayout(getLayout());
            content[i] = new PanelShadow();
            content[i] = new PanelShadow(getSt[i][1], getSt[i][0], getSt[i][2]);

            center.add(content[i]);
//            content[i].setPreferredSize(new Dimension(300, 450));
//            content[i].setBackground(MainColor);
//            content[i].setLayout(new FlowLayout(1, 0, 10));
//
//            info[i] = new JPanel();
//            info[i].setPreferredSize(new Dimension(250, 150));
//            info[i].setBackground(BackgroundColor);
//            info[i].setLayout(null);
//
//            infoIcon[i] = new JLabel();
//            infoIcon[i].setBounds(60, 20, 120, 120);
//            infoIcon[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(iconArr[i])));
//            info[i].add(infoIcon[i]);
//
//            content[i].add(info[i]);
//
//            infoDetail[i] = new JLabel(tkArr[i]);
//            infoDetail[i].setPreferredSize(new Dimension(190, 60));
//            infoDetail[i].setFont(new Font("Segoe UI", Font.BOLD, 16));
//            content[i].add(infoDetail[i]);
//
//            objDetail[i] = new JLabel(obj1Arr[i]);
//            objDetail[i].setPreferredSize(new Dimension(220, 20));
//            objDetail[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
//            content[i].add(objDetail[i]);
//
//            objDetail[i] = new JLabel(obj2Arr[i]);
//            objDetail[i].setPreferredSize(new Dimension(220, 20));
//            objDetail[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
//            content[i].add(objDetail[i]);
//
        //    center.add(content[i]);

        }

        this.add(center, BorderLayout.CENTER);

    }

    public TrangChu() {
        initComponent();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }


}
