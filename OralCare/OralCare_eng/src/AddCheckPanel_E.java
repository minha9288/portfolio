

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AddCheckPanel_E extends JPanel {
	
	JLabel la_tartar, la_gingivitis, 
			la_micro_sal_Amount, la_micro_sal_Movement, la_micro_gu_Amount, la_micro_gu_Movement,
			la_snyder,
			la_malocclusion, la_odontoclasis, la_infection, la_badBreath, la_disease,
			la_brushMethod, la_periodontal, la_mobility, la_mandibular, la_aesthetic,
			la_saliva, la_consistency, la_dazzling, la_wisdomTooth_pain, 
			la_partialDenture, la_prosthesis_need, la_denture_need ;
	JComboBox info_tartar, info_gingivitis, 
			info_micro_sal_Amount, info_micro_sal_Movement, info_micro_gu_Amount, info_micro_gu_Movement,
			info_snyder,
			info_malocclusion, info_odontoclasis, info_infection, info_badBreath, info_disease,
			info_brushMethod, info_periodontal, info_mobility, info_mandibular, info_aesthetic,
			info_saliva, info_consistency, info_dazzling, info_wisdomTooth_pain,
			info_partialDenture, info_prosthesis_need, info_denture_need;
	
	int age = 0;
	
	public AddCheckPanel_E(int sendAge){
		
		age = sendAge;
	
		la_tartar = new JLabel("Calculus");
	      info_tartar = new JComboBox(new String[] {"None", "A few", "Moderate", "Severe", "Numerousness"});
	      la_gingivitis = new JLabel("Gingivitis");
	      info_gingivitis = new JComboBox(new String[] {"None", "1 portion", "2 portion", "3 portion", "4 or more"});
	      
	      
	      la_micro_sal_Amount = new JLabel("Amount filament/spiral");
	      info_micro_sal_Amount = new JComboBox(new String[] {"Many", "A few"});
	      la_micro_sal_Movement = new JLabel("Motile filament/spiral");
	      info_micro_sal_Movement = new JComboBox(new String[] {"High", "Low"});
	      
	      la_micro_gu_Amount = new JLabel("Amount cocci/bacillus");
	      info_micro_gu_Amount = new JComboBox(new String[] {"Many", "A few"});
	      la_micro_gu_Movement = new JLabel("Motile cocci/bacillus");
	      info_micro_gu_Movement = new JComboBox(new String[] {"High", "Low"});

	      la_snyder = new JLabel("Snyder test");
	      info_snyder = new JComboBox(new String[] {"+++", "++", "+", "-"});
	      
	      la_malocclusion = new JLabel("Malocclusion");
	      info_malocclusion = new JComboBox(new String[] {"Normal", "Slight", "On treatment", "Severe"});
	      la_odontoclasis = new JLabel("Tooth fracture");
	      info_odontoclasis = new JComboBox(new String[] {"None", "1 portion", "2 portion", "3 or more"});
	      la_infection = new JLabel("Oral infection");
	      info_infection = new JComboBox(new String[] {"None", "1 portion", "2 or more"});
	      la_badBreath = new JLabel("Oral malodor");
	      info_badBreath = new JComboBox(new String[] {"None", "Slight", "Severe on treatment", "Severe without treatment"});

	      la_brushMethod = new JLabel("Brushing method");
	      info_brushMethod = new JComboBox(new String[] {"Rolling", "Un-skilled rolling", "Horizontal rolling "});
	      
	      la_periodontal = new JLabel("Periodontal pocket");
	      info_periodontal = new JComboBox(new String[] {"None", "1 portion", "2 portion", "3 portion", "4 portion", "5 portion", "6 or more"});
	      la_mobility = new JLabel("Tooth mobility");
	      info_mobility = new JComboBox(new String[] {"None", "1 tooth", "2 teeth", "3 teeth", "4 teeth", "5 or more"});
	      la_mandibular = new JLabel("TMJ disorder");
	      info_mandibular = new JComboBox(new String[] {"None", "Clicking sound", "Pain", "Severe TMJ trouble"});
	      la_aesthetic = new JLabel("Esthetic problem");
	      info_aesthetic = new JComboBox(new String[] {"None", "Slight", "Severe"});
	      
	      la_saliva = new JLabel("Salivary flow");
	      info_saliva = new JComboBox(new String[] {"Many", "A few"});
	      la_consistency = new JLabel("Viscosity");
	      info_consistency = new JComboBox(new String[] {"Low", "High"});
	      la_dazzling = new JLabel("3rd Molar problem/abrasion");
	      info_dazzling = new JComboBox(new String[] {"None", "1 portion", "2 portion", "3 or more"});
	      la_wisdomTooth_pain = new JLabel("3rd Molar problem");
	      info_wisdomTooth_pain = new JComboBox(new String[] {"None", "Slight", "Severe"});
	      
	      la_partialDenture = new JLabel("Partial denture");
	      info_partialDenture = new JComboBox(new String[] {"None", "One portion of the upper and lower", "Both protion of the upper and lower", "Severe"});
	      la_prosthesis_need = new JLabel("Bridge/denture need");
	      info_prosthesis_need = new JComboBox(new String[] {"No need", "Need bridge and wearing", "Need Denture and wearing", "Need but not wearing"});
	      la_denture_need = new JLabel("Denture need");
	      info_denture_need = new JComboBox(new String[] {"No need", "Need and wearing", "Need but not wearing"});
	      
	
		
		
		if(0 <= age && age < 6) {
			
			// 위치 지정!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
		}	
		else if (6 <= age && age < 12) {
			
			// 위치 지정!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
		}
		else if (12 <= age && age < 20) {
			// 위치 지정!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
		}
		else if (20 <= age && age < 40) {
			
			// 위치 지정!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_saliva  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_saliva     , new GridBagConstraints(1, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_consistency    , new GridBagConstraints(2, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_consistency    , new GridBagConstraints(3, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_dazzling , new GridBagConstraints(0, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_dazzling     , new GridBagConstraints(1, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_wisdomTooth_pain  , new GridBagConstraints(2, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_wisdomTooth_pain  , new GridBagConstraints(3, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			
		}
		else if (40 <= age && age < 65) {
			
			// 위치 지정!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_odontoclasis, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_dazzling  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(1, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_partialDenture  , new GridBagConstraints(0, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_partialDenture   , new GridBagConstraints(1, 7, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_prosthesis_need   , new GridBagConstraints(2, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_prosthesis_need, new GridBagConstraints(3, 7, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
		}
		else if (age > 64) {
			
			// 위치 지정!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_odontoclasis, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_saliva  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_saliva     , new GridBagConstraints(1, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_consistency    , new GridBagConstraints(2, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_consistency    , new GridBagConstraints(3, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_partialDenture  , new GridBagConstraints(0, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_partialDenture   , new GridBagConstraints(1, 7, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_denture_need   , new GridBagConstraints(2, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_denture_need, new GridBagConstraints(3, 7, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
		}
		

	}

}
