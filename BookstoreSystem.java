import java.util.*;

// Book class to represent books in the system
class Book {
    String isbn;
    String title;
    String author;
    double price;
    int quantity;

    public Book(String isbn, String title, String author, double price, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Price: $%.2f | Qty: %d",
                isbn, title, author, price, quantity);
    }
}

// Order class to represent customer orders
class Order {
    int orderId;
    String customerName;
    String shippingAddress;
    List<Book> books;
    Date orderDate;
    String status;

    public Order(int orderId, String customerName, String shippingAddress) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.books = new ArrayList<>();
        this.orderDate = new Date();
        this.status = "Processing";
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== Order #" + orderId + " ===\n");
        sb.append("Customer: " + customerName + "\n");
        sb.append("Address: " + shippingAddress + "\n");
        sb.append("Date: " + orderDate + "\n");
        sb.append("Status: " + status + "\n");
        sb.append("Books:\n");
        for (Book book : books) {
            sb.append("  - " + book.toString() + "\n");
        }
        return sb.toString();
    }
}

// === STACK IMPLEMENTATION (LIFO) ===
class MyStack {
    private ArrayList<Order> stack;
    public MyStack() {
        stack = new ArrayList<>();
    }
    public void push(Order order) {
        stack.add(order);
    }
    public Order pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack.remove(stack.size() - 1);
    }
    public Order peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
    public int size() {
        return stack.size();
    }
    public void display() {
        System.out.println("\n=== Stack Contents (Top to Bottom) ===");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println("Position " + (stack.size() - i) + ": Order #" + stack.get(i).orderId);
        }
    }
}

// === QUEUE IMPLEMENTATION (FIFO) ===
class MyQueue {
    private LinkedList<Order> queue;

    public MyQueue() {
        queue = new LinkedList<>();
    }

    // Enqueue operation - O(1)
    public void enqueue(Order order) {
        queue.addLast(order);
    }

    // Dequeue operation - O(1)
    public Order dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        return queue.removeFirst();
    }

    // Front operation - O(1)
    public Order front() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        return queue.getFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void display() {
        System.out.println("\n=== Queue Contents (Front to Rear) ===");
        int position = 1;
        for (Order order : queue) {
            System.out.println("Position " + position++ + ": Order #" + order.orderId);
        }
    }
}

// === SINGLY LINKED LIST IMPLEMENTATION ===
class SinglyLinkedList {
    class Node {
        Order data;
        Node next;

        Node(Order data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    // Insert at beginning - O(1)
    public void insertAtBeginning(Order order) {
        Node newNode = new Node(order);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Insert at end - O(n)
    public void insertAtEnd(Order order) {
        Node newNode = new Node(order);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Delete from beginning - O(1)
    public void deleteFromBeginning() {
        if (head != null) {
            head = head.next;
            size--;
        }
    }

    // Search - O(n)
    public Order search(int orderId) {
        Node current = head;
        while (current != null) {
            if (current.data.orderId == orderId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public void display() {
        System.out.println("\n=== Singly Linked List Contents ===");
        Node current = head;
        int position = 1;
        while (current != null) {
            System.out.println("Position " + position++ + ": Order #" + current.data.orderId);
            current = current.next;
        }
    }
}

// === SORTING ALGORITHMS ===
class SortingAlgorithms {

    // Bubble Sort - O(n²)
    public static void bubbleSort(List<Book> books) {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (books.get(j).title.compareTo(books.get(j + 1).title) > 0) {
                    // Swap
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }

    // Selection Sort - O(n²)
    public static void selectionSort(List<Book> books) {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (books.get(j).title.compareTo(books.get(minIdx).title) < 0) {
                    minIdx = j;
                }
            }
            // Swap
            Book temp = books.get(i);
            books.set(i, books.get(minIdx));
            books.set(minIdx, temp);
        }
    }

    // Insertion Sort - O(n²)
    public static void insertionSort(List<Book> books) {
        int n = books.size();
        for (int i = 1; i < n; i++) {
            Book key = books.get(i);
            int j = i - 1;
            while (j >= 0 && books.get(j).title.compareTo(key.title) > 0) {
                books.set(j + 1, books.get(j));
                j--;
            }
            books.set(j + 1, key);
        }
    }

    // Merge Sort - O(n log n)
    public static void mergeSort(List<Book> books, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(books, left, mid);
            mergeSort(books, mid + 1, right);
            merge(books, left, mid, right);
        }
    }

    private static void merge(List<Book> books, int left, int mid, int right) {
        List<Book> leftList = new ArrayList<>();
        List<Book> rightList = new ArrayList<>();

        for (int i = left; i <= mid; i++) {
            leftList.add(books.get(i));
        }
        for (int i = mid + 1; i <= right; i++) {
            rightList.add(books.get(i));
        }

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).title.compareTo(rightList.get(j).title) <= 0) {
                books.set(k++, leftList.get(i++));
            } else {
                books.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            books.set(k++, leftList.get(i++));
        }
        while (j < rightList.size()) {
            books.set(k++, rightList.get(j++));
        }
    }

    // Quick Sort - O(n log n) average, O(n²) worst
    public static void quickSort(List<Book> books, int low, int high) {
        if (low < high) {
            int pi = partition(books, low, high);
            quickSort(books, low, pi - 1);
            quickSort(books, pi + 1, high);
        }
    }

    private static int partition(List<Book> books, int low, int high) {
        Book pivot = books.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (books.get(j).title.compareTo(pivot.title) < 0) {
                i++;
                Book temp = books.get(i);
                books.set(i, books.get(j));
                books.set(j, temp);
            }
        }

        Book temp = books.get(i + 1);
        books.set(i + 1, books.get(high));
        books.set(high, temp);

        return i + 1;
    }
}

// === SEARCHING ALGORITHMS ===
class SearchingAlgorithms {

    // Linear Search - O(n)
    public static Order linearSearch(List<Order> orders, int orderId) {
        for (Order order : orders) {
            if (order.orderId == orderId) {
                return order;
            }
        }
        return null;
    }

    // Binary Search - O(log n)
    public static Order binarySearch(List<Order> orders, int orderId) {
        int left = 0;
        int right = orders.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (orders.get(mid).orderId == orderId) {
                return orders.get(mid);
            }

            if (orders.get(mid).orderId < orderId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }
}

// === MAIN SYSTEM CLASS ===
public class BookstoreSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static MyQueue orderQueue = new MyQueue();
    private static MyStack recentOrders = new MyStack();
    private static List<Order> allOrders = new ArrayList<>();
    private static List<Book> bookCatalog = new ArrayList<>();
    private static int nextOrderId = 1001;

    public static void main(String[] args) {
        initializeSampleData();

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    demonstrateStackOperations();
                    break;
                case 2:
                    demonstrateQueueOperations();
                    break;
                case 3:
                    demonstrateSortingAlgorithms();
                    break;
                case 4:
                    demonstrateSearchingAlgorithms();
                    break;
                case 5:
                    createNewOrder();
                    break;
                case 6:
                    processNextOrder();
                    break;
                case 7:
                    searchForOrder();
                    break;
                case 8:
                    performanceComparison();
                    break;
                case 9:
                    System.out.println("\nThank you for using the Bookstore System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===============================================");
        System.out.println("    ONLINE BOOKSTORE ORDER PROCESSING SYSTEM    ");
        System.out.println("===============================================");
        System.out.println("1. Demonstrate Stack Operations (Recent Orders)");
        System.out.println("2. Demonstrate Queue Operations (Order Processing)");
        System.out.println("3. Demonstrate Sorting Algorithms");
        System.out.println("4. Demonstrate Searching Algorithms");
        System.out.println("5. Create New Order");
        System.out.println("6. Process Next Order in Queue");
        System.out.println("7. Search for Order");
        System.out.println("8. Performance Comparison of Algorithms");
        System.out.println("9. Exit");
        System.out.print("\nEnter your choice: ");
    }

    private static void initializeSampleData() {
        // Initialize book catalog
        bookCatalog.add(new Book("001", "Java Programming", "John Smith", 45.99, 10));
        bookCatalog.add(new Book("002", "Data Structures", "Alice Johnson", 55.99, 8));
        bookCatalog.add(new Book("003", "Algorithms Design", "Bob Williams", 65.99, 5));
        bookCatalog.add(new Book("004", "Database Systems", "Carol Davis", 50.99, 12));
        bookCatalog.add(new Book("005", "Web Development", "David Brown", 40.99, 15));
        bookCatalog.add(new Book("006", "Python Basics", "Emma Wilson", 35.99, 20));
        bookCatalog.add(new Book("007", "Machine Learning", "Frank Miller", 75.99, 7));
        bookCatalog.add(new Book("008", "Cloud Computing", "Grace Taylor", 60.99, 9));

        // Create sample orders
        for (int i = 0; i < 3; i++) {
            Order order = new Order(nextOrderId++, "Customer " + (i+1), "Address " + (i+1));
            order.addBook(bookCatalog.get(i % bookCatalog.size()));
            order.addBook(bookCatalog.get((i+1) % bookCatalog.size()));
            allOrders.add(order);
            orderQueue.enqueue(order);
        }
    }

    private static void demonstrateStackOperations() {
        System.out.println("\n=== STACK OPERATIONS DEMONSTRATION ===");
        System.out.println("Stack follows LIFO (Last In, First Out) principle");

        // Clear stack and add sample orders
        recentOrders = new MyStack();

        System.out.println("\n1. Pushing orders to stack...");
        for (int i = 0; i < 3; i++) {
            Order order = allOrders.get(i);
            recentOrders.push(order);
            System.out.println("   Pushed Order #" + order.orderId);
        }

        recentOrders.display();

        System.out.println("\n2. Peek operation (view top without removing):");
        Order top = recentOrders.peek();
        if (top != null) {
            System.out.println("   Top order: #" + top.orderId);
        }

        System.out.println("\n3. Pop operation (remove and return top):");
        Order popped = recentOrders.pop();
        if (popped != null) {
            System.out.println("   Popped Order #" + popped.orderId);
        }

        recentOrders.display();

        System.out.println("\n4. Stack Properties:");
        System.out.println("   - Time Complexity: O(1) for push, pop, peek");
        System.out.println("   - Space Complexity: O(n)");
        System.out.println("   - Use Case: Recent order history, undo operations");
    }

    private static void demonstrateQueueOperations() {
        System.out.println("\n=== QUEUE OPERATIONS DEMONSTRATION ===");
        System.out.println("Queue follows FIFO (First In, First Out) principle");

        orderQueue.display();

        System.out.println("\n1. Front operation (view first without removing):");
        Order front = orderQueue.front();
        if (front != null) {
            System.out.println("   Front order: #" + front.orderId);
        }

        System.out.println("\n2. Enqueue operation (add to rear):");
        System.out.print("   Enter customer name: ");
        String name = scanner.nextLine();
        Order newOrder = new Order(nextOrderId++, name, "Sample Address");
        newOrder.addBook(bookCatalog.get(0));
        orderQueue.enqueue(newOrder);
        allOrders.add(newOrder);
        System.out.println("   Enqueued Order #" + newOrder.orderId);

        orderQueue.display();

        System.out.println("\n3. Queue Properties:");
        System.out.println("   - Time Complexity: O(1) for enqueue, dequeue, front");
        System.out.println("   - Space Complexity: O(n)");
        System.out.println("   - Use Case: Order processing, fairness in handling");
    }

    private static void demonstrateSortingAlgorithms() {
        System.out.println("\n=== SORTING ALGORITHMS DEMONSTRATION ===");

        // Create a list of books to sort
        List<Book> booksToSort = new ArrayList<>();
        System.out.println("\nOriginal book list:");
        for (int i = 0; i < 5; i++) {
            Book book = bookCatalog.get((i * 2) % bookCatalog.size());
            booksToSort.add(new Book(book.isbn, book.title, book.author, book.price, book.quantity));
            System.out.println("  " + (i+1) + ". " + book.title);
        }

        System.out.println("\nChoose sorting algorithm:");
        System.out.println("1. Bubble Sort - O(n²)");
        System.out.println("2. Selection Sort - O(n²)");
        System.out.println("3. Insertion Sort - O(n²)");
        System.out.println("4. Merge Sort - O(n log n)");
        System.out.println("5. Quick Sort - O(n log n) average");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Book> testBooks = new ArrayList<>(booksToSort);
        long startTime = System.nanoTime();

        switch (choice) {
            case 1:
                SortingAlgorithms.bubbleSort(testBooks);
                System.out.println("\nUsing BUBBLE SORT:");
                System.out.println("- Compares adjacent elements and swaps");
                System.out.println("- Simple but inefficient for large datasets");
                break;
            case 2:
                SortingAlgorithms.selectionSort(testBooks);
                System.out.println("\nUsing SELECTION SORT:");
                System.out.println("- Finds minimum element and places at beginning");
                System.out.println("- Fewer swaps than bubble sort");
                break;
            case 3:
                SortingAlgorithms.insertionSort(testBooks);
                System.out.println("\nUsing INSERTION SORT:");
                System.out.println("- Builds sorted array one element at a time");
                System.out.println("- Efficient for small or nearly sorted data");
                break;
            case 4:
                SortingAlgorithms.mergeSort(testBooks, 0, testBooks.size() - 1);
                System.out.println("\nUsing MERGE SORT:");
                System.out.println("- Divide and conquer approach");
                System.out.println("- Guaranteed O(n log n) but uses extra space");
                break;
            case 5:
                SortingAlgorithms.quickSort(testBooks, 0, testBooks.size() - 1);
                System.out.println("\nUsing QUICK SORT:");
                System.out.println("- Partition-based divide and conquer");
                System.out.println("- Fast average case, in-place sorting");
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        long endTime = System.nanoTime();

        System.out.println("\nSorted book list (by title):");
        for (int i = 0; i < testBooks.size(); i++) {
            System.out.println("  " + (i+1) + ". " + testBooks.get(i).title);
        }

        System.out.println("\nTime taken: " + (endTime - startTime) + " nanoseconds");
    }

    private static void demonstrateSearchingAlgorithms() {
        System.out.println("\n=== SEARCHING ALGORITHMS DEMONSTRATION ===");

        System.out.println("\nCurrent orders in system:");
        for (Order order : allOrders) {
            System.out.println("  Order #" + order.orderId + " - " + order.customerName);
        }

        System.out.print("\nEnter Order ID to search: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1. LINEAR SEARCH:");
        long startTime = System.nanoTime();
        Order found = SearchingAlgorithms.linearSearch(allOrders, searchId);
        long endTime = System.nanoTime();

        if (found != null) {
            System.out.println("   Found: Order #" + found.orderId);
            System.out.println("   Customer: " + found.customerName);
        } else {
            System.out.println("   Order not found!");
        }
        System.out.println("   Time: " + (endTime - startTime) + " nanoseconds");
        System.out.println("   Complexity: O(n) - checks each element");

        // Sort orders for binary search
        List<Order> sortedOrders = new ArrayList<>(allOrders);
        sortedOrders.sort((a, b) -> Integer.compare(a.orderId, b.orderId));

        System.out.println("\n2. BINARY SEARCH (on sorted list):");
        startTime = System.nanoTime();
        found = SearchingAlgorithms.binarySearch(sortedOrders, searchId);
        endTime = System.nanoTime();

        if (found != null) {
            System.out.println("   Found: Order #" + found.orderId);
            System.out.println("   Customer: " + found.customerName);
        } else {
            System.out.println("   Order not found!");
        }
        System.out.println("   Time: " + (endTime - startTime) + " nanoseconds");
        System.out.println("   Complexity: O(log n) - divides search space in half");
        System.out.println("   Note: Requires sorted data!");
    }

    private static void createNewOrder() {
        System.out.println("\n=== CREATE NEW ORDER ===");

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter shipping address: ");
        String address = scanner.nextLine();

        Order newOrder = new Order(nextOrderId++, name, address);

        System.out.println("\nAvailable books:");
        for (int i = 0; i < bookCatalog.size(); i++) {
            Book book = bookCatalog.get(i);
            System.out.println((i+1) + ". " + book.title + " - $" + book.price);
        }

        System.out.print("\nHow many books to add? ");
        int numBooks = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numBooks; i++) {
            System.out.print("Enter book number (1-" + bookCatalog.size() + "): ");
            int bookNum = scanner.nextInt();
            scanner.nextLine();

            if (bookNum >= 1 && bookNum <= bookCatalog.size()) {
                Book selectedBook = bookCatalog.get(bookNum - 1);
                newOrder.addBook(new Book(selectedBook.isbn, selectedBook.title,
                        selectedBook.author, selectedBook.price, 1));
            }
        }

        // Add to queue and other data structures
        orderQueue.enqueue(newOrder);
        recentOrders.push(newOrder);
        allOrders.add(newOrder);

        System.out.println("\nOrder created successfully!");
        System.out.println(newOrder);
    }

    private static void processNextOrder() {
        System.out.println("\n=== PROCESS NEXT ORDER ===");

        if (orderQueue.isEmpty()) {
            System.out.println("No orders in queue to process!");
            return;
        }

        Order order = orderQueue.dequeue();
        order.status = "Completed";

        System.out.println("Processing order:");
        System.out.println(order);
        System.out.println("\nOrder has been processed and removed from queue!");
    }

    private static void searchForOrder() {
        System.out.println("\n=== SEARCH FOR ORDER ===");

        System.out.print("Enter Order ID to search: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order found = SearchingAlgorithms.linearSearch(allOrders, orderId);

        if (found != null) {
            System.out.println("\nOrder found:");
            System.out.println(found);
        } else {
            System.out.println("\nOrder not found!");
        }
    }

    private static void performanceComparison() {
        System.out.println("\n=== PERFORMANCE COMPARISON ===");

        // Create test data
        System.out.print("Enter number of elements to test (e.g., 100, 1000, 5000): ");
        int n = scanner.nextInt();
        scanner.nextLine();

        List<Book> testData = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            testData.add(new Book(
                    String.valueOf(i),
                    "Book" + rand.nextInt(10000),
                    "Author" + i,
                    rand.nextDouble() * 100,
                    rand.nextInt(50)
            ));
        }

        System.out.println("\n=== SORTING ALGORITHMS COMPARISON ===");
        System.out.println("Testing with " + n + " elements...\n");

        // Test each sorting algorithm
        String[] algorithms = {"Bubble", "Selection", "Insertion", "Merge", "Quick"};
        long[] times = new long[5];

        // Bubble Sort
        List<Book> testCopy = new ArrayList<>(testData);
        long start = System.nanoTime();
        SortingAlgorithms.bubbleSort(testCopy);
        times[0] = System.nanoTime() - start;

        // Selection Sort
        testCopy = new ArrayList<>(testData);
        start = System.nanoTime();
        SortingAlgorithms.selectionSort(testCopy);
        times[1] = System.nanoTime() - start;

        // Insertion Sort
        testCopy = new ArrayList<>(testData);
        start = System.nanoTime();
        SortingAlgorithms.insertionSort(testCopy);
        times[2] = System.nanoTime() - start;

        // Merge Sort
        testCopy = new ArrayList<>(testData);
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(testCopy, 0, testCopy.size() - 1);
        times[3] = System.nanoTime() - start;

        // Quick Sort
        testCopy = new ArrayList<>(testData);
        start = System.nanoTime();
        SortingAlgorithms.quickSort(testCopy, 0, testCopy.size() - 1);
        times[4] = System.nanoTime() - start;

        // Display results
        System.out.println("Algorithm      | Time (ms)    | Time Complexity | Space Complexity");
        System.out.println("---------------|--------------|-----------------|------------------");
        System.out.printf("Bubble Sort    | %10.3f | O(n²)           | O(1)\n", times[0]/1000000.0);
        System.out.printf("Selection Sort | %10.3f | O(n²)           | O(1)\n", times[1]/1000000.0);
        System.out.printf("Insertion Sort | %10.3f | O(n²)           | O(1)\n", times[2]/1000000.0);
        System.out.printf("Merge Sort     | %10.3f | O(n log n)      | O(n)\n", times[3]/1000000.0);
        System.out.printf("Quick Sort     | %10.3f | O(n log n)*     | O(log n)\n", times[4]/1000000.0);
        System.out.println("\n* Quick Sort average case. Worst case is O(n²)");

        // Find best performer
        int bestIndex = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[bestIndex]) {
                bestIndex = i;
            }
        }
        System.out.println("\nBest performer for " + n + " elements: " + algorithms[bestIndex] + " Sort");

        System.out.println("\n=== SEARCHING ALGORITHMS COMPARISON ===");

        // Create ordered list for binary search
        List<Order> testOrders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            testOrders.add(new Order(i, "Customer" + i, "Address" + i));
        }

        int searchTarget = n / 2; // Search for middle element

        // Linear Search
        start = System.nanoTime();
        SearchingAlgorithms.linearSearch(testOrders, searchTarget);
        long linearTime = System.nanoTime() - start;

        // Binary Search
        start = System.nanoTime();
        SearchingAlgorithms.binarySearch(testOrders, searchTarget);
        long binaryTime = System.nanoTime() - start;

        System.out.println("\nSearching for element in middle of " + n + " elements:");
        System.out.println("Algorithm     | Time (µs)    | Time Complexity | Best Use Case");
        System.out.println("--------------|--------------|-----------------|----------------");
        System.out.printf("Linear Search | %10.3f | O(n)            | Unsorted data\n", linearTime/1000.0);
        System.out.printf("Binary Search | %10.3f | O(log n)        | Sorted data\n", binaryTime/1000.0);

    }
}
