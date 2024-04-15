import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoard extends JFrame implements ActionListener
{
	//private PiecePanel piecePanel;
	private JPanel boardPanel;
	private JButton[][] boardSquares;
	private Controleur ctrl;

	private boolean clique;
	private int 	ligD,ligF;
	private char	colD,colF;


	public ChessBoard(Controleur ctrl)
	{
		
		this.ctrl=  ctrl;
		this.clique=false;

		this.ligD=0;
		this.ligF=0;
		this.colD='Z';
		this.colF='Z';

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 800); // Set the size of the frame

		// Create the PiecePanel
		//piecePanel = new PiecePanel();
		//frame.add(piecePanel, BorderLayout.CENTER);

		boardPanel = new JPanel(new GridLayout(8, 8));
		this.add(boardPanel, BorderLayout.CENTER);

		Color colorBlack = new Color(240,195,128);
		Color colorWhite = new Color(109,62,23);
		this.boardSquares = new JButton[8][8];

		

		String[] pieces = {"To", "Ca", "Fo", "Re", "Ro", "Fo", "Ca", "To"}; // Letters
																		// representing
																		// the
																		// pieces

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				String sImage = "./images/";
				boardSquares[i][j] = new JButton();
				boardPanel.add(boardSquares[i][j]);

				if ((i + j) % 2 == 0)
				{
					boardSquares[i][j].setBackground(colorWhite);
				}
				else
				{
					boardSquares[i][j].setBackground(colorBlack);
				}
				
				switch (i) {
					case 0 -> {boardSquares[i][j].setIcon(new ImageIcon(sImage + pieces[j] + "N.png"));}
					case 1 -> {boardSquares[i][j].setIcon(new ImageIcon(sImage + "PiN.png"));}
					case 6 -> {boardSquares[i][j].setIcon(new ImageIcon(sImage + "PiB.png"));}
					case 7 -> {boardSquares[i][j].setIcon(new ImageIcon(sImage + pieces[j] +"B.png"));}
				}

				boardSquares[i][j].addActionListener(this);
			}
		}

		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for (int i = 0; i < this.boardSquares.length; i++) 
		{
			for (int j = 0; j < this.boardSquares.length; j++) 
			{
				if (e.getSource() == this.boardSquares[i][j])
				{
					JButton b = this.boardSquares[i][j];
					if (!clique)
					{
						this.ligD=this.boardSquares.length-i ;
						this.colD=(char)('A' + j);
						this.clique = true;
						System.out.println( this.ligD+""+this.colD+" clique : 1");
					}
					else
					{
						this.ligF=this.boardSquares.length-i ;
						this.colF=(char)('A' + j);
						this.clique = false;
						this.ctrl.deplacer(this.ligD, this.colD, this.ligF, this.colF);
						this.IhmMaj();
						System.out.println( this.ligD+""+this.colD+"clique :2");
					}
					
				}
			}
		}
	}

	public void IhmMaj()
	{
		for (int i = this.boardSquares.length-1; i >0; i--)
		{
			for (int j = 0; j < this.boardSquares.length; j++)
			{
				for (int k=0; k< this.ctrl.getTabPiece().length; k++)
				{
					if (this.ctrl.getTabPiece()[k].getLig()==i+1 && this.ctrl.getTabPiece()[k].getCol()==(char)('A' + j))
					{
						
						boardSquares[i][j].setIcon(new ImageIcon("./images/"+this.ctrl.getTabPiece()[k].getType().substring(0,2) + Character.toUpperCase(this.ctrl.getTabPiece()[k].getCoul())+ ".png"));
					}
				}

			}
		}
		System.out.println(this.ctrl.metier().toString(this.ctrl.getTabPiece()));

		// Redessine le panneau pour refléter les changements
		this.repaint();
	}
	
	
}