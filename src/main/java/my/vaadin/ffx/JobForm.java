package my.vaadin.ffx;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.ValoTheme;

import my.vaadin.ffx.MyUI;



public class JobForm extends GridLayout {
	//private JobRequestService service = JobRequestService.getInstance();
	private Job job;
	private MyUI myUI;
	private TextField jobName = new TextField("Job Name");
	private TextField email = new TextField("Email");
	private NativeSelect file = new NativeSelect("File");
	private String[] fileOptions = {"file1.pdb", "file2.pdb"};
	private NativeSelect command = new NativeSelect("Command");
	private String[] commandOptions = {"Energy", "Mutation"};
	private TextField aminoAcidPosition = new TextField("Amino Acid Position");
	private TextField chain = new TextField("Chain");
	private NativeSelect aminoAcidChange = new NativeSelect("Amino Acid Change");
	private String[] aminoAcids = {"ALA", "ARG", "ASN", "ASP", "CYS", "GLU", "GLN", "GLY", "HIS", "ILE", 
			"LEU", "LYS", "MET", "PHE", "PRO", "SER", "THR", "TRP", "TYR", "VAL"};
	private Button commandHelp = new Button();
	private Button save = new Button("Save");
	private Button clear = new Button("Clear");
	
	public JobForm() {
		
		super(3, 7);
		
		jobName.setIcon(FontAwesome.USER);
        jobName.setRequired(true);
        jobName.addValidator(new NullValidator("Must be given", false));
        
        email.setIcon(FontAwesome.ENVELOPE);
        email.setRequired(true);
        email.addValidator(new EmailValidator("Please enter a valid email address."));
        
        aminoAcidPosition.setRequired(true);
        aminoAcidPosition.addValidator(new IntegerRangeValidator("Please enter a valid Amino Acid Position", 1, 100000));
        
		// Set file drop down menu
		for (int i = 0; i < fileOptions.length; i++) {
			file.addItem(fileOptions[i]);
			file.setItemCaption(i, fileOptions[i]);
		}
		
		file.setNullSelectionAllowed(false);
		file.setIcon(FontAwesome.FILE);
		file.setRequired(true);
        file.addValidator(new NullValidator("Must be given", false));
		
		// Set command drop down menu
		for (int i = 0; i < 2; i++){
			command.addItem(commandOptions[i]);
			command.setItemCaption(i, commandOptions[i]);
		}
		
		command.setNullSelectionAllowed(false);
		command.setRequired(true);
		command.addValidator(new NullValidator("Must be given", false));
        
        //command.setSizeFull();
		
		// Set amino acid dropdown menu
		for (int i = 0; i < 19; i++){
			aminoAcidChange.addItem(aminoAcids[i]);
			aminoAcidChange.setItemCaption(i, aminoAcids[i]);
		}
		
		aminoAcidChange.setNullSelectionAllowed(false);
		aminoAcidChange.setRequired(true);
		aminoAcidChange.addValidator(new NullValidator("Must be given", false));
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		
		// Informational icons
		commandHelp.setIcon(FontAwesome.INFO_CIRCLE);
		commandHelp.setStyleName(BaseTheme.BUTTON_LINK);
        commandHelp.setDescription("Please visit http://ffx.biochem.uiowa.edu for command descriptions.");        
		
        // (col1, row1) (col2, row2)
		addComponent(jobName);
		setSpacing(true);
		addComponent(email, 0, 1);
		setSpacing(true);
		addComponent(file, 0, 2);
		setSpacing(true);
		file.setWidth("100%");
		addComponent(command, 0, 3);
		setSpacing(true);
		command.setWidth("100%");
		addComponent(commandHelp, 1, 3);
		setSpacing(true);
		setComponentAlignment(commandHelp, Alignment.BOTTOM_LEFT);
		addComponent(aminoAcidPosition, 0, 4);
		setSpacing(true);
		addComponent(chain, 0, 5);
		setSpacing(true);
		addComponent(aminoAcidChange, 0, 6);
		setSpacing(true);
		
	}
}
