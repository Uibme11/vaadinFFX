package my.vaadin.ffx;

import javax.servlet.annotation.WebServlet;
import ffx.numerics.Erf;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
	private Job job = new Job();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    	VerticalLayout layout = new VerticalLayout();
    	//Label title = new Label("Force Field X");
    	//layout.addComponent(title);
    	//layout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        
    	layout.addComponent(form);
    	layout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
       
    	// Bind information entered into single Job item
        final FieldGroup binder = new FieldGroup();
        BeanItem<Job> item = new BeanItem<Job>(job);
        binder.setItemDataSource(item);
        
        binder.bindMemberFields(form);
        
        // When save button is pressed, save user input into Job BeanItem
        Button save = new Button("Save");
        save.addClickListener(e -> {
        	try {
        		double result = ffx.numerics.Erf.erfc(1.00);
        		System.out.printf("XXXXXXX %f", result);
        		//Runtime.getRuntime().exec(command);
				binder.commit();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        layout.addComponent(save);
        layout.setSpacing(true);
        layout.setComponentAlignment(save, Alignment.MIDDLE_CENTER);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
