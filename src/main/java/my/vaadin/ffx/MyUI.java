package my.vaadin.ffx;

import javax.servlet.annotation.WebServlet;
import ffx.numerics.Erf;


import ffx.Main;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("my.vaadin.ffx.MyAppWidgetset")
public class MyUI extends UI {
	
	private JobForm form = new JobForm();
	private MutatePDBForm mutatePDBForm = new MutatePDBForm();
	private Job job = new Job();
	private String previousCommandSelection = "";
	private String commandSelection = "";
	String pdbWebAddress = "http://www.rcsb.org/pdb/home/home.do";
	BrowserFrame displayPDBProfile = new BrowserFrame();
	private String previousFileSelection = "";
	private String aminoAcidSelection = "";
	private Window resultWindow = new Window();
	VerticalLayout subContent = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        GridLayout grid = new GridLayout(2, 4);
    	
        // Title for form
    	Label title = new Label("Welcome to Force Field X");
    	title.setStyleName("extra large");
    	grid.addComponent(title, 0, 0);
    	grid.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
    	grid.setMargin(true);
        
    	// Add JobForm to grid
    	grid.addComponent(form, 0, 1);
    	
		displayPDBProfile.setWidth("700px");
		displayPDBProfile.setHeight("750px");
		displayPDBProfile.setSource(new ExternalResource(pdbWebAddress));
		grid.addComponent(displayPDBProfile, 1, 1, 1, 2);
    	
    	// Action listener for change in file selection
        form.file.addValueChangeListener(e -> {
    		String fileSelection = form.file.getValue().toString().split(" ")[0].toLowerCase();
    		if(fileSelection == "") {
    			grid.removeComponent(1, 1);
    			pdbWebAddress = "http://www.rcsb.org/pdb/home/home.do";
    			displayPDBProfile = new BrowserFrame();
    			displayPDBProfile.setWidth("700px");
    			displayPDBProfile.setHeight("750px");
    			displayPDBProfile.setSource(new ExternalResource(pdbWebAddress));
    			grid.addComponent(displayPDBProfile, 1, 1, 1, 2);
    		}
    		else if(fileSelection != previousFileSelection) {
    			grid.removeComponent(1, 1);
    			pdbWebAddress = "http://www.rcsb.org/pdb/explore/explore.do?structureId=" + fileSelection;
    			displayPDBProfile = new BrowserFrame();
    			displayPDBProfile.setWidth("700px");
    			displayPDBProfile.setHeight("750px");
    			displayPDBProfile.setSource(new ExternalResource(pdbWebAddress));
    			grid.addComponent(displayPDBProfile, 1, 1, 1, 2);
    		}
    		else {
    			pdbWebAddress = "http://www.rcsb.org/pdb/explore/explore.do?structureId=" + fileSelection;
    			displayPDBProfile = new BrowserFrame();
    			displayPDBProfile.setWidth("700px");
    			displayPDBProfile.setHeight("750px");
    			displayPDBProfile.setSource(new ExternalResource(pdbWebAddress));
    			grid.addComponent(displayPDBProfile, 1, 1, 1, 2);
    		}
    		previousFileSelection = fileSelection;
            UI.getCurrent();
    	});
    	
        // Action listener for change in command selection
    	form.command.addValueChangeListener(e -> {
    		commandSelection = form.command.getValue().toString();
            // If mutatePDB is selected, add fields from mutatePDBForm
    		if (commandSelection == "MutatePDB") {
            	grid.addComponent(mutatePDBForm, 0, 2);
        	}
           else if(previousCommandSelection == "MutatePDB" && commandSelection != "MutatePDB") {
            	grid.removeComponent(0, 2);
            	/*job.setAminoAcidChange("");
            	job.setChain("");
            	job.setAminoAcidPosition("0");*/
            }
           else if (commandSelection == "Energy" || commandSelection == "Minimize"){
        	   	/*job.setAminoAcidChange("");
           		job.setChain("");
           		job.setAminoAcidPosition("0");*/
           }
            previousCommandSelection = commandSelection;
            UI.getCurrent();
    	});
    	
    	
    	// Action listener for change in amino acid selection
    	mutatePDBForm.aminoAcidChange.addValueChangeListener(e -> {
    		aminoAcidSelection = mutatePDBForm.aminoAcidChange.getValue().toString().split(" ")[0].toLowerCase();
    	});
        
        // When save button is pressed, save user input into Job BeanItem
        Button save = new Button("Submit");
        
        save.addClickListener(e -> {
        	try {
        		
        		Notification notif = new Notification("Thank you!", "Your FFX job has been completed.", Notification.TYPE_WARNING_MESSAGE);
        		notif.setPosition(Position.BOTTOM_RIGHT);
        		notif.show(Page.getCurrent());
        		if (form.commandSelection == "MutatePDB") {
        			String[] mutatePDBcommand = {"mutatePDB", "-Djava.awt.headless=\"true\"", "-n " + aminoAcidSelection, 
        					"-c " + job.getChain(), "-r " + job.getAminoAcidPosition(), form.file.getValue().toString().split(" ")[0].toLowerCase()};
        			Main.main(mutatePDBcommand);
        		}
        		else {
        			String[] command = {form.commandSelection.toLowerCase(), "-Djava.awt.headless=\"true\"", form.file.getValue().toString().split(" ")[0].toLowerCase()};
        			Main.main(command);
        		}
        		if (form.commandSelection.equalsIgnoreCase("Energy")) {
        				double energy = Main.mainPanel.getHierarchy().getActive().getPotentialEnergy().getTotalEnergy();
        				Label resultLabel = new Label("Total Potential Energy:");
        				Label energyResult = new Label(energy + "kcal/mol");
        				subContent.addComponent(resultLabel);
        				subContent.addComponent(energyResult);
        				resultWindow.setContent(subContent);
        		        
        				// Center it in the browser window
        		        resultWindow.center();

        		        // Open it in the UI
        		        addWindow(resultWindow);
        		}
        		else {
        			Label resultLabel = new Label();
    				resultLabel.setCaption("Your job has completed.\n"
    						+ "Please find your files in the current working directory.");
    				subContent.addComponent(resultLabel);
    				resultWindow.setContent(subContent);
    		        
    				// Center it in the browser window
    		        resultWindow.center();

    		        // Open it in the UI
    		        addWindow(resultWindow);
        		}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        grid.addComponent(save, 0, 3);
        grid.setSpacing(true);
        
        setContent(grid);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
