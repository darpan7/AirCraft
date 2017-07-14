# AirCraft

The goal of this exercise is to demonstrate programming ability by developing a simple software subsystem. Work can be done in any language. 

System Implementation Requirements
1.	Develop one or more data structures to hold the state of an individual AC. (AirCraft class)
2.	Develop one or more data structures to hold the state of the AC queue. (List<AirCraft>, because size is undefined, I needed to use List, if that was not the case, int[] would fit best in order to implement heap logic)
3.	Define data structures and/or constants needed to represent requests. 
=> implemented enqueue/dequeue in List. 
For enqueue: 
	Considered elements of List as binary tree. Switching elements between parent & its children with priority. So at the end, on every enqueue call, first element of List is candidate for 1st dequeue call. that is all guaranteed. Other work is done at dequeue part.
For dequeue:
	Taking out first element of List. Putting last element to first index and then checking priority starting from start. This way, I am making sure that first element is highest priority in list which is a candidate for another dequeue call.

6.	Please send your complete project including artifacts needed to import project in a workspace (include .project etc.).  Please add README if needed to run the project locally.
7.	Bonus: Expose the implementation as REST endpoints for integration with a 3rd party UI.
I have 2 entrypoints. 
1. Console:
	javac com.aircraft.AqmRequestProcess
	java com.aircraft.AqmRequestProcess
2. Rest:
	start the server. (In eclipse, I have installed tomcat 7).
	Rest response is coming as json. 
	On browser 3 requests:
	1. http://localhost:8087/AC/rest/binary/start
	2. http://localhost:8087/AC/rest/binary/enqueue/Passenger/Small (You can call this multiple times with changing Type/Size, But Type comes first before Size. Every call, it adds AirCraft into queue).
	3. http://localhost:8087/AC/rest/binary/dequeue (Result comes as "Who Updated" attribute inside json response)
 
8.	Additional Bonus:  UI to show queue and add/remove planes
	I added html file(with worst html design :( ). But I would like to extend this for UI with this way:
	1. I can change rest response to application/html, and return html as a string, with add/remove button, display of updated queue. and on buttons, i can do href with link equals to one of the rest url.
	2. Other way is, to use jsp pages and servlets. Following MVC patterns, UI can be done with same logic mentioned in 1st point.

Note:
Although, I have used List to store, I believe using my own LinkedList type class will work as well. I need to make sure when I want to add in middle, i will just change the refs. Helper class has simple implementation (first idea that came), it works as well, but changing into priority rules will change that class as well. 
OrderJSON: Normally, JSON does not guruanteed ordering inside json text. However, i am able to make it with my custom class. 
