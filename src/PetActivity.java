

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;
/**
 * 7��21��
 * ��a.gif��b.gif���ã��ֱ��ǣ���겻��������ʱ����Ϊa.gif������Ƴ�����ʱ����Ϊb.gif
 * gif�ı��ֶ����߳���ʵ�֣�ÿ��gif��Ӧһ���߳�
 * �̷߳��ʾ�̬�࣬�õ���Ϣ��������ֹͣ
 * @author ����
 *
 */
public class PetActivity {
	Boolean[] flag = {false,false};
	Boolean[] state = {false,false};
	JFrame frame = new JFrame();
	JLabel label;
	static Point origin = new Point();
	//GifThread t = new GifThread();
	GifThread ta = new GifThread(5,"a",0);
	GifThread tb = new GifThread(7,"b",1);

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new PetActivity();
	
	}
	public PetActivity() {
		// TODO Auto-generated constructor stub
		//������
		frame = new JFrame();
		frame.setUndecorated(true);
		AWTUtilities.setWindowOpaque(frame, false);
		label = new JLabel();
		frame.add(label,"Center");
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.addMouseMotionListener(new mouseMotionListener());
		frame.addMouseListener(new mouseListener());
		ta.start();
		tb.start();
		
	}
	
	class GifThread extends Thread{
		int num;
		String icon;
		int c;
		public GifThread(int num,String icon,int c) {
			// TODO Auto-generated constructor stub
			this.num = num;
			this.icon = icon;
			this.c = c;
		}
		public void run(){  //num �Ǹ�gif�ж���֡��icon��ʾ��һ��gif	
			System.out.println("it's run");
			System.out.println("icon = "+icon+"  num = "+num);
			while(true){
				if(!share.User.equals(icon)){
					System.out.println("[�߳�"+icon+"]���е���"+share.User+"�߳�");
					while(!share.User.equals(icon)){
						System.out.print("");
					}
					System.out.println("[�߳�"+icon+"]���е���"+share.User+"�߳�");
					try {
						sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
				
					GifPlay();			
			}
		}
		
		synchronized void GifPlay(){
			for(int i = 0;i<num;i++){
				ImageIcon ii = new ImageIcon("pic/"+icon+i+".jpg");
				//System.out.println("i="+i);
				label.setIcon(ii);
				try {
					sleep(60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	class mouseListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mousePressed(e);
			origin = e.getPoint();
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseEntered(e);
			/*�����룬�л�b.gif*/
			
			//ֹͣta
			System.out.println("[������]�л���tb�߳�");
			//��ʼb
			share.User = "b";
			new timerThread().run();
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseExited(e);
			//����˳����л�a.gif
			
			//ֹͣtb
			System.out.println("[������]�л���ta�߳�");
			//��ʼa
			share.User = "a";
			new timerThread().run();
		}
	}
	
	class mouseMotionListener extends MouseMotionAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			Point nowlocation = frame.getLocation();
			frame.setLocation(nowlocation.x+e.getX()-origin.x, nowlocation.y+e.getY()-origin.y);
		}

		
		
	}

}

class timerThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class share{
	public static String User = "a";
}
