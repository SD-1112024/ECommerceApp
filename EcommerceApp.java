import java.util.*;
import java.io.*;

class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

class Product {
    private int id;
    private String name;
    private double price;
    private String description;

    public Product(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
    }
}

class Cart {
    private Map<Integer, Product> products = new HashMap<>();
    private double totalPrice;

    public void addProduct(Product product) {
        if (!products.containsKey(product.getId())) {
            products.put(product.getId(), product);
            totalPrice += product.getPrice();
        }
    }

    public void removeProduct(int productId) {
        Product product = products.remove(productId);
        if (product != null) {
            totalPrice -= product.getPrice();
        }
    }

    public void clearCart() {
        products.clear();
        totalPrice = 0.0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public String toString() {
        return "Cart [products=" + products + ", totalPrice=" + totalPrice + "]";
    }
}

class Order {
    private int orderId;
    private List<Product> products;
    private double totalAmount;
    private String status;

    public Order(int orderId, List<Product> products) {
        this.orderId = orderId;
        this.products = products;
        this.totalAmount = calculateTotalAmount();
        this.status = "Pending";
    }

    private double calculateTotalAmount() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Order [orderId=" + orderId + ", totalAmount=" + totalAmount + ", status=" + status + "]";
    }
}

class ProductController {
    private static List<Product> products = new ArrayList<>();
    private static int productIdCounter = 1;

    static {
        products.add(new Product(productIdCounter++, "Laptop", 1000.00, "High-performance laptop"));
        products.add(new Product(productIdCounter++, "Phone", 500.00, "Latest smartphone"));
        products.add(new Product(productIdCounter++, "Headphones", 150.00, "Noise-cancelling headphones"));
    }

    public static List<Product> getAllProducts() {
        return products;
    }

    public static Product getProductById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void removeProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }
}

class UserController {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("admin", "admin123", "admin"));
        users.add(new User("customer", "customer123", "customer"));
    }

    public static User login(String username, String password) {
        return users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    public static void register(String username, String password, String role) {
        users.add(new User(username, password, role));
    }
}

class OrderController {
    private static List<Order> orders = new ArrayList<>();
    private static int orderIdCounter = 1;

    public static void placeOrder(Cart cart) {
        Order order = new Order(orderIdCounter++, new ArrayList<>(cart.getProducts().values()));
        orders.add(order);
        System.out.println("Order placed successfully: " + order);
    }

    public static List<Order> getAllOrders() {
        return orders;
    }

    public static Order getOrderById(int orderId) {
        return orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
    }

    public static void updateOrderStatus(int orderId, String status) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }
}

class AdminController {
    public static void addNewProduct(String name, double price, String description) {
        ProductController.addProduct(new Product(ProductController.getAllProducts().size() + 1, name, price, description));
    }

    public static void removeProduct(int productId) {
        ProductController.removeProduct(productId);
    }
}

class ECommerceApp {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isAuthenticated = false;
    private static String currentUsername;
    private static User currentUser;
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        System.out.println("Welcome to the E-Commerce Platform!");

        while (true) {
            if (!isAuthenticated) {
                System.out.println("\n1. Login");
                System.out.println("2. Register");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    login();
                } else if (choice == 2) {
                    register();
                } else {
                    System.out.println("Invalid choice!");
                }
            } else {
                mainMenu();
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = UserController.login(username, password);
        if (currentUser != null) {
            System.out.println("Login successful!");
            isAuthenticated = true;
            currentUsername = username;
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        UserController.register(username, password, "customer");
        System.out.println("Registration successful!");
    }

    private static void mainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. View Products");
        System.out.println("2. View Cart");
        System.out.println("3. Place Order");
        System.out.println("4. View Orders");
        System.out.println("5. Admin Options");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            viewProducts();
        } else if (choice == 2) {
            viewCart();
        } else if (choice == 3) {
            placeOrder();
        } else if (choice == 4) {
            viewOrders();
        } else if (choice == 5) {
            adminOptions();
        } else if (choice == 6) {
            logout();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void viewProducts() {
        System.out.println("\nAvailable Products:");
        List<Product> products = ProductController.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void viewCart() {
        System.out.println("\nYour Cart:");
        System.out.println(cart);
    }

    private static void placeOrder() {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty! Add products to your cart first.");
            return;
        }
        OrderController.placeOrder(cart);
        cart.clearCart();
    }

    private static void viewOrders() {
        List<Order> orders = OrderController.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private static void adminOptions() {
        if (!currentUser.getRole().equals("admin")) {
            System.out.println("You do not have admin privileges.");
            return;
        }

        System.out.println("\nAdmin Options:");
        System.out.println("1. Add New Product");
        System.out.println("2. Remove Product");
        System.out.println("3. View All Orders");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            addNewProduct();
        } else if (choice == 2) {
            removeProduct();
        } else if (choice == 3) {
            viewAllOrders();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void addNewProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        AdminController.addNewProduct(name, price, description);
        System.out.println("Product added successfully.");
    }

    private static void removeProduct() {
        System.out.print("Enter product ID to remove: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        AdminController.removeProduct(productId);
        System.out.println("Product removed successfully.");
    }

    private static void viewAllOrders() {
        List<Order> orders = OrderController.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private static void logout() {
        isAuthenticated = false;
        currentUser = null;
        System.out.println("You have logged out.");
    }
}
