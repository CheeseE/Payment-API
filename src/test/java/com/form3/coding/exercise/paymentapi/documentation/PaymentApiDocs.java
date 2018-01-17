package com.form3.coding.exercise.paymentapi.documentation;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.ReferencedTypesSupportingTypesStrategy;
import com.structurizr.analysis.SpringComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.*;
import com.structurizr.view.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This is a C4 representation of the PaymentAPI.
 */
@Ignore
public class PaymentApiDocs {
    private static final String VERSION = "1";

    private static final long WORKSPACE_ID = 38061;
    private static final String API_KEY = "aeb181af-5b7d-432e-87b8-6528f0a42c6c";
    private static final String API_SECRET = "24de9d3c-9138-4518-be26-287594a212eb";

    @Test
    public void uploadDocuments() throws Exception {

        ClassLoader.getSystemClassLoader().loadClass("com.form3.coding.exercise.paymentapi.PaymentApiApplication");

        Workspace workspace = new Workspace("Spring Boot Payment API",
                "This is a C4 representation of a simple payment api (https://github.com/CheeseE/Payment-API)");
        workspace.setVersion(VERSION);
        Model model = workspace.getModel();

        // create the basic model (the stuff we can't get from the code)
        SoftwareSystem paymentApi = model.addSoftwareSystem("Payment API", "Allows users to create and list payments.");
        Person client = model.addPerson("JavaScript client", "A client that interacts with the service");
        client.uses(paymentApi, "Uses");

        Container webApplication = paymentApi.addContainer(
                "REST Application", "Allows users to create and list payments", "Spring Boot");
        Container relationalDatabase = paymentApi.addContainer(
                "Relational Database", "Stores information regarding the payments, parties.", "H2");
        client.uses(webApplication, "Uses", "HTTP");
        webApplication.uses(relationalDatabase, "Reads from and writes to", "JDBC");

        SpringComponentFinderStrategy springComponentFinderStrategy = new SpringComponentFinderStrategy(new ReferencedTypesSupportingTypesStrategy(false));
        springComponentFinderStrategy.setIncludePublicTypesOnly(false);

        // and now automatically find all Spring @Controller, @Component, @Service and @Repository components
        ComponentFinder componentFinder = new ComponentFinder(
                webApplication,
                "com.form3.coding.exercise.paymentapi",
                springComponentFinderStrategy);

        componentFinder.exclude(".*Formatter.*");
        componentFinder.findComponents();

        // connect the user to all of the Spring MVC controllers
        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_REST_CONTROLLER))
                .forEach(c -> client.uses(c, "Uses", "HTTP"));

        // connect all of the repository components to the relational database
        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_REPOSITORY))
                .forEach(c -> c.uses(relationalDatabase, "Reads from and writes to", "JDBC"));

        // finally create views
        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(paymentApi, "context", "The System Context diagram for the Spring Payment API system.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        ContainerView containerView = views.createContainerView(paymentApi, "containers", "The Containers diagram for the Spring Payment API system.");
        containerView.addAllPeople();
        containerView.addAllSoftwareSystems();
        containerView.addAllContainers();

        ComponentView componentView = views.createComponentView(webApplication, "components", "The Components diagram for the Spring Payment API web application.");
        componentView.addAllComponents();
        componentView.addAllPeople();
        componentView.add(relationalDatabase);

        // tag and style some elements
        paymentApi.addTags("Spring Payment API");
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_REST_CONTROLLER)).forEach(c -> c.addTags("Spring REST Controller"));
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_SERVICE)).forEach(c -> c.addTags("Spring Service"));
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_REPOSITORY)).forEach(c -> c.addTags("Spring Repository"));
        relationalDatabase.addTags("Database");

        Styles styles = views.getConfiguration().getStyles();
        styles.addElementStyle("Spring Payment API").background("#6CB33E").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#519823").color("#ffffff").shape(Shape.Person);
        styles.addElementStyle(Tags.CONTAINER).background("#91D366").color("#ffffff");
        styles.addElementStyle("Database").shape(Shape.Cylinder);
        styles.addElementStyle("Spring REST Controller").background("#D4F3C0").color("#000000");
        styles.addElementStyle("Spring Service").background("#6CB33E").color("#000000");
        styles.addElementStyle("Spring Repository").background("#95D46C").color("#000000");


        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }

}