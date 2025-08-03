// Abstract Products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

// Concrete Products for Windows
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a Windows button.");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering a Windows checkbox.");
    }
}

// Concrete Products for Mac
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a Mac button.");
    }
}

class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering a Mac checkbox.");
    }
}

// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory for Windows
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// Concrete Factory for Mac
class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

public class Main {
    // Factory
    private GUIFactory factory;
    private Button button;
    private Checkbox checkbox;

    public Main(GUIFactory factory) {
        this.factory = factory;
    }

    public void renderUI() {
        button.render();
        checkbox.render();
    }
    public static void main(String[] args) {
        // Client code
        GUIFactory factory;
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            factory = new WindowsFactory();
        } else if (os.contains("mac")) {
            factory = new MacFactory();
        } else {
            throw new UnsupportedOperationException("Unsupported OS: " + os);
        }

        Main app = new Main(factory);
        app.button = factory.createButton();
        app.checkbox = factory.createCheckbox();
        app.renderUI();
    }
}