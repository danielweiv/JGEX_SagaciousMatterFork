package wprover;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;


/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2005-2-27
 * Time: 21:05:35
 * To change this template use File | Settings | File Templates.
 */
public class DialogTextFrame extends DialogBase implements ItemListener,
        ActionListener, FocusListener, MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5028129112403750711L;
	JEditorPane textpane = null;
    GEText text = null;

    JComboBox<String> fonts, styles;
	JComboBox<Integer> color, sizes;
    JButton bok, bcancel;
    int index = 0;
    String fontchoice = "fontchoice";
    int stChoice = 0;
    String siChoice = "10";
//    Vector<String> fontfamily;
    //    Font defaultFont = new Font("Dialog", Font.PLAIN, 16);
    DrawPanelFrame gxInstance;

    int x, y;

    public void init() {

        Font tf = new Font("Dialog", Font.PLAIN, 12);

        getContentPane().setLayout(new BorderLayout());
        JPanel topPanel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -7091765440034144674L;

			public Dimension getPreferredSize() {
                Dimension dm = super.getPreferredSize();
                dm.setSize(dm.getWidth(), 22);
                return dm;
            }
        };
        topPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String envfonts[] = gEnv.getAvailableFontFamilyNames();

        Font cfont = null;
//        for (int i = 0; i < envfonts.length; i++) {
//            if (envfonts[i].equalsIgnoreCase("Dialog")) {
//                cfont = new Font("Dialog", Font.PLAIN, 16);
//                break;
//            }
//        }
//        if (cfont == null)
            cfont = new Font("Dialog", Font.PLAIN, 16);
        
//        fontfamily = new Vecto<String>();
//        for (int i = 1; i < envfonts.length; i++)
//            fontfamily.addElement(envfonts[i]);
        fonts = new JComboBox<String>(envfonts);
        fonts.setFont(tf);

        fonts.setMaximumRowCount(9);
        fonts.addItemListener(this);
        fontchoice = envfonts[0];
        topPanel.add(fonts);
        topPanel.add(Box.createHorizontalStrut(5));


        int[] fz = UtilityMiscellaneous.getFontSizePool();
        int n = fz.length;
        Integer[] o = new Integer[n];
        for (int i = 0; i < n; i++)
            o[i] = new Integer(fz[i]);

        sizes = new JComboBox<Integer>(o);

        sizes.setFont(tf);
        sizes.setMaximumRowCount(20);
        sizes.addItemListener(this);
        topPanel.add(sizes);
        topPanel.add(Box.createHorizontalStrut(5));


        styles = new JComboBox<String>(new String[]{
                "PLAIN",
                "BOLD",
                "ITALIC"});
        styles.setMaximumRowCount(9);
        styles.setFont(tf);
        styles.addItemListener(this);
        sizes.setMaximumRowCount(9);
        topPanel.add(styles);
        topPanel.add(Box.createHorizontalStrut(5));


        color = ComboBoxInteger.CreateAInstance();
        color.setPreferredSize(new Dimension(100, 20));
        color.setFont(tf);
        topPanel.add(color);
        topPanel.add(Box.createHorizontalStrut(5));

        bok = new JButton("OK");
        bok.addActionListener(this);
        topPanel.add(bok);
        bcancel = new JButton("Cancel");
        bcancel.addActionListener(this);
        topPanel.add(bcancel);
        bok.setText(DrawPanelFrame.getLanguage(3204, "OK"));
        bcancel.setText(DrawPanelFrame.getLanguage("Cancel"));

        fonts.addActionListener(this);
        sizes.addActionListener(this);
        styles.addActionListener(this);
        color.addActionListener(this);

        color.setSelectedIndex(16);

        JToolBar tb = new JToolBar("style");
        tb.add(topPanel);
        tb.setFloatable(false);

        textpane.addMouseListener(this);
        this.setCurrentFont(cfont);
        getContentPane().add(BorderLayout.SOUTH, tb);
    }

    public void setCurrentFont(Font f) {
        if (f == null) return;
        int size = f.getSize();
        int type = f.getStyle();
        String fname = f.getName();

        
//                for (int i = 0; i < fontfamily.size(); i++) {
//                    String s = fontfamily.get(i);
//        for (int i = 0; i < envfonts.; i++) {
//            String s = envfonts[i];
//            if (s.compareTo(fname) == 0) {
                fonts.setSelectedItem(fname);
//                break;
//            }
//        }
        styles.setSelectedIndex(type);

        for (int i = 0; i < sizes.getItemCount(); i++) {
            int s = sizes.getItemAt(i).intValue();
            if (s == size) {
                sizes.setSelectedIndex(i);
                break;
            }

        }

    }

    public DialogTextFrame(DrawPanelFrame gx, double xt, double yt) {
        super(gx.getFrame());
        this.gxInstance = gx;
        this.x = (int) xt;
        this.y = (int) yt;

        if (UtilityMiscellaneous.isApplication())
            this.setAlwaysOnTop(true);
        textpane = new JEditorPane();
        this.init();
        this.getContentPane().add(new JScrollPane(textpane));
        text = null;
 //       setCurrentFont(gxInstance.getDefaultFont());
        this.setSize(550, 200);

    }

    void setTextLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void setText(GEText tx) {
        if (tx != null) {
            text = tx;
            textpane.setText(tx.getText());
            textpane.setFont(tx.getFont());
            Font f = tx.getFont();
            color.setSelectedIndex(tx.m_color);
            setCurrentFont(f);
            this.setTitle(DrawPanelFrame.getLanguage("Text"));
        } else {
            this.setTitle(DrawPanelFrame.getLanguage("Text"));
            text = null;
            textpane.setText("");
        }
    }

    public Dimension getPreferredSize() {
        Dimension dm = super.getPreferredSize();
        dm.setSize(dm.getWidth(), 200);
        return dm;
    }

    GEText getText() {

        String s = (String) fonts.getSelectedItem();
        int size = ((Integer) sizes.getSelectedItem()).intValue();
        int type = styles.getSelectedIndex();
        Font f = new Font(s, type, size);

        if (text != null) {
            text.setText(textpane.getText());
            text.setFont(f);
            return text;
        } else {
            return null;
        }
    }

    String getString() {
        return textpane.getText();
    }

    public void itemStateChanged(ItemEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bok) {
            String str = textpane.getText();

            GEText ct = text;
            if (ct == null) {
                if (str == null || str.length() == 0) {
                    this.setVisible(false);
                    return;
                }
                ct = new GEText(x, y, str);
            } else
                ct.setText(str);

            String s = (String) fonts.getSelectedItem();
            int size = ((Integer) sizes.getSelectedItem()).intValue();
            int type = styles.getSelectedIndex();
            Font f = new Font(s, type, size);
            ct.setFont(f);
            int id = color.getSelectedIndex();
            ct.setColor(id);
            if (ct != text)
                gxInstance.dp.addText(ct);
            this.setVisible(false);
            gxInstance.d.repaint();
        } else if (e.getSource() == bcancel) {
            this.setVisible(false);
        } else {
            String s = (String) fonts.getSelectedItem();
            int size = ((Integer) sizes.getSelectedItem()).intValue();
            int type = styles.getSelectedIndex();
            Font f = new Font(s, type, size);
            textpane.setFont(f);
            int id = color.getSelectedIndex();
            textpane.setForeground(DrawData.getColor(id));
            if (text != null)
                text.m_color = (id);
        }

    }

    public void focusGained(FocusEvent e) {
    }

    public void focusLost(FocusEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            new TextPopupMenu(textpane.getSelectionStart()
                    != textpane.getSelectionEnd()).show(textpane, e.getX(), e.getY());
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    class TextPopupMenu extends JPopupMenu implements ActionListener, ClipboardOwner {
        /**
		 * 
		 */
		private static final long serialVersionUID = -2615800900722067218L;

		public TextPopupMenu(boolean selected) {
            this.addMenuItem("Cut", selected);
            this.addMenuItem("Copy", selected);
            this.addMenuItem("Paste", true);
            this.addSeparator();
            this.addMenuItem("Delete", selected);
            this.addMenuItem("Select All", true);
        }

        public void addMenuItem(String s, boolean selected) {
            JMenuItem item = new JMenuItem(s);
            item.addActionListener(TextPopupMenu.this);
            item.setEnabled(selected);
            this.add(item);
        }

        public void lostOwnership(Clipboard aClipboard, Transferable aContents) {
            //do nothing
        }


        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equalsIgnoreCase("Cut")) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection stringSelection = new StringSelection(textpane.getSelectedText());
                clipboard.setContents(stringSelection, TextPopupMenu.this);
                int st = textpane.getSelectionStart();
                int ed = textpane.getSelectionEnd();
                String text = textpane.getText();
                textpane.setText(text.substring(0, st) + text.substring(ed, text.length()));

            } else if (s.equalsIgnoreCase("Copy")) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection stringSelection = new StringSelection(textpane.getSelectedText());
                clipboard.setContents(stringSelection, this);
            } else if (s.equalsIgnoreCase("Paste")) {
                int st = textpane.getSelectionStart();
                int ed = textpane.getSelectionEnd();
                String text = textpane.getText();
                textpane.setText(text.substring(0, st) + getClipboardContents() + text.substring(ed, text.length()));
            } else if (s.equalsIgnoreCase("Delete")) {
                int st = textpane.getSelectionStart();
                int ed = textpane.getSelectionEnd();
                String text = textpane.getText();
                textpane.setText(text.substring(0, st) + text.substring(ed, text.length()));
            } else if (s.equalsIgnoreCase("Select All")) {
                textpane.setSelectionStart(0);
                textpane.setSelectionEnd(textpane.getText().length());
            }
        }

        public String getClipboardContents() {
            String result = "";
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            //odd: the Object param of getContents is not currently used
            Transferable contents = clipboard.getContents(TextPopupMenu.this);
            //Object oobs[] = clipboard.getAvailableDataFlavors();
            boolean hasTransferableText =
                    (contents != null) &&
                            contents.isDataFlavorSupported(DataFlavor.stringFlavor);
            if (hasTransferableText) {
                try {
                    result = (String) contents.getTransferData(DataFlavor.stringFlavor);
                }
                catch (UnsupportedFlavorException ex) {
                    //highly unlikely since we are using a standard DataFlavor
                    System.out.println(ex);
                    ex.printStackTrace();
                }
                catch (IOException ex) {
                    System.out.println(ex);
                    ex.printStackTrace();
                }
            }
            return result;
        }

    }

}
