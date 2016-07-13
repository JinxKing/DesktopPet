

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
 * 7月21日
 * 将a.gif和b.gif运用，分别是，鼠标不进入助手时表现为a.gif，鼠标移出助手时表现为b.gif
 * gif的表现都在线程中实现，每个gif对应一个线程
 * 线程访问静态类，得到信息，播放与停止
 * @author 金哲
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
		//窗体框架
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
		public void run(){  //num 是该gif有多少帧，icon表示哪一张gif	
			System.out.println("it's run");
			System.out.println("icon = "+icon+"  num = "+num);
			while(true){
				if(!share.User.equals(icon)){
					System.out.println("[线程"+icon+"]运行的是"+share.User+"线程");
					while(!share.User.equals(icon)){
						System.out.print("");
					}
					System.out.println("[线程"+icon+"]运行的是"+share.User+"线程");
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
			/*鼠标进入，切换b.gif*/
			
			//停止ta
			System.out.println("[鼠标进入]切换成tb线程");
			//开始b
			share.User = "b";
			new timerThread().run();
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseExited(e);
			//鼠标退出，切换a.gif
			
			//停止tb
			System.out.println("[鼠标进入]切换成ta线程");
			//开始a
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
