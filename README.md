# 🔺 MaxHeap with Lifespan — Java Implementation

This project implements a **Max Heap** data structure in Java, extended with a concept of **lifespan-based event decay**. Each element (event) in the heap has both a **priority** and a **lifespan**, and expired events are removed automatically over time. This model is useful for **real-time systems**, **job schedulers**, or **temporary resource management**.

---

## 🚀 Features

- ✅ **Max Heap Insertion**  
  Insert `Event(priority, lifespan)` into the heap while maintaining heap properties.

- 🔍 **Peek Max**  
  Access the event with the highest priority (and longest lifespan in case of tie) without removing it.

- ⛏️ **Extract Max**  
  Remove and return the highest-priority event from the heap.

- ⏳ **Lifespan Decrement**  
  Decrease the lifespan of all events at every iteration (simulating time passage).

- 🧹 **Automatic Expiration**  
  Events with `lifespan ≤ 0` are removed from the heap after each decrement.

- 🌳 **Level-Based Heap Tree Printing**  
  Visualize the current state of the heap as a binary tree.

---

## 📦 Project Structure

├── Main2.java           # Driver program for testing the heap

├── MaxHeap.java         # Contains heap logic and lifespan management

├── Event.java           # Represents an event with priority and lifespan

---

## 🧠 How It Works

### Event Model

Each event has:
- `priority`: Integer value that determines heap order.
- `lifespan`: Number of "ticks" it stays valid.

### Heap Rules

- The heap is a **max-heap**, prioritizing higher `priority`.
- If priorities are equal, the event with **longer lifespan** is prioritized.
- Lifespans decrease after each operation cycle.
- Expired events are **cleaned up** automatically.

---

## 📊 Example Output

Heap Tree (priority-lifespan):

Level 0: (98,1) 

Level 1: (83,4) (80,2) 

Level 2: (71,4) (66,4) (38,2) (32,3) 

Level 3: (56,2) (38,2) (60,3) (5,5) (5,4) (25,3) (28,2) (14,4) 

Level 4: (42,2) (2,1) (30,3) (2,4) (41,4) 

Extracted event -> Priority: 98, Original Lifespan: 1


Heap Tree (priority-lifespan):

Level 0: (83,3) 

Level 1: (80,1) (66,3) 

Level 2: (71,3) (60,2) (38,1) (32,2) 

Level 3: (56,1) (38,1) (41,3) (5,4) (5,3) (25,2) (28,1) (14,3) 

Level 4: (42,1) (30,2) (2,3) 

Extracted event -> Priority: 83, Original Lifespan: 4


Heap Tree (priority-lifespan):

Level 0: (71,2) 

Level 1: (66,2) (60,1) 

Level 2: (32,1) (41,2) (5,3) (5,2) 

Level 3: (25,1) (14,2) (2,2) (30,1) 

Extracted event -> Priority: 71, Original Lifespan: 4


Heap Tree (priority-lifespan):
Level 0: (66,1) 

Level 1: (41,1) (5,2) 

Level 2: (5,1) (14,1) (2,1) 

Extracted event -> Priority: 66, Original Lifespan: 4


Heap Tree (priority-lifespan):

Level 0: (5,1) 

Extracted event -> Priority: 5, Original Lifespan: 5


Heap Tree (priority-lifespan):

(empty)

## 🧰 Technologies
	•	💻 Java 17+
	•	🧠 Object-Oriented Programming
	•	🧱 Array-backed Binary Heap
	•	🔁 Recursive Heapify Logic

