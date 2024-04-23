package panelsSpeciaux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

import api.RestApi;
import composanteGraphique.Train;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class PanelTrain extends JPanel {
	
	private JLabel lblIdVar,lblArrivee,lblDepart ;
	private JLabel lblVitesseVar ;
	private JLabel lblChargeVar ;
	private JLabel lblPuissanceVar ;
	private JLabel lblCompagnieVar ;
	private JProgressBar prgbrPosition;
	private JLabel lblTitreStation;
	private JPanel routesp;
	private boolean stop = false;
	/**
	 * Create the panel.
	 * @param train 
	 */
	public PanelTrain(Train train) {
		
		var details = RestApi.requestDetails(train.getId());
		var gb = new GridBagLayout();
	
		
		setBorder(new LineBorder(Color.BLACK));
		setBounds(0,0,900,230);
		setLayout(null);
		setBackground(Color.WHITE);
		
		JLabel lblId = new JLabel("Id Train:");
		lblId.setBounds(10, 10, 99, 25);
		add(lblId);
		
		JLabel lblVitesse = new JLabel("Vitesse de Croissière:");
		lblVitesse.setBounds(10, 50, 125, 25);
		add(lblVitesse);
		
		JLabel lblCharge = new JLabel("Charge du Train:");
		lblCharge.setBounds(10, 95, 125, 25);
		add(lblCharge);
		
		JLabel lblPuissance = new JLabel("Puissance du Train:");
		lblPuissance.setBounds(10, 140, 125, 25);
		add(lblPuissance);
		
		JLabel lblCompagnie = new JLabel("Compagnie Propriétaire:");
		lblCompagnie.setBounds(10, 185, 149, 25);
		add(lblCompagnie);
		
		lblIdVar = new JLabel();
		lblIdVar.setBounds(169, 10, 125, 25);
		add(lblIdVar);
		
		lblVitesseVar = new JLabel("...");
		lblVitesseVar.setBounds(169, 50, 125, 25);
		add(lblVitesseVar);
		
		lblChargeVar = new JLabel("...");
		lblChargeVar.setBounds(169, 95, 125, 25);
		add(lblChargeVar);
		
		lblPuissanceVar = new JLabel("...");
		lblPuissanceVar.setBounds(169, 140, 125, 25);
		add(lblPuissanceVar);
		
		lblCompagnieVar = new JLabel("...");
		lblCompagnieVar.setBounds(169, 185, 125, 25);
		add(lblCompagnieVar);
		
		routesp=new JPanel();
		
		prgbrPosition = new JProgressBar();
		prgbrPosition.setBounds(393, 133, 146, 11);
		add(prgbrPosition);
		
		lblTitreStation = new JLabel("Station Parcourue Par le Train");
		lblTitreStation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitreStation.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitreStation.setBounds(688, 24, 196, 25);
		add(lblTitreStation);
		
		lblDepart = new JLabel("Id Train:");
		lblDepart.setBounds(280, 119, 99, 25);
		add(lblDepart);
		
		lblArrivee = new JLabel("Id Train:");
		lblArrivee.setBounds(570, 119, 99, 25);
		add(lblArrivee);
		
		JScrollPane scrollPane = new JScrollPane(routesp);
		scrollPane.setBounds(688, 72, 196, 138);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		setValues(train);

	}

	private void setValues(Train train) {
		
		var details = RestApi.requestDetails(train.getId());
		details.ifPresent(d -> {
			
			lblIdVar.setText(""+train.getId());
			lblVitesseVar.setText(String.format("%.2f km/h", d.speed()));
			lblChargeVar.setText(String.format("%d kg", d.charge()));
			lblPuissanceVar.setText(String.format("%d HP", d.puissance()));
			lblCompagnieVar.setText(d.company());
			lblArrivee.setText(train.getNextStation().getName());
			prgbrPosition.setValue((int)(train.getPos()));
			
		
			lblDepart.setText(train.getLastStation().getName());
	
			
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
			
			
			new Thread(() -> {
				while (!stop) {
					prgbrPosition.setValue((int)train.getPos());
					lblArrivee.setText(train.getLastStation().getName());
					lblDepart.setText(train.getNextStation().getName());

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
	}


