# Space Ball Game
### Created by Nash Smith & Justin Govender
#### An assignment in COMPX202 (Mobile Computing and Software Architecture)

![alt text][pic1]
![alt text][pic2]
![alt text][pic3]
![alt text][pic4]

#### The Brief:
##### Android Group Project
The goal of this exercise is to demonstrate the Android skills you have developed in previous
weeks with a larger application, bringing together a number of Android features. It should be
possible to run the program you create on Android 6.0 (Marshmallow) or more recent
phones or emulators.
This project should be completed by pairs of students working together.
As usual this project will use Git. Make commits for each 'release' you complete. Only one
Git repository is required for each team.
Overview
You will be building an application that utilizes most of the concepts you have learned about
Android development and object structures. The basic idea is to build an application in which
users 'throw' balls at targets.
* Throw is touch and drag. Options include taking into account direction, speed, and
possibly curve or spin of the drag operation to determine ball movement. You may
like to use the "Fling" gesture detector.
* Limit the touch and drag operation so as not to allow the user to drag the ball all the
way to a target.
* There should be ONE or more targets. I suggest using circles as targets because
detecting collisions between circles is much easier to code than detecting collisions
with more complex shapes.
* There should be TWO or more obstacles, again circles will be simplest. An obstacle
should effect the throw in some way. Possibilities include speed up, slow down,
bounce, anti-gravity, destroy, give/take points. You may think of others. A question to
keep in mind is 'how will the player know what to expect and what has happened
when interacting with obstacles?'
* All visible objects (ball, target, obstacles) should be built as a class hierarchy with a
common parent.

##### Task 1:
You are required to use an agile software methodology to develop your project. For example,
your project should begin with a planning phase in which you write down a list of the 'releases'
(product backlog) with a list of smaller tasks required by each release (iteration backlog); and
estimates of the time and number of lines of code you will require for each of the tasks. You
should have documentation for your planning, and documentation tracking the actual time
and code size as you complete tasks.
You can choose to either (1) use the generalised agile approach suggested above or (2) selected
one approach in particular (i.e. XP, Scrum) to base your project methodology on.
##### Task 2:
You need to create an application that consists of a ball game with supporting screens
(Activities). Your application should have:
* a game play screen (which will be full screen - no task bar or status bar) that implements
the ball game described in the overview above. It should also have a scoring system,
showing a point count on screen and some kind of win/lose system so that a game can
finish.
* A home screen, showing the name of your game, the names of the developers (you),
and having a 'start play' button.
* A high-score screen listing the 5 best scores achieved.
Your game should have at least ONE target and TWO obstacles.


Don't forget that your code should use a class hierarchy for the objects on screen.





[pic1]: https://i.ibb.co/rtdRr5K/ball1.jpg "Home Screen"
[pic2]: https://i.ibb.co/PY1S8cW/ball2.jpg "Play Screen"
[pic3]: https://i.ibb.co/JQ8yxrh/ball3.jpg "End Game Screen"
[pic4]: https://i.ibb.co/M5QgQ9g/ball4.jpg "Highscores Screen"
