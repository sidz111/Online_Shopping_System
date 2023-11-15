import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private int productId;
    private String name;
    private double price;
    private int quantityInStock;

    public Product(int productId, String name, double price, int quantityInStock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name + ", Price: $" + price + ", Quantity in Stock: " + quantityInStock;
    }
}

class ShoppingCart {
    private List<Product> products;
    private double totalPrice;

    public ShoppingCart() {
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalPrice += product.getPrice();
        product.setQuantityInStock(product.getQuantityInStock() - 1);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        totalPrice -= product.getPrice();
        product.setQuantityInStock(product.getQuantityInStock() + 1);
    }

    public void viewCart() {
        if (products.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.println("Total Price: $" + totalPrice);
        }
    }

    public void clearCart() {
        products.clear();
        totalPrice = 0.0;
    }
}

class User {
    private static int nextUserId = 1;

    private int userId;
    private String username;
    private String password;
    private String address;
    private String email;

    public User(String username, String password, String address, String email) {
        this.userId = nextUserId++;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Address: " + address + ", Email: " + email;
    }
}

class Order {
    private static int nextOrderId = 1;

    private int orderId;
    private List<Product> products;
    private String orderDate;
    private double totalPrice;
    private int userId;

    public Order(List<Product> products, double totalPrice, int userId) {
        this.orderId = nextOrderId++;
        this.products = products;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", User ID: " + userId + ", Total Price: $" + totalPrice + ", Order Date: " + orderDate;
    }
}

public class OnlineShoppingSystem {
    private static List<Product> productList = new ArrayList<>();
    private static List<User> userList = new ArrayList<>();
    private static List<Order> orderHistory = new ArrayList<>();
    private static ShoppingCart shoppingCart = new ShoppingCart();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        initializeProducts();
        displayWelcomeScreen();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMainMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showProductList();
                    break;
                case 2:
                    registerUser(scanner);
                    break;
                case 3:
                    loginUser(scanner);
                    break;
                case 4:
                    viewUserProfile();
                    break;
                case 5:
                    addToCart(scanner);
                    break;
                case 6:
                    viewShoppingCart();
                    break;
                case 7:
                    placeOrder();
                    break;
                case 8:
                    displayOrderHistory();
                    break;
                case 9:
                    System.out.println("Thank you for using our Online Shopping System. Have Nice Day!");
                    break;
                default:
                    System.out.println("Invalid Choice. Please enter a valid option.");
            }
        } while (choice != 9);

        scanner.close();
    }

    private static void initializeProducts() {
        productList.add(new Product(1, "Laptop", 1999.99, 10));
        productList.add(new Product(2, "Smartphone", 499.99, 15));
        productList.add(new Product(3, "Headphones", 69.99, 20));
        // Add more products as needed
    }

    private static void displayWelcomeScreen() {
        System.out.println("Welcome to My Online Shopping System!");
        System.out.println("=======================================");
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. View Product List");
        System.out.println("2. Register");
        System.out.println("3. Login");
        System.out.println("4. View User Profile");
        System.out.println("5. Add to Cart");
        System.out.println("6. View Shopping Cart");
        System.out.println("7. Place Order");
        System.out.println("8. View Order History");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void showProductList() {
        System.out.println("\nProduct List:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.println("\nUser Registration:");
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        User newUser = new User(username, password, address, email);
        userList.add(newUser);
        System.out.println("Registration successful. Welcome, " + username + "!");
    }

    private static void loginUser(Scanner scanner) {
        System.out.println("\nUser Login:");
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful. Welcome back, " + username + "!");
                return;
            }
        }

        System.out.println("Invalid username or password. Login failed.");
    }

    private static void viewUserProfile() {
        if (loggedInUser != null) {
            System.out.println("\nUser Profile:");
            System.out.println(loggedInUser);
        } else {
            System.out.println("You need to log in first.");
        }
    }

    private static void addToCart(Scanner scanner) {
        if (loggedInUser != null) {
            showProductList();
            System.out.print("Enter the Product ID to add to your cart: ");
            int productId = scanner.nextInt();

            Product selectedProduct = getProductById(productId);
            if (selectedProduct != null && selectedProduct.getQuantityInStock() > 0) {
                shoppingCart.addProduct(selectedProduct);
                System.out.println(selectedProduct.getName() + " added to your cart.");
            } else if (selectedProduct != null) {
                System.out.println("Sorry, " + selectedProduct.getName() + " is out of stock.");
            } else {
                System.out.println("Invalid Product ID. Please try again.");
            }
        } else {
            System.out.println("You need to log in first.");
        }
    }

    private static void viewShoppingCart() {
        if (loggedInUser != null) {
            shoppingCart.viewCart();
        } else {
            System.out.println("You need to log in first.");
        }
    }

    private static void placeOrder() {
        if (loggedInUser != null && !shoppingCart.getProducts().isEmpty()) {
            Order newOrder = new Order(new ArrayList<>(shoppingCart.getProducts()), shoppingCart.getTotalPrice(), loggedInUser.getUserId());
            orderHistory.add(newOrder);
            shoppingCart.clearCart();
            System.out.println("Order placed successfully. Your order ID is: " + newOrder.getOrderId());
        } else if (loggedInUser == null) {
            System.out.println("You need to log in first.");
        } else {
            System.out.println("Your shopping cart is empty. Add items before placing an order.");
        }
    }

    private static void displayOrderHistory() {
        if (loggedInUser != null && !orderHistory.isEmpty()) {
            System.out.println("\nOrder History:");
            for (Order order : orderHistory) {
                if (order.getUserId() == loggedInUser.getUserId()) {
                    System.out.println(order);
                }
            }
        } else if (loggedInUser == null) {
            System.out.println("You need to log in first.");
        } else {
            System.out.println("Your order history is empty.");
        }
    }

    private static Product getProductById(int productId) {
        for (Product product : productList) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
}
