package com.ye.snack;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Timer;

/*
 * ̰���߲���
 */
public class Snack {

    public static int delay = 150;
    static AudioClip sound = null;

    JFrame jf = new JFrame("̰����");
    /*
     * �����˵�
     */
    //�˵���
    JMenuBar menu = new JMenuBar();
    //����һ���˵�
    JMenu dif = new JMenu("�Ѷ�");
    JMenu select = new JMenu("ѡ��");

    //����dif�µĸ�ѡ��
    //JCheckBoxMenuItem checkBoxMenuItem = new
    JRadioButtonMenuItem jrbmItem1 = new JRadioButtonMenuItem("��");
    JRadioButtonMenuItem jrbmItem2 = new JRadioButtonMenuItem("��");
    JRadioButtonMenuItem jrbmItem3 = new JRadioButtonMenuItem("��");
    JMenuItem exitMenuItem = new JMenuItem("�˳�");

    // �������� ��ѡ��ť�Ӳ˵���Ҫʵ�ֵ�ѡ��ť��Ч������Ҫ�����Ƿŵ�һ����ť����
    ButtonGroup btnGroup = new ButtonGroup();

    //ѡ���µ��Ӳ˵�
    JMenuItem about = new JMenuItem("����");
    JMenuItem music = new JMenuItem("����");
    //��ӻ���
    SnackPanel snackPanel = new SnackPanel();

    public Snack() {
        jf.setBounds(50, 20, 1005, 988);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponent();
        jf.setVisible(true);
    }

    public void addComponent() {
        //һ���˵���ӵ��˵�����
        menu.add(dif);
        menu.add(select);
        //��ѡ����ӵ�dif��
        dif.add(jrbmItem1);
        dif.add(jrbmItem2);
        dif.add(jrbmItem3);
        dif.addSeparator();//���һ���ָ���
        dif.add(exitMenuItem);
        btnGroup.add(jrbmItem1);
        btnGroup.add(jrbmItem2);
        btnGroup.add(jrbmItem3);

        // Ĭ�ϵ�һ����ѡ��ť�Ӳ˵�ѡ��
        jrbmItem1.setSelected(true);

        select.add(about);
        select.add(music);

        jf.setJMenuBar(menu);

        jf.add(snackPanel);

        //���ü�����
        exitMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("�˳���Ϸ��");
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });

        //�����Ѷ�
        jrbmItem1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("�Ѷȣ���");
//				GlobalDataMgr.getInstance().setDelay(180);
            }
        });
        jrbmItem2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("�Ѷȣ���");
//				GlobalDataMgr.getInstance().setDelay(120);
            }
        });
        jrbmItem3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("�Ѷȣ���");
//				GlobalDataMgr.getInstance().setDelay(60);
            }
        });
        music.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //��������
                try {
                    URL soundFile = new URL("file:music/����ҹSOLO.wav");
                    sound = Applet.newAudioClip(soundFile);
                    sound.loop();

                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }


}
