# Collaborative Graphical Editor

## Introduction
This project implements a collaborative graphical editor, enabling multiple users to simultaneously edit a shared sketch in real time. Users can draw, manipulate, and interact with various shapes (rectangles, ellipses, line segments, and freehand curves). A server maintains a global view of the sketch, synchronizing changes across all clients.

---

## Features
- Collaborative real-time editing with multiple clients.
- Supports shapes such as ellipses, rectangles, line segments, and freehand curves.
- Shape manipulation, including recoloring, moving, and deleting.
- Consistent global sketch view managed by a server.

---

## Architecture Overview
- **Client-Server Model:**  
  Clients communicate with the server for all sketch updates. The server synchronizes updates across connected clients.

- **Message Passing:**  
  - Clients send requests (e.g., add, delete, move shapes) to the server.  
  - The server processes requests and broadcasts updates to all clients.

- **Thread Management:**  
  - The server uses threads to handle communication with each client.  
  - Clients manage GUI updates and server communication on separate threads.

---

## Project Components
- **Editor:** Client application for drawing and editing shapes.
- **SketchServer:** Server application maintaining the global sketch state.
- **EditorCommunicator:** Handles client-server communication.
- **SketchServerCommunicator:** Manages server-side communication for each client.
- **Sketch:** Manages the state and mapping of shapes in the sketch.
- **Shape Interface:** Includes implementations for Ellipse, Rectangle, Segment, and Polyline.
- **EchoServer:** A debugging tool that echoes back client messages for protocol testing.

---

## Usage Instructions
1. Clone the repository and compile the code.
2. Start the server using the `SketchServer`.
3. Launch clients using the `Editor`.
4. Use the `EchoServer` for testing protocols during development.

---

## Key Functionalities
1. **Adding Shapes:**  
   Clients request the server to add new shapes. The server assigns a unique ID and notifies all clients to update their local sketches.

2. **Manipulating Shapes:**  
   Clients perform actions like moving, recoloring, or deleting shapes via server requests, ensuring synchronized updates.

3. **Real-Time Synchronization:**  
   New clients receive the current global sketch state when connecting to the server.

---

## Development Notes
- Sketch methods are synchronized to ensure thread safety.
- Messages follow a simple text-based protocol for easy parsing and debugging.
- Use `EchoServer` to test message handling before deploying the full server.

---

## Credits
Authors: Lydia Jin and Sudi Zhao as part of CS 10: Problem Solving via Object Oriented Programming, certain files include skeleton files provided by the course.
