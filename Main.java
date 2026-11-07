import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String status);
}

// Customer receives SMS notifications
class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        System.out.println("SMS to " + name + ": your order is now " + status);
    }
}

// Kitchen screen receives updates except when the order is delivered
class KitchenScreen implements Observer {
    @Override
    public void update(String status) {
        if (!status.equals("Delivered")) {
            System.out.println("Kitchen screen: " + status);
        }
    }
}

// Order is the subject being observed
class Order {
    private String status = "Placed";
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setStatus(String newStatus) {
        status = newStatus;
        System.out.println("\nOrder is now: " + status);

        // Notify only observers currently on the list
        for (Observer observer : observers) {
            observer.update(status);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Order order = new Order();

        Customer customer = new Customer("Rahim");
        KitchenScreen kitchen = new KitchenScreen();

        order.addObserver(customer);
        order.addObserver(kitchen);

        order.setStatus("Cooking");
        order.setStatus("Out for delivery");
        order.setStatus("Delivered");
    }
}
