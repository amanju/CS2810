package iitm.apl.MazeGenerator;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 
 * @author Sathyanarayanan S (Modified by Sachin)
 */
public class Recorder extends javax.swing.JFrame {
	private static final long serialVersionUID = -8226900270194610798L;
	
	private LinkedList<StringRepresentable> refs;
	private LinkedList<String> snaps;
	private LinkedList<genericDraw> gdraws ;
	
	Button play, rewind, next, prev ;
	ListIterator<String> SnapIterator ;

	public Recorder() {
		refs = new LinkedList<StringRepresentable>();
		snaps = new LinkedList<String>();
		gdraws = new LinkedList<genericDraw>();
		this.setVisible(false);
		this.setLayout(null);

		ButtonListener bl = new ButtonListener(this);

		play = new Button("Play");
		this.add(play);
		play.setBounds(500, 25, 60, 25);
		play.setEnabled(false);
		play.addActionListener(bl);

		rewind = new Button("Rewind");
		this.add(rewind);
		rewind.setBounds(570, 25, 60, 25);
		rewind.setEnabled(false);
		rewind.addActionListener(bl);

		prev = new Button("Prev");
		this.add(prev);
		prev.setBounds(640, 25, 60, 25);
		prev.setEnabled(false);
		prev.addActionListener(bl);

		next = new Button("Next");
		this.add(next);
		next.setBounds(710, 25, 60, 25);
		next.setEnabled(false);
		next.addActionListener(bl);

		this.addWindowListener(new WindowListener());
	}

	void play() {
		this.setTitle("Graphical Interface");
		this.setVisible(true);
		this.setBounds(0, 0, 1280, 800);

		SnapIterator = snaps.listIterator();

		if (SnapIterator.hasNext()) {
			next.setEnabled(true);
			play.setEnabled(true);
			this.repaint();
		}
	}

	void playSequence() {
		next.setEnabled(false);
		prev.setEnabled(false);

		rewind.setEnabled(false);
		play.setEnabled(false);

		while (SnapIterator.hasNext()) {
			this.paint(this.getGraphics()) ;
			
			SnapIterator.next() ;
			
			try {
				Thread.sleep(700);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		next.setEnabled(false);
		prev.setEnabled(true);

		rewind.setEnabled(true);
		play.setEnabled(false);
	}

	void rewind() {
		SnapIterator = snaps.listIterator() ;
		this.paint(this.getGraphics()) ;
		play.setEnabled(true) ;
		prev.setEnabled(false) ;
		next.setEnabled(true) ;
	}

	void loadNextSnap() {
		if (SnapIterator.hasNext()) {
			SnapIterator.next();
		}

		if (SnapIterator.hasNext()) {
			next.setEnabled(true);
		} else {
			next.setEnabled(false);
		}

		if (SnapIterator.hasPrevious()) {
			prev.setEnabled(true);
		} else {
			prev.setEnabled(false);
		}

		this.repaint();
	}

	void loadPrevSnap() {
		if (SnapIterator.hasPrevious()) {
			SnapIterator.previous();
		}

		if (SnapIterator.hasNext()) {
			next.setEnabled(true);
		} else {
			next.setEnabled(false);
		}

		if (SnapIterator.hasPrevious()) {
			prev.setEnabled(true);
		} else {
			prev.setEnabled(false);
		}

		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1280, 800);

		String str;
		if (SnapIterator.hasNext()) {
			str = SnapIterator.next();
			SnapIterator.previous();
		} 
		else {
			str = SnapIterator.previous();
			SnapIterator.next();
		}

		genericDraw d = gdraws.getFirst() ;
		d.draw(g, str) ;
	}

	void addRef(StringRepresentable o, genericDraw d) {
		refs.addLast(o);
		gdraws.addLast(d);
	}

	void removeLastSnap() {
		snaps.removeLast();
	}

	void takeSnap(StringRepresentable sr) {
		snaps.addLast(new String(sr.getStringRepresentation()));
	}

	public String toString() {
		return snaps.toString();
	}
}
