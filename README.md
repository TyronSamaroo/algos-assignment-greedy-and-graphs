# Greedy Algorithms & Graphs Assignment (Due May 10th 11:59PM)

First, instructions which you MUST follow exactly:
* git fork this repository to your own github account, then clone it from there
* work on the java files in your preferred editor, but the only changes you introduce should be in the java files. I should not get any extra project files.
* In your java files, make sure your name is commented at the top of the java files as a co-author.
* your code should have variables namedInThisStyle (snakecase), it should be clearly written and understandable, and it should have relevant comments where necessary. Please do auto-indent before submission. Make sure your formatting and spacing is on-point.
* Please submit something that COMPILES. If you know your code does not compile without commenting things out, make sure you are commenting in your code adequately to make up for that.
* Put your writeups + drawings to the problems in <b>*ONE* pdf file titled Your_Full_Name_Greedy_Graphs_Writeup.pdf</b> in this same directory. You must include this write-up (even if it's blank) because otherwise I will have to ask you about it. Please type if you can, and present it neatly. Clearly label which question you are answering.
* In the write-up, if you collaborated with anybody in the class or used an online source for any inspiration, please list who you worked with or cite the source. You must do this.
* Th******e only files you should be updating are the java files and adding your one write-up PDF. There should be no other files.
* From your account, select "Create a pull request" where the base is this account's (CS-Queens-College-Yao) repository.
* created a pull request from your fork to this original version
* Submit your PR link via this Google form: https://forms.gle/KaHQf7FMQxWC2ZnXA

## 1. Experiments Scheduling

You are running a physics experiment with n complicated steps that you must do in order, and students sign-up for some steps to help. Your experiment requires n steps, and each of the m students gives you a list of which steps they can help out with (steps require special skills). From experience, you know things run most smoothly when you have as little switching of shifts as possible.

For example, if your experiment has <1, 2, 3, 4, 5, 6> steps
* Student 1: <1, 2, 3, 5>
* Student 2: <2, 3, 4>
* Student 3: <1, 4, 5, 6> <br>
Your optimal solution would be:<br>
Student 1 <1, 2, 3>, Student 2 does no steps, Student 3 does <4, 5, 6>. Only 1 switch

Another example if your experiment has 8 steps <1, 2, 3, 4, 5, 6, 7, 8>
* Student 1: <5, 7, 8>
* Student 2: <2, 3, 4, 5, 6>
* Student 3: <1, 5, 7, 8>
* Student 4: <1, 3, 4, 8> <br>
Your optimal solutions could be any one of these: <br>
  * Student 1 does no steps, Student 2 does <2, 3, 4, 5, 6>, Student 3 does <1, 7, 8>, Student 4 does no steps --> 2 switches (student 3 does step 1, student 2 does up to 6, student 3 picks up again to do 7 and 8)<br>
  * Student 1 does <7, 8>, Student 2 does <2, 3, 4, 5, 6>, Student 3 does <1>, Student 4 does nothing --> 2 switches <br>
  * Student 1 does no steps, Student 2 does <2, 3, 4, 5, 6>, Student 3 does <7, 8>, Student 4 does <1> --> 2 switches <br>

#### Given: n number of steps, m number of students that give you a list of steps (sorted) they can participate in. Assume there's a lookup table where you can find if student X signed up for step Y in O(1), so no need to factor that into your runtime.
#### Find: An optimal way to schedule students to steps such that there is the least amount of switching as possible.

##### (a) Describe the optimal substructure of this problem
#### (b) Describe the greedy algorithm that could find an optimal way to schedule the students
#### (c) Code your greedy algorithm in the file "PhysicsExperiment.java" under the "scheduleExperiments" method where it says "Your code here". Read through the documentation for that method. Note that I've already set up the lookup table automatically for every test case. Do not touch the other methods except possibly the main method to build your own test cases (but delete/comment out your own test cases in the submission). Run the code without your implementation first and you should see this: [![initial-output.png](https://i.postimg.cc/SKBDtqgL/initial-output.png)](https://postimg.cc/qtGsNfVg) <br> With your implementation, your final output should look something like this: [![final-output-example.png](https://i.postimg.cc/FsLw9WLH/final-output-example.png)](https://postimg.cc/YhtdRxR5)
#### (d) What is the runtime complexity of your greedy algorithm? Again, you don't need to factor in the setup of the lookup table, just your scheduling algorithm.
#### (e) In your PDF, based on your answer to part b, give a full proof that your greedy algorithm returns an optimal solution.


#### Turn in: In your write-up file you should have answers to parts: a, b, d with clear and careful explanations! Coding part c in the file PhysicsExperiment.java, I will be looking at the final output

## 2. Public, Public Transit
It's 2009 and you are staying in Oak City for the summer. Because of its history leading to people-centric urban planning, it has a free, well-planned, and timely public transit system, unlike the MTA. Since smartphones aren't widespread yet, you have a map with a lot to work with. The map is a connected graph and there are directed edges (u, v) between the stations on the map, and each edge e=(u,v) has the following data:
* length(e): the time, in minutes, it takes to travel from u to v
* first(e)>= 0 the first train of the day that passes through u to v
* freq(e) > 0 how frequently the train comes from u to v

So the train passes through that a station at first(e), first(e) + freq(e), first(e) + 2freq(e), and so on... 
Transfers are also super efficient because the platforms are well designed. S
****o you can assume that as soon as you arrive at somewhere to get off, <b>if</b> your transfer train arrives at the same time you can catch it. <br> With your copy of the map, you store the data in files and want to make an API that'll tell you the shortest time it takes to get from a station S to station T at a certain starting time. Since you are the only one using the API, you just care about getting places from a single starting station. For the API, you'll design an algorithm. You don't care how many transfers you have to make.

<b>Given</b>: starting time X minutes from 5:30am, station S, station T, three adjacency matrices (one for length(e), another for first(e), another for freq(e)). The first(e) adjacency matrix could just contain the first arrival time as number of minutes from 5:30am, so 6:01am would be 31.<br>
<b>Goal</b>: Find the shortest time it would take to get from S to T.

Note: If at Y trains arrive at 5:59a, 6:07a, 6:15a, 6:23a, etc. If I make it to Y at 6:21a I'll have to wait 2 minutes but if I make it at 6:23a I can get on right away.

#### (a) Describe an algorithm solution to this problem. Feel free to talk about how you would adapt an algorithm we covered in class.
#### (b) What is the complexity of your proposed solution in (a)?
#### (c) See the file FastestRoutePublicTransit.java, the method "shortestTime". Note you can run the file and it'll output the solution from that method. Which algorithm is this implementing?
#### (d) In the file FastestRoutePublicTransit.java, how would you use the existing code to help you implement your algorithm? The existing code only handles one piece of data per edge, so describe some modifications.
#### (e) What's the current complexity of "shortestTime" given V vertices and E edges? How would you make the "shortestTime" implementation faster? Describe any algorithm changes or data structure changes. What's the complexity of the optimal implementation?
#### (g) Code! In the file FastestRoutePublicTransit.java, in the method "myShortestTravelTime", implement the algorithm you described in part (a) using your answers to (d). Don't need to implement the optimal data structure.
#### (g) Extra credit (15 points): I haven't set up the test cases for "myShortestTravelTime", which takes in 3 matrices. Set up those three matrices (first, freq, length) to make a test case for your myShortestTravelTime method. Make a call to your method from main passing in the test case you set up.

#### Turn in: In your write-up file you should have answers to parts: a-e! Coding part f and (optional) g in the file FastestRoutePublicTransit.java
