package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	public MainFrame() {
		setTitle("������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel groundPane = new JPanel();
		setContentPane(groundPane);
		groundPane.setBackground(Color.white);

		setResizable(false);
		groundPane.setLayout(null);

		// ��� (�˻� �Է�â(������ �̹���+JTextField),�ΰ� �̹���,�α��� ��ư)

		// �˻� �Է�â
		// ������
		ImageIcon icon1 = new ImageIcon("images/������.png");
		Image image1 = icon1.getImage();
		Image image5 = image1.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image6 = new ImageIcon(image5);
		JLabel imageLabel2 = new JLabel();
		imageLabel2.setIcon(image6);
		imageLabel2.setBounds(10, 50, 40, 40);
		groundPane.add(imageLabel2);

		// �˻�
		RoundJTextField searchTF = new RoundJTextField(250);
		searchTF.setBounds(50, 50, 250, 40);
		groundPane.add(searchTF);

		// �ΰ�
		ImageIcon icon = new ImageIcon("images/logo.jpg");
		Image image = icon.getImage();
		Image image2 = image.getScaledInstance(200, 90, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image3 = new ImageIcon(image2);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(image3);
		imageLabel.setBounds(500, 0, 200, 90);
		groundPane.add(imageLabel);

		// �α��� ��ư
		JButton btnLogin = new JButton("�α���");
		btnLogin.setBounds(1000, 50, 100, 40);
		btnLogin.setBackground(Color.white);
		groundPane.add(btnLogin);

		// �޴� ī�װ� (JPanel+JLabel�� �׼��̺�Ʈ)
		JPanel categoryPane = new JPanel();
		categoryPane.setBackground(new Color(204, 204, 255, 100));
		categoryPane.setBounds(10, 100, 1170, 40);
		groundPane.add(categoryPane);

		JLabel[] category = new JLabel[5];
		String[] cNames = { "��������", "�����Խ���", "��������", "ȸ���ı�", "����" };
		for (int i = 0; i < category.length; i++) {
			category[i] = new JLabel("  " + cNames[i] + "  |");
			category[i].setFont(new Font("�������", Font.BOLD, 26));
			categoryPane.add(category[i]);
		}

		// ��������

		// �����Խ���

		// �� ���� �ִ� ���

		// ���� ����

		// ����

		setSize(1200, 900);
		setVisible(true);
	}

	public class RoundJTextField extends JTextField {
		/**
		 * �˻�â ���� ������ (�𼭸� �ձ۰�) ������ �̹��� �ؽ�Ʈ�ʵ� �ȿ� �־����
		 */
		private static final long serialVersionUID = 1L;
		private Shape shape;
		private Icon icon;

		public RoundJTextField(int size) {
			super(size);
			setOpaque(false);
			setText("�˻� ���");

		}

		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
			super.paintComponent(g);
		}

		protected void paintBorder(Graphics g) {
			g.setColor(getForeground());
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
			}
			return shape.contains(x, y);
		}

	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}

}
