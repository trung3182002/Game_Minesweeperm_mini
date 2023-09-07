package view;

import dao.QL_TaiKhoanDAO;
import gui.NguoiDung;
import gui.TrangChu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.LoadData;

public class GameFrame extends JFrame {

    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoadData loadData;

	private GamePanel gamePanel;

	private JMenuBar mnb;
	private JMenu menu;
	private JMenuItem  newGame, exit;
	public GameFrame()
        {}
	public GameFrame(int w, int h, int boom) {

		loadData = new LoadData();

		setJMenuBar(mnb = new JMenuBar());
                mnb.add(menu = new JMenu("Game"));
		menu.add(newGame = new JMenuItem("New game"));
		menu.addSeparator();
		menu.addSeparator();
		menu.add(exit = new JMenuItem("Exit"));

	



		
		newGame.addActionListener(new ActionListener() {
                    
			@Override
			public void actionPerformed(ActionEvent e) {
                            
                        int capchoi=QL_TaiKhoanDAO.layCapGame();
                            if(capchoi==1)
                            {
                                setVisible(false);
				new GameFrame(8, 8, 10);
                            }
                            else if(capchoi==2)
                            {
                                	setVisible(false);
				new GameFrame(16, 16, 40);
     
                            }
                            else
                            {
                                setVisible(false);
				new GameFrame(16, 30, 99);
                            }
			
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                            
                            if(QL_TaiKhoanDAO.layQuyen()==0)
                            {
                            QL_TaiKhoanDAO.capNhatTrangThaiOnGame(QL_TaiKhoanDAO.layCapGame(),"Off");
                            dispose();
                            TrangChu tc = new TrangChu();
                            tc.setVisible(true);                             
                            }
                            else
                            {
                                QL_TaiKhoanDAO.capNhatTrangThaiOnGame(QL_TaiKhoanDAO.layCapGame(),"Off");
                                dispose();
                                NguoiDung tc = new NguoiDung();
                                tc.setVisible(true);
                            }
                         
			}
		});

		add(gamePanel = new GamePanel(w, h, boom, this));

		setIconImage(loadData.getListImage().get("title"));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GameFrame(8, 8, 10);
	}

	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}


}
