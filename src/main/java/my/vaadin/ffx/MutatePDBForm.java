package my.vaadin.ffx;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.ValoTheme;

public class MutatePDBForm extends GridLayout {
	
	private Label mutationInfoTitle = new Label("Mutation Information");
	private Job job;
	private MyUI myUI;
	private TextField aminoAcidPosition = new TextField("Amino Acid Position");
	private String aminoAcidPos = "0";
	private TextField chain = new TextField("Chain");
	private String chainID = "";
	NativeSelect aminoAcidChange = new NativeSelect("Amino Acid Change");
	private String[] aminoAcids = {"ALA", "ARG", "ASN", "ASP", "CYS", "GLU", "GLN", "GLY", "HIS", "ILE", 
			"LEU", "LYS", "MET", "PHE", "PRO", "SER", "THR", "TRP", "TYR", "VAL"};
	private String aminoAcidSele = ""; 
	
	public MutatePDBForm() {
		
		super(3,8);
		
		aminoAcidPosition.setRequired(true);
		aminoAcidPosition.addValidator(new NullValidator("Must be given", false));
		
		// Set amino acid dropdown menu
		for (int i = 0; i < aminoAcids.length; i++){
			aminoAcidChange.addItem(aminoAcids[i]);
			aminoAcidChange.setItemCaption(i, aminoAcids[i]);
		}
		
		aminoAcidChange.setNullSelectionAllowed(false);
		aminoAcidChange.setRequired(true);
		aminoAcidChange.addValidator(new NullValidator("Must be given", false));
		
		
		// Informational icons
		
        // (col1, row1) (col2, row2)
		addComponent(mutationInfoTitle);
		setSpacing(true);
		addComponent(aminoAcidPosition, 0, 1);
		setSpacing(true);
		addComponent(chain, 0, 2);
		setSpacing(true);
		addComponent(aminoAcidChange, 0, 3);
		aminoAcidChange.setWidth("100%");
		setSpacing(true);
		
	}
	
	public Button helpButtonGenerator(Button button, String prompt){
		button.setIcon(FontAwesome.INFO_CIRCLE);
		button.setStyleName(BaseTheme.BUTTON_LINK);
		button.setDescription(prompt); 
		return button;
	}
	
	public String getAminoAcidPosition()
	{
		return aminoAcidPosition.getValue();
	}
	
	public String getChain()
	{
		return chain.getValue();
	}
	
	public String getAminoAcidChange() {
		return aminoAcidChange.getValue().toString();
	}
}
