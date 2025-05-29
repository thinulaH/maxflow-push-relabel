# Push-Relabel Maximum Flow Algorithm

This is a Java implementation of the **Push-Relabel (Preflow-Push)** algorithm to solve the **maximum flow problem** in a flow network. The program supports both manual and batch testing using input files.

---

## 📌 Features

* Implements the Push-Relabel algorithm using adjacency lists.
* Supports:

  * Custom input file (`input.txt`)
  * Automatic benchmarking of all `.txt` files in `src/benchmarks/`
* Shows internal steps (optional) like push and relabel actions.
* Clean, modular Java design using OOP principles.

---

## 🧠 Algorithm Overview

The **Push-Relabel algorithm** maintains a preflow from the source and pushes flow locally using node heights and excess flow. It relabels nodes when they cannot push further, and repeats until no active nodes remain.

* **Time Complexity**: Worst-case `O(V²E)`
* **Space Complexity**: `O(V + E)` using adjacency lists

---

## 📁 Project Structure

```
.
├── src/
│   ├── benchmarks/          # Folder for batch input files
│   ├── main/java/
│   │   ├── Graph.java       # Core algorithm
│   │   ├── Edge.java        # Flow edge with residual support
│   │   ├── Parser.java      # Input file parser
│   │   └── MaxFlow.java     # Main class (user interface)
│   └── input.txt            # Example input graph
```

---

## 📄 Example Input Format

```
6
0 1 10
0 2 5
1 3 10
2 3 10
3 4 7
4 5 10
```

* First line: number of nodes
* Next lines: `from to capacity`

---

## 🚀 How to Run

1. **Compile and Run the Project**

   ```bash
   javac src/main/java/*.java
   java MaxFlow
   ```

2. **Choose an option**

   * Option 1: Run `input.txt`
   * Option 2: Run all benchmark `.txt` files in `src/benchmarks/`

---

## 📦 Build & Push to GitHub

1. Initialize Git (if not already)

   ```bash
   git init
   git add .
   git commit -m "Initial commit with Push-Relabel algorithm"
   git remote add origin https://github.com/<your-username>/<repo-name>.git
   git push -u origin main
   ```

---

## 🧑‍💻 Author

* **Thinula Harischandra**
  University of Westminster
  ID: `w2051872`

---

## 📜 License

This project is for academic and learning purposes. You may use and modify it with attribution.
