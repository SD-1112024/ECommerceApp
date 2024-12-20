# E-Commerce Platform - Java Application

This is a simple console-based e-commerce platform implemented in Java. The application allows users to register, log in, browse products, add items to a shopping cart, and place orders. Admin users can manage products and view orders. The system supports role-based authentication, product management, and order processing.

## Features

- **User Authentication**: 
  - Users can register, log in, and update their profile.
  - Admin users have special privileges to manage products and orders.

- **Product Management**: 
  - View a list of available products.
  - Search for products by name or category.
  - Admin users can add and remove products.

- **Shopping Cart**:
  - Users can add products to the cart and view the total price.
  - Admin users can manage the cart functionality.

- **Order Management**:
  - Users can place orders from their cart.
  - Admin users can view all orders, update the status of orders, and process payments.

- **Role-based Access Control**: 
  - Differentiates between regular users and admins.

## Technologies Used

- Java 8+
- Collections (Lists, Maps)
- Object-Oriented Programming (OOP) Concepts

## How to Run the Application

### Prerequisites

- JDK 8 or higher must be installed.
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line interface can be used.

### Running the Application

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/ecommerce-platform.git
    cd ecommerce-platform
    ```

2. Compile and run the `ECommerceApp` class:
    ```bash
    javac ECommerceApp.java
    java ECommerceApp
    ```

3. Follow the on-screen instructions to log in or register and start interacting with the platform.

## Main Classes

### `User`
This class represents a user with attributes like `username`, `password`, `role`, `name`, and `email`. It includes methods for getting and setting these attributes.

### `Product`
Represents a product in the store. Attributes include `id`, `name`, `price`, `description`, and `category`. It contains methods to manipulate and display product details.

### `Cart`
Manages the shopping cart. The cart holds a collection of products and calculates the total price. It provides methods for adding, removing, and clearing items.

### `Order`
Represents an order placed by a user. It holds a list of products, calculates the total amount, and tracks the status and payment status of the order.

### `ProductController`
Manages a list of products in the system. Provides methods for retrieving all products, adding/removing products, and searching/filtering products.

### `UserController`
Handles user authentication, registration, and profile management. It supports logging in, registering, and updating user profiles.

### `OrderController`
Manages the order processing workflow. It allows users to place orders, and admin users to update order statuses and process payments.

### `AdminController`
Provides methods for admin users to manage products and orders, such as adding/removing products and viewing/updating order statuses.

### `ECommerceApp`
The main entry point of the application. It offers a console-based user interface for users to interact with the platform, including login, product browsing, cart management, and order placement.

## Example Workflow

1. **Login/Register**: Users can either log in with an existing account or register a new one.
2. **Browse Products**: Users can view a list of products or search for them by name or category.
3. **Add to Cart**: Users can add selected products to their shopping cart.
4. **Place Order**: Users can place an order based on the items in their cart.
5. **Admin Features**: Admin users can manage products and orders (add/remove products, update order statuses, process payments).

## Role Management

- **Admin**: Can add/remove products, view all orders, update order statuses, and process payments.
- **Customer**: Can browse products, add items to the cart, place orders, and update their profile.

## Example Interaction

1. **Login Prompt**:  
   Enter username and password to log in.

2. **Main Menu Options**:  
   Choose from options like viewing products, managing the cart, placing orders, and accessing admin features (if an admin).

3. **Admin Options**:  
   If logged in as an admin, you will be able to add/remove products and manage orders.

## Known Issues

- The application does not persist data across sessions. It is a simple in-memory solution.
- The UI is console-based and lacks advanced features like a GUI or web interface.

## Contributions

Feel free to fork the repository, make changes, and create pull requests. Contributions are welcome to improve features or add new functionalities.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
