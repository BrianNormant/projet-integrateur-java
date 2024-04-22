package interfaces;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import api.RestApi;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

import composanteGraphique.Train;


public class TrainInterface extends JPanel {

	private JSlider sl;
	private JLabel lastlbl, nextlbl;
	private JPanel routesp;

	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	private boolean stop = false;
	public TrainInterface(Train train) {
		var details = RestApi.requestDetails(train.getId());
		var gb = new GridBagLayout();
		setLayout(gb);
		var c = new GridBagConstraints();


		details.ifPresent(d -> {
			c.fill = GridBagConstraints.HORIZONTAL;
			// id: ${id}
			c.gridx = 0;
			c.gridy = 0;
			this.add(new JLabel("id"), c);
			c.gridx = 1;
			c.gridy = 0;
			this.add(new JLabel(String.valueOf(train.getId())), c);
			c.gridx = 0; c.gridy = 1;
			this.add(new JLabel("speed"), c);
			c.gridx = 1; c.gridy = 1;
			this.add(new JLabel(String.format("%.2f km/h", d.speed())), c);

			c.gridx = 0; c.gridy = 2;
			this.add(new JLabel("charge"), c);
			c.gridx = 1; c.gridy = 2;
			this.add(new JLabel(String.format("%d kg", d.charge())), c);
			c.gridx = 0; c.gridy = 3;
			this.add(new JLabel("puissance"), c);
			c.gridx = 1; c.gridy = 3;
			this.add(new JLabel(String.format("%d HP", d.puissance())), c);

			c.gridx = 0; c.gridy = 4;
			this.add(new JLabel("company"), c);
			c.gridx = 1; c.gridy = 4;
			this.add(new JLabel(d.company()), c);

			c.gridx = 0; c.gridy = 5;
			lastlbl = new JLabel(train.getNextStation().getName());
			this.add(lastlbl, c);


			c.gridx = 1; c.gridy = 5;
			sl = new JSlider(0, 100, (int)(train.getPos()));
			sl.setEnabled(false);
			this.add(sl, c);

			c.gridx = 2; c.gridy = 5;
			nextlbl = new JLabel(train.getLastStation().getName());
			this.add(nextlbl, c);

			routesp = new JPanel();
			routesp.setLayout(new BoxLayout(routesp, BoxLayout.Y_AXIS));

			d.route()
				.stream()
				.map(r -> {
					var l = new JLabel(r);
					if (r.equals(train.getNextStation().getName())) {
						l.setForeground(Color.RED);
					}
					return l;
				})
			.forEach(l -> routesp.add(l));
			c.fill = GridBagConstraints.HORIZONTAL;  
			c.gridx = 0; c.gridy = 6;
			c.gridwidth = 3;
			this.add(routesp, c);

			var btn = new JButton("Retour");
			btn.addActionListener(i -> back());
			c.gridx = 0; c.gridy = 7;
			c.gridwidth = 3;
			this.add(btn, c);

			new Thread(() -> {
				while (!stop) {
					sl.setValue((int)train.getPos());
					lastlbl.setText(train.getNextStation().getName());
					nextlbl.setText(train.getLastStation().getName());

					routesp.removeAll();
					d.route()
						.stream()
						.map(r -> {
							var l = new JLabel(r);
							if (r.equals(train.getNextStation().getName())) {
								l.setForeground(Color.RED);
							}
							return l;
						})
					.forEach(l -> routesp.add(l));
					routesp.revalidate();
					this.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		});

	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	public void back() {
		this.stop = !stop;
		PCS.firePropertyChange("back", 0, -1);
	}

}
