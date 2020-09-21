package com.ye.snack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.omg.Messaging.SyncScopeHelper;

/**
 * ��壺������Ϸ������ҵ��
 *
 * @author Mr.Ye
 */
public class SnackPanel extends JPanel implements KeyListener, ActionListener {
    //��������ͼƬ
    ImageIcon title = new ImageIcon("img/title.png");
    ImageIcon head = new ImageIcon("img/head.png");
    ImageIcon body = new ImageIcon("img/body.png");
    ImageIcon[] food = {
            new ImageIcon("img/food.png"),
            new ImageIcon("img/food1.png"),
            new ImageIcon("img/food2.png"),
            new ImageIcon("img/food3.jpg"),
            new ImageIcon("img/food4.jpg"),
    };


    //�����ߵ����ݽṹ
    int[] snackX = new int[1005];
    int[] snackY = new int[988];
    int len = 3;
    String direction = "R";//��ʾ�ҷ���

    //����ʳ���λ��
    Random rand = new Random();
    int foodX = rand.nextInt(24) * 43 + 8;
    int foodY = rand.nextInt(20) * 41 + 158;

    int kind = rand.nextInt(5);


    boolean isStart = false;//�ж���Ϸ�Ƿ�ʼ

    boolean isFaild = false;//�ж���Ϸ�Ƿ�ʧ��

    boolean isEat = false;
    //���÷���
    int score;

    //���ö�ʱ��
//	int delay = GlobalDataMgr.getInstance().getDelay() ;
    Timer timer = new Timer(150, this);


    //���ù�����
    public SnackPanel() {
        //�Ȼ�ȡ�������㣬�Ի�������
        this.setFocusable(true);
        init();//��ʼ��
        this.addKeyListener(this);//���ü��̼����ӿ�
        timer.start();

    }

    //��ʼ����̬��
    public void init() {
//System.out.println("delay:"+delay);
        isStart = false;
        isFaild = false;
        len = 3;
        direction = "R";
        //��ͷ
        snackX[0] = 137;
        snackY[0] = 199;
        //����
        snackX[1] = 94;
        snackY[1] = 199;
        snackX[2] = 51;
        snackY[2] = 199;

        //��ʼ������
        score = 0;
    }

    //����һ����������
    public void paint(Graphics g) {
        //���屳����ɫ
        this.setBackground(Color.black);//�����this������
        g.fillRect(8, 158, 989, 820);

        //���ñ���
        title.paintIcon(this, g, 0, 0);

        //����ͷ
        head.paintIcon(this, g, snackX[0], snackY[0]);
        //������
        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snackX[i], snackY[i]);
        }

        //������¿ո������һ����ʾ���
        if (isStart == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("aruak", Font.BOLD, 30));
            g.drawString("����ո����ʼ/��ͣ", 450, 450);
        }
        //��ʳ��(ʳ����ʽ���)
//System.out.println("ʳ�����꣺"+foodX+","+foodY);
        food[kind].paintIcon(this, g, foodX, foodY);


        //ʧ����ʾ�ﵯ��
        if (isFaild == true) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("aruak", Font.BOLD, 30));
            g.drawString("��Ϸ�������ո�����¿�ʼ", 450, 450);

        }

        //������¼
        if (isStart == true) {
            g.setColor(Color.green);
            g.setFont(new Font("aruak", Font.BOLD, 20));
            g.drawString("������" + score, 20, 90);
        }


        //�ж���Ϸʧ��
        for (int i = 1; i < len; i++) {
            if (snackX[0] == snackX[i] && snackY[0] == snackY[i]) {
                head.paintIcon(this, g, snackX[i], snackY[i]);
                isFaild = true;
            }
        }
        if (isEat == true) {
            food[kind].paintIcon(this, g, snackX[len - 1], snackY[len - 1]);

        }
        if (isEat == false) {
//			System.out.println("û�г���ʳ��");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //���̰��µ��¼�
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {

            if (isFaild == true) {
                init();
            } else {
                isStart = !isStart;//����Ϊtrue���ٰ�Ϊfalse
            }

            //repaint();
        } else if (keyCode == KeyEvent.VK_UP && !direction.equals("D")) {
            direction = "U";
        } else if (keyCode == KeyEvent.VK_DOWN && !direction.equals("U")) {
            direction = "D";
        } else if (keyCode == KeyEvent.VK_LEFT && !direction.equals("R")) {
            direction = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")) {
            direction = "R";
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //�������ö�ʱ��
        timer.start();
        //�����ƶ�
        if (isStart == true && !isFaild) {
            //�����ƶ�
            for (int i = len; i > 0; i--) {
                snackX[i] = snackX[i - 1];
                snackY[i] = snackY[i - 1];
            }
            //ͷ���ƶ�
            if (direction.equals("R")) {
                snackX[0] = snackX[0] + 43;
                snackY[0] = snackY[0];
                //���ͷ�����˱߽磬�����һ�߳���
//System.out.println("����ͷ��"+snackX[0]);
                if (snackX[0] >= 997) {
                    snackX[0] = 8;
                }
            } else if (direction.equals("U")) {
                snackX[0] = snackX[0];
                snackY[0] = snackY[0] - 41;
                //���ͷ�����˱߽磬�����һ�߳���
                if (snackY[0] < 158) {
                    snackY[0] = 896;
                }
            } else if (direction.equals("L")) {
                snackX[0] = snackX[0] - 43;
                snackY[0] = snackY[0];
                //���ͷ�����˱߽磬�����һ�߳���
                if (snackX[0] < 8) {
                    snackX[0] = 954;
                }
            } else if (direction.equals("D")) {
                snackX[0] = snackX[0];
                snackY[0] = snackY[0] + 41;
                //���ͷ�����˱߽磬�����һ�߳���
                if (snackY[0] > 896) {
                    snackY[0] = 158;
                }
            }
            //ʵ�ֳ�ʳ��
            if (snackX[0] == foodX && snackY[0] == foodY) {
                len++;
                score++;
                isEat = true;
                //���ʳ��
                kind = rand.nextInt(5);
                //����ʳ��Խ��
                while (true) {
                    foodX = rand.nextInt(24) * 43 + 8;
                    foodY = rand.nextInt(20) * 41 + 158;
                    if (foodX < 997 && foodY <= 868) {
                        break;
                    }
                }

            }


        }
        //�ػ�
        repaint();

    }
}
