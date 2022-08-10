# AI course's projects

## Informed and Uniformed search:
In this project, we are going to solve puzzle 8 

1- First, we need to implement the succesor function, which is enough to consider a two-dimensional array (3*3) and an empty place to move.
Then generate all possible states as children of nodes.

2- We should receive an initial state that is unorganized from the numbers 1 to 8 and one empty place from the input and the output then show the sorted state.

3- There is a cost to move the puzzles, just subtract the number of the puzzle from the corresponding house to get the cost of moving the puzzle.
For example, you want to move the number 8 to house eight, the cost of moving it is equal to 0, or you want to move the number 5 to room 8, which will cost 3.

4- For uninformed algorithms: 
  - BFS
  - DFS
  - BDS
  - UCS.

5- For Informed algorithms, first we need to find a suitable heuristic that is both acceptable and consistent, and then proceed:
  - A* 
  - RBFS


## Local Search 

In this project, we intend to implement the widely used Local Search algorithms on a problem. There is a garden containing **m** fruits and each fruit has a specific amount of energy.
A group of students including **n** people come to the garden to eat these fruits. Every student needs a minimum amount of energy to be satisfied.
We want to distribute the fruits of the garden among the students in such a way that everyone is satisfied and we have the least wasted energy. 
The amount of wasted energy for a person is equal to the amount of energy received from eating fruits minus the amount of energy needed to satisfy that person, and the amount of total wasted energy is equal to the sum of the amount of wasted energy of everyone.

For example, in a garden containing 4 fruits with energies of 5, 7, 3 and 9, if 2 students enter, they need energy of 10 and 8, one way to divide the fruits among the students is to give the first 2 fruits to the first student (5 and 7) and give the second student the last fruit (9). In this case, the amount of wasted energy is equal to **5+7-10+9-8 = 3**. But if we give the second and third fruits to the first student (7 and 3) and
Let's give the last one to the second student, the amount of wasted energy is equal to **3+7-10+9-8 = 1**.
So the second method is a better way to divide the fruits among the students. (note that only one person can eat each fruit and also only one person can eat the whole fruit, for example, you cannot eat half of a fruit)

As you know, Local Search algorithms may not find the best answer. We are going to implement 2 algorithms:

- Hill Climbing
- Simulated Annealing

And also finding a suitable fitness function to reach the optimal answer.

## CSP

This project is actually a supplement to the local search project. We have to find a solution to the problem by assuming that some students are sitting next to each other, and also students next to each other cannot eat fruits with the same energy. That is, provide a way to spread the fruits so that the all students become satisfied and two students sitting next to each other do not eat fruits with the same energy. Note that you only need to provide a method for distributing the fruits which satisfies the mentioned limitations and it is not necessary that this method has the least waste.

Try the backtracking method by appropriately transforming the problem into a constraint satisfaction problem (csp).
Solve and implement the following heuristics:
- Minimum Remaining value
- ‫‪Least‬‬ ‫‪Constraining‬‬ ‫‪Value‬‬
- ‫‪Forward‬‬ ‫‪Checking‬‬
