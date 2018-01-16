package com.form3.coding.exercise.paymentapi.documentation;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.SpringComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.*;
import com.structurizr.view.*;
import org.junit.Test;

/**
 * This is a C4 representation of the PaymentAPI.
 */
public class PaymentApiDocs {

    @Test
    public void uploadDocuments() throws Exception{
        Workspace workspace = new Workspace("Getting Started", "This is a model of my software system.");
        Model model = workspace.getModel();

        // create the basic model (the stuff we can't get from the code)
        SoftwareSystem paymentApi = model.addSoftwareSystem(Location.Internal, "Form3 Payment API", "");
        Person user = model.addPerson(Location.External, "User", "");
        user.uses(paymentApi, "Uses");

        Container webApplication = paymentApi.addContainer("Web Application", "", "Apache Tomcat 7.x");
        Container relationalDatabase = paymentApi.addContainer("Relational Database", "", "H2DB");
        user.uses(webApplication, "Uses");
        webApplication.uses(relationalDatabase, "Reads from and writes to");

        // and now automatically find all Spring @Controller, @Component, @Service and @Repository components
        ComponentFinder componentFinder = new ComponentFinder(webApplication, "com.form3.coding.exercise.paymentapi",
                new SpringComponentFinderStrategy());
        componentFinder.findComponents();

        // finally create some views
        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(paymentApi, "SystemContext", "System Context diagram.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        ContainerView containerView = views.createContainerView(paymentApi, "Container", "");
        containerView.addAllPeople();
        containerView.addAllSoftwareSystems();
        containerView.addAllContainers();

        ComponentView componentView = views.createComponentView(webApplication, "Component", "");
        componentView.addAllComponents();

        Styles styles = views.getConfiguration().getStyles();
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#08427b").color("#ffffff").shape(Shape.Person);
        styles.addElementStyle(Tags.CONTAINER).background("#08427b").color("#ffffff").shape(Shape.Cylinder);

        StructurizrClient structurizrClient = new StructurizrClient("aeb181af-5b7d-432e-87b8-6528f0a42c6c", "24de9d3c-9138-4518-be26-287594a212eb");
        structurizrClient.putWorkspace(38061, workspace);
    }

}