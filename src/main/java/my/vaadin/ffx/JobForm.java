package my.vaadin.ffx;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusListener;
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
	private Label generalInfoTitle = new Label("General Information");
	private Job job;
	private MyUI myUI;
	private TextField jobName = new TextField("Job Name");
	private TextField email = new TextField("Email");
	NativeSelect file = new NativeSelect("File");
	private String[] fileOptions = {"5F07 (C3b)", "2QKI (C3c and compstatin)", "1A1P (compstatin)"};
	NativeSelect command = new NativeSelect("Command");
	private String[] commandOptions = {"Energy", "MutatePDB", "Minimize"};
	//private MutatePDBForm mutatePDBForm = new MutatePDBForm();
	private Button jobNameHelp = new Button();
	private Button emailHelp = new Button();
	private Button fileHelp = new Button();
	private Button commandHelp = new Button();
	private Button save = new Button("Save");
	private Button clear = new Button("Clear");
	public String commandSelection;
	private boolean jobValid;
	private boolean emailValid;
	private boolean fileValid;
	private boolean commandValid;
	
	public JobForm() {
		
		super(3, 8);
		
		
		jobName.setIcon(FontAwesome.USER);
        jobName.setRequired(true);
        jobName.addValidator(new NullValidator("Must be given", false));
        jobValid = jobName.isValid();
        
        email.setIcon(FontAwesome.ENVELOPE);
        email.setRequired(true);
        email.addValidator(new EmailValidator("Please enter a valid email address."));
        emailValid = email.isValid(); 
        
		// Set file drop down menu
		for (int i = 0; i < fileOptions.length; i++) {
			file.addItem(fileOptions[i]);
			file.setItemCaption(i, fileOptions[i]);
		}
		
		file.setNullSelectionAllowed(false);
		file.setIcon(FontAwesome.FILE);
		file.setRequired(true);
        file.addValidator(new NullValidator("Must be given", false));
        fileValid = file.isValid();
		
		// Set command drop down menu
		for (int i = 0; i < commandOptions.length; i++){
			command.addItem(commandOptions[i]);
			command.setItemCaption(i, commandOptions[i]);
		}
		
		command.setNullSelectionAllowed(false);
		command.setRequired(true);
		command.addValidator(new NullValidator("Must be given", false));
		command.addValueChangeListener(e -> {
			commandSelection = command.getValue().toString();
	            
	            //job.setCommand(commandValue);
	    });
		commandValid = command.isValid();

        
        //command.setSizeFull();
		
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		
		// Informational icons
		jobNameHelp = helpButtonGenerator(jobNameHelp, "Please enter a job name using letters, numbers, dashes, or underscores.");
        emailHelp = helpButtonGenerator(emailHelp, "Please enter an email address to which the results can be sent.");
        fileHelp = helpButtonGenerator(fileHelp, "Please select a PDB on which to perform the FFX job.");
        commandHelp = helpButtonGenerator(commandHelp, "Please visit http://ffx.biochem.uiowa.edu for command descriptions.");
        
        // Add different forms depending on command
        
        
		
        // (col1, row1) (col2, row2)
		addComponent(generalInfoTitle);
		setComponentAlignment(generalInfoTitle, Alignment.MIDDLE_RIGHT);
		setSpacing(true);
        addComponent(jobName, 0, 1);
		setSpacing(true);
		addComponent(jobNameHelp, 1, 1);
		setSpacing(true);
		setComponentAlignment(jobNameHelp, Alignment.BOTTOM_LEFT);
		addComponent(email, 0, 2);
		setSpacing(true);
		addComponent(emailHelp, 1, 2);
		setSpacing(true);
		setComponentAlignment(emailHelp, Alignment.BOTTOM_LEFT);
		addComponent(file, 0, 3);
		setSpacing(true);
		file.setWidth("100%");
		addComponent(fileHelp, 1, 3);
		setSpacing(true);
		setComponentAlignment(fileHelp, Alignment.BOTTOM_LEFT);
		addComponent(command, 0, 4);
		setSpacing(true);
		command.setWidth("100%");
		addComponent(commandHelp, 1, 4);
		setSpacing(true);
		setComponentAlignment(commandHelp, Alignment.BOTTOM_LEFT);
		
	}
	
	public Button helpButtonGenerator(Button button, String prompt){
		button.setIcon(FontAwesome.INFO_CIRCLE);
		button.setStyleName(BaseTheme.BUTTON_LINK);
		button.setDescription(prompt); 
		return button;
	}
}
