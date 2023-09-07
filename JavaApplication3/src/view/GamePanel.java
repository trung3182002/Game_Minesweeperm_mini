package view;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.World;
import dao.QL_TaiKhoanDAO;
import gui.TrangChu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.concurrent.CountDownLatch;

import javax.swing.Timer;

public class GamePanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelNotification p1;
	private PanelPlayer p2;

	private GameFrame gameFrame;

	private World world;

	private int w;
	private int h;
	private int boom;
        
        private Timer time;
	private int nowTime=QL_TaiKhoanDAO.layTimeGame();


        public  void thoat()
        {
            gameFrame.dispose();
        }
	public GamePanel(int w, int h, int boom, GameFrame gameFrame) {

		this.gameFrame = gameFrame;

		this.boom = boom;
		this.w = w;
		this.h = h;

		world = new World(w, h, boom, this);

		setLayout(new BorderLayout(20, 20));

		add(p1 = new PanelNotification(this), BorderLayout.NORTH);
		add(p2 = new PanelPlayer(this), BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
            	time = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nowTime--;
			}
		});
               
	}
	@Override
	public void mousePressed(MouseEvent e) {
		getP1().getBt().setStage(ButtonSmile.wow);
		getP1().getBt().repaint();
		ButtonPlay[][] arrayButton = p2.getArrayButton();
		for (int i = 0; i < arrayButton.length; i++) {
			for (int j = 0; j < arrayButton[i].length; j++) {

				if (e.getButton() == 1 && e.getSource() == arrayButton[i][j] && !world.getArrayCamCo()[i][j]) {

					if (!getP1().getTime().isRunning()) {  
                                            time.start();
                                            getP1().getTime().start();
					}
					if (!world.open(i, j)  ) {

						if (world.isComplete() ) {

                                                    int tg = 0;
                                                    int diem =0;
                                                        time.stop();
							getP1().getTime().stop();
                                                        tg = QL_TaiKhoanDAO.layTimeGame() - nowTime;
                                                        diem = tg + 130;
                                                          
                                                        String dm = String.valueOf(diem);
                                                       String ck = String.valueOf(tg);
                                                      
							getP1().getBt().setStage(ButtonSmile.lose);
							getP1().getBt().repaint();

							int option = JOptionPane.showConfirmDialog(this, "Thất Bại,\n Điểm : -'"+dm+"' \n Thời Gian :'"+ck+"' (s) \n play again?", "Notification",
									JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w, h, boom);
							} else {
								world.fullTrue();
							}
						} else if (world.isEnd()) {
                                                    
                                                 int tg = 0;
                                                    int diem =0;
                                                    
							time.stop();
							getP1().getTime().stop();
                                                          tg = QL_TaiKhoanDAO.layTimeGame() - nowTime;
                                                          diem = tg +130;
							getP1().getBt().setStage(ButtonSmile.win);

							getP1().getBt().repaint();
                                                        
                                                      

                                                       String dm = String.valueOf(diem);
                                                       String ck = String.valueOf(tg);
                                                       
							int option = JOptionPane.showConfirmDialog(this, "Thắng cuộc, \n Điểm :'"+dm+"' \n Thời Gian :'"+ck+"'(s) \n Bạn có muốn lưu lại kết quả ?", "Notification",
									JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
							QL_TaiKhoanDAO tkAO=new QL_TaiKhoanDAO();
                                                        tkAO.ghiKetQua(diem, tg);
							}
                                                        else
                                                        {
                                                            	int option1 = JOptionPane.showConfirmDialog(this, "Bạn có muốn chơi lại không", "Thông Báo",
								JOptionPane.YES_NO_OPTION);

                                                            if (option1 == JOptionPane.YES_OPTION) {
                                                                    gameFrame.setVisible(false);
                                                                    new GameFrame(w, h, boom);
                                                            } else {
                                                        
                                                                    world.fullTrue();
                                                            }
                                                        }
						}
					}
				} else if (e.getButton() == 3 && e.getSource() == arrayButton[i][j]) {
					world.camCo(i, j);
				}
				if (e.getClickCount() == 2 && e.getSource() == arrayButton[i][j] && world.getArrayBoolean()[i][j]) {
					if (!world.clickDouble(i, j)) {

						int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn chơi lại không ?", "Thông Báo",
								JOptionPane.YES_NO_OPTION);

						if (option == JOptionPane.YES_OPTION) {
							gameFrame.setVisible(false);
							new GameFrame(w, h, boom);
						} else {
							world.fullTrue();
						}
					}
				}
			}
		}
        }
	@Override
	public void mouseReleased(MouseEvent e) {
		getP1().getBt().setStage(ButtonSmile.now);
		getP1().getBt().repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public int getBoom() {
		return boom;
	}

	public void setBoom(int boom) {
		this.boom = boom;
	}

	public PanelNotification getP1() {
		return p1;
	}

	public void setP1(PanelNotification p1) {
		this.p1 = p1;
	}

	public PanelPlayer getP2() {
		return p2;
	}

	public void setP2(PanelPlayer p2) {
		this.p2 = p2;
	}
}
