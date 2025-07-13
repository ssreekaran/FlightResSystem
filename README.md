# ✈️ Flight Reservation System

A Java-based Flight Reservation System that simulates airline operations including flight scheduling, seat reservations, and passenger management. Built with object-oriented design principles and custom exception handling.

## 🚀 Features

- **Flight Management**: Create and manage flights with different aircraft types
- **Passenger Handling**: Add, remove, and manage passenger information
- **Seat Reservation**: Reserve seats with different classes (Economy, Business, First)
- **Flight Status Tracking**: Monitor flight status (ON_TIME, DELAYED, CANCELLED)
- **Data Persistence**: Load flight data from external text files
- **Custom Exceptions**: Comprehensive error handling with custom exceptions

## 🛠️ Technologies Used

- Java 8+
- Object-Oriented Programming (OOP) principles
- Custom Exception Handling
- File I/O Operations

## 📂 Project Structure

```
FlightResSystem/
├── src/
│   ├── Aircraft.java          # Base aircraft class
│   ├── Flight.java            # Flight management and operations
│   ├── FlightManager.java     # Flight scheduling and management
│   ├── LongHaulFlight.java    # Extended flight type for long-haul flights
│   ├── Passenger.java         # Passenger information handling
│   ├── Reservation.java       # Reservation management
│   └── FlightReservationSystem.java  # Main application
├── flights.txt                # Sample flight data
└── README.md
```

## 🚦 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Git (optional, for version control)

### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/FlightResSystem.git
   cd FlightResSystem
   ```

2. Compile the Java files:
   ```bash
   javac *.java
   ```

3. Run the application:
   ```bash
   java FlightReservationSystem
   ```

## 🎮 Usage

### Available Commands

- `LIST` - Display all available flights
- `RES <flightNum> <name> <passport> <seatType>` - Make a reservation
- `CANCEL <flightNum> <name> <passport>` - Cancel a reservation
- `SEATS <flightNum>` - Check available seats
- `PASMAN <flightNum>` - View passenger manifest
- `MYRES` - View your reservations
- `Q` or `QUIT` - Exit the program

## 📝 Customization

You can customize the flight data by modifying the `flights.txt` file with your own flight information in the following format:

```
<flightNumber>,<origin>,<destination>,<departureTime>,<duration>,<capacity>,<type>
```