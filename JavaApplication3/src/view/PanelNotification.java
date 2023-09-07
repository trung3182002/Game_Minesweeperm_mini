package view;

import control.World;
import dao.QL_TaiKhoanDAO;
import gui.TrangChu;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelNotification extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel p11, p12, p13;

	private LableNumber lbTime, lbBoom;

	private GamePanel game;
        private  GameFrame gameFrame;

	private ButtonSmile bt;
	private World world;
	private Timer time;
        int t=QL_TaiKhoanDAO.layTimeGame();
	private int nowTime=t;

        
	public PanelNotification(GamePanel game) {
		this.game = game;

		lbTime = game.getWorld().getLbTime();
		lbBoom = game.getWorld().getLbBoom();

		bt = game.getWorld().getButtonSmile();
		setLayout(new BorderLayout());

		setBorder(BorderFactory.createLoweredBevelBorder());

		add(p11 = new JPanel(), BorderLayout.WEST);
		add(p12 = new JPanel(), BorderLayout.EAST);
		add(p13 = new JPanel(), BorderLayout.CENTER);

		p11.add(lbBoom = new LableNumber(this, "000"));
		updateLbBoom();

		p12.add(lbTime = new LableNumber(this, "000"));

		time = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nowTime--;
				updateLbTime();
                                if (nowTime == 0) 
                                {
                                    ((Timer) e.getSource()).stop();
                                   
                                   
                                       game.thoat();
                                
                               
                                     
                int option = JOptionPane.showConfirmDialog(gameFrame, "Bạn đã hết thời gian chơi !", "Thất Bại",
									JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
                int capchoi=QL_TaiKhoanDAO.layCapGame();
                            if(capchoi==1)
                            {
                                
				new GameFrame(8, 8, 10);
                                setVisible(true);
                            }
                            else if(capchoi==2)
                            {
                                	
				new GameFrame(16, 16, 40);
                                 setVisible(true);
     
                            }
                            else
                            {
                              
				new GameFrame(16, 30, 99);
                                 setVisible(true);
                            }
			} else {
                       QL_TaiKhoanDAO.capNhatTrangThai(QL_TaiKhoanDAO.layTenTK(),"Off");
                      QL_TaiKhoanDAO.capNhatTrangThaiOnGame(QL_TaiKhoanDAO.layCapGame(),"Off");
			world.fullTrue();
			}
                                         
                                }
			}
		});

		p13.add(bt = new ButtonSmile(this));

		bt.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				bt.setStage(ButtonSmile.now);
				bt.repaint();

				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi ván mới không ?", "Thông Báo",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					getGame().getGameFrame().setVisible(false);
					new GameFrame(game.getW(), game.getH(), game.getBoom());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (getGame().getWorld().isEnd() || getGame().getWorld().isComplete()) {
					getGame().getGameFrame().setVisible(false);
					new GameFrame(game.getW(), game.getH(), game.getBoom());
				} else {
					bt.setStage(ButtonSmile.press);
					bt.repaint();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	public void updateLbTime() {
		if (nowTime>999) {
			lbTime.setNumber("voCuc");
		} else {
			String cTime = String.valueOf(nowTime);
			if (cTime.length() == 1) {
				lbTime.setNumber("00" + cTime);
			} else if (cTime.length() == 2) {
				lbTime.setNumber("0" + cTime);
			} else {
				lbTime.setNumber(cTime);
			}
			lbTime.repaint();
		}
	}

	public void updateLbBoom() {
		String boom = String.valueOf(game.getBoom() - game.getWorld().getCo());
		if (boom.length() == 1) {
			lbBoom.setNumber("00" + boom);
		} else if (boom.length() == 2) {
			lbBoom.setNumber("0" + boom);
		} else {
			lbBoom.setNumber("0" + boom);
		}
		lbBoom.repaint();
	}

	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}

	public Timer getTime() {
		return time;
	}

	public void setTime(Timer time) {
		this.time = time;
	}

	public ButtonSmile getBt() {
		return bt;
	}

	public void setBt(ButtonSmile bt) {
		this.bt = bt;
	}

}
